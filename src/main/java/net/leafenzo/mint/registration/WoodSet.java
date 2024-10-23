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
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.entity.ModBoatEntity;
import net.leafenzo.mint.entity.ModEntityTypes;
import net.leafenzo.mint.item.custom.ModBoatItem;
import net.leafenzo.mint.registries.ModFabricRegistries;
import net.leafenzo.mint.registry.tag.ModTags;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

import static net.leafenzo.mint.registration.ModRegistryHelper.BlockRegistry;
import static net.leafenzo.mint.registration.ModRegistryHelper.ItemRegistry;

public class WoodSet {
    //<editor-fold desc ="Properties">
    private final List<Block> registeredBlocksList = new ArrayList<>();
    private final List<Item> registeredItemsList = new ArrayList<>();
    private final Identifier name;
    private final MapColor sideColor;
    private final MapColor topColor;
    private final MapColor leavesColor;
    private final WoodPreset woodPreset;
    private BlockSetType blockSetType;
    private PressurePlateBlock.ActivationRule pressurePlateActivationRule;
    private WoodType woodType;
    private final boolean isFlammable;
    private final boolean isUsedAsFuel;
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
    public WoodSet(Identifier name, MapColor sideColor, MapColor topColor, MapColor leavesColor, ModBoatEntity.ModBoat boatType, WoodPreset woodPreset, boolean hasMosaic, SaplingGenerator saplingGenerator, boolean isFlammable, boolean isUsedAsFuel) {
        this.woodPreset = woodPreset;
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        this.leavesColor = leavesColor;
        this.boatType = boatType;
        this.hasMosaic = hasMosaic;
        this.saplingGenerator = saplingGenerator;
        this.isFlammable = isFlammable;
        this.isUsedAsFuel = isUsedAsFuel;

        registerWoodSet();
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

        blockLogsTag = ModTags.Blocks.getOrCreateTag(this.getName() + "_logs");
        itemLogsTag = ModTags.Items.getOrCreateTag(this.getName() + "_logs");

//        // addToBuildingTab(getButtonBefore(), getLogBefore(), getSignBefore(), getBoatBefore(), this);
//        // for(Block item : this.getRegisteredBlocksList()) ItemGroupEvents.modifyEntriesEvent(ModItemGroups.NS_WOOD_ITEM_GROUP).register(entries -> entries.add(item));
//        // for(Item item : this.getRegisteredItemsList()) ItemGroupEvents.modifyEntriesEvent(ModItemGroups.NS_WOOD_ITEM_GROUP).register(entries -> entries.add(item));

        ModBlocks.WOODSETS.add(this);
    }
    //</editor-fold>
    //<editor-fold desc ="Registration">
    private Block registerBlock(String blockID, Block block) {
        Block b = BlockRegistry.registerBlock(blockID, block);
        registeredBlocksList.add(b);
        return b;
    }
    private Block registerBlockWithoutBlockItem(String blockID, Block block) {
        Block b = BlockRegistry.registerBlockWithoutBlockItem(blockID, block);
        registeredBlocksList.add(b);
        return b;
    }
    public Item registerItem(String name, Item item){
        Item i = ItemRegistry.registerItem(name, item);
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
        return new PillarBlock(FabricBlockSettings.copyOf(this.getBase()).mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).instrument(Instrument.BASS).strength(2.0F).sounds(this.woodType().soundType()));
    }
    private Block registerLogBlock() {
        Block b = registerBlock(getLogName(), createLogBlock(this.getTopColor(), this.getSideColor()));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) {
            ModFabricRegistries.registerFlammable(b, 5, 5);
            ModBlocks.LOGS_THAT_BURN.add(b);
        }
        else {
            ModBlocks.LOGS.add(b);
        }
        return b;
    }
    private Block registerStrippedLogBlock() {
        Block b = registerBlock("stripped_" + getLogName(), createLogBlock(this.getTopColor(), this.getTopColor()));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) {
            ModFabricRegistries.registerFlammable(b, 5, 5);
            ModBlocks.LOGS_THAT_BURN.add(b);
        }
        else {
            ModBlocks.LOGS.add(b);
        }
        return b;
    }
    private Block registerWoodBlock() {
        Block b = registerBlock(getWoodName(), createLogBlock(this.getSideColor(), this.getSideColor()));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) {
            ModFabricRegistries.registerFlammable(b, 5, 5);
            ModBlocks.LOGS_THAT_BURN.add(b);
        }
        else {
            ModBlocks.LOGS.add(b);
        }
        return b;
    }
    private Block registerStrippedWoodBlock() {
        Block b = registerBlock("stripped_" + getWoodName(), createLogBlock(this.getTopColor(), this.getTopColor()));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) {
            ModFabricRegistries.registerFlammable(b, 5, 5);
            ModBlocks.LOGS_THAT_BURN.add(b);
        }
        else {
            ModBlocks.LOGS.add(b);
        }
        return b;
    }
    private Block registerLeavesBlock() {
        Block b = registerBlock(this.getName() + "_leaves", new LeavesBlock(FabricBlockSettings.copy(getBaseLeaves()).mapColor(leavesColor)));
        ModFabricRegistries.registerCompostable(b, 0.3f);
        if(isFlammable) {
            ModFabricRegistries.registerFlammable(b, 60, 30);
        }
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModBlocks.HAS_FOLIAGE_COLOR_PROVIDER.add(b);
        ModBlocks.LEAVES.add(b);
        return b;
    }
    private Block registerLeavesBlock(String prefix) {
        Block b = registerBlock(prefix + this.getName() + "_leaves", new LeavesBlock(FabricBlockSettings.copy(getBaseLeaves()).mapColor(leavesColor)));
        ModFabricRegistries.registerCompostable(b, 0.3f);
        if(isFlammable) {
            ModFabricRegistries.registerFlammable(b, 60, 30);
        }
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModBlocks.HAS_FOLIAGE_COLOR_PROVIDER.add(b);
        ModBlocks.LEAVES.add(b);
        return b;
    }
    private Block registerPlanksBlock(){
        Block b = registerBlock(this.getName() + "_planks", new Block(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) { ModFabricRegistries.registerFlammable(b, 20, 5); }
        ModBlocks.PLANKS.add(b);
        return b;
    }
    private Block registerStairsBlock(){
        Block b = registerBlock(this.getName() + "_stairs", new StairsBlock(getBase().getDefaultState(), FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) { ModFabricRegistries.registerFlammable(b, 20, 5); }
        ModBlocks.WOODEN_STAIRS.add(b);
        return b;
    }
    private Block registerSlabBlock(){
        Block b = registerBlock(this.getName() + "_slab", new SlabBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 150); }
        if(isFlammable) { ModFabricRegistries.registerFlammable(b, 20, 5); }
        ModBlocks.WOODEN_SLABS.add(b);
        return b;
    }
    private Block registerMosaicBlock(){
        Block b = registerBlock(this.getName() + "_mosaic", new Block(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) { ModFabricRegistries.registerFlammable(b, 20, 5); }
        return b;
    }
    private Block registerMosaicStairsBlock(){
        Block b = registerBlock(this.getName() + "_mosaic_stairs", new StairsBlock(getBase().getDefaultState(), FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) { ModFabricRegistries.registerFlammable(b, 20, 5); }
        return b;
    }
    private Block registerMosaicSlabBlock(){
        Block b = registerBlock(this.getName() + "_mosaic_slab", new SlabBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 150); }
        if(isFlammable) { ModFabricRegistries.registerFlammable(b, 20, 5); }
        return b;
    }
    private Block registerFenceBlock(){
        Block b = registerBlock(this.getName() + "_fence", new FenceBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) { ModFabricRegistries.registerFlammable(b, 20, 5); }
        ModBlocks.WOODEN_FENCES.add(b);
        return b;
    }
    private Block registerFenceGateBlock(){
        Block b = registerBlock(this.getName() + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getWoodType()));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        if(isFlammable) { ModFabricRegistries.registerFlammable(b, 20, 5); }
        ModBlocks.FENCE_GATES.add(b);
        return b;
    }
    private Block createPressurePlate(){
        Block b = registerBlock(this.getName() + "_pressure_plate", new PressurePlateBlock(this.pressurePlateActivationRule, FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType()));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        ModBlocks.WOODEN_PRESSURE_PLATES.add(b);
        return b;
    }
    private Block registerButtonBlock(){
        Block b = registerBlock(this.getName() + "_button", new ButtonBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType(), 30, true));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 100); }
        ModBlocks.WOODEN_BUTTONS.add(b);
        return b;
    }
    private Block registerDoorBlock(){
        Block b = registerBlock(this.getName() + "_door", new DoorBlock(FabricBlockSettings.copy(getBase()).burnable().sounds(getBlockSetType().soundType()).nonOpaque().mapColor(getTopColor()), this.getBlockSetType()));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 200); }
        ModBlocks.WOODEN_DOORS.add(b);
        return b;
    }
    private Block registerTrapdoorBlock() {
        Block b = registerBlock(this.getName() + "_trapdoor", new TrapdoorBlock(FabricBlockSettings.copy(getBase()).sounds(getBlockSetType().soundType()).nonOpaque().mapColor(getTopColor()), this.getBlockSetType()));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(b, 300); }
        ModBlocks.WOODEN_TRAPDOORS.add(b);
        return b;
    }
    private Block registerSignBlock(){
        Block b = registerBlockWithoutBlockItem(this.getName() + "_sign", new SignBlock(FabricBlockSettings.copy(getSignBase()).mapColor(this.getTopColor()), this.getWoodType()));
        ModBlocks.SIGNS.add(b);
        return b;
    }
    private Block registerWallSignBlock(){
        Block b = registerBlockWithoutBlockItem(this.getName() + "_wall_sign", new WallSignBlock(FabricBlockSettings.copy(this.getSignBase()).mapColor(this.getTopColor()).dropsLike(this.getSign()), this.getWoodType()));
        ModBlocks.SIGNS.add(b);
        return b;
    }
    private Block registerHangingSignBlock(){
        Block b = registerBlockWithoutBlockItem(this.getName() + "_hanging_sign", new HangingSignBlock(FabricBlockSettings.copy(this.getHangingSignBase()).mapColor(this.getTopColor()), this.getWoodType()));
        ModBlocks.SIGNS.add(b);
        return b;
    }
    private Block registerWallHangingSignBlock(){
        Block b = registerBlockWithoutBlockItem(this.getName() + "_wall_hanging_sign", new WallHangingSignBlock(FabricBlockSettings.copy(this.getHangingSignBase()).mapColor(this.getTopColor()).dropsLike(this.getHangingSign()), this.getWoodType()));
        ModBlocks.SIGNS.add(b);
        return b;
    }
    public Block registerSaplingBlock(SaplingGenerator saplingGenerator) {
        Block b = registerBlock(this.getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
        // Saplings are not ever flammable
        ModFabricRegistries.registerCompostable(b, 0.3f);
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModBlocks.SAPLINGS.add(b);
        return b;
    }
    public Block registerSaplingBlock(String prefix, SaplingGenerator saplingGenerator) {
        Block b = registerBlock(prefix + this.getName() + "_sapling", new SaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.SPRUCE_SAPLING)));
        CompostingChanceRegistry.INSTANCE.add(b, 0.3F);
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModBlocks.SAPLINGS.add(b);
        return b;
    }
    public Block registerPottedSaplingBlock(Block sapling) {
        Block b = registerBlockWithoutBlockItem("potted_" + this.getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModBlocks.FLOWER_POT_FROM_BLOCK.put(sapling, (FlowerPotBlock) b);
        return b;
    }
    public Block registerPottedSaplingBlock(String prefix, Block sapling) {
        Block b = registerBlockWithoutBlockItem("potted_" + prefix + this.getName() + "_sapling", new FlowerPotBlock(sapling, FabricBlockSettings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
        ModBlocks.RENDER_LAYER_CUTOUT_MIPPED.add(b);
        ModBlocks.FLOWER_POT_FROM_BLOCK.put(sapling, (FlowerPotBlock) b);
        return b;
    }
    private Item registerSignItem(Block sign, Block wallSign){
        Item i = registerItem(this.getName() + "_sign", new SignItem(new FabricItemSettings().maxCount(16), sign, wallSign));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(i, 200); }
        ItemRegistry.SIGN_ITEMS.add(i);
        return i;
    }
    private Item registerHangingSignItem(Block hangingSign, Block hangingWallSign){
        Item i = registerItem(this.getName() + "_hanging_sign", new HangingSignItem(hangingSign, hangingWallSign, new FabricItemSettings().maxCount(16)));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(i, 200); }
        ItemRegistry.HANGING_SIGN_ITEMS.add(i);
        return i;
    }
    private Item registerBoatItem(){
        Item i = registerItem(this.getName() + "_boat", new ModBoatItem(false, this.getBoatType(), new FabricItemSettings().maxCount(1)));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(i, 1200); }
        ItemRegistry.BOAT_ITEMS.add(i);
        return i;
    }
    private Item registerChestBoatItem(){
        Item i = registerItem(this.getName() + "_chest_boat", new ModBoatItem(true, this.getBoatType(), new  FabricItemSettings().maxCount(1)));
        if(isUsedAsFuel) { ModFabricRegistries.registerFuel(i, 1200); }
        ItemRegistry.CHEST_BOAT_ITEMS.add(i);
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
        return this.getWoodPreset() != WoodPreset.NO_SAPLING || this.getWoodPreset() != WoodPreset.NO_TREE;
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
        BAMBOO,
        NO_TREE
    }
    //</editor-fold>
}