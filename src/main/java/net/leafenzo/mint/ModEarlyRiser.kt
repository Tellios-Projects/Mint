/*
* Sources:
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/fabric/src/main/kotlin/com/remodstudios/voidlands/fabric/VoidlandsEarlyRiser.kt
*/

package net.leafenzo.mint

import net.leafenzo.mint.util.ModDyeColor
import com.chocohead.mm.api.ClassTinkerers
import com.chocohead.mm.api.EnumAdder
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.MapColor
import org.spongepowered.asm.mixin.Debug

//@Debug(export = true)
@Suppress("unused")
object ModEarlyRiser : Runnable {

    //Someday consider replacing this with a ModDyeColor enum with mixins targeting and adding the types from that enum to every location where DyeColor is referenced

    override fun run() {
        val mr = FabricLoader.getInstance().mappingResolver
        val dyeColor = mr.mapClassName("intermediary", "net.minecraft.class_1767") //maybe the class got renamed?
        val mapColor = mr.mapClassName("intermediary", "net.minecraft.class_3620")

        val adder =
                ClassTinkerers.enumBuilder(dyeColor, Int::class.java, String::class.java, Int::class.java, "L$mapColor;", Int::class.java, Int::class.java)
        addDyeColorEnum(adder, ModDyeColor.Values.MINT)
        addDyeColorEnum(adder, ModDyeColor.Values.PEACH)
        addDyeColorEnum(adder, ModDyeColor.Values.PERIWINKLE)
        addDyeColorEnum(adder, ModDyeColor.Values.ARTICHOKE)
        addDyeColorEnum(adder, ModDyeColor.Values.FUCHSIA)
        addDyeColorEnum(adder, ModDyeColor.Values.VERMILION)
        addDyeColorEnum(adder, ModDyeColor.Values.SHAMROCK)
        addDyeColorEnum(adder, ModDyeColor.Values.INDIGO)
        addDyeColorEnum(adder, ModDyeColor.Values.BANANA)
        addDyeColorEnum(adder, ModDyeColor.Values.CERULEAN)
        addDyeColorEnum(adder, ModDyeColor.Values.ACORN)
        addDyeColorEnum(adder, ModDyeColor.Values.MAUVE)
        addDyeColorEnum(adder, ModDyeColor.Values.MAROON)
        addDyeColorEnum(adder, ModDyeColor.Values.GRAPE)
        addDyeColorEnum(adder, ModDyeColor.Values.NAVY)
        addDyeColorEnum(adder, ModDyeColor.Values.SAP)
        addDyeColorEnum(adder, ModDyeColor.Values.AMBER)
        addDyeColorEnum(adder, ModDyeColor.Values.SAGE)
        addDyeColorEnum(adder, ModDyeColor.Values.VELVET)
        addDyeColorEnum(adder, ModDyeColor.Values.MOLD)
        adder.build()
    }

    private fun addDyeColorEnum(adder: EnumAdder, values: ModDyeColor.Values) {
        adder.addEnum(values.fieldName) { arrayOf(-1, values.name, values.color.value, MapColor.get(values.mapColorId),
                values.signColor.value, values.fireworkColor.value) }
    }
}

