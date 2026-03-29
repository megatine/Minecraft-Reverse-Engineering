package com.miracsclient.Modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

public class Reach {

    public static boolean isEnabled = false;

    private static final double MAX_REACH = 100.0;
    private static final double STEP_SIZE = 8.0;

    private static boolean wasAttackDown = false;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!isEnabled || client.player == null || client.world == null) return;

            long window = client.getWindow().getHandle();
            boolean isAttackDown = GLFW.glfwGetMouseButton(window, GLFW.GLFW_MOUSE_BUTTON_LEFT) == GLFW.GLFW_PRESS;
            boolean justPressed = isAttackDown && !wasAttackDown;
            wasAttackDown = isAttackDown;

            if (!justPressed) return;

            Entity target = getTarget(client);
            if (target == null) return;

            executeReach(client, target);
        });
    }

    private static Entity getTarget(MinecraftClient client) {
        Vec3d camera = client.player.getCameraPosVec(1.0F);
        Vec3d rotation = client.player.getRotationVec(1.0F);
        Vec3d end = camera.add(rotation.multiply(MAX_REACH));

        Entity closest = null;
        double closestDist = MAX_REACH;

        for (Entity entity : client.world.getEntities()) {
            if (entity == client.player || !entity.isAlive()) continue;

            Optional<Vec3d> hit = entity.getBoundingBox().expand(0.3).raycast(camera, end);
            if (hit.isEmpty()) continue;

            double dist = camera.distanceTo(hit.get());
            if (dist < closestDist) {
                closestDist = dist;
                closest = entity;
            }
        }

        return closest;
    }

    private static void executeReach(MinecraftClient client, Entity target) {
        Vec3d origin = client.player.getPos();
        Vec3d dest = target.getPos();
        double distance = origin.distanceTo(dest);

        // actionbar
        client.player.sendMessage(
                Text.literal("§c" + target.getName().getString() + " §7| §e" + String.format("%.1f", distance) + "m"),
                true
        );

        int steps = Math.max(1, (int) Math.ceil(distance / STEP_SIZE));
        double dx = dest.x - origin.x;
        double dy = dest.y - origin.y;
        double dz = dest.z - origin.z;

        // hedefe git
        for (int i = 1; i <= steps; i++) {
            double t = (double) i / steps;
            sendPos(client, origin.x + dx * t, origin.y + dy * t, origin.z + dz * t);
        }

        // vur
        client.getNetworkHandler().sendPacket(
                PlayerInteractEntityC2SPacket.attack(target, client.player.isSneaking())
        );
        client.player.playSound(
                net.minecraft.sound.SoundEvents.ENTITY_PLAYER_ATTACK_STRONG,
                1.0F,
                1.0F
        );

        // geri dön
        for (int i = steps - 1; i >= 0; i--) {
            double t = (double) i / steps;
            sendPos(client, origin.x + dx * t, origin.y + dy * t, origin.z + dz * t);
        }
    }

    private static void sendPos(MinecraftClient client, double x, double y, double z) {
        client.getNetworkHandler().sendPacket(
                new PlayerMoveC2SPacket.PositionAndOnGround(x, y, z, true, false)
        );
    }
}