package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    //boolean isRequired = false;
    int minLengthNumber = 0;
    String containsText = "";

    public StringSchema() {
    }

    public StringSchema required() {
        this.isRequired = true;
        return this;
    }


    public StringSchema minLength(int minLengthNumber) {
        this.minLengthNumber = minLengthNumber;
        return this;
    }

    public StringSchema contains(String containsText) {
        this.containsText = containsText;
        return this;
    }

    @Override
    public boolean isGetStaus(Object data) {
        String text = (String) data;
        if (minLengthNumber != 0 && text.length() < minLengthNumber) {
            return false;
        }
        if (containsText.isEmpty() == false && text.contains(containsText) == false) {
            return false;
        }
        return true;
    }
/*    public boolean isValid(String text) {
        if (isRequired == true && (text == null || text.isEmpty())) {
            return false;
        }
        if (minLengthNumber != 0 && text.length() < minLengthNumber) {
            return false;
        }
        if (containsText.isEmpty() == false && text.contains(containsText) == false) {
            return false;
        }
        return true;
    }
*/
}
