package com.badbones69.crazyvouchers.listeners;

import com.badbones69.crazyvouchers.api.enums.PersistentKeys;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;

public class FireworkDamageListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onFireworkDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if (!(damager instanceof Firework firework)) return;

        PersistentDataContainer container = firework.getPersistentDataContainer();
        if (container.has(PersistentKeys.no_firework_damage.getNamespacedKey())) event.setCancelled(true);
    }
}