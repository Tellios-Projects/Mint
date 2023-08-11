//package net.leafenzo.mint.mixin;
//import com.google.common.collect.Maps;
//import net.fabricmc.fabric.api.datagen.v1.loot.FabricBlockLootTableGenerator;
//import net.minecraft.data.server.loottable.BlockLootTableGenerator;
//import net.minecraft.data.server.loottable.EntityLootTableGenerator;
//import net.minecraft.entity.EntityType;
//import net.minecraft.loot.LootTable;
//import net.minecraft.util.Identifier;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import java.util.Map;
//@Mixin(EntityLootTableGenerator.class)
//public abstract class EntityLootTableGeneratorMixin {
//    @Shadow private final Map<EntityType<?>, Map<Identifier, LootTable.Builder>> lootTables = Maps.newHashMap();
//
//    @Shadow protected abstract void register(EntityType<?> entityType, LootTable.Builder lootTable);
//
//}
