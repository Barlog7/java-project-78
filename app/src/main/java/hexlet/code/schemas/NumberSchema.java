package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        addCheck(
                "positive",
                value -> {
                    if (value == null) {
                        return true;
                    }
                    return value > 0;
                });
        return this;
    }

    public NumberSchema range(int min, int max) {

        Predicate<Integer> fn = x -> {
            if (x == null) {
                return false;
            }
            return !(x < min || x > max);
        };
        addCheck("range", fn);
        return this;
    }

    @Override
    public NumberSchema required()   {
        return (NumberSchema) super.required();
    }
}

