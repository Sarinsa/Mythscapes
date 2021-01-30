package com.radish.mythscapes.common.register.registry;

import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MythTileEntities {

    private static Block[] getSignBlocks() {
        return new Block[]{
                MythBlocks.WOLT_SIGN.get(),
                MythBlocks.WOLT_WALL_SIGN.get()
        };
    }

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Mythscapes.MODID);

    public static final RegistryObject<TileEntityType<MythSignTileEntity>> SIGN = register("sign", () -> TileEntityType.Builder.create(MythSignTileEntity::new, getSignBlocks()).build(null));

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<TileEntityType<T>> tileEntityType) {
        return TILE_ENTITIES.register(name, tileEntityType);
    }
}
