package siwy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    //Zmienne
    //Maksymalna liczba znaków na tablicę
    private static final int MAXN = 1000000;
    //Tablice
    private static List<Integer>[] tab_u = new ArrayList[MAXN];
    private static List<Integer>[] tab_w = new ArrayList[MAXN];
    private static List<Integer> p = new ArrayList<>(Collections.nCopies(MAXN, 0));
    private static int odd[] = new int[MAXN + 1];
    private static int n;

    public static void main(String[] args) {
        try {
            //Zmienne
            //Ścieżka pliku wejściowego
            String filePath = "lit10b.in";
            //Odczytanie pierwszej linii z pliku wejściowego (długości nazwiska Jasia)
            String sLengthLine = Files.readAllLines(Paths.get(filePath)).get(0);
            //Konwersja pierwszej linii na integer
            n = Integer.parseInt(sLengthLine);
            //Odczytanie drugiej linii z pliku wejściowego (nazwisko Jasia)
            char[] u = Files.readAllLines(Paths.get(filePath)).get(1).toCharArray();
            //Odczytanie trzeciej linii z pliku wejściowego (nazwisko Małgosi)
            char[] w = Files.readAllLines(Paths.get(filePath)).get(2).toCharArray();

            //Populacja tab_u i tab_w
            for (int i = 0; i < MAXN; i++) {
                tab_u[i] = new ArrayList<>();
                tab_w[i] = new ArrayList<>();
            }

            //Wypełnianie poszczególnych ArrayList() w tab_u i tab_w znakami z pliku wejściowego
            for (int i = n - 1; i >= 0; --i) {
                tab_u[(int) u[i]].add(i);
            }

            for (int i = n - 1; i >= 0; --i) {
                tab_w[(int) w[i]].add(i);
            }

            //Iteracja kolejnych liter z linii w pliku wejściowym
            for (int ch = 'A'; ch <= 'Z'; ++ch) {
                for (int i = 0; i < tab_u[ch].size(); ++i) {
                    p.set(tab_u[ch].get(i), tab_w[ch].get(i));
                }
            }

            System.out.println(countSteps());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metoda służąca do zliczenia minimalnej ilości inwersji
    public static long countSteps() {
        long result = 0;
        int zeroesCount = 0;
        while (zeroesCount < n) {
            Arrays.fill(odd, 0);
            zeroesCount = 0;

            for (int i = 0; i < n; ++i) {
                if (p.get(i) % 2 != 0) {
                    ++odd[p.get(i)];
                } else {
                    result += odd[p.get(i) + 1];
                }
                p.set(i, p.get(i) / 2);
                if (p.get(i) == 0) {
                    ++zeroesCount;
                }
            }
        }
        return result;
    }
}