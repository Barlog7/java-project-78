package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
        assertTrue(schema.isValid(5));
    }
    @Test
    void isGetStausCheckRequaerd() {

        schema.required();
        assertFalse(schema.isValid(null));
    }
    @Test
    void isGetStausCheckPositive() {

        schema.positive();
        assertFalse(schema.isValid(-1));

        schema.positive();
        assertTrue(schema.isValid(1));
    }
    @Test
    void isGetStausCheckRange() {

        schema.range(1, 5);
        assertFalse(schema.isValid(6));

        assertTrue(schema.isValid(1));
    }
}
