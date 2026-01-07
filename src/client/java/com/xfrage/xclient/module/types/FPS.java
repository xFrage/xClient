package com.xfrage.xclient.module.types;

import com.xfrage.xclient.module.Module;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class FPS extends Module {

    public FPS() {
        super("FPSModule");
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (isEnabled()) onTick();
        });
    }
}
