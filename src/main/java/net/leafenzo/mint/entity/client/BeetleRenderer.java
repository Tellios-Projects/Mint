package net.leafenzo.mint.entity.client;

import net.leafenzo.mint.Super;
import net.leafenzo.mint.entity.BeetleEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BeetleRenderer extends GeoEntityRenderer<BeetleEntity> {
    public BeetleRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BeetleModel());
    }

    @Override
    public Identifier getTextureLocation(BeetleEntity animatable) {
        return new Identifier(Super.MOD_ID, "textures/entity/beetle.png");
    }

    @Override
    public void render(BeetleEntity entity, float entityYaw, float partialTick, MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.8f,0.8f,0.8f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
