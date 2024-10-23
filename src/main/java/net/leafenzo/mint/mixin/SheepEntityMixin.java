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

import net.leafenzo.mint.ElsDyeMod;
import net.leafenzo.mint.block.ElsDyeModBlocks;
import net.leafenzo.mint.util.ElsDyeModDyeColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends AnimalEntity implements Shearable {
   // For now new sheep loot tables must be manually copied and renamed for each new dye in data/mint/loot_tables/sheep/ (unless we figure out how to datagen with the EntityLootTableGenerator) -Leah
   @Unique private static final Identifier MINT_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/mint");
   @Unique private static final Identifier PEACH_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/peach");
   @Unique private static final Identifier PERIWINKLE_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/periwinkle");
   @Unique private static final Identifier ARTICHOKE_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/artichoke");
   @Unique private static final Identifier FUCHSIA_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/fuchsia");
   @Unique private static final Identifier VERMILION_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/vermilion");
   @Unique private static final Identifier SHAMROCK_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/shamrock");
   @Unique private static final Identifier INDIGO_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/indigo");
   @Unique private static final Identifier BANANA_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/banana");
   @Unique private static final Identifier CERULEAN_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/cerulean");
   @Unique private static final Identifier ACORN_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/acorn");
   @Unique private static final Identifier MAUVE_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/mauve");
   @Unique private static final Identifier MAROON_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/maroon");
   @Unique private static final Identifier GRAPE_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/grape");
   @Unique private static final Identifier NAVY_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/navy");
   @Unique private static final Identifier SAP_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/sap");
   @Unique private static final Identifier AMBER_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/amber");
   @Unique private static final Identifier SAGE_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/sage");
   @Unique private static final Identifier VELVET_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/velvet");
   @Unique private static final Identifier MOLD_SHEEP_LOOT_TABLE = new Identifier(ElsDyeMod.MOD_ID,"entities/sheep/mold");

   @Shadow @Final private static Map<DyeColor, ItemConvertible> DROPS;
   @Shadow @Final private static TrackedData <Byte> COLOR;

   static {
      DROPS.put(ElsDyeModDyeColor.MINT, ElsDyeModBlocks.MINT_WOOL);
      DROPS.put(ElsDyeModDyeColor.PEACH, ElsDyeModBlocks.PEACH_WOOL);
      DROPS.put(ElsDyeModDyeColor.PERIWINKLE, ElsDyeModBlocks.PERIWINKLE_WOOL);
      DROPS.put(ElsDyeModDyeColor.ARTICHOKE, ElsDyeModBlocks.ARTICHOKE_WOOL);
      DROPS.put(ElsDyeModDyeColor.FUCHSIA, ElsDyeModBlocks.FUCHSIA_WOOL);
      DROPS.put(ElsDyeModDyeColor.VERMILION, ElsDyeModBlocks.VERMILION_WOOL);
      DROPS.put(ElsDyeModDyeColor.SHAMROCK, ElsDyeModBlocks.SHAMROCK_WOOL);
      DROPS.put(ElsDyeModDyeColor.INDIGO, ElsDyeModBlocks.INDIGO_WOOL);
      DROPS.put(ElsDyeModDyeColor.BANANA, ElsDyeModBlocks.BANANA_WOOL);
      DROPS.put(ElsDyeModDyeColor.CERULEAN, ElsDyeModBlocks.CERULEAN_WOOL);
      DROPS.put(ElsDyeModDyeColor.ACORN, ElsDyeModBlocks.ACORN_WOOL);
      DROPS.put(ElsDyeModDyeColor.MAUVE, ElsDyeModBlocks.MAUVE_WOOL);
      DROPS.put(ElsDyeModDyeColor.MAROON, ElsDyeModBlocks.MAROON_WOOL);
      DROPS.put(ElsDyeModDyeColor.GRAPE, ElsDyeModBlocks.GRAPE_WOOL);
      DROPS.put(ElsDyeModDyeColor.NAVY, ElsDyeModBlocks.NAVY_WOOL);
      DROPS.put(ElsDyeModDyeColor.SAP, ElsDyeModBlocks.SAP_WOOL);
      DROPS.put(ElsDyeModDyeColor.AMBER, ElsDyeModBlocks.AMBER_WOOL);
      DROPS.put(ElsDyeModDyeColor.SAGE, ElsDyeModBlocks.SAGE_WOOL);
      DROPS.put(ElsDyeModDyeColor.VELVET, ElsDyeModBlocks.VELVET_WOOL);
      DROPS.put(ElsDyeModDyeColor.MOLD, ElsDyeModBlocks.MOLD_WOOL);
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
      if (color == ElsDyeModDyeColor.MINT) cir.setReturnValue(MINT_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.PEACH) cir.setReturnValue(PEACH_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.PERIWINKLE) cir.setReturnValue(PERIWINKLE_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.ARTICHOKE) cir.setReturnValue(ARTICHOKE_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.FUCHSIA) cir.setReturnValue(FUCHSIA_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.VERMILION) cir.setReturnValue(VERMILION_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.SHAMROCK) cir.setReturnValue(SHAMROCK_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.INDIGO) cir.setReturnValue(INDIGO_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.BANANA) cir.setReturnValue(BANANA_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.CERULEAN) cir.setReturnValue(CERULEAN_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.ACORN) cir.setReturnValue(ACORN_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.MAUVE) cir.setReturnValue(MAUVE_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.MAROON) cir.setReturnValue(MAROON_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.GRAPE) cir.setReturnValue(GRAPE_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.NAVY) cir.setReturnValue(NAVY_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.SAP) cir.setReturnValue(SAP_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.AMBER) cir.setReturnValue(AMBER_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.SAGE) cir.setReturnValue(SAGE_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.VELVET) cir.setReturnValue(VELVET_SHEEP_LOOT_TABLE);
      else if (color == ElsDyeModDyeColor.MOLD) cir.setReturnValue(MOLD_SHEEP_LOOT_TABLE);
   }
}
