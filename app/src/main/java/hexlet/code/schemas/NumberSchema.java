package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {
    private boolean isRequired = false;
    private boolean positive = false;
    private boolean range = false;
    private int minRange = 0;
    private int maxRange = 0;

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
        if (data == null && (range)) {
            return false;
        }
        if (data == null && positive) {
            return true;
        }
        Integer number = (Integer) data;
        if (positive && number <= 0) {
            return false;
        }
        if (range && (number < minRange || number > maxRange)) {
            return false;
        }
        return true;
    }
    @Override
    public NumberSchema required()   {
        this.setRequired(true);
        return this;
    }
}

