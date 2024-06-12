package me.gamrboy4life.paradox.gvent;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GventManager {
    private static final Map<Class<? extends Gvent>, ArrayList<GventData>> REGISTRY_MAP = new HashMap<Class<? extends Gvent>, ArrayList<GventData>>();

    private static void sortListValue(final Class<? extends Gvent> clazz) {
        final ArrayList<GventData> flexibleArray = new ArrayList<GventData>();
        for (final byte b : GventPriority.VALUE_ARRAY) {
            for (GventData methodData : GventManager.REGISTRY_MAP.get(clazz)) {
                if (methodData.priority == b) {
                    flexibleArray.add(methodData);
                }
            }
        }
        GventManager.REGISTRY_MAP.put(clazz, flexibleArray);
    }
    private static boolean isMethodBad(final Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(GventTarget.class);
    }

    private static boolean isMethodBad(final Method method, final Class<? extends Gvent> clazz) {
        return isMethodBad(method) || method.getParameterTypes()[0].equals(clazz);
    }

    public static ArrayList<GventData> get(final Class<? extends Gvent> clazz) {
        return REGISTRY_MAP.get(clazz);
    }

    public static void cleanMap(final boolean removeOnlyEmptyValues) {
        final Iterator<Map.Entry<Class<? extends Gvent>, ArrayList<GventData>>> iterator = GventManager.REGISTRY_MAP.entrySet().iterator();
        while (iterator.hasNext()) {
            if (removeOnlyEmptyValues || iterator.next().getValue().isEmpty()) {
                iterator.remove();
            }
        }
    }

    public static void unregister(final Object o, final Class<? extends Gvent> clazz) {
        if (REGISTRY_MAP.containsKey(clazz)) {
            for (final GventData methodData : REGISTRY_MAP.get(clazz)) {
                if (methodData.source.equals(o)) {
                    REGISTRY_MAP.get(clazz).remove(methodData);
                }
            }
        }
    cleanMap(true);
    }
    
    public static void unregister(final Object o) {
        for (ArrayList<GventData> flexibleArray : REGISTRY_MAP.values()) {
            for (int i = flexibleArray.size() - 1; i >= 0; i--) {
                if (flexibleArray.get(i).source.equals(o)) {
                    flexibleArray.remove(i);
                }
            }
        }
        cleanMap(true);
    }
    public static void register(final Method method, final Object o) {
        final Class<?> clazz = method.getParameterTypes()[0];
        final GventData methodData = new GventData(o, method, method.getAnnotation(GventTarget.class).value());
        if (!methodData.target.isAccessible()) {
            methodData.target.setAccessible(true);
        }
        if (REGISTRY_MAP.containsKey(clazz)) {
            if (!REGISTRY_MAP.get(clazz).contains(methodData)) {
                REGISTRY_MAP.get(clazz).add(methodData);
                sortListValue((Class<? extends Gvent>) clazz);
            }
        } else {
            REGISTRY_MAP.put((Class<? extends Gvent>) clazz, new ArrayList<GventData>() {
                {
                    add(methodData);
                }
            });
        }
    }

    public static void register(final Object o, final Class<? extends Gvent> clazz) {
        for (final Method method : o.getClass().getMethods()) {
            if (!isMethodBad(method, clazz)) {
                register(method, o);
            }
        }
    }
    
    public static void register(Object o) {
        for (final Method method : o.getClass().getMethods()) {
            if (!isMethodBad(method)) {
                register(method, o);
            }
        }
    }
    
    
    
    
    
}