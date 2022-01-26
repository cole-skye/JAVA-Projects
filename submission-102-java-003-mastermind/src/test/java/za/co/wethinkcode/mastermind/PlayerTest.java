package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PlayerTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void UserInputValid(){
        InputStream byteArrayInputStream = new ByteArrayInputStream("1111\n".getBytes(StandardCharsets.UTF_8));
        Player playerInput = new Player(byteArrayInputStream);
        String playerGuess = playerInput.getGuess();

        assertEquals("1111", playerGuess);
    }

    @Test
    public void UserInputnotValid(){
        InputStream byteArrayInputStream = new ByteArrayInputStream("1111\n".getBytes(StandardCharsets.UTF_8));
        Player playerInput = new Player(byteArrayInputStream);
        String playerGuess = playerInput.getGuess();

        assertNotEquals("111", playerGuess);
    }

    @Test
    void GuessTooShort() {
        Player player = new Player(new ByteArrayInputStream("1\n1111\n".getBytes()));
        String guess = player.getGuess();
        assertEquals("1111", guess);
        assertEquals(
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:",
                outputStreamCaptor.toString().trim());
    }

    @Test
    void GuessTooLong() {
        Player player = new Player(new ByteArrayInputStream("123456\n1111\n".getBytes()));
        String guess = player.getGuess();
        assertEquals("1111", guess);
        assertEquals(
                "Input 4 digit code:\nPlease enter exactly 4 digits (each from 1 to 8).\nInput 4 digit code:",
                outputStreamCaptor.toString().trim());

    }

}
