package com.clubtur.core.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManager {

    private final static String IS_INITILISING = "isinitilised";
    private static CacheManager instance;
    private final Map<String, Object> cache = Collections.synchronizedMap(new HashMap<>());

    private CacheManager() {
    }

    public void put(String cacheKey, Object value) {
        getInstance().cache.put(cacheKey, value);
    }

    public void putAll(Map<String, ? extends Object> data) {
        getInstance().cache.putAll(data);
    }

    public Object get(String cacheKey) {

        return getInstance().cache.get(cacheKey);
    }

    public String getFirstKey() {
        return getInstance().cache.keySet().stream().findFirst().get();
    }

    public Object getFirstValue() {
        return getInstance().cache.values().stream().findFirst().get();
    }

    public boolean contains(String cacheKey) {
        return getInstance().cache.containsKey(cacheKey);
    }

    public void clear(String cacheKey) {
        getInstance().cache.put(cacheKey, null);
    }

    public void clear() {
        getInstance().cache.clear();
    }

    public static List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        getInstance().cache.forEach((key, value) ->
                keys.add(key));
        return keys;
    }

    public synchronized static boolean isInit() {
        return instance != null && instance.cache.size() > 0;
    }

    public static CacheManager init() {
        System.out.println("START CACHE INIT");
        CacheManager cache = CacheManager.getInstance();
        cache.clear();
        System.out.println("Cache initialized.");
        return cache;
    }

    public synchronized static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }

    public static boolean isInitilising() {
        Object result = getInstance().get(IS_INITILISING);
        return result != null ? (Boolean) result : false;
    }

    public static void setInitilising(boolean isinitilising) {
        getInstance().put(IS_INITILISING, isinitilising);
    }
}