package net.leafenzo.mint.item;

import net.leafenzo.mint.Super;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class WaxcapWaxItem
extends AliasedBlockItem
implements SignChangingItem {
    public WaxcapWaxItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        if(HoneycombItem.getWaxedState(blockState).isEmpty()) {
            return super.useOnBlock(context); //TODO Fix double use error against unwaxxed copper blocks
        }
        else { HoneycombItem.getWaxedState(blockState).map(state -> {
                PlayerEntity playerEntity = context.getPlayer();
                ItemStack itemStack = context.getStack();
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos, itemStack);
                }
                itemStack.decrement(1);
                world.setBlockState(blockPos, (BlockState)state, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, state));
                world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_WAXED, blockPos, 0);
                return ActionResult.success(world.isClient);
            });
        }
        return ActionResult.PASS;
    }

    @Override
    public boolean useOnSign(World world, SignBlockEntity signBlockEntity, boolean front, PlayerEntity player) {
        if (signBlockEntity.setWaxed(true)) {
            world.syncWorldEvent(null, WorldEvents.BLOCK_WAXED, signBlockEntity.getPos(), 0);
            return true;
        }
        return false;
    }
}
