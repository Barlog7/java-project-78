package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    private Map<String, ?> schemasCheck;
    private int size = 0;

    public MapSchema() {
        Predicate<Map<?, ?>> fn = x -> {
            if (x == null) {
                return true;
            }

            if (schemasCheck != null && !schemasCheck.isEmpty()) {
                return isCheckMapByShechma((Map) x);
            }
            return true;
        };
        addCheck("isCheckMapByShechma", fn);
    }

    public MapSchema sizeof(int sizeParam) {

        this.size = sizeParam;
        Predicate<Map<?, ?>> fn = x -> {
            if (x == null) {
                return false;
            }

            Map map = (Map) x;
            return !(map.size() != size);

        };
        addCheck("sizeof", fn);
        return this;
    }

    @Override
    public MapSchema required() {
        return (MapSchema) super.required();
    }

    private <T> boolean isCheckMapByShechma(Map<String, T> map) {
        var entries = map.entrySet();
        for (var entry : entries) {

            String sKey = entry.getKey();
            var value = entry.getValue();

            BaseSchema<T> schema = (BaseSchema<T>) schemasCheck.getOrDefault(sKey, null);
            if (schema != null && !schema.isValid(value)) {
                return false;
            }

        }
        return true;
    }

    public <T> boolean shape(Map<String, BaseSchema<T>> map) {
        schemasCheck = new HashMap<>(map);
        return true;
    }


}
