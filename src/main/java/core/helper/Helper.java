package core.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static String extracType(String raw) {
        Pattern pattern;
        if (raw.contains("[")) {
            return raw.substring(0, raw.indexOf(" "));
        } else if (raw.contains("<")) { //List
            pattern = Pattern.compile("\\<(.+?)\\>");
            Matcher matcher = pattern.matcher(raw);
            if (matcher.find()) return matcher.group(1);
        } else return raw;
        return "";
    }
}
