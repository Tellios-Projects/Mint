package net.leafenzo.mint.entity.sensor;

import net.leafenzo.mint.Super;
import net.minecraft.entity.ai.brain.sensor.HoglinSpecificSensor;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ModSensorType<U extends Sensor<?>> {
   public static final SensorType<SpiderSpecificSensor> SPIDER_SPECIFIC_SENSOR = register(Super.MOD_ID + "spider_specific_sensor", SpiderSpecificSensor::new);

   private static <U extends Sensor<?>> SensorType<U> register(String id, Supplier<U> factory) {
      return (SensorType) Registry.register(Registries.SENSOR_TYPE, new Identifier(id), new SensorType(factory));
   }
}
