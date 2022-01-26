package za.co.wethinkcode.mastermind;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Player {
    private final Scanner inputScanner;

    public Player(){
        this.inputScanner = new Scanner(System.in);
    }

    public Player(InputStream inputStream){
        this.inputScanner = new Scanner(inputStream);
    }

    /**
     * Gets a guess from user via text console.
     * This must prompt the user to re-enter a guess until a valid 4-digit string is entered, or until the user enters `exit` or `quit`.
     *
     * @return the value entered by the user
     */
    public String getGuess(){
        while (true){
            System.out.println("Input 4 digit code:");
            String userInput = inputScanner.nextLine();
            if (userInput.equals("exit") || userInput.equals(("quit"))){
                System.exit(0);
            }
            if (userInput.length() == 4){
                int count = 0;
                for (int i = 0; i < 4; i++){
                    if (Character.isDigit(userInput.charAt(i))){
                        count = count + 1 ;
                    }
                    else{
                        break;
                    }
                    Pattern regex = Pattern.compile("[1-8]{4}");
                    if (regex.matcher(userInput).find()){
                        if (count == 4){
                            return userInput;
                        }
                    }

                }
            }
            System.out.println("Please enter exactly 4 digits (each from 1 to 8).");
        }
    }
}
