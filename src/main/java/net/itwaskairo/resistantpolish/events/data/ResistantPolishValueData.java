package net.itwaskairo.resistantpolish.events.data;

import net.itwaskairo.resistantpolish.ResistantPolish;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = ResistantPolish.MODID)
public class ResistantPolishValueData extends SavedData {

    private final Map<BlockPos, Integer> resistantPolishValueMap = new HashMap<>();

    public void addResistance(BlockPos pos, int value) {
        int current = resistantPolishValueMap.getOrDefault(pos, 0);
        int maxResistance = 100;
        resistantPolishValueMap.put(pos, Math.min(current + value, maxResistance));
        setDirty();
    }

    public int getResistance(BlockPos pos) {
        return resistantPolishValueMap.getOrDefault(pos, 0);
    }

    public void remove(BlockPos pos) {
        resistantPolishValueMap.remove(pos);
        setDirty();
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();

        for (var entry : resistantPolishValueMap.entrySet()) {
            CompoundTag entryTag = new CompoundTag();
            entryTag.putLong("pos", entry.getKey().asLong());
            entryTag.putInt("polishresistance", entry.getValue());
            list.add(entryTag);
        }

        tag.put("polishresistance", list);
        return tag;
    }

    public static ResistantPolishValueData load(CompoundTag tag) {
        ResistantPolishValueData data = new ResistantPolishValueData();
        ListTag list = tag.getList("polishresistance", Tag.TAG_COMPOUND);

        for (Tag t : list) {
            CompoundTag ct = (CompoundTag) t;
            BlockPos pos = BlockPos.of(ct.getLong("pos"));
            int resistance = ct.getInt("polishresistance");
            data.resistantPolishValueMap.put(pos, resistance);
        }

        return data;
    }

    public static ResistantPolishValueData get(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            return serverLevel.getDataStorage().computeIfAbsent(
                    ResistantPolishValueData::load,
                    ResistantPolishValueData::new,
                    "resistant_polish_resistance"
            );
        }

        throw new RuntimeException("Tried to access BlockResistanceData on the client!");
    }
}