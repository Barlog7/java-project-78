package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    int minLengthNumber = 0;
    String containsText = "";

    public StringSchema() {
    }

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }


    public StringSchema minLength(int minLengthNumberParam) {
        this.minLengthNumber = minLengthNumberParam;
        return this;
    }

    public StringSchema contains(String containsTextParam) {
        this.containsText = containsTextParam;
        return this;
    }

    @Override
    public boolean isGetStaus(Object data) {
        String text = (String) data;
        if (minLengthNumber != 0 && text.length() < minLengthNumber) {
            return false;
        }
        if (!containsText.isEmpty() && !text.contains(containsText)) {
            return false;
        }
        return true;
    }

}
