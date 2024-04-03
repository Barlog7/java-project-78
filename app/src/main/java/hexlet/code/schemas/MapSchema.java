package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema {

    private int size = 0;

    public MapSchema() {
        Predicate<Object> fn = x -> {
            if (x == null) {
                return true;
            }
            Map map = (Map) x;
            if (getSchemasCheck() != null && !getSchemasCheck().isEmpty()) {
                return isCheckMapByShechma(map);
            }
            return true;
        };
        addCheck("isCheckMapByShechma", fn);
    }

    public MapSchema sizeof(int sizeParam) {

        this.size = sizeParam;
        Predicate<Object> fn = x -> {
            if (x == null) {
                return false;
            }
            Map map = (Map) x;
            if (map.size() != size) {
                return false;
            }
            return true;
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

            BaseSchema<T> schema = (BaseSchema<T>) getSchemasCheck().getOrDefault(sKey, null);
            if (schema != null && !schema.isValid(value)) {
                return false;
            }

        }
        return true;
    }


}
