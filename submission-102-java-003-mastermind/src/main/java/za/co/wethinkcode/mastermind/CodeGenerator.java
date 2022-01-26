package za.co.wethinkcode.mastermind;

import java.util.Random;
import java.util.*;

public class CodeGenerator {
    private final Random random;

    public CodeGenerator(){
        this.random = new Random();
    }

    public CodeGenerator(Random random){
        this.random = random;
    }

    /**
     * Generates a random 4 digit code, using this.random, where each digit is in the range 1 to 8 only.
     * Duplicated digits are allowed.
     * @return the generated 4-digit code
     */
    public String generateCode(){
        //TODO: implement using this.random
        System.out.println("4-digit Code has been set. Digits in range 1 to 8. You have 12 turns to break it.");
        String numbers = "12345678";
        StringBuilder randomstring = new StringBuilder();
        int length = 4;

        char [] code = new char[length];
        for ( int i = 0; i < length; i++) {
            code[i] = numbers.charAt(this.random.nextInt(numbers.length()));
        }
        for (char n : code ) {
            randomstring.append(n);
        }
        return randomstring.toString();
    }
}
