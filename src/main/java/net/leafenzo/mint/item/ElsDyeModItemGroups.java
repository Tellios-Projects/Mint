package net.leafenzo.mint.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.potions.ElsDyeModPotions;
import net.leafenzo.mint.registration.WoodSet;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
import net.leafenzo.mint.util.ElsDyeModUtil;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.potion.PotionUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import static net.leafenzo.mint.util.ElsDyeModUtil.formatMultipleIdsForErrorLog;

public class ElsDyeModItemGroups {
    public static final HashMap<ItemGroup, DyeColor> ITEM_GROUP_FOR_DYE_COLOR = new HashMap<ItemGroup, DyeColor>();

    public static void registerModItemGroups() {
        ElsDyeModInit.LOGGER.debug("Registering item groups for " + ElsDyeMod.MOD_ID);
        modifyVanillaItemGroupEntries();
    }

    // you can tell I was having a rough time huh :p
    public static RuntimeException ColoredBlocksNotEqualError(DyeColor ca, DyeColor cb, Block[] a, Block[] b) {
        String errorMessage = (ca.getName() + " has " + a.length + " blocks in the colored blocks category" + ", while " + cb.getName() + " has " + b.length + " (" + formatMultipleIdsForErrorLog(a) + " VS " + formatMultipleIdsForErrorLog(b) + ")");
        ElsDyeModInit.LOGGER.error(errorMessage);
        return new RuntimeException(errorMessage);
    }
    public static RuntimeException FunctionalBlocksNotEqualError(DyeColor ca, DyeColor cb, Block[] a, Block[] b) {
        String errorMessage = (ca.getName() + " has " + a.length + " blocks in the functional blocks category, while " + cb.getName() + " has " + b.length + " (" + formatMultipleIdsForErrorLog(a) + " VS " + formatMultipleIdsForErrorLog(b) + ")");
        ElsDyeModInit.LOGGER.error(errorMessage);
        return new RuntimeException(errorMessage);
    }

