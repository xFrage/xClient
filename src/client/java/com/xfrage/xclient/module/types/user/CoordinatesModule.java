package com.xfrage.xclient.module.types.user;

import com.xfrage.xclient.module.Module;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;

public class CoordinatesModule extends Module {

    private final MinecraftClient mc = MinecraftClient.getInstance();

    public CoordinatesModule() {
        super("Coordinates", "User", true);

        HudElement element = new HudElement() {
            @Override
            public void render(DrawContext context, RenderTickCounter tickCounter) {
                if (!isEnabled() || mc.player == null) return;

                ClientPlayerEntity player = mc.player;

                int x = (int) player.getX();
                int y = (int) player.getY();
                int z = (int) player.getZ();

                int xPos = 5;
                int yPos = 15;
                int lineHeight = mc.textRenderer.fontHeight + 2;

                context.drawText(mc.textRenderer, "X: " + x, xPos, yPos, 0xFFFFFFFF, true);
                context.drawText(mc.textRenderer, "Y: " + y, xPos, yPos + lineHeight, 0xFFFFFFFF, true);
                context.drawText(mc.textRenderer, "Z: " + z, xPos, yPos + lineHeight * 2, 0xFFFFFFFF, true);

            }
        };
        HudElementRegistry.addLast(
                Identifier.of("modid", "coordinates_hud"),
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
