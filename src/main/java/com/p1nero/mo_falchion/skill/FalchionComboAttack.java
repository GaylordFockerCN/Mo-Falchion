package com.p1nero.mo_falchion.skill;

import com.google.common.collect.Lists;
import com.p1nero.invincible.client.keymappings.InvincibleKeyMappings;
import com.p1nero.invincible.skill.ComboBasicAttack;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.*;

public class FalchionComboAttack extends ComboBasicAttack {
    public FalchionComboAttack(Builder builder) {
        super(builder);
    }

    @Override
    public List<Component> getTooltipOnItem(ItemStack itemstack, CapabilityItem cap, PlayerPatch<?> playerCap) {
        List<Component> list = Lists.newArrayList();
        list.add(new TranslatableComponent(this.getTranslationKey()).withStyle(ChatFormatting.GOLD).append(new TextComponent(String.format("[%.0f]", this.consumption)).withStyle(ChatFormatting.AQUA)));
        list.add(new TextComponent("KEY1: ").append(InvincibleKeyMappings.getTranslatableKey1()));
        list.add(new TextComponent("KEY2: ").append(InvincibleKeyMappings.getTranslatableKey2()));
        list.add(new TextComponent(""));
        list.add(new TranslatableComponent("skill.mo_falchion.falchion_combo.tooltip1"));
        list.add(new TextComponent(""));
        list.add(new TranslatableComponent("skill.mo_falchion.falchion_combo.tooltip2"));
        list.add(new TextComponent(""));
        list.add(new TranslatableComponent("skill.mo_falchion.falchion_combo.tooltip3"));
        list.add(new TextComponent(""));
        list.add(new TranslatableComponent("skill.mo_falchion.falchion_combo.tooltip4"));
        return list;
    }

}
