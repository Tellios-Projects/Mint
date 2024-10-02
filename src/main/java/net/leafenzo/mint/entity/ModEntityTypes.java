/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/src/main/java/net/hibiscus/naturespirit/registration/HibiscusEntityTypes.java
 */

package net.leafenzo.mint.entity;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.registry.sync.FabricRegistryInit;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.client.render.entity.ModBoatEntityRenderer;
//import net.leafenzo.mint.entity.renderer.BeetleRenderer;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.SpectralArrowEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.ChestBoatEntityModel;

import java.util.function.Supplier;

public class ModEntityTypes {
    public static <T extends EntityType <?>> T registerEntityType(String id, T type) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(Super.MOD_ID, id), type);
    }

    public static EntityType <BoatEntity> createBoatType(boolean chest, ModBoatEntity.ModBoat boat) {
        return FabricEntityTypeBuilder.create(SpawnGroup.MISC, boat.factory(chest)).dimensions(EntityDimensions.changing(1.375f, 0.5625f)).trackRangeChunks(10).build();
    }
    public static final EntityType<? extends EmberArrowEntity> EMBER_ARROW = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(ModInit.MOD_ID, "ember_arrow"),
            FabricEntityTypeBuilder.<EmberArrowEntity>create(SpawnGroup.MISC, EmberArrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

//    public static final EntityType<GasBombProjectileEntity> GAS_BOMB_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
//            new Identifier(Super.MOD_ID, "gas_bomb"),
//            FabricEntityTypeBuilder.<GasBombProjectileEntity>create(SpawnGroup.MISC, GasBombProjectileEntity::new)
//                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).trackRangeBlocks(4).trackedUpdateRate(10).build());
//
//    public static final EntityType<CinnabarCloudEntity> CINNABAR_CLOUD = Registry.register(Registries.ENTITY_TYPE,
//            new Identifier(Super.MOD_ID, "cinnabar_cloud"),
//            FabricEntityTypeBuilder.<CinnabarCloudEntity>create(SpawnGroup.MISC, CinnabarCloudEntity::new)
//                    .dimensions(EntityDimensions.fixed(6.0F, 0.5F)).trackRangeBlocks(10)
//                    .trackedUpdateRate(Integer.MAX_VALUE).fireImmune().build());

//    public static final EntityType<BeetleEntity> BEETLE = Registry.register(Registries.ENTITY_TYPE,
//            new Identifier(Super.MOD_ID, "beetle"),
//            FabricEntityTypeBuilder.<BeetleEntity>create(SpawnGroup.MISC, BeetleEntity::new)
//                    .dimensions(EntityDimensions.fixed(1.0F, 0.25F))
//                    .build());

    public static void registerEntityTypes() {
        ModInit.LOGGER.debug("Registering mod entity types for " + Super.MOD_ID);
    }
}