    public static void addColoredItemsAfter(DyeColor reference, DyeColor toAdd) {
        @NotNull Block[] coloredBlocksReference = ElsDyeModUtil.ColoredBlocksOfColor(reference);
        @NotNull Block[] coloredBlocksToAdd = ElsDyeModUtil.ColoredBlocksOfColor(toAdd);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            if(coloredBlocksReference.length != coloredBlocksToAdd .length) {
                throw ColoredBlocksNotEqualError(reference, toAdd, coloredBlocksReference, coloredBlocksToAdd );
            }
            for (int i = 0; i < coloredBlocksReference.length; i++) {
                content.addAfter(coloredBlocksReference[i].asItem(), coloredBlocksToAdd [i].asItem());
            }
        });

        @NotNull Block[] functionalBlocksBefore = ElsDyeModUtil.FunctionalBlocksOfColor(reference);
        @NotNull Block[] functionalBlocksAfter = ElsDyeModUtil.FunctionalBlocksOfColor(toAdd);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            if(functionalBlocksBefore.length != functionalBlocksAfter.length) {
                throw FunctionalBlocksNotEqualError(reference, toAdd, coloredBlocksReference, coloredBlocksToAdd );
            }
            for (int i = 0; i < functionalBlocksBefore.length; i++) {
                content.addAfter(functionalBlocksBefore[i].asItem(), functionalBlocksAfter[i].asItem());
            }
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
               content.addAfter(DyeItem.byColor(reference), DyeItem.byColor(toAdd));
        });
    }
    public static void addColoredItemsBefore(DyeColor reference, DyeColor toAdd) {
        @NotNull Block[] coloredBlocksReference = ElsDyeModUtil.ColoredBlocksOfColor(reference);
        @NotNull Block[] coloredBlocksToAdd = ElsDyeModUtil.ColoredBlocksOfColor(toAdd);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            if(coloredBlocksReference.length != coloredBlocksToAdd.length) {
                throw ColoredBlocksNotEqualError(reference, toAdd, coloredBlocksReference, coloredBlocksToAdd);
            }
            for (int i = 0; i < coloredBlocksReference.length; i++) {
                content.addBefore(coloredBlocksReference[i].asItem(), coloredBlocksToAdd[i].asItem());
            }
        });

        @NotNull Block[] functionalBlocksBefore = ElsDyeModUtil.FunctionalBlocksOfColor(reference);
        @NotNull Block[] functionalBlocksAfter = ElsDyeModUtil.FunctionalBlocksOfColor(toAdd);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            if(functionalBlocksBefore.length != functionalBlocksAfter.length) {
                throw FunctionalBlocksNotEqualError(reference, toAdd, coloredBlocksReference, coloredBlocksToAdd);
            }
            for (int i = 0; i < functionalBlocksBefore.length; i++) {
                content.addBefore(functionalBlocksBefore[i].asItem(), functionalBlocksAfter[i].asItem());
            }
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addBefore(DyeItem.byColor(reference), DyeItem.byColor(toAdd));
        });
    }

    public static void modifyVanillaItemGroupEntries() {
        addWoodsetItemsToVanillaItemGroups(ElsDyeModBlocks.WINTERGREEN_WOODSET, Items.CHERRY_BUTTON, Items.CHERRY_LEAVES, Items.CHERRY_SAPLING, Items.CHERRY_HANGING_SIGN, Items.CHERRY_CHEST_BOAT);

        //keep in the right places to make a big rainbow ^~^

        // Roughly sort by Hue then Light to Dark
        // make sure they only ever are set to come before or after vanilla dyes!

        //White
        //Light Gray
        //Gray

        //Black
        addColoredItemsAfter(DyeColor.BLACK, ElsDyeModDyeColor.ACORN);


        //Brown
        addColoredItemsAfter(DyeColor.BROWN, ElsDyeModDyeColor.MAROON);
            addColoredItemsAfter(DyeColor.BROWN, ElsDyeModDyeColor.MOLD); //this places it in order before Maroon


        //Red
        addColoredItemsAfter(DyeColor.RED, ElsDyeModDyeColor.PEACH);

        addColoredItemsBefore(DyeColor.ORANGE, ElsDyeModDyeColor.VERMILION);
        //Orange
        addColoredItemsAfter(DyeColor.ORANGE, ElsDyeModDyeColor.AMBER);


        //Yellow
        addColoredItemsAfter(DyeColor.YELLOW, ElsDyeModDyeColor.BANANA);

        addColoredItemsBefore(DyeColor.LIME, ElsDyeModDyeColor.ARTICHOKE);
        //Lime

        addColoredItemsBefore(DyeColor.GREEN, ElsDyeModDyeColor.SAP);
        //Green
        addColoredItemsAfter(DyeColor.GREEN, ElsDyeModDyeColor.SHAMROCK);
            addColoredItemsAfter(DyeColor.GREEN, ElsDyeModDyeColor.SAGE); //this places it in order before Shamrock

        addColoredItemsBefore(DyeColor.CYAN, ElsDyeModDyeColor.MINT);
        //Cyan


        addColoredItemsBefore(DyeColor.LIGHT_BLUE, ElsDyeModDyeColor.CERULEAN);
        //Light Blue


        addColoredItemsBefore(DyeColor.BLUE, ElsDyeModDyeColor.NAVY);
        //Blue
        addColoredItemsAfter(DyeColor.BLUE, ElsDyeModDyeColor.PERIWINKLE);

        addColoredItemsBefore(DyeColor.PURPLE, ElsDyeModDyeColor.GRAPE);
        //Purple
        addColoredItemsAfter(DyeColor.PURPLE, ElsDyeModDyeColor.INDIGO);


        //Magenta
        addColoredItemsAfter(DyeColor.MAGENTA, ElsDyeModDyeColor.MAUVE);
            addColoredItemsAfter(DyeColor.MAGENTA, ElsDyeModDyeColor.VELVET); //this places it in order before Mauve

        addColoredItemsBefore(DyeColor.PINK, ElsDyeModDyeColor.FUCHSIA);
        //Pink

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
//            content.addAfter(Items.COOKIE, ModItems.MINT_COOKIE);
//            content.addAfter(Items.MILK_BUCKET, ModItems.MINT_SPRIG, ModItems.MINT_TEA);
////            content.addAfter(Items.GLOW_BERRIES, ModItems.MINT_SPRIG);
////            content.addAfter(Items.MILK_BUCKET, ModItems.MINT_TEA);
//        });

//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
//            content.addAfter(Items.LARGE_FERN, ModBlocks.WILD_MINT, ModBlocks.MINT_SPRIG_BLOCK);
//        });
//
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
//            content.addAfter(Blocks.DARK_PRISMARINE_SLAB, ModBlocks.MINT_BRICKS, ModBlocks.MINT_BRICK_STAIRS, ModBlocks.MINT_BRICK_SLAB);
//        });
    }

    public static Collection<ItemStack> woodsetItems(WoodSet woodSet) {
        ArrayList<ItemStack> c = new ArrayList<ItemStack>();
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getLeaves());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getSapling());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getLog());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getWood());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getStrippedLog());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getStrippedWood());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getPlanks());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getStairs());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getSlab());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getMosaic());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getMosaicStairs());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getMosaicSlab());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getFence());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getFenceGate());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getDoor());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getTrapDoor());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getPressurePlate());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getButton());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getSignItem());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getHangingSignItem());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getBoatItem());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getChestBoatItem());
        return c;
    }
    public static Collection<ItemStack> woodsetItemsForBuildingBlocksTab(WoodSet woodSet) {
        ArrayList<ItemStack> c = new ArrayList<ItemStack>();
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getLog());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getWood());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getStrippedLog());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getStrippedWood());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getPlanks());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getStairs());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getSlab());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getMosaic());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getMosaicStairs());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getMosaicSlab());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getFence());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getFenceGate());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getDoor());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getTrapDoor());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getPressurePlate());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getButton());
        return c;
    }
    public static Collection<ItemStack> woodsetItemsForFunctionalBlocksTab(WoodSet woodSet) {
        ArrayList<ItemStack> c = new ArrayList<ItemStack>();
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getSignItem());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getHangingSignItem());
        return c;
    }
    public static Collection<ItemStack> woodsetItemsForToolsTab(WoodSet woodSet) {
        ArrayList<ItemStack> c = new ArrayList<ItemStack>();
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getBoatItem());
        ElsDyeModUtil.addToIfNotNull(c, woodSet.getChestBoatItem());
        return c;
    }
    public static void addWoodsetItemsToVanillaItemGroups(WoodSet woodSet, ItemConvertible buttonBefore, ItemConvertible leavesBefore, ItemConvertible saplingBefore, ItemConvertible signBefore, ItemConvertible boatBefore) {
        ArrayList<ItemStack> bb = (ArrayList<ItemStack>) woodsetItemsForBuildingBlocksTab(woodSet);
        ArrayList<ItemStack> fb = (ArrayList<ItemStack>) woodsetItemsForFunctionalBlocksTab(woodSet);
        ArrayList<ItemStack> tt = (ArrayList<ItemStack>) woodsetItemsForToolsTab(woodSet);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            for (int i = bb.size()-1; i >= 0; i--) {
                content.addAfter(buttonBefore, bb.get(i));
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            if(woodSet.getLeaves() != null) { content.addAfter(leavesBefore, woodSet.getLeaves()); }
            if(woodSet.getSapling() != null) { content.addAfter(saplingBefore, woodSet.getSapling()); }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            for (int i = fb.size()-1; i >= 0; i--) {
                content.addAfter(signBefore, fb.get(i));
            }
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            for (int i = tt.size()-1; i >= 0; i--) {
                content.addAfter(boatBefore, tt.get(i));
            }
        });
    }

    public static ItemGroup DYE_MOD_ADDITIONS = Registry.register(Registries.ITEM_GROUP, new Identifier(ElsDyeMod.MOD_ID, "dye_mod_additions"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + ElsDyeMod.MOD_ID + ".dye_mod_additions"))
                    .icon(() -> new ItemStack(ElsDyeModItems.MINT_SPRIG)).entries((displayContext, entries) -> {
                        entries.add(ElsDyeModItems.MINT_DYE);
                        entries.add(ElsDyeModBlocks.MINT_WOOL);
                        entries.add(ElsDyeModBlocks.MINT_CARPET);
                        entries.add(ElsDyeModBlocks.MINT_CONCRETE);
                        entries.add(ElsDyeModBlocks.MINT_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.MINT_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.MINT_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.MINT_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.MINT_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModItems.MINT_BED);
                        entries.add(ElsDyeModItems.MINT_BANNER);
                        entries.add(ElsDyeModItems.MINT_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.MINT_CANDLE);

                        entries.add(ElsDyeModItems.MINT_SPRIG);
                        entries.add(ElsDyeModItems.MINT_COOKIE);
                        entries.add(ElsDyeModBlocks.MINT_SPRIG_BLOCK);
                        entries.add(ElsDyeModItems.MINT_TEA);
                        entries.add(ElsDyeModItems.WINTERGREEN_SAP);
                        entries.add(ElsDyeModItems.WINTERGREEN_BERRIES);
                        entries.add(ElsDyeModItems.WINTER_MEDLEY);
                        entries.add(ElsDyeModBlocks.WILD_MINT);
                        entries.add(ElsDyeModBlocks.MINT_BRICKS);
                        entries.add(ElsDyeModBlocks.MINT_BRICK_SLAB);
                        entries.add(ElsDyeModBlocks.MINT_BRICK_STAIRS);
                        entries.add(ElsDyeModBlocks.MINT_BRICK_WALL);
                        entries.addAll(woodsetItems(ElsDyeModBlocks.WINTERGREEN_WOODSET));
                        entries.add(ElsDyeModItems.WINTERGREEN_CANDY_CANE);
                        entries.add(ElsDyeModItems.PEPPERMINT_CANDY_CANE);
                        entries.add(ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BLOCK);
                        entries.add(ElsDyeModBlocks.WINTERGREEN_CANDY_CANE_BARK);
                        entries.add(ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BLOCK);
                        entries.add(ElsDyeModBlocks.PEPPERMINT_CANDY_CANE_BARK);

                        entries.add(PotionUtil.setPotion(Items.POTION.getDefaultStack(), ElsDyeModPotions.MINT_CHILL));
                        entries.add(PotionUtil.setPotion(Items.POTION.getDefaultStack(), ElsDyeModPotions.LONG_MINT_CHILL));
                        entries.add(PotionUtil.setPotion(Items.POTION.getDefaultStack(), ElsDyeModPotions.STRONG_MINT_CHILL));
                        entries.add(PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), ElsDyeModPotions.MINT_CHILL));
                        entries.add(PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), ElsDyeModPotions.LONG_MINT_CHILL));
                        entries.add(PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), ElsDyeModPotions.STRONG_MINT_CHILL));
                        entries.add(PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), ElsDyeModPotions.MINT_CHILL));
                        entries.add(PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), ElsDyeModPotions.LONG_MINT_CHILL));
                        entries.add(PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), ElsDyeModPotions.STRONG_MINT_CHILL));

                        entries.add(ElsDyeModItems.PEACH_DYE);
                        entries.add(ElsDyeModBlocks.PEACH_WOOL);
                        entries.add(ElsDyeModBlocks.PEACH_CARPET);
                        entries.add(ElsDyeModBlocks.PEACH_CONCRETE);
                        entries.add(ElsDyeModBlocks.PEACH_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.PEACH_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.PEACH_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.PEACH_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.PEACH_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.PEACH_BED);
                        entries.add(ElsDyeModBlocks.PEACH_BANNER);
                        entries.add(ElsDyeModBlocks.PEACH_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.PEACH_CANDLE);

                        entries.add(ElsDyeModBlocks.HYPERICUM);
                        entries.add(ElsDyeModBlocks.PEACH_SAPLING);
                        entries.add(ElsDyeModBlocks.PEACH_LOG);
                        entries.add(ElsDyeModBlocks.PEACH_WOOD);
                        entries.add(ElsDyeModBlocks.STRIPPED_PEACH_LOG);
                        entries.add(ElsDyeModBlocks.STRIPPED_PEACH_WOOD);
                        entries.add(ElsDyeModBlocks.PEACH_LEAVES);
                        entries.add(ElsDyeModBlocks.FLOWERING_PEACH_LEAVES);
                        entries.add(ElsDyeModItems.PEACH);
                        entries.add(ElsDyeModItems.PEACH_PIT);
                        entries.add(ElsDyeModItems.PEACH_SLICE);
                        entries.add(ElsDyeModItems.GOLDEN_PEACH);
                        entries.add(ElsDyeModItems.PEACH_COBBLER);
                        entries.add(ElsDyeModItems.FRUIT_SALAD);
                        entries.add(ElsDyeModItems.AMBROSIA);
                        entries.add(ElsDyeModBlocks.CORAL_ANEMONE);
                        entries.add(ElsDyeModItems.COOKED_ANEMONE);
                        entries.add(ElsDyeModItems.PEACH_BRANCH);
                        entries.add(ElsDyeModBlocks.CORALSOIL);
                        entries.add(ElsDyeModBlocks.CORALSOIL_BRICKS);
                        entries.add(ElsDyeModBlocks.CRACKED_CORALSOIL_BRICKS);
                        entries.add(ElsDyeModBlocks.CORALSOIL_BRICK_SLAB);
                        entries.add(ElsDyeModBlocks.CORALSOIL_BRICK_STAIRS);
                        entries.add(ElsDyeModBlocks.CORALSOIL_BRICK_WALL);

                        entries.add(ElsDyeModItems.PERIWINKLE_DYE);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_WOOL);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_CARPET);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_CONCRETE);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_BED);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_BANNER);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_CANDLE);

                        entries.add(ElsDyeModBlocks.LAVENDER_BRICKS);
                        entries.add(ElsDyeModBlocks.LAVENDER_BRICK_STAIRS);
                        entries.add(ElsDyeModBlocks.LAVENDER_BRICK_SLAB);
                        entries.add(ElsDyeModBlocks.LAVENDER_BRICK_WALL);
                        entries.add(ElsDyeModBlocks.MOSSY_LAVENDER_BRICKS);
                        entries.add(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_SLAB);
                        entries.add(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_STAIRS);
                        entries.add(ElsDyeModBlocks.MOSSY_LAVENDER_BRICK_WALL);
                        entries.add(ElsDyeModBlocks.LAVENDER_CLAY);
                        entries.add(ElsDyeModBlocks.LAVENDER_BUSHEL);
                        entries.add(ElsDyeModBlocks.PERIWINKLE_PETALS);
                        entries.add(ElsDyeModBlocks.HIDCOTE_LAVENDER);
                        entries.add(ElsDyeModBlocks.LAVENDER_OIL_LANTERN);
                        entries.add(ElsDyeModItems.FLOWERING_MELON);
                        entries.add(ElsDyeModItems.SMOKED_LAVENDER);
                        entries.add(ElsDyeModItems.LAVENDER_BREAD);
                        entries.add(ElsDyeModItems.LAVENDER_SOAP);
                        entries.add(ElsDyeModItems.LAVENDER_OIL);


                        entries.add(ElsDyeModItems.ARTICHOKE_DYE);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_WOOL);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_CARPET);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_CONCRETE);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_BED);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_BANNER);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.ARTICHOKE_CANDLE);

                        entries.add(ElsDyeModBlocks.WAXCAP_WAX_BLOCK);
                        entries.add(ElsDyeModBlocks.WAXCAP_STEM_BLOCK);
                        entries.add(ElsDyeModBlocks.WAXCAP_CAP_BLOCK);
                        entries.add(ElsDyeModBlocks.WAXCAP_GILLS);
                        entries.add(ElsDyeModBlocks.WAXCAP_GILL_SLAB);
                        entries.add(ElsDyeModBlocks.WAXCAP_MUSHROOM);
                        entries.add(ElsDyeModItems.WAXCAP_WAX);
                        entries.add(ElsDyeModBlocks.THISTLE_FLOWER);
                        entries.add(ElsDyeModItems.ARTICHOKE);
                        entries.add(ElsDyeModItems.ARTICHOKE_HEART);
                        entries.add(ElsDyeModItems.ARTICHOKE_LAMB);
                        entries.add(ElsDyeModItems.BREAKFAST_PORKCHOP);

                        entries.add(PotionUtil.setPotion(Items.POTION.getDefaultStack(), ElsDyeModPotions.THORNS));
                        entries.add(PotionUtil.setPotion(Items.POTION.getDefaultStack(), ElsDyeModPotions.LONG_THORNS));
                        entries.add(PotionUtil.setPotion(Items.POTION.getDefaultStack(), ElsDyeModPotions.STRONG_THORNS));
                        entries.add(PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), ElsDyeModPotions.THORNS));
                        entries.add(PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), ElsDyeModPotions.LONG_THORNS));
                        entries.add(PotionUtil.setPotion(Items.SPLASH_POTION.getDefaultStack(), ElsDyeModPotions.STRONG_THORNS));
                        entries.add(PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), ElsDyeModPotions.THORNS));
                        entries.add(PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), ElsDyeModPotions.LONG_THORNS));
                        entries.add(PotionUtil.setPotion(Items.LINGERING_POTION.getDefaultStack(), ElsDyeModPotions.STRONG_THORNS));

                        entries.add(ElsDyeModItems.FUCHSIA_DYE);
                        entries.add(ElsDyeModBlocks.FUCHSIA_WOOL);
                        entries.add(ElsDyeModBlocks.FUCHSIA_CARPET);
                        entries.add(ElsDyeModBlocks.FUCHSIA_CONCRETE);
                        entries.add(ElsDyeModBlocks.FUCHSIA_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.FUCHSIA_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.FUCHSIA_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.FUCHSIA_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.FUCHSIA_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.FUCHSIA_BED);
                        entries.add(ElsDyeModBlocks.FUCHSIA_BANNER);
                        entries.add(ElsDyeModBlocks.FUCHSIA_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.FUCHSIA_CANDLE);

                        entries.add(ElsDyeModItems.VERMILION_DYE);
                        entries.add(ElsDyeModBlocks.VERMILION_WOOL);
                        entries.add(ElsDyeModBlocks.VERMILION_CARPET);
                        entries.add(ElsDyeModBlocks.VERMILION_CONCRETE);
                        entries.add(ElsDyeModBlocks.VERMILION_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.VERMILION_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.VERMILION_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.VERMILION_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.VERMILION_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.VERMILION_BED);
                        entries.add(ElsDyeModBlocks.VERMILION_BANNER);
                        entries.add(ElsDyeModBlocks.VERMILION_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.VERMILION_CANDLE);

                        entries.add(ElsDyeModItems.SHAMROCK_DYE);
                        entries.add(ElsDyeModBlocks.SHAMROCK_WOOL);
                        entries.add(ElsDyeModBlocks.SHAMROCK_CARPET);
                        entries.add(ElsDyeModBlocks.SHAMROCK_CONCRETE);
                        entries.add(ElsDyeModBlocks.SHAMROCK_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.SHAMROCK_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.SHAMROCK_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.SHAMROCK_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.SHAMROCK_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.SHAMROCK_BED);
                        entries.add(ElsDyeModBlocks.SHAMROCK_BANNER);
                        entries.add(ElsDyeModBlocks.SHAMROCK_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.SHAMROCK_CANDLE);

                        entries.add(ElsDyeModItems.INDIGO_DYE);
                        entries.add(ElsDyeModBlocks.INDIGO_WOOL);
                        entries.add(ElsDyeModBlocks.INDIGO_CARPET);
                        entries.add(ElsDyeModBlocks.INDIGO_CONCRETE);
                        entries.add(ElsDyeModBlocks.INDIGO_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.INDIGO_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.INDIGO_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.INDIGO_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.INDIGO_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.INDIGO_BED);
                        entries.add(ElsDyeModBlocks.INDIGO_BANNER);
                        entries.add(ElsDyeModBlocks.INDIGO_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.INDIGO_CANDLE);

                        entries.add(ElsDyeModItems.BANANA_DYE);
                        entries.add(ElsDyeModBlocks.BANANA_WOOL);
                        entries.add(ElsDyeModBlocks.BANANA_CARPET);
                        entries.add(ElsDyeModBlocks.BANANA_CONCRETE);
                        entries.add(ElsDyeModBlocks.BANANA_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.BANANA_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.BANANA_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.BANANA_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.BANANA_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.BANANA_BED);
                        entries.add(ElsDyeModBlocks.BANANA_BANNER);
                        entries.add(ElsDyeModBlocks.BANANA_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.BANANA_CANDLE);

                        entries.add(ElsDyeModItems.CERULEAN_DYE);
                        entries.add(ElsDyeModBlocks.CERULEAN_WOOL);
                        entries.add(ElsDyeModBlocks.CERULEAN_CARPET);
                        entries.add(ElsDyeModBlocks.CERULEAN_CONCRETE);
                        entries.add(ElsDyeModBlocks.CERULEAN_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.CERULEAN_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.CERULEAN_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.CERULEAN_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.CERULEAN_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.CERULEAN_BED);
                        entries.add(ElsDyeModBlocks.CERULEAN_BANNER);
                        entries.add(ElsDyeModBlocks.CERULEAN_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.CERULEAN_CANDLE);

                        entries.add(ElsDyeModItems.ACORN_DYE);
                        entries.add(ElsDyeModBlocks.ACORN_WOOL);
                        entries.add(ElsDyeModBlocks.ACORN_CARPET);
                        entries.add(ElsDyeModBlocks.ACORN_CONCRETE);
                        entries.add(ElsDyeModBlocks.ACORN_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.ACORN_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.ACORN_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.ACORN_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.ACORN_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.ACORN_BED);
                        entries.add(ElsDyeModBlocks.ACORN_BANNER);
                        entries.add(ElsDyeModBlocks.ACORN_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.ACORN_CANDLE);

                        entries.add(ElsDyeModItems.MAUVE_DYE);
                        entries.add(ElsDyeModBlocks.MAUVE_WOOL);
                        entries.add(ElsDyeModBlocks.MAUVE_CARPET);
                        entries.add(ElsDyeModBlocks.MAUVE_CONCRETE);
                        entries.add(ElsDyeModBlocks.MAUVE_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.MAUVE_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.MAUVE_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.MAUVE_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.MAUVE_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.MAUVE_BED);
                        entries.add(ElsDyeModBlocks.MAUVE_BANNER);
                        entries.add(ElsDyeModBlocks.MAUVE_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.MAUVE_CANDLE);

                        entries.add(ElsDyeModItems.MAROON_DYE);
                        entries.add(ElsDyeModBlocks.MAROON_WOOL);
                        entries.add(ElsDyeModBlocks.MAROON_CARPET);
                        entries.add(ElsDyeModBlocks.MAROON_CONCRETE);
                        entries.add(ElsDyeModBlocks.MAROON_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.MAROON_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.MAROON_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.MAROON_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.MAROON_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.MAROON_BED);
                        entries.add(ElsDyeModBlocks.MAROON_BANNER);
                        entries.add(ElsDyeModBlocks.MAROON_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.MAROON_CANDLE);

                        entries.add(ElsDyeModItems.COCHINEAL_BEETLE);
                        entries.add(ElsDyeModItems.CARMINIC_COCHINEAL_BEETLE);
                        entries.add(ElsDyeModItems.CACTUS_CHUNK);
                        entries.add(ElsDyeModBlocks.CACTUS_FEED);
                        entries.add(ElsDyeModBlocks.MADDER);
                        entries.add(ElsDyeModBlocks.MADDER_ROOTED_GRASS_BLOCK);
                        entries.add(ElsDyeModBlocks.MADDER_ROOTED_DIRT);
                        entries.add(ElsDyeModItems.MADDER_ROOT);
                        entries.addAll(woodsetItems(ElsDyeModBlocks.MADDER_WOODSET));
                        entries.add(ElsDyeModBlocks.CINNABAR_BLOCK);
                        entries.add(ElsDyeModBlocks.CINNABAR_PILLAR);
                        entries.add(ElsDyeModBlocks.BUDDING_CINNABAR);
                        entries.add(ElsDyeModBlocks.SMALL_CINNABAR_BUD);
                        entries.add(ElsDyeModBlocks.MEDIUM_CINNABAR_BUD);
                        entries.add(ElsDyeModBlocks.LARGE_CINNABAR_BUD);
                        entries.add(ElsDyeModBlocks.CINNABAR_CLUSTER);
                        entries.add(ElsDyeModItems.CINNABAR);
                        entries.add(ElsDyeModItems.POWDERED_CINNABAR);
                        entries.add(ElsDyeModBlocks.CINNAMON_BRICKS);
                        entries.add(ElsDyeModBlocks.CINNAMON_BRICK_STAIRS);
                        entries.add(ElsDyeModBlocks.CINNAMON_BRICK_SLAB);
                        entries.add(ElsDyeModBlocks.CINNAMON_BRICK_WALL);
                        entries.add(ElsDyeModBlocks.CRACKED_CINNAMON_BRICKS);
