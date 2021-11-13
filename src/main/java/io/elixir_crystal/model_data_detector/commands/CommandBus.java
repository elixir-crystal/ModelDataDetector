package io.elixir_crystal.model_data_detector.commands;

import io.elixir_crystal.model_data_detector.PlugGividado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import redempt.redlib.commandmanager.CommandHook;
import redempt.redlib.misc.FormatUtils;

@RequiredArgsConstructor
@Getter
public class CommandBus {

    private final PlugGividado plug;

    @CommandHook("reload")
    public void reload(CommandSender sender) {
        Bukkit.getScheduler().runTask(getPlug(), new ReloadRunnable(getPlug(), sender));
    }

    @AllArgsConstructor
    @Getter
    private class ReloadRunnable implements Runnable {

        private final PlugGividado plug;
        private final CommandSender sender;

        @Override
        public void run() {
            try {
                getPlug().getCman().load();
                getSender().sendMessage(FormatUtils.color(getPlug().getPrefix() + "&fReloaded"));
            } catch (Exception e) {
                getSender().sendMessage(FormatUtils.color(getPlug().getPrefix() + "&cError occurred: " + e.getMessage()));
            }
        }

    }


}
