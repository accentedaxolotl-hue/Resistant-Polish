package net.itwaskairo.resistantpolish.items.base;

import net.itwaskairo.resistantpolish.events.data.ResistantPolishValueData;
import net.itwaskairo.resistantpolish.utility.inventory.InventoryHelper;
import net.itwaskairo.resistantpolish.utility.tooltip.TooltipHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public abstract class PolishItemBase extends Item {
    protected PolishItemBase(Item.Properties properties) { super(properties); }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        TooltipHelper.addTooltip(stack, level, tooltip, flag);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();

        if (player == null) return InteractionResult.PASS;

        if (!level.isClientSide) {

            int resistance = ResistantPolishValueData.get(level)
                    .getResistance(context.getClickedPos());

            if (resistance >= 100F) {

                if (player.level() instanceof ServerLevel serverLevel) {

                    float soundPitch = 1.0F + (float)Math.random() * 0.4F;

                    serverLevel.playSound(
                            null,
                            player.blockPosition(),
                            SoundEvents.VILLAGER_NO,
                            SoundSource.PLAYERS,
                            0.8F,
                            soundPitch
                    );
                }
                player.sendSystemMessage(
                        Component.translatable("message.resistantpolish.too_polished")
                );

                return InteractionResult.FAIL;
            }
            applyPolish(player, level, context.getClickedPos());
        }

        if (!player.isCreative()) {
            context.getItemInHand().shrink(1);
        }

        if (player.level() instanceof ServerLevel serverLevel) {

            float slimePitch = 1.0F + (float)Math.random() * 0.4F;

            serverLevel.sendParticles(
                    ParticleTypes.ITEM_SLIME,
                    context.getClickedPos().getX() + 0.5,
                    context.getClickedPos().getY() + 0.5,
                    context.getClickedPos().getZ() + 0.5,
                    9,
                    0, 0, 0, 0
            );

            serverLevel.playSound(
                    null,
                    player.blockPosition(),
                    SoundEvents.SLIME_SQUISH,
                    SoundSource.PLAYERS,
                    0.8F,
                    slimePitch
            );
        }

        player.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    protected abstract void applyPolish(Player player, Level level, BlockPos pos);

    protected abstract int getResistanceValue();
}