package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
    @Test
    void isGetStausCheckShapeNumber() {


        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstNumber", v.number().range(1, 5));
        schemas.put("lastNumber", v.number().positive());
        schema.shape(schemas);

        Map<String, Integer> human1 = new HashMap<>();
        human1.put("firstNumber", 2);
        human1.put("lastNumber", 3);
        boolean actual = schema.isValid(human1);
        boolean expected = true;
        assertEquals(expected, actual);

        Map<String, Integer> human2 = new HashMap<>();
        human2.put("firstNumber", 6);
        human2.put("lastNumber", null);
        actual = schema.isValid(human2); // false
        expected = false;
        assertEquals(expected, actual);

        Map<String, Integer> human3 = new HashMap<>();
        human3.put("firstNumber", 2);
        human3.put("lastNumber", -1);
        actual = schema.isValid(human3); // false
        expected = false;
        //assertEquals(expected, actual);

    }
    @Test
    void isGetStausCheckShapeString() {


        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        boolean actual = schema.isValid(human1);
        boolean expected = true;
        assertEquals(expected, actual);

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        actual = schema.isValid(human2); // false
        expected = false;
        assertEquals(expected, actual);

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        actual = schema.isValid(human3); // false
        expected = false;
        assertEquals(expected, actual);

    }
}