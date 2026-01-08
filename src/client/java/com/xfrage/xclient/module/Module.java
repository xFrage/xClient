package com.xfrage.xclient.module;

public abstract class Module {

    private final String name;
    private final String category; // performance, user, etc.
    private boolean enabled;

    protected Module(String name, String category, boolean defaultEnabled) {
        this.name = name;
        this.category = category;
        this.enabled = defaultEnabled;
        if (enabled) onEnable();
    }

    public String getName() {
        return name;
    }

    public String getCategory() { return category; }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        enabled = !enabled;
        if (enabled) onEnable();
        else onDisable();
    }

    protected void onEnable() {};
    protected void onDisable() {};
    protected void onTick() {};

}
