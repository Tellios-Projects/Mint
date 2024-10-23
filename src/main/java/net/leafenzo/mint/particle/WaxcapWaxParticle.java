package net.leafenzo.mint.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

/**
 * Oh the things I do to avoid using an accessor.
 */
public class WaxcapWaxParticle extends SpriteBillboardParticle {
    WaxcapWaxParticle(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
        this.setBoundingBoxSpacing(0.01f, 0.01f);
        this.gravityStrength = 0.06f;
    }
    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }
    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        this.updateAge();
        if (this.dead) {
            return;
        }
        this.velocityY -= this.gravityStrength;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        this.updateVelocity();
        if (this.dead) {
            return;
        }
        this.velocityX *= 0.98f;
        this.velocityY *= 0.98f;
        this.velocityZ *= 0.98f;
    }
    protected void updateAge() {
        if (this.maxAge-- <= 0) {
            this.markDead();
            this.world.addParticle(ModParticleTypes.FALLING_WAXCAP_WAX, this.x, this.y, this.z, this.velocityX, this.velocityY, this.velocityZ);
        }
    }
    protected void updateVelocity() { }

    // Something to do with this doesn't seem to work for some reason. //TODO fix first stage of dripping waxcap wax particle
    public static SpriteBillboardParticle createDrippingWaxcapWax(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        WaxcapWaxParticle.Dripping blockLeakParticle = new Dripping(world, x, y, z, ModParticleTypes.FALLING_WAXCAP_WAX);
        blockLeakParticle.setColor(0.8f, 0.8627450980392157f, 0.2313725490196078f); //TODO fix colors
        return blockLeakParticle;
    }
    public static SpriteBillboardParticle createFallingWaxcapWax(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        WaxcapWaxParticle.HangingWaxcapWaxDrip blockLeakParticle = new HangingWaxcapWaxDrip(world, x, y, z, ModParticleTypes.LANDING_WAXCAP_WAX);
        blockLeakParticle.setColor(0.8f, 0.8627450980392157f, 0.2313725490196078f); //TODO fix colors
        return blockLeakParticle;
    }
    public static SpriteBillboardParticle createLandingWaxcapWax(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        WaxcapWaxParticle.Landing blockLeakParticle = new WaxcapWaxParticle.Landing(world, x, y, z);
        blockLeakParticle.setColor(0.8f, 0.8627450980392157f, 0.2313725490196078f); //TODO fix colors
        return blockLeakParticle;
    }

    @Environment(value=EnvType.CLIENT)
    static class HangingWaxcapWaxDrip
            extends WaxcapWaxParticle.ContinuousFalling {
        HangingWaxcapWaxDrip(ClientWorld clientWorld, double d, double e, double f, ParticleEffect particleEffect) {
            super(clientWorld, d, e, f, particleEffect);
        }

        @Override
        protected void updateVelocity() {
            if (this.onGround) {
                this.markDead();
                this.world.addParticle(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }
    @Environment(value=EnvType.CLIENT)
    static class Dripping
            extends WaxcapWaxParticle {
        private final ParticleEffect nextParticle;

        Dripping(ClientWorld world, double x, double y, double z, ParticleEffect nextParticle) {
            super(world, x, y, z);
            this.nextParticle = nextParticle;
            this.gravityStrength *= 0.02f;
            this.maxAge = 40;
        }

        @Override
        protected void updateAge() {
//            this.red = 1.0f;
//            this.green = 16.0f / (float)(40 - this.maxAge + 16);
//            this.blue = 4.0f / (float)(40 - this.maxAge + 8);

            if (this.maxAge-- <= 0) {
                this.markDead();
                this.world.addParticle(this.nextParticle, this.x, this.y, this.z, this.velocityX, this.velocityY, this.velocityZ);
            }
        }

        @Override
        protected void updateVelocity() {
            this.velocityX *= 0.02;
            this.velocityY *= 0.02;
            this.velocityZ *= 0.02;
        }
    }
    @Environment(value=EnvType.CLIENT)
    static class ContinuousFalling
            extends WaxcapWaxParticle.Falling {
        protected final ParticleEffect nextParticle;

        ContinuousFalling(ClientWorld world, double x, double y, double z, ParticleEffect nextParticle) {
            super(world, x, y, z);
            this.nextParticle = nextParticle;
        }

        @Override
        protected void updateVelocity() {
            if (this.onGround) {
                this.markDead();
                this.world.addParticle(this.nextParticle, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            }
        }
    }

    @Environment(value=EnvType.CLIENT)
    static class Falling
            extends WaxcapWaxParticle {
        Falling(ClientWorld clientWorld, double d, double e, double f) {
            this(clientWorld, d, e, f, (int)(64.0 / (Math.random() * 0.8 + 0.2)));
        }

        Falling(ClientWorld world, double x, double y, double z, int maxAge) {
            super(world, x, y, z);
            this.maxAge = maxAge;
        }

        @Override
        protected void updateVelocity() {
            if (this.onGround) {
                this.markDead();
            }
        }
    }
    @Environment(value=EnvType.CLIENT)
    static class Landing
            extends WaxcapWaxParticle {
        Landing(ClientWorld clientWorld, double d, double e, double f) {
            super(clientWorld, d, e, f);
            this.maxAge = (int)(16.0 / (Math.random() * 0.8 + 0.2));
        }

        @Override
        protected void updateAge() {
//            this.red = 0.8f / (float)(1 + ((10 - this.maxAge) / 10));  // Doesn't work TODO make these color changes look better
//            this.green = 0.8627450980392157f / (float)(1 + ((10 - this.maxAge) / 10));
//            this.blue = 0.2313725490196078f / (float)(1 + ((5 - this.maxAge) / 5));
            super.updateAge();
        }
    }

    @Environment(EnvType.CLIENT)
    public static abstract class Factory implements ParticleFactory<DefaultParticleType> {
        @Environment(EnvType.CLIENT)
        public static class Dripping extends Factory implements ParticleFactory<DefaultParticleType> {
            private final SpriteProvider spriteProvider;
            public Dripping(SpriteProvider spriteProvider) {
                this.spriteProvider = spriteProvider;
            }
            @Override
            public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z, double dx, double dy, double dz) {
                SpriteBillboardParticle particle = createDrippingWaxcapWax(particleType, level, x, y, z, dx, dy, dz);
                particle.setSprite(spriteProvider);
                return particle;
            }
        }
        @Environment(EnvType.CLIENT)
        public static class Falling extends Factory implements ParticleFactory<DefaultParticleType> {
            private final SpriteProvider spriteProvider;
            public Falling(SpriteProvider spriteProvider) {
                this.spriteProvider = spriteProvider;
            }
            @Override
            public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z, double dx, double dy, double dz) {
                SpriteBillboardParticle particle = createFallingWaxcapWax(particleType, level, x, y, z, dx, dy, dz);
                particle.setSprite(spriteProvider);
                return particle;
            }
        }
        @Environment(EnvType.CLIENT)
        public static class Landing extends Factory implements ParticleFactory<DefaultParticleType> {
            private final SpriteProvider spriteProvider;
            public Landing(SpriteProvider spriteProvider) {
                this.spriteProvider = spriteProvider;
            }
            @Override
            public Particle createParticle(DefaultParticleType particleType, ClientWorld level, double x, double y, double z, double dx, double dy, double dz) {
                SpriteBillboardParticle particle = createLandingWaxcapWax(particleType, level, x, y, z, dx, dy, dz);
                particle.setSprite(spriteProvider);
                return particle;
            }
        }
    }
}
