package net.leafenzo.mint.mixin.compat.create;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReceiver;
import com.simibubi.create.content.redstone.nixieTube.NixieTubeRenderer;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntities;

@Pseudo
@Mixin(NixieTubeRenderer.class)
public class NixieTubeRendererMixin {

//    @ModifyVariable(
//            method = "drawTube(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;Ljava/lang/String;FLnet/minecraft/util/DyeColor;)V",
//            at = @At(value = "INVOKE", target = "java/util/Map.get (Ljava/lang/Object;)Ljava/lang/Object;")
//    )
//    private static DyeColor[] truncateDyeColors(DyeColor[] original) {
//        // Use only vanilla dye colors, assuming that it expects nothing else to be there
//        return ModUtil.VANILLA_DYE_COLORS;
//    }

}
