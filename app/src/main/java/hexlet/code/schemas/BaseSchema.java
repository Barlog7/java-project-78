package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseSchema<T> {
    public void setRequired(boolean required) {
        isRequired = required;
    }

    private boolean isRequired = false;

    public Map<String, BaseSchema<T>> getSchemasCheck() {
        return schemasCheck;
    }

    public void setSchemasCheck(Map<String, BaseSchema<T>> schemasCheck) {
        this.schemasCheck = schemasCheck;
    }

    private Map<String, BaseSchema<T>> schemasCheck;

    public BaseSchema required() {
        return this;
    }
/*
    {
        this.isRequired = true;
        return this;
    }
*/

    public boolean isValid(T data) {
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

    public boolean shape(Map<String, BaseSchema<T>> map) {
        schemasCheck = new HashMap<>(map);
        return true;
    }

    public boolean isRequired() {
        return isRequired;
    }
}

