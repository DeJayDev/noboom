package dev.dejay.noboom;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Noboom extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onCreeperPrime(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if(!(entity instanceof Creeper)) {
            return;
        }

        event.setCancelled(true);

        entity.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, entity.getLocation(), 1);
        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, SoundCategory.HOSTILE, 1, 0);
    }

    @EventHandler
    public void onCreeperDmg(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Creeper) {
            event.setCancelled(true);
        }
    }
}
