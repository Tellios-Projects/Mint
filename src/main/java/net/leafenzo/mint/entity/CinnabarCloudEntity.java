package net.leafenzo.mint.entity;

//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.mojang.brigadier.StringReader;
//import com.mojang.brigadier.exceptions.CommandSyntaxException;
//import com.mojang.logging.LogUtils;
//import net.minecraft.block.piston.PistonBehavior;
//import net.minecraft.command.argument.ParticleEffectArgumentType;
//import net.minecraft.entity.*;
//import net.minecraft.entity.data.DataTracker;
//import net.minecraft.entity.data.TrackedData;
//import net.minecraft.entity.data.TrackedDataHandlerRegistry;
//import net.minecraft.entity.effect.StatusEffectInstance;
//import net.minecraft.nbt.NbtCompound;
//import net.minecraft.nbt.NbtList;
//import net.minecraft.particle.ParticleEffect;
//import net.minecraft.particle.ParticleTypes;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionUtil;
//import net.minecraft.potion.Potions;
//import net.minecraft.registry.Registries;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.util.math.MathHelper;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//import org.slf4j.Logger;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//public class CinnabarCloudEntity extends Entity implements Ownable {
//    private static final Logger LOGGER = LogUtils.getLogger();
//    private static final TrackedData<Float> RADIUS;
//    private static final TrackedData<Integer> COLOR;
//    private static final TrackedData<Boolean> WAITING;
//    private static final TrackedData<ParticleEffect> PARTICLE_ID;
//    private Potion potion;
//    private final List<StatusEffectInstance> effects;
//    private final Map<Entity, Integer> affectedEntities;
//    private int duration;
//    private int waitTime;
//    private int reapplicationDelay;
//    private int durationOnUse;
//    private float radiusOnUse;
//    private float radiusGrowth;
//    @Nullable
//    private LivingEntity owner;
//    @Nullable
//    private UUID ownerUuid;
//
//    public CinnabarCloudEntity(EntityType<? extends CinnabarCloudEntity> entityType, World world) {
//        super(entityType, world);
//        this.potion = Potions.HARMING;
//        this.effects = Lists.newArrayList();
//        this.affectedEntities = Maps.newHashMap();
//        this.duration = 600;
//        this.waitTime = 20;
//        this.reapplicationDelay = 20;
//        this.noClip = true;
//    }
//
//    public CinnabarCloudEntity(World world, double x, double y, double z) {
//        this(ModEntityTypes.CINNABAR_CLOUD, world);
//        this.potion = Potions.HARMING;
//        this.duration = 600;
//        this.waitTime = 20;
//        this.reapplicationDelay = 20;
//        this.noClip = true;
//        this.setPosition(x, y, z);
//    }
//
//    protected void initDataTracker() {
//        this.getDataTracker().startTracking(COLOR, 0);
//        this.getDataTracker().startTracking(RADIUS, 3.0F);
//        this.getDataTracker().startTracking(WAITING, false);
//        this.getDataTracker().startTracking(PARTICLE_ID, ParticleTypes.ENTITY_EFFECT);
//    }
//
//    public void setRadius(float radius) {
//        if (!this.getWorld().isClient) {
//            this.getDataTracker().set(RADIUS, MathHelper.clamp(radius, 0.0F, 32.0F));
//        }
//
//    }
//
//    public void calculateDimensions() {
//        double d = this.getX();
//        double e = this.getY();
//        double f = this.getZ();
//        super.calculateDimensions();
//        this.setPosition(d, e, f);
//    }
//
//    public float getRadius() {
//        return (Float)this.getDataTracker().get(RADIUS);
//    }
//
//    public ParticleEffect getParticleType() {
//        return (ParticleEffect)this.getDataTracker().get(PARTICLE_ID);
//    }
//
//    public void setParticleType(ParticleEffect particle) {
//        this.getDataTracker().set(PARTICLE_ID, particle);
//    }
//
//    protected void setWaiting(boolean waiting) {
//        this.getDataTracker().set(WAITING, waiting);
//    }
//
//    public boolean isWaiting() {
//        return (Boolean)this.getDataTracker().get(WAITING);
//    }
//
//    public int getDuration() {
//        return this.duration;
//    }
//
//    public void setDuration(int duration) {
//        this.duration = duration;
//    }
//
//    public void tick() {
//        super.tick();
//        boolean bl = this.isWaiting();
//        float f = this.getRadius();
//        if (this.getWorld().isClient) {
//            if (bl && this.random.nextBoolean()) {
//                return;
//            }
//
//        } else {
//            if (this.age >= this.waitTime + this.duration) {
//                this.discard();
//                return;
//            }
//
//            boolean bl2 = this.age < this.waitTime;
//            if (bl != bl2) {
//                this.setWaiting(bl2);
//            }
//
//            if (bl2) {
//                return;
//            }
//
//            if (this.radiusGrowth != 0.0F) {
//                f += this.radiusGrowth;
//                if (f < 0.5F) {
//                    this.discard();
//                    return;
//                }
//
//                this.setRadius(f);
//            }
//
//            if (this.age % 5 == 0) {
//                this.affectedEntities.entrySet().removeIf((entry) -> {
//                    return this.age >= (Integer)entry.getValue();
//                });
//                List<StatusEffectInstance> list = Lists.newArrayList();
//                Iterator var24 = this.potion.getEffects().iterator();
//
//                while(var24.hasNext()) {
//                    StatusEffectInstance statusEffectInstance = (StatusEffectInstance)var24.next();
//                    list.add(new StatusEffectInstance(statusEffectInstance.getEffectType(), statusEffectInstance.mapDuration((ix) -> {
//                        return ix / 4;
//                    }), statusEffectInstance.getAmplifier(), statusEffectInstance.isAmbient(), statusEffectInstance.shouldShowParticles()));
//                }
//
//                list.addAll(this.effects);
//                if (list.isEmpty()) {
//                    this.affectedEntities.clear();
//                } else {
//                    List<LivingEntity> list2 = this.getWorld().getNonSpectatingEntities(LivingEntity.class, this.getBoundingBox());
//                    if (!list2.isEmpty()) {
//                        Iterator var27 = list2.iterator();
//
//                        while(true) {
//                            double s;
//                            LivingEntity livingEntity;
//                            do {
//                                do {
//                                    do {
//                                        if (!var27.hasNext()) {
//                                            return;
//                                        }
//
//                                        livingEntity = (LivingEntity)var27.next();
//                                    } while(this.affectedEntities.containsKey(livingEntity));
//                                } while(!livingEntity.isAffectedBySplashPotions());
//
//                                double q = livingEntity.getX() - this.getX();
//                                double r = livingEntity.getZ() - this.getZ();
//                                s = q * q + r * r;
//                            } while(!(s <= (double)(f * f)));
//
//                            this.affectedEntities.put(livingEntity, this.age + this.reapplicationDelay);
//                            Iterator var14 = list.iterator();
//
//                            while(var14.hasNext()) {
//                                StatusEffectInstance statusEffectInstance2 = (StatusEffectInstance)var14.next();
//                                statusEffectInstance2.getEffectType().applyInstantEffect(this, this.getOwner(), livingEntity, statusEffectInstance2.getAmplifier(), 0.5);
//                            }
//
//                            if (this.radiusOnUse != 0.0F) {
//                                f += this.radiusOnUse;
//                                if (f < 0.5F) {
//                                    this.discard();
//                                    return;
//                                }
//
//                                this.setRadius(f);
//                            }
//
//                            if (this.durationOnUse != 0) {
//                                this.duration += this.durationOnUse;
//                                if (this.duration <= 0) {
//                                    this.discard();
//                                    return;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//    public float getRadiusOnUse() {
//        return this.radiusOnUse;
//    }
//
//    public void setRadiusOnUse(float radiusOnUse) {
//        this.radiusOnUse = radiusOnUse;
//    }
//
//    public float getRadiusGrowth() {
//        return this.radiusGrowth;
//    }
//
//    public void setRadiusGrowth(float radiusGrowth) {
//        this.radiusGrowth = radiusGrowth;
//    }
//
//    public int getDurationOnUse() {
//        return this.durationOnUse;
//    }
//
//    public void setDurationOnUse(int durationOnUse) {
//        this.durationOnUse = durationOnUse;
//    }
//
//    public int getWaitTime() {
//        return this.waitTime;
//    }
//
//    public void setWaitTime(int waitTime) {
//        this.waitTime = waitTime;
//    }
//
//    public void setOwner(@Nullable LivingEntity owner) {
//        this.owner = owner;
//        this.ownerUuid = owner == null ? null : owner.getUuid();
//    }
//
//    @Nullable
//    public LivingEntity getOwner() {
//        if (this.owner == null && this.ownerUuid != null && this.getWorld() instanceof ServerWorld) {
//            Entity entity = ((ServerWorld)this.getWorld()).getEntity(this.ownerUuid);
//            if (entity instanceof LivingEntity) {
//                this.owner = (LivingEntity)entity;
//            }
//        }
//
//        return this.owner;
//    }
//
//    public void setPotion() {
//        this.potion = Potions.HARMING;
//    }
//
//    protected void readCustomDataFromNbt(NbtCompound nbt) {
//        this.age = nbt.getInt("Age");
//        this.duration = nbt.getInt("Duration");
//        this.waitTime = nbt.getInt("WaitTime");
//        this.reapplicationDelay = nbt.getInt("ReapplicationDelay");
//        this.durationOnUse = nbt.getInt("DurationOnUse");
//        this.radiusOnUse = nbt.getFloat("RadiusOnUse");
//        this.radiusGrowth = nbt.getFloat("RadiusPerTick");
//        this.setRadius(nbt.getFloat("Radius"));
//        if (nbt.containsUuid("Owner")) {
//            this.ownerUuid = nbt.getUuid("Owner");
//        }
//
//        if (nbt.contains("Particle", 8)) {
//            try {
//                this.setParticleType(ParticleEffectArgumentType.readParameters(new StringReader(nbt.getString("Particle")), Registries.PARTICLE_TYPE.getReadOnlyWrapper()));
//            } catch (CommandSyntaxException var5) {
//                LOGGER.warn("Couldn't load custom particle {}", nbt.getString("Particle"), var5);
//            }
//        }
//
//        if (nbt.contains("Potion", 8)) {
//            this.setPotion();
//        }
//
//        if (nbt.contains("Effects", 9)) {
//            NbtList nbtList = nbt.getList("Effects", 10);
//            this.effects.clear();
//
//            for(int i = 0; i < nbtList.size(); ++i) {
//                StatusEffectInstance statusEffectInstance = StatusEffectInstance.fromNbt(nbtList.getCompound(i));
//                if (statusEffectInstance != null) {
//                    this.addEffect(statusEffectInstance);
//                }
//            }
//        }
//
//    }
//
//    public void addEffect(StatusEffectInstance effect) {
//        this.effects.add(effect);
//    }
//
//    protected void writeCustomDataToNbt(NbtCompound nbt) {
//        nbt.putInt("Age", this.age);
//        nbt.putInt("Duration", this.duration);
//        nbt.putInt("WaitTime", this.waitTime);
//        nbt.putInt("ReapplicationDelay", this.reapplicationDelay);
//        nbt.putInt("DurationOnUse", this.durationOnUse);
//        nbt.putFloat("RadiusOnUse", this.radiusOnUse);
//        nbt.putFloat("RadiusPerTick", this.radiusGrowth);
//        nbt.putFloat("Radius", this.getRadius());
//        nbt.putString("Particle", this.getParticleType().asString());
//        if (this.ownerUuid != null) {
//            nbt.putUuid("Owner", this.ownerUuid);
//        }
//
//        if (this.potion != Potions.EMPTY) {
//            nbt.putString("Potion", Registries.POTION.getId(this.potion).toString());
//        }
//
//        if (!this.effects.isEmpty()) {
//            NbtList nbtList = new NbtList();
//            Iterator var3 = this.effects.iterator();
//
//            while(var3.hasNext()) {
//                StatusEffectInstance statusEffectInstance = (StatusEffectInstance)var3.next();
//                nbtList.add(statusEffectInstance.writeNbt(new NbtCompound()));
//            }
//
//            nbt.put("Effects", nbtList);
//        }
//
//    }
//
//    public void onTrackedDataSet(TrackedData<?> data) {
//        if (RADIUS.equals(data)) {
//            this.calculateDimensions();
//        }
//
//        super.onTrackedDataSet(data);
//    }
//
//    public Potion getPotion() {
//        return this.potion;
//    }
//
//    public PistonBehavior getPistonBehavior() {
//        return PistonBehavior.IGNORE;
//    }
//
//    public EntityDimensions getDimensions(EntityPose pose) {
//        return EntityDimensions.changing(this.getRadius() * 2.0F, 0.5F);
//    }
//
//    static {
//        RADIUS = DataTracker.registerData(CinnabarCloudEntity.class, TrackedDataHandlerRegistry.FLOAT);
//        COLOR = DataTracker.registerData(CinnabarCloudEntity.class, TrackedDataHandlerRegistry.INTEGER);
//        WAITING = DataTracker.registerData(CinnabarCloudEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
//        PARTICLE_ID = DataTracker.registerData(CinnabarCloudEntity.class, TrackedDataHandlerRegistry.PARTICLE);
//    }
//}
