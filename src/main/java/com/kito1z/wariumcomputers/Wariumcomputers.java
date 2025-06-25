package com.kito1z.wariumcomputers;

import com.kito1z.wariumcomputers.peripherals.AimablePeripheral;
import com.mojang.logging.LogUtils;
import dan200.computercraft.api.ForgeComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Wariumcomputers.MODID)
public class Wariumcomputers {
    public static final String MODID = "wariumcomputers";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Wariumcomputers() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ForgeComputerCraftAPI.registerPeripheralProvider(new IPeripheralProvider() {
            @Override
            public LazyOptional<IPeripheral> getPeripheral(Level level, BlockPos blockPos, Direction direction) {
                BlockEntity blockEntity = level.getBlockEntity(blockPos);
                if(blockEntity==null) return LazyOptional.empty();
                String id = BlockEntityType.getKey(blockEntity.getType()).toString();
                if(Config.WEAPONS.contains(id)) return LazyOptional.of(()->new AimablePeripheral(blockEntity));
                return LazyOptional.empty();
            }
        });
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
