package ra.dex;

import ra.common.JSONSerializable;
import ra.util.JSONParser;
import ra.util.JSONPretty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Good implements JSONSerializable {

    private Map<String,Object> toExchange;

    public Good() {
        this.toExchange = new HashMap<>();
        toExchange.put("id", UUID.randomUUID());
    }

    public Good(Map<String,Object> toExchange) {
        if(toExchange.get("id")==null) {
            toExchange.put("id", UUID.randomUUID());
        }
        this.toExchange = toExchange;
    }

    public void setToExchange(Map<String,Object> toExchange) {
        if(toExchange.get("id")==null) {
            toExchange.put("id", UUID.randomUUID());
        }
        this.toExchange = toExchange;
    }

    public Object getToExchange() {
        return toExchange;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> m = new HashMap<>();
        if(toExchange!=null) m.put("toExchange", toExchange);
        return m;
    }

    @Override
    public void fromMap(Map<String, Object> m) {
        if(m.get("toExchange")!=null) toExchange = (Map<String, Object>) m.get("toExchange");
    }

    public String toJSON() {
        return JSONPretty.toPretty(JSONParser.toString(this.toMap()), 4);
    }

    public void fromJSON(String json) {
        this.fromMap((Map)JSONParser.parse(json));
    }

    @Override
    public String toString() {
        return toJSON();
    }
}
