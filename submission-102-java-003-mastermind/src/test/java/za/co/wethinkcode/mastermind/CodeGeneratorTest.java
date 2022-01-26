package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CodeGeneratorTest {


    @Test
    public void TestingCodeLength() {
        CodeGenerator code = new CodeGenerator();
        String LengthFinder = code.generateCode();

        int stringLength = LengthFinder.length();

        assertEquals(stringLength, 4);
        assertNotEquals(stringLength, 0);
        assertNotEquals(stringLength, 5);
    }


    @Test
    public void TestTypeofCode(){
        CodeGenerator code = new CodeGenerator();
        String type = code.generateCode();

        assertEquals(type.getClass(), "".getClass());
    }


}