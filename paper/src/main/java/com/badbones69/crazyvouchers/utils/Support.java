package com.badbones69.crazyvouchers.utils;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public enum Support {
    oraxen("Oraxen"),
    items_adder("ItemsAdder"),
    head_database("HeadDatabase"),
    cmi("cmi"),
    fancy_holograms("FancyHolograms"),
    decent_holograms("DecentHolograms"),
    placeholder_api("PlaceholderAPI");

    private final String name;

    private Support(final String name) {
        this.name = name;
    }

    public final boolean isEnabled() {
        return Bukkit.getServer().getPluginManager().isPluginEnabled(this.name);
    }

    public final @NotNull String getName() {
        return this.name;
    }
}