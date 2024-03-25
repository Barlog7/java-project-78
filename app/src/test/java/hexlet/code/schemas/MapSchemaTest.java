package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MapSchemaTest {

    Validator v;
    MapSchema schema;
    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.map();
    }
    @Test
    void isGetStausBaseTest() {

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        boolean actual = schema.isValid(data); // true
        boolean expected = true;

        assertEquals(expected, actual);
    }
    @Test
    void isGetStausCheckRequaerd() {
        boolean actual = schema.isValid(null); // false
        boolean expected = true;

        schema.required();
        actual = schema.isValid(null); // false
        expected = false;

        assertEquals(expected, actual);

        schema.required();
        actual = schema.isValid(new HashMap<>()); // false
        expected = true;

        assertEquals(expected, actual);
    }

    @Test
    void isGetStausCheckRange() {

        schema.range(1);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        boolean actual = schema.isValid(data); // false
        boolean expected = true;

        assertEquals(expected, actual);

        schema.range(2);
        actual = schema.isValid(data); // false
        expected = false;

        assertEquals(expected, actual);
    }
}