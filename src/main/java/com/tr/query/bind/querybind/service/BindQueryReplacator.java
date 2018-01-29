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

public class BindQueryReplacator {

    private Map<Integer, String[]> mapParametes = new HashMap<Integer, String[]>();

    private Map<Integer, String[]> mapTypes = new HashMap<Integer, String[]>();

    private String singleQuot = new String("'");

    /*
     * public static void main(String[] args) throws FileNotFoundException {
     * BindQueryReplacator bindQueryReplacator = new BindQueryReplacator();
     * bindQueryReplacator.createNewQuery(bindQueryReplacator.replaceBinds(bindQueryReplacator.getFileContents("OriginalQuery.sql")));
     * }
     */

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
        String[] arrayParameters = mapParametes.get(2);
        String[] arrayTypes = mapTypes.get(3);

        String[] teste = sqlOriginal.split("\\?");
        StringBuilder sb = new StringBuilder();

        for (String item : teste) {

            if (count <= teste.length - 2) {

                try {

                    if ("java.lang.String".equals(arrayTypes[count].replaceAll("^\\s+", "")) && !"null".equals(arrayParameters[count])) {
                        sb.append(new StringBuilder(item).append(singleQuot + arrayParameters[count].substring(1) + singleQuot));
                    } else {
                        sb.append(new StringBuilder(item).append(arrayParameters[count].substring(1)));
                    }

                } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    // when is package the quantity of binds "?" they're not the same of parameters
                }
            }

            count++;

        }

        sb.append(teste[teste.length - 1]);

        return sb.toString();

    }

    private void getParameters(String line,
        int count) {

        if (count == 2) {
            mapParametes.put(2, getStringInsideBracket(line).split(","));
        } else if (count == 3) {
            mapTypes.put(3, getStringInsideBracket(line).split(","));
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
