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

}