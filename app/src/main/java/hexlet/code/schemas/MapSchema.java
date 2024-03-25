package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {
    boolean ifSize = false;
    int size = 0;
    //Map schemasCheck;

    public MapSchema range(int sizeParam) {
        this.ifSize = true;
        this.size = sizeParam;
        return this;
    }
    @Override
    public boolean isGetStaus(Object data) {
        Map map = (Map) data;
        if (ifSize && map.size() != size) {
            return false;
        }
        if (schemasCheck != null && !schemasCheck.isEmpty()) {
            return isCheckMapByShechma(map);
        }
        return true;
    }

    private <T> boolean isCheckMapByShechma(Map<String, T> map) {
        var entries = map.entrySet();
        for (var entry : entries) {
            //System.out.println(entry.getKey());
            String sKey = entry.getKey();
            var value = entry.getValue();

            BaseSchema<T> schema = (BaseSchema<T>) schemasCheck.getOrDefault(sKey, null);
            if (schema != null && !schema.isValid(value)) {
                return false;
            }
            //System.out.println(entry.getValue());
        }
        return true;
    }


}
