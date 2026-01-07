package com.xfrage.xclient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class xClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("xClient Client initialised");

        // HUD, Keybinds, GUI hier
    }
}