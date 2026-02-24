package com.miracsclient.Modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class AutoFish {
    public static boolean isEnabled = false;
    private int countDown = -1;
    public static final AutoFish INSTANCE = new AutoFish();
    private final MinecraftClient client = MinecraftClient.getInstance();

    private AutoFish() {
        ClientTickEvents.END_CLIENT_TICK.register(this::tick);
    }

    private void tick(MinecraftClient client) {
        if (!isEnabled) return;

        if (countDown > 0) {
            countDown--;
        } else if (countDown == 0) {
            countDown = -1;
            useRod(client);
            if (client.player != null) {
                client.player.sendMessage(Text.of("§c[Hacked Client] §3 Olta kullanildi."), true);
            }
        }
    }

    private void useRod(MinecraftClient client) {
        if (client != null && client.player != null) {
            client.interactionManager.interactItem(client.player, client.player.getActiveHand());
            client.player.swingHand(client.player.getActiveHand());
        }
    }

    public void setCountDown(int countDown) {
        this.countDown = countDown;
    }

    public int getCountDown() {
        return this.countDown;
    }
}