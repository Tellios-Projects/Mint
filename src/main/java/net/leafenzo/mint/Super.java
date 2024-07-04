package net.leafenzo.mint;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class Super {
    public static String MOD_ID = "mint";

    // For Integrations
    public static final String AMENDMENTS_MOD_ID = "amendments";
    public static final String BOTANY_POTS_MOD_ID = "botanypots";
    public static final String COMFORTS_MOD_ID = "comforts";
    public static final String SUPPLEMENTARIES_MOD_ID = "supplementaries";
    public static final String SUPPLEMENTARIES_SQUARED_MOD_ID = "suppsquared";
    public static final String SNOWY_SPIRIT_MOD_ID = "snowyspirit";
    public static final String BIOME_MAKEOVER_MOD_ID = "biomemakeover";
    public static final String CREATE_MOD_ID = "create";
    public static final String CREATE_DECO_MOD_ID = "createdeco";
    public static final String SLEEP_TIGHT_MOD_ID = "sleep_tight";
    public static final String TWIGS_MOD_ID = "twigs";

    public static Identifier asResource(String path) {
        return new Identifier(MOD_ID, path.toLowerCase().replace(' ', '_')); // Silently make the path lowercase if it's not.
    }
    public static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }
    public static boolean isDatagen() { // Thank you Hecco oh my lord did I need this
        return System.getProperty("fabric-api.datagen") != null;
    }

}
//Sources:
//https://github.com/Alpha-s-Stuff/Nebula/blob/8fa67790b706a576bc6d687e1fc6f94a0f773d3c/src/main/java/me/alphamode/nebula/Nebula.java
