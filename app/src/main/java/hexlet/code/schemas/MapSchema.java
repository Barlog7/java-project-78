package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema{
    boolean ifSize = false;
    int size = 0;

    public MapSchema range(int size) {
        this.ifSize = true;
        this.size = size;
        return this;
    }
    @Override
    public boolean isGetStaus(Object data) {
        Map map = (Map) data;
        if (ifSize == true && map.size() != size) {
            return false;
        }
        return true;
    }
}
