package net.itwaskairo.resistantpolish.events.handler;

import net.itwaskairo.resistantpolish.ResistantPolish;
import net.itwaskairo.resistantpolish.config.ResistantPolishConfig;
import net.itwaskairo.resistantpolish.events.data.ResistantPolishValueData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.level.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ResistantPolish.MODID)
public class ExplosionHandler {

    @SubscribeEvent
    public static void onDetonate(ExplosionEvent.Detonate event){
        Level level = event.getLevel();
        ResistantPolishValueData data = ResistantPolishValueData.get(level);

        event.getAffectedBlocks().removeIf(pos -> {
            int resistance = data.getResistance(pos);

            if (resistance > 0) {

                float value = resistance / 100f;

                if (level instanceof ServerLevel serverLevel) {
                    float soundVolumeLevel = 0.7F + 0.9F * value;
                    float soundPitchLevel = 0.6F + 0.9F * value;
                    serverLevel.playSound(
                            null,
                            pos,
                            SoundEvents.SLIME_SQUISH,
                            SoundSource.BLOCKS,
                            soundVolumeLevel,
                            soundPitchLevel
                    );
                }

                if (value < 1) {
                    float chance = level.random.nextFloat();

                    boolean survives = value > chance;

                    if (survives) {
                        data.addResistance(pos, ResistantPolishConfig.SERVER.resistantPolishDecayValue.get() * resistance / 20);
                    }
                    else { data.remove(pos); }

                    return survives;
                }
                else {
                    float chance = level.random.nextFloat();

                    boolean survives = value > chance;

                    if (survives) {
                        data.addResistance(pos, ResistantPolishConfig.SERVER.resistantPolishDecayValue.get() * resistance / 15);
                    }
                    else { data.remove(pos); }

                    return survives;
                }
            }
            return false;
        });
    }
}