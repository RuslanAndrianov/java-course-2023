package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("HideUtilityClassConstructor")
public class Task6 {

    public static boolean isSubstr(String substr, String str) {

        String regexp = Pattern.quote(substr);
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }
}
