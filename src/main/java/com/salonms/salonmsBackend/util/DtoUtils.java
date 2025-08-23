package com.salonms.salonmsBackend.util;

import java.util.Optional;
import java.util.function.Function;

public class DtoUtils {
    public static <T, R> R navigate(T object, Function<T, R> mapper, R defaultValue) {
        try {
            return (R) Optional.ofNullable(object).map(mapper).orElse(defaultValue);
        } catch (Exception var4) {
            return defaultValue;
        }
    }
}
