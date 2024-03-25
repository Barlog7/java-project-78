package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    boolean isRequired = false;

    public BaseSchema required() {
        this.isRequired = true;
        return this;
    }

    public boolean isValid(T data) {
        if (isRequired == true && data == null) {
            return false;
        }

        boolean status = isGetStaus(data);
        if (status == false) {
            return false;
        }
        return true;
    }
    public abstract boolean isGetStaus(T data);
}

