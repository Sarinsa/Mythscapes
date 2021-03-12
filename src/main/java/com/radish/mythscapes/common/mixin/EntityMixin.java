package com.radish.mythscapes.common.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

//TODO: This needs some rethinking
// -
//      Planning to do some spooky stuff
//      here that lets us apply entity
//      fluid motion behavior to our own
//      fluids without tagging them as water.

@Mixin(Entity.class)
public abstract class EntityMixin {

}
