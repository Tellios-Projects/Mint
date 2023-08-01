/*
* Sources:
* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/common/src/main/kotlin/com/remodstudios/voidlands/util/VoidlandsDyeColors.kt#L4
 */
package net.leafenzo.mint.util;
import net.leafenzo.mint.PlatformInterfaceImpl.getMintDyeColor
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
                Values("MINT", "mint",
                Color(r = 101, g = 255, b = 142),
                61,
                fireworkColor = Color(r = 101, g = 255, b = 142),
                signColor = Color(r = 101, g = 255, b = 142)
                )
        }
    }
    // if you're wondering "why bother", shulker boxes require a dye color to render with the correct color
    @JvmField
    val MINT = getMintDyeColor()

    @JvmField
    val VALUES = arrayOf(MINT)
}