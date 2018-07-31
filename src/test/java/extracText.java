import core.helper.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extracText {
    public static void main(String[] args) {
        /**
         * \<(.+?)\>
         *  \[(.+?)\]
         */
        Pattern pattern = Pattern.compile("^new (.*)\\[");
//        Pattern pattern = Pattern.compile("\\[(.+?)\\]");
        String input = "new ItemElement[] { new Boo()}";
        String[] ss = input.split("\n");
        for (String i : ss) {
            Matcher m = pattern.matcher(i);
            if (m.find())
                System.out.println(m.group(1));
        }

//        System.out.println(Helper.extracType("Object"));
    }
}
