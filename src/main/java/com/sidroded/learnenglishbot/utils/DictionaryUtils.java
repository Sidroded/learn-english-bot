package com.sidroded.learnenglishbot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryUtils {

    public boolean isWordTranslationPattern(String input) {
        Pattern pattern = Pattern.compile("^.* - .*");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
