package net.leafenzo.mint.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.leafenzo.mint.entity.mob.SpiderBrain;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(SpiderEntity.class)
public class SpiderEntityMixin {


//   protected static final ImmutableList <? extends SensorType <? extends Sensor <? super SpiderEntity>>> SENSOR_TYPES;
//   protected static final ImmutableList<? extends MemoryModuleType <?>> MEMORY_MODULE_TYPES;

//   protected Brain.Profile <SpiderEntity> createBrainProfile() {
//      return Brain.createProfile(MEMORY_MODULE_TYPES, SENSOR_TYPES);
//   }
//
//   protected Brain<?> deserializeBrain(Dynamic <?> dynamic) {
//      return SpiderBrain.create(this, this.createBrainProfile().deserialize(dynamic));
//   }
//
//   public Brain <SpiderEntity> getBrain() {
//      return ((SpiderEntity)(Object)this).getBrain();
//   }
//
//   public float getPathfindingFavor(BlockPos pos, WorldView world) {
//      if (SpiderBrain.isMintAround(this, pos)) {
//         return -1.0F;
//      } else {
//         return super.getpathFindingFavor(pos, world);
//      }
//   }
//   static {
//      SENSOR_TYPES = (ImmutableList <? extends SensorType <? extends Sensor <? super SpiderEntity>>>) ImmutableList.of(SensorType.NEAREST_LIVING_ENTITIES, SensorType.NEAREST_PLAYERS, SensorType.NEAREST_ADULT, SensorType.HOGLIN_SPECIFIC_SENSOR);
//      MEMORY_MODULE_TYPES = (ImmutableList <? extends MemoryModuleType <?>>) ImmutableList.of(MemoryModuleType.BREED_TARGET, MemoryModuleType.MOBS, MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.PATH, MemoryModuleType.ATTACK_TARGET, MemoryModuleType.ATTACK_COOLING_DOWN, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLIN, new MemoryModuleType[]{MemoryModuleType.AVOID_TARGET, MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, MemoryModuleType.NEAREST_VISIBLE_ADULT_HOGLINS, MemoryModuleType.NEAREST_VISIBLE_ADULT, MemoryModuleType.NEAREST_REPELLENT, MemoryModuleType.PACIFIED});
//   }
}
