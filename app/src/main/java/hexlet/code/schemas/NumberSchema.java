package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    //private boolean isRequired = false;
    //private boolean positive = false;
    //private boolean range = false;
    private int minRange = 0;
    private int maxRange = 0;

    public NumberSchema() {
    }
    public NumberSchema positive() {
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
    }

    public NumberSchema range(int min, int max) {
        //this.range = true;
        this.minRange = min;
        this.maxRange = max;
        Predicate<Object> fn = x -> {
            if (x == null) {
                return false;
            }
            Integer number = (Integer) x;
            if (number < minRange || number > maxRange) {
                return false;
            }
            return true;
        };
        addCheck("range", fn);
        return this;
    }

    @Override
    public NumberSchema required()   {
        return (NumberSchema) super.required();
    }
}

