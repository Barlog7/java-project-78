package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class NumberSchemaTest {
    private Validator v;
    private NumberSchema schema;

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
        assertTrue(schema.isValid(null));
    }
    @Test
    void isGetStausCheckRange() {

        schema.range(1, 5);
        assertFalse(schema.isValid(6));

        assertTrue(schema.isValid(1));
        assertFalse(schema.isValid(null));
    }
    @Test
    void isGetStausCheckAll() {
        schema.positive();
        schema.range(1, 5);
        assertFalse(schema.isValid(-1));
        assertTrue(schema.isValid(2));
        //assertTrue(schema.positive().range(1, 5).isValid(2));
    }
    @Test
    void isGetStausCheckAllParam() {
        assertTrue(schema.positive().range(1, 5).isValid(2));
    }
}
