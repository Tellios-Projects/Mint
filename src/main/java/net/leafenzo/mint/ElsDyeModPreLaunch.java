package net.leafenzo.mint;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElsDyeModPreLaunch implements PreLaunchEntrypoint {
    public static final Logger LOGGER = LoggerFactory.getLogger(ElsDyeMod.MOD_ID);

    @Override
    public void onPreLaunch() {
        //ModBlocks.registerModBlocks();
    }
}
