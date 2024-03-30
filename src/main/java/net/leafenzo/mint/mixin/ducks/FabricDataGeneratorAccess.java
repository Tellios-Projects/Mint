package net.leafenzo.mint.mixin.ducks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

public interface FabricDataGeneratorAccess {
    public net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack Mint$CreatePack(String forModId);
}
