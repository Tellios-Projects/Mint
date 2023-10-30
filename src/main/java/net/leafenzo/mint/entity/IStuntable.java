package net.leafenzo.mint.entity;

import net.minecraft.entity.passive.PassiveEntity;

public interface IStuntable {
    boolean isStunted();

    void setStunted(boolean stunted);

    static void setStunted(PassiveEntity entity, boolean stunted)
    {
        ((IStuntable)entity).setStunted(stunted);
    }

}
