/*
* Sources:
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/fabric/src/main/kotlin/com/remodstudios/voidlands/fabric/PlatformInterfaceImpl.kt
 */


package net.leafenzo.mint
import com.chocohead.mm.api.ClassTinkerers
import net.leafenzo.mint.util.ElsDyeModDyeColor
import net.minecraft.util.DyeColor

@Suppress("unused")
object PlatformInterfaceImpl {
    @JvmStatic fun printHelloWorld() {
        println("Hello Fabric World!")
    }

    @JvmStatic fun getMintDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.MINT.fieldName)
    @JvmStatic fun getPeachDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.PEACH.fieldName)
    @JvmStatic fun getPeriwinkleDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.PERIWINKLE.fieldName)
    @JvmStatic fun getArtichokeDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.ARTICHOKE.fieldName)
    @JvmStatic fun getFuchsiaDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.FUCHSIA.fieldName)
    @JvmStatic fun getVermilionDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.VERMILION.fieldName)
    @JvmStatic fun getShamrockDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.SHAMROCK.fieldName)
    @JvmStatic fun getIndigoDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.INDIGO.fieldName)
    @JvmStatic fun getBananaDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.BANANA.fieldName)
    @JvmStatic fun getCeruleanDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.CERULEAN.fieldName)
    @JvmStatic fun getAcornDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.ACORN.fieldName)
    @JvmStatic fun getMauveDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.MAUVE.fieldName)
    @JvmStatic fun getMaroonDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.MAROON.fieldName)
    @JvmStatic fun getGrapeDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.GRAPE.fieldName)
    @JvmStatic fun getNavyDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.NAVY.fieldName)
    @JvmStatic fun getSapDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.SAP.fieldName)
    @JvmStatic fun getAmberDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.AMBER.fieldName)
    @JvmStatic fun getSageDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.SAGE.fieldName)
    @JvmStatic fun getVelvetDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.VELVET.fieldName)
    @JvmStatic fun getMoldDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ElsDyeModDyeColor.Values.MOLD.fieldName)
}
