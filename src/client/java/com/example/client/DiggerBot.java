package com.example.client;

import net.minecraft.client.Minecraft;

import com.example.ExampleMod;

/**
 * Holds the bot's state and runs once per game tick (20 times a second)
 * while the bot is switched on.
 */
public class DiggerBot {

    // The states the bot can be in. We will add more later
    // (SELLING, EATING, BUYING_FOOD, RECONNECTING, GIVING_UP).
    public enum State {
        IDLE,
        MINING
    }

    private static State state = State.IDLE;
    private static int tickCount = 0;

    public static State getState() {
        return state;
    }

    public static boolean isRunning() {
        return state != State.IDLE;
    }

    public static void start() {
        state = State.MINING;
        tickCount = 0;
    }

    public static void stop() {
        state = State.IDLE;
        Minecraft client = Minecraft.getInstance();
client.options.keyAttack.setDown(false);
client.options.keyUp.setDown(false);
    }

    // Called every tick by ExampleModClient.
    public static void tick(Minecraft client) {
        if (state == State.IDLE) {
            return;
        }

        // If we are not in a world (for example, back at the menu), switch off.
        if (client.player == null || client.level == null) {
            stop();
            return;
        }

        tickCount++;client.options.keyAttack.setDown(true);
client.options.keyUp.setDown(true);

        // Every 100 ticks (5 seconds), write a line to the game log.
        // This is just proof the loop is running. Real mining goes here later.
        if (tickCount % 100 == 0) {
            ExampleMod.LOGGER.info("Digger is running. State: {}, ticks: {}", state, tickCount);
        }
    }
}
