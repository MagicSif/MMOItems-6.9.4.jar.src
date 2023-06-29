/*     */ package net.Indyuce.mmoitems.api.item.mmoitem;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.UpgradeTemplate;
/*     */ import net.Indyuce.mmoitems.api.item.ItemReference;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.util.MMOItemReforger;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.GemSocketsData;
/*     */ import net.Indyuce.mmoitems.stat.data.GemstoneData;
/*     */ import net.Indyuce.mmoitems.stat.data.MaterialData;
/*     */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import net.Indyuce.mmoitems.util.Pair;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class MMOItem implements ItemReference {
/*     */   private final Type type;
/*     */   @NotNull
/*  37 */   private final Map<ItemStat, StatData> stats = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final String id;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private final Map<ItemStat, StatHistory> mergeableStatHistory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType() {
/*  56 */     return this.type;
/*     */   }
/*     */   public String getId() {
/*  59 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mergeData(@NotNull ItemStat paramItemStat, @NotNull StatData paramStatData, @Nullable UUID paramUUID) {
/*  74 */     if (paramStatData instanceof net.Indyuce.mmoitems.stat.data.type.Mergeable) {
/*     */ 
/*     */ 
/*     */       
/*  78 */       StatHistory statHistory = StatHistory.from(this, paramItemStat);
/*     */ 
/*     */       
/*  81 */       if (paramUUID != null) {
/*     */ 
/*     */ 
/*     */         
/*  85 */         statHistory.registerGemstoneData(paramUUID, paramStatData);
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/*  92 */         statHistory.registerExternalData(paramStatData);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       setData(paramItemStat, statHistory.recalculate(getUpgradeLevel()));
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 104 */       setData(paramItemStat, paramStatData);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setData(@NotNull ItemStat paramItemStat, @NotNull StatData paramStatData) {
/* 109 */     this.stats.put(paramItemStat, paramStatData);
/*     */   }
/*     */   
/*     */   public void replaceData(@NotNull ItemStat paramItemStat, @NotNull StatData paramStatData) {
/* 113 */     this.stats.replace(paramItemStat, paramStatData);
/*     */   }
/*     */   
/*     */   public void removeData(@NotNull ItemStat paramItemStat) {
/* 117 */     this.stats.remove(paramItemStat);
/*     */   }
/*     */   
/*     */   public StatData getData(@NotNull ItemStat paramItemStat) {
/* 121 */     return this.stats.get(paramItemStat);
/*     */   }
/*     */   public boolean hasData(@NotNull ItemStat paramItemStat) {
/* 124 */     return (this.stats.get(paramItemStat) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<ItemStat> getStats() {
/* 130 */     return this.stats.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStackBuilder newBuilder() {
/* 139 */     return new ItemStackBuilder(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOItem clone() {
/* 150 */     MMOItem mMOItem = new MMOItem(this.type, this.id);
/*     */ 
/*     */     
/* 153 */     for (ItemStat itemStat : this.stats.keySet()) {
/*     */ 
/*     */       
/* 156 */       mMOItem.stats.put(itemStat, this.stats.get(itemStat));
/*     */ 
/*     */       
/* 159 */       StatHistory statHistory = getStatHistory(itemStat);
/* 160 */       if (statHistory != null) mMOItem.setStatHistory(itemStat, statHistory.clone(mMOItem));
/*     */     
/*     */     } 
/*     */     
/* 164 */     return mMOItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOItem(Type paramType, String paramString) {
/* 174 */     this.mergeableStatHistory = new HashMap<>();
/*     */     this.type = paramType;
/*     */     this.id = paramString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StatHistory getStatHistory(@NotNull ItemStat paramItemStat) {
/* 190 */     if (paramItemStat instanceof net.Indyuce.mmoitems.stat.Enchants) return this.mergeableStatHistory.getOrDefault(paramItemStat, StatHistory.from(this, paramItemStat, true));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 198 */       return this.mergeableStatHistory.get(paramItemStat);
/*     */     }
/* 200 */     catch (ClassCastException classCastException) {
/* 201 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<StatHistory> getStatHistories() {
/* 207 */     return new ArrayList<>(this.mergeableStatHistory.values());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatHistory(@NotNull ItemStat paramItemStat, @NotNull StatHistory paramStatHistory) {
/* 216 */     this.mergeableStatHistory.put(paramItemStat, paramStatHistory);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemTier getTier() {
/* 226 */     return hasData(ItemStats.TIER) ? MMOItems.plugin.getTiers().get(((StatData)this.stats.get(ItemStats.TIER)).toString()) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getDamage() {
/* 239 */     byte b = hasData(ItemStats.MAX_DURABILITY) ? SilentNumbers.round(((DoubleData)getData(ItemStats.MAX_DURABILITY)).getValue()) : -1;
/*     */     
/* 241 */     if (b) {
/*     */ 
/*     */       
/* 244 */       NBTItem nBTItem = newBuilder().buildNBT();
/*     */ 
/*     */       
/* 247 */       byte b1 = hasData(ItemStats.CUSTOM_DURABILITY) ? SilentNumbers.round(((DoubleData)getData(ItemStats.CUSTOM_DURABILITY)).getValue()) : b;
/*     */ 
/*     */       
/* 250 */       return b - b1;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 255 */     return hasData(ItemStats.ITEM_DAMAGE) ? SilentNumbers.round(((DoubleData)getData(ItemStats.ITEM_DAMAGE)).getValue()) : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setDamage(int paramInt) {
/* 269 */     if (hasData(ItemStats.UNBREAKABLE)) {
/*     */       return;
/*     */     }
/* 272 */     byte b = hasData(ItemStats.MAX_DURABILITY) ? SilentNumbers.round(((DoubleData)getData(ItemStats.MAX_DURABILITY)).getValue()) : -1;
/*     */     
/* 274 */     if (b) {
/*     */ 
/*     */       
/* 277 */       NBTItem nBTItem = newBuilder().buildNBT();
/*     */ 
/*     */       
/* 280 */       setData(ItemStats.CUSTOM_DURABILITY, (StatData)new DoubleData((b - paramInt)));
/*     */ 
/*     */       
/* 283 */       Material material = hasData(ItemStats.MATERIAL) ? ((MaterialData)getData(ItemStats.MATERIAL)).getMaterial() : Material.GOLD_INGOT;
/* 284 */       double d = paramInt * material.getMaxDurability() / b;
/* 285 */       if (d == 0.0D) {
/*     */         return;
/*     */       }
/* 288 */       setData(ItemStats.ITEM_DAMAGE, (StatData)new DoubleData(d));
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 293 */       setData(ItemStats.ITEM_DAMAGE, (StatData)new DoubleData(paramInt));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgrade() {
/* 308 */     getUpgradeTemplate().upgrade(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasUpgradeTemplate() {
/* 316 */     return (hasData(ItemStats.UPGRADE) && ((UpgradeData)getData(ItemStats.UPGRADE)).getTemplate() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getUpgradeLevel() {
/* 323 */     return hasData(ItemStats.UPGRADE) ? ((UpgradeData)getData(ItemStats.UPGRADE)).getLevel() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxUpgradeLevel() {
/* 332 */     if (hasData(ItemStats.UPGRADE))
/*     */     {
/*     */       
/* 335 */       return ((UpgradeData)getData(ItemStats.UPGRADE)).getMax();
/*     */     }
/*     */ 
/*     */     
/* 339 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public UpgradeTemplate getUpgradeTemplate() {
/* 350 */     Validate.isTrue(hasUpgradeTemplate(), "Item has no upgrade information");
/*     */ 
/*     */     
/* 353 */     UpgradeData upgradeData = (UpgradeData)getData(ItemStats.UPGRADE);
/*     */ 
/*     */     
/* 356 */     return upgradeData.getTemplate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<GemstoneData> getGemStones() {
/* 366 */     if (hasData(ItemStats.GEM_SOCKETS)) {
/*     */ 
/*     */       
/* 369 */       GemSocketsData gemSocketsData = (GemSocketsData)getData(ItemStats.GEM_SOCKETS);
/*     */ 
/*     */       
/* 372 */       return gemSocketsData.getGemstones();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 378 */     return new HashSet<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<Pair<GemstoneData, MMOItem>> extractGemstones() {
/* 399 */     GemSocketsData gemSocketsData = (GemSocketsData)getData(ItemStats.GEM_SOCKETS);
/* 400 */     if (gemSocketsData == null) {
/* 401 */       return new ArrayList<>();
/*     */     }
/*     */     
/* 404 */     ArrayList<Pair> arrayList = new ArrayList();
/* 405 */     for (GemstoneData gemstoneData : gemSocketsData.getGemstones()) {
/* 406 */       MMOItem mMOItem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(gemstoneData.getMMOItemType()), gemstoneData.getMMOItemID());
/* 407 */       if (mMOItem != null) {
/* 408 */         arrayList.add(Pair.of(gemstoneData, mMOItem));
/*     */       }
/*     */     } 
/*     */     
/* 412 */     if (MMOItemReforger.gemstonesRevIDWhenUnsocket) {
/* 413 */       return (List)arrayList;
/*     */     }
/*     */     
/* 416 */     for (StatHistory statHistory : this.mergeableStatHistory.values()) {
/* 417 */       for (Pair pair : arrayList) {
/* 418 */         StatData statData = statHistory.getGemstoneData(((GemstoneData)pair.getKey()).getHistoricUUID());
/* 419 */         if (statData != null)
/* 420 */           ((MMOItem)pair.getValue()).setData(statHistory.getItemStat(), statData); 
/*     */       } 
/*     */     } 
/* 423 */     return (List)arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MMOItem extractGemstone(@NotNull GemstoneData paramGemstoneData) {
/* 438 */     MMOItem mMOItem = MMOItems.plugin.getMMOItem(MMOItems.plugin.getTypes().get(paramGemstoneData.getMMOItemType()), paramGemstoneData.getMMOItemID());
/* 439 */     if (mMOItem == null) {
/* 440 */       return null;
/*     */     }
/*     */     
/* 443 */     if (MMOItemReforger.gemstonesRevIDWhenUnsocket) {
/* 444 */       return mMOItem;
/*     */     }
/*     */     
/* 447 */     for (StatHistory statHistory : this.mergeableStatHistory.values()) {
/* 448 */       StatData statData = statHistory.getGemstoneData(paramGemstoneData.getHistoricUUID());
/* 449 */       if (statData != null) {
/* 450 */         mMOItem.setData(statHistory.getItemStat(), statData);
/*     */       }
/*     */     } 
/* 453 */     return mMOItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeGemStone(@NotNull UUID paramUUID, @Nullable String paramString) {
/* 466 */     if (!hasData(ItemStats.GEM_SOCKETS)) {
/*     */       return;
/*     */     }
/*     */     
/* 470 */     StatHistory statHistory = StatHistory.from(this, ItemStats.GEM_SOCKETS);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 478 */     if (((GemSocketsData)statHistory.getOriginalData()).removeGem(paramUUID, paramString)) {
/*     */       return;
/*     */     }
/*     */     
/* 482 */     for (UUID uUID : statHistory.getAllGemstones()) {
/* 483 */       if (((GemSocketsData)statHistory.getGemstoneData(uUID)).removeGem(paramUUID, paramString)) {
/*     */         return;
/*     */       }
/*     */     } 
/* 487 */     for (StatData statData : statHistory.getExternalData()) {
/* 488 */       if (((GemSocketsData)statData).removeGem(paramUUID, paramString)) {
/*     */         return;
/*     */       }
/*     */     } 
/* 492 */     for (UUID uUID : statHistory.getAllModifiers()) {
/* 493 */       if (((GemSocketsData)statHistory.getModifiersBonus(uUID)).removeGem(paramUUID, paramString))
/*     */         return; 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\mmoitem\MMOItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */