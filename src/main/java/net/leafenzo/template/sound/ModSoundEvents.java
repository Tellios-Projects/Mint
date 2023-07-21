package net.leafenzo.template.sound;

import net.leafenzo.template.Super;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static final SoundEvent BLOCK_METAL_STICKS_BREAK = registerSoundEvent("block.metal_sticks.break");
    public static final SoundEvent BLOCK_METAL_STICKS_STEP = registerSoundEvent("block.metal_sticks.step");
    public static final SoundEvent BLOCK_METAL_STICKS_PLACE = registerSoundEvent("block.metal_sticks.place");
    public static final SoundEvent BLOCK_METAL_STICKS_HIT = registerSoundEvent("block.metal_sticks.hit");
    public static final SoundEvent BLOCK_METAL_STICKS_FALL = registerSoundEvent("block.metal_sticks.fall");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Super.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
