/*
* Sources:
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/fabric/src/main/kotlin/com/remodstudios/voidlands/fabric/VoidlandsEarlyRiser.kt
*/

package net.leafenzo.mint

import com.chocohead.mm.api.ClassTinkerers
import com.chocohead.mm.api.EnumAdder
import net.fabricmc.loader.api.FabricLoader
import net.leafenzo.mint.util.ElsDyeModDyeColor
import net.minecraft.block.MapColor

//@Debug(export = true)
@Suppress("unused")
object ElsDyeModEarlyRiser : Runnable {

    //Someday consider replacing this with a ModDyeColor enum with mixins targeting and adding the types from that enum to every location where DyeColor is referenced

    override fun run() {
        val mr = FabricLoader.getInstance().mappingResolver
        val dyeColor = mr.mapClassName("intermediary", "net.minecraft.class_1767") //maybe the class got renamed?
        val mapColor = mr.mapClassName("intermediary", "net.minecraft.class_3620")

        val adder =
                ClassTinkerers.enumBuilder(dyeColor, Int::class.java, String::class.java, Int::class.java, "L$mapColor;", Int::class.java, Int::class.java)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.MINT)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.PEACH)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.PERIWINKLE)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.ARTICHOKE)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.FUCHSIA)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.VERMILION)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.SHAMROCK)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.INDIGO)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.BANANA)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.CERULEAN)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.ACORN)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.MAUVE)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.MAROON)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.GRAPE)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.NAVY)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.SAP)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.AMBER)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.SAGE)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.VELVET)
        addDyeColorEnum(adder, ElsDyeModDyeColor.Values.MOLD)
        adder.build()
    }

    private fun addDyeColorEnum(adder: EnumAdder, values: ElsDyeModDyeColor.Values) {
        adder.addEnum(values.fieldName) { arrayOf(-1, values.name, values.color.value, MapColor.get(values.mapColorId),
                values.signColor.value, values.fireworkColor.value) }
    }
}

