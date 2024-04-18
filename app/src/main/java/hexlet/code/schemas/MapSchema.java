package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema sizeof(int sizeParam) {

        //this.size = sizeParam;
        Predicate<Map<?, ?>> fn = x -> {
            if (x == null) {
                return false;
            }

            Map map = (Map) x;
            return x.size() == sizeParam;

        };
        addCheck("sizeof", fn);
        return this;
    }

    @Override
    public MapSchema required() {
        return (MapSchema) super.required();
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addCheck(
                "shape",
                value -> schemas.entrySet().stream().allMatch(
                        schema -> {
                            Object o = value.get(schema.getKey());
                            return schema.getValue().isValid((T) o);
                        }
                )
        );
        return this;
    }



}
