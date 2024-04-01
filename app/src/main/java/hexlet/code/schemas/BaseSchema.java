package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema<T> {

    private boolean isRequired = false;
    public final void setRequired(boolean required) {
        isRequired = required;
    }
    public final boolean isRequired() {
        return isRequired;
    }

    private Map<String, BaseSchema<T>> schemasCheck;
    public final Map<String, BaseSchema<T>> getSchemasCheck() {
        return schemasCheck;
    }
    public final void setSchemasCheck(Map<String, BaseSchema<T>> schemasCheckCurent) {
        this.schemasCheck = schemasCheckCurent;
    }

    /*public BaseSchema required() {
        return this;
    }*/
    public abstract BaseSchema required();
    public final boolean isValid(T data) {
        if (isRequired && data == null) {
            return false;
        }

        boolean status = isGetStaus(data);
        if (!status) {
            return false;
        }
        return true;
    }
    public abstract boolean isGetStaus(T data);

    public final boolean shape(Map<String, BaseSchema<T>> map) {
        schemasCheck = new HashMap<>(map);
        return true;
    }
}

