package net.leafenzo.mint.registries;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.leafenzo.mint.ElsDyeModInit;
import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.item.ElsDyeModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;

public class ElsDyeModVillagerTrades {
    public static void registerVillagerTrades() {
        ElsDyeModInit.LOGGER.debug("Registering villager trades for " + ElsDyeMod.MOD_ID);

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
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 2), new ItemStack(ElsDyeModBlocks.CORAL_ANEMONE, 1), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 5), new ItemStack(ElsDyeModItems.PEACH_PIT, 1), 8, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 5), new ItemStack(ElsDyeModItems.PEACH, 1), 8, 5, 0.15f)));

                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModItems.FUCHSIA_DYE, 3), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModItems.VELVET_DYE, 3), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModItems.INDIGO_DYE, 3), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModItems.MINT_DYE, 3), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModItems.SHAMROCK_DYE, 3), 12, 5, 0.15f)));

                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModBlocks.HIDCOTE_LAVENDER, 1), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModBlocks.HYPERICUM, 1), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModBlocks.WILD_MINT, 1), 12, 5, 0.15f)));
                    factories.add(((entity, random) -> new TradeOffer( new ItemStack(Items.EMERALD, 1), new ItemStack(ElsDyeModBlocks.WAXCAP_MUSHROOM, 1), 12, 5, 0.15f)));
                }
        );
    }
}
