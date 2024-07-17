package net.leafenzo.mint.sound;

import net.leafenzo.mint.Super;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSoundEvents {
    public static final SoundEvent ENTITY_BEETLE_AMBIENT = registerSoundEvent("entity_beetle_ambient");
    public static final SoundEvent ENTITY_BEETLE_DEATH = registerSoundEvent("entity_beetle_death");
    public static final SoundEvent ENTITY_BEETLE_HURT = registerSoundEvent("entity_beetle_hurt");
    public static final SoundEvent ENTITY_BEETLE_STEP = registerSoundEvent("entity_beetle_step");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Super.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}