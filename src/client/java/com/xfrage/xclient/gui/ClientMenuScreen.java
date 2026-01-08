package com.xfrage.xclient.gui;

import com.xfrage.xclient.module.Module;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClientMenuScreen extends Screen {

    public ClientMenuScreen() {
        super(Text.literal("xClient Menu"));
    }

    @Override
    public void init() {
        int y = 50;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("Performance Mods"),
                btn -> this.client.setScreen(new PerformanceModsScreen(this))
        ).dimensions(this.width / 2 - 100, y, 200, 20).build());

        y+=30;

        this.addDrawableChild(ButtonWidget.builder(
                Text.literal("User Mods"),
                btn -> this.client.setScreen(new UserModsScreen(this))
        ).dimensions(this.width / 2 - 100, y, 200, 20).build());

    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    private Text getButtonText(Module module) {
        return Text.literal(
                module.getName() + ": " + (module.isEnabled() ? "ON" : "OFF")
        );
    }

}
