package net.leafenzo.mint.mixin;

import net.minecraft.block.MapColor;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Debug(export = true)  // Debug from ModEarlyRiser if you're looking for the more extensive changes to this file
@Mixin(DyeColor.class)
public abstract class DyeColorMixin {
    @Shadow @Final @Mutable private int id;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void setIdToOrdinal(String enumName, int ordinal, int j, String string2, int k, MapColor mapColor, int l, int m, CallbackInfo ci) {
        if (this.id < 0)
            this.id = ordinal;
    }
}

// Source:
// ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/master/fabric/src/main/java/com/remodstudios/voidlands/fabric/mixin/DyeColorMixin.java