package com.p1nero.mo_falchion.skill;

import com.google.common.collect.Lists;
import com.p1nero.invincible.skill.ComboBasicAttack;
import com.p1nero.mo_falchion.gameassets.SkillDataKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.*;

public class FalchionComboAttack extends ComboBasicAttack {
    private static final UUID EVENT_UUID = UUID.fromString("d1d114cc-f11f-11ed-a05b-0242ac191981");
    public FalchionComboAttack(Builder builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.HURT_EVENT_PRE, EVENT_UUID, (event -> {
            if(event.isParried()){
                container.getDataManager().setDataSync(SkillDataKeys.BLOCK_SUCCESS_TIMER.get(), 20, event.getPlayerPatch().getOriginal());
            }
        }), -1);
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.HURT_EVENT_PRE, EVENT_UUID);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
        container.getDataManager().setData(SkillDataKeys.BLOCK_SUCCESS_TIMER.get(), Math.max(container.getDataManager().getDataValue(SkillDataKeys.BLOCK_SUCCESS_TIMER.get()) - 1, 0));
    }

    @Override
    public List<Component> getTooltipOnItem(ItemStack itemstack, CapabilityItem cap, PlayerPatch<?> playerCap) {
        List<Component> list = Lists.newArrayList();
        list.add(Component.translatable(this.getTranslationKey()).withStyle(ChatFormatting.GOLD).append(Component.literal(String.format("[%.0f]", this.consumption)).withStyle(ChatFormatting.AQUA)));
        list.add(Component.translatable("skill.mo_falchion.falchion_combo.tooltip1"));
        list.add(Component.literal(""));
        list.add(Component.translatable("skill.mo_falchion.falchion_combo.tooltip2"));
        list.add(Component.literal(""));
        list.add(Component.translatable("skill.mo_falchion.falchion_combo.tooltip3"));
        list.add(Component.literal(""));
        list.add(Component.translatable("skill.mo_falchion.falchion_combo.tooltip4"));
        return list;
    }

}
