package com.example.client;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * The bot's control panel. Open it with the key bound in
 * Options > Controls > Key Binds (default: K).
 */
public class DiggerScreen extends Screen {

    public DiggerScreen() {
        super(Component.literal("AFK Digger"));
    }

    // init() runs when the screen opens. Widgets (buttons) are created here,
    // not in the constructor, because the screen size is not known yet.
    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        // Start / stop button. Clicking it flips the bot on or off,
        // then updates its own label.
        Button toggleButton = Button.builder(toggleLabel(), button -> {
            if (DiggerBot.isRunning()) {
                DiggerBot.stop();
            } else {
                DiggerBot.start();
            }
            button.setMessage(toggleLabel());
        }).bounds(centerX - 90, centerY - 24, 180, 20).build();
        this.addRenderableWidget(toggleButton);

        // Close button.
        Button closeButton = Button.builder(Component.literal("Close"), button -> this.onClose())
            .bounds(centerX - 90, centerY + 4, 180, 20)
            .build();
        this.addRenderableWidget(closeButton);
    }

    private static Component toggleLabel() {
        if (DiggerBot.isRunning()) {
            return Component.literal("Digger: ON (click to stop)");
        }
        return Component.literal("Digger: OFF (click to start)");
    }
}
