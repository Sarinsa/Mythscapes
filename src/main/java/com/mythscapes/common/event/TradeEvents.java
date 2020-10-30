package com.mythscapes.common.event;

import com.mythscapes.core.Mythscapes;
import com.mythscapes.register.MythItems;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraftforge.common.BasicTrade;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mythscapes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TradeEvents {

    @SubscribeEvent
    @SuppressWarnings("all")
    public static void onVillagerTrade(VillagerTradesEvent event) {
        // Shepherd
        if (event.getType() == VillagerProfession.SHEPHERD) {
            event.getTrades().get(5).add(new BasicTrade(4, new ItemStack(MythItems.BRUSH.get()), 3, 0));
            event.getTrades().get(5).removeIf((iTrade -> {
                MerchantOffer offer = iTrade.getOffer(null, null);

                return offer != null && offer.getSellingStack().getItem() == Items.PAINTING;
            }));
        }
    }
}
