package com.radish.mythscapes.common.register;

import com.radish.mythscapes.common.blocks.compat.ModAbstractSignBlock;
import com.radish.mythscapes.common.blocks.compat.MythChestBlock;
import com.radish.mythscapes.common.core.Mythscapes;
import com.radish.mythscapes.common.register.MythBlocks;
import com.radish.mythscapes.common.tile.MythChestTileEntity;
import com.radish.mythscapes.common.tile.MythSignTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class MythTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Mythscapes.MODID);

    public static final RegistryObject<TileEntityType<MythSignTileEntity>> SIGN = register("sign", () -> TileEntityType.Builder.of(MythSignTileEntity::new, ModAbstractSignBlock.SIGN_BLOCKS.toArray(new Block[0])).build(null));
    public static final RegistryObject<TileEntityType<MythChestTileEntity>> CHEST = register("chest", () -> TileEntityType.Builder.of(MythChestTileEntity::new, MythChestBlock.CHEST_BLOCKS.toArray(new Block[0])).build(null));

    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<TileEntityType<T>> tileEntityType) {
        return TILE_ENTITIES.register(name, tileEntityType);
    }
}
