package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class ModVillagerTrades {
    public static void registerVillagerTrades() {
        ModInit.LOGGER.debug("Registering villager trades for " + Super.MOD_ID);

//        TradeOfferHelper.registerVillagerOffers(VillagerProfession.CLERIC, 4,
//                factories -> {
//                    factories.add(((entity, random) -> new TradeOffer(
//                            new ItemStack(Items.EMERALD, 12),
//                            new ItemStack(ModBlocks., 4),
//                            14, 5, 0.15f))
//                    );
//                }
//        );

        TradeOfferHelper.registerWanderingTraderOffers(1,
                factories -> {
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 2), new ItemStack(ModBlocks.CORAL_ANEMONE, 1), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 5), new ItemStack(ModItems.PEACH_PIT, 1), 8, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 5), new ItemStack(ModItems.PEACH, 1), 8, 5, 0.15f)));

                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.FUCHSIA_DYE, 3), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.VELVET_DYE, 3), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.INDIGO_DYE, 3), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.MINT_DYE, 3), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModItems.SHAMROCK_DYE, 3), 12, 5, 0.15f)));

                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModBlocks.HIDCOTE_LAVENDER, 1), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModBlocks.HYPERICUM, 1), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModBlocks.WILD_MINT, 1), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ModBlocks.WAXCAP_MUSHROOM, 1), 12, 5, 0.15f)));
                }
        );
    }
}
