package com.miracsclient.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    @Inject(at = @At("TAIL"), method = "send")
    public void send(Packet packet, CallbackInfo ci){
        if(packet instanceof PlayerMoveC2SPacket){
            PlayerMoveC2SPacket packet1 = (PlayerMoveC2SPacket) packet;
            if(packet1.changesPosition()){
                System.out.println("Konum değişti x: " + packet1.getX(0) + ", y: " + packet1.getY(0) + ", z: " + packet1.getZ(0));
            }
        }
    }
}
