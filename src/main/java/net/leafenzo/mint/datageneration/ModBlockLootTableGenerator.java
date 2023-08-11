package net.leafenzo.mint.datageneration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.leafenzo.mint.block.MintCropBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BedPart;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.CopyNameLootFunction;
import net.minecraft.loot.function.CopyNbtLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.nbt.ContextLootNbtProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }
    public LootTable.Builder wildMintDrops(Block dropWithShears) {
        return BlockLootTableGenerator.dropsWithShears(dropWithShears, (LootPoolEntry.Builder)this.applyExplosionDecay(dropWithShears, ((LeafEntry.Builder)ItemEntry.builder(ModItems.MINT_SPRIG).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f))))));
    }
    @Override
    public void generate() {
        this.addDrop(ModBlocks.WILD_MINT, (Block block) -> this.wildMintDrops(ModBlocks.WILD_MINT));

        BlockStatePropertyLootCondition.Builder mintCropBuilder = BlockStatePropertyLootCondition.builder(ModBlocks.MINT_CROP).properties(StatePredicate.Builder.create().exactMatch(MintCropBlock.AGE, MintCropBlock.MAX_AGE));
        this.addDrop(ModBlocks.MINT_CROP, this.applyExplosionDecay(ModBlocks.MINT_CROP, LootTable.builder().pool(LootPool.builder().with(ItemEntry.builder(ModItems.MINT_SPRIG))).pool(LootPool.builder().conditionally(mintCropBuilder).with((LootPoolEntry.Builder<?>)((Object)ItemEntry.builder(ModItems.MINT_SPRIG).apply(ApplyBonusLootFunction.binomialWithBonusCount(Enchantments.FORTUNE, 0.5714286f, 3)))))));
        this.addDrop(ModBlocks.MINT_WOOL);
        this.addDrop(ModBlocks.MINT_CARPET);
        this.addDrop(ModBlocks.MINT_CONCRETE);
        this.addDrop(ModBlocks.MINT_CONCRETE_POWDER);
        this.addDrop(ModBlocks.MINT_SPRIG_BLOCK);
        this.addDropWithSilkTouch(ModBlocks.MINT_STAINED_GLASS);
        this.addDropWithSilkTouch(ModBlocks.MINT_STAINED_GLASS_PANE);
        this.addDrop(ModBlocks.MINT_BED, (block) -> this.dropsWithProperty(block, BedBlock.PART, BedPart.HEAD));

        this.addDrop(ModBlocks.MINT_BANNER, (Block block) -> this.bannerDrops((Block)block));
        //this.addDrop(ModBlocks.MINT_WALL_BANNER, (Block block) -> this.bannerDrops(ModBlocks.MINT_BANNER));

        this.addDrop(ModBlocks.MINT_SHULKER_BOX, (Block block) -> this.shulkerBoxDrops((Block)block));
        this.addDrop(ModBlocks.MINT_TERRACOTTA);
        this.addDrop(ModBlocks.MINT_GLAZED_TERRACOTTA);
        this.addDrop(ModBlocks.MINT_CANDLE, (Block block) -> this.candleDrops((Block)block));

        //this.addDrop(ModBlocks.BLAZE_ROD_BLOCK);
        //this.addDrop(ModBlocks.GRASS_CLIPPINGS_BLOCK, (Block block) -> this.drops((Block)block, block));
        //this.addDrop(ModBlocks.BOOK_BLOCK, (Block block) -> this.drops((Block)block, Items.BOOK, ConstantLootNumberProvider.create(9.0f)));
        //this.addDrop(ModBlocks.COMPRESSED_OAK_LEAVES, (Block block) -> this.leavesDrops((Block)block, Blocks.OAK_SAPLING, SAPLING_DROP_CHANCE));
        //this.addDropWithSilkTouch(ModBlocks.EGG_BLOCK);
    }
}
