package net.leafenzo.mint.particle;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModParticleTypes {
    public static final DefaultParticleType SOAP_BUBBLE = registerParticle("soap_bubble");
    public static final DefaultParticleType SOAP_SPLASH = registerParticle("soap_splash");

    private static DefaultParticleType registerParticle(String name) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Super.MOD_ID, name), FabricParticleTypes.simple());
    }

    public static void registerFactoriesForClient() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();

        registry.register(SOAP_BUBBLE, SoapBubbleParticle.Factory::new);
        registry.register(SOAP_SPLASH, SoapSplashParticle.Factory::new);
    }

    public static void registerModParticleTypes() {
        ModInit.LOGGER.debug("Registering particle types for " + Super.MOD_ID);
    }
}
