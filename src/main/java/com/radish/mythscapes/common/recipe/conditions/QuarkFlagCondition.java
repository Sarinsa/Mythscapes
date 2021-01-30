package com.radish.mythscapes.common.recipe.conditions;

import com.google.gson.JsonObject;
import com.radish.mythscapes.common.core.Mythscapes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.fml.ModList;

public class QuarkFlagCondition implements ICondition {

    private final ResourceLocation id;
    private final String flag;

    public QuarkFlagCondition(String flag) {
        this.id = Mythscapes.resourceLoc("quark_flag");
        this.flag = flag;
    }

    @Override
    public ResourceLocation getID() {
        return this.id;
    }

    @Override
    public boolean test() {
        if (ModList.get().isLoaded("quark")) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", "quark:flag");
            jsonObject.addProperty("flag", this.flag);
            // If I knew one could get conditions with JsonObject
            // sooner, that would have been pretty cool.
            return CraftingHelper.getCondition(jsonObject).test();
        }
        else {
            return false;
        }
    }

    public static class Serializer implements IConditionSerializer<QuarkFlagCondition> {

        private final ResourceLocation id;

        public Serializer(ResourceLocation id) {
            this.id = id;
        }

        @Override
        public void write(JsonObject json, QuarkFlagCondition value) {
            json.addProperty("flag", value.flag);
        }

        @Override
        public QuarkFlagCondition read(JsonObject json) {
            return new QuarkFlagCondition(json.getAsJsonPrimitive("flag").getAsString());
        }

        @Override
        public ResourceLocation getID() {
            return this.id;
        }
    }
}
