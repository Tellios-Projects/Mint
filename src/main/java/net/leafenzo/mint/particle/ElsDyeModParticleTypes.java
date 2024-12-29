package net.leafenzo.mint.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ElsDyeModParticleTypes {
    public static final DefaultParticleType SOAP_BUBBLE = registerParticle("soap_bubble");
    public static final DefaultParticleType SOAP_SPLASH = registerParticle("soap_splash");
    public static final DefaultParticleType DRIPPING_WAXCAP_WAX = registerParticle("dripping_waxcap_wax");
    public static final DefaultParticleType FALLING_WAXCAP_WAX = registerParticle("falling_waxcap_wax");
    public static final DefaultParticleType LANDING_WAXCAP_WAX = registerParticle("landing_waxcap_wax");

    private static DefaultParticleType registerParticle(String name) {
        return Registry.register(Registries.PARTICLE_TYPE, new Identifier(ElsDyeMod.MOD_ID, name), FabricParticleTypes.simple());
    }

    public static void registerModParticleTypes() {
        ElsDyeModInit.LOGGER.debug("Registering particle types for " + ElsDyeMod.MOD_ID);
    }
}
