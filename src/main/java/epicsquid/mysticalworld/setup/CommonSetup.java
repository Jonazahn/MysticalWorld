package epicsquid.mysticalworld.setup;

import epicsquid.mysticalworld.MysticalWorld;
import epicsquid.mysticalworld.api.IPlayerShoulderCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapability;
import epicsquid.mysticalworld.capability.AnimalCooldownCapabilityStorage;
import epicsquid.mysticalworld.capability.PlayerShoulderCapability;
import epicsquid.mysticalworld.capability.PlayerShoulderCapabilityStorage;
import epicsquid.mysticalworld.entity.*;
import epicsquid.mysticalworld.events.CapabilityHandler;
import epicsquid.mysticalworld.events.DamageHandler;
import epicsquid.mysticalworld.events.LootHandler;
import epicsquid.mysticalworld.init.*;
import epicsquid.mysticalworld.network.Networking;
import epicsquid.mysticalworld.potions.PotionRecipes;
import epicsquid.mysticalworld.recipe.ingredients.SeedIngredient;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import noobanidus.libs.noobutil.setup.ShadedCommonSetup;

import java.util.Arrays;

@SuppressWarnings("deprecation")
public class CommonSetup {
  public CommonSetup() {
  }

  public void init(FMLCommonSetupEvent event) {
    MysticalWorld.STONE_PLANT = PlantType.get("stone");

    event.enqueueWork(() -> {
      ModStructures.setupStructures();
      ConfiguredStructures.registerStructures();
      CraftingHelper.register(new ResourceLocation(MysticalWorld.MODID, "seeds"), SeedIngredient.Serializer.INSTANCE);
      ModCompost.init();
      ModEntities.registerEntities();
      Networking.INSTANCE.registerMessages();
      CapabilityManager.INSTANCE.register(AnimalCooldownCapability.class, new AnimalCooldownCapabilityStorage(), AnimalCooldownCapability::new);
      CapabilityManager.INSTANCE.register(IPlayerShoulderCapability.class, new PlayerShoulderCapabilityStorage(), PlayerShoulderCapability::new);

      ShadedCommonSetup.init(event);
      PotionRecipes.registerRecipes();

      GlobalEntityTypeAttributes.put(ModEntities.BEETLE.get(), BeetleEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.DEER.get(), DeerEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.FROG.get(), FrogEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SILVER_FOX.get(), SilverFoxEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SPROUT.get(), SproutEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.ENDERMINI.get(), EnderminiEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.LAVA_CAT.get(), LavaCatEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.OWL.get(), OwlEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SILKWORM.get(), SilkwormEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.HELL_SPROUT.get(), HellSproutEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SPIRIT_BEETLE.get(), SpiritBeetleEntity.attributes().create());
      GlobalEntityTypeAttributes.put(ModEntities.SPIRIT_DEER.get(), SpiritDeerEntity.attributes().create());

      ChickenEntity.TEMPTATION_ITEMS = Ingredient.merge(Arrays.asList(ChickenEntity.TEMPTATION_ITEMS, Ingredient.fromItems(ModItems.AUBERGINE_SEEDS.get())));

      FireBlock fire = (FireBlock) Blocks.FIRE;

      fire.setFireInfo(ModBlocks.THATCH.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_FENCE.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_FENCE_GATE.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_SLAB.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_SMALL_POST.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_WIDE_POST.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_STAIRS.get(), 5, 20);
      fire.setFireInfo(ModBlocks.THATCH_WALL.get(), 5, 20);
      fire.setFireInfo(ModBlocks.SIMPLE_THATCH.get(), 5, 20);
      fire.setFireInfo(ModBlocks.CHARRED_PLANKS.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_LOG.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_FENCE.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_FENCE_GATE.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_SLAB.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_SMALL_POST.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_STAIRS.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_WALL.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_WIDE_POST.get(), 1, 1);
      fire.setFireInfo(ModBlocks.CHARRED_WOOD.get(), 1, 1);

    });
  }

  public void serverStarting(FMLServerStartedEvent event) {
  }

  public void serverAboutToStart(FMLServerAboutToStartEvent event) {
  }

  @SuppressWarnings("Duplicates")
  public void registerListeners() {
    MinecraftForge.EVENT_BUS.addListener(DamageHandler::onAttackDamage);
    /*    MinecraftForge.EVENT_BUS.addListener(EntityHandler::onEntityInteract);*/
    // TODO: Temporarily disabled
    //MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onDeath);
    //MinecraftForge.EVENT_BUS.addListener(ShoulderHandler::onRightClickBlock);
    MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, true, LootHandler::onLootLoad);
    MinecraftForge.EVENT_BUS.addListener(LootHandler::onLooting);
    MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityHandler::attachCapability);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onSquidMilked);
    MinecraftForge.EVENT_BUS.addListener(CapabilityHandler::onPlayerJoin);
  }

  public void loadComplete(FMLLoadCompleteEvent event) {
    ModifyLoot.modify();
  }
}
