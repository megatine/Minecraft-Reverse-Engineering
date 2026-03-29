package com.miracsclient.Modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class Fly {
    public static boolean isEnabled = false;
    private static final int FALL_DELAY = 40;
    private static int ticks = FALL_DELAY;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!isEnabled || client.player == null) return;

            ClientPlayerEntity player = client.player;

            double motionY = 0;
            if (client.options.jumpKey.isPressed()) {
                motionY = 0.5;
            } else if (client.options.sneakKey.isPressed()) {
                motionY = -0.5;
            }

            ticks--;
            if (ticks <= 0) {
                motionY = -0.04;
                ticks = FALL_DELAY;
            }

            Vec3d forward = Vec3d.fromPolar(0, player.getYaw());
            Vec3d right = Vec3d.fromPolar(0, player.getYaw() + 90);
            double motionX = 0;
            double motionZ = 0;
            double speed = 0.5;

            if (client.options.forwardKey.isPressed()) {
                motionX += forward.x * speed;
                motionZ += forward.z * speed;
            }
            if (client.options.backKey.isPressed()) {
                motionX -= forward.x * speed;
                motionZ -= forward.z * speed;
            }
            if (client.options.rightKey.isPressed()) {
                motionX += right.x * speed;
                motionZ += right.z * speed;
            }
            if (client.options.leftKey.isPressed()) {
                motionX -= right.x * speed;
                motionZ -= right.z * speed;
            }

            player.setVelocity(motionX, motionY, motionZ);
        });
    }
}