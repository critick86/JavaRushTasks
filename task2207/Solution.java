package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;


/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileString = reader.readLine();
        reader.close();
        System.out.println(fileString);
        Path filePath = Paths.get(fileString);
        List<String> textLines = Files.readAllLines(filePath);
        StringBuilder sb = new StringBuilder();
        for (String s :
                textLines) {
            sb.append(s).append(" ");
        }
        String string = sb.toString().trim();
        String[] strings;
        strings = string.replaceAll("\uFEFF", "").split("\\s+");
        for (int i = 0; i < strings.length - 1; i++) {
            for (int j = i + 1; j < strings.length; j++) {
                if (strings[i].trim().equals(new StringBuilder(strings[j].trim()).reverse().toString())) {
                    if (!result.contains(new Pair(strings[i], strings[j]))) {
                        result.add(new Pair(strings[i], strings[j]));
                    }
                }
            }
        }
    }

    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
