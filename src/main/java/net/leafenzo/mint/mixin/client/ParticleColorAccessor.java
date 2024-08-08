package net.leafenzo.mint.mixin.client;

import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Particle.class)
public interface ParticleColorAccessor {
    @Accessor("red") void setRed(float red);
    @Accessor("green") void setGreen(float green);
    @Accessor("blue") void setBlue(float blue);
}