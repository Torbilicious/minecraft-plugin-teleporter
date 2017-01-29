package de.schrottwald;

import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportPlugin extends JavaPlugin {

    @Override
    public void onDisable() {

        super.onDisable();

        getLogger().info("onDisable has been invoked!");
    }

    @Override
    public void onEnable() {

        super.onEnable();

        getLogger().info("onEnable has been invoked!");
    }
}
