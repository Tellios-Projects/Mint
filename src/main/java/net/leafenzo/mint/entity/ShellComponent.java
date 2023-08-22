//package net.leafenzo.mint.entity;
//import net.minecraft.entity.data.DataTracker;
//import net.minecraft.entity.data.TrackedData;
//import net.minecraft.nbt.NbtCompound;
//

// UNUSED
//public class ShellComponent {
//    private final DataTracker dataTracker;
//    private final TrackedData<Float> shell_health;
//
//    public ShellComponent(DataTracker dataTracker, TrackedData<Float> shellHealth) {
//        this.dataTracker = dataTracker;
//        this.shell_health = shellHealth;
//    }
//    public void writeNbt(NbtCompound nbt) {
//        nbt.putFloat("Shell_Health", this.getShellHealth());
//    }
//    public void readNbt(NbtCompound nbt) {
//        this.setShellHealth(nbt.getFloat("Shell_Health"));
//    }
//    public void setShellHealth(float health) {
//        this.dataTracker.set(this.shell_health, health);
//    }
//    public float getShellHealth() {
//        return this.dataTracker.get(this.shell_health);
//    }
//}
//
