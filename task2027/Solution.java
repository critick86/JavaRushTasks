package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> words = detectAllWords(crossword, "home", "same", "oln", "re");
        for (Word w :
                words) {
            System.out.println(w);
        }
/*
        String[] s = directions(crossword, 2, 1, 'n');
        for (String st :
                s) {
            System.out.println(st);
        }
*/
        /*

Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    private static String[] directions(int[][] crossword, int x, int y, int secondChar) {
        StringBuilder resultBuilder = new StringBuilder();
        if (y > 0) {
            if (crossword[y - 1][x] == secondChar) resultBuilder.append("N ");
        }
        if (x < crossword[y].length - 1 && y > 0) {
            if (crossword[y - 1][x + 1] == secondChar) resultBuilder.append("NE ");
        }
        if (x < crossword[y].length - 1) {
            if (crossword[y][x + 1] == secondChar) resultBuilder.append("E ");
        }
        if (y < crossword.length - 1 && x < crossword[y].length - 1) {
            if (crossword[y + 1][x + 1] == secondChar) resultBuilder.append("SE ");
        }
        if (y < crossword.length - 1) {
            if (crossword[y + 1][x] == secondChar) resultBuilder.append("S ");
        }
        if (x > 0 && y < crossword.length - 1) {
            if (crossword[y + 1][x - 1] == secondChar) resultBuilder.append("SW ");
        }
        if (x > 0) {
            if (crossword[y][x - 1] == secondChar) resultBuilder.append("W ");
        }
        if (x > 0 && y > 0) {
            if (crossword[y - 1][x - 1] == secondChar) resultBuilder.append("NW ");
        }
        return resultBuilder.toString().split(" ");
    }
    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> result = new ArrayList<>();
        for (String word :
                words) {
            boolean found = false;
            for (int y = 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[y].length; x++) {
                    if (word.charAt(0) == crossword[y][x]) {
                        for (String direction :
                                directions(crossword, x, y, word.charAt(1))) {
                            if (!direction.equals("")) {
                                int tempX = x, tempY = y;
                                StringBuilder sb = new StringBuilder();
                                sb.append((char) crossword[tempY][tempX]);
                                while (!sb.toString().equals(word)) {
                                    switch (direction) {
                                        case "S": {
                                            tempY++;
                                            break;
                                        }
                                        case "N": {
                                            tempY--;
                                            break;
                                        }
                                        case "E": {
                                            tempX++;
                                            break;
                                        }
                                        case "W": {
                                            tempX--;
                                            break;
                                        }
                                        case "NE": {
                                            tempX++;
                                            tempY--;
                                            break;
                                        }
                                        case "NW": {
                                            tempX--;
                                            tempY--;
                                            break;
                                        }
                                        case "SW": {
                                            tempX--;
                                            tempY++;
                                            break;
                                        }
                                        case "SE": {
                                            tempX++;
                                            tempY++;
                                            break;
                                        }

                                    }
                                    try {
                                        sb.append((char) crossword[tempY][tempX]);
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        break;
                                    }

                                }
                                if (sb.toString().equals(word)) {
                                    found = true;
                                    result.add(new Word(sb.toString()));
                                    result.get(result.size() - 1).setStartPoint(x, y);
                                    result.get(result.size() - 1).setEndPoint(tempX, tempY);
                                }

                            }
                        }

                    }
                }
            }
        }

        return result;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
