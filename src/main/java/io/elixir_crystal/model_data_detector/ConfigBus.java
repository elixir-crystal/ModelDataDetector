package io.elixir_crystal.model_data_detector;

import redempt.redlib.configmanager.annotations.ConfigValue;
import redempt.redlib.misc.FormatUtils;

import java.util.ArrayList;
import java.util.List;

public class ConfigBus {

    @ConfigValue("prefix")
    public String prefix = FormatUtils.color("&f[&e检测&f] ");

    @ConfigValue("bypassPerm")
    public String bypassPerm = "modatadetector.bypass";

    @ConfigValue("filters")
    public List<String> filters = new ArrayList<>();

    public ConfigBus() {
        filters.add("SKULL;1;ebpgive <player> orange");
    }

}
