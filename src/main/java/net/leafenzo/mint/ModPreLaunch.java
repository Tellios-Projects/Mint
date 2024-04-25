package net.leafenzo.mint;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.leafenzo.mint.block.ModBlocks;
import net.leafenzo.mint.util.ModDyeColor;
import net.minecraft.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModPreLaunch implements PreLaunchEntrypoint {
    public static final Logger LOGGER = LoggerFactory.getLogger(Super.MOD_ID);

    @Override
    public void onPreLaunch() {
        //ModBlocks.registerModBlocks();
    }
}
