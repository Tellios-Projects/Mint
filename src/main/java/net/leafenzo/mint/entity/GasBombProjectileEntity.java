package net.leafenzo.mint.entity;

//public class GasBombProjectileEntity extends ThrownItemEntity {
//    public GasBombProjectileEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
//        super(entityType, world);
//    }
//
//    public GasBombProjectileEntity(LivingEntity livingEntity, World world) {
//        super(ModEntityTypes.GAS_BOMB_PROJECTILE, livingEntity, world);
//    }
//
//    public GasBombProjectileEntity(World world, double x, double y, double z) {
//        super(ModEntityTypes.GAS_BOMB_PROJECTILE, x, y, z, world);
//    }
//
//    @Override
//    public Packet<ClientPlayPacketListener> createSpawnPacket() {
//        return new EntitySpawnS2CPacket(this);
//    }
//
//    @Override
//    protected Item getDefaultItem() {
//        return ModItems.CINNABAR;
//    }
//
//
//    @Override
//    protected void onCollision(HitResult hitResult) {
//        super.onCollision(hitResult);
//        World world = this.getWorld();
//        if (!world.isClient()) {
//            world.sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
//        }
////        this.getWorld().playSound(null, this.getBlockPos(), BFSounds.FLOUR_LAND, SoundCategory.BLOCKS, 1.0f, 0.9f + world.random.nextFloat()/4);
//
//        if (world.isClient()) {
//            return;
//        }
//        if (!this.getWorld().isClient && !this.isRemoved()) {
//            this.discard();
//        }
//    }
//
//    @Override
//    public void handleStatus(byte status) {
//        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
//            CinnabarCloudEntity cinnabarCloudEntity = new CinnabarCloudEntity(this.getWorld(), this.getX(), this.getY(), this.getZ());
//            Entity entity = this.getOwner();
//            if (entity instanceof LivingEntity) {
//                cinnabarCloudEntity.setOwner((LivingEntity)entity);
//            }
//
//            cinnabarCloudEntity.setRadius(3.0F);
//            cinnabarCloudEntity.setRadiusOnUse(-0.5F);
//            cinnabarCloudEntity.setWaitTime(10);
//            cinnabarCloudEntity.setRadiusGrowth(-cinnabarCloudEntity.getRadius() / (float)cinnabarCloudEntity.getDuration());
//            cinnabarCloudEntity.setPotion();
//
//            this.getWorld().spawnEntity(cinnabarCloudEntity);
////            for (int x = 0; x < 16; ++x) {
////                this.getWorld().addParticle(BFParticles.FLOUR_CLOUD_PARTICLE, this.getX()+this.getWorld().random.nextFloat(), this.getY()+this.getWorld().random.nextFloat(), this.getZ()+this.getWorld().random.nextFloat(), this.getWorld().random.nextGaussian()/16 + this.getVelocity().getX(), this.getWorld().random.nextFloat()/8, this.getWorld().random.nextGaussian()/16 + this.getVelocity().getZ());
////            }
////            for (int x = 0; x < 16; ++x) {
////                this.getWorld().addImportantParticle(BFParticles.FLOUR_CLOUD_PARTICLE, this.getX()+this.getWorld().random.nextFloat(), this.getY()+this.getWorld().random.nextFloat(), this.getZ()+this.getWorld().random.nextFloat(), this.getWorld().random.nextGaussian()/16 + this.getVelocity().getX(), this.getWorld().random.nextFloat()/8, this.getWorld().random.nextGaussian()/16 + this.getVelocity().getZ());
////            }
//        }
//    }
//}