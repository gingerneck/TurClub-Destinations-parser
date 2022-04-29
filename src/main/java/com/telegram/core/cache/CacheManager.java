package com.telegram.core.cache;

import com.telegram.Parser.ParsePIK;
import com.telegram.core.model.Destination;
import com.telegram.core.model.Route;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheManager {

    private static CacheManager instance;
    private final Map<String, Object> cache = Collections.synchronizedMap(new HashMap<>());

    private CacheManager() {
    }

    public void put(String cacheKey, Object value) {
        cache.put(cacheKey, value);
    }

    public Object get(String cacheKey) {
        return cache.get(cacheKey);
    }

    public String getFirstKey() {
        return cache.keySet().stream().findFirst().get();
    }

    public Object getFirstValue() {
        return cache.values().stream().findFirst().get();
    }

    public boolean contains(String cacheKey) {
        return cache.containsKey(cacheKey);
    }

    public void clear(String cacheKey) {
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public static List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        instance.cache.forEach((key, value) ->
                keys.add(key));
        return keys;
    }

    public synchronized static boolean isInit() {
        return instance != null && instance.cache.size() > 0;
    }

    @SneakyThrows
    public static void init() {
        System.out.println("START CACHE INIT");
        ParsePIK parsePIK = new ParsePIK();
        Map<Destination, List<Route>> routes = parsePIK.getInfoRoute();
        //todo validation
        //      if (parsePIK.isValidParsedData(routes)) {
        CacheManager cache = CacheManager.getInstance();
        cache.clear();

        routes.forEach(((destination, routesList) -> {
            cache.put(destination.getNameDestination(), routesList);
        }));
        System.out.println(routes.size());
        System.out.println(String.join(",", cache.cache.keySet()));
        System.out.println("Cache initialized.");
        //    } else {
        //        System.out.println("Cache not initialized. Cause routes not valid!");
        //    }

    }

    public synchronized static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }
}