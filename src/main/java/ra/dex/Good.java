package ra.dex;

import ra.common.JSONSerializable;
import ra.common.JSONParser;
import ra.common.JSONPretty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Good implements JSONSerializable {

    private String category;
    private String type;
    private String clazz;
    private UUID id;
    private Map<String,Object> toExchange;

    public Good() {
        this.toExchange = new HashMap<>();
    }

    public Good(Map<String,Object> toExchange) {
        this.toExchange = toExchange;
    }

    public void setToExchange(Map<String,Object> toExchange) {
        this.toExchange = toExchange;
    }

    public Object getToExchange() {
        return toExchange;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> m = new HashMap<>();
        if(category!=null) m.put("category", category);
        if(type!=null) m.put("type", type);
        if(clazz!=null) m.put("clazz", clazz);
        if(id!=null) m.put("id", id.toString());
        if(toExchange!=null) m.put("toExchange", toExchange);
        return m;
    }

    @Override
    public void fromMap(Map<String, Object> m) {
        if(m.get("category")!=null) category = (String)m.get("category");
        if(m.get("type")!=null) type = (String)m.get("type");
        if(m.get("clazz")!=null) clazz = (String)m.get("clazz");
        if(m.get("id")!=null) id = UUID.fromString((String)m.get("id"));
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
