package net.leafenzo.mint.entity.client;

import net.leafenzo.mint.Super;
import net.leafenzo.mint.entity.BeetleEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BeetleModel extends GeoModel<BeetleEntity> {
    @Override
    public Identifier getModelResource(BeetleEntity animatable) {
        return new Identifier(Super.MOD_ID, "geo/beetle.geo.json");
    }

    @Override
    public Identifier getTextureResource(BeetleEntity animatable) {
        return new Identifier(Super.MOD_ID, "textures/entity/beetle.png");
    }

    @Override
    public Identifier getAnimationResource(BeetleEntity animatable) {
        return new Identifier(Super.MOD_ID, "animations/beetle.animation.json");
    }

    @Override
    public void setCustomAnimations(BeetleEntity animatable, long instanceId, AnimationState<BeetleEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");
        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}