package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema {

    private int minLengthNumber = 0;
    private String containsText = "";

    public StringSchema() {
    }

    public StringSchema minLength(int minLengthNumberParam) {
        this.minLengthNumber = minLengthNumberParam;
        Predicate<Object> fn = x -> {
            if (x == null) {
                return false;
            }
            String text = (String) x;
            if (minLengthNumber != 0 && text.length() < minLengthNumber) {
                return false;
            }
            return true;
        };
        addCheck("minLengthNumber", fn);
        return this;
    }

    public StringSchema contains(String containsTextParam) {

        this.containsText = containsTextParam;
        Predicate<Object> fn = x -> {
            if (x == null) {
                return false;
            }
            String text = (String) x;
            if (!containsText.isEmpty() && !text.contains(containsText)) {
                return false;
            }
            return true;
        };
        addCheck("contains", fn);
        return this;
    }

    @Override
    public StringSchema required()   {
        return (StringSchema) super.required();
    }

}
