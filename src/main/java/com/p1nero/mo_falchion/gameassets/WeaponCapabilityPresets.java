package com.p1nero.mo_falchion.gameassets;

import com.p1nero.mo_falchion.MoFalchionMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = MoFalchionMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> MO_FALCHION = (item) ->
            (CapabilityItem.Builder) WeaponCapability.builder().category(CapabilityItem.WeaponCategories.LONGSWORD)//蹭格挡
                    .styleProvider((entityPatch) -> CapabilityItem.Styles.TWO_HAND)
                    .collider(new MultiOBBCollider(3, 0.5, 1.0, 1.5, 0.0, 0.0, -1.0))
                    .swingSound(EpicFightSounds.WHOOSH_BIG)
                    .hitSound(EpicFightSounds.BLADE_HIT)
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(CapabilityItem.Styles.TWO_HAND, Animations.FALCHION_AUTO1)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> Skills.FALCHION_COMBO)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, Animations.FALCHION_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, Animations.FALCHION_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.RUN, Animations.FALCHION_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.CHASE, Animations.FALCHION_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, Animations.FALCHION_BLOCK)
                    .comboCancel((style) -> false);

    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put("mo_falchion", MO_FALCHION);
    }
}
