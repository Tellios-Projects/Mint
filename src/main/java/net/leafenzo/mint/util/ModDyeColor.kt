/*
* Sources:
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/common/src/main/kotlin/com/remodstudios/voidlands/util/VoidlandsDyeColors.kt#L4
 */
package net.leafenzo.mint.util

import net.leafenzo.mint.PlatformInterfaceImpl
import net.leafenzo.mint.PlatformInterfaceImpl.getAcornDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getAmberDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getArtichokeDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getBananaDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getCeruleanDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getFuchsiaDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getGrapeDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getIndigoDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getMaroonDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getMauveDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getMintDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getMoldDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getNavyDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getPeachDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getPeriwinkleDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getSageDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getSapDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getShamrockDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getVelvetDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getVermilionDyeColor
import org.jetbrains.annotations.ApiStatus

object ModDyeColor {
    @ApiStatus.Internal
    data class Values internal constructor(
        val fieldName: String,
        val name: String, val color: Color, val mapColorId: Int,
        val fireworkColor: Color, val signColor: Color
        ) {
@ApiStatus.Internal
        companion object {
                val MINT =
                Values("MINT", "mint", Color(r = 100, g = 249, b = 162), 61,
                fireworkColor = Color(r = 101, g = 255, b = 142),
                signColor = Color(r = 101, g = 255, b = 142)
                )
                val PEACH =
                    Values("PEACH", "peach", Color(r = 253, g = 177, b = 140), 60,
                    fireworkColor = Color(r = 251, g = 146, b = 95),
                    signColor = Color(r = 251, g = 146, b = 95)
                )
                val PERIWINKLE =
                    Values("PERIWINKLE", "periwinkle", Color(r = 190, g = 177, b = 245), 5,
                    fireworkColor = Color(r = 153, g = 134, b = 235),
                    signColor = Color(r = 153, g = 134, b = 235)
                )
                val ARTICHOKE =
                    Values("ARTICHOKE", "artichoke", Color(r = 193, g = 216, b = 30), 41,
                    fireworkColor = Color(r = 193, g = 216, b = 30), //potion color = Color(r = 212, g = 112, b = 176)
                    signColor = Color(r = 193, g = 216, b = 30)
                )

                val FUCHSIA =
                    Values("FUCHSIA", "fuchsia", Color(r = 223, g = 24, b = 120), 53,
                    fireworkColor = Color(r = 220, g = 89, b = 152),
                    signColor = Color(r = 220, g = 89, b = 152)
                )
                val VERMILION =
                    Values("VERMILION", "vermilion", Color(r = 251, g = 101, b = 40), 52,
                    fireworkColor = Color(r = 251, g = 101, b = 40),
                    signColor = Color(r = 255, g = 91, b = 30)
                )
                val SHAMROCK =
                    Values("SHAMROCK", "shamrock", Color(r = 30, g = 188, b = 0), 33,
                    fireworkColor = Color(r = 30, g = 188, b = 0),
                    signColor = Color(r = 30, g = 188, b = 0)
                )
                val INDIGO =
                    Values("INDIGO", "indigo", Color(r = 95, g = 63, b = 169), 32,
                    fireworkColor = Color(r = 95, g = 63, b = 169),
                    signColor = Color(r = 95, g = 63, b = 169)
                )

                val BANANA =
                    Values("BANANA", "banana", Color(r = 232, g = 201, b = 133), 2,
                    fireworkColor = Color(r = 226, g = 193, b = 121),
                    signColor = Color(r = 226, g = 193, b = 121)
                )
                val CERULEAN =
                Values("CERULEAN", "cerulean", Color(r = 67, g = 100, b = 132), 23,
                    fireworkColor = Color(r = 67, g = 100, b = 132),
                    signColor = Color(r = 67, g = 100, b = 132)
                )
                val ACORN =
                Values("ACORN", "acorn", Color(r = 71, g = 50, b = 40), 48,
                    fireworkColor = Color(r = 71, g = 50, b = 40),
                    signColor = Color(r = 71, g = 50, b = 40)
                )
                val MAUVE =
                Values("MAUVE", "mauve", Color(r = 132, g = 67, b = 80), 46,
                    fireworkColor = Color(r = 132, g = 67, b = 80),
                    signColor = Color(r = 132, g = 67, b = 80)
                )

                val MAROON =
                Values("MAROON", "maroon", Color(r = 85, g = 24, b = 16), 35,
                    fireworkColor = Color(r = 85, g = 24, b = 16),
                    signColor = Color(r = 85, g = 24, b = 16)
                )
                val GRAPE =
                Values("GRAPE", "grape", Color(r = 59, g = 0, b = 72), 47,
                    fireworkColor = Color(r = 59, g = 0, b = 72),
                    signColor = Color(r = 59, g = 0, b = 72)
                )
                val NAVY =
                Values("NAVY", "navy", Color(r = 11, g = 24, b = 71), 25,
                    fireworkColor = Color(r = 11, g = 24, b = 71),
                    signColor = Color(r = 11, g = 24, b = 71)
                )
                val SAP =
                Values("SAP", "sap", Color(r = 25, g = 60, b = 35), 7,
                    fireworkColor = Color(r = 25, g = 60, b = 35),
                    signColor = Color(r = 25, g = 60, b = 35)
                )

                val AMBER =
                Values("AMBER", "amber", Color(r = 204, g = 141, b = 19), 40,
                    fireworkColor = Color(r = 204, g = 141, b = 19),
                    signColor = Color(r = 204, g = 141, b = 19)
                )
                val SAGE =
                Values("SAGE", "sage", Color(r = 97, g = 122, b = 84), 61,
                    fireworkColor = Color(r = 97, g = 122, b = 84),
                    signColor = Color(r = 97, g = 122, b = 84)
                )
                val VELVET =
                Values("VELVET", "velvet", Color(r = 159, g = 15, b = 70), 42,
                    fireworkColor = Color(r = 159, g = 15, b = 70),
                    signColor = Color(r = 159, g = 15, b = 70)
                )
                val MOLD =
                Values("MOLD", "mold", Color(r = 106, g = 95, b = 36), 49,
                    fireworkColor = Color(r = 106, g = 95, b = 36),
                    signColor = Color(r = 106, g = 95, b = 36)
                )
        }
    }
    // if you're wondering "why bother", shulker boxes require a dye color to render with the correct color
    @JvmField
    val MINT = getMintDyeColor()
    @JvmField
    val PEACH = getPeachDyeColor()
    @JvmField
    val PERIWINKLE = getPeriwinkleDyeColor()
    @JvmField
    val ARTICHOKE = getArtichokeDyeColor()
    @JvmField
    val FUCHSIA = getFuchsiaDyeColor()
    @JvmField
    val VERMILION = getVermilionDyeColor()
    @JvmField
    val SHAMROCK = getShamrockDyeColor()
    @JvmField
    val INDIGO = getIndigoDyeColor()
    @JvmField
    val BANANA = getBananaDyeColor()
    @JvmField
    val CERULEAN = getCeruleanDyeColor()
    @JvmField
    val ACORN = getAcornDyeColor()
    @JvmField
    val MAUVE = getMauveDyeColor()
    @JvmField
    val MAROON = getMaroonDyeColor()
    @JvmField
    val GRAPE = getGrapeDyeColor()
    @JvmField
    val NAVY = getNavyDyeColor()
    @JvmField
    val SAP = getSapDyeColor()
    @JvmField
    val AMBER = getAmberDyeColor()
    @JvmField
    val SAGE = getSageDyeColor()
    @JvmField
    val VELVET = getVelvetDyeColor()
    @JvmField
    val MOLD = getMoldDyeColor()

    @JvmField
    val VALUES = arrayOf(
            MINT,
            PEACH,
            PERIWINKLE,
            ARTICHOKE,
            FUCHSIA,
            VERMILION,
            SHAMROCK,
            INDIGO,
            BANANA,
            CERULEAN,
            ACORN,
            MAUVE,
            MAROON,
            GRAPE,
            NAVY,
            SAP,
            AMBER,
            SAGE,
            VELVET,
            MOLD
    )
}