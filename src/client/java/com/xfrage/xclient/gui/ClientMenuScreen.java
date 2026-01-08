package com.xfrage.xclient.gui;

import com.xfrage.xclient.module.Module;
import com.xfrage.xclient.module.ModuleManager;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ClientMenuScreen extends Screen {

    public ClientMenuScreen() {
        super(Text.literal("xClient Menu"));
    }

    @Override
    public void init() {
        int y = 40;

        for (Module module : ModuleManager.getModules()) {
            ButtonWidget button = ButtonWidget.builder(
                    getButtonText(module),
                    btn -> {
                        module.toggle();
                        btn.setMessage(getButtonText(module));
                    }
            ).dimensions(this.width / 2 - 100, y, 200, 20).build();

            this.addDrawableChild(button);
            y += 40;
        }

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
