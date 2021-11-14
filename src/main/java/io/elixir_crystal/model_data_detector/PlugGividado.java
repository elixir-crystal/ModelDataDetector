package io.elixir_crystal.model_data_detector;

import io.elixir_crystal.model_data_detector.commands.CommandBus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.configmanager.ConfigManager;
import redempt.redlib.misc.EventListener;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
public class PlugGividado extends JavaPlugin {

    private Plugin plug;
    private ConfigManager cman;

    @SneakyThrows
    @Override
    public void onEnable() {

        plug = this;
        cman = new ConfigManager(this).register(new ConfigBus()).saveDefaults().load();
        new CommandParser(getPlug().getResource("command.rdcml")).parse().register(getPlug().getDescription().getName(),
                new CommandBus(this));

        new EventListener<>(this, InventoryOpenEvent.class, e -> {
            if (e.isCancelled()) return;
            if (e.getPlayer().hasPermission(Objects.requireNonNull(getCman().getConfig().getString("bypassPerm"))))
                return;

            int amount = 0;
            for (ItemStack i : e.getPlayer().getInventory().getContents()) {
                if (i == null || !i.hasItemMeta() || !i.getItemMeta().hasCustomModelData()) {
                    debug("continue");
                    continue;
                }
                for (String s : getCman().getConfig().getStringList("filters")) {
                    String[] args = s.split(";");
                    debug(Arrays.toString(args));
                    debug(i.getType().name() + " " + i.getItemMeta().getCustomModelData());
                    if (i.getType().name().equals(args[0])) {
                        debug("0");
                        if (i.getItemMeta().getCustomModelData() == Integer.parseInt(args[1])) {
                            debug("1");
                            i.setType(Material.AIR);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), args[2].replaceAll("<player>", e.getPlayer().getName()));
                            amount++;
                        }
                    }
                }
            }

            debug("finish " + amount);
            if (getCman().getConfig().getBoolean("enableNotify") && amount > 0) {
                e.getPlayer().sendMessage(Objects.requireNonNull(getCman().getConfig().getString("notify")).replaceAll("<amuont>", String.valueOf(amount)));
            }
        });

        new EventListener<>(this, InventoryInteractEvent.class, e -> {
            if (e.isCancelled()) return;
            if (e.getWhoClicked().hasPermission(Objects.requireNonNull(getCman().getConfig().getString("bypassPerm"))))
                return;

            int amount = 0;
            for (ItemStack i : e.getWhoClicked().getInventory().getContents()) {
                if (i == null || !i.hasItemMeta() || !i.getItemMeta().hasCustomModelData()) {
                    debug("continue");
                    continue;
                }
                for (String s : getCman().getConfig().getStringList("filters")) {
                    String[] args = s.split(";");
                    debug(Arrays.toString(args));
                    debug(i.getType().name() + " " + i.getItemMeta().getCustomModelData());
                    if (i.getType().name().equals(args[0])) {
                        debug("0");
                        if (i.getItemMeta().getCustomModelData() == Integer.parseInt(args[1])) {
                            debug("1");
                            i.setType(Material.AIR);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), args[2].replaceAll("<player>", e.getWhoClicked().getName()));
                            amount++;
                        }
                    }
                }
            }

            debug("finish " + amount);
            if (getCman().getConfig().getBoolean("enableNotify") && amount > 0) {
                e.getWhoClicked().sendMessage(Objects.requireNonNull(getCman().getConfig().getString("notify")).replaceAll("<amuont>", String.valueOf(amount)));
            }
        });

        new EventListener<>(this, InventoryInteractEvent.class, e -> {
            if (e.isCancelled()) return;
            if (e.getWhoClicked().hasPermission(Objects.requireNonNull(getCman().getConfig().getString("bypassPerm"))))
                return;

            int amount = 0;
            for (ItemStack i : e.getWhoClicked().getInventory().getContents()) {
                if (i == null || !i.hasItemMeta() || !i.getItemMeta().hasCustomModelData()) {
                    debug("continue");
                    continue;
                }
                for (String s : getCman().getConfig().getStringList("filters")) {
                    String[] args = s.split(";");
                    debug(Arrays.toString(args));
                    debug(i.getType().name() + " " + i.getItemMeta().getCustomModelData());
                    if (i.getType().name().equals(args[0])) {
                        debug("0");
                        if (i.getItemMeta().getCustomModelData() == Integer.parseInt(args[1])) {
                            debug("1");
                            i.setType(Material.AIR);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), args[2].replaceAll("<player>", e.getWhoClicked().getName()));
                            amount++;
                        }
                    }
                }
            }

            debug("finish " + amount);
            if (getCman().getConfig().getBoolean("enableNotify") && amount > 0) {
                e.getWhoClicked().sendMessage(Objects.requireNonNull(getCman().getConfig().getString("notify")).replaceAll("<amuont>", String.valueOf(amount)));
            }
        });


    }

    public String getPrefix() {
        return cman.getConfig().getString("prefix");
    }

    public boolean isDebug() {
        return cman.getConfig().getBoolean("debug");
    }

    private void debug(String msg) {
        if (isDebug()) getPlug().getLogger().warning(msg);
    }

}
