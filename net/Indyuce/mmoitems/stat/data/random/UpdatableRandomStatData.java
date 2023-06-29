package net.Indyuce.mmoitems.stat.data.random;

import net.Indyuce.mmoitems.stat.type.ItemStat;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface UpdatableRandomStatData<S extends net.Indyuce.mmoitems.stat.data.type.StatData> {
  @NotNull
  S reroll(@NotNull ItemStat paramItemStat, @NotNull S paramS, int paramInt);
}


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\UpdatableRandomStatData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */