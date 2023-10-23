package net.leafenzo.mint.data.client;

import net.leafenzo.mint.Super;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModels {
    public static final Model FULLHEIGHT_CROSS = block(Super.MOD_ID + "block/fullheight_cross", TextureKey.CROSS);
    public static final Model TWO_END_ORIENTABLE = block(Super.MOD_ID + "block/two_end_orientable", TextureKey.FRONT, TextureKey.BACK, TextureKey.SIDE);
    public static final Model CUBE_DIRECTIONAL_DOWN_ROTATED = block(Super.MOD_ID + ":block/cube_directional_down_rotated", TextureKey.PARTICLE, TextureKey.NORTH, TextureKey.SOUTH, TextureKey.EAST, TextureKey.WEST, TextureKey.UP, TextureKey.DOWN);

    public static Model block(String path, TextureKey ... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(path)), Optional.empty(), requiredTextureKeys);
    }
}
