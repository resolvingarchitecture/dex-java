package ra.dex;

import ra.common.JSONSerializable;
import ra.common.JSONParser;
import ra.common.JSONPretty;

import java.util.*;

public class Offer implements JSONSerializable {

    private Good from;
    private Good to;
    private Method method;

    public Offer() {

    }

    public Offer(Good from, Good to, Method method) {
        this.from = from;
        this.to = to;
        this.method = method;
    }

    public Good getFrom() {
        return from;
    }

    public void setFrom(Good from) {
        this.from = from;
    }

    public Good getTo() {
        return to;
    }

    public void setTo(Good to) {
        this.to = to;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String,Object> m = new HashMap<>();

        return m;
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
