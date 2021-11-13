package io.elixir_crystal.model_data_detector;

import io.elixir_crystal.model_data_detector.commands.CommandBus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import redempt.redlib.commandmanager.CommandParser;
import redempt.redlib.configmanager.ConfigManager;
import redempt.redlib.misc.EventListener;

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

            for (ItemStack i : e.getInventory()) {
                for (String s : getCman().getConfig().getStringList("filters")) {
                    String[] args = s.split(";");
                    if (i.getType().equals(Material.getMaterial(args[0]))) {
                        if (i.getItemMeta().getCustomModelData() == Integer.parseInt(args[1])) {
                            i.setType(Material.AIR);
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), args[2]);
                        }
                    }
                }
            }

        });

    }

    public String getPrefix() {
        return cman.getConfig().getString("prefix");
    }

}
