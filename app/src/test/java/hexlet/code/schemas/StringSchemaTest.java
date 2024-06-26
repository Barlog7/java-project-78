package hexlet.code.schemas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hexlet.code.Validator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class StringSchemaTest {

    private Validator v;
    private StringSchema schema;
    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.string();
    }
    @Test
    public void testValidCheckNull() {

        assertTrue(schema.isValid(""));

        assertTrue(schema.isValid(null));
    }
    @Test
    public void testValidCheckRequaerd() {

        schema.required();
        assertFalse(schema.isValid(null));

        assertFalse(schema.isValid(""));

        assertTrue(schema.isValid("what does the fox say"));

        assertTrue(schema.isValid("hexlet"));
    }
    @Test
    public void testValidCheckContains() {

        assertTrue(schema.contains("wh").isValid("what does the fox say"));

        assertTrue(schema.contains("what").isValid("what does the fox say"));

        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
    }
    @Test
    public void testValidCheckMinLength() {
        assertTrue(schema.minLength(10).isValid("what does the fox say"));

        assertFalse(schema.minLength(100).isValid("what does the fox say"));

        assertTrue(schema.minLength(10).minLength(4).isValid("Hexlet"));

        assertFalse(schema.isValid(null));
    }
    @Test
    public void testValidCheckAll() {
        assertTrue(schema.required().minLength(5).contains("hex").isValid("hex dex pex lex"));

        assertFalse(schema.required().minLength(25).contains("hex").isValid("hex dex pex lex"));

        assertFalse(schema.required().minLength(5).contains("apex").isValid("hex dex pex lex"));
    }

}
