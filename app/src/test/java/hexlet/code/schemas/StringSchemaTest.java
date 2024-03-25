package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hexlet.code.Validator;
//import hexlet.code.schemas.StringSchema;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringSchemaTest {

    Validator v;
    StringSchema schema;
    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.string();
    }
    @Test
    public void testValidCheckNull() {
        //var v = new Validator();

        //var schema = v.string();
        boolean actual = schema.isValid(""); // true
        boolean expected = true;

        assertEquals(expected, actual);

        actual = schema.isValid(null);
        expected = true;
        assertEquals(expected, actual);
    }
    @Test
    public void testValidCheckRequaerd() {
        //var v = new Validator();

        //var schema = v.string();
        schema.required();
        boolean actual = schema.isValid(null); // true
        boolean expected = false;

        assertEquals(expected, actual);

        /*actual = schema.isValid("");
        expected = false;
        assertEquals(expected, actual);*/

        actual = schema.isValid("what does the fox say");
        expected = true;
        assertEquals(expected, actual);

        actual = schema.isValid("hexlet");
        expected = true;
        assertEquals(expected, actual);
    }
    @Test
    public void testValidCheckContains() {
        boolean actual = schema.contains("wh").isValid("what does the fox say"); // true
        boolean expected = true;
        assertEquals(expected, actual);
        actual = schema.contains("what").isValid("what does the fox say"); // true
        expected = true;
        assertEquals(expected, actual);
        actual = schema.contains("whatthe").isValid("what does the fox say"); // false
        expected = false;
        assertEquals(expected, actual);

        actual = schema.isValid("what does the fox say"); // false
        expected = false;
        assertEquals(expected, actual);
    }
    @Test
    public void testValidCheckMinLength() {
        boolean actual = schema.minLength(10).isValid("what does the fox say"); // true
        boolean expected = true;
        assertEquals(expected, actual);
        actual = schema.minLength(100).isValid("what does the fox say"); // false
        expected = false;
        assertEquals(expected, actual);
        actual = schema.minLength(10).minLength(4).isValid("Hexlet");
        expected = true;
        assertEquals(expected, actual);
    }
    @Test
    public void testValidCheckAll() {
        boolean actual = schema.required().minLength(5).contains("hex").isValid("hex dex pex lex");
        boolean expected = true;
        assertEquals(expected, actual);
    }
}
