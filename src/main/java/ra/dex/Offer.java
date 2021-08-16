package ra.dex;

import ra.common.JSONSerializable;
import ra.util.JSONParser;
import ra.util.JSONPretty;

import java.util.List;
import java.util.Map;

public class Offer implements JSONSerializable {

    public List<Good> from;
    public List<Good> to;

    @Override
    public Map<String, Object> toMap() {
        return null;
    }

    @Override
    public void fromMap(Map<String, Object> map) {

    }

    public String toJSON() {
        return JSONPretty.toPretty(JSONParser.toString(this.toMap()), 4);
    }

    public void fromJSON(String json) {
        this.fromMap((Map)JSONParser.parse(json));
    }
}
