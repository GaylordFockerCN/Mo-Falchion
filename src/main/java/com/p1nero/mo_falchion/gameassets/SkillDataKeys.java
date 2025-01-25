package com.p1nero.mo_falchion.gameassets;

import com.p1nero.mo_falchion.MoFalchionMod;
import com.p1nero.mo_falchion.skill.FalchionComboAttack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class SkillDataKeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(new ResourceLocation(EpicFightMod.MODID, "skill_data_keys"), MoFalchionMod.MOD_ID);

    public static final RegistryObject<SkillDataKey<Integer>> BLOCK_SUCCESS_TIMER = DATA_KEYS.register("block_success_timer", () ->
            SkillDataKey.createIntKey(0, false, FalchionComboAttack.class));//是否成功格挡计时器

}
