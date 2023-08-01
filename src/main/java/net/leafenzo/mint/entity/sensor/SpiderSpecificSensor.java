package net.leafenzo.mint.entity.sensor;

import net.leafenzo.mint.mixin.SpiderEntityMixin;
import net.leafenzo.mint.registry.tag.ModBlockTags;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;
import java.util.Set;

public class SpiderSpecificSensor extends Sensor <SpiderEntity> {
   protected void sense(ServerWorld world, SpiderEntity entity) {
//      Brain <SpiderEntity> brain = SpiderEntity.getBrain();
//      brain.remember(MemoryModuleType.NEAREST_REPELLENT, this.findNearestMint(world, entity));
   }

   public Set <MemoryModuleType <?>> getOutputMemoryModules() {
      return null;
   }

   private Optional <BlockPos> findNearestMint(ServerWorld world, SpiderEntity spider) {
      return BlockPos.findClosest(spider.getBlockPos(), 8, 4, (pos) -> {
         return world.getBlockState(pos).isIn(ModBlockTags.SPIDER_REPELLENTS);
      });
   }
}
