// SOURCE:
// Hecco - https://github.com/Heccology/Bountiful-Fares/blob/597a7b1e35460e135ebdea44a424c6913b47c255/src/main/java/net/hecco/bountifulfares/compat/BFResourcePacks.java

package net.leafenzo.mint;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModBuiltinResourcePacks {
    private static void registerBuiltinResourcePack(ModContainer modContainer, String forModID) {
        boolean success = ResourceManagerHelper.registerBuiltinResourcePack(
                new Identifier(Super.MOD_ID, forModID),
                modContainer,
                Text.translatable("pack." + Super.MOD_ID + "." + forModID),
                ResourcePackActivationType.ALWAYS_ENABLED
        );
        if(success) { ModInit.LOGGER.info("Registered builtin resource pack for " + forModID); }
        else { ModInit.LOGGER.error("COULD NOT REGISTER BUILTIN RESOURCE PACK FOR " + forModID); }
    }
    private static void registerBuiltinDataPack(ModContainer modContainer, String forModID) {
        boolean success = ResourceManagerHelper.registerBuiltinResourcePack(
                new Identifier(Super.MOD_ID, forModID),
                modContainer,
                Text.translatable("pack." + Super.MOD_ID + "." + forModID),
                ResourcePackActivationType.ALWAYS_ENABLED
        );
        if(success) { ModInit.LOGGER.info("Registered builtin data pack for " + forModID); }
        else { ModInit.LOGGER.error("COULD NOT REGISTER BUILTIN DATA PACK FOR " + forModID); }
    }
    private static void registerPacks(Optional<ModContainer> modContainer, String modId) {
        if(modContainer.isPresent() && Super.isModLoaded(modId)) {
            registerBuiltinResourcePack(modContainer.get(), modId);
            registerBuiltinDataPack(modContainer.get(), modId);
        }
    }
    public static void registerBuiltinPacks() {
        ModInit.LOGGER.info("Registering builtin resource and data packs for " + Super.MOD_ID);
        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(Super.MOD_ID);
        registerPacks(modContainer, Super.AMENDMENTS_MOD_ID);
        registerPacks(modContainer, Super.BOTANY_POTS_MOD_ID);
        registerPacks(modContainer, Super.COMFORTS_MOD_ID);
        registerPacks(modContainer, Super.SUPPLEMENTARIES_MOD_ID);
        registerPacks(modContainer, Super.SUPPLEMENTARIES_SQUARED_MOD_ID);
        registerPacks(modContainer, Super.SNOWY_SPIRIT_MOD_ID);
        registerPacks(modContainer, Super.BIOME_MAKEOVER_MOD_ID);
        registerPacks(modContainer, Super.CREATE_MOD_ID);
        registerPacks(modContainer, Super.CREATE_DECO_MOD_ID);
        registerPacks(modContainer, Super.SLEEP_TIGHT_MOD_ID);
        registerPacks(modContainer, Super.TWIGS_MOD_ID);
    }
}
