package com.p1nero.mo_falchion;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.world.item.EpicFightCreativeTabs;
import yesman.epicfight.world.item.WeaponItem;

@Mod(MoFalchionMod.MOD_ID)
public class MoFalchionMod {
    public static final String MOD_ID = "mo_falchion";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final RegistryObject<Item> FALCHION = ITEMS.register("falchion", () -> new WeaponItem(Tiers.NETHERITE, 12, -2.4F, new Item.Properties()) {
        @Override
        public boolean isCorrectToolForDrops(BlockState blockIn) {
            return super.isCorrectToolForDrops(blockIn);
        }
    });
    public MoFalchionMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTab() == EpicFightCreativeTabs.ITEMS.get()){
            event.accept(FALCHION);
        }
    }

}
