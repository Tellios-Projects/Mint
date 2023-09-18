//package net.leafenzo.mint.block.entity;
//
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
//import net.leafenzo.mint.Super;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.damage.DamageEffects;
//import net.minecraft.entity.damage.DamageSource;
//import net.minecraft.entity.damage.DamageType;
//import net.minecraft.entity.damage.DamageTypes;
//import net.minecraft.registry.*;
//import net.minecraft.util.Identifier;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class ModDamageSources {
//    public static final RegistryKey<DamageType> ARTICHOKING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, new Identifier(Super.MOD_ID, "artichoke_choking"));
//    public static void bootstrap(Registerable<DamageType> damageTypeRegisterable) {
//        damageTypeRegisterable.register(ARTICHOKING, new DamageType("artichokeChoking", 0.1f, DamageEffects.POKING));
//    }
//
//    public static DamageSource of(World world, RegistryKey<DamageType> key) {
//        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
//    }
//
//    public static void registerDamageSources() {
//        new RegistryBuilder().addRegistry(RegistryKeys.DAMAGE_TYPE, DamageTypes::bootstrap);
//    }
//}
