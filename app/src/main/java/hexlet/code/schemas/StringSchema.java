package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    private int minLengthNumber = 0;
    private String containsText = "";

    public StringSchema() {
    }

    public StringSchema minLength(int minLengthNumberParam) {
        this.minLengthNumber = minLengthNumberParam;
        Predicate<String> fn = x -> {
            if (x == null) {
                return false;
            }
            return !(minLengthNumber != 0 && x.length() < minLengthNumber);
            //String text = (String) x;
/*            if (minLengthNumber != 0 && x.length() < minLengthNumber) {
                return false;
            }
            return true;*/
        };
        addCheck("minLengthNumber", fn);
        return this;
    }

    public StringSchema contains(String containsTextParam) {

        this.containsText = containsTextParam;
        Predicate<String> fn = x -> {
            if (x == null) {
                return false;
            }
            return !(!containsText.isEmpty() && !x.contains(containsText));
            //String text = (String) x;
/*            if (!containsText.isEmpty() && !x.contains(containsText)) {
                return false;
            }
            return true;*/
        };
        addCheck("contains", fn);
        return this;
    }

    @Override
    public StringSchema required()   {
        return (StringSchema) super.required();
    }

}
