package net.leafenzo.mint.compat.twigs;

import net.leafenzo.mint.Super;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

//  The ARR License (ARR)
//
//  Copyright (c) 2022 Ninni

// Used with permission.
// SOURCE: https://github.com/N1nn1/twigs/blob/main/src/main/java/com/ninni/twigs/registry/TwigsSoundEvents.java

public interface TwigsSoundEvents {
    BlockSoundGroup PACKED_SILT = register("packed_silt", 1.0F, 1.0F);
    BlockSoundGroup SILT_POT = register("silt_pot", 1.0F, 1.0F);
    BlockSoundGroup SILT_POT_FILLED = register("silt_pot_filled", 1.0F, 1.0F);
    BlockSoundGroup SILT_SHINGLES = register("silt_shingles", 1.0F, 1.0F);

    private static BlockSoundGroup register(String name, float volume, float pitch) {
        return new BlockSoundGroup(volume, pitch, register("block." + name + ".break"), register("block." + name + ".step"), register("block." + name + ".place"), register("block." + name + ".hit"), register("block." + name + ".fall"));
    }

    private static SoundEvent register(String name) {
        Identifier id = new Identifier(Super.MOD_ID, name);
        return (SoundEvent) Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