//                        entries.add(ModItems.GAS_BOMB);

                        entries.add(ElsDyeModItems.GRAPE_DYE);
                        entries.add(ElsDyeModBlocks.GRAPE_WOOL);
                        entries.add(ElsDyeModBlocks.GRAPE_CARPET);
                        entries.add(ElsDyeModBlocks.GRAPE_CONCRETE);
                        entries.add(ElsDyeModBlocks.GRAPE_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.GRAPE_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.GRAPE_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.GRAPE_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.GRAPE_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.GRAPE_BED);
                        entries.add(ElsDyeModBlocks.GRAPE_BANNER);
                        entries.add(ElsDyeModBlocks.GRAPE_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.GRAPE_CANDLE);

                        entries.add(ElsDyeModItems.NAVY_DYE);
                        entries.add(ElsDyeModBlocks.NAVY_WOOL);
                        entries.add(ElsDyeModBlocks.NAVY_CARPET);
                        entries.add(ElsDyeModBlocks.NAVY_CONCRETE);
                        entries.add(ElsDyeModBlocks.NAVY_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.NAVY_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.NAVY_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.NAVY_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.NAVY_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.NAVY_BED);
                        entries.add(ElsDyeModBlocks.NAVY_BANNER);
                        entries.add(ElsDyeModBlocks.NAVY_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.NAVY_CANDLE);
