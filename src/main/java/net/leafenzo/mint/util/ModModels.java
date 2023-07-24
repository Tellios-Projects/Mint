package net.leafenzo.mint.util;

import net.leafenzo.mint.Super;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModels {
    public static final Model FULLHEIGHT_CROSS = block(Super.MOD_ID + "block/fullheight_cross", TextureKey.CROSS);

    private static Model block(String path, TextureKey ... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(path)), Optional.empty(), requiredTextureKeys);
    }
}
