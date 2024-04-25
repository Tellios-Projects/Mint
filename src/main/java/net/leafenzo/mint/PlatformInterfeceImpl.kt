/*
* Sources:
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/fabric/src/main/kotlin/com/remodstudios/voidlands/fabric/PlatformInterfaceImpl.kt
 */


package net.leafenzo.mint
import com.chocohead.mm.api.ClassTinkerers
import net.leafenzo.mint.util.ModDyeColor
import net.minecraft.util.DyeColor

@Suppress("unused")
object PlatformInterfaceImpl {
    @JvmStatic fun printHelloWorld() {
        println("Hello Fabric World!")
    }

    @JvmStatic fun getMintDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.MINT.fieldName)
    @JvmStatic fun getPeachDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.PEACH.fieldName)
    @JvmStatic fun getPeriwinkleDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.PERIWINKLE.fieldName)
    @JvmStatic fun getArtichokeDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.ARTICHOKE.fieldName)
    @JvmStatic fun getFuchsiaDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.FUCHSIA.fieldName)
    @JvmStatic fun getVermilionDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.VERMILION.fieldName)
    @JvmStatic fun getShamrockDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.SHAMROCK.fieldName)
    @JvmStatic fun getIndigoDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.INDIGO.fieldName)
    @JvmStatic fun getBananaDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.BANANA.fieldName)
    @JvmStatic fun getCeruleanDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.CERULEAN.fieldName)
    @JvmStatic fun getAcornDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.ACORN.fieldName)
    @JvmStatic fun getMauveDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.MAUVE.fieldName)
    @JvmStatic fun getMaroonDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.MAROON.fieldName)
    @JvmStatic fun getGrapeDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.GRAPE.fieldName)
    @JvmStatic fun getNavyDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.NAVY.fieldName)
    @JvmStatic fun getSapDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.SAP.fieldName)
    @JvmStatic fun getAmberDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.AMBER.fieldName)
    @JvmStatic fun getSageDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.SAGE.fieldName)
    @JvmStatic fun getVelvetDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.VELVET.fieldName)
    @JvmStatic fun getMoldDyeColor(): DyeColor =
            ClassTinkerers.getEnum(DyeColor::class.java, ModDyeColor.Values.MOLD.fieldName)
}
