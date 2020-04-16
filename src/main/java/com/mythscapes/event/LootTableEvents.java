package com.mythscapes.event;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.misc.ModResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.data.LootTableProvider;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class LootTableEvents {

    @SubscribeEvent
    public static void modifyVanillaLootTables(LootTableLoadEvent event) {
        LootTableManager manager = event.getLootTableManager();

        switch(event.getName().toString()) {
            case "minecraft:chests/shipwreck_supply":
                LootTable lootTable = manager.getLootTableFromLocation(new ModResourceLocation("inject/shipwreck_supply"));
        }
    }
}
