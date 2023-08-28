//
// Sources:
// Wisp Forest - https://github.com/wisp-forest/gelatin/blob/bdcbd97addb00be29580e36cd5f827322809b0a3/dye-entries/src/main/java/io/wispforest/gelatin/dye_entries/mixins/PointOfInterestTypesMixin.java#L19
//

package net.leafenzo.mint.mixin;

import net.leafenzo.mint.block.ModBlocks;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BedPart;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.poi.PointOfInterestType;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Optional;

//@Debug(export = true)
@Mixin(PointOfInterestTypes.class)
public class PointOfInterestTypesMixin {

//    @Shadow @Final @Mutable
//    private static Set<BlockState> BED_HEADS;
//
//    @Inject(method = "<clinit>", at = @At("TAIL"))
//    private static void gelatin$addCustomColoredBeds(CallbackInfo ci){
//        BED_HEADS = Registry.BLOCK.stream()
//            .filter(block -> block instanceof BedBlock)
//            .flatMap(block -> block.getStateManager().getStates().stream())
//            .filter(blockState -> blockState.get(BedBlock.PART) == BedPart.HEAD)
//            .collect(Collectors.toSet());
//    }

    @Inject(method = "getTypeForState", at = @At(value = "RETURN"), cancellable = true)
    private static void checkForCustomBedStates(BlockState state, CallbackInfoReturnable<Optional<RegistryEntry<PointOfInterestType>>> cir){
        if(state.getBlock() instanceof BedBlock && Arrays.stream(ModBlocks.toBlockArray(ModBlocks.BED_BLOCKS)).anyMatch(x -> x.equals(state.getBlock())) && state.get(BedBlock.PART) == BedPart.HEAD){
            cir.setReturnValue(
                    Optional.ofNullable(Registries.POINT_OF_INTEREST_TYPE.getEntry(PointOfInterestTypes.HOME).orElse(null))
            );
        }
    }

}
