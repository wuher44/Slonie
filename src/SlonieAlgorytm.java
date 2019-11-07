import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class SlonieAlgorytm {


    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());

        Scanner fileScanner = new Scanner(file);

        int n = fileScanner.nextInt();
        int[] wagi = new int[n];
        for (int i = 0; i < n; i++) {
            wagi[i] = fileScanner.nextInt();
        }

        int[] uklad = new int[n];
        for (int i = 0; i < n; i++) {
            uklad[i] = fileScanner.nextInt();
        }

        int[] cel = new int[n];
        for (int i = 0; i < n; i++) {
            cel[i] = fileScanner.nextInt();
        }
        System.out.println(algorytm(n, wagi, uklad, cel));


    }

    public static long algorytm(int n, int[] m, int[] a, int[] b) {

        for (int i = 0; i < a.length; i++) {
            a[i] = a[i] - 1;
            b[i] = b[i] - 1;
        }

        // { Konstrukcja permutacji p. }

        int[] p = new int[n];
        for (int i = 0; i < p.length; i++) {
            p[b[i]] = a[i];
        }

        // { Rozkład p na cykle proste. }

        boolean[] odw = new boolean[n];

        int c = 0;

        List<List<Integer>> cc = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            cc.add(i, new ArrayList<>());
        }


        for (int i = 0; i < n; i++) {
            if (!odw[i]) {
                c = c + 1;
                int x = i;

                while (!odw[x]) {
                    odw[x] = true;
                    cc.get(c).add(x);
                    x = p[x];
                }
            }
        }

        // { Wyznaczenie parametrów cykli. }

        int minW = Integer.MAX_VALUE;

        long[] suma = new long[c + 1];
        int[] min = new int[c + 1];

        for (int i = 1; i <= c; i++) {

            suma[i] = 0;
            min[i] = Integer.MAX_VALUE;

            for (int e : cc.get(i)) {
                suma[i] = suma[i] + m[e];
                min[i] = Math.min(min[i], m[e]);
            }
            minW = Math.min(minW, min[i]);


        }
        // { Obliczenie wyniku. }

        long wynik = 0;

        for (int i = 1; i <= c; i++) {
            long metoda1 = suma[i] + ((cc.get(i).size() - 2) * min[i]);
            long metoda2 = suma[i] + (min[i] + (cc.get(i).size() + 1) * minW);
            wynik = wynik + Math.min(metoda1, metoda2);

        }
        return wynik;
    }
}
