package net.leafenzo.mint.mixin;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import net.leafenzo.mint.entity.mob.SpiderBrain;
import net.leafenzo.mint.entity.sensor.ModSensorType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.mob.HoglinBrain;
import net.minecraft.entity.mob.HoglinEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(SpiderEntity.class)
public class SpiderEntityMixin extends HostileEntity {


   private static final ImmutableList <? extends SensorType <? extends Sensor <? super SpiderEntity>>> SENSOR_TYPES;
   private static final ImmutableList<? extends MemoryModuleType <?>> MEMORY_MODULE_TYPES;

   protected SpiderEntityMixin(EntityType <? extends HostileEntity> entityType, World world) {
      super(entityType, world);
   }

   protected Brain.Profile <SpiderEntity> createBrainProfile() {
      return Brain.createProfile(MEMORY_MODULE_TYPES, SENSOR_TYPES);
   }

   protected Brain<?> deserializeBrain(Dynamic <?> dynamic) {
      return SpiderBrain.create(createBrainProfile().deserialize(dynamic));
   }

   public Brain <SpiderEntity> getBrain() {
      return (Brain <SpiderEntity>) super.getBrain();
   }

   protected void mobTick() {
      this.getWorld().getProfiler().push("spiderBrain");
      this.getBrain().tick((ServerWorld) this.getWorld(), ((SpiderEntity)(Object)this));
      this.getWorld().getProfiler().pop();
   }

   public float getPathfindingFavor(BlockPos pos, WorldView world) {
      if (SpiderBrain.isMintAround(((SpiderEntity)(Object)this), pos)) {
         return -1.0F;
      } else {
         return super.getPathfindingFavor(pos, world);
      }
   }

   private static boolean hasNearestRepellent(SpiderEntity spider) {
      return spider.getBrain().hasMemoryModule(MemoryModuleType.NEAREST_REPELLENT);
   }


   static {
      SENSOR_TYPES = ImmutableList.of(ModSensorType.SPIDER_SPECIFIC_SENSOR);
      MEMORY_MODULE_TYPES = ImmutableList.of(MemoryModuleType.NEAREST_REPELLENT);
   }
}
