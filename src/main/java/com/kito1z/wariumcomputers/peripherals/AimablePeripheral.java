package com.kito1z.wariumcomputers.peripherals;

import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.lua.LuaFunction;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class AimablePeripheral implements IPeripheral {

    private final BlockEntity weapon;

    public AimablePeripheral(BlockEntity weapon) {
        this.weapon = weapon;
    }

    @Override
    public String getType() {
        return "warium_weapon";
    }

    @Override
    public boolean equals(@Nullable IPeripheral iPeripheral) {
        if(iPeripheral instanceof  AimablePeripheral peripheral){
            return weapon==peripheral.weapon;
        }
        return false;
    }

    @LuaFunction
    public void setPitch(double pitch) throws LuaException {
        if(pitch>44.5||pitch<-44.5) throw new LuaException("Pitch is out of range (-44.5 ~ 44.5)");
        weapon.getPersistentData().putDouble("Pitch",pitch/45);
        BlockState state = weapon.getLevel().getBlockState(weapon.getBlockPos());
        weapon.getLevel().sendBlockUpdated(weapon.getBlockPos(),state,state,3);
    }
    @LuaFunction
    public void setYaw(double pitch) throws LuaException {
        if(pitch>44.5||pitch<-44.5) throw new LuaException("Yaw is out of range (-44.5 ~ 44.5)");
        weapon.getPersistentData().putDouble("X",pitch/45);
        BlockState state = weapon.getLevel().getBlockState(weapon.getBlockPos());
        weapon.getLevel().sendBlockUpdated(weapon.getBlockPos(),state,state,3);
    }
}
