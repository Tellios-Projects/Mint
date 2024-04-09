package net.leafenzo.mint.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.leafenzo.mint.ModInit;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class EmberArrowEntityRenderer extends ProjectileEntityRenderer<SpectralArrowEntity> {
    public static final Identifier TEXTURE = new Identifier(ModInit.MOD_ID, "textures/entity/projectiles/ember_arrow.png");

    public EmberArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public Identifier getTexture(SpectralArrowEntity spectralArrowEntity) {
        return TEXTURE;
    }
}
