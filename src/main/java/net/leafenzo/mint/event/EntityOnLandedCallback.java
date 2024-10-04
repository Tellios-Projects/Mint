//package net.leafenzo.mint.event;
//
//import net.fabricmc.fabric.api.event.Event;
//import net.fabricmc.fabric.api.event.EventFactory;
//import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
//import net.minecraft.util.ActionResult;
//
//public interface EntityOnLandedCallback {
//    Event<EntityOnLandedCallback> EVENT = EventFactory.createArrayBacked(EntityOnLandedCallback.class,
//            (listeners) -> (player, world, hand, entity, hitResult) -> {
//                for (AttackEntityCallback event : listeners) {
//                    ActionResult result = event.interact(player, world, hand, entity, hitResult);
//
//                    if (result != ActionResult.PASS) {
//                        return result;
//                    }
//                }
//
//                return ActionResult.PASS;
//            }
//    );
//}