//                        entries.add(ModItems.BEETLE_SPAWN_EGG);

                        entries.add(ElsDyeModItems.SAP_DYE);
                        entries.add(ElsDyeModBlocks.SAP_WOOL);
                        entries.add(ElsDyeModBlocks.SAP_CARPET);
                        entries.add(ElsDyeModBlocks.SAP_CONCRETE);
                        entries.add(ElsDyeModBlocks.SAP_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.SAP_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.SAP_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.SAP_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.SAP_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.SAP_BED);
                        entries.add(ElsDyeModBlocks.SAP_BANNER);
                        entries.add(ElsDyeModBlocks.SAP_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.SAP_CANDLE);

                        entries.add(ElsDyeModItems.AMBER_DYE);
                        entries.add(ElsDyeModBlocks.AMBER_WOOL);
                        entries.add(ElsDyeModBlocks.AMBER_CARPET);
                        entries.add(ElsDyeModBlocks.AMBER_CONCRETE);
                        entries.add(ElsDyeModBlocks.AMBER_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.AMBER_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.AMBER_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.AMBER_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.AMBER_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.AMBER_BED);
                        entries.add(ElsDyeModBlocks.AMBER_BANNER);
                        entries.add(ElsDyeModBlocks.AMBER_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.AMBER_CANDLE);

                        entries.add(ElsDyeModItems.AMBER);
                        entries.add(ElsDyeModBlocks.AMBER_BLOCK);
                        entries.add(ElsDyeModBlocks.AMBER_BRICKS);
                        entries.add(ElsDyeModBlocks.AMBER_BRICK_STAIRS);
                        entries.add(ElsDyeModBlocks.AMBER_BRICK_SLAB);
                        entries.add(ElsDyeModBlocks.AMBER_BRICK_WALL);
                        entries.add(ElsDyeModBlocks.CHISELED_AMBER_BRICKS);
                        entries.add(ElsDyeModItems.EMBER);
                        entries.add(ElsDyeModItems.EMBER_ARROW);
                        entries.add(ElsDyeModItems.SAVANNABUD_SEEDS);
                        entries.add(ElsDyeModBlocks.SHIMMERING_SAVANNABUDS);
                        entries.add(ElsDyeModItems.PINEAPPLE);
                        entries.add(ElsDyeModItems.PINEAPPLE_CROWN);
                        entries.add(ElsDyeModItems.PINEAPPLE_SLICES);
                        entries.add(ElsDyeModItems.PINEAPPLE_KEBAB);
                        entries.add(ElsDyeModItems.PINEAPPLE_TART);

                        entries.add(ElsDyeModItems.SAGE_DYE);
                        entries.add(ElsDyeModBlocks.SAGE_WOOL);
                        entries.add(ElsDyeModBlocks.SAGE_CARPET);
                        entries.add(ElsDyeModBlocks.SAGE_CONCRETE);
                        entries.add(ElsDyeModBlocks.SAGE_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.SAGE_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.SAGE_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.SAGE_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.SAGE_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.SAGE_BED);
                        entries.add(ElsDyeModBlocks.SAGE_BANNER);
                        entries.add(ElsDyeModBlocks.SAGE_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.SAGE_CANDLE);

                        entries.add(ElsDyeModItems.VELVET_DYE);
                        entries.add(ElsDyeModBlocks.VELVET_WOOL);
                        entries.add(ElsDyeModBlocks.VELVET_CARPET);
                        entries.add(ElsDyeModBlocks.VELVET_CONCRETE);
                        entries.add(ElsDyeModBlocks.VELVET_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.VELVET_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.VELVET_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.VELVET_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.VELVET_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.VELVET_BED);
                        entries.add(ElsDyeModBlocks.VELVET_BANNER);
                        entries.add(ElsDyeModBlocks.VELVET_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.VELVET_CANDLE);

                        entries.add(ElsDyeModItems.STRAWBERRY);
                        entries.add(ElsDyeModItems.CHOCOLATE_STRAWBERRY);
                        entries.add(ElsDyeModItems.GOLDEN_STRAWBERRY);
                        entries.add(ElsDyeModItems.STRAWBERRY_MILK);
                        entries.add(ElsDyeModItems.STRAWBERRY_SHORTCAKE);
                        entries.add(ElsDyeModItems.STRAWBERRY_CHEESECAKE);
                        entries.add(ElsDyeModItems.ANGEL_FOOD_CAKE);
                        entries.add(ElsDyeModItems.CHERRIES);
                        entries.add(ElsDyeModItems.CHERRY_PIE);
                        entries.add(ElsDyeModBlocks.CORDYLINE);
                        entries.add(ElsDyeModBlocks.TALL_CORDYLINE);
                        entries.add(ElsDyeModBlocks.PLUM_CORDYLINE);
                        entries.add(ElsDyeModBlocks.TALL_PLUM_CORDYLINE);
                        entries.add(ElsDyeModItems.POKEBERRIES);
                        entries.add(ElsDyeModBlocks.VELVET_CAKE);

                        entries.add(ElsDyeModItems.MOLD_DYE);
                        entries.add(ElsDyeModBlocks.MOLD_WOOL);
                        entries.add(ElsDyeModBlocks.MOLD_CARPET);
                        entries.add(ElsDyeModBlocks.MOLD_CONCRETE);
                        entries.add(ElsDyeModBlocks.MOLD_CONCRETE_POWDER);
                        entries.add(ElsDyeModBlocks.MOLD_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.MOLD_GLAZED_TERRACOTTA);
                        entries.add(ElsDyeModBlocks.MOLD_STAINED_GLASS);
                        entries.add(ElsDyeModBlocks.MOLD_STAINED_GLASS_PANE);
                        entries.add(ElsDyeModBlocks.MOLD_BED);
                        entries.add(ElsDyeModBlocks.MOLD_BANNER);
                        entries.add(ElsDyeModBlocks.MOLD_SHULKER_BOX);
                        entries.add(ElsDyeModBlocks.MOLD_CANDLE);

                        for(Block block : ElsDyeModBlocks.ALL_CORRUGATED_IRON_BLOCKS) { entries.add(block.asItem()); }
                        for(Block block : ElsDyeModBlocks.ALL_MUCKTUFF_BLOCKS) { entries.add(block.asItem()); }
                    }).build());

