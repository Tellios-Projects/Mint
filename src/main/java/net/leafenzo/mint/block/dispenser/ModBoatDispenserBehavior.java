/*
 * SOURCES:
 * Team Hibiscus - https://github.com/Team-Hibiscus/NaturesSpirit/blob/c69094e549abe10993e009cff36efdf2c5e1e828/remappedSrc/net/hibiscus/naturespirit/items/HibiscusBoatDispensorBehavior.java
 */

package net.leafenzo.mint.block.dispenser;

import net.leafenzo.mint.entity.ModBoatEntity;
import net.minecraft.block.dispenser.BoatDispenserBehavior;
import net.minecraft.entity.vehicle.BoatEntity;

public final class ModBoatDispenserBehavior extends BoatDispenserBehavior {
    private final ModBoatEntity.ModBoat boatData;
    public ModBoatDispenserBehavior(ModBoatEntity.ModBoat boatData, boolean chest) {
        super(BoatEntity.Type.OAK, chest);
        this.boatData = boatData;
    }

    public ModBoatEntity.ModBoat getBoatData() {
        return boatData;
    }
}