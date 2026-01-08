package com.xfrage.xclient.module.types.user;

import com.xfrage.xclient.module.Module;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class FPSModule extends Module {

    private final MinecraftClient mc = MinecraftClient.getInstance();
    private int fps;
    private int frameCount;
    private long lastTime;

    public FPSModule() {
        super("FPS", "User", true);
        lastTime = System.currentTimeMillis();
        HudElement element = new HudElement() {
            @Override
            public void render(DrawContext context, RenderTickCounter tickCounter) {
                if (!isEnabled() || mc.player == null) return;
                frameCount++;
                long now = System.currentTimeMillis();
                if (now - lastTime >= 1000) {
                    fps = frameCount;
                    frameCount = 0;
                    lastTime = now;
                }

                context.drawText(
                        mc.textRenderer,
                        "FPS: " + fps,
                        5,
                        5,
                        0xFFFFFFFF,
                        true
                );

            }
        };

        HudElementRegistry.addLast(
                Identifier.of("modid", "fps_hud"),
                element
        );
    }

    @Override
    public void onEnable() {
        System.out.println(getName() + " activated");
    }

    @Override
    public void onDisable() {
        System.out.println(getName() + " deactivated");
    }

}
