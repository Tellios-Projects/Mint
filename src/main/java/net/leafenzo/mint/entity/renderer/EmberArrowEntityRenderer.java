package net.leafenzo.mint.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.entity.EmberArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EmberArrowEntityRenderer extends ProjectileEntityRenderer<EmberArrowEntity> {
    public static final Identifier TEXTURE = new Identifier(Super.MOD_ID, "textures/entity/projectiles/ember_arrow.png");

    public EmberArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(EmberArrowEntity spectralArrowEntity) {
        return TEXTURE;
    }
}
