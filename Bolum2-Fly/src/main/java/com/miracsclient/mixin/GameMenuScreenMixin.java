package com.miracsclient.mixin;

import com.miracsclient.Modules.AutoFish;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "init")
    private void addAutoFishButton(CallbackInfo ci) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal("AutoFish: " + (AutoFish.isEnabled ? "ON" : "OFF")), button -> {
            AutoFish.isEnabled = !AutoFish.isEnabled;
            button.setMessage(Text.literal("AutoFish: " + (AutoFish.isEnabled ? "ON" : "OFF")));
        }).dimensions(10, 10, 120, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Fly: " + (com.miracsclient.Modules.Fly.isEnabled ? "ON" : "OFF")), button -> {
            com.miracsclient.Modules.Fly.isEnabled = !com.miracsclient.Modules.Fly.isEnabled;
            button.setMessage(Text.literal("Fly: " + (com.miracsclient.Modules.Fly.isEnabled ? "ON" : "OFF")));
        }).dimensions(10, 35, 120, 20).build());
    }
}