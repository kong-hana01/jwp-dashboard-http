package org.apache.catalina.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Session {

    private final String id;
    private final Map<String, Object> values = new ConcurrentHashMap<>();

    public Session(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Object getAttribute(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        return null;
    }

    public void setAttribute(String key, Object value) {
        values.put(key, value);
    }

    public void removeAttribute(String key) {
        values.remove(key);
    }

    public void invalidate() {
        values.clear();
    }
}
