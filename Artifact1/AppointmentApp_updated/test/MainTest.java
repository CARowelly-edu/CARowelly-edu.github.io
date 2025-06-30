package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.io.*;

/**
 * Test class for Main.java to verify the main method runs without crashing.
 * This does not test interactive behavior but ensures the application can start up.
 */
class MainTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    /**
     * This test checks that the main method executes without throwing exceptions.
     * It simulates a user entering '6' to trigger the exit option immediately.
     */
    @Test
    void testMainRunsAndExitsCleanly() {
        String simulatedInput = "6\n"; // Simulate choosing "Exit"
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        assertDoesNotThrow(() -> {
            Main.main(new String[]{});
        });

        String output = testOut.toString();
        assertTrue(output.contains("Main Menu")); // Validate that output at least includes the menu
    }
}
