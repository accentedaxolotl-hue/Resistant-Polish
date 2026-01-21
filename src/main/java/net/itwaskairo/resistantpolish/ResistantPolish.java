package net.itwaskairo.resistantpolish;

import net.itwaskairo.resistantpolish.config.ResistantPolishConfig;
import net.itwaskairo.resistantpolish.items.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ResistantPolish.MODID)
public class ResistantPolish
{
    public static final String MODID = "resistantpolish";

    public ResistantPolish(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModCreativeTab.TABS.register(modEventBus);

        context.registerConfig(
                ModConfig.Type.COMMON,
                ResistantPolishConfig.COMMON_SPEC
        );
        context.registerConfig(
                ModConfig.Type.SERVER,
                ResistantPolishConfig.SERVER_SPEC
        );

        MinecraftForge.EVENT_BUS.register(this);
    }
}