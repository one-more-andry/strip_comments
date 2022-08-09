package com.lapsho.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void stripComments_NullInput_ShouldReturnEmptyString() {
        assertEquals(
                "stripComments_NullInput_ShouldReturnEmptyString",
                "",
                App.stripComments( null, new String[] { "#", "!" } )
        );
    }

    @Test
    public void stripComments_EmptyStringInput_ShouldReturnEmptyString() {
        assertEquals(
                "stripComments_EmptyStringInput_ShouldReturnEmptyString",
                "",
                App.stripComments( "", new String[] { "#", "!" } )
        );
    }

    @Test
    public void stripComments_EmptyArrayBreakCharacters_ShouldReturnOriginalString() {
        assertEquals(
                "stripComments_EmptyArrayBreakCharacters_ShouldReturnOriginalString",
                "test string",
                App.stripComments( "test string", new String[] {} )
        );
    }

    @Test
    public void stripComments_OnlyNewLineCharStringInput_ShouldReturnOriginalString() {
        assertEquals(
                "stripComments_EmptyStringInput_ShouldReturnEmptyString",
                "\n",
                App.stripComments( "\n", new String[] { "#", "!" } )
        );
    }

    @Test
    public void stripComments_SingleAndDoubleQuotesInput_ShouldStripByBothQuotesType() {
        assertEquals(
                "stripComments_SingleAndDoubleQuotesInput_ShouldStripByBothQuotesType",
                "before single quote \n before double quotes ",
                App.stripComments( "before single quote ' text to ignore\n before double quotes \" text to ignore",
                        new String[] { "'", "\"" } )
        );
    }

    @Test
    public void stripComments_BackSlashInput_ShouldStripByBackSlash() {
        assertEquals(
                "stripComments_BackSlashInput_ShouldStripByBackSlash",
                "before backslash",
                App.stripComments( "before backslash\\ text to ignore",
                        new String[] { "\\" } )
        );
    }

    @Test
    public void stripComments_SpecialCharsWithRegularString_ShouldStripBySpecialChars() throws Exception {
        assertEquals(
                "stripComments_SpecialCharsWithRegularString_ShouldStripBySpecialChars first assert: ",
                "apples, pears\ngrapes\nbananas",
                App.stripComments( "apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" } )
        );

        assertEquals(
                "stripComments_SpecialCharsWithRegularString_ShouldStripBySpecialChars second assert: ",
                "a\nc\nd",
                App.stripComments( "a #b\nc\nd $e f g", new String[] { "#", "$" } )
        );
    }
}
