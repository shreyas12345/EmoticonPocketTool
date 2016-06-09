package com.example.shrey.emoticon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void toEnglishTest() throws Exception {
        ArrayList<String> input = new ArrayList<>(Arrays.asList(new String[] {"....", "/", ".", "/", ".-..",  "/", ".-..", "/", "---"}));
        assertEquals("HELLO", MainActivity.toEnglish(input));
    }
}