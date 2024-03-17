package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.registration.ModRegistryHelper;
import net.leafenzo.mint.util.Color;
import net.leafenzo.mint.util.ModDyeColor;
import net.leafenzo.mint.util.ModUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

import static net.leafenzo.mint.registration.ModRegistryHelper.ItemRegistry.*;

public class ModItems {
    //<editor-fold desc ="MINT - Special">
    public static final Item MINT_SPRIG = registerItem("mint_sprig", new MintSprigItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    //.hunger(0)
                    .saturationModifier(4.0f)
                    .build())));

    public static final Item MINT_COOKIE = registerItem("mint_cookie", new MintCookieItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(4)
                    .saturationModifier(0.8f) //double that of a vanilla game cookie
                    .build())));

    public static final Item MINT_TEA = registerItem("mint_tea", new MintTeaItem(new FabricItemSettings().maxCount(1)
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    //.hunger(0)
                    .saturationModifier(4.0f)
                    .build())));

    public static final Item WINTERGREEN_SAP = registerItem("wintergreen_sap", new Item(new FabricItemSettings()));
    //TODO smelting recipe from wintergreen leaves to get these
    //TODO mint dye recipe from this
    //TODO mint bricks recipe using this

    public static final Item WINTERGREEN_BERRIES = registerItem("wintergreen_berries", new MintSprigItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(1)
                    .saturationModifier(0.3f)
                    .build())));
    //TODO drop these from wintergreen leaves occasionally

    public static final Item WINTER_MEDLEY = registerItem("winter_medley", new WinterMedleyItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
//                    .hunger(0)
                    .saturationModifier(4.0f)
                    .build())));

    //TODO made from mint sprigs and wintergreen berries
    //TODO mint chill potion recipe using this instead of just a mint sprig

    //</editor-fold>
    //<editor-fold desc ="PEACH - Special">
    public static final Item PEACH_PIT = registerItem("peach_pit", new AliasedBlockItem(ModBlocks.PEACH_TREE, new FabricItemSettings()));
    public static final Item PEACH = registerItem("peach", new Item(new FabricItemSettings()
            .recipeRemainder(PEACH_PIT)
            .food(new FoodComponent.Builder()
                    .hunger(6) // apple + 2
                    .saturationModifier(0.3f) // apple
                    .build())));
    public static final Item PEACH_SLICE = registerItem("peach_slice", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(1)
                    .saturationModifier(0.3f) // apple
            .build())));
    public static final Item GOLDEN_PEACH = registerItem("golden_peach", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(6)
                    .saturationModifier(1.2f) // golden apple
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20, 5), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2400, 0), 1.0f)
                    // consider, a "fast falling" effect that damages creatures near where you land
            .build())));
    public static final Item PEACH_COBBLER = registerItem("peach_cobbler", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(8) // pumpkin pie
                    .saturationModifier(0.3f) // pumpkin pie
            .build())));
    public static final Item FRUIT_SALAD = registerItem("fruit_salad", new StewItem(new FabricItemSettings().maxCount(1) //StewItem just so it gives you a bowl when you finish eating
            .food(new FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(0.6f)
            .build())));
    public static final Item AMBROSIA = registerItem("ambrosia", new StewItem(new FabricItemSettings().maxCount(1) //StewItem just so it gives you a bowl when you finish eating
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(0)
                    .saturationModifier(1.2f)
                    // from golden peach
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20, 5), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 2400, 0), 1.0f)
                    // from golden apple
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0f)
                    // unique
                    .statusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 20, 5), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 2400, 0), 1.0f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 2400, 0), 1.0f)
            .build())));
    public static final Item COOKED_ANEMONE = registerItem("cooked_anemone", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(7)
                    .saturationModifier(0.6f)
                    .build())));
    public static final Item PEACH_BRANCH = registerItem("peach_branch", new Item(new FabricItemSettings()));
    //public static final Item PEACH_BRANCH = registerItem(new BlockItem(ModBlocks.PEACH_TREE, new FabricItemSettings()));
    //</editor-fold>

    //<editor-fold desc ="PERIWINKLE - Special">
    public static final Item FLOWERING_MELON = registerItem("flowering_melon", new FloweringMelonItem(new FabricItemSettings()));
    public static final Item SMOKED_LAVENDER = registerItem("smoked_lavender", new Item(new FabricItemSettings()));
    public static final Item LAVENDER_BREAD = registerItem("lavender_bread", new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(0.5f)
            .build())));
    public static final Item LAVENDER_SOAP = registerItem("lavender_soap", new LavenderSoapItem(new FabricItemSettings().maxDamage(16)));
    public static final Item LAVENDER_OIL = registerItem("lavender_oil", new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE)));
    //</editor-fold>

    //<editor-fold desc ="ARTICHOKE - Special">
    public static final Item WAXCAP_WAX = registerItem("waxcap_wax", new WaxcapWaxItem(ModBlocks.HANGING_WAXCAP_WAX, new FabricItemSettings()));
    public static final Item ARTICHOKE = registerItem("artichoke", new ArtichokeItem(ModBlocks.ARTICHOKE_CROP, new FabricItemSettings().food(new FoodComponent.Builder()
            .alwaysEdible()
            .hunger(3)
            .saturationModifier(0.6f)
            .build())));
    public static final Item ARTICHOKE_HEART = registerItem("artichoke_heart", new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(3)
            .saturationModifier(0.6f)
            .build())));
    public static final Item BREAKFAST_PORKCHOP = registerItem("breakfast_porkchop", new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(11)
            .saturationModifier(0.8f)
            .build())));
    public static final Item ARTICHOKE_LAMB = registerItem("artichoke_lamb", new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(11)
            .saturationModifier(0.8f)
            .build())));


    //<editor-fold desc ="Decor Additions">
