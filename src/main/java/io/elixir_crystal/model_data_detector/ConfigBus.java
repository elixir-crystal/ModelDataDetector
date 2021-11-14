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

    @ConfigValue("enableNotify")
    public boolean enableNotify = true;

    @ConfigValue("notify")
    public String notify = prefix + FormatUtils.color("&4检测并处理了你背包里的 &c<amuont> &4个物品");

    @ConfigValue("debug")
    public boolean debug = false;

    public ConfigBus() {
        filters.add("orange;ebpgive <player> orange");
    }

}
