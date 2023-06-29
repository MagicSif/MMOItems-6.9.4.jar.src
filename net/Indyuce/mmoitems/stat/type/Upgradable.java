package net.Indyuce.mmoitems.stat.type;

import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.stat.data.type.StatData;
import net.Indyuce.mmoitems.stat.data.type.UpgradeInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Upgradable {
  @NotNull
  UpgradeInfo loadUpgradeInfo(@Nullable Object paramObject) throws IllegalArgumentException;
  
  @NotNull
  StatData apply(@NotNull StatData paramStatData, @NotNull UpgradeInfo paramUpgradeInfo, int paramInt);
  
  default void preprocess(@NotNull MMOItem item) {}
  
  default void midprocess(@NotNull MMOItem item) {}
  
  default void postprocess(@NotNull MMOItem item) {}
}


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\Upgradable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */