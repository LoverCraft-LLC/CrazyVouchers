package com.badbones69.crazyvouchers.utils;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DyeUtil {
    private DyeUtil() {
        throw new AssertionError();
    }

    public static @NotNull DyeColor getDyeColor(@NotNull String value) {
        if (value.isEmpty()) {
            return DyeColor.WHITE;
        } else {
            DyeColor var10000;
            switch (value.toUpperCase()) {
                case "ORANGE" -> var10000 = DyeColor.ORANGE;
                case "MAGENTA" -> var10000 = DyeColor.MAGENTA;
                case "LIGHT_BLUE" -> var10000 = DyeColor.LIGHT_BLUE;
                case "YELLOW" -> var10000 = DyeColor.YELLOW;
                case "LIME" -> var10000 = DyeColor.LIME;
                case "PINK" -> var10000 = DyeColor.PINK;
                case "GRAY" -> var10000 = DyeColor.GRAY;
                case "LIGHT_GRAY" -> var10000 = DyeColor.LIGHT_GRAY;
                case "CYAN" -> var10000 = DyeColor.CYAN;
                case "PURPLE" -> var10000 = DyeColor.PURPLE;
                case "BLUE" -> var10000 = DyeColor.BLUE;
                case "BROWN" -> var10000 = DyeColor.BROWN;
                case "GREEN" -> var10000 = DyeColor.GREEN;
                case "RED" -> var10000 = DyeColor.RED;
                case "BLACK" -> var10000 = DyeColor.BLACK;
                default -> var10000 = DyeColor.WHITE;
            }

            return var10000;
        }
    }

    public static @NotNull Color getDefaultColor(@NotNull String color) {
        if (color.isEmpty()) {
            return Color.WHITE;
        } else {
            Color var10000;
            switch (color.toUpperCase()) {
                case "AQUA" -> var10000 = Color.AQUA;
                case "BLACK" -> var10000 = Color.BLACK;
                case "BLUE" -> var10000 = Color.BLUE;
                case "FUCHSIA" -> var10000 = Color.FUCHSIA;
                case "GRAY" -> var10000 = Color.GRAY;
                case "GREEN" -> var10000 = Color.GREEN;
                case "LIME" -> var10000 = Color.LIME;
                case "MAROON" -> var10000 = Color.MAROON;
                case "NAVY" -> var10000 = Color.NAVY;
                case "OLIVE" -> var10000 = Color.OLIVE;
                case "ORANGE" -> var10000 = Color.ORANGE;
                case "PURPLE" -> var10000 = Color.PURPLE;
                case "RED" -> var10000 = Color.RED;
                case "SILVER" -> var10000 = Color.SILVER;
                case "TEAL" -> var10000 = Color.TEAL;
                case "YELLOW" -> var10000 = Color.YELLOW;
                default -> var10000 = Color.WHITE;
            }

            return var10000;
        }
    }

    public static @Nullable Color getColor(@NotNull String color) {
        Bukkit.getLogger().warning("Step 1: " + color);
        if (color.isEmpty()) {
            return null;
        } else {
            String[] rgb = color.split(",");
            if (rgb.length != 3) {
                return null;
            } else {
                int red = Integer.parseInt(rgb[0]);
                int green = Integer.parseInt(rgb[1]);
                int blue = Integer.parseInt(rgb[2]);
                return Color.fromRGB(red, green, blue);
            }
        }
    }
}