//    public static ItemGroup MINT = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "mint"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".mint"))
//                    .icon(() -> new ItemStack(ModItems.MINT_SPRIG)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.MINT_DYE);
//                        entries.add(ModBlocks.MINT_WOOL);
//                        entries.add(ModBlocks.MINT_CARPET);
//                        entries.add(ModBlocks.MINT_CONCRETE);
//                        entries.add(ModBlocks.MINT_CONCRETE_POWDER);
//                        entries.add(ModBlocks.MINT_TERRACOTTA);
//                        entries.add(ModBlocks.MINT_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.MINT_STAINED_GLASS);
//                        entries.add(ModBlocks.MINT_STAINED_GLASS_PANE);
//                        entries.add(ModItems.MINT_BED);
//                        entries.add(ModItems.MINT_BANNER);
//                        entries.add(ModItems.MINT_SHULKER_BOX);
//                        entries.add(ModBlocks.MINT_CANDLE);
//
//                        entries.add(ModItems.MINT_SPRIG);
//                        entries.add(ModItems.MINT_COOKIE);
//                        entries.add(ModBlocks.MINT_SPRIG_BLOCK);
//                        entries.add(ModItems.MINT_TEA);
//                        entries.add(ModBlocks.WILD_MINT);
//                        entries.add(ModBlocks.MINT_BRICKS);
//                        entries.add(ModBlocks.MINT_BRICK_SLAB);
//                        entries.add(ModBlocks.MINT_BRICK_STAIRS);
//                        //entries.add(ModBlocks.MINT_BRICK_WALL);
//                    }).build());

