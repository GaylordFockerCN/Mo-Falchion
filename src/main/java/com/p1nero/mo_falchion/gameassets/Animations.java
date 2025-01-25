package com.p1nero.mo_falchion.gameassets;

import com.p1nero.mo_falchion.MoFalchionMod;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.MoveCoordFunctions;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.MovementAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.api.utils.LevelUtil;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.damagesource.StunType;

import static yesman.epicfight.gameasset.Animations.ReusableSources.FRACTURE_GROUND_SIMPLE;

@Mod.EventBusSubscriber(modid = MoFalchionMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Animations {

    public static StaticAnimation FALCHION_IDLE;
    public static StaticAnimation FALCHION_WALK;
    public static StaticAnimation FALCHION_RUN;
    public static StaticAnimation FALCHION_AUTO1;
    public static StaticAnimation FALCHION_AUTO2;
    public static StaticAnimation FALCHION_AUTO3;
    public static StaticAnimation FALCHION_AUTO4;
    public static StaticAnimation FALCHION_HEAVY;
    public static StaticAnimation FALCHION_HEAVY_IN_COMBO;
    public static StaticAnimation FALCHION_BLOCK;
    public static StaticAnimation FALCHION_BLOCK_ATTACK;
    public static StaticAnimation FALCHION_AIR_ATTACK;
    public static StaticAnimation FALCHION_DASH;

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(MoFalchionMod.MOD_ID, Animations::build);
    }

    private static void build() {

        HumanoidArmature biped = Armatures.BIPED;
        FALCHION_IDLE = new StaticAnimation(true, "biped/idle", biped);
        FALCHION_WALK = new MovementAnimation(true, "biped/walk", biped)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.5F));
        FALCHION_RUN = new MovementAnimation(true, "biped/run", biped);
        FALCHION_BLOCK = new StaticAnimation(true, "biped/block", biped);
        FALCHION_BLOCK_ATTACK = new BasicAttackAnimation(0.1F, 0.8F, 1.0F, 1.5F, null, biped.toolR, "biped/block_attack", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.53F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .newTimePair(0.0F, 1.2F)
                .addStateRemoveOld(EntityState.ATTACK_RESULT, (damageSource) -> AttackResult.ResultType.BLOCKED)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F));
        FALCHION_DASH = new BasicAttackAnimation(0.1F, 0.23F, 0.3F, 0.8F, null, biped.toolR, "biped/dash_attack", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.6F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F));
        FALCHION_AIR_ATTACK = new BasicAttackAnimation(0.1F, 0.13F, 0.23F, 0.667F, null, biped.toolR, "biped/air_attack", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.7F));
        FALCHION_AUTO1 = new BasicAttackAnimation(0.1F, 0.567F, 0.767F, 0.9F, null, biped.toolR, "biped/auto1", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F));
        FALCHION_AUTO2 = new BasicAttackAnimation(0.1F, 0.4F, 0.7F, 0.7F, null, biped.toolR, "biped/auto2", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.3F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F));
        FALCHION_AUTO3 = new BasicAttackAnimation(0.1F, 0.35F, 0.5F, 0.6F, null, biped.toolR, "biped/auto3", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.0F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F));
        FALCHION_AUTO4 = new BasicAttackAnimation(0.1F, 0.6F, 0.8F, 1.3F, null, biped.toolR, "biped/auto4", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.8F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F))
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.7F, ((livingEntityPatch, staticAnimation, objects) -> {
                    LivingEntity entity = livingEntityPatch.getOriginal();
                    Vec3 pos = entity.position();
                    Vec3 dir = entity.getViewVector(1.0F).normalize().scale(2);
                    Vec3 target = pos.add(dir.x, -1, dir.z);
                    LevelUtil.circleSlamFracture(entity, entity.level(), target, 1.5);
                }), AnimationEvent.Side.SERVER));
        FALCHION_HEAVY_IN_COMBO = new BasicAttackAnimation(0.4F, 0.66F, 0.86F, 1.533F, null, biped.toolR, "biped/heavy_in_combo", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .newTimePair(0.0F, 0.66F)
                .addStateRemoveOld(EntityState.ATTACK_RESULT, (damageSource) -> AttackResult.ResultType.BLOCKED)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F));
        FALCHION_HEAVY = new BasicAttackAnimation(0.1F, 1.6F, 1.8F, 2.2F, null, biped.toolR, "biped/heavy", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(2.4F))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_ON_LINK, false)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 1.333F))
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_UPDATE_TIME, TimePairList.create(0.0F, 1.333F))
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_BEGIN, MoveCoordFunctions.TRACE_DEST_LOCATION_BEGIN)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_TICK, MoveCoordFunctions.TRACE_DEST_LOCATION)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_GET, MoveCoordFunctions.WORLD_COORD)
                .newTimePair(0.0F, 2.0F)
                .addStateRemoveOld(EntityState.ATTACK_RESULT, (damageSource) -> AttackResult.ResultType.BLOCKED)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F))
                .addEvents(AnimationEvent.TimeStampedEvent.create(1.7F, ((livingEntityPatch, staticAnimation, objects) -> {
                    LivingEntity entity = livingEntityPatch.getOriginal();
                    Vec3 pos = entity.position();
                    Vec3 dir = entity.getViewVector(1.0F).normalize().scale(2);
                    Vec3 target = pos.add(dir.x, -1, dir.z);
                    LevelUtil.circleSlamFracture(entity, entity.level(), target, 2);
                }), AnimationEvent.Side.SERVER));
    }

}
