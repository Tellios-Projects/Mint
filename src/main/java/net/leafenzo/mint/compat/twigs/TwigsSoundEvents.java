package net.leafenzo.mint.compat.twigs;

import net.leafenzo.mint.Super;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;

//  The ARR License (ARR)
//
//  Copyright (c) 2022 Ninni

// Used with permission.
// SOURCE: https://github.com/N1nn1/twigs/blob/main/src/main/java/com/ninni/twigs/registry/TwigsSoundEvents.java

public interface TwigsSoundEvents {
    BlockSoundGroup PACKED_SILT = registerBlockSoundGroup("packed_silt", 1.0F, 1.0F);
    BlockSoundGroup SILT_POT = registerBlockSoundGroup("silt_pot", 1.0F, 1.0F);
    BlockSoundGroup SILT_POT_FILLED = registerBlockSoundGroup("silt_pot_filled", 1.0F, 1.0F);
    BlockSoundGroup SILT_SHINGLES = registerBlockSoundGroup("silt_shingles", 1.0F, 1.0F);

    private static BlockSoundGroup registerBlockSoundGroup(String name, float volume, float pitch) {
        return new BlockSoundGroup(volume, pitch, registerSoundEvent("block." + name + ".break"), registerSoundEvent("block." + name + ".step"), registerSoundEvent("block." + name + ".place"), registerSoundEvent("block." + name + ".hit"), registerSoundEvent("block." + name + ".fall"));
    }
    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Super.MOD_ID, name);
        return (SoundEvent) Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

//    @Nullable  // Doesn't work
//    private static SoundEvent getSoundEvent(String name) {
//        Identifier id = new Identifier(Super.TWIGS_MOD_ID, name);
//        return (SoundEvent) Registries.SOUND_EVENT.get(id);
//    }
}
