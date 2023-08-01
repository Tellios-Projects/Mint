package net.leafenzo.mint.entity.sensor;

import com.google.common.collect.ImmutableSet;
import net.leafenzo.mint.ModInit;
import net.leafenzo.mint.Super;
import net.leafenzo.mint.mixin.SpiderEntityMixin;
import net.leafenzo.mint.registry.tag.ModBlockTags;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;
import java.util.Set;

public class SpiderSpecificSensor extends Sensor <SpiderEntity> {
   @Override
   protected void sense(ServerWorld world, SpiderEntity entity) {
      Brain <SpiderEntity> brain = (Brain <SpiderEntity>) entity.getBrain();
      brain.remember(MemoryModuleType.NEAREST_REPELLENT, this.findNearestMint(world, entity));
   }
   @Override
   public Set <MemoryModuleType <?>> getOutputMemoryModules() {
      return ImmutableSet.of(MemoryModuleType.NEAREST_REPELLENT, MemoryModuleType.DUMMY);

   }


   private Optional <BlockPos> findNearestMint(ServerWorld world, SpiderEntity spider) {
      Optional <BlockPos> optional = BlockPos.findClosest(spider.getBlockPos(), 8, 4, (pos) -> world.getBlockState(pos).isIn(ModBlockTags.SPIDER_REPELLENTS));
      return optional;
   }
}
