package com.stealthmountain.javaplayground;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class HelloWorldTest {
    @Test
    public void testHelloWorld() throws Throwable {
        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (PrintStream printStream = new PrintStream(byteArrayOutputStream)) {
            System.setOut(printStream);
            HelloWorld.main();
            assertEquals(
                    "Hello World!\n",
                    byteArrayOutputStream.toString()
            );
        } finally {
            System.setOut(originalOut);
        }
    }
}
