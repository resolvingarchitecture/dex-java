package ra.dex;

import ra.common.JSONSerializable;
import ra.common.JSONParser;
import ra.common.JSONPretty;

import java.util.*;

public class Offer implements JSONSerializable {

    private List<Good> from = new ArrayList<>();
    private List<Good> to = new ArrayList<>();
    private Method method;

    public Offer() {

    }

    public Offer(Good from, Good to, Method method) {
        this.from.add(from);
        this.to.add(to);
        this.method = method;
    }

    public Offer(List<Good> from, List<Good> to, Method method) {
        this.from = from;
        this.to = to;
        this.method = method;
    }

    public List<Good> getFrom() {
        return from;
    }

    public void setFrom(List<Good> from) {
        this.from = from;
    }

    public List<Good> getTo() {
        return to;
    }

    public void setTo(List<Good> to) {
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
