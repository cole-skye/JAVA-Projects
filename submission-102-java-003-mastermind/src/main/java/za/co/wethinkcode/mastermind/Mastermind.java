package za.co.wethinkcode.mastermind;

public class Mastermind {
    private final String code;
    private final Player player;

    public Mastermind(CodeGenerator generator, Player player){
        this.code = generator.generateCode();
        this.player = player;
    }
    public Mastermind(){
        this(new CodeGenerator(), new Player());
    }

    public void runGame(){
        //TODO: implement the main run loop logic
        String codeString = code;
        int attemps = 12;
        while (attemps != 0){
            if (attemps < 12){
                System.out.println("Turns left: "+ attemps);
            }
            String userInput = player.getGuess();
            if (userInput.equals("exit")){
                System.exit(0);
            }
            int NumofPlace = 0;
            int NumNotinPlace = 0;

            for (int i = 0; i < 4; i++){
                boolean right = false;
                if (userInput.charAt(i) == codeString.charAt(i)){
                    NumofPlace = NumofPlace + 1;
                    right = true;
                }
                if (!right && userInput.contains(((Character) codeString.charAt(i)).toString())){
                    NumNotinPlace = NumNotinPlace + 1;
                }
            }
            System.out.println("Number of correct digits in correct place: "+ NumofPlace);
            System.out.println("Number of correct digits not in correct place: "+ NumNotinPlace);

            if (userInput.equals(codeString)){
                System.out.println("Congratulations! You are a codebreaker!");
                break;
            }
            attemps--;
        }
        if (attemps == 0){
            System.out.println("No more turns left.");
        }
        System.out.println("The code was: " + codeString);
    }

    public static void main(String[] args){
        Mastermind game = new Mastermind();
        game.runGame();
    }
}
