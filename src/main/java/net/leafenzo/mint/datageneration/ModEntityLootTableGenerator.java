/*
/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 * Copyright 2023 Tellio's Projects
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * /
/*
 * https://github.com/QuiltMC/quilt-standard-libraries/blob/4ffee61397abfc74960a7cf8632511d0cc24c5ac/codeformat/FABRIC_MODIFIED_HEADER
 * /
 */
package net.leafenzo.mint.datageneration;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLootTableProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.fabricmc.fabric.impl.datagen.loot.FabricLootTableProviderImpl;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.MintCropBlock;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataWriter;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.data.server.loottable.EntityLootTableGenerator;
import net.minecraft.data.server.loottable.vanilla.VanillaEntityLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.TypeSpecificPredicate;
import net.minecraft.registry.Registries;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class ModEntityLootTableGenerator extends EntityLootTableGenerator implements FabricLootTableProvider {
    @Override
    public void generate() {
        this.register(EntityType.SHEEP, new Identifier(Super.MOD_ID + "entity/sheep/black"), createForSheep(ModBlocks.MINT_WOOL));
    }

    //private final Set<Identifier> excludedFromStrictValidation = new HashSet<>();
    //protected static final EntityPredicate.Builder NEEDS_ENTITY_ON_FIRE = EntityPredicate.Builder.create().flags(EntityFlagsPredicate.Builder.create().onFire(true).build());
    private static final Set<EntityType<?>> ENTITY_TYPES_IN_MISC_GROUP_TO_CHECK = ImmutableSet.of(EntityType.PLAYER, EntityType.ARMOR_STAND, EntityType.IRON_GOLEM, EntityType.SNOW_GOLEM, EntityType.VILLAGER);
    private final Map<EntityType<?>, Map<Identifier, LootTable.Builder>> lootTables = Maps.newHashMap();
    private final FeatureSet requiredFeatures;
    private final FeatureSet featureSet;
    private final FabricDataOutput output;
    protected ModEntityLootTableGenerator(FabricDataOutput dataOutput) {
        super(FeatureSet.empty(), FeatureSet.empty());
        requiredFeatures = FeatureSet.empty();
        featureSet = FeatureSet.empty();
        this.output = dataOutput;
    }
    protected static LootTable.Builder createForSheep(ItemConvertible item) {
        return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(ItemEntry.builder(item))).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with(LootTableEntry.builder(EntityType.SHEEP.getLootTableId())));
    }
    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> exporter) {
        this.generate();
        HashSet set = Sets.newHashSet();
        Registries.ENTITY_TYPE.streamEntries().forEach(entityType -> {
            EntityType entityType2 = (EntityType)entityType.value();
            if (!entityType2.isEnabled(this.requiredFeatures)) {
                return;
            }
            if (this.shouldCheck(entityType2)) {
                Map<Identifier, LootTable.Builder> map = this.lootTables.remove(entityType2);
                Identifier identifier = entityType2.getLootTableId();
                if (!(identifier.equals(LootTables.EMPTY) || !entityType2.isEnabled(this.featureSet) || map != null && map.containsKey(identifier))) {
                    throw new IllegalStateException(String.format(Locale.ROOT, "Missing loottable '%s' for '%s'", identifier, entityType.registryKey().getValue()));
                }
                if (map != null) {
                    map.forEach((lootTableId, lootTableBuilder) -> {
                        if (!set.add(lootTableId)) {
                            throw new IllegalStateException(String.format(Locale.ROOT, "Duplicate loottable '%s' for '%s'", lootTableId, entityType.registryKey().getValue()));
                        }
                        exporter.accept((Identifier)lootTableId, (LootTable.Builder)lootTableBuilder);
                    });
                }
            } else {
                Map<Identifier, LootTable.Builder> map = this.lootTables.remove(entityType2);
                if (map != null) {
                    throw new IllegalStateException(String.format(Locale.ROOT, "Weird loottables '%s' for '%s', not a LivingEntity so should not have loot", map.keySet().stream().map(Identifier::toString).collect(Collectors.joining(",")), entityType.registryKey().getValue()));
                }
            }
        });
//        if (!this.lootTables.isEmpty()) {
//            throw new IllegalStateException("Created loot tables for entities not supported by datapack: " + this.lootTables.keySet());
//        }
    }

    private static boolean shouldCheck(EntityType<?> entityType) {
        return ENTITY_TYPES_IN_MISC_GROUP_TO_CHECK.contains(entityType) || entityType.getSpawnGroup() != SpawnGroup.MISC;
    }

    protected LootCondition.Builder killedByFrog() {
        return DamageSourcePropertiesLootCondition.builder(DamageSourcePredicate.Builder.create().sourceEntity(EntityPredicate.Builder.create().type(EntityType.FROG)));
    }

    protected LootCondition.Builder killedByFrog(FrogVariant variant) {
        return DamageSourcePropertiesLootCondition.builder(DamageSourcePredicate.Builder.create().sourceEntity(EntityPredicate.Builder.create().type(EntityType.FROG).typeSpecific(TypeSpecificPredicate.frog(variant))));
    }

    protected void register(EntityType<?> entityType, LootTable.Builder lootTable) {
        this.register(entityType, entityType.getLootTableId(), lootTable);
    }

    protected void register(EntityType<?> entityType, Identifier entityId, LootTable.Builder lootTable) {
        this.lootTables.computeIfAbsent(entityType, type -> new HashMap()).put(entityId, lootTable);
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        return FabricLootTableProviderImpl.run(writer, this, LootContextTypes.ENTITY, output);
    }

    @Override
    public String getName() {
        return "Entity Loot Tables";
    }
}
