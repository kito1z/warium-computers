package com.kito1z.wariumcomputers;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = Wariumcomputers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    // a list of strings that are treated as resource locations for items
    private static final ForgeConfigSpec.ConfigValue<List<? extends String>> WEAPONS_CONF = BUILDER.comment("ids of warium aimable blocks.").defineList("weapons",List.of(
            "ordinance_core","mortar","rotary_auto_cannon","autocannon","light_autocannon","battle_cannon_breech","artillerybreech","rocket_pod","large_rocket_pod","empty_missile_hardpoint","fire_spear_missile_hardpoint","seeker_spear_missile_hardpoint","strike_spear_missile_hardpoint"
    ),(e)->e instanceof String);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static Set<String> WEAPONS = new HashSet<>();
    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        WEAPONS.clear();
        WEAPONS_CONF.get().forEach(s -> {
            WEAPONS.add("crusty_chunks:"+s);
        });
    }
}
