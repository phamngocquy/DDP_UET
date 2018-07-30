package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class bbbb {

    private void compute(List<Integer> list) {
        int min = list.size() + 1;
        for (int i = 0; i < list.size(); i++) {
            if (i >= min) {
                break;
            } else {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(j).equals(list.get(i))) {
                        if (min > j) min = j;
                    }
                }
            }
        }
        if (min == list.size() + 1) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(min));
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            integerList.add(scanner.nextInt());
        }
        bbbb bbb = new bbbb();
        bbb.compute(integerList);
    }
}