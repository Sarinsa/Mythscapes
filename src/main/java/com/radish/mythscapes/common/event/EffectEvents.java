package com.radish.mythscapes.common.event;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EffectEvents {

    // This is being put on ice for now, as there are no
    // suitable events for checking item cooldowns after item interaction.

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onEntityItemUse(PlayerInteractEvent.RightClickItem event) {
        /*
        PlayerEntity player = (PlayerEntity)event.getEntity();
        CooldownTracker cooldownTracker = player.getCooldownTracker();

        if (player.getActivePotionEffect(MythEffects.STATIC.get()) != null && !event.getItemStack().isEmpty()) {
            Mythscapes.LOGGER.info("Has static");
            Item item = event.getItemStack().getItem();

            if (cooldownTracker.hasCooldown(item)) {
                Mythscapes.LOGGER.info("Cooldown applied");
                cooldownTracker.setCooldown(item, (int)cooldownTracker.getCooldown(item, 0.0F) + (20 * 7));
            }
            else {
                Mythscapes.LOGGER.info("Could not apply cooldown");
            }
        }
        */
    }
}
