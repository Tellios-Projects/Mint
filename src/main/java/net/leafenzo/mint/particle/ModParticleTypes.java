package net.leafenzo.mint.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

//@Environment(value= EnvType.CLIENT)
public class ModParticleTypes {
    public static final DefaultParticleType SOAP_BUBBLE = registerParticle("soap_bubble");
    public static final DefaultParticleType SOAP_SPLASH = registerParticle("soap_splash");
    public static final DefaultParticleType DRIPPING_WAXCAP_WAX = registerParticle("dripping_waxcap_wax");
    public static final DefaultParticleType FALLING_WAXCAP_WAX = registerParticle("falling_waxcap_wax");
    public static final DefaultParticleType LANDING_WAXCAP_WAX = registerParticle("landing_waxcap_wax");

    private static DefaultParticleType registerParticle(String name) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(Super.MOD_ID, name), FabricParticleTypes.simple());
    }

    public static void registerFactoriesForClient() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();

        registry.register(SOAP_BUBBLE, SoapBubbleParticle.Factory::new);
        registry.register(SOAP_SPLASH, SoapSplashParticle.Factory::new);
        registry.register(ModParticleTypes.DRIPPING_WAXCAP_WAX, WaxcapWaxParticle.Factory.Dripping::new);
        registry.register(ModParticleTypes.FALLING_WAXCAP_WAX, WaxcapWaxParticle.Factory.Falling::new);
        registry.register(ModParticleTypes.DRIPPING_WAXCAP_WAX, WaxcapWaxParticle.Factory.Landing::new);
    }

    public static void registerModParticleTypes() {
        ModInit.LOGGER.debug("Registering particle types for " + Super.MOD_ID);
    }
}
