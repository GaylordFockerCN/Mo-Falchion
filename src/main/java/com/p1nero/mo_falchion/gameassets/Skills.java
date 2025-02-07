package com.p1nero.mo_falchion.gameassets;

import com.p1nero.invincible.api.events.TimeStampedEvent;
import com.p1nero.invincible.conditions.*;
import com.p1nero.invincible.skill.api.ComboNode;
import com.p1nero.mo_falchion.MoFalchionMod;
import com.p1nero.mo_falchion.skill.FalchionComboAttack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.data.reloader.SkillManager;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

@Mod.EventBusSubscriber(modid = MoFalchionMod.MOD_ID)
public class Skills {
    public static Skill FALCHION_COMBO;

    public static void registerSkills() {
        ComboNode root = ComboNode.create();
        ComboNode a = ComboNode.create();
        ComboNode blockAttack = ComboNode.createNode(()->Animations.FALCHION_BLOCK_ATTACK).setNotCharge(true).setCooldown(1).addCondition(new ParrySuccessCondition()).setPriority(3);
        ComboNode jumpAttack = ComboNode.createNode(() -> Animations.FALCHION_AIR_ATTACK).setNotCharge(true).addCondition(new JumpCondition()).setPriority(2);
        ComboNode dashAttack = ComboNode.createNode(() -> Animations.FALCHION_DASH).setNotCharge(true).addCondition(new SprintingCondition()).setPriority(1).setCanBeInterrupt(false);
        ComboNode auto1 = ComboNode.createNode(() -> Animations.FALCHION_AUTO1);
        a.addConditionAnimation(blockAttack).addConditionAnimation(jumpAttack).addConditionAnimation(dashAttack).addConditionAnimation(auto1);
        ComboNode aa = ComboNode.createNode(() -> Animations.FALCHION_AUTO2);
        ComboNode aaa = ComboNode.createNode(() -> Animations.FALCHION_AUTO3);
        ComboNode aaaa = ComboNode.createNode(() -> Animations.FALCHION_AUTO4).setCanBeInterrupt(false);
        ComboNode heavy = ComboNode.createNode(() -> Animations.FALCHION_HEAVY)
                .addCondition(new StackCondition(1, Integer.MAX_VALUE))
                .addCondition(new CooldownCondition(false))
                .setNotCharge(true)
                .setCooldown(300)
                .addTimeEvent(new TimeStampedEvent(0.15F, (entityPatch -> {
                    if (entityPatch instanceof ServerPlayerPatch serverPlayerPatch) {
                        SkillContainer container = serverPlayerPatch.getSkill(SkillSlots.WEAPON_INNATE);
                        container.getSkill().setStackSynchronize(serverPlayerPatch, container.getStack() - 1);
                        container.getSkill().setConsumptionSynchronize(serverPlayerPatch, 1);
                    }
                })));
        ComboNode comboB = ComboNode.createNode(() -> Animations.FALCHION_HEAVY_IN_COMBO).setNotCharge(true);
        a.key2(comboB);
        a.key1(aa);
        aa.key1(aaa);
        aa.key2(comboB);
        aaa.key1(aaaa);
        aaa.key2(comboB);
        aaaa.key2(comboB);
        root.key1(a);
        root.key1_2(heavy);
        SkillManager.register(FalchionComboAttack::new, FalchionComboAttack.createComboBasicAttack().setCombo(root).setShouldDrawGui(true), MoFalchionMod.MOD_ID, "falchion_combo");
    }

    @SubscribeEvent
    public static void BuildSkills(SkillBuildEvent event) {
        FALCHION_COMBO =  event.build(MoFalchionMod.MOD_ID, "falchion_combo");
    }

}
