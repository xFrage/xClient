package com.xfrage.xclient.gui;

import com.xfrage.xclient.module.Module;
import com.xfrage.xclient.module.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class PerformanceModsScreen extends Screen {

    private final Screen parent;

    public PerformanceModsScreen(Screen parent) {
        super(Text.literal("Performance Mods"));
        this.parent = parent;
    }

    @Override
    public void init() {
        int y = 40;

        for (Module m : ModuleManager.getModules()) {
            if (!m.getCategory().equals("Performance")) continue;

            ButtonWidget button = ButtonWidget.builder(
                    getButtonText(m),
                    btn -> {
                        m.toggle();
                        btn.setMessage(getButtonText(m));
                    }
            ).dimensions(this.width / 2 - 100, y, 200, 20).build();

            this.addDrawableChild(button);
            y += 25;
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private Text getButtonText(Module module) {
        return Text.literal(module.getName() + ": " + (module.isEnabled() ? "ON" : "OFF"));
    }
}
