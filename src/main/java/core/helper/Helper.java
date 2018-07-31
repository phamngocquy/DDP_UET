package core.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static String extractType(String raw) {
        Pattern pattern;
        if (raw.contains("<")) { //List
            pattern = Pattern.compile("\\<(.+?)\\>");
            Matcher matcher = pattern.matcher(raw);
            if (matcher.find()) return matcher.group(1);
        }
        return null;
    }

    public static List<String> extractValueTypeMethodLevel(String raw) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("new (.*)\\(");
        Pattern pattern2 = Pattern.compile("new (.*)\\[");
        String[] ss = raw.split("\n");
        for (String i : ss) {
            Matcher matcher = pattern.matcher(i);
            if (matcher.find()) {
                result.add(matcher.group(1));
            }
            matcher = pattern2.matcher(i);
            if (matcher.find()) {
                result.add(matcher.group(1));
            }
        }
        return result;
    }

//    public static void main(String[] args) {
//        Helper helper = new Helper();
//        helper.
//    }
}
