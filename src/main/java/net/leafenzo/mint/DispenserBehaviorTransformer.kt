///*
//* Source:
//* ReMod Studios - https://github.com/ReMod-Studios/Voidlands-Java/blob/1f07d4a1b72a103636ee104401a3652714411289/fabric/src/main/kotlin/com/remodstudios/voidlands/fabric/fix_dispenser_behavior/DispenserBehaviorTransformer.kt
// */
//
//package net.leafenzo.mint.fabric.fix_dispenser_behavior
//
//import net.fabricmc.loader.api.FabricLoader
//import net.leafenzo.mint.Super
//import org.apache.logging.log4j.LogManager
//import org.objectweb.asm.Opcodes
//import org.objectweb.asm.tree.*
//
//object DispenserBehaviorTransformer {
//    private val LOGGER = LogManager.getLogger("${Super.MOD_ID}:DispenserBehaviorTransformer")
//
//    private const val NAMESPACE = "intermediary"
//
//    private const val DISPENSER_BEHAVIOR = "net.minecraft.class_2357"
//    private const val REGISTER_DEFAULTS = "method_18346"
//
//    // net/minecraft/block/ShulkerBoxBlock.get (Lnet/minecraft/util/DyeColor;)Lnet/minecraft/block/Block;
//    private const val SHULKER_BOX_BLOCK = "net.minecraft.class_2480"
//    private const val DYE_COLOR = "net.minecraft.class_1767"
//    private const val BLOCK = "net.minecraft.class_2248"
//    private const val GET = "method_10525"
//    private val GET_DESC = "(L${DYE_COLOR.toBinaryName()};)L${BLOCK.toBinaryName()};"
//
//    private fun String.toBinaryName() = replace('.', '/')
//
//    data class RemappedNames(val shulkerBoxBlock: String, val dyeColor: String,
//                             val get: String, val getDesc: String, val hookDesc: String) {
//        val valuesDesc = "()[L$dyeColor;"
//    }
//
//    @JvmStatic fun transform(classNode: ClassNode): Boolean {
//        val mr = FabricLoader.getInstance().mappingResolver
//
//        val registerDefaults = mr.mapMethodName(NAMESPACE, DISPENSER_BEHAVIOR, REGISTER_DEFAULTS, "()V").toBinaryName()
//        val shulkerBoxBlock = mr.mapClassName(NAMESPACE, SHULKER_BOX_BLOCK).toBinaryName()
//        val get = mr.mapMethodName(NAMESPACE, SHULKER_BOX_BLOCK, GET, GET_DESC)
//        val dyeColor = mr.mapClassName(NAMESPACE, DYE_COLOR).toBinaryName()
//        val getDesc = "(L$dyeColor;)L${mr.mapClassName(NAMESPACE, BLOCK).toBinaryName()};"
//        val hookDesc = "(L$dyeColor;)Z"
//
//        val remappedNames = RemappedNames(shulkerBoxBlock, dyeColor, get, getDesc, hookDesc)
//
//        for (methodNode in classNode.methods) {
//            if (methodNode.name == registerDefaults && methodNode.desc == "()V") {
//                return transformMethod(remappedNames, methodNode)
//            }
//        }
//
//        return false
//    }
//
//    private fun transformMethod(remappedNames: RemappedNames, methodNode: MethodNode): Boolean {
//        var inForInit = false
//        var lastInsnIsICONST0 = false
//        var forIIndex: Int = -1
//        var dyeColorIndex: Int = -1
//        var insertBefore: MethodInsnNode? = null
//        var lastLabel: LabelNode? = null
//        var advanceLabel: LabelNode? = null
//        loop@ for (insn in methodNode.instructions) {
//            when (insn) {
//                is InsnNode -> lastInsnIsICONST0 = insn.opcode == Opcodes.ICONST_0
//                is LabelNode -> lastLabel = insn
//                is MethodInsnNode -> {
//                    if (insn.owner == remappedNames.dyeColor && insn.name == "values" && insn.desc == remappedNames.valuesDesc) {
//                        LOGGER.trace("we're in for init!")
//                        inForInit = true
//                        continue@loop
//                    } else
//                        inForInit = false
//                    if (insn.owner == remappedNames.shulkerBoxBlock && insn.name == remappedNames.get && insn.desc == remappedNames.getDesc) {
//                        LOGGER.trace("found ShulkerBoxBlock.get!")
//                        insertBefore = insn
//                    }
//                }
//                is VarInsnNode -> {
//                    if (inForInit && lastInsnIsICONST0 && insn.opcode == Opcodes.ISTORE) {
//                        forIIndex = insn.`var`
//                        LOGGER.trace("found for i var index, it's $forIIndex")
//                    } else if (forIIndex > 0 && insn.opcode == Opcodes.ASTORE) {
//                        dyeColorIndex = insn.`var`
//                        LOGGER.trace("found dye color var index, it's $dyeColorIndex")
//                    }
//                }
//                is IincInsnNode -> {
//                    if (insn.`var` == forIIndex) {
//                        advanceLabel = lastLabel
//                        LOGGER.trace("found the for advance label!")
//                        break@loop
//                    }
//                }
//            }
//        }
//        if (insertBefore == null || advanceLabel == null || dyeColorIndex < 0)
//            return false
//        LOGGER.trace("injecting!!!")
//        val toInsert = InsnList()
//        toInsert.add(MethodInsnNode(Opcodes.INVOKESTATIC,
//            "com.remodstudios.voidlands.fabric.fix_dispenser_behavior.TransformerHooks".toBinaryName(),
//            "isCustomDyeColor",
//            remappedNames.hookDesc))
//        toInsert.add(JumpInsnNode(Opcodes.IFNE, advanceLabel))
//        toInsert.add(VarInsnNode(Opcodes.ALOAD, dyeColorIndex))
//        methodNode.instructions.insertBefore(insertBefore, toInsert)
//        return true
//    }
//}