//    public static ItemGroup PEACH = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "peach"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".peach"))
//                    .icon(() -> new ItemStack(ModItems.PEACH_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.PEACH_DYE);
//                        entries.add(ModBlocks.PEACH_WOOL);
//                        entries.add(ModBlocks.PEACH_CARPET);
//                        entries.add(ModBlocks.PEACH_CONCRETE);
//                        entries.add(ModBlocks.PEACH_CONCRETE_POWDER);
//                        entries.add(ModBlocks.PEACH_TERRACOTTA);
//                        entries.add(ModBlocks.PEACH_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.PEACH_STAINED_GLASS);
//                        entries.add(ModBlocks.PEACH_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.PEACH_BED);
//                        entries.add(ModBlocks.PEACH_BANNER);
//                        entries.add(ModBlocks.PEACH_SHULKER_BOX);
//                        entries.add(ModBlocks.PEACH_CANDLE);
//
//                        entries.add(ModBlocks.HYPERICUM);
//                        entries.add(ModBlocks.PEACH_LOG);
//                        entries.add(ModItems.PEACH);
//                        entries.add(ModItems.PEACH_PIT);
//                        entries.add(ModItems.PEACH_SLICE);
//                        entries.add(ModItems.GOLDEN_PEACH);
//                        entries.add(ModItems.PEACH_COBBLER);
//                        entries.add(ModItems.FRUIT_SALAD);
//                        entries.add(ModItems.AMBROSIA);
//                        entries.add(ModBlocks.CORAL_ANEMONE);
//                        entries.add(ModItems.COOKED_ANEMONE);
//                        entries.add(ModItems.PEACH_BRANCH);
//                    }).build());

//    public static ItemGroup PERIWINKLE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "periwinkle"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".periwinkle"))
//                    .icon(() -> new ItemStack(ModItems.PERIWINKLE_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.PERIWINKLE_DYE);
//                        entries.add(ModBlocks.PERIWINKLE_WOOL);
//                        entries.add(ModBlocks.PERIWINKLE_CARPET);
//                        entries.add(ModBlocks.PERIWINKLE_CONCRETE);
//                        entries.add(ModBlocks.PERIWINKLE_CONCRETE_POWDER);
//                        entries.add(ModBlocks.PERIWINKLE_TERRACOTTA);
//                        entries.add(ModBlocks.PERIWINKLE_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.PERIWINKLE_STAINED_GLASS);
//                        entries.add(ModBlocks.PERIWINKLE_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.PERIWINKLE_BED);
//                        entries.add(ModBlocks.PERIWINKLE_BANNER);
//                        entries.add(ModBlocks.PERIWINKLE_SHULKER_BOX);
//                        entries.add(ModBlocks.PERIWINKLE_CANDLE);
//
//                        entries.add(ModBlocks.LAVENDER_BRICKS);
//                        entries.add(ModBlocks.LAVENDER_BRICK_STAIRS);
//                        entries.add(ModBlocks.LAVENDER_BRICK_SLAB);
//                        entries.add(ModBlocks.LAVENDER_BRICK_WALL);
//                        entries.add(ModBlocks.MOSSY_LAVENDER_BRICKS);
//                        entries.add(ModBlocks.MOSSY_LAVENDER_BRICK_SLAB);
//                        entries.add(ModBlocks.MOSSY_LAVENDER_BRICK_STAIRS);
//                        entries.add(ModBlocks.MOSSY_LAVENDER_BRICK_WALL);
//                        entries.add(ModBlocks.LAVENDER_CLAY);
//                        entries.add(ModBlocks.LAVENDER_BUSHEL);
//                        entries.add(ModBlocks.PERIWINKLE_PETALS);
//                        entries.add(ModBlocks.HIDCOTE_LAVENDER);
//                        entries.add(ModBlocks.LAVENDER_OIL_LANTERN);
//                        entries.add(ModItems.FLOWERING_MELON);
//                        entries.add(ModItems.SMOKED_LAVENDER);
//                        entries.add(ModItems.LAVENDER_BREAD);
//                        entries.add(ModItems.LAVENDER_SOAP);
//                        entries.add(ModItems.LAVENDER_OIL);
//                    }).build());

