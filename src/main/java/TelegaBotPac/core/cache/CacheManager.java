package TelegaBotPac.core.cache;

import TelegaBotPac.Parser.ParsePIK;
import TelegaBotPac.core.model.Destination;
import TelegaBotPac.core.model.Route;
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

    public boolean contains(String cacheKey) {
        return cache.containsKey(cacheKey);
    }

    public void clear(String cacheKey) {
        cache.put(cacheKey, null);
    }

    public void clear() {
        cache.clear();
    }

    public static List<String> getKeys(){
        List<String> keys = new ArrayList<>();
        instance.cache.forEach((key, value) ->
                keys.add(key));
        return keys;
    }

    public synchronized static boolean isInit() {
        return instance!=null && instance.cache.size()>0;
    }

    @SneakyThrows
    public static void init(){
        Map<Destination, List<Route>> routes = ParsePIK.getInfoRoute();
        CacheManager cache = CacheManager.getInstance();
        routes.forEach(((destination, routesList) -> {
            cache.put(destination.getNameDestination(), routesList);
        }));
        System.out.println("Cache initialized.");
    }

    public synchronized static CacheManager getInstance() {
        if (instance == null) {
            instance = new CacheManager();
        }
        return instance;
    }

}