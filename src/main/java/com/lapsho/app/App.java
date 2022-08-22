package com.lapsho.app;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Hello world!
 *
 */
public class App 
{
    private static final List<String> REGEX_SPECIAL_CHARS = Arrays.asList(
            "<", "(", "[", "{", "\\", "^", "-", "=", "$", "!", "|", "]", "}", ")", "?", "*", "+", ".", ">");

    private static final Pattern END_OF_LINE = Pattern.compile("[\s]+$");

    public static String stripComments(String text, String[] commentSymbols) {
        if (text == null) {
            return "";

        } else if (commentSymbols == null || text.equals("") || commentSymbols.length == 0) {
            return text;
        }
        String pattern = Arrays.stream(commentSymbols).reduce("", (commentPattern, commentVariation) ->
             commentPattern + (commentPattern.isEmpty() ? "" : "|") +
                     "(" + (REGEX_SPECIAL_CHARS.contains(commentVariation) ? "\\" : "") + commentVariation + ")"
        );
        Pattern commentSymbolsPattern = Pattern.compile(pattern);
        List<String> stripedLines = new LinkedList<String>();

        text.lines().forEach((line) -> {
            Matcher commentSymbolsMatcher = commentSymbolsPattern.matcher(line);
            int stripCharIndex = commentSymbolsMatcher.find() ? commentSymbolsMatcher.start() : -1;
            String newLine = (stripCharIndex > -1) ? line.substring(0, stripCharIndex) : line;
            Matcher spaceAtTheEndMatcher = END_OF_LINE.matcher(newLine);
            int spaceAtTheEndIndex = spaceAtTheEndMatcher.find() ? spaceAtTheEndMatcher.start() : -1;
            newLine = (spaceAtTheEndIndex > -1) ? newLine.substring(0, spaceAtTheEndIndex) : newLine;
            stripedLines.add(newLine);
        });

        return String.join("\n", stripedLines);
    }
}
