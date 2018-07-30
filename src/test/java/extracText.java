import core.helper.Helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class extracText {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\<(.+?)\\>");
//        Pattern pattern = Pattern.compile("\\[(.+?)\\]");
//        Matcher m = pattern.matcher("[int] ");
//        if (m.find())
//            System.out.println(m.group(1));
        System.out.println(Helper.extracType("Tkeo< <Code> Test>"));
    }
}
