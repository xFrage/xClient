package com.xfrage.xclient.module;

import com.xfrage.xclient.gui.ClientMenuScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public static ModuleManager INSTANCE;

    private static final List<Module> modules = new ArrayList<>();
    private static final List<KeyBinding> keyBindings = new ArrayList<>();

    private static KeyBinding openMenuKey;

    public ModuleManager() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            // register tick event
            for (Module module : modules) {
                if (module.isEnabled()) {
                    module.onTick();
                }
            }

            // keybinds
            for (int i = 0; i < keyBindings.size(); i++) {
                KeyBinding key = keyBindings.get(i);
                if (key.wasPressed()) {
                    modules.get(i).toggle();
                }
            }
        });
    }

    public void registerModule(Module module) {
        modules.add(module);

        // create default keybind (no key = unbound)
        KeyBinding key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                module.getName(),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_UNKNOWN,
                "xClient"
        ));
        keyBindings.add(key);
    }

    public void initMenuKeybind() {
        openMenuKey = KeyBindingHelper.registerKeyBinding( new KeyBinding(
                "open client menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "xClient"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (openMenuKey.wasPressed()) {
                client.setScreen(new ClientMenuScreen());
            }
        });
    }

    public static List<Module> getModules() {
        return modules;
    }

    public Module getModuleByName(String name) {
        return modules.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
