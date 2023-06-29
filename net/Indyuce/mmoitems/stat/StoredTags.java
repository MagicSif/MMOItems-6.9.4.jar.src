/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.data.StoredTagsData;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*    */ import net.Indyuce.mmoitems.stat.type.InternalStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.apache.commons.lang.NotImplementedException;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StoredTags
/*    */   extends ItemStat<RandomStatData<StoredTagsData>, StoredTagsData>
/*    */   implements InternalStat, GemStoneStat
/*    */ {
/*    */   public StoredTags() {
/* 30 */     super("STORED_TAGS", VersionMaterial.OAK_SIGN.toMaterial(), "Stored Tags", new String[] { "You found a secret dev easter egg", "introduced during the 2020 epidemic!" }, new String[] { "all" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public RandomStatData<StoredTagsData> whenInitialized(Object paramObject) {
/* 37 */     throw new NotImplementedException();
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 42 */     throw new NotImplementedException();
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 47 */     throw new NotImplementedException();
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenDisplayed(List<String> paramList, Optional<RandomStatData<StoredTagsData>> paramOptional) {
/* 52 */     throw new NotImplementedException();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StoredTagsData paramStoredTagsData) {
/* 59 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStoredTagsData));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StoredTagsData paramStoredTagsData) {
/* 67 */     return new ArrayList<>(paramStoredTagsData.getTags());
/*    */   }
/*    */   
/*    */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 71 */     paramReadMMOItem.setData(ItemStats.STORED_TAGS, (StatData)new StoredTagsData(paramReadMMOItem.getNBT()));
/*    */   }
/*    */   @Nullable
/*    */   public StoredTagsData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 75 */     return new StoredTagsData(paramArrayList);
/*    */   }
/*    */   @NotNull
/*    */   public StoredTagsData getClearStatData() {
/* 79 */     return new StoredTagsData(new ArrayList());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\StoredTags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */