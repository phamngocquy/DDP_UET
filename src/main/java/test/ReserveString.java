package test;
import java.util.Scanner;

public class ReserveString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        ReserveString reserveString = new ReserveString();
        while (true) {
            boolean check = false;
            if (str.contains("(") && str.contains(")")) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '(') {
                        for (int j = i + 1; j < str.length(); j++) {
                            if (str.charAt(j) == '(') {
                                break;
                            } else if (str.charAt(j) == ')') {
                                check = true;
//                                System.out.println(str.substring(i, j + 1));
                                String sub = String.valueOf(reserveString.reserver(str.substring(i, j + 1)));
                                str = str.replace(str.substring(i, j + 1), sub);
                                break;
                            }
                        }

                    }
                    if (check){
//                        System.out.println("str: "+ str);
                        break;
                    }
                }
            } else break;
        }
        System.out.println(str);
    }

    private StringBuilder reserver(String s) {
//        System.out.println("re: "+s);
        String result = s.replace("(", "");
        result = result.replace(")", "");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = result.length() - 1; i >= 0; i--) {
            stringBuilder.append(result.charAt(i));
        }
        return stringBuilder;
    }
}
