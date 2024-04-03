package hexlet.code.schemas;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<Object>> checks = new LinkedHashMap<>();

    private Map<String, BaseSchema<T>> schemasCheck;

    protected final void addCheck(String key, Predicate<Object> pred) {
        checks.put(key, pred);
    }

    public final Map<String, BaseSchema<T>> getSchemasCheck() {
        return schemasCheck;
    }

    public final void setSchemasCheck(Map<String, BaseSchema<T>> schemasCheckCurent) {
        this.schemasCheck = schemasCheckCurent;
    }

    //public abstract BaseSchema required();

    /**
     * <p>required() — делает данные обязательными для заполнения. Иными словами добавляет в схему ограничение, которое не позволяет использовать null или пустую строку в качестве значения
     * Метод переопределяется в дочерних классах для изменения возвращаемой схемы 
     * </p>
     */
    public BaseSchema required() {
        Predicate<Object> fn = x -> {
            if (x == null) {
                return false;
            }
            if (x.getClass() == String.class && ((String) x).isEmpty()) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public final boolean isValid(T data) {
        if (checks.isEmpty()) {
            return true;
        }
        var values = checks.values();
        for (var value : values) {
            if (!value.test(data)) {
                return false;
            }
        }
        return true;
    }

    public final boolean shape(Map<String, BaseSchema<T>> map) {
        schemasCheck = new HashMap<>(map);
        return true;
    }
}

