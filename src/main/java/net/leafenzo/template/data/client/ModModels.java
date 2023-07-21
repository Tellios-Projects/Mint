package net.leafenzo.template.data.client;

import net.leafenzo.template.Super;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModels {
    //public static final Model CUBE_DIRECTIONAL_DOWN_ROTATED = block(Super.MOD_ID + ":block/cube_directional_down_rotated", TextureKey.PARTICLE, TextureKey.NORTH, TextureKey.SOUTH, TextureKey.EAST, TextureKey.WEST, TextureKey.UP, TextureKey.DOWN);

    private static Model block(String path, TextureKey ... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(path)), Optional.empty(), requiredTextureKeys);
    }
}
