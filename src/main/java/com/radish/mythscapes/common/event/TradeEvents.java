package com.radish.mythscapes.common.event;

import com.radish.mythscapes.common.register.MythItems;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TradeEvents {

    @SubscribeEvent
    @SuppressWarnings("all")
    public void onVillagerTrade(VillagerTradesEvent event) {

        if (event.getType() == VillagerProfession.SHEPHERD) {
            event.getTrades().get(5).add(new BasicTrade(4, new ItemStack(MythItems.BRUSH.get()), 3, 0));
            event.getTrades().get(5).removeIf((iTrade -> {
                MerchantOffer offer = iTrade.getOffer(null, null);
                return offer != null && offer.getSellingStack().getItem() == Items.PAINTING;
            }));
        }
    }
}
