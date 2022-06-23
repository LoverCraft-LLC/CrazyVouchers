package com.badbones69.vouchers;

import com.badbones69.vouchers.api.enums.Version;
import com.badbones69.vouchers.api.FileManager.Files;
import com.badbones69.vouchers.api.enums.Messages;
import com.badbones69.vouchers.controllers.FireworkDamageAPI;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Methods {
    
    public static Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Vouchers");
    
    public final static Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F0-9]{6}");
    
    public static void removeItem(ItemStack item, Player player) {
        if (item.getAmount() <= 1) {
            player.getInventory().removeItem(item);
        } else if (item.getAmount() > 1) {
            item.setAmount(item.getAmount() - 1);
        }
    }
    
    public static String getPrefix(String message) {
        return color(Files.CONFIG.getFile().getString("Settings.Prefix") + message);
    }

    public static String color(String message) {
        if (Version.isNewer(Version.v1_15_R1)) {
            Matcher matcher = HEX_PATTERN.matcher(message);
            StringBuffer buffer = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.of(matcher.group()).toString());
            }
            return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    
    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isInt(CommandSender sender, String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            HashMap<String, String> placeholders = new HashMap<>();
            placeholders.put("%Arg%", s);
            placeholders.put("%arg%", s);
            sender.sendMessage(Messages.NOT_A_NUMBER.getMessage(placeholders));
            return false;
        }
        return true;
    }
    
    public static boolean isOnline(CommandSender sender, String name) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        sender.sendMessage(Messages.NOT_ONLINE.getMessage());
        return false;
    }
    
    public static boolean hasPermission(Player player, String perm) {
        if (!player.hasPermission("voucher." + perm)) {
            player.sendMessage(Messages.NO_PERMISSION.getMessage());
            return false;
        }
        return true;
    }
    
    public static boolean hasPermission(CommandSender sender, String perm) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission("voucher." + perm)) {
                player.sendMessage(Messages.NO_PERMISSION.getMessage());
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
    
    public static boolean isInventoryFull(Player player) {
        return player.getInventory().firstEmpty() == -1;
    }
    
    public static void fireWork(Location loc, List<Color> list) {
        final Firework f = loc.getWorld().spawn(loc, Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        fm.addEffects(FireworkEffect.builder().with(FireworkEffect.Type.BALL_LARGE).withColor(list).trail(false).flicker(false).build());
        fm.setPower(0);
        f.setFireworkMeta(fm);
        FireworkDamageAPI.addFirework(f);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, f :: detonate, 2);
    }
    
    public static ItemStack addGlow(ItemStack item, boolean glowing) {
        if (Version.isNewer(Version.v1_7_R3)) {
            if (glowing) {
                if (item != null) {
                    if (item.hasItemMeta()) {
                        if (item.getItemMeta().hasEnchants()) {
                            return item;
                        }
                    }

                    item.addUnsafeEnchantment(Enchantment.LUCK, 1);
                    ItemMeta meta = item.getItemMeta();
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    item.setItemMeta(meta);
                }
            }
        }
        return item;
    }
    
    public static Color getColor(String color) {
        if (color.equalsIgnoreCase("AQUA")) return Color.AQUA;
        if (color.equalsIgnoreCase("BLACK")) return Color.BLACK;
        if (color.equalsIgnoreCase("BLUE")) return Color.BLUE;
        if (color.equalsIgnoreCase("FUCHSIA")) return Color.FUCHSIA;
        if (color.equalsIgnoreCase("GRAY")) return Color.GRAY;
        if (color.equalsIgnoreCase("GREEN")) return Color.GREEN;
        if (color.equalsIgnoreCase("LIME")) return Color.LIME;
        if (color.equalsIgnoreCase("MAROON")) return Color.MAROON;
        if (color.equalsIgnoreCase("NAVY")) return Color.NAVY;
        if (color.equalsIgnoreCase("OLIVE")) return Color.OLIVE;
        if (color.equalsIgnoreCase("ORANGE")) return Color.ORANGE;
        if (color.equalsIgnoreCase("PURPLE")) return Color.PURPLE;
        if (color.equalsIgnoreCase("RED")) return Color.RED;
        if (color.equalsIgnoreCase("SILVER")) return Color.SILVER;
        if (color.equalsIgnoreCase("TEAL")) return Color.TEAL;
        if (color.equalsIgnoreCase("WHITE")) return Color.WHITE;
        if (color.equalsIgnoreCase("YELLOW")) return Color.YELLOW;
        return Color.WHITE;
    }
    
    public static boolean isSimilar(ItemStack one, ItemStack two) {
        if (one.getType() == two.getType()) {
            if (one.hasItemMeta()) {
                if (one.getItemMeta().hasDisplayName()) {
                    if (one.getItemMeta().getDisplayName().equalsIgnoreCase(two.getItemMeta().getDisplayName())) {
                        if (one.getItemMeta().hasLore()) {
                            if (one.getItemMeta().getLore().size() == two.getItemMeta().getLore().size()) {
                                int i = 0;
                                for (String lore : one.getItemMeta().getLore()) {
                                    if (!lore.equals(two.getItemMeta().getLore().get(i))) {
                                        return false;
                                    }
                                    i++;
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
}