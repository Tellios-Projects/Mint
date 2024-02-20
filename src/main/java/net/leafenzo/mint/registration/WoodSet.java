/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/remappedSrc/net/hibiscus/naturespirit/util/WoodSet.java
 */

package net.leafenzo.mint.registration;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.entity.ModBoatEntity;
import net.leafenzo.mint.entity.ModEntityTypes;
import net.leafenzo.mint.item.ModBoatItem;
import net.leafenzo.mint.item.ModItems;
import net.leafenzo.mint.registries.ModFabricRegistries;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Debug;

import java.util.ArrayList;
import java.util.List;

public class WoodSet {
    //<editor-fold desc ="Properties">
    private final List<Block> registeredBlocksList = new ArrayList<>();
    private final List<Item> registeredItemsList = new ArrayList<>();
    private final Identifier name;
    private final MapColor sideColor;
    private final MapColor topColor;
    private final WoodPreset woodPreset;
    private BlockSetType blockSetType;
    private PressurePlateBlock.ActivationRule pressurePlateActivationRule;
    private WoodType woodType;
    private Block log;
    private Block strippedLog;
    private Block wood;
    private Block strippedWood;
    private Block leaves;
    private Block sapling;
    private Block pottedSapling;
    private Block planks;
    private Block stairs;
    private Block slab;
    private Block mosaic;
    private Block mosaicStairs;
    private Block mosaicSlab;
    private Block fence;
    private Block fenceGate;
    private Block pressurePlate;
    private Block button;
    private Block door;
    private Block trapDoor;
    private Block sign;
    private Block wallSign;
    private Block hangingSign;
    private Block hangingWallSign;
    private Item signItem;
    private Item hangingSignItem;
    private Item boatItem;
    private Item chestBoatItem;
    private TagKey <Item> itemLogsTag;
    private TagKey <Block> blockLogsTag;
    private final ModBoatEntity.ModBoat boatType;
    private EntityType<BoatEntity> boatEntityType;
    private EntityType<BoatEntity> chestBoatEntityType;
    private final SaplingGenerator saplingGenerator;
//    private final Optional<RegistryKey<ConfiguredFeature<?, ?>>> configuredFeature;
//    private final Optional<RegistryKey<ConfiguredFeature<?, ?>>> configuredFeature2;
    private boolean hasLargeTree;
    private final boolean hasMosaic;
    //</editor-fold>
    //<editor-fold desc ="Constructors">
    public WoodSet(Identifier name, MapColor sideColor, MapColor topColor, ModBoatEntity.ModBoat boatType, WoodPreset woodPreset, boolean hasMosaic, SaplingGenerator saplingGenerator){
        this.woodPreset = woodPreset;
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        this.boatType = boatType;
        this.hasMosaic = hasMosaic;
        this.saplingGenerator = saplingGenerator;
        registerWoodSet();
        ModBlocks.WOODSETS.add(this);
    }
    //</editor-fold>
    //<editor-fold desc ="Getters">
    private BlockSetType woodType() {
        return this.blockSetType;
    }
    public Identifier getNameID() {
        return name;
    }
    public String getName() {
        return name.getPath();
    }
    public String getModID() {
        return name.getNamespace();
    }
    public BlockSetType getBlockSetType() {
        return this.blockSetType;
    }
    public PressurePlateBlock.ActivationRule getPressurePlateActivationRule() {
        return this.pressurePlateActivationRule;
    }
    public WoodPreset getWoodPreset() {
        return woodPreset;
    }
    public MapColor getSideColor() {
        return sideColor;
    }
    public MapColor getTopColor() {
        return topColor;
    }
    public WoodType getWoodType() {
        return woodType;
    }
    public Block getButton() {
        return button;
    }
    public Block getFence() {
        return fence;
    }
    public Block getPlanks() {
        return planks;
    }
    public Block getSlab() {
        return slab;
    }
    public Block getFenceGate() {
        return fenceGate;
    }
    public Block getStairs() {
        return stairs;
    }
    public Block getDoor() {
        return door;
    }
    public Block getHangingSign() {
        return hangingSign;
    }
    public Block getHangingWallSign() {
        return hangingWallSign;
    }
    public Block getPressurePlate() {
        return pressurePlate;
    }
    public Block getSign() {
        return sign;
    }
    public Block getTrapDoor() {
        return trapDoor;
    }
    public Block getWallSign() {
        return wallSign;
    }
    public Item getHangingSignItem() {
        return hangingSignItem;
    }
    public Item getSignItem() {
        return signItem;
    }
    public Item getBoatItem() {
        return boatItem;
    }
    public Item getChestBoatItem() {
        return chestBoatItem;
    }
    public Block getLog() {
        return log;
    }
    public Block getStrippedLog() {
        return strippedLog;
    }
    public Block getWood() {
        return wood;
    }
    public Block getStrippedWood() {
        return strippedWood;
    }
    public Block getMosaic() {
        return mosaic;
    }
    public Block getMosaicStairs() {
        return mosaicStairs;
    }
    public Block getMosaicSlab() {
        return mosaicSlab;
    }
    public Block getLeaves() {return leaves;}
    public Block getSapling() {return sapling;}
    public Block getPottedSapling() {return pottedSapling;}
    public ModBoatEntity.ModBoat getBoatType() {
        return boatType;
    }
    public EntityType <BoatEntity> getBoatEntityType() {
        return boatEntityType;
    }
    public EntityType <BoatEntity> getChestBoatEntityType() {
        return chestBoatEntityType;
    }
    public TagKey <Item> getItemLogsTag() {
        return itemLogsTag;
    }
    public TagKey <Block> getBlockLogsTag() {
        return blockLogsTag;
    }
    public List<Block> getRegisteredBlocksList() {
        return registeredBlocksList;
    }
    public List<Item> getRegisteredItemsList() {
        return registeredItemsList;
    }
    //</editor-fold>
    //<editor-fold desc ="Main">
    private void registerWoodSet(){
        blockSetType = registerBlockSetType();
        woodType = new WoodTypeBuilder().register(this.getNameID(), this.getBlockSetType());

        log = registerLogBlock();
        strippedLog = registerStrippedLogBlock();
            StrippableBlockRegistry.register(log, strippedLog);

        wood = registerWoodBlock();
        strippedWood = registerStrippedWoodBlock();
            StrippableBlockRegistry.register(wood, strippedWood);

        if (this.hasDefaultLeaves()){
            leaves = registerLeavesBlock();

            if (this.hasDefaultSapling()) {
                sapling = registerSaplingBlock(saplingGenerator);
                pottedSapling = registerPottedSaplingBlock(this.getSapling());
//                ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> entries.addAfter(this.getSaplingBefore(), this.getSapling().asItem()));
//                SaplingHashMap.put(this.getName(), new Block[]{this.getSapling(), this.getPottedSapling()});
            }
        }

//        if (this.hasMosaic()){
//            mosaic = registerMosaicBlock();
//            mosaicStairs = registerMosaicStairsBlock();
//            mosaicSlab = registerMosaicSlabBlock();
//        }

        planks = registerPlanksBlock();
        stairs = registerStairsBlock();
        slab = registerSlabBlock();
        fence = registerFenceBlock();
        fenceGate = registerFenceGateBlock();

        this.pressurePlateActivationRule = PressurePlateBlock.ActivationRule.EVERYTHING;
        pressurePlate = createPressurePlate();

        button = registerButtonBlock();
        door = registerDoorBlock();
        trapDoor = registerTrapdoorBlock();

        sign = registerSignBlock();
        wallSign = registerWallSignBlock();
        signItem = registerSignItem(sign, wallSign);
        hangingSign = registerHangingSignBlock();
        hangingWallSign = registerWallHangingSignBlock();
        hangingSignItem = registerHangingSignItem(hangingSign, hangingWallSign);

        boatEntityType = ModEntityTypes.registerEntityType(this.getName() + "_boat", ModEntityTypes.createBoatType(false, this.getBoatType()));
        chestBoatEntityType = ModEntityTypes.registerEntityType(this.getName() + "_chest_boat", ModEntityTypes.createBoatType(true, this.getBoatType()));
        boatItem = registerBoatItem();
        chestBoatItem = registerChestBoatItem();

//        // blockLogsTag = TagKey.of(RegistryKeys.BLOCK, new Identifier(this.getModID(), this.getName() + "_logs"));
//        // itemLogsTag = TagKey.of(RegistryKeys.ITEM, new Identifier(this.getModID(), this.getName() + "_logs"));
//        // addToBuildingTab(getButtonBefore(), getLogBefore(), getSignBefore(), getBoatBefore(), this);
//        // for(Block item : this.getRegisteredBlocksList()) ItemGroupEvents.modifyEntriesEvent(ModItemGroups.NS_WOOD_ITEM_GROUP).register(entries -> entries.add(item));
//        // for(Item item : this.getRegisteredItemsList()) ItemGroupEvents.modifyEntriesEvent(ModItemGroups.NS_WOOD_ITEM_GROUP).register(entries -> entries.add(item));
    }
    //</editor-fold>
    //<editor-fold desc ="Registration">
    private Block registerBlock(String blockID, Block block) {
        Block b = ModBlocks.registerBlock(blockID, block);
        registeredBlocksList.add(b);
        return b;
    }
    private Block registerBlockWithoutBlockItem(String blockID, Block block) {
        Block b = ModBlocks.registerBlockWithoutBlockItem(blockID, block);
        registeredBlocksList.add(b);
        return b;
    }
    public Item registerItem(String name, Item item){
        //        Item i = ModItems.registerItem(name, item); // Doesn't work for some reason.
        Item i = Registry.register(Registries.ITEM, new Identifier(Super.MOD_ID, name), (Item)item); //HUH??? why does that work? It's the same code! Maybe it's a load error issue?
        registeredItemsList.add(i);
        return i;
    }
    private BlockSetType registerBlockSetType(){
        if (this.woodPreset == WoodPreset.BAMBOO){
            return BlockSetTypeBuilder.copyOf(BlockSetType.BAMBOO).register(this.getNameID());
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            return BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).register(this.getNameID());
        }
        else if (this.woodPreset == WoodPreset.NETHER){
            return BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).register(this.getNameID());
        }
        else{
            return BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(this.getNameID());
        }
    }
    //</editor-fold>
    //<editor-fold desc ="Registration Functions">
    private PillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return new PillarBlock(AbstractBlock.Settings.create().mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(this.woodType().soundType()));
    }
    private Block registerLogBlock() {
        Block b = registerBlock(getLogName(), createLogBlock(this.getSideColor(), this.getTopColor()));
        ModFabricRegistries.registerFlammable(b, 5, 5);
        return b;
    }
    private Block registerStrippedLogBlock() {
        Block b = registerBlock("stripped_" + getLogName(), createLogBlock(this.getSideColor(), this.getTopColor()));
        ModFabricRegistries.registerFlammable(b, 5, 5);
        return b;
    }
    private Block registerWoodBlock() {
        Block b = registerBlock(getWoodName(), createLogBlock(this.getSideColor(), this.getSideColor()));
        ModFabricRegistries.registerFlammable(b, 5, 5);
        return b;
    }
    private Block registerStrippedWoodBlock() {
        Block b = registerBlock("stripped_" + getWoodName(), createLogBlock(this.getTopColor(), this.getTopColor()));
        ModFabricRegistries.registerFlammable(b, 5, 5);
        return b;
    }
    private Block registerLeavesBlock() {
        Block b = registerBlock(this.getName() + "_leaves", new LeavesBlock(FabricBlockSettings.copy(getBaseLeaves())));
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModFabricRegistries.registerCompostable(b, 0.3f);
        ModFabricRegistries.registerFlammable(b, 60, 30);
        ModBlocks.LEAVES.add(b);
        return b;
    }
    private Block registerLeavesBlock(String prefix) {
        Block b = registerBlock(prefix + this.getName() + "_leaves", new LeavesBlock(FabricBlockSettings.copy(getBaseLeaves())));
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModFabricRegistries.registerCompostable(b, 0.3f);
        ModFabricRegistries.registerFlammable(b, 60, 30);
        ModBlocks.LEAVES.add(b);
        return b;
    }
    private Block registerPlanksBlock(){
        Block b = registerBlock(this.getName() + "_planks", new Block(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        ModFabricRegistries.registerFlammable(b, 20, 5);
        ModFabricRegistries.registerFuel(b, 300);
        return b;
    }
    private Block registerStairsBlock(){
        Block b = registerBlock(this.getName() + "_stairs", new StairsBlock(getBase().getDefaultState(), FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        ModFabricRegistries.registerFlammable(b, 20, 5);
        ModFabricRegistries.registerFuel(b, 300);
        return b;
    }
    private Block registerSlabBlock(){
        Block b = registerBlock(this.getName() + "_slab", new SlabBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        ModFabricRegistries.registerFlammable(b, 20, 5);
        ModFabricRegistries.registerFuel(b, 150);
        return b;
    }
    private Block registerMosaicBlock(){
        Block b = registerBlock(this.getName() + "_mosaic", new Block(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        ModFabricRegistries.registerFlammable(b, 20, 5);
        ModFabricRegistries.registerFuel(b, 300);
        return b;
    }
    private Block registerMosaicStairsBlock(){
        Block b = registerBlock(this.getName() + "_mosaic_stairs", new StairsBlock(getBase().getDefaultState(), FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        ModFabricRegistries.registerFlammable(b, 20, 5);
        ModFabricRegistries.registerFuel(b, 300);
        return b;
    }
    private Block registerMosaicSlabBlock(){
        Block b = registerBlock(this.getName() + "_mosaic_slab", new SlabBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        ModFabricRegistries.registerFlammable(b, 20, 5);
        ModFabricRegistries.registerFuel(b, 150);
        return b;
    }
    private Block registerFenceBlock(){
        Block b = registerBlock(this.getName() + "_fence", new FenceBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        ModFabricRegistries.registerFlammable(b, 20, 5);
        ModFabricRegistries.registerFuel(b, 300);
        return b;
    }
    private Block registerFenceGateBlock(){
        Block b = registerBlock(this.getName() + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getWoodType()));
        ModFabricRegistries.registerFlammable(b, 20, 5);
        ModFabricRegistries.registerFuel(b, 300);
        return b;
    }
    private Block createPressurePlate(){
        Block b = registerBlock(this.getName() + "_pressure_plate", new PressurePlateBlock(this.pressurePlateActivationRule, FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType()));
//        ModFabricRegistries.registerFlammable(b, 20, 5); //TODO make sure this can ignite due to lava, but not fully burn. (as that's how it works in vanilla)
        ModFabricRegistries.registerFuel(b, 300);
        return b;
    }
    private Block registerButtonBlock(){
        Block b = registerBlock(this.getName() + "_button", new ButtonBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType(), 30, true));
        ModFabricRegistries.registerFuel(b, 100);
        return b;
    }
    private Block registerDoorBlock(){
        Block b = registerBlock(this.getName() + "_door", new DoorBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).nonOpaque().mapColor(getTopColor()), this.getBlockSetType()));
//        ModFabricRegistries.registerFlammable(b, 20, 5); //TODO make sure this can ignite due to lava, but not fully burn. (as that's how it works in vanilla)
        ModFabricRegistries.registerFuel(b, 200);
        return b;
    }
    private Block registerTrapdoorBlock() {
        Block b = registerBlock(this.getName() + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).nonOpaque().mapColor(getTopColor()), this.getBlockSetType()));
//        ModFabricRegistries.registerFlammable(b, 20, 5); //TODO make sure this can ignite due to lava, but not fully burn. (as that's how it works in vanilla)
        ModFabricRegistries.registerFuel(b, 300);
        return b;
    }
    private Block registerSignBlock(){
        Block b = registerBlockWithoutBlockItem(this.getName() + "_sign", new SignBlock(FabricBlockSettings.copy(getSignBase()).mapColor(this.getTopColor()), this.getWoodType()));
        // ModFabricRegistries.registerFlammable(b, 20, 5); //TODO make sure this can ignite due to lava, but not fully burn. (as that's how it works in vanilla)
        return b;
    }
    private Block registerWallSignBlock(){
        Block b = registerBlockWithoutBlockItem(this.getName() + "_wall_sign", new WallSignBlock(FabricBlockSettings.copy(this.getSignBase()).mapColor(this.getTopColor()).dropsLike(this.getSign()), this.getWoodType()));
        // ModFabricRegistries.registerFlammable(b, 20, 5); //TODO make sure this can ignite due to lava, but not fully burn. (as that's how it works in vanilla)
        return b;
    }
    private Block registerHangingSignBlock(){
        return registerBlockWithoutBlockItem(this.getName() + "_hanging_sign", new HangingSignBlock(FabricBlockSettings.copy(this.getHangingSignBase()).mapColor(this.getTopColor()), this.getWoodType()));
        //TODO make sure this can ignite due to lava, but not fully burn. (as that's how it works in vanilla)
    }
    private Block registerWallHangingSignBlock(){
        return registerBlockWithoutBlockItem(this.getName() + "_wall_hanging_sign", new WallHangingSignBlock(FabricBlockSettings.copy(this.getHangingSignBase()).mapColor(this.getTopColor()).dropsLike(this.getHangingSign()), this.getWoodType()));
        //TODO make sure this can ignite due to lava, but not fully burn. (as that's how it works in vanilla)
    }
    public Block registerSaplingBlock(SaplingGenerator saplingGenerator) {
        Block b = ModBlocks.registerBlock(this.getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
        ModFabricRegistries.registerCompostable(b, 0.3f);
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        return b;
    }
    public Block registerSaplingBlock(String prefix, SaplingGenerator saplingGenerator) {
        Block b = ModBlocks.registerBlock(prefix + this.getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
        CompostingChanceRegistry.INSTANCE.add(b, 0.3F);
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        return b;
    }
    public Block registerPottedSaplingBlock(Block sapling) {
        Block b = registerBlock("potted_" + this.getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModBlocks.FLOWER_POT_FROM_BLOCK.put(sapling, (FlowerPotBlock) b);
        return b;
    }
    public Block registerPottedSaplingBlock(String prefix, Block sapling) {
        Block b = registerBlock("potted_" + prefix + this.getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModBlocks.FLOWER_POT_FROM_BLOCK.put(sapling, (FlowerPotBlock) b);
        return b;
    }
    private Item registerSignItem(Block sign, Block wallSign){
        Item i = registerItem(this.getName() + "_sign", new SignItem(new FabricItemSettings().maxCount(16), sign, wallSign));
        ModFabricRegistries.registerFuel(i, 200);
        return i;
    }
    private Item registerHangingSignItem(Block hangingSign, Block hangingWallSign){
        Item i = registerItem(this.getName() + "_hanging_sign", new HangingSignItem(hangingSign, hangingWallSign, new FabricItemSettings().maxCount(16)));
        ModFabricRegistries.registerFuel(i, 200);
        return i;
    }
    private Item registerBoatItem(){
        Item i = registerItem(this.getName() + "_boat", new ModBoatItem(false, this.getBoatType(), new FabricItemSettings().maxCount(1)));
        ModFabricRegistries.registerFuel(i, 1200);
        return i;
    }
    private Item registerChestBoatItem(){
        Item i = registerItem(this.getName() + "_chest_boat", new ModBoatItem(true, this.getBoatType(), new  FabricItemSettings().maxCount(1)));
        ModFabricRegistries.registerFuel(i, 1200);
        return i;
    }
    //</editor-fold>
    //<editor-fold desc ="Special Getters">
    private String getWoodName(){
        String name;
        if (this.getWoodPreset() == WoodPreset.NETHER){
            name = this.getName() + "_hyphae";
        }
        else{
            name = this.getName() + "_wood";
        }
        return name;
    }
    private String getLogName(){
        String name;
        if (this.getWoodPreset() == WoodPreset.BAMBOO){
            name = this.getName() + "_block";
        }
        else if (this.getWoodPreset() == WoodPreset.NETHER){
            name = this.getName() + "_stem";
        }
        else{
            name = this.getName() + "_log";
        }
        return name;
    }
    private Block getBaseLeaves(){
        Block base;
        if (this.getWoodPreset() == WoodPreset.FANCY){
            base = Blocks.AZALEA_LEAVES;
        }
        else{
            base = Blocks.OAK_LEAVES;
        }
        return base;
    }
    private Block getBase(){
        Block base;
        if (this.getWoodPreset() == WoodPreset.BAMBOO){
            base = Blocks.BAMBOO_PLANKS;
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            base = Blocks.CHERRY_PLANKS;
        }
        else if (this.getWoodPreset() == WoodPreset.NETHER){
            base = Blocks.CRIMSON_PLANKS;
        }
        else{
            base = Blocks.OAK_PLANKS;
        }
        return base;
    }
    private Block getSignBase(){
        Block base;
        if (this.getWoodPreset() == WoodPreset.BAMBOO){
            base = Blocks.BAMBOO_SIGN;
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            base = Blocks.CHERRY_SIGN;
        }
        else if (this.getWoodPreset() == WoodPreset.NETHER){
            base = Blocks.CRIMSON_SIGN;
        }
        else{
            base = Blocks.OAK_SIGN;
        }
        return base;
    }
    private Block getHangingSignBase(){
        Block base;
        if (this.getWoodPreset() == WoodPreset.BAMBOO){
            base = Blocks.BAMBOO_HANGING_SIGN;
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            base = Blocks.CHERRY_HANGING_SIGN;
        }
        else if (this.getWoodPreset() == WoodPreset.NETHER){
            base = Blocks.CRIMSON_HANGING_SIGN;
        }
        else{
            base = Blocks.OAK_HANGING_SIGN;
        }
        return base;
    }
    public boolean hasDefaultLeaves(){
        return this.getWoodPreset() == WoodPreset.DEFAULT || this.getWoodPreset() == WoodPreset.FANCY || this.getWoodPreset() == WoodPreset.NO_SAPLING;
    }
    public boolean hasDefaultSapling() {
        return this.getWoodPreset() != WoodPreset.NO_SAPLING;
    }
    public boolean hasBark(){
        return this.getWoodPreset() != WoodPreset.BAMBOO;
    }
    public boolean hasMosaic(){
        return this.hasMosaic;
    }
    public enum WoodPreset {
        DEFAULT,
        NO_SAPLING,
        FANCY,
        NETHER,
        BAMBOO
    }
    //</editor-fold>
}