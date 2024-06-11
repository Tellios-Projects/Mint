package net.leafenzo.mint.util.old

    @JvmInline
    value class Color(val value: Int) {
        constructor(a: Int = 255, r: Int, g: Int, b: Int) : this(
                value = (a and 0xff shl 24) or (r and 0xff shl 16) or (g and 0xff shl 8) or (b and 0xff)
        )
}



    //Sources:
// ReMod Studios - https://github.com/ReMod-Studios/remod-core/blob/cf066c5d9652f56dad1bd024ad5767899f8cd1af/common/src/main/kotlin/io/github/remodstudios/remodcore/Extensions.kt#L60