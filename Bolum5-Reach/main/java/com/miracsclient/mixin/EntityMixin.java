package com.miracsclient.mixin;

import com.miracsclient.Modules.Esp;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "Lnet/minecraft/entity/Entity;isGlowing()Z", at=@At("HEAD"), cancellable = true)
    public void onIsGlowing(CallbackInfoReturnable<Boolean> cl){
        if(Esp.isEnabled){
            Entity entity = (Entity) (Object) this;

            if(entity instanceof LivingEntity){
                cl.setReturnValue(true);
            }
        }
    }
}