package net.leafenzo.mint.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<BeetleEntity> BEETLE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(Super.MOD_ID, "beetle"),
            FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, BeetleEntity::new)
                    .dimensions(EntityDimensions.fixed(1, 1))
                    .build()
    );


    public static void registerModEntities() {
        ModInit.LOGGER.debug("Registering entities for " + Super.MOD_ID);

        FabricDefaultAttributeRegistry.register(BEETLE, BeetleEntity.setAttributes());
    }
}
