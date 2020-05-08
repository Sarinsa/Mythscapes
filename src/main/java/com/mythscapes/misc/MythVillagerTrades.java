package com.mythscapes.misc;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythItems;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MythVillagerTrades {

    public static void modifyVanillaTrades() {
        inject(VillagerProfession.SHEPHERD, 5, new VillagerTrades.ItemsForEmeraldsTrade(MythItems.BRUSH.get(), 4, 1, 30));
        remove(VillagerProfession.SHEPHERD, 5, Items.PAINTING);
    }

    private static void inject(VillagerProfession profession, int tradeLevel, ITrade... tradesToAdd) {
        Int2ObjectMap<ITrade[]> tradeMap = VillagerTrades.VILLAGER_DEFAULT_TRADES.get(profession);
        try {
            ITrade[] oldTrades = tradeMap.getOrDefault(tradeLevel, null);
            if (oldTrades != null) {
                ArrayList<ITrade> newTrades = new ArrayList<>();
                Collections.addAll(newTrades, oldTrades);
                Collections.addAll(newTrades, tradesToAdd);

                tradeMap.put(tradeLevel, newTrades.toArray(new ITrade[0]));
            }
        }
        catch (Exception e) {
            Mythscapes.LOGGER.fatal("Failed to inject villager trade for profession " + profession.getRegistryName());
            Mythscapes.LOGGER.fatal("Trade tier: " + tradeLevel);
            Mythscapes.LOGGER.fatal("Trade(s): " + (tradesToAdd == null ? "null" : Arrays.toString(tradesToAdd)));
            e.printStackTrace();
        }
    }

    @SuppressWarnings("all")
    private static void remove(VillagerProfession profession, int tradeLevel, Item itemToRemove) {
        Int2ObjectMap<VillagerTrades.ITrade[]> tradeMap = VillagerTrades.VILLAGER_DEFAULT_TRADES.get(profession);
        try {
            ITrade[] oldTrades = tradeMap.getOrDefault(tradeLevel, null);
            if (oldTrades != null) {
                ArrayList<ITrade> newTrades = new ArrayList<>();
                for (ITrade trade : oldTrades) {
                    // Probably not the safest way to do this
                    if (trade.getOffer(null, null).getSellingStack().getItem() != itemToRemove) {
                        newTrades.add(trade);
                    }
                }
                tradeMap.put(tradeLevel, newTrades.toArray(new ITrade[0]));
            }
        }
        catch (Exception e) {
            Mythscapes.LOGGER.fatal("Failed to remove villager trade for profession " + profession.getRegistryName());
            Mythscapes.LOGGER.fatal("Trade tier: " + tradeLevel);
            Mythscapes.LOGGER.fatal("Attempted item to remove: " + itemToRemove.getRegistryName());
            e.printStackTrace();
        }
    }
}
