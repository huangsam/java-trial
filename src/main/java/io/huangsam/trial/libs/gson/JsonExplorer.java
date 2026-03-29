package io.huangsam.trial.libs.gson;

import com.google.gson.Gson;

public class JsonExplorer {
    private static final Gson GSON = new Gson();

    public String toJson(Object src) {
        return GSON.toJson(src);
    }

    public <T> T fromJson(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }
}
