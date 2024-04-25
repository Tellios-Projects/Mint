package net.leafenzo.mint.mixin.compat.create;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.DyeHelper;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Pseudo
@Mixin(DyeHelper.class)
public class DyeHelperMixin {
//    @ModifyReceiver(method = "DYE_TABLE")

//    @Inject(method = "<clinit>()V", at = @At("HEAD"))
//    private static void Debug(CallbackInfo ci) {
//        String test = "meow";
//    }

//    @ModifyReturnValue(method = "<clinit>()V", at = @At("RETURN"))
//    private static Map<DyeColor, Couple<Integer>> appendToDyeTable(Map<DyeColor, Couple<Integer>> original) {
//        return original;
//    }
}
