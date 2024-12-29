package net.leafenzo.mint.particle;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class ElsDyeModParticleFactoryRegistries {
    public static void registerFactoriesForClient() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();

        registry.register(ElsDyeModParticleTypes.SOAP_BUBBLE, SoapBubbleParticle.Factory::new);
        registry.register(ElsDyeModParticleTypes.SOAP_SPLASH, SoapSplashParticle.Factory::new);
        registry.register(ElsDyeModParticleTypes.DRIPPING_WAXCAP_WAX, WaxcapWaxParticle.Factory.Dripping::new);
        registry.register(ElsDyeModParticleTypes.FALLING_WAXCAP_WAX, WaxcapWaxParticle.Factory.Falling::new);
        registry.register(ElsDyeModParticleTypes.DRIPPING_WAXCAP_WAX, WaxcapWaxParticle.Factory.Landing::new);
    }
}
