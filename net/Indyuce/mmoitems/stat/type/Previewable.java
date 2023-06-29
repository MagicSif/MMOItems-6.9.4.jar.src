package net.Indyuce.mmoitems.stat.type;

import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
import org.jetbrains.annotations.NotNull;

public interface Previewable<R extends net.Indyuce.mmoitems.stat.data.random.RandomStatData<S>, S extends net.Indyuce.mmoitems.stat.data.type.StatData> {
  void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull S paramS, @NotNull R paramR) throws IllegalArgumentException;
}


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\Previewable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */