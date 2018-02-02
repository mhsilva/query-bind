package com.tr.query.bind.querybind.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    private void createNewQuery(String newSQL) {
        BufferedWriter writer = null;
        try {

            writer = new BufferedWriter(new FileWriter("src/main/resources/" + "/NewQuery.sql"));
            writer.write(newSQL);

        } catch (IOException e) {
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
            }
        }
    }

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

        sb.delete(0, sb.lastIndexOf("Executing Statement:") + 20);

        sb.append(sqlOriginalArray[sqlOriginalArray.length - 1]);
        GenericCollection findByNome = service.findByNome("contador");
        if (findByNome == null) {
            findByNome = new GenericCollection();
            findByNome.setCount(0);
            findByNome.setNome("contador");
        }
        findByNome.setCount(findByNome.getCount() + 1);
        service.save(findByNome);
        return sb.toString().replaceAll("^\\s+", "");

    }

    private void getParameters(String line,
        int count) {

        if (count == 2) {
            mapTypeAndParameters.put(2, getStringInsideBracket(line).split(","));
        } else if (count == 3) {
            mapTypeAndParameters.put(3, getStringInsideBracket(line).split(","));
        }
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
