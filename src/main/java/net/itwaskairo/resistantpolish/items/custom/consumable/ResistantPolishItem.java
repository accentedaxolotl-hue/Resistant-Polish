package net.itwaskairo.resistantpolish.items.custom.consumable;

import net.itwaskairo.resistantpolish.config.ResistantPolishConfig;
import net.itwaskairo.resistantpolish.events.data.ResistantPolishValueData;
import net.itwaskairo.resistantpolish.items.base.PolishItemBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ResistantPolishItem extends PolishItemBase {
    public ResistantPolishItem(Properties properties) { super(properties); }

    @Override
    protected void applyPolish(Player player, Level level, BlockPos pos) {

        ResistantPolishValueData.get(level)
                .addResistance(pos, getResistanceValue());
    }

    @Override
    protected int getResistanceValue() {
        return ResistantPolishConfig.COMMON.normalPolishValue.get();
    }
}