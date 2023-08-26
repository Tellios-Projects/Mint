/*
* Sources:
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/common/src/main/kotlin/com/remodstudios/voidlands/util/VoidlandsDyeColors.kt#L4
 */
package net.leafenzo.mint.util;
import net.leafenzo.mint.PlatformInterfaceImpl
import net.leafenzo.mint.PlatformInterfaceImpl.getArtichokeDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getBananaDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getFuchsiaDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getIndigoDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getMintDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getPeachDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getPeriwinkleDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getShamrockDyeColor
import net.leafenzo.mint.PlatformInterfaceImpl.getVermilionDyeColor
import org.jetbrains.annotations.ApiStatus;

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
                    Values("PEACH", "peach", Color(r = 253, g = 177, b = 140), 61,
                    fireworkColor = Color(r = 251, g = 146, b = 95),
                    signColor = Color(r = 251, g = 146, b = 95)
                )
                val PERIWINKLE =
                    Values("PERIWINKLE", "periwinkle", Color(r = 190, g = 177, b = 245), 6,
                    fireworkColor = Color(r = 153, g = 134, b = 235),
                    signColor = Color(r = 153, g = 134, b = 235)
                )
                val ARTICHOKE =
                    Values("ARTICHOKE", "artichoke", Color(r = 193, g = 216, b = 30), 42,
                    fireworkColor = Color(r = 193, g = 216, b = 30), //potion color = Color(r = 212, g = 112, b = 176)
                    signColor = Color(r = 193, g = 216, b = 30)
                )

                val FUCHSIA =
                    Values("FUCHSIA", "fuchsia", Color(r = 223, g = 24, b = 120), 54,
                    fireworkColor = Color(r = 220, g = 89, b = 152),
                    signColor = Color(r = 220, g = 89, b = 152)
                )
                val VERMILION =
                    Values("VERMILION", "vermilion", Color(r = 251, g = 101, b = 40), 53,
                    fireworkColor = Color(r = 251, g = 101, b = 40),
                    signColor = Color(r = 255, g = 91, b = 30)
                )
                val SHAMROCK =
                    Values("SHAMROCK", "shamrock", Color(r = 35, g = 229, b = 0), 34,
                    fireworkColor = Color(r = 35, g = 229, b = 0),
                    signColor = Color(r = 35, g = 229, b = 0)
                )
                val INDIGO =
                    Values("INDIGO", "indigo", Color(r = 117, g = 49, b = 218), 33,
                    fireworkColor = Color(r = 117, g = 49, b = 218),
                    signColor = Color(r = 117, g = 49, b = 218)
                )

                val BANANA =
                    Values("BANANA", "banana", Color(r = 232, g = 201, b = 133), 3,
                    fireworkColor = Color(r = 226, g = 193, b = 121),
                    signColor = Color(r = 226, g = 193, b = 121)
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
    val VALUES = arrayOf(
            MINT,
            PEACH,
            PERIWINKLE,
            ARTICHOKE,
            FUCHSIA,
            VERMILION,
            SHAMROCK,
            INDIGO,
            BANANA
    )
}