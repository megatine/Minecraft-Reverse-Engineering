package com.miracsclient.mixin;

import com.miracsclient.Modules.AutoFish;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {
    @Shadow private boolean caughtFish;

    @Inject(at = @At("TAIL"), method = "tick")
    public void onTick(CallbackInfo ci) {
        if (!AutoFish.isEnabled) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        if (caughtFish && AutoFish.INSTANCE.getCountDown() == -1) {
            client.interactionManager.interactItem(client.player, client.player.getActiveHand());
            client.player.swingHand(Hand.MAIN_HAND);
            AutoFish.INSTANCE.setCountDown(20);
            client.player.sendMessage(Text.of("§c[Hacked Client] §3 Balik Cekildi, geri sayim baslatildi."), true);
        }
    }
}