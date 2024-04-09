package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MapSchemaTest {

    private Validator v;
    private MapSchema schema;
    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.map();
    }
    @Test
    void isGetStausBaseTest() {

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));
    }
    @Test
    void isGetStausCheckRequaerd() {

        assertTrue(schema.isValid(null));

        schema.required();
        assertFalse(schema.isValid(null));

        schema.required();
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    void isGetStausCheckRange() {

        schema.sizeof(1);
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        schema.sizeof(2);
        assertFalse(schema.isValid(data));
    }
    @Test
    void isGetStausCheckShapeNumber() {


        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put("firstNumber", v.number().range(1, 5));
        schemas.put("lastNumber", v.number().positive());
        schema.shape(schemas);

        Map<String, Integer> human1 = new HashMap<>();
        human1.put("firstNumber", 2);
        human1.put("lastNumber", 3);
        assertTrue(schema.isValid(human1));

        Map<String, Integer> human2 = new HashMap<>();
        human2.put("firstNumber", 6);
        human2.put("lastNumber", null);
        assertFalse(schema.isValid(human2));

        Map<String, Integer> human3 = new HashMap<>();
        human3.put("firstNumber", 2);
        human3.put("lastNumber", -1);
        assertFalse(schema.isValid(human3));
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
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));

    }
}