//    public static ItemGroup ARTICHOKE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "artichoke"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".artichoke"))
//                    .icon(() -> new ItemStack(ModItems.ARTICHOKE_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.ARTICHOKE_DYE);
//                        entries.add(ModBlocks.ARTICHOKE_WOOL);
//                        entries.add(ModBlocks.ARTICHOKE_CARPET);
//                        entries.add(ModBlocks.ARTICHOKE_CONCRETE);
//                        entries.add(ModBlocks.ARTICHOKE_CONCRETE_POWDER);
//                        entries.add(ModBlocks.ARTICHOKE_TERRACOTTA);
//                        entries.add(ModBlocks.ARTICHOKE_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.ARTICHOKE_STAINED_GLASS);
//                        entries.add(ModBlocks.ARTICHOKE_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.ARTICHOKE_BED);
//                        entries.add(ModBlocks.ARTICHOKE_BANNER);
//                        entries.add(ModBlocks.ARTICHOKE_SHULKER_BOX);
//                        entries.add(ModBlocks.ARTICHOKE_CANDLE);
//
//                        entries.add(ModBlocks.WAXCAP_WAX_BLOCK);
//                        entries.add(ModBlocks.WAXCAP_STEM_BLOCK);
//                        entries.add(ModBlocks.WAXCAP_CAP_BLOCK);
//                        entries.add(ModBlocks.WAXCAP_GILL_SLAB);
//                        entries.add(ModBlocks.WAXCAP_MUSHROOM);
//                        entries.add(ModItems.WAXCAP_WAX);
//
//                        entries.add(ModBlocks.THISTLE_FLOWER);
//                        entries.add(ModItems.ARTICHOKE);
//                        entries.add(ModItems.ARTICHOKE_HEART);
//                        entries.add(ModItems.ARTICHOKE_LAMB);
//                        entries.add(ModItems.BREAKFAST_PORKCHOP);
//                    }).build());
//
//    public static ItemGroup FUCHSIA = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "fuchsia"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".fuchsia"))
//                    .icon(() -> new ItemStack(ModItems.FUCHSIA_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.FUCHSIA_DYE);
//                        entries.add(ModBlocks.FUCHSIA_WOOL);
//                        entries.add(ModBlocks.FUCHSIA_CARPET);
//                        entries.add(ModBlocks.FUCHSIA_CONCRETE);
//                        entries.add(ModBlocks.FUCHSIA_CONCRETE_POWDER);
//                        entries.add(ModBlocks.FUCHSIA_TERRACOTTA);
//                        entries.add(ModBlocks.FUCHSIA_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.FUCHSIA_STAINED_GLASS);
//                        entries.add(ModBlocks.FUCHSIA_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.FUCHSIA_BED);
//                        entries.add(ModBlocks.FUCHSIA_BANNER);
//                        entries.add(ModBlocks.FUCHSIA_SHULKER_BOX);
//                        entries.add(ModBlocks.FUCHSIA_CANDLE);
//                    }).build());
//
//    public static ItemGroup VERMILION = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "vermilion"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".vermilion"))
//                    .icon(() -> new ItemStack(ModItems.VERMILION_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.VERMILION_DYE);
//                        entries.add(ModBlocks.VERMILION_WOOL);
//                        entries.add(ModBlocks.VERMILION_CARPET);
//                        entries.add(ModBlocks.VERMILION_CONCRETE);
//                        entries.add(ModBlocks.VERMILION_CONCRETE_POWDER);
//                        entries.add(ModBlocks.VERMILION_TERRACOTTA);
//                        entries.add(ModBlocks.VERMILION_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.VERMILION_STAINED_GLASS);
//                        entries.add(ModBlocks.VERMILION_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.VERMILION_BED);
//                        entries.add(ModBlocks.VERMILION_BANNER);
//                        entries.add(ModBlocks.VERMILION_SHULKER_BOX);
//                        entries.add(ModBlocks.VERMILION_CANDLE);
//                    }).build());
//
//    public static ItemGroup SHAMROCK = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "shamrock"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".shamrock"))
//                    .icon(() -> new ItemStack(ModItems.SHAMROCK_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.SHAMROCK_DYE);
//                        entries.add(ModBlocks.SHAMROCK_WOOL);
//                        entries.add(ModBlocks.SHAMROCK_CARPET);
//                        entries.add(ModBlocks.SHAMROCK_CONCRETE);
//                        entries.add(ModBlocks.SHAMROCK_CONCRETE_POWDER);
//                        entries.add(ModBlocks.SHAMROCK_TERRACOTTA);
//                        entries.add(ModBlocks.SHAMROCK_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.SHAMROCK_STAINED_GLASS);
//                        entries.add(ModBlocks.SHAMROCK_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.SHAMROCK_BED);
//                        entries.add(ModBlocks.SHAMROCK_BANNER);
//                        entries.add(ModBlocks.SHAMROCK_SHULKER_BOX);
//                        entries.add(ModBlocks.SHAMROCK_CANDLE);
//                    }).build());
//
//    public static ItemGroup INDIGO = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "indigo"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".indigo"))
//                    .icon(() -> new ItemStack(ModItems.INDIGO_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.INDIGO_DYE);
//                        entries.add(ModBlocks.INDIGO_WOOL);
//                        entries.add(ModBlocks.INDIGO_CARPET);
//                        entries.add(ModBlocks.INDIGO_CONCRETE);
//                        entries.add(ModBlocks.INDIGO_CONCRETE_POWDER);
//                        entries.add(ModBlocks.INDIGO_TERRACOTTA);
//                        entries.add(ModBlocks.INDIGO_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.INDIGO_STAINED_GLASS);
//                        entries.add(ModBlocks.INDIGO_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.INDIGO_BED);
//                        entries.add(ModBlocks.INDIGO_BANNER);
//                        entries.add(ModBlocks.INDIGO_SHULKER_BOX);
//                        entries.add(ModBlocks.INDIGO_CANDLE);
//                    }).build());
//
//    public static ItemGroup BANANA = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "banana"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".banana"))
//                    .icon(() -> new ItemStack(ModItems.BANANA_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.BANANA_DYE);
//                        entries.add(ModBlocks.BANANA_WOOL);
//                        entries.add(ModBlocks.BANANA_CARPET);
//                        entries.add(ModBlocks.BANANA_CONCRETE);
//                        entries.add(ModBlocks.BANANA_CONCRETE_POWDER);
//                        entries.add(ModBlocks.BANANA_TERRACOTTA);
//                        entries.add(ModBlocks.BANANA_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.BANANA_STAINED_GLASS);
//                        entries.add(ModBlocks.BANANA_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.BANANA_BED);
//                        entries.add(ModBlocks.BANANA_BANNER);
//                        entries.add(ModBlocks.BANANA_SHULKER_BOX);
//                        entries.add(ModBlocks.BANANA_CANDLE);
//                    }).build());
//
//    public static ItemGroup CERULEAN = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "cerulean"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".cerulean"))
//                    .icon(() -> new ItemStack(ModItems.CERULEAN_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.CERULEAN_DYE);
//                        entries.add(ModBlocks.CERULEAN_WOOL);
//                        entries.add(ModBlocks.CERULEAN_CARPET);
//                        entries.add(ModBlocks.CERULEAN_CONCRETE);
//                        entries.add(ModBlocks.CERULEAN_CONCRETE_POWDER);
//                        entries.add(ModBlocks.CERULEAN_TERRACOTTA);
//                        entries.add(ModBlocks.CERULEAN_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.CERULEAN_STAINED_GLASS);
//                        entries.add(ModBlocks.CERULEAN_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.CERULEAN_BED);
//                        entries.add(ModBlocks.CERULEAN_BANNER);
//                        entries.add(ModBlocks.CERULEAN_SHULKER_BOX);
//                        entries.add(ModBlocks.CERULEAN_CANDLE);
//                    }).build());
//
//    public static ItemGroup ACORN = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "acorn"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".acorn"))
//                    .icon(() -> new ItemStack(ModItems.ACORN_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.ACORN_DYE);
//                        entries.add(ModBlocks.ACORN_WOOL);
//                        entries.add(ModBlocks.ACORN_CARPET);
//                        entries.add(ModBlocks.ACORN_CONCRETE);
//                        entries.add(ModBlocks.ACORN_CONCRETE_POWDER);
//                        entries.add(ModBlocks.ACORN_TERRACOTTA);
//                        entries.add(ModBlocks.ACORN_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.ACORN_STAINED_GLASS);
//                        entries.add(ModBlocks.ACORN_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.ACORN_BED);
//                        entries.add(ModBlocks.ACORN_BANNER);
//                        entries.add(ModBlocks.ACORN_SHULKER_BOX);
//                        entries.add(ModBlocks.ACORN_CANDLE);
//                    }).build());
//
//    public static ItemGroup MAUVE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "mauve"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".mauve"))
//                    .icon(() -> new ItemStack(ModItems.MAUVE_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.MAUVE_DYE);
//                        entries.add(ModBlocks.MAUVE_WOOL);
//                        entries.add(ModBlocks.MAUVE_CARPET);
//                        entries.add(ModBlocks.MAUVE_CONCRETE);
//                        entries.add(ModBlocks.MAUVE_CONCRETE_POWDER);
//                        entries.add(ModBlocks.MAUVE_TERRACOTTA);
//                        entries.add(ModBlocks.MAUVE_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.MAUVE_STAINED_GLASS);
//                        entries.add(ModBlocks.MAUVE_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.MAUVE_BED);
//                        entries.add(ModBlocks.MAUVE_BANNER);
//                        entries.add(ModBlocks.MAUVE_SHULKER_BOX);
//                        entries.add(ModBlocks.MAUVE_CANDLE);
//                    }).build());
//
//    public static ItemGroup MAROON = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "maroon"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".maroon"))
//                    .icon(() -> new ItemStack(ModItems.MAROON_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.MAROON_DYE);
//                        entries.add(ModBlocks.MAROON_WOOL);
//                        entries.add(ModBlocks.MAROON_CARPET);
//                        entries.add(ModBlocks.MAROON_CONCRETE);
//                        entries.add(ModBlocks.MAROON_CONCRETE_POWDER);
//                        entries.add(ModBlocks.MAROON_TERRACOTTA);
//                        entries.add(ModBlocks.MAROON_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.MAROON_STAINED_GLASS);
//                        entries.add(ModBlocks.MAROON_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.MAROON_BED);
//                        entries.add(ModBlocks.MAROON_BANNER);
//                        entries.add(ModBlocks.MAROON_SHULKER_BOX);
//                        entries.add(ModBlocks.MAROON_CANDLE);
//                    }).build());
//
//    public static ItemGroup GRAPE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "grape"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".grape"))
//                    .icon(() -> new ItemStack(ModItems.GRAPE_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.GRAPE_DYE);
//                        entries.add(ModBlocks.GRAPE_WOOL);
//                        entries.add(ModBlocks.GRAPE_CARPET);
//                        entries.add(ModBlocks.GRAPE_CONCRETE);
//                        entries.add(ModBlocks.GRAPE_CONCRETE_POWDER);
//                        entries.add(ModBlocks.GRAPE_TERRACOTTA);
//                        entries.add(ModBlocks.GRAPE_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.GRAPE_STAINED_GLASS);
//                        entries.add(ModBlocks.GRAPE_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.GRAPE_BED);
//                        entries.add(ModBlocks.GRAPE_BANNER);
//                        entries.add(ModBlocks.GRAPE_SHULKER_BOX);
//                        entries.add(ModBlocks.GRAPE_CANDLE);
//                    }).build());
//
//    public static ItemGroup NAVY = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "navy"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".navy"))
//                    .icon(() -> new ItemStack(ModItems.NAVY_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.NAVY_DYE);
//                        entries.add(ModBlocks.NAVY_WOOL);
//                        entries.add(ModBlocks.NAVY_CARPET);
//                        entries.add(ModBlocks.NAVY_CONCRETE);
//                        entries.add(ModBlocks.NAVY_CONCRETE_POWDER);
//                        entries.add(ModBlocks.NAVY_TERRACOTTA);
//                        entries.add(ModBlocks.NAVY_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.NAVY_STAINED_GLASS);
//                        entries.add(ModBlocks.NAVY_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.NAVY_BED);
//                        entries.add(ModBlocks.NAVY_BANNER);
//                        entries.add(ModBlocks.NAVY_SHULKER_BOX);
//                        entries.add(ModBlocks.NAVY_CANDLE);
//                    }).build());
//
//    public static ItemGroup SAP = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "sap"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".sap"))
//                    .icon(() -> new ItemStack(ModItems.SAP_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.SAP_DYE);
//                        entries.add(ModBlocks.SAP_WOOL);
//                        entries.add(ModBlocks.SAP_CARPET);
//                        entries.add(ModBlocks.SAP_CONCRETE);
//                        entries.add(ModBlocks.SAP_CONCRETE_POWDER);
//                        entries.add(ModBlocks.SAP_TERRACOTTA);
//                        entries.add(ModBlocks.SAP_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.SAP_STAINED_GLASS);
//                        entries.add(ModBlocks.SAP_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.SAP_BED);
//                        entries.add(ModBlocks.SAP_BANNER);
//                        entries.add(ModBlocks.SAP_SHULKER_BOX);
//                        entries.add(ModBlocks.SAP_CANDLE);
//                    }).build());
//
//    public static ItemGroup AMBER = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "amber"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".amber"))
//                    .icon(() -> new ItemStack(ModItems.AMBER_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.AMBER_DYE);
//                        entries.add(ModBlocks.AMBER_WOOL);
//                        entries.add(ModBlocks.AMBER_CARPET);
//                        entries.add(ModBlocks.AMBER_CONCRETE);
//                        entries.add(ModBlocks.AMBER_CONCRETE_POWDER);
//                        entries.add(ModBlocks.AMBER_TERRACOTTA);
//                        entries.add(ModBlocks.AMBER_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.AMBER_STAINED_GLASS);
//                        entries.add(ModBlocks.AMBER_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.AMBER_BED);
//                        entries.add(ModBlocks.AMBER_BANNER);
//                        entries.add(ModBlocks.AMBER_SHULKER_BOX);
//                        entries.add(ModBlocks.AMBER_CANDLE);
//                    }).build());
//
//    public static ItemGroup SAGE = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "sage"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".sage"))
//                    .icon(() -> new ItemStack(ModItems.SAGE_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.SAGE_DYE);
//                        entries.add(ModBlocks.SAGE_WOOL);
//                        entries.add(ModBlocks.SAGE_CARPET);
//                        entries.add(ModBlocks.SAGE_CONCRETE);
//                        entries.add(ModBlocks.SAGE_CONCRETE_POWDER);
//                        entries.add(ModBlocks.SAGE_TERRACOTTA);
//                        entries.add(ModBlocks.SAGE_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.SAGE_STAINED_GLASS);
//                        entries.add(ModBlocks.SAGE_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.SAGE_BED);
//                        entries.add(ModBlocks.SAGE_BANNER);
//                        entries.add(ModBlocks.SAGE_SHULKER_BOX);
//                        entries.add(ModBlocks.SAGE_CANDLE);
//                    }).build());
//
//    public static ItemGroup VELVET = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "velvet"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".velvet"))
//                    .icon(() -> new ItemStack(ModItems.VELVET_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.VELVET_DYE);
//                        entries.add(ModBlocks.VELVET_WOOL);
//                        entries.add(ModBlocks.VELVET_CARPET);
//                        entries.add(ModBlocks.VELVET_CONCRETE);
//                        entries.add(ModBlocks.VELVET_CONCRETE_POWDER);
//                        entries.add(ModBlocks.VELVET_TERRACOTTA);
//                        entries.add(ModBlocks.VELVET_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.VELVET_STAINED_GLASS);
//                        entries.add(ModBlocks.VELVET_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.VELVET_BED);
//                        entries.add(ModBlocks.VELVET_BANNER);
//                        entries.add(ModBlocks.VELVET_SHULKER_BOX);
//                        entries.add(ModBlocks.VELVET_CANDLE);
//                    }).build());
//
//    public static ItemGroup MOLD = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "mold"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".mold"))
//                    .icon(() -> new ItemStack(ModItems.MOLD_DYE)).entries((displayContext, entries) -> {
//                        entries.add(ModItems.MOLD_DYE);
//                        entries.add(ModBlocks.MOLD_WOOL);
//                        entries.add(ModBlocks.MOLD_CARPET);
//                        entries.add(ModBlocks.MOLD_CONCRETE);
//                        entries.add(ModBlocks.MOLD_CONCRETE_POWDER);
//                        entries.add(ModBlocks.MOLD_TERRACOTTA);
//                        entries.add(ModBlocks.MOLD_GLAZED_TERRACOTTA);
//                        entries.add(ModBlocks.MOLD_STAINED_GLASS);
//                        entries.add(ModBlocks.MOLD_STAINED_GLASS_PANE);
//                        entries.add(ModBlocks.MOLD_BED);
//                        entries.add(ModBlocks.MOLD_BANNER);
//                        entries.add(ModBlocks.MOLD_SHULKER_BOX);
//                        entries.add(ModBlocks.MOLD_CANDLE);
//                    }).build());
//
//    public static ItemGroup ADDITIONS = Registry.register(Registries.ITEM_GROUP, new Identifier(Super.MOD_ID, "additions"),
//            FabricItemGroup.builder().displayName(Text.translatable("itemgroup." + Super.MOD_ID + ".additions"))
//                    .icon(() -> new ItemStack(ModBlocks.ALL_CORRUGATED_IRON_BLOCKS.get(0).asItem())).entries((displayContext, entries) -> {
//                        for(Block block : ModBlocks.ALL_CORRUGATED_IRON_BLOCKS) { entries.add(block.asItem()); }
//                        for(Block block : ModBlocks.ALL_MUCKTUFF_BLOCKS) { entries.add(block.asItem()); }
//                    }).build());
}