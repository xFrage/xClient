package com.xfrage.xclient;

import com.xfrage.xclient.module.ModuleManager;
import com.xfrage.xclient.module.types.user.CoordinatesModule;
import com.xfrage.xclient.module.types.user.FPSModule;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class Main implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        System.out.println("xClient Client initialised");

        initModules();

    }

    private void initModules() {
        ModuleManager moduleManager = new ModuleManager();
        ModuleManager.INSTANCE = moduleManager;

        moduleManager.initMenuKeybind(); // init xClient Menu

        moduleManager.registerModule(new FPSModule()); // true
        moduleManager.registerModule(new CoordinatesModule()); // true

    }
}