//    static {
//        for (DyeColor color : ModUtil.concat(ModDyeColor.VALUES, ModUtil.VANILLA_DYE_COLORS)) {
//            if(color == DyeColor.WHITE) {
//                ModRegistryHelper.ItemRegistry.DYED_PAPER_ITEMS.add(registerItem("paper_covering", new Item(new FabricItemSettings())));
//            }
//            else {
//                ModRegistryHelper.ItemRegistry.DYED_PAPER_ITEMS.add(registerItem(color.getName() + "_paper_covering", new Item(new FabricItemSettings())));
//            }
//        }
//    }
    //</editor-fold>


    //</editor-fold>
    //<editor-fold desc ="Templates">
        public static final Item MINT_DYE = registerItem("mint_dye", createDyeItem(ModDyeColor.MINT));
        public static final Item MINT_BED = registerItem(new BedItem(ModBlocks.MINT_BED, new FabricItemSettings().maxCount(1)));
        public static final Item MINT_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.MINT_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item MINT_BANNER = registerItem(new BannerItem(ModBlocks.MINT_BANNER, ModBlocks.MINT_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item PEACH_DYE = registerItem("peach_dye", createDyeItem(ModDyeColor.PEACH));
        public static final Item PEACH_BED = registerItem(new BedItem(ModBlocks.PEACH_BED, new FabricItemSettings().maxCount(1)));
        public static final Item PEACH_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.PEACH_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item PEACH_BANNER = registerItem(new BannerItem(ModBlocks.PEACH_BANNER, ModBlocks.PEACH_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item PERIWINKLE_DYE = registerItem("periwinkle_dye", createDyeItem(ModDyeColor.PERIWINKLE));
        public static final Item PERIWINKLE_BED = registerItem(new BedItem(ModBlocks.PERIWINKLE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item PERIWINKLE_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.PERIWINKLE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item PERIWINKLE_BANNER = registerItem(new BannerItem(ModBlocks.PERIWINKLE_BANNER, ModBlocks.PERIWINKLE_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item ARTICHOKE_DYE = registerItem("artichoke_dye", createDyeItem(ModDyeColor.ARTICHOKE));
        public static final Item ARTICHOKE_BED = registerItem(new BedItem(ModBlocks.ARTICHOKE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item ARTICHOKE_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.ARTICHOKE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item ARTICHOKE_BANNER = registerItem(new BannerItem(ModBlocks.ARTICHOKE_BANNER, ModBlocks.ARTICHOKE_WALL_BANNER, new FabricItemSettings().maxCount(16)));

        public static final Item FUCHSIA_DYE = registerItem("fuchsia_dye", createDyeItem(ModDyeColor.FUCHSIA));
        public static final Item FUCHSIA_BED = registerItem(new BedItem(ModBlocks.FUCHSIA_BED, new FabricItemSettings().maxCount(1)));
        public static final Item FUCHSIA_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.FUCHSIA_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item FUCHSIA_BANNER = registerItem(new BannerItem(ModBlocks.FUCHSIA_BANNER, ModBlocks.FUCHSIA_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item VERMILION_DYE = registerItem("vermilion_dye", createDyeItem(ModDyeColor.VERMILION));
        public static final Item VERMILION_BED = registerItem(new BedItem(ModBlocks.VERMILION_BED, new FabricItemSettings().maxCount(1)));
        public static final Item VERMILION_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.VERMILION_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item VERMILION_BANNER = registerItem(new BannerItem(ModBlocks.VERMILION_BANNER, ModBlocks.VERMILION_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item SHAMROCK_DYE = registerItem("shamrock_dye", createDyeItem(ModDyeColor.SHAMROCK));
        public static final Item SHAMROCK_BED = registerItem(new BedItem(ModBlocks.SHAMROCK_BED, new FabricItemSettings().maxCount(1)));
        public static final Item SHAMROCK_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.SHAMROCK_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item SHAMROCK_BANNER = registerItem(new BannerItem(ModBlocks.SHAMROCK_BANNER, ModBlocks.SHAMROCK_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item INDIGO_DYE = registerItem("indigo_dye", createDyeItem(ModDyeColor.INDIGO));
        public static final Item INDIGO_BED = registerItem(new BedItem(ModBlocks.INDIGO_BED, new FabricItemSettings().maxCount(1)));
        public static final Item INDIGO_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.INDIGO_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item INDIGO_BANNER = registerItem(new BannerItem(ModBlocks.INDIGO_BANNER, ModBlocks.INDIGO_WALL_BANNER, new FabricItemSettings().maxCount(16)));

        public static final Item BANANA_DYE = registerItem("banana_dye", createDyeItem(ModDyeColor.BANANA));
        public static final Item BANANA_BED = registerItem(new BedItem(ModBlocks.BANANA_BED, new FabricItemSettings().maxCount(1)));
        public static final Item BANANA_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.BANANA_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item BANANA_BANNER = registerItem(new BannerItem(ModBlocks.BANANA_BANNER, ModBlocks.BANANA_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item CERULEAN_DYE = registerItem("cerulean_dye", createDyeItem(ModDyeColor.CERULEAN));
        public static final Item CERULEAN_BED = registerItem(new BedItem(ModBlocks.CERULEAN_BED, new FabricItemSettings().maxCount(1)));
        public static final Item CERULEAN_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.CERULEAN_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item CERULEAN_BANNER = registerItem(new BannerItem(ModBlocks.CERULEAN_BANNER, ModBlocks.CERULEAN_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item ACORN_DYE = registerItem("acorn_dye", createDyeItem(ModDyeColor.ACORN));
        public static final Item ACORN_BED = registerItem(new BedItem(ModBlocks.ACORN_BED, new FabricItemSettings().maxCount(1)));
        public static final Item ACORN_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.ACORN_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item ACORN_BANNER = registerItem(new BannerItem(ModBlocks.ACORN_BANNER, ModBlocks.ACORN_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item MAUVE_DYE = registerItem("mauve_dye", createDyeItem(ModDyeColor.MAUVE));
        public static final Item MAUVE_BED = registerItem(new BedItem(ModBlocks.MAUVE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item MAUVE_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.MAUVE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item MAUVE_BANNER = registerItem(new BannerItem(ModBlocks.MAUVE_BANNER, ModBlocks.MAUVE_WALL_BANNER, new FabricItemSettings().maxCount(16)));

        public static final Item MAROON_DYE = registerItem("maroon_dye", createDyeItem(ModDyeColor.MAROON));
        public static final Item MAROON_BED = registerItem(new BedItem(ModBlocks.MAROON_BED, new FabricItemSettings().maxCount(1)));
        public static final Item MAROON_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.MAROON_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item MAROON_BANNER = registerItem(new BannerItem(ModBlocks.MAROON_BANNER, ModBlocks.MAROON_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item GRAPE_DYE = registerItem("grape_dye", createDyeItem(ModDyeColor.GRAPE));
        public static final Item GRAPE_BED = registerItem(new BedItem(ModBlocks.GRAPE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item GRAPE_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.GRAPE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item GRAPE_BANNER = registerItem(new BannerItem(ModBlocks.GRAPE_BANNER, ModBlocks.GRAPE_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item NAVY_DYE = registerItem("navy_dye", createDyeItem(ModDyeColor.NAVY));
        public static final Item NAVY_BED = registerItem(new BedItem(ModBlocks.NAVY_BED, new FabricItemSettings().maxCount(1)));
        public static final Item NAVY_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.NAVY_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item NAVY_BANNER = registerItem(new BannerItem(ModBlocks.NAVY_BANNER, ModBlocks.NAVY_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item SAP_DYE = registerItem("sap_dye", createDyeItem(ModDyeColor.SAP));
        public static final Item SAP_BED = registerItem(new BedItem(ModBlocks.SAP_BED, new FabricItemSettings().maxCount(1)));
        public static final Item SAP_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.SAP_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item SAP_BANNER = registerItem(new BannerItem(ModBlocks.SAP_BANNER, ModBlocks.SAP_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        
        public static final Item AMBER_DYE = registerItem("amber_dye", createDyeItem(ModDyeColor.AMBER));
        public static final Item AMBER_BED = registerItem(new BedItem(ModBlocks.AMBER_BED, new FabricItemSettings().maxCount(1)));
        public static final Item AMBER_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.AMBER_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item AMBER_BANNER = registerItem(new BannerItem(ModBlocks.AMBER_BANNER, ModBlocks.AMBER_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item SAGE_DYE = registerItem("sage_dye", createDyeItem(ModDyeColor.SAGE));
        public static final Item SAGE_BED = registerItem(new BedItem(ModBlocks.SAGE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item SAGE_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.SAGE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item SAGE_BANNER = registerItem(new BannerItem(ModBlocks.SAGE_BANNER, ModBlocks.SAGE_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item VELVET_DYE = registerItem("velvet_dye", createDyeItem(ModDyeColor.VELVET));
        public static final Item VELVET_BED = registerItem(new BedItem(ModBlocks.VELVET_BED, new FabricItemSettings().maxCount(1)));
        public static final Item VELVET_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.VELVET_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item VELVET_BANNER = registerItem(new BannerItem(ModBlocks.VELVET_BANNER, ModBlocks.VELVET_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item MOLD_DYE = registerItem("mold_dye", createDyeItem(ModDyeColor.MOLD));
        public static final Item MOLD_BED = registerItem(new BedItem(ModBlocks.MOLD_BED, new FabricItemSettings().maxCount(1)));
        public static final Item MOLD_SHULKER_BOX = registerItem(new BlockItem(ModBlocks.MOLD_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item MOLD_BANNER = registerItem(new BannerItem(ModBlocks.MOLD_BANNER, ModBlocks.MOLD_WALL_BANNER, new FabricItemSettings().maxCount(16)));
    //</editor-fold>

    //<editor-fold desc ="Item Creation Functions">
    private static DyeItem createDyeItem(DyeColor color) {
        DyeItem item = new DyeItem(color, new FabricItemSettings());
        DYE_ITEMS.add(item);
        DYE_ITEM_FROM_COLOR.put(color, item);
        return item;
    }
    //</editor-fold>
    public static void registerModItems() {
        ModInit.LOGGER.info("Registering Mod Items for " + Super.MOD_ID);
    }
}
