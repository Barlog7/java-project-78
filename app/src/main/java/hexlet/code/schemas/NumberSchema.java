package hexlet.code.schemas;

public class NumberSchema extends BaseSchema{
    boolean isRequired = false;
    boolean positive = false;
    boolean range = false;
    int minRange = 0;
    int maxRange = 0;

    public NumberSchema() {
    }
    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.range = true;
        this.minRange = min;
        this.maxRange = max;
        return this;
    }

    @Override
    public boolean isGetStaus(Object data) {
        Integer number = (Integer) data;
        if (positive == true && number < 0) {
            return false;
        }
        if (range == true && (number < minRange || number > maxRange)) {
            return false;
        }
        return true;
    }
}
