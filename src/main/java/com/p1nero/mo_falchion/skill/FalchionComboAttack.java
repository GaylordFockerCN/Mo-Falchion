package com.p1nero.mo_falchion.skill;

import com.p1nero.invincible.skill.ComboBasicAttack;
import com.p1nero.mo_falchion.gameassets.SkillDataKeys;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

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
}
