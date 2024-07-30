package com.badbones69.crazyvouchers.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class StringUtil {
    private StringUtil() {
        throw new AssertionError();
    }

    public static String convertList(@NotNull List<String> list) {
        if (list.isEmpty()) {
            return "";
        } else {
            StringBuilder message = new StringBuilder();
            Iterator var2 = list.iterator();

            while (var2.hasNext()) {
                String line = (String) var2.next();
                message.append(line).append("\n");
            }

            return chomp(message.toString());
        }
    }

    public static @NotNull String chomp(@NotNull String key) {
        if (key.isEmpty()) {
            return key;
        } else {
            char CR = 13;
            char LF = 10;
            if (key.length() == 1) {
                char ch = key.charAt(0);
                return ch != CR && ch != LF ? key : "";
            } else {
                int lastIdx = key.length() - 1;
                char last = key.charAt(lastIdx);
                if (last == LF) {
                    if (key.charAt(lastIdx - 1) == CR) {
                        --lastIdx;
                    }
                } else if (last != CR) {
                    ++lastIdx;
                }

                return key.substring(0, lastIdx);
            }
        }
    }

    public static Optional<Number> tryParseInt(@NotNull String value) {
        try {
            return Optional.of(Integer.parseInt(value));
        } catch (NumberFormatException var2) {
            return Optional.empty();
        }
    }

    public static Optional<Boolean> tryParseBoolean(@NotNull String value) {
        try {
            return Optional.of(Boolean.parseBoolean(value));
        } catch (NumberFormatException var2) {
            return Optional.empty();
        }
    }
}