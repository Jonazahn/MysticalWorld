package epicsquid.mysticalworld.entity;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.entity.ai.HealTargetGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;

public class SpiritDeerEntity extends AnimalEntity implements IFlyingAnimal {
  public SpiritDeerEntity(EntityType<? extends SpiritDeerEntity> type, World world) {
    super(type, world);
    this.experienceValue = 3;
    this.moveController = new FlyingMovementController(this, 15, true);
  }

  @Override
  public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
    return super.getBlockPathWeight(pos, worldIn);
    //return worldIn.getBlockState(pos).isAir(world, pos) ? 10.0F : 0.0F;
  }

  @Override
  protected PathNavigator createNavigator(World worldIn) {
    FlyingPathNavigator pathnavigateflying = new FlyingPathNavigator(this, worldIn); /* {
      @Override
      public boolean canEntityStandOnPos(BlockPos pos) {
        return true;
      }
    };*/
    pathnavigateflying.setCanOpenDoors(false);
    pathnavigateflying.setCanEnterDoors(true);
    return pathnavigateflying;
  }

  @Override
  protected void registerGoals() {
    goalSelector.addGoal(4, new HealTargetGoal(this, 1.5d));
    goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
    goalSelector.addGoal(7, new LookRandomlyGoal(this));
  }

  @Override
  public boolean onLivingFall(float distance, float damageMultiplier) {
    return false;
  }

  @Override
  public boolean doesEntityNotTriggerPressurePlate() {
    return true;
  }

  @Override
  protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
  }

  @Override
  public void tick() {
    super.tick();

    Vector3d motion = this.getMotion();
    if (!this.onGround && motion.y < 0.0D) {
      this.setMotion(motion.x, motion.y * 0.6D, motion.z);
    }
    this.rotationYaw = this.rotationYawHead;
  }

  public static AttributeModifierMap.MutableAttribute attributes() {
    return LivingEntity.registerAttributes().createMutableAttribute(Attributes.MAX_HEALTH, 15.0d).createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3d).createMutableAttribute(Attributes.FLYING_SPEED, 0.6d);
  }

  @Override
  @Nonnull
  public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity ageable) {
    return null;
  }

  @Override
  @Nonnull
  public ResourceLocation getLootTable() {
    return new ResourceLocation(MysticalWorld.MODID, "entities/spirit_deer");
  }

  @Override
  public float getStandingEyeHeight(Pose pose, EntitySize size) {
    return this.isChild() ? this.getHeight() : 1.3F;
  }
}