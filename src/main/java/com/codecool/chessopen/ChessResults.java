package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {
    private static Map<String, Integer> names = new HashMap<>();

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        List<String> results = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;

        try {
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);
            String line = br.readLine();
            List<String> name = new ArrayList<>();

            while (line != null) {
                name.add(line);
                line = br.readLine();
            }
            for (String n : name) {
                String[] data = n.split(",");
                int points = 0;
                for (int i = 1; i < data.length; i++) {
                    points += Integer.parseInt(data[i]);
                }

                names.put(data[0], points);
            }
            results = names.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("File not found!");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }

}
