package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private boolean ifSize = false;
    private int size = 0;

    public MapSchema sizeof(int sizeParam) {
        this.ifSize = true;
        this.size = sizeParam;
        return this;
    }

    @Override
    public MapSchema required() {
        this.setRequired(true);
        return this;
    }

    @Override
    public boolean isGetStaus(Object data) {
        Map map = (Map) data;
        if (ifSize && map.size() != size) {
            return false;
        }
        if (getSchemasCheck() != null && !getSchemasCheck().isEmpty()) {
            return isCheckMapByShechma(map);
        }
        return true;
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
