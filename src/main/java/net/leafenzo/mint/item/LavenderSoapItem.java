package net.leafenzo.mint.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class LavenderSoapItem extends Item {
    public LavenderSoapItem(Settings settings) {
        super(settings);
    }
//    public LavenderSoapItem(ToolMaterial material, Settings settings) {
//        super(material, settings);
//    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if(entity instanceof PassiveEntity passiveEntity) {
            //Summon bubble effects
            //Play sfx (hay bale walk at high volume?)
            //Increase animals age / happiness

            if (!((PlayerEntity)user).getAbilities().creativeMode) {
                stack.damage(1, user, playerx -> playerx.sendToolBreakStatus(hand));
            }
            return ActionResult.SUCCESS;
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
