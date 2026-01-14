package net.itwaskairo.resistantpolish.events.handler;

import net.itwaskairo.resistantpolish.ResistantPolish;
import net.itwaskairo.resistantpolish.config.ResistantPolishConfig;
import net.itwaskairo.resistantpolish.events.data.ResistantPolishValueData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ResistantPolish.MODID)
public class BlockBreakHandler {

    @SubscribeEvent
    public static void onBreak(BlockEvent.BreakEvent event) {

        LevelAccessor levelAccessor = event.getLevel();
        BlockPos pos = event.getPos();

        if (levelAccessor instanceof Level level) {
            if (level.isClientSide()) return;

            ResistantPolishValueData data = ResistantPolishValueData.get(level);

            int resistance = data.getResistance(pos);

            if (resistance > 0) { data.remove(pos); }
        }
    }
}