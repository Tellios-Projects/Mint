package net.leafenzo.mint.entity.mob;

import com.google.common.collect.ImmutableSet;
import net.leafenzo.mint.mixin.SpiderEntityMixin;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class SpiderBrain {
   public static Brain <?> create(Brain<SpiderEntity> brain) {
      brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
      brain.setDefaultActivity(Activity.IDLE);
      brain.resetPossibleActivities();
      return brain;
   }

   static boolean isMintAround(SpiderEntity spider, BlockPos pos) {
      Optional <BlockPos> optional = spider.getBrain().getOptionalRegisteredMemory(MemoryModuleType.NEAREST_REPELLENT);
      return optional.isPresent() && ((BlockPos)optional.get()).isWithinDistance(pos, 8.0D);
   }
}
