package net.leafenzo.mint.util;

import com.mojang.serialization.Codec;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.world.feature.HugeWaxcapMushroomFoliagePlacer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class ModWorldGen {
    public static final FoliagePlacerType<HugeWaxcapMushroomFoliagePlacer> HUGE_WAXCAP_MUSHROOM_FOLIAGE_PLACER_TYPE = registerFoliagePlacer("huge_waxcap_mushroom_foliage_placer", HugeWaxcapMushroomFoliagePlacer.CODEC);

    private static <P extends FoliagePlacer> FoliagePlacerType<P> registerFoliagePlacer(String id, Codec<P> codec) {
        return (FoliagePlacerType) Registry.register(Registries.FOLIAGE_PLACER_TYPE, new Identifier(Super.MOD_ID, id), new FoliagePlacerType(codec));
    }
    private static <P extends TrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String id, Codec<P> codec) {
        return (TrunkPlacerType)Registry.register(Registries.TRUNK_PLACER_TYPE,  new Identifier (Super.MOD_ID, id), new TrunkPlacerType(codec));
    }
    private static <P extends TreeDecorator> TreeDecoratorType<P> registerTreeDecorator(String id, Codec<P> codec) {
        return (TreeDecoratorType)Registry.register(Registries.TREE_DECORATOR_TYPE,  new Identifier (Super.MOD_ID, id), new TreeDecoratorType(codec));
    }

}
