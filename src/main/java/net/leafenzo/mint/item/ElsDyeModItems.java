package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.item.custom.*;
import net.leafenzo.mint.registry.tag.ElsDyeModTags;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.DyeColor;

import static net.leafenzo.mint.registration.ElsDyeModRegistryHelper.ItemRegistry.*;

public class ElsDyeModItems {
    //<editor-fold desc ="Custom Items - MINT">
    public static final Item MINT_SPRIG = registerItem("mint_sprig", new MintSprigItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
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
                    .saturationModifier(4.0f)
                    .build())));
    public static final Item WINTERGREEN_SAP = registerItem("wintergreen_sap", new Item(new FabricItemSettings()));
    public static final Item WINTERGREEN_BERRIES = registerItem("wintergreen_berries", new MintSprigItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .hunger(1)
                    .saturationModifier(0.3f)
                    .build())));
    public static final Item WINTER_MEDLEY = registerItem("winter_medley", new WinterMedleyItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .alwaysEdible()
                    .saturationModifier(4.0f)
                    .build())));
    public static final Item WINTERGREEN_CANDY_CANE = registerItem("wintergreen_candy_cane", new CandyCaneItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(2)
                    .saturationModifier(0.0f)
                    .build())));
    public static final Item PEPPERMINT_CANDY_CANE = registerItem("peppermint_candy_cane", new CandyCaneItem(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(2)
                    .saturationModifier(0.0f)
                    .build())));
    //</editor-fold>
    //<editor-fold desc ="Custom Items - PEACH">
    public static final Item PEACH_PIT = registerItem("peach_pit", new Item(new FabricItemSettings()));
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
                    // TODO consider a "fast falling" effect that damages creatures near where you land
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
                    .hunger(4)
                    .saturationModifier(0.4f)
                    .build())));
    public static final Item PEACH_BRANCH = registerItem("peach_branch", new Item(new FabricItemSettings())); //Replaces: //@Obsolete public static final Item PEACH_BRANCH = registerItem(new BlockItem(ModBlocks.PEACH_TREE, new FabricItemSettings()));
    //</editor-fold>
    //<editor-fold desc ="Custom Items - PERIWINKLE">
    public static final Item FLOWERING_MELON = registerItem("flowering_melon", new FloweringMelonItem(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(1)
            .saturationModifier(1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 600, 1), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300, 0), 1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.WITHER, 300, 1), 1.0f)
            .build())
    ));
    public static final Item SMOKED_LAVENDER = registerItem("smoked_lavender", new Item(new FabricItemSettings()));
    public static final Item LAVENDER_BREAD = registerItem("lavender_bread", new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(6)
            .saturationModifier(0.5f)
            .build())));
    public static final Item LAVENDER_SOAP = registerItem("lavender_soap", new LavenderSoapItem(new FabricItemSettings().maxDamage(16)));
    public static final Item LAVENDER_OIL = registerItem("lavender_oil", new Item(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE)));
    //</editor-fold>
    //<editor-fold desc ="Custom Items - ARTICHOKE">
    public static final Item WAXCAP_WAX = registerItem("waxcap_wax", new WaxcapWaxItem(ElsDyeModBlocks.HANGING_WAXCAP_WAX, new FabricItemSettings()));
    public static final Item ARTICHOKE = registerItem("artichoke", new ArtichokeItem(ElsDyeModBlocks.ARTICHOKE_CROP, new FabricItemSettings().food(new FoodComponent.Builder()
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
            .meat()
            .build())));
    public static final Item ARTICHOKE_LAMB = registerItem("artichoke_lamb", new Item(new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(11)
            .saturationModifier(0.8f)
            .build())));
    //</editor-fold>
    //<editor-fold desc ="Custom Items - FUCHSIA">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - VERMILION">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - SHAMROCK">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - INDIGO">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - BANANA">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - CERULEAN">
    public static final Item COCHINEAL_BEETLE = registerItem("cochineal_beetle", new AliasedBlockItem(ElsDyeModBlocks.COCHINEAL_BEETLES, new FabricItemSettings()));
    public static final Item CARMINIC_COCHINEAL_BEETLE = registerItem("carminic_cochineal_beetle", new Item(new FabricItemSettings()));
    public static final Item CACTUS_CHUNK = registerItem("cactus_chunk", new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.1f).build())));
    public static final Item MADDER_ROOT = registerItem("madder_root", new Item(new FabricItemSettings()));
    public static final Item CINNABAR = registerItem("cinnabar", new Item(new FabricItemSettings()));
    public static final Item POWDERED_CINNABAR = registerItem("powdered_cinnabar", new Item(new FabricItemSettings()));
//    public static final Item GAS_BOMB = registerItem("gas_bomb", new GasBombItem(new FabricItemSettings()));
    //</editor-fold>
    //<editor-fold desc ="Custom Items - ACORN">
