package com.example.client;

import com.example.ExampleMod;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ExampleModClient implements ClientModInitializer {

    // The group our key appears under in Options > Controls > Key Binds.
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
        Identifier.fromNamespaceAndPath(ExampleMod.MOD_ID, "afk_digger"));

    private static KeyMapping openGuiKey;

    @Override
    public void onInitializeClient() {
        // Register a key (default Right Shift) that opens the control panel.
        openGuiKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
            "key.modid.open_digger",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_RIGHT_SHIFT,
            CATEGORY));

        // Runs once per game tick.
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Open the GUI when the key is pressed (only while in a world, with no other screen open).
            while (openGuiKey.consumeClick()) {
                if (client.player != null && client.screen == null) {
                    client.setScreen(new DiggerScreen());
                }
            }

            // Run the bot's own tick.
            DiggerBot.tick(client);
        });
    }
}
