package net.leafenzo.mint.item.custom;

import net.leafenzo.mint.entity.EmberArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EmberArrowItem extends ArrowItem {
    public EmberArrowItem(Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new EmberArrowEntity(world, shooter);
    }
}