//    public static final Item WEEVIL_SPAWN_EGG = registerItem("weevil_spawn_egg",
//            new SpawnEggItem(ModEntityTypes.WEEVIL.get(), 803406, 0x39d976,
//                    new FabricItemSettings()));
    //</editor-fold>
    //<editor-fold desc ="Custom Items - MAUVE">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - MAROON">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - GRAPE">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - NAVY">
//    public static final Item BEETLE_SPAWN_EGG = registerSpawnEgg("beetle_spawn_egg", ModEntityTypes.BEETLE, 803406, 0x39d976, new FabricItemSettings());
    //</editor-fold>
    //<editor-fold desc ="Custom Items - SAP">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - AMBER">
    public static final Item AMBER = registerItem("amber", new Item(new FabricItemSettings()));
    public static final Item EMBER = registerItem("ember", new AliasedBlockItem(ElsDyeModBlocks.EMBER, new FabricItemSettings()));
    public static final Item EMBER_ARROW = registerItem("ember_arrow", new EmberArrowItem(new FabricItemSettings()));
    public static final Item SAVANNABUD_SEEDS = registerItem("savannabud_seeds", new AliasedBlockItem(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS_CROP, new FabricItemSettings()));
    public static final Item PINEAPPLE_CROWN = registerItem("pineapple_crown", new PineappleCrownItem(ElsDyeModBlocks.PINEAPPLE_CROWN, new FabricItemSettings()));
    public static final Item PINEAPPLE = registerItem("pineapple", new AliasedBlockItem(ElsDyeModBlocks.PINEAPPLE, new FabricItemSettings().recipeRemainder(PINEAPPLE_CROWN)));
    public static final Item PINEAPPLE_SLICES = registerItem("pineapple_slices", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(3)
                    .saturationModifier(0.2f)
                    .build())));
    public static final Item PINEAPPLE_KEBAB = registerItem("pineapple_kebab", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(9)
                    .saturationModifier(0.9f)
                    .build())));
    public static final Item PINEAPPLE_TART = registerItem("pineapple_tart", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.5f)
                    .build())));
    //</editor-fold>
    //<editor-fold desc ="Custom Items - SAGE">
    //</editor-fold>
    //<editor-fold desc ="Custom Items - VELVET">
    public static final Item STRAWBERRY = registerItem("strawberry", new AliasedBlockItem(ElsDyeModBlocks.STRAWBERRY_PLANT, new FabricItemSettings().food(new FoodComponent.Builder()
            .hunger(2)
            .saturationModifier(0.2f)
            .snack()
            .build())));
    public static final Item STRAWBERRY_SHORTCAKE = registerItem("strawberry_shortcake", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.8F)
                    .build())));
    public static final Item CHOCOLATE_STRAWBERRY = registerItem("chocolate_strawberry", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(4)
                    .saturationModifier(0.4F)
                    .snack()
                    .build())));
    public static final Item GOLDEN_STRAWBERRY = registerItem("golden_strawberry", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(6)
                    .saturationModifier(1f)
                    .snack()
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0), 1f)
                    .build())));
    public static final Item STRAWBERRY_MILK = registerItem("strawberry_milk", new StrawberryMilkItem(new FabricItemSettings().recipeRemainder(Items.GLASS_BOTTLE).maxCount(16)
            .food(new FoodComponent.Builder()
                    .hunger(5)
                    .saturationModifier(0.5F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0), 1f)
                    .build())));

    public static final Item STRAWBERRY_CHEESECAKE = registerItem("strawberry_cheesecake", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 600, 0), 1f)
                    .build())));
    public static final Item ANGEL_FOOD_CAKE = registerItem("angel_food_cake", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(10)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0), 1f)
                    .build())));

    public static final Item CHERRIES = registerItem("cherries", new Item(new FabricItemSettings().food(new FoodComponent.Builder().hunger(3).saturationModifier(0.2f).snack().build())));
    public static final Item CHERRY_PIE = registerItem("cherry_pie", new Item(new FabricItemSettings()
            .food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(0.4F)
                    .build())));

    public static final Item POKEBERRIES = registerItem("pokeberries", new AliasedBlockItem(ElsDyeModBlocks.POKEWEED, new FabricItemSettings().food(new FoodComponent.Builder().hunger(3).saturationModifier(0.2f).statusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 0), 1f).snack().build())));
    //</editor-fold>
    //<editor-fold desc ="Custom Items - MOLD">
    //</editor-fold>

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
        public static final Item MINT_DYE = registerItem("mint_dye", createDyeItem(ElsDyeModDyeColor.MINT));
        public static final Item MINT_BED = registerItem(new BedItem(ElsDyeModBlocks.MINT_BED, new FabricItemSettings().maxCount(1)));
        public static final Item MINT_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.MINT_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item MINT_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.MINT_BANNER, ElsDyeModBlocks.MINT_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item PEACH_DYE = registerItem("peach_dye", createDyeItem(ElsDyeModDyeColor.PEACH));
        public static final Item PEACH_BED = registerItem(new BedItem(ElsDyeModBlocks.PEACH_BED, new FabricItemSettings().maxCount(1)));
        public static final Item PEACH_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.PEACH_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item PEACH_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.PEACH_BANNER, ElsDyeModBlocks.PEACH_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item PERIWINKLE_DYE = registerItem("periwinkle_dye", createDyeItem(ElsDyeModDyeColor.PERIWINKLE));
        public static final Item PERIWINKLE_BED = registerItem(new BedItem(ElsDyeModBlocks.PERIWINKLE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item PERIWINKLE_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.PERIWINKLE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item PERIWINKLE_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.PERIWINKLE_BANNER, ElsDyeModBlocks.PERIWINKLE_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item ARTICHOKE_DYE = registerItem("artichoke_dye", createDyeItem(ElsDyeModDyeColor.ARTICHOKE));
        public static final Item ARTICHOKE_BED = registerItem(new BedItem(ElsDyeModBlocks.ARTICHOKE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item ARTICHOKE_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.ARTICHOKE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item ARTICHOKE_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.ARTICHOKE_BANNER, ElsDyeModBlocks.ARTICHOKE_WALL_BANNER, new FabricItemSettings().maxCount(16)));

        public static final Item FUCHSIA_DYE = registerItem("fuchsia_dye", createDyeItem(ElsDyeModDyeColor.FUCHSIA));
        public static final Item FUCHSIA_BED = registerItem(new BedItem(ElsDyeModBlocks.FUCHSIA_BED, new FabricItemSettings().maxCount(1)));
        public static final Item FUCHSIA_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.FUCHSIA_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item FUCHSIA_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.FUCHSIA_BANNER, ElsDyeModBlocks.FUCHSIA_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item VERMILION_DYE = registerItem("vermilion_dye", createDyeItem(ElsDyeModDyeColor.VERMILION));
        public static final Item VERMILION_BED = registerItem(new BedItem(ElsDyeModBlocks.VERMILION_BED, new FabricItemSettings().maxCount(1)));
        public static final Item VERMILION_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.VERMILION_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item VERMILION_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.VERMILION_BANNER, ElsDyeModBlocks.VERMILION_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item SHAMROCK_DYE = registerItem("shamrock_dye", createDyeItem(ElsDyeModDyeColor.SHAMROCK));
        public static final Item SHAMROCK_BED = registerItem(new BedItem(ElsDyeModBlocks.SHAMROCK_BED, new FabricItemSettings().maxCount(1)));
        public static final Item SHAMROCK_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.SHAMROCK_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item SHAMROCK_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.SHAMROCK_BANNER, ElsDyeModBlocks.SHAMROCK_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item INDIGO_DYE = registerItem("indigo_dye", createDyeItem(ElsDyeModDyeColor.INDIGO));
        public static final Item INDIGO_BED = registerItem(new BedItem(ElsDyeModBlocks.INDIGO_BED, new FabricItemSettings().maxCount(1)));
        public static final Item INDIGO_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.INDIGO_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item INDIGO_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.INDIGO_BANNER, ElsDyeModBlocks.INDIGO_WALL_BANNER, new FabricItemSettings().maxCount(16)));

        public static final Item BANANA_DYE = registerItem("banana_dye", createDyeItem(ElsDyeModDyeColor.BANANA));
        public static final Item BANANA_BED = registerItem(new BedItem(ElsDyeModBlocks.BANANA_BED, new FabricItemSettings().maxCount(1)));
        public static final Item BANANA_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.BANANA_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item BANANA_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.BANANA_BANNER, ElsDyeModBlocks.BANANA_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item CERULEAN_DYE = registerItem("cerulean_dye", createDyeItem(ElsDyeModDyeColor.CERULEAN));
        public static final Item CERULEAN_BED = registerItem(new BedItem(ElsDyeModBlocks.CERULEAN_BED, new FabricItemSettings().maxCount(1)));
        public static final Item CERULEAN_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.CERULEAN_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item CERULEAN_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.CERULEAN_BANNER, ElsDyeModBlocks.CERULEAN_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item ACORN_DYE = registerItem("acorn_dye", createDyeItem(ElsDyeModDyeColor.ACORN));
        public static final Item ACORN_BED = registerItem(new BedItem(ElsDyeModBlocks.ACORN_BED, new FabricItemSettings().maxCount(1)));
        public static final Item ACORN_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.ACORN_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item ACORN_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.ACORN_BANNER, ElsDyeModBlocks.ACORN_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item MAUVE_DYE = registerItem("mauve_dye", createDyeItem(ElsDyeModDyeColor.MAUVE));
        public static final Item MAUVE_BED = registerItem(new BedItem(ElsDyeModBlocks.MAUVE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item MAUVE_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.MAUVE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item MAUVE_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.MAUVE_BANNER, ElsDyeModBlocks.MAUVE_WALL_BANNER, new FabricItemSettings().maxCount(16)));

        public static final Item MAROON_DYE = registerItem("maroon_dye", createDyeItem(ElsDyeModDyeColor.MAROON));
        public static final Item MAROON_BED = registerItem(new BedItem(ElsDyeModBlocks.MAROON_BED, new FabricItemSettings().maxCount(1)));
        public static final Item MAROON_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.MAROON_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item MAROON_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.MAROON_BANNER, ElsDyeModBlocks.MAROON_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item GRAPE_DYE = registerItem("grape_dye", createDyeItem(ElsDyeModDyeColor.GRAPE));
        public static final Item GRAPE_BED = registerItem(new BedItem(ElsDyeModBlocks.GRAPE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item GRAPE_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.GRAPE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item GRAPE_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.GRAPE_BANNER, ElsDyeModBlocks.GRAPE_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item NAVY_DYE = registerItem("navy_dye", createDyeItem(ElsDyeModDyeColor.NAVY));
        public static final Item NAVY_BED = registerItem(new BedItem(ElsDyeModBlocks.NAVY_BED, new FabricItemSettings().maxCount(1)));
        public static final Item NAVY_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.NAVY_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item NAVY_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.NAVY_BANNER, ElsDyeModBlocks.NAVY_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item SAP_DYE = registerItem("sap_dye", createDyeItem(ElsDyeModDyeColor.SAP));
        public static final Item SAP_BED = registerItem(new BedItem(ElsDyeModBlocks.SAP_BED, new FabricItemSettings().maxCount(1)));
        public static final Item SAP_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.SAP_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item SAP_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.SAP_BANNER, ElsDyeModBlocks.SAP_WALL_BANNER, new FabricItemSettings().maxCount(16)));

        public static final Item AMBER_DYE = registerItem("amber_dye", createDyeItem(ElsDyeModDyeColor.AMBER));
        public static final Item AMBER_BED = registerItem(new BedItem(ElsDyeModBlocks.AMBER_BED, new FabricItemSettings().maxCount(1)));
        public static final Item AMBER_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.AMBER_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item AMBER_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.AMBER_BANNER, ElsDyeModBlocks.AMBER_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item SAGE_DYE = registerItem("sage_dye", createDyeItem(ElsDyeModDyeColor.SAGE));
        public static final Item SAGE_BED = registerItem(new BedItem(ElsDyeModBlocks.SAGE_BED, new FabricItemSettings().maxCount(1)));
        public static final Item SAGE_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.SAGE_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item SAGE_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.SAGE_BANNER, ElsDyeModBlocks.SAGE_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item VELVET_DYE = registerItem("velvet_dye", createDyeItem(ElsDyeModDyeColor.VELVET));
        public static final Item VELVET_BED = registerItem(new BedItem(ElsDyeModBlocks.VELVET_BED, new FabricItemSettings().maxCount(1)));
        public static final Item VELVET_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.VELVET_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item VELVET_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.VELVET_BANNER, ElsDyeModBlocks.VELVET_WALL_BANNER, new FabricItemSettings().maxCount(16)));
        public static final Item MOLD_DYE = registerItem("mold_dye", createDyeItem(ElsDyeModDyeColor.MOLD));
        public static final Item MOLD_BED = registerItem(new BedItem(ElsDyeModBlocks.MOLD_BED, new FabricItemSettings().maxCount(1)));
        public static final Item MOLD_SHULKER_BOX = registerItem(new BlockItem(ElsDyeModBlocks.MOLD_SHULKER_BOX, new FabricItemSettings().maxCount(1)));
        public static final Item MOLD_BANNER = registerItem(new BannerItem(ElsDyeModBlocks.MOLD_BANNER, ElsDyeModBlocks.MOLD_WALL_BANNER, new FabricItemSettings().maxCount(16)));
    //</editor-fold>

    //<editor-fold desc ="Item Creation Functions">
    private static DyeItem createDyeItem(DyeColor color) {
        DyeItem item = new DyeItem(color, new FabricItemSettings());
        DYE_ITEMS.add(item);
        DYE_ITEM_FROM_COLOR.put(color, item);
        DYE_ITEM_TAG_FROM_DYE_ITEM.put(item, ElsDyeModTags.Items.getOrCreateDyeItemCommonTag(item));
        return item;
    }
    //</editor-fold>
    public static void registerModItems() {
        ElsDyeModInit.LOGGER.info("Registering Mod Items for " + ElsDyeMod.MOD_ID);
    }
}
