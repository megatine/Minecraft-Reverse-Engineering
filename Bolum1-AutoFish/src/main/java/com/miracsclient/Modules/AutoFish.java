package com.miracsclient.Modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class AutoFish {
    private int countDown = -1;
    public static final AutoFish INSTANCE = new AutoFish();
    MinecraftClient client = MinecraftClient.getInstance();

    private AutoFish() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> this.tick(client));
    }

    void tick(MinecraftClient client){
        if(countDown > 0){
            countDown--;
        }else if(countDown == 0){
            countDown = -1;
            useRod(client);
            client.player.sendMessage(Text.of("§c[Hacked Client] §3 Olta kullanıldı."), true);
        }
    }

    void useRod(MinecraftClient client){
        if(client != null && client.player != null){
            client.interactionManager.interactItem(client.player, client.player.getActiveHand());
            client.player.swingHand(client.player.getActiveHand());
        }
    }

    public void setCountDown(int countDown){this.countDown = countDown;}
}
