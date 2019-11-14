package siwy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            //Zmienne
            //Ścieżka pliku wejściowego
            String filePath = "lit10a.in";
            //Odczytanie pierwszej linii z pliku wejściowego (długości nazwiska Jasia)
            String sLengthLine = Files.readAllLines(Paths.get(filePath)).get(0);
            //Konwersja pierwszej linii na integer
            int sLength = Integer.parseInt(sLengthLine);
            //Odczytanie drugiej linii z pliku wejściowego (nazwisko Jasia)
            String sJasia = Files.readAllLines(Paths.get(filePath)).get(1);
            //Odczytanie trzeciej linii z pliku wejściowego (nazwisko Małgosi)
            String sMalgosi = Files.readAllLines(Paths.get(filePath)).get(2);

            //Wypisanie liczby inwersji w konsoli
            System.out.println(CountSteps(sJasia.toCharArray(), sMalgosi.toCharArray(), sLength));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Metoda służąca do zliczenia minimalnej ilości inwersji
    static int CountSteps(char[] s1, char[] s2, int size) {
        int i = 0, j;
        int result = 0;

        //Iteracja pierwszej linii
        while (i < size) {
            j = i;

            //Wyszukiwanie indeksu pierwszego elementu w pierwszej linii, który jest równy elementowi w drugimj linii
            while (s1[j] != s2[i]) {
                j += 1;
            }

            while (i < j) {

                //Zamiana elementów miejscami
                char temp = s1[j];
                s1[j] = s1[j - 1];
                s1[j - 1] = temp;
                j -= 1;
                result += 1;
            }
            i += 1;
        }
        return result;
    }
}