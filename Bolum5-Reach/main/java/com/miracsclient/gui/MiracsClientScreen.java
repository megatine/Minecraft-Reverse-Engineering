package com.miracsclient.gui;

import com.miracsclient.Modules.AutoFish;
import com.miracsclient.Modules.Esp;
import com.miracsclient.Modules.Fly;
import com.miracsclient.Modules.Reach;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class MiracsClientScreen extends Screen {
    private final Screen parent;

    public MiracsClientScreen(Screen parent) {
        super(Text.literal("Mirac's Client"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int startY = this.height / 2 - 50;
        int centerX = this.width / 2 - 60;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("AutoFish: " + (AutoFish.isEnabled ? "ON" : "OFF")), button -> {
            AutoFish.isEnabled = !AutoFish.isEnabled;
            button.setMessage(Text.literal("AutoFish: " + (AutoFish.isEnabled ? "ON" : "OFF")));
        }).dimensions(centerX, startY, 120, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Fly: " + (Fly.isEnabled ? "ON" : "OFF")), button -> {
            Fly.isEnabled = !Fly.isEnabled;
            button.setMessage(Text.literal("Fly: " + (Fly.isEnabled ? "ON" : "OFF")));
        }).dimensions(centerX, startY + 25, 120, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Esp: " + (Esp.isEnabled ? "ON" : "OFF")), button -> {
            Esp.isEnabled = !Esp.isEnabled;
            button.setMessage(Text.literal("Esp: " + (Esp.isEnabled ? "ON" : "OFF")));
        }).dimensions(centerX, startY + 50, 120, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Reach: " + (Reach.isEnabled ? "ON" : "OFF")), button -> {
            Reach.isEnabled = !Reach.isEnabled;
            button.setMessage(Text.literal("Reach: " + (Reach.isEnabled ? "ON" : "OFF")));
        }).dimensions(centerX, startY + 75, 120, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Geri Dön"), button -> {
            this.client.setScreen(parent);
        }).dimensions(centerX, startY + 110, 120, 20).build());
    }

    @Override
    public void close() {
        this.client.setScreen(parent);
    }
}