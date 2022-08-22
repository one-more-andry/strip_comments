package com.lapsho.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void stripComments_NullTextInput_ShouldReturnEmptyString() {
        assertEquals(
                "not empty string returned",
                "",
                App.stripComments( null, new String[] { "#", "!" } )
        );
    }

    @Test
    public void stripComments_EmptyTextInput_ShouldReturnEmptyString() {
        assertEquals(
                "not empty string returned",
                "",
                App.stripComments( "", new String[] { "#", "!" } )
        );
    }

    @Test
    public void stripComments_EmptyArrayBreakCharacters_ShouldReturnOriginalString() {
        assertEquals(
                "not original string returned",
                "test string",
                App.stripComments( "test string", new String[] {} )
        );
    }

    @Test
    public void stripComments_NullArrayBreakCharacters_ShouldReturnOriginalString() {
        assertEquals(
                "not original string returned",
                "test string",
                App.stripComments( "test string", null )
        );
    }

    @Test
    public void stripComments_TwoNewLineCharStringInput_ShouldReturnOneNewLineString() {
        assertEquals(
                "not original string returned",
                "\n",
                App.stripComments( "\n\n", new String[] { "#", "!" } )
        );
    }

    @Test
    public void stripComments_SingleAndDoubleQuotesInput_ShouldStripByBothQuotesType() {
        assertEquals(
                "failed to strip by single and double quotes",
                "before single quote\n before double quotes",
                App.stripComments( "before single quote ' text to ignore\n before double quotes \" text to ignore",
                        new String[] { "'", "\"" } )
        );
    }

    @Test
    public void stripComments_BackSlashInput_ShouldStripByBackSlash() {
        assertEquals(
                "failed to strip by backslash",
                "before backslash",
                App.stripComments( "before backslash\\ text to ignore",
                        new String[] { "\\" } )
        );
    }

    @Test
    public void stripComments_SpaceBeforeStripChar_ShouldBeStripped() {
        String[] strip = new String[] { "#", "!", "@" };

        assertEquals(
                "failed to strip by " + String.join(", ", strip),
                "apples, pears\ngrapes\nbananas\n apples",
                App.stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples\n apples@ glue", strip)
        );
    }

    @Test
    public void stripComments_RegexSpecialChars_ShouldStripBySpecialChars() {
        String inputString = "less <ignore \nparentheses open (ignore \nbrackets open [ignore \ncurly open {ignore \nbackslash \\ignore \ncaret ^ignore \nminus -ignore \nplus +ignore \nequal =ignore \ndollar $ignore \nexclamation !ignore \nor |ignore \nbracket close ]ignore \ncurly close}ignore \nparentheses close )ignore \nquestion ?ignore \nasterisk *ignore \ndot .ignore \nmore >ignore",
                outputString = "less\nparentheses open\nbrackets open\ncurly open\nbackslash\ncaret\nminus\nplus\nequal\ndollar\nexclamation\nor\nbracket close\ncurly close\nparentheses close\nquestion\nasterisk\ndot\nmore";
        String[] regexSpecialChars = new String[]
                { "<", "(", "[", "{", "\\", "^", "-", "=", "$", "!", "|", "]", "}", ")", "?", "*", "+", ".", ">" };

        assertEquals(
                "failed to strip via regex special chars",
                outputString,
                App.stripComments( inputString, regexSpecialChars )
        );
    }

    @Test
    public void stripComments_ByWords_ShouldStripByWords() {
        String inputString = "one two three \n four five six seven \n one \ntwo",
                outputString ="one\n four five\n one\n";
        String[] words = new String[]
                { "two", "six" };

        assertEquals(
                "failed to strip by words",
                outputString,
                App.stripComments( inputString, words )
        );
    }

    @Test
    public void stripComments_CharAtBeginningAndNewLineAsStripChar_IgnoreNewLineAsStripChar() {
        String inputString = "o\ntwo three \n four five six seven \n one \ntwo";
        String outputString = "o\ntwo three\n four five six seven\n one\ntwo";
        String[] strip = new String[] { "\n" };

        assertEquals(
                "failed to strip by newline",
                outputString, App.stripComments( inputString, strip )
        );
    }

    @Test
    public void stripComments_NewLineAtBeginningAndNewLineAsStripChar_IgnoreNewLineAsStripChar() {
        String inputString = "\n test \n one \ntwo";
        String outputString = "\n test\n one\ntwo";
        String[] strip = new String[] { "\n"};

        assertEquals(
                "failed to strip by newline",
                outputString, App.stripComments( inputString, strip )
        );
    }

    @Test
    public void stripComments_DuplicateStripChar_IgnoreDuplicate() {
        String inputString = "test1; ignore \n test2; ignore",
                outputString = "test1\n test2";
        String[] strip = new String[] { ";", ";"};

        assertEquals(
                "failed to strip when duplicate strip char provided",
                outputString, App.stripComments( inputString, strip )
        );
    }
}
