package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {
    //private boolean isRequired = false;
    //private boolean positive = false;
    //private boolean range = false;
    private int minRange = 0;
    private int maxRange = 0;

    public NumberSchema() {
    }
/*    public NumberSchema positive() {
        //this.positive = true;

        Predicate<Object> fn = x -> {
            if (x == null) {
                return true;
            }
            Integer number = (Integer) x;
            if (x == null) {
                return true;
            }
            //Integer number = (Integer) data;
            if (number <= 0) {
                return false;
            }
            return true;
        };
        addCheck("positive", fn);
        return this;
    }*/

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
        //this.range = true;
        this.minRange = min;
        this.maxRange = max;
        Predicate<Integer> fn = x -> {
            if (x == null) {
                return false;
            }
            return !(x < minRange || x > maxRange);
            //Integer number = (Integer) x;
            /*if (x < minRange || x > maxRange) {
                return false;
            }
            return true;*/
        };
        addCheck("range", fn);
        return this;
    }

    @Override
    public NumberSchema required()   {
        return (NumberSchema) super.required();
    }
}

