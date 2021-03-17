package com.radish.mythscapes.datagen.lang_providers;

import com.radish.mythscapes.api.impl.SnailTypeRegister;
import com.radish.mythscapes.client.GuiTexts;
import com.radish.mythscapes.common.register.*;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;

public class MythLanguageProviderEnglishUS extends MythBaseLangProvider {

    public MythLanguageProviderEnglishUS(DataGenerator gen) {
        super(gen, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add("itemGroup.mythscapes", "Mythscapes");

        this.addBlock(MythBlocks.GILDED_GALVITE, "Gilded Galvite");
        this.addBlock(MythBlocks.BEJEWELED_GALVITE, "Bejeweled Galvite");
        this.addBlock(MythBlocks.POWERED_GALVITE, "Powered Galvite");
        this.addBlock(MythBlocks.GALVITE, "Galvite");
        this.addBlock(MythBlocks.GALVITE_SLAB, "Galvite Slab");
        this.addBlock(MythBlocks.GALVITE_VERTICAL_SLAB, "Galvite Vertical Slab");
        this.addBlock(MythBlocks.GALVITE_STAIRS, "Galvite Stairs");
        this.addBlock(MythBlocks.GALVITE_WALL, "Galvite Wall");
        this.addBlock(MythBlocks.POLISHED_GALVITE, "Polished Galvite");
        this.addBlock(MythBlocks.POLISHED_GALVITE_SLAB, "Polished Galvite Slab");
        this.addBlock(MythBlocks.POLISHED_GALVITE_VERTICAL_SLAB, "Polished Galvite Vertical Slab");
        this.addBlock(MythBlocks.POLISHED_GALVITE_STAIRS, "Polished Galvite Stairs");
        this.addBlock(MythBlocks.POLISHED_GALVITE_WALL, "Polished Galvite Wall");
        this.addBlock(MythBlocks.POLISHED_GALVITE_PRESSURE_PLATE, "Polished Galvite Pressure Plate");
        this.addBlock(MythBlocks.POLISHED_GALVITE_BUTTON, "Polished Galvite Button");
        this.addBlock(MythBlocks.POLISHED_GALVITE_BRICKS, "Polished Galvite Bricks");
        this.addBlock(MythBlocks.POLISHED_GALVITE_BRICK_SLAB, "Polished Galvite Brick Slab");
        this.addBlock(MythBlocks.POLISHED_GALVITE_BRICK_VERTICAL_SLAB, "Polished Galvite Brick Vertical Slab");
        this.addBlock(MythBlocks.POLISHED_GALVITE_BRICK_STAIRS, "Polished Galvite Brick Stairs");
        this.addBlock(MythBlocks.POLISHED_GALVITE_BRICK_WALL, "Polished Galvite Brick Wall");
        this.addBlock(MythBlocks.CHISELED_POLISHED_GALVITE, "Chiseled Polished Galvite");
        this.addBlock(MythBlocks.GALVITE_SHINGLES, "Galvite Shingles");
        this.addBlock(MythBlocks.GALVITE_SHINGLE_SLAB, "Galvite Shingle Slab");
        this.addBlock(MythBlocks.GALVITE_SHINGLE_VERTICAL_SLAB, "Galvite Shingle Vertical Slab");
        this.addBlock(MythBlocks.GALVITE_SHINGLE_STAIRS, "Galvite Shingle Stairs");
        this.addBlock(MythBlocks.GILDED_GALVITE_BRICKS, "Gilded Galvite Bricks");
        this.addBlock(MythBlocks.GILDED_GALVITE_BRICK_SLAB, "Gilded Galvite Brick Slab");
        this.addBlock(MythBlocks.GILDED_GALVITE_BRICK_VERTICAL_SLAB, "Gilded Galvite Brick Vertical Slab");
        this.addBlock(MythBlocks.GILDED_GALVITE_BRICK_STAIRS, "Gilded Galvite Brick Stairs");
        this.addBlock(MythBlocks.GILDED_GALVITE_BRICK_WALL, "Gilded Galvite Brick Wall");
        this.addBlock(MythBlocks.BEJEWELED_GALVITE_BRICKS, "Bejeweled Galvite Bricks");
        this.addBlock(MythBlocks.BEJEWELED_GALVITE_BRICK_SLAB, "Bejeweled Galvite Brick Slab");
        this.addBlock(MythBlocks.BEJEWELED_GALVITE_BRICK_VERTICAL_SLAB, "Bejeweled Galvite Brick Vertical Slab");
        this.addBlock(MythBlocks.BEJEWELED_GALVITE_BRICK_STAIRS, "Bejeweled Galvite Brick Stairs");
        this.addBlock(MythBlocks.BEJEWELED_GALVITE_BRICK_WALL, "Bejeweled Galvite Brick Wall");
        this.addBlock(MythBlocks.POWERED_GALVITE_BRICKS, "Powered Galvite Bricks");
        this.addBlock(MythBlocks.POWERED_GALVITE_BRICK_SLAB, "Powered Galvite Brick Slab");
        this.addBlock(MythBlocks.POWERED_GALVITE_BRICK_VERTICAL_SLAB, "Powered Galvite Brick Vertical Slab");
        this.addBlock(MythBlocks.POWERED_GALVITE_BRICK_STAIRS, "Powered Galvite Brick Stairs");
        this.addBlock(MythBlocks.POWERED_GALVITE_BRICK_WALL, "Powered Galvite Brick Wall");
        this.addBlock(MythBlocks.TROLLSTONE, "Trollstone");
        this.addBlock(MythBlocks.TROLLSTONE_SLAB, "Trollstone Slab");
        this.addBlock(MythBlocks.TROLLSTONE_VERTICAL_SLAB, "Trollstone Vertical Slab");
        this.addBlock(MythBlocks.TROLLSTONE_STAIRS, "Trollstone Stairs");
        this.addBlock(MythBlocks.TROLLSTONE_WALL, "Trollstone Wall");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE, "Polished Trollstone");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_SLAB, "Polished Trollstone Slab");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_VERTICAL_SLAB, "Polished Trollstone Vertical Slab");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_STAIRS, "Polished Trollstone Stairs");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_WALL, "Polished Trollstone Wall");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_PRESSURE_PLATE, "Polished Trollstone Pressure Plate");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_BUTTON, "Polished Trollstone Button");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_BRICKS, "Polished Trollstone Bricks");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_BRICK_SLAB, "Polished Trollstone Brick Slab");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_BRICK_VERTICAL_SLAB, "Polished Trollstone Brick Vertical Slab");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_BRICK_STAIRS, "Polished Trollstone Brick Stairs");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_BRICK_WALL, "Polished Trollstone Brick Wall");
        this.addBlock(MythBlocks.POLISHED_TROLLSTONE_PILLAR, "Polished Trollstone Pillar");
        this.addBlock(MythBlocks.CHISELED_POLISHED_TROLLSTONE, "Chiseled Polished Trollstone");
        this.addBlock(MythBlocks.WOLT_LOG, "Wolt Log");
        this.addBlock(MythBlocks.WOLT_WOOD, "Wolt Wood");
        this.addBlock(MythBlocks.WOLT_LOG_STRIPPED, "Stripped Wolt Log");
        this.addBlock(MythBlocks.WOLT_WOOD_STRIPPED, "Stripped Wolt Log");
        this.addBlock(MythBlocks.WOLT_PLANKS, "Wolt Planks");
        this.addBlock(MythBlocks.WOLT_VERTICAL_PLANKS, "Vertical Wolt Planks");
        this.addBlock(MythBlocks.WOLT_SLAB, "Wolt Slab");
        this.addBlock(MythBlocks.WOLT_VERTICAL_SLAB, "Wolt Vertical Slab");
        this.addBlock(MythBlocks.WOLT_STAIRS, "Wolt Stairs");
        this.addBlock(MythBlocks.WOLT_FENCE, "Wolt Fence");
        this.addBlock(MythBlocks.WOLT_FENCE_GATE, "Wolt Fence Gate");
        this.addBlock(MythBlocks.WOLT_PRESSURE_PLATE, "Wolt Pressure Plate");
        this.addBlock(MythBlocks.WOLT_BUTTON, "Wolt Button");
        this.addBlock(MythBlocks.WOLT_DOOR, "Wolt Door");
        this.addBlock(MythBlocks.WOLT_TRAPDOOR, "Wolt Trapdoor");
        this.addBlock(MythBlocks.WOLT_LADDER, "Wolt Ladder");
        this.addBlock(MythBlocks.WOLT_BOOKSHELF, "Wolt Bookshelf");
        this.addBlock(MythBlocks.WOLT_SIGN, "Wolt Sign");
        this.addBlock(MythBlocks.WOLT_POST, "Wolt Post");
        this.addBlock(MythBlocks.WOLT_POST_STRIPPED, "Stripped Wolt Post");
        this.addBlock(MythBlocks.WOLT_CHEST, "Wolt Chest");
        this.addBlock(MythBlocks.WOLT_TRAPPED_CHEST, "Wolt Trapped Chest");
        this.addBlock(MythBlocks.WOLT_HEDGE, "Wolt Hedge");
        this.addBlock(MythBlocks.WOLT_SAPLING, "Wolt Sapling");
        this.addBlock(MythBlocks.WOLT_LEAVES, "Wolt Leaves");
        this.addBlock(MythBlocks.WOLT_LEAF_CARPET, "Wolt Leaf Carpet");

        /*
        this.addBlock(MythBlocks.VIRIDIAN_STEM, "Viridian Log");
        this.addBlock(MythBlocks.VIRIDIAN_WOOD, "Viridian Wood");
        this.addBlock(MythBlocks.VIRIDIAN_STEM_STRIPPED, "Stripped Viridian Log");
        this.addBlock(MythBlocks.VIRIDIAN_WOOD_STRIPPED, "Stripped Viridian Log");
        this.addBlock(MythBlocks.VIRIDIAN_PLANKS, "Viridian Planks");
        this.addBlock(MythBlocks.VIRIDIAN_SLAB, "Viridian Slab");
        this.addBlock(MythBlocks.VIRIDIAN_STAIRS, "Viridian Stairs");
        this.addBlock(MythBlocks.VIRIDIAN_FENCE, "Viridian Fence");
        this.addBlock(MythBlocks.VIRIDIAN_FENCE_GATE, "Viridian Fence Gate");
        this.addBlock(MythBlocks.VIRIDIAN_PRESSURE_PLATE, "Viridian Pressure Plate");
        this.addBlock(MythBlocks.VIRIDIAN_BUTTON, "Viridian Button");
         */

        this.addBlock(MythBlocks.CHARGED_DANDELION, "Charged Dandelion");
        this.addBlock(MythBlocks.WOLT_POWDER_BLOCK, "Wolt Powder Block");
        this.addBlock(MythBlocks.GOLDEN_WOLT_POWDER_BLOCK, "Golden Wolt Powder Block");
        this.addBlock(MythBlocks.BIOBULB_CLUSTER, "Biobulb Cluster");
        this.addBlock(MythBlocks.ROASTED_BIOBULB_CLUSTER, "Roasted Biobulb Cluster");
        this.addBlock(MythBlocks.BIOBULB_LAMP, "Biobulb Lamp");
        this.addBlock(MythBlocks.STATIC_COTTON_BLOCK, "Static Cotton Block");
        this.addBlock(MythBlocks.STATIC_COTTON_PILES, "Static Cotton Piles");
        this.addBlock(MythBlocks.LAUNCHER_RAIL, "Launcher Rail");
        this.addBlock(MythBlocks.SNAIL_SHELL_BLOCK, "Snail Shell Block");
        this.addBlock(MythBlocks.SNAIL_SHELL_BRICKS, "Snail Shell Bricks");
        this.addBlock(MythBlocks.SNAIL_SHELL_BRICK_SLAB, "Snail Shell Brick Slab");
        this.addBlock(MythBlocks.SNAIL_SHELL_BRICK_VERTICAL_SLAB, "Snail Shell Brick Vertical Slab");
        this.addBlock(MythBlocks.SNAIL_SHELL_BRICK_STAIRS, "Snail Shell Brick Stairs");
        this.addBlock(MythBlocks.SNAIL_SHELL_BRICK_WALL, "Snail Shell Brick Wall");
        this.addBlock(MythBlocks.BEJEWELED_SNAIL_SHELL_BLOCK, "Bejeweled Snail Shell Block");
        this.addBlock(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICKS, "Bejeweled Snail Shell Bricks");
        this.addBlock(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_SLAB, "Bejeweled Snail Shell Brick Slab");
        this.addBlock(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_VERTICAL_SLAB, "Bejeweled Snail Shell Brick Vertical Slab");
        this.addBlock(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_STAIRS, "Bejeweled Snail Shell Brick Stairs");
        this.addBlock(MythBlocks.BEJEWELED_SNAIL_SHELL_BRICK_WALL, "Bejeweled Snail Shell Brick Wall");
        this.addBlock(MythBlocks.LIQUID_SULFUR_FLUID_BLOCK, "Liquid Sulfur");

        this.addItem(MythItems.POND_SERPENT, "Pond Serpent");
        this.addItem(MythItems.BLISTERBERRY, "Blisterberry");
        this.addItem(MythItems.ACTIVATED_BLISTERBERRY, "Activated Blisterberry");
        this.addItem(MythItems.BIOBULB, "Biobulb");
        this.addItem(MythItems.ROASTED_BIOBULB, "Roasted Biobulb");
        this.addItem(MythItems.WOLT_FRUIT, "Wolt Fruit");
        this.addItem(MythItems.GOLDEN_WOLT_FRUIT, "Golden Wolt Fruit");
        this.addItem(MythItems.WOLT_POWDER, "Wolt Powder");
        this.addItem(MythItems.GOLDEN_WOLT_POWDER, "Golden Wolt Powder");
        this.addItem(MythItems.GLOWBALL, "Glowball");
        this.addItem(MythItems.LIQUID_SULFUR_BOTTLE, "Liquid Sulfur Bottle");
        this.addItem(MythItems.POND_SERPENT_FISH_BUCKET, "Bucket of Pond Serpent");
        this.addItem(MythItems.STATIC_COTTON, "Static Cotton");
        this.addItem(MythItems.COTTON_HIDE, "Cotton Hide");
        this.addItem(MythItems.LION_FUR, "Lion Fur");
        this.addItem(MythItems.ANTLER, "Antler");
        this.addItem(MythItems.SNAIL_SHELL, "Snail Shell");
        this.addItem(MythItems.BEJEWELED_SNAIL_SHELL, "Bejeweled Snail Shell");
        this.addItem(MythItems.SNAIL_BUCKET, "Bucket of Snail");
        this.addItem(MythItems.BRUSH, "Brush");
        this.addItem(MythItems.COTTON_HOOD, "Cotton Hood");
        this.addItem(MythItems.COTTON_COAT, "Cotton Coat");
        this.addItem(MythItems.COTTON_PANTS, "Cotton Pants");
        this.addItem(MythItems.COTTON_BOOTS, "Cotton Boots");
        this.addItem(MythItems.BARBARIAN_HOOD, "Barbarian Hood");
        this.addItem(MythItems.WOLT_BOAT, "Wolt Boat");
        //this.addItem(MythItems.VIRIDIAN_BOAT, "Viridian Boat");
        this.addItem(MythItems.LIQUID_SULFUR_BUCKET, "Liquid Sulfur Bucket");
        this.addItem(MythItems.POND_SERPENT_SPAWN_EGG, "Pond Serpent Spawn Egg");
        this.addItem(MythItems.LION_SPAWN_EGG, "Lion Spawn Egg");
        this.addItem(MythItems.FISHBONES_SPAWN_EGG, "Fishbones Spawn Egg");
        this.addItem(MythItems.PYGMY_SNAIL_SPAWN_EGG, "Pygmy Snail Spawn Egg");
        this.addItem(MythItems.DEER_SPAWN_EGG, "Deer Spawn Egg");

        this.addFluid(MythFluids.SULFUR, "Liquid Sulfur");

        this.addEntityType(MythEntities.MYTH_BOAT, "Boat");
        this.addEntityType(MythEntities.BLISTERBERRY, "Blisterberry");
        this.addEntityType(MythEntities.GLOWBALL, "Glowball");
        this.addEntityType(MythEntities.STATIC_COTTON, "Static Cotton");
        this.addEntityType(MythEntities.POND_SERPENT, "Pond Serpent");
        this.addEntityType(MythEntities.LION, "Lion");
        this.addEntityType(MythEntities.FISHBONES, "Fishbones");
        this.addEntityType(MythEntities.PYGMY_SNAIL, "Pygmy Snail");
        this.addEntityType(MythEntities.DEER, "Deer");

        this.addEffect(MythEffects.PETRIFIED, "Petrification [WIP]");
        this.addEffect(MythEffects.STATIC, "Static");
        this.addEffect(MythEffects.VOLATILE, "Volatile");

        this.addBiome(MythBiomes.STATIC_FIELDS, "Static Fields");

        this.addPotionItem(MythPotions.PETRIFICATION, "Potion of Petrification");
        this.addPotionItem(MythPotions.LONG_PETRIFICATION, "Potion of Petrification");
        this.addSplashPotionItem(MythPotions.PETRIFICATION, "Splash Potion of Petrification");
        this.addSplashPotionItem(MythPotions.LONG_PETRIFICATION, "Splash Potion of Petrification");
        this.addLingeringPotionItem(MythPotions.PETRIFICATION, "Lingering Potion of Petrification");
        this.addLingeringPotionItem(MythPotions.LONG_PETRIFICATION, "Lingering Potion of Petrification");
        this.addTippedArrowItem(MythPotions.PETRIFICATION, "Arrow of Petrification");
        this.addTippedArrowItem(MythPotions.LONG_PETRIFICATION, "Arrow of Petrification");

        this.addEnchantment(MythEnchantments.SOOTHING, "Soothing");

        this.addSnailType(SnailTypeRegister.BOG, "Bog");
        this.addSnailType(SnailTypeRegister.FLOWER_FOREST, "Flower Forest");
        this.addSnailType(SnailTypeRegister.JUNGLE, "Jungle");
        this.addSnailType(SnailTypeRegister.ROOFED_FOREST, "Roofed Forest");
        this.addSnailType(SnailTypeRegister.BEJEWELED, "Jeweled");
        this.addSnailType(SnailTypeRegister.MUSHROOM, "Mushroom");
        this.addSnailType(SnailTypeRegister.SWAMP, "Swamp");

        this.addPotion(MythPotions.PETRIFICATION, "Potion of Petrification");
        this.addPotion(MythPotions.LONG_PETRIFICATION, "Potion of Petrification");

        this.addSoundSubtitle("entity.lion_idle", "Lion Idle");
        this.addSoundSubtitle("misc.oof", "Oof");

        this.addDamageSource("volatile_explosion", VICTIM + " exploded");
        this.addDamageSource("static_shock", VICTIM + " was electrocuted to death");
        this.addDamageSource("salt_dehydration", VICTIM + " shriveled up");
        this.addDamageSourceWithAttacker("volatile_explosion", VICTIM + " exploded while fighting " + ATTACKER);
        this.addDamageSourceWithAttacker("static_shock", VICTIM + " was electrocuted to death while fighting " + ATTACKER);
        this.addDamageSourceWithAttacker("salt_dehydration", VICTIM + " shriveled up while fighting " + ATTACKER);

        this.addAdvancementTitle("root", "\"This... is a bucket!\"");
        this.addAdvancementDesc("root", "Dear god... (Pick up a snail with a bucket)");

        // Hwyla stuff
        this.add("config.waila.plugin_mythscapes", "Mythscapes");
        this.addHwylaConfig("show_snail_type", "Show Snail Type");

        this.add(GuiTexts.BRUSH_INFO, "This is a brush! You can right click brushable creatures with this to soothe them and give a short regeneration buff. Some creatures may also drop certain items upon being brushed.");

        this.add(GuiTexts.CONFIG_MAIN_SCREEN_TITLE, "Mythscapes Config");
        this.add(GuiTexts.CONFIG_OH_NO, "Currently no gui config. Here is the physical one:");
        this.add(GuiTexts.CONFIG_OOF, "Oof");
        this.add(GuiTexts.OPEN_CONFIG, "Open Config");
        this.add(GuiTexts.CONFIG_NOT_PRESENT, "Config N/A");
    }
}
