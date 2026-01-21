package net.itwaskairo.resistantpolish.items.custom.consumable;

import net.itwaskairo.resistantpolish.events.data.ResistantPolishValueData;
import net.itwaskairo.resistantpolish.items.base.PolishItemBase;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LeftoverResistantPolishItem extends PolishItemBase {
    public LeftoverResistantPolishItem(Properties properties) { super(properties); }

    @Override
    protected void applyPolish(Player player, Level level, BlockPos pos) {
        ItemStack stack = player.getMainHandItem();

        int leftoverValue = Math.round (getStoredResistance(stack) / 1.25F) ;
        ResistantPolishValueData.get(level)
                    .addResistance(pos, leftoverValue);
    }


    public static int getStoredResistance(ItemStack stack) {
        if (stack.hasTag() && stack.getTag().contains("StoredPolishValue")) {
            return stack.getTag().getInt("StoredPolishValue");
        }
        return 0;
    }

    @Override
    protected int getResistanceValue() {
        return 0; // not needed
    }
}
