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

@Suppress("unused")
object ModEarlyRiser : Runnable {
    override fun run() {
        val mr = FabricLoader.getInstance().mappingResolver
        val dyeColor = mr.mapClassName("intermediary", "net.minecraft.class_1767") //maybe the class got renamed?
        val mapColor = mr.mapClassName("intermediary", "net.minecraft.class_3620")

        val adder =
                ClassTinkerers.enumBuilder(dyeColor, Int::class.java, String::class.java, Int::class.java, "L$mapColor;", Int::class.java, Int::class.java)
        addDyeColorEnum(adder, ModDyeColor.Values.MINT)
        adder.build()
    }

    private fun addDyeColorEnum(adder: EnumAdder, values: ModDyeColor.Values) {
        adder.addEnum(values.fieldName) { arrayOf(-1, values.name, values.color.value, MapColor.get(values.mapColorId),
                values.signColor.value, values.fireworkColor.value) }
    }
}

