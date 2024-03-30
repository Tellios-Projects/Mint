package net.leafenzo.mint.mixin.ducks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.class)
public class FabricDataGeneratorMixin implements FabricDataGeneratorAccess {
//    @Accessor("fabricOutput") // This accessor didn't work because of how the class is final... I think?
//    FabricDataOutput getFabricOutput();

    @Final
    @Shadow
    private FabricDataOutput fabricOutput;

    @Override
    public net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack Mint$CreatePack(String forModId) {
        return new net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator.Pack(true, forModId, fabricOutput));
    }
}
