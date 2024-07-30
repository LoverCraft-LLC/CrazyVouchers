package com.badbones69.crazyvouchers.vital;

import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class VitalPaper extends Vital {
    private final JavaPlugin plugin;
    private boolean isLogging;

    public VitalPaper(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public final @NotNull File getDirectory() {
        return this.plugin.getDataFolder();
    }

    public final @NotNull ComponentLogger getLogger() {
        return this.plugin.getComponentLogger();
    }

    public void saveResource(String fileName, boolean replace) {
        this.plugin.saveResource(fileName, replace);
    }

    public final boolean isLogging() {
        return this.isLogging;
    }

    public void setLogging(boolean isLogging) {
        this.isLogging = isLogging;
    }
}