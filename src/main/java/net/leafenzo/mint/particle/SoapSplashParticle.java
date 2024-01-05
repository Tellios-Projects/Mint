package net.leafenzo.mint.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;

@Environment(value = EnvType.CLIENT)
public class SoapSplashParticle extends SpriteBillboardParticle {
    protected SoapSplashParticle(ClientWorld clientWorld, double xCoord, double yCoord, double zCoord, double xd, double yd, double zd) {
        super(clientWorld, xCoord, yCoord, zCoord, xd, yd, zd);
    }

//    private final float maxScale = 1.0f;

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.maxAge-- <= 0) {
            this.markDead();
            return;
        }

        this.angle += 1f;
//        if(this.scale < maxScale) {
//            this.scale *= 1.2f;
//        }

//        this.velocityY -= 0.006;
        this.velocityY -= 0.02;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        this.velocityX *= (double)0.5f;
        this.velocityY *= (double)0.5f;
        this.velocityZ *= (double)0.5f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z, double dx, double dy, double dz) {
            SoapSplashParticle particle = new SoapSplashParticle(level, x, y, z, dx, dy, dz);
            particle.setSprite(spriteProvider);
            particle.scale = 0.33f;
            particle.maxAge = 20 + level.random.nextBetween(-4, 4);
            particle.collidesWithWorld = true;
            return particle;
        }
    }
}
