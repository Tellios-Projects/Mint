/*
Copyright 2021 ReMod Studios

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
// https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/LICENSE#L4

package net.leafenzo.mint.mixin;
import com.google.common.collect.Maps;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity implements Shearable {
   // For now new sheep loot tables must be manually copied and renamed for each new dye in data/mint/loot_tables/sheep/ (unless we figure out how to datagen with the EntityLootTableGenerator) -Leah
   @Unique private static final Identifier MINT_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/mint");
   @Unique private static final Identifier PEACH_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/peach");
   @Unique private static final Identifier PERIWINKLE_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/periwinkle");
   @Unique private static final Identifier ARTICHOKE_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/artichoke");
   @Unique private static final Identifier FUCHSIA_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/fuchsia");
   @Unique private static final Identifier VERMILION_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/vermilion");
   @Unique private static final Identifier SHAMROCK_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/shamrock");
   @Unique private static final Identifier INDIGO_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/indigo");
   @Unique private static final Identifier BANANA_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/banana");
   @Unique private static final Identifier CERULEAN_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/cerulean");
   @Unique private static final Identifier ACORN_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/acorn");
   @Unique private static final Identifier MAUVE_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/mauve");
   @Unique private static final Identifier MAROON_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/maroon");
   @Unique private static final Identifier GRAPE_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/grape");
   @Unique private static final Identifier NAVY_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/navy");
   @Unique private static final Identifier SAP_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/sap");
   @Unique private static final Identifier AMBER_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/amber");
   @Unique private static final Identifier SAGE_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/sage");
   @Unique private static final Identifier VELVET_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/velvet");
   @Unique private static final Identifier MOLD_SHEEP_LOOT_TABLE = new Identifier(Super.MOD_ID,"entities/sheep/mold");

   @Shadow @Final private static Map<DyeColor, ItemConvertible> DROPS;
   @Shadow @Final private static TrackedData <Byte> COLOR;

   static {
      DROPS.put(ModDyeColor.MINT, ModBlocks.MINT_WOOL);
      DROPS.put(ModDyeColor.PEACH, ModBlocks.PEACH_WOOL);
      DROPS.put(ModDyeColor.PERIWINKLE, ModBlocks.PERIWINKLE_WOOL);
      DROPS.put(ModDyeColor.ARTICHOKE, ModBlocks.ARTICHOKE_WOOL);
      DROPS.put(ModDyeColor.FUCHSIA, ModBlocks.FUCHSIA_WOOL);
      DROPS.put(ModDyeColor.VERMILION, ModBlocks.VERMILION_WOOL);
      DROPS.put(ModDyeColor.SHAMROCK, ModBlocks.SHAMROCK_WOOL);
      DROPS.put(ModDyeColor.INDIGO, ModBlocks.INDIGO_WOOL);
      DROPS.put(ModDyeColor.BANANA, ModBlocks.BANANA_WOOL);
      DROPS.put(ModDyeColor.CERULEAN, ModBlocks.CERULEAN_WOOL);
      DROPS.put(ModDyeColor.ACORN, ModBlocks.ACORN_WOOL);
      DROPS.put(ModDyeColor.MAUVE, ModBlocks.MAUVE_WOOL);
      DROPS.put(ModDyeColor.MAROON, ModBlocks.MAROON_WOOL);
      DROPS.put(ModDyeColor.GRAPE, ModBlocks.GRAPE_WOOL);
      DROPS.put(ModDyeColor.NAVY, ModBlocks.NAVY_WOOL);
      DROPS.put(ModDyeColor.SAP, ModBlocks.SAP_WOOL);
      DROPS.put(ModDyeColor.AMBER, ModBlocks.AMBER_WOOL);
      DROPS.put(ModDyeColor.SAGE, ModBlocks.SAGE_WOOL);
      DROPS.put(ModDyeColor.VELVET, ModBlocks.VELVET_WOOL);
      DROPS.put(ModDyeColor.MOLD, ModBlocks.MOLD_WOOL);
   }

   private SheepEntityMixin() {
      super(EntityType.SHEEP, null);
      throw new AssertionError();
   }

   /**
    * @reason Allowing >16 unique dye colors (128)
    * @author ADudeCalledLeo
    */
   @Overwrite
   public DyeColor getColor() {
      byte b = dataTracker.get(COLOR);
      return DyeColor.byId(b & 0x7F);
   }

   /**
    * @reason Allowing >16 unique dye colors (128)
    * @author ADudeCalledLeo
    */
   @Overwrite
   public void setColor(DyeColor color) {
      byte b = dataTracker.get(COLOR);
      dataTracker.set(COLOR, (byte) ((b & 0x80) | color.getId() % 0x7F));
   }

   /**
    * @reason Allowing >16 unique dye colors (128)
    * @author ADudeCalledLeo
    */
   @Overwrite
   public boolean isSheared() {
      return (dataTracker.get(COLOR) & 0x80) != 0;
   }

   /**
    * @reason Allowing >16 unique dye colors (128)
    * @author ADudeCalledLeo
    */
   @Overwrite
   public void setSheared(boolean sheared) {
      byte b = dataTracker.get(COLOR);
      dataTracker.set(COLOR, (byte) ((b & 0x7F) | (sheared ? 0x80 : 0)));
   }

   @Inject(method = "getLootTableId", at = @At("HEAD"), cancellable = true)
   public void getCustomLootTableIds(CallbackInfoReturnable <Identifier> cir) {
      DyeColor color = getColor();
      if (color == ModDyeColor.MINT) cir.setReturnValue(MINT_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.PEACH) cir.setReturnValue(PEACH_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.PERIWINKLE) cir.setReturnValue(PERIWINKLE_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.ARTICHOKE) cir.setReturnValue(ARTICHOKE_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.FUCHSIA) cir.setReturnValue(FUCHSIA_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.VERMILION) cir.setReturnValue(VERMILION_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.SHAMROCK) cir.setReturnValue(SHAMROCK_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.INDIGO) cir.setReturnValue(INDIGO_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.BANANA) cir.setReturnValue(BANANA_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.CERULEAN) cir.setReturnValue(CERULEAN_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.ACORN) cir.setReturnValue(ACORN_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.MAUVE) cir.setReturnValue(MAUVE_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.MAROON) cir.setReturnValue(MAROON_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.GRAPE) cir.setReturnValue(GRAPE_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.NAVY) cir.setReturnValue(NAVY_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.SAP) cir.setReturnValue(SAP_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.AMBER) cir.setReturnValue(AMBER_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.SAGE) cir.setReturnValue(SAGE_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.VELVET) cir.setReturnValue(VELVET_SHEEP_LOOT_TABLE);
      else if (color == ModDyeColor.MOLD) cir.setReturnValue(MOLD_SHEEP_LOOT_TABLE);
   }
}
