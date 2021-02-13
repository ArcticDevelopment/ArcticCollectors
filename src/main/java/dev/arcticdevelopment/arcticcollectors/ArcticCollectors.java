package dev.arcticdevelopment.arcticcollectors;

import dev.finn.plugin.commands.giveCollector;
import dev.kyro.arcticapi.ArcticAPI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ArcticCollectors extends JavaPlugin {

    public static ArcticCollectors INSTANCE;
    public static Economy VAULT = null;

    private static final Logger log = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {

        INSTANCE = this;
        ArcticAPI.init(this, "&d&lCOLLECTORS&r &8|| &7", "&d&lCOLLECTORS&r &8|| &7");

        if(!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.getServer().getPluginManager().registerEvents(new dev.arcticdevelopment.arcticcollectors.listeners.CollectorListener(), this);

        loadConfig();

        registerCommands();
        registerListeners();

    }

    @Override
    public void onDisable() {


    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        VAULT = rsp.getProvider();
        return VAULT != null;
    }

    private void loadConfig() {

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerCommands() {

        getCommand("collector").setExecutor(new giveCollector());

    }

    private void registerListeners() {

//        getServer().getPluginManager().registerEvents(new PrinterEvents(), this);
    }
}

