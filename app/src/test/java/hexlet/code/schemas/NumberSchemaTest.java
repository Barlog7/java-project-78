package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberSchemaTest {
    Validator v;
    NumberSchema schema;

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    void isGetStausBaseTest() {

        boolean actual = schema.isValid(5); // true
        boolean expected = true;

        assertEquals(expected, actual);
    }
    @Test
    void isGetStausCheckRequaerd() {

        schema.required();
        boolean actual = schema.isValid(null); // false
        boolean expected = false;

        assertEquals(expected, actual);
    }
    @Test
    void isGetStausCheckPositive() {

        schema.positive();
        boolean actual = schema.isValid(-1); // false
        boolean expected = false;

        schema.positive();
        actual = schema.isValid(1); // true
        expected = true;

        assertEquals(expected, actual);
    }
    @Test
    void isGetStausCheckRange() {

        schema.range(1, 5);
        boolean actual = schema.isValid(6); // false
        boolean expected = false;

        //schema.positive();
        actual = schema.isValid(1); // true
        expected = true;

        assertEquals(expected, actual);
    }
}