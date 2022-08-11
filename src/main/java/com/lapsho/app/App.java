package com.lapsho.app;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String PATTERN_TEMPLATE = "[{}]";

    public static String stripComments(String text, String[] commentSymbols) {
        if (text == null) {
            return "";

        } else if (commentSymbols == null || text.equals("") || commentSymbols.length == 0) {
            return text;
        }
        Pattern commentSymbolsPattern = Pattern.compile("[" + String.join("", commentSymbols) + "]");

        Optional<String> value = text.lines().reduce((newText, line) -> {
            Matcher commentSymbolsMatcher = commentSymbolsPattern.matcher(line);
            int start = commentSymbolsMatcher.find() ? commentSymbolsMatcher.start() : -1;
            String newLine = start > -1 ? line.substring(0, start) : line;

            return newText + System.lineSeparator() + newLine;
        });

        return value.orElse("");
    }
}
