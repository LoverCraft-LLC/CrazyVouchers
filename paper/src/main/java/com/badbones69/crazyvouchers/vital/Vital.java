package com.badbones69.crazyvouchers.vital;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.lang.reflect.Field;

public abstract class Vital {
    public Vital() {
        try {
            Field api = Provider.class.getDeclaredField("api");
            api.setAccessible(true);
            api.set((Object) null, this);
        } catch (IllegalAccessException | NoSuchFieldException var2) {
            ReflectiveOperationException exception = var2;
            throw new RuntimeException(exception);
        }
    }

    public static @NotNull Vital api() {
        return Vital.Provider.api();
    }

    public abstract @NotNull File getDirectory();

    public abstract @NotNull ComponentLogger getLogger();

    public abstract void saveResource(String var1, boolean var2);

    public abstract boolean isLogging();

    protected static final class Provider {
        static Vital api;

        private Provider() {
            throw new AssertionError();
        }

        static @NotNull Vital api() {
            return api;
        }
    }
}