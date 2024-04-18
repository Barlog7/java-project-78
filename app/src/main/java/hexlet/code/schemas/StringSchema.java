package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema minLength(int minLengthNumberParam) {
        //this.minLengthNumber = minLengthNumberParam;
        Predicate<String> fn = x -> {
            if (x == null) {
                return false;
            }
            return !(minLengthNumberParam != 0 && x.length() < minLengthNumberParam);

        };
        addCheck("minLengthNumber", fn);
        return this;
    }

    public StringSchema contains(String containsTextParam) {

        //this.containsText = containsTextParam;
        Predicate<String> fn = x -> {
            if (x == null) {
                return false;
            }
            return !(!containsTextParam.isEmpty() && !x.contains(containsTextParam));

        };
        addCheck("contains", fn);
        return this;
    }

    @Override
    public StringSchema required()   {
        return (StringSchema) super.required();
    }

}
