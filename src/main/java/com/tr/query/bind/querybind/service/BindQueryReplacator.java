package com.tr.query.bind.querybind.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BindQueryReplacator {

    private Map<Integer, String[]> mapTypeAndParameters = new HashMap<Integer, String[]>();

    private String singleQuot = new String("'");

    @Autowired
    private GenericCollectionServiceImpl service;

    public String replaceBinds(List<String> list) {
        int count = 0;

        String sqlOriginal = list.get(0);
        String[] arrayParameters = mapTypeAndParameters.get(2);
        String[] arrayTypes = mapTypeAndParameters.get(3);

        String[] sqlOriginalArray = sqlOriginal.split("\\?");
        StringBuilder sb = new StringBuilder();

        for (String item : sqlOriginalArray) {

            if (count <= sqlOriginalArray.length - 2) {

                try {

                    if ("java.lang.String".equals(arrayTypes[count].replaceAll("^\\s+", "")) && !"null".equals(arrayParameters[count].replaceAll("^\\s+", ""))) {
                        sb = (count == 0) ? sb.append(new StringBuilder(item).append(singleQuot + arrayParameters[count] + singleQuot))
                            : sb.append(new StringBuilder(item).append(singleQuot + arrayParameters[count].substring(1) + singleQuot));

                    } else {
                        sb = (count == 0) ? new StringBuilder(item).append(arrayParameters[count]) : sb.append(new StringBuilder(item).append(arrayParameters[count].substring(1)));
                    }

                } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    // when is package the quantity of binds "?" they're not the same of parameters
                }
            }

            count++;

        }

        sb.append(sqlOriginalArray[sqlOriginalArray.length - 1]);

        sb.delete(0, sb.lastIndexOf("Executing Statement:") + 20);

        GenericCollection findByNome = service.findByName("count");
        if (findByNome == null) {
            findByNome = new GenericCollection();
            findByNome.setValue("0");
            findByNome.setName("count");
        }
        findByNome.setValue(String.valueOf(Integer.parseInt(findByNome.getValue()) + 1));
        service.save(findByNome);
        return sb.toString().replaceAll("^\\s+", "");

    }

    private void getParameters(String line,
        int count) {

        if (count != 1)
            mapTypeAndParameters.put(count, getStringInsideBracket(line).split(","));

    }

    private String getStringInsideBracket(String input) {

        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(input);
        while (m.find()) {
            input = m.group(1);
        }
        return input;
    }

    public List<String> getFileContents(String text) {

        List<String> listFile = new ArrayList<String>();

        /*
         * // Get file from resources folder
         * ClassLoader classLoader = getClass().getClassLoader();
         * File file = new File(classLoader.getResource(fileName).getFile());
         */

        try (Scanner scanner = new Scanner(text)) {

            while (scanner.hasNextLine()) {

                listFile.add(scanner.nextLine());
            }

            scanner.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        listLineFiles(listFile);

        return listFile;

    }

    private void listLineFiles(List<String> list) {
        int count = 0;

        for (String item : list) {
            count++;
            System.out.println(item);
            getParameters(item, count);
        }
    }
}
