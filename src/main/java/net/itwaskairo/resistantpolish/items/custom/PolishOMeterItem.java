package net.itwaskairo.resistantpolish.items.custom;

import net.itwaskairo.resistantpolish.events.data.ResistantPolishValueData;
import net.itwaskairo.resistantpolish.utility.tooltip.PolishOMeterTooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class PolishOMeterItem extends Item {
    public PolishOMeterItem(Properties properties) { super(properties); }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        PolishOMeterTooltip.addTooltip(stack, level, tooltip, flag);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (player == null) return InteractionResult.PASS;

        if (!level.isClientSide) {
            float polishValue = ResistantPolishValueData.get(level)
                    .getResistance(context.getClickedPos());

            context.getItemInHand().getOrCreateTag()
                    .putFloat("PolishValue", polishValue);

            player.sendSystemMessage(
                    Component.translatable(
                            "tooltip.resistantpolish.polish_value", polishValue
                    ));
        }

        if (player.level() instanceof ServerLevel serverLevel) {

            float soundPitch = 1.0F + (float)Math.random() * 0.4F;

            serverLevel.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.SCULK_SHRIEKER_SHRIEK,
                    SoundSource.PLAYERS,
                    1F,
                    soundPitch
            );
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
