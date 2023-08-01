package net.leafenzo.mint.entity.mob;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.*;
import net.minecraft.entity.mob.*;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public class SpiderBrain {
   public static Brain <?> create(Brain <SpiderEntity> brain) {
      addIdleActivities(brain);
      brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
      brain.setDefaultActivity(Activity.IDLE);
      brain.resetPossibleActivities();
      return brain;
   }

   private static void addIdleActivities(Brain<SpiderEntity> spider) {
      MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(Text.literal("Add Run Task"));
      spider.setTaskList(Activity.IDLE, 10, ImmutableList.of(PacifyTask.create(MemoryModuleType.NEAREST_REPELLENT, 200), GoToRememberedPositionTask.createPosBased(MemoryModuleType.NEAREST_REPELLENT, 1.0f, 8, true)));
   }

   public static boolean isMintAround(SpiderEntity spider, BlockPos pos) {
      Optional <BlockPos> optional = spider.getBrain().getOptionalRegisteredMemory(MemoryModuleType.NEAREST_REPELLENT);
      return optional.isPresent() && ((BlockPos)optional.get()).isWithinDistance(pos, 8.0D);
   }
}
