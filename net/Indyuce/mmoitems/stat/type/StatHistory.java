/*      */ package net.Indyuce.mmoitems.stat.type;
/*      */ 
/*      */ import io.lumine.mythic.lib.api.item.ItemTag;
/*      */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*      */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*      */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*      */ import io.lumine.mythic.lib.gson.JsonArray;
/*      */ import io.lumine.mythic.lib.gson.JsonElement;
/*      */ import io.lumine.mythic.lib.gson.JsonObject;
/*      */ import io.lumine.mythic.lib.gson.JsonParser;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.UUID;
/*      */ import net.Indyuce.mmoitems.ItemStats;
/*      */ import net.Indyuce.mmoitems.MMOItems;
/*      */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*      */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*      */ import net.Indyuce.mmoitems.stat.data.EnchantListData;
/*      */ import net.Indyuce.mmoitems.stat.data.GemSocketsData;
/*      */ import net.Indyuce.mmoitems.stat.data.GemstoneData;
/*      */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*      */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*      */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*      */ import net.Indyuce.mmoitems.stat.data.type.UpgradeInfo;
/*      */ import net.Indyuce.mmoitems.util.MMOUtils;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.jetbrains.annotations.Contract;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ public class StatHistory
/*      */ {
/*      */   @NotNull
/*      */   private final ItemStat itemStat;
/*      */   @NotNull
/*      */   MMOItem parent;
/*      */   @NotNull
/*      */   StatData originalData;
/*      */   
/*      */   @NotNull
/*      */   public ItemStat getItemStat() {
/*   45 */     return this.itemStat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isClear() {
/*   60 */     if (getOriginalData() instanceof EnchantListData && (
/*   61 */       (EnchantListData)getOriginalData()).getEnchants().size() != 0)
/*      */     {
/*   63 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*   68 */     if (getAllGemstones().size() > 0 || getExternalData().size() > 0 || getAllModifiers().size() > 0)
/*      */     {
/*   70 */       return false;
/*      */     }
/*      */ 
/*      */     
/*   74 */     if (((Mergeable)getOriginalData()).isClear() && (!isUpgradeable() || getMMOItem().getUpgradeLevel() == 0))
/*      */     {
/*   76 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*   81 */     return getOriginalData().equals(getMMOItem().getData(getItemStat()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public MMOItem getMMOItem() {
/*   95 */     return this.parent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public StatData getOriginalData() {
/*  112 */     if (this.originalData == null) {
/*  113 */       setOriginalData((StatData)getItemStat().getClearStatData());
/*  114 */       MMOItems.print(null, "Stat History for $e{0}$b in $u{1} {2}$b had null original data.", null, new String[] { getItemStat().getId(), getMMOItem().getType().toString(), getMMOItem().getId() });
/*      */     } 
/*  116 */     return this.originalData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setOriginalData(@NotNull StatData paramStatData) {
/*  124 */     this.originalData = paramStatData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*  131 */   public HashMap<UUID, StatData> perModifierBonus = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("null -> null")
/*      */   @Nullable
/*      */   public StatData getModifiersBonus(@Nullable UUID paramUUID) {
/*  141 */     if (paramUUID == null) {
/*  142 */       return null;
/*      */     }
/*  144 */     return this.perModifierBonus.get(paramUUID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerModifierBonus(@NotNull UUID paramUUID, @NotNull StatData paramStatData) {
/*  153 */     this.perModifierBonus.put(paramUUID, paramStatData);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeModifierBonus(@NotNull UUID paramUUID) {
/*  162 */     this.perModifierBonus.remove(paramUUID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public ArrayList<UUID> getAllModifiers() {
/*  170 */     return new ArrayList<>(this.perModifierBonus.keySet());
/*      */   }
/*      */ 
/*      */   
/*      */   public void clearModifiersBonus() {
/*  175 */     this.perModifierBonus.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*  181 */   public HashMap<UUID, StatData> perGemstoneData = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Contract("null -> null")
/*      */   @Nullable
/*      */   public StatData getGemstoneData(@Nullable UUID paramUUID) {
/*  190 */     if (paramUUID == null) {
/*  191 */       return null;
/*      */     }
/*  193 */     return this.perGemstoneData.get(paramUUID);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeGemData(@NotNull UUID paramUUID) {
/*  200 */     this.perGemstoneData.remove(paramUUID);
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public ArrayList<UUID> getAllGemstones() {
/*  205 */     return new ArrayList<>(this.perGemstoneData.keySet());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerGemstoneData(@NotNull UUID paramUUID, @NotNull StatData paramStatData) {
/*  214 */     this.perGemstoneData.put(paramUUID, paramStatData);
/*      */   }
/*      */   
/*      */   public void clearGemstones() {
/*  218 */     this.perGemstoneData.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*  224 */   ArrayList<StatData> perExternalData = new ArrayList<>();
/*      */   
/*      */   static final String enc_Stat = "Stat";
/*      */   
/*      */   static final String enc_OGS = "OGStory";
/*      */   
/*      */   static final String enc_GSS = "Gemstory";
/*      */   
/*      */   static final String enc_EXS = "Exstory";
/*      */   
/*      */   static final String enc_MOD = "Mod";
/*      */   
/*      */   @NotNull
/*      */   public ArrayList<StatData> getExternalData() {
/*  238 */     return this.perExternalData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void consolidateEXSH() {
/*  245 */     Object object = getItemStat().getClearStatData();
/*      */ 
/*      */     
/*  248 */     for (StatData statData : getExternalData()) {
/*  249 */       if (statData == null) {
/*      */         continue;
/*      */       }
/*  252 */       ((Mergeable)object).merge(statData);
/*      */     } 
/*      */ 
/*      */     
/*  256 */     getExternalData().clear();
/*  257 */     registerExternalData((StatData)object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerExternalData(@NotNull StatData paramStatData) {
/*  267 */     this.perExternalData.add(paramStatData);
/*      */   }
/*      */   
/*      */   public void clearExternalData() {
/*  271 */     this.perExternalData.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static StatHistory from(@NotNull MMOItem paramMMOItem, @NotNull ItemStat paramItemStat) {
/*  284 */     return from(paramMMOItem, paramItemStat, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static StatHistory from(@NotNull MMOItem paramMMOItem, @NotNull ItemStat paramItemStat, boolean paramBoolean) {
/*  302 */     if (!paramBoolean) {
/*  303 */       StatHistory statHistory1 = paramMMOItem.getStatHistory(paramItemStat);
/*      */ 
/*      */       
/*  306 */       if (statHistory1 != null)
/*      */       {
/*      */         
/*  309 */         return statHistory1;
/*      */       }
/*      */     } 
/*      */     
/*  313 */     Validate.isTrue(paramItemStat.getClearStatData() instanceof Mergeable, "Non-Mergeable stat data wont have a Stat History; they cannot be modified dynamically in the first place.");
/*      */ 
/*      */     
/*  316 */     StatData statData = paramMMOItem.getData(paramItemStat);
/*      */     
/*  318 */     if (statData == null) {
/*  319 */       statData = (StatData)paramItemStat.getClearStatData();
/*  320 */       paramMMOItem.setData(paramItemStat, statData);
/*      */     }
/*      */     else {
/*      */       
/*  324 */       statData = ((Mergeable)statData).cloneData();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  330 */     StatHistory statHistory = new StatHistory(paramMMOItem, paramItemStat, statData);
/*      */ 
/*      */ 
/*      */     
/*  334 */     paramMMOItem.setStatHistory(paramItemStat, statHistory);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  340 */     return statHistory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StatHistory(@NotNull MMOItem paramMMOItem, @NotNull ItemStat paramItemStat, @NotNull StatData paramStatData) {
/*  348 */     this.itemStat = paramItemStat; this.originalData = paramStatData; this.parent = paramMMOItem;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void purgeGemstones() {
/*  358 */     ArrayList<UUID> arrayList = new ArrayList();
/*  359 */     GemSocketsData gemSocketsData = (GemSocketsData)getMMOItem().getData(ItemStats.GEM_SOCKETS);
/*  360 */     if (gemSocketsData == null) gemSocketsData = new GemSocketsData(new ArrayList());
/*      */ 
/*      */     
/*  363 */     for (UUID uUID : this.perGemstoneData.keySet()) {
/*      */ 
/*      */       
/*  366 */       boolean bool = false;
/*  367 */       for (GemstoneData gemstoneData : gemSocketsData.getGemstones()) {
/*      */ 
/*      */         
/*  370 */         if (gemstoneData != null)
/*      */         {
/*      */           
/*  373 */           if (uUID.equals(gemstoneData.getHistoricUUID())) {
/*      */             
/*  375 */             bool = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         }
/*      */       } 
/*      */       
/*  382 */       if (!bool)
/*      */       {
/*      */         
/*  385 */         arrayList.add(uUID);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  390 */     for (UUID uUID : arrayList)
/*      */     {
/*      */ 
/*      */       
/*  394 */       removeGemData(uUID);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isUpgradeable() {
/*  404 */     if (!getMMOItem().hasUpgradeTemplate()) return false;
/*      */ 
/*      */     
/*  407 */     UpgradeInfo upgradeInfo = getMMOItem().getUpgradeTemplate().getUpgradeInfo(getItemStat());
/*      */ 
/*      */     
/*  410 */     return (upgradeInfo != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public StatData recalculate(int paramInt) {
/*  419 */     return recalculate(true, paramInt);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public StatData recalculate(boolean paramBoolean, int paramInt) {
/*  432 */     if (paramBoolean) purgeGemstones();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  439 */     if (paramInt != 0 && 
/*  440 */       getItemStat() instanceof Upgradable && 
/*  441 */       getMMOItem().hasUpgradeTemplate())
/*      */     {
/*      */       
/*  444 */       return recalculateUpgradeable(paramInt);
/*      */     }
/*      */ 
/*      */     
/*  448 */     return recalculateMergeable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public StatData recalculateUnupgraded() {
/*  457 */     return recalculateUnupgraded(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public StatData recalculateUnupgraded(boolean paramBoolean) {
/*  469 */     if (paramBoolean) purgeGemstones();
/*      */ 
/*      */     
/*  472 */     return recalculateMergeable();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private StatData recalculateUpgradeable(int paramInt) {
/*  489 */     UpgradeInfo upgradeInfo = getMMOItem().getUpgradeTemplate().getUpgradeInfo(getItemStat());
/*      */ 
/*      */     
/*  492 */     if (upgradeInfo == null) return recalculateMergeable();
/*      */ 
/*      */     
/*  495 */     StatData statData1 = ((Mergeable)this.originalData).cloneData();
/*      */ 
/*      */ 
/*      */     
/*  499 */     for (UUID uUID : this.perModifierBonus.keySet())
/*      */     {
/*      */ 
/*      */       
/*  503 */       ((Mergeable)statData1).merge(((Mergeable)getModifiersBonus(uUID)).cloneData());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  508 */     StatData statData2 = ((Upgradable)getItemStat()).apply(statData1, upgradeInfo, paramInt);
/*      */ 
/*      */ 
/*      */     
/*  512 */     for (UUID uUID : this.perGemstoneData.keySet()) {
/*      */ 
/*      */       
/*  515 */       int i = 0;
/*      */ 
/*      */       
/*  518 */       for (GemstoneData gemstoneData : getMMOItem().getGemStones()) {
/*  519 */         if (gemstoneData == null) {
/*      */           continue;
/*      */         }
/*      */         
/*  523 */         if (gemstoneData.getHistoricUUID().equals(uUID)) {
/*      */           
/*  525 */           if (gemstoneData.isScaling()) {
/*      */ 
/*      */             
/*  528 */             i = gemstoneData.getLevel().intValue();
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */           
/*  534 */           i = paramInt;
/*      */         } 
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  541 */       int j = paramInt - i;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  547 */       StatData statData = ((Upgradable)getItemStat()).apply(((Mergeable)getGemstoneData(uUID)).cloneData(), upgradeInfo, j);
/*      */ 
/*      */ 
/*      */       
/*  551 */       ((Mergeable)statData2).merge(((Mergeable)statData).cloneData());
/*      */     } 
/*      */ 
/*      */     
/*  555 */     for (StatData statData : getExternalData())
/*      */     {
/*      */ 
/*      */       
/*  559 */       ((Mergeable)statData2).merge(((Mergeable)statData).cloneData());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  564 */     return statData2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private StatData recalculateMergeable() {
/*  580 */     StatData statData = ((Mergeable)getOriginalData()).cloneData();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  585 */     for (StatData statData1 : this.perModifierBonus.values())
/*      */     {
/*      */       
/*  588 */       ((Mergeable)statData).merge(((Mergeable)statData1).cloneData());
/*      */     }
/*      */ 
/*      */     
/*  592 */     for (StatData statData1 : this.perGemstoneData.values())
/*      */     {
/*  594 */       ((Mergeable)statData).merge(((Mergeable)statData1).cloneData());
/*      */     }
/*      */ 
/*      */     
/*  598 */     for (StatData statData1 : getExternalData())
/*      */     {
/*  600 */       ((Mergeable)statData).merge(((Mergeable)statData1).cloneData());
/*      */     }
/*      */ 
/*      */     
/*  604 */     return statData;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public JsonObject toJson() {
/*  616 */     JsonObject jsonObject = new JsonObject();
/*      */ 
/*      */     
/*  619 */     jsonObject.addProperty("Stat", getItemStat().getId());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  632 */     if (!((Mergeable)getOriginalData()).isClear() || getItemStat() == ItemStats.ENCHANTS) jsonObject.add("OGStory", (JsonElement)ItemTag.compressTags(getItemStat().getAppliedNBT(getOriginalData())));
/*      */ 
/*      */     
/*  635 */     JsonArray jsonArray1 = new JsonArray();
/*      */ 
/*      */     
/*  638 */     for (UUID uUID : getAllGemstones()) {
/*      */ 
/*      */       
/*  641 */       JsonObject jsonObject1 = new JsonObject();
/*      */ 
/*      */ 
/*      */       
/*  645 */       JsonArray jsonArray = ItemTag.compressTags(getItemStat().getAppliedNBT(getGemstoneData(uUID)));
/*      */ 
/*      */       
/*  648 */       jsonObject1.add(uUID.toString(), (JsonElement)jsonArray);
/*      */ 
/*      */       
/*  651 */       jsonArray1.add((JsonElement)jsonObject1);
/*      */     } 
/*      */ 
/*      */     
/*  655 */     if (jsonArray1.size() > 0) jsonObject.add("Gemstory", (JsonElement)jsonArray1);
/*      */ 
/*      */ 
/*      */     
/*  659 */     JsonArray jsonArray2 = new JsonArray();
/*      */ 
/*      */     
/*  662 */     for (StatData statData : getExternalData()) {
/*      */ 
/*      */       
/*  665 */       if (((Mergeable)statData).isClear()) {
/*      */         continue;
/*      */       }
/*  668 */       jsonArray2.add((JsonElement)ItemTag.compressTags(getItemStat().getAppliedNBT(statData)));
/*      */     } 
/*      */ 
/*      */     
/*  672 */     if (jsonArray2.size() > 0) jsonObject.add("Exstory", (JsonElement)jsonArray2);
/*      */ 
/*      */     
/*  675 */     JsonArray jsonArray3 = new JsonArray();
/*      */ 
/*      */     
/*  678 */     for (UUID uUID : getAllModifiers()) {
/*      */ 
/*      */       
/*  681 */       JsonObject jsonObject1 = new JsonObject();
/*      */ 
/*      */ 
/*      */       
/*  685 */       JsonArray jsonArray = ItemTag.compressTags(getItemStat().getAppliedNBT(getModifiersBonus(uUID)));
/*      */ 
/*      */       
/*  688 */       jsonObject1.add(uUID.toString(), (JsonElement)jsonArray);
/*      */ 
/*      */       
/*  691 */       jsonArray3.add((JsonElement)jsonObject1);
/*      */     } 
/*      */ 
/*      */     
/*  695 */     if (jsonArray3.size() > 0) jsonObject.add("Mod", (JsonElement)jsonArray3);
/*      */ 
/*      */     
/*  698 */     return jsonObject;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public String toNBTString() {
/*  712 */     return toJson().toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static StatHistory fromJson(@NotNull MMOItem paramMMOItem, @NotNull JsonObject paramJsonObject) {
/*      */     JsonElement jsonElement1;
/*      */     Object object;
/*  724 */     JsonElement jsonElement2 = null;
/*  725 */     JsonElement jsonElement3 = null;
/*  726 */     JsonElement jsonElement4 = null;
/*  727 */     JsonElement jsonElement5 = null;
/*      */ 
/*      */     
/*  730 */     if (paramJsonObject.has("Stat")) { jsonElement1 = paramJsonObject.get("Stat"); } else { return null; }
/*  731 */      if (paramJsonObject.has("OGStory")) jsonElement2 = paramJsonObject.get("OGStory"); 
/*  732 */     if (paramJsonObject.has("Gemstory")) jsonElement3 = paramJsonObject.get("Gemstory"); 
/*  733 */     if (paramJsonObject.has("Exstory")) jsonElement4 = paramJsonObject.get("Exstory"); 
/*  734 */     if (paramJsonObject.has("Mod")) jsonElement5 = paramJsonObject.get("Mod");
/*      */ 
/*      */     
/*  737 */     if (!jsonElement1.isJsonPrimitive()) return null; 
/*  738 */     if (jsonElement2 != null && !jsonElement2.isJsonArray()) return null; 
/*  739 */     if (jsonElement3 != null && !jsonElement3.isJsonArray()) return null; 
/*  740 */     if (jsonElement4 != null && !jsonElement4.isJsonArray()) return null; 
/*  741 */     if (jsonElement5 != null && !jsonElement5.isJsonArray()) return null;
/*      */ 
/*      */     
/*  744 */     String str = jsonElement1.getAsJsonPrimitive().getAsString();
/*      */ 
/*      */     
/*  747 */     ItemStat itemStat = MMOItems.plugin.getStats().get(str);
/*      */ 
/*      */     
/*  750 */     if (itemStat == null) return null;
/*      */ 
/*      */ 
/*      */     
/*  754 */     if (jsonElement2 != null) {
/*      */ 
/*      */       
/*  757 */       ArrayList<ItemTag> arrayList = ItemTag.decompressTags(jsonElement2.getAsJsonArray());
/*  758 */       object = itemStat.getLoadedNBT(arrayList);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  764 */       object = itemStat.getClearStatData();
/*      */     } 
/*      */ 
/*      */     
/*  768 */     if (object == null) return null;
/*      */ 
/*      */     
/*  771 */     StatHistory statHistory = new StatHistory(paramMMOItem, itemStat, (StatData)object);
/*      */ 
/*      */     
/*  774 */     if (jsonElement3 != null)
/*      */     {
/*      */       
/*  777 */       for (JsonElement jsonElement : jsonElement3.getAsJsonArray()) {
/*      */ 
/*      */         
/*  780 */         if (jsonElement.isJsonObject()) {
/*      */ 
/*      */           
/*  783 */           JsonObject jsonObject = jsonElement.getAsJsonObject();
/*      */ 
/*      */           
/*  786 */           Set set = jsonObject.entrySet();
/*      */ 
/*      */           
/*  789 */           for (Map.Entry entry : set) {
/*      */ 
/*      */             
/*  792 */             String str1 = (String)entry.getKey();
/*      */ 
/*      */             
/*  795 */             UUID uUID = MMOUtils.UUIDFromString(str1);
/*      */ 
/*      */             
/*  798 */             JsonElement jsonElement6 = (JsonElement)entry.getValue();
/*      */ 
/*      */             
/*  801 */             if (jsonElement6.isJsonArray() && uUID != null) {
/*      */ 
/*      */               
/*  804 */               ArrayList<ItemTag> arrayList = ItemTag.decompressTags(jsonElement6.getAsJsonArray());
/*      */ 
/*      */               
/*  807 */               Object object1 = itemStat.getLoadedNBT(arrayList);
/*      */ 
/*      */               
/*  810 */               if (object1 != null)
/*      */               {
/*      */                 
/*  813 */                 statHistory.registerGemstoneData(uUID, (StatData)object1);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  823 */     if (jsonElement4 != null)
/*      */     {
/*      */       
/*  826 */       for (JsonElement jsonElement : jsonElement4.getAsJsonArray()) {
/*      */ 
/*      */         
/*  829 */         if (jsonElement.isJsonArray()) {
/*      */ 
/*      */           
/*  832 */           ArrayList<ItemTag> arrayList = ItemTag.decompressTags(jsonElement.getAsJsonArray());
/*      */ 
/*      */           
/*  835 */           Object object1 = itemStat.getLoadedNBT(arrayList);
/*      */ 
/*      */           
/*  838 */           if (object1 != null)
/*      */           {
/*      */             
/*  841 */             statHistory.registerExternalData((StatData)object1);
/*      */           }
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  849 */     if (jsonElement5 != null)
/*      */     {
/*      */       
/*  852 */       for (JsonElement jsonElement : jsonElement5.getAsJsonArray()) {
/*      */ 
/*      */         
/*  855 */         if (jsonElement.isJsonObject()) {
/*      */ 
/*      */           
/*  858 */           JsonObject jsonObject = jsonElement.getAsJsonObject();
/*      */ 
/*      */           
/*  861 */           Set set = jsonObject.entrySet();
/*      */ 
/*      */           
/*  864 */           for (Map.Entry entry : set) {
/*      */ 
/*      */             
/*  867 */             String str1 = (String)entry.getKey();
/*      */ 
/*      */             
/*  870 */             UUID uUID = MMOUtils.UUIDFromString(str1);
/*      */ 
/*      */             
/*  873 */             JsonElement jsonElement6 = (JsonElement)entry.getValue();
/*      */ 
/*      */             
/*  876 */             if (jsonElement6.isJsonArray() && uUID != null) {
/*      */ 
/*      */               
/*  879 */               ArrayList<ItemTag> arrayList = ItemTag.decompressTags(jsonElement6.getAsJsonArray());
/*      */ 
/*      */               
/*  882 */               Object object1 = itemStat.getLoadedNBT(arrayList);
/*      */ 
/*      */               
/*  885 */               if (object1 != null)
/*      */               {
/*      */                 
/*  888 */                 statHistory.registerModifierBonus(uUID, (StatData)object1);
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  897 */     return statHistory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public static StatHistory fromNBTString(@NotNull MMOItem paramMMOItem, @NotNull String paramString) {
/*      */     try {
/*  911 */       JsonParser jsonParser = new JsonParser();
/*      */ 
/*      */       
/*  914 */       JsonObject jsonObject = jsonParser.parse(paramString).getAsJsonObject();
/*      */ 
/*      */       
/*  917 */       return fromJson(paramMMOItem, jsonObject);
/*      */     }
/*  919 */     catch (Throwable throwable) {
/*      */ 
/*      */       
/*  922 */       FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*  923 */       friendlyFeedbackProvider.activatePrefix(true, "Stat History");
/*  924 */       friendlyFeedbackProvider.log(FriendlyFeedbackCategory.ERROR, "Could not get stat history: $f{0}$b at $f{1}", new String[] { throwable.getMessage(), throwable.getStackTrace()[0].toString() });
/*  925 */       friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.ERROR, MMOItems.getConsole());
/*  926 */       return null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void assimilate(@NotNull StatHistory paramStatHistory) {
/*  939 */     if (paramStatHistory.getItemStat().getNBTPath().equals(getItemStat().getNBTPath())) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  946 */       for (UUID uUID : paramStatHistory.getAllGemstones())
/*      */       {
/*  948 */         registerGemstoneData(uUID, paramStatHistory.getGemstoneData(uUID));
/*      */       }
/*      */       
/*  951 */       for (StatData statData : paramStatHistory.getExternalData()) registerExternalData(statData);
/*      */ 
/*      */       
/*  954 */       for (UUID uUID : paramStatHistory.getAllModifiers())
/*      */       {
/*  956 */         registerModifierBonus(uUID, paramStatHistory.getModifiersBonus(uUID));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public StatHistory clone(@NotNull MMOItem paramMMOItem) {
/*  978 */     StatHistory statHistory = new StatHistory(paramMMOItem, getItemStat(), ((Mergeable)getOriginalData()).cloneData());
/*      */ 
/*      */     
/*  981 */     for (UUID uUID : getAllGemstones()) {
/*  982 */       if (uUID == null)
/*      */         continue; 
/*  984 */       StatData statData = getGemstoneData(uUID);
/*  985 */       if (!(statData instanceof Mergeable)) {
/*      */         continue;
/*      */       }
/*  988 */       statHistory.registerGemstoneData(uUID, ((Mergeable)statData).cloneData());
/*      */     } 
/*      */ 
/*      */     
/*  992 */     for (StatData statData : getExternalData()) {
/*  993 */       if (!(statData instanceof Mergeable)) {
/*      */         continue;
/*      */       }
/*  996 */       statHistory.registerExternalData(((Mergeable)statData).cloneData());
/*      */     } 
/*      */     
/*  999 */     for (UUID uUID : getAllModifiers()) {
/*      */       
/* 1001 */       if (uUID == null)
/*      */         continue; 
/* 1003 */       StatData statData = getModifiersBonus(uUID);
/* 1004 */       if (!(statData instanceof Mergeable)) {
/*      */         continue;
/*      */       }
/* 1007 */       statHistory.registerModifierBonus(uUID, ((Mergeable)statData).cloneData());
/*      */     } 
/*      */ 
/*      */     
/* 1011 */     return statHistory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void log() {
/* 1026 */     MMOItems.print(null, "§6SH of §e" + getItemStat().getId() + "§7, §b" + getMMOItem().getType() + " " + getMMOItem().getId(), null, new String[0]);
/*      */     
/* 1028 */     if (getOriginalData() instanceof StringListData) {
/*      */       
/* 1030 */       MMOItems.print(null, "§a++ Original", null, new String[0]);
/* 1031 */       for (String str : ((StringListData)getOriginalData()).getList()) MMOItems.print(null, "§a ++§7 " + str, null, new String[0]);
/*      */       
/* 1033 */       MMOItems.print(null, "§e++ Gemstones", null, new String[0]);
/* 1034 */       for (UUID uUID : getAllGemstones()) { StatData statData = getGemstoneData(uUID); if (!(statData instanceof StringListData)) continue;  for (String str : ((StringListData)statData).getList()) MMOItems.print(null, "§e ++§7 " + str, null, new String[0]);  }
/*      */       
/* 1036 */       MMOItems.print(null, "§c++ ExSH", null, new String[0]);
/* 1037 */       for (StatData statData : getExternalData()) { if (!(statData instanceof StringListData)) continue;  for (String str : ((StringListData)statData).getList()) MMOItems.print(null, "§e ++§7 " + str, null, new String[0]);  }
/*      */       
/* 1039 */       MMOItems.print(null, "§d++ Modifiers", null, new String[0]);
/* 1040 */       for (UUID uUID : getAllModifiers()) { StatData statData = getModifiersBonus(uUID); if (!(statData instanceof StringListData)) continue;  for (String str : ((StringListData)statData).getList()) MMOItems.print(null, "§d ++§7 " + str, null, new String[0]);  }
/*      */     
/*      */     } else {
/* 1043 */       MMOItems.print(null, "§a-- Original", null, new String[0]);
/* 1044 */       MMOItems.print(null, "§a ++§7 " + getOriginalData(), null, new String[0]);
/*      */       
/* 1046 */       MMOItems.print(null, "§e-- Gemstones", null, new String[0]);
/* 1047 */       for (UUID uUID : getAllGemstones()) { StatData statData = getGemstoneData(uUID); if (statData == null) continue;  MMOItems.print(null, "§e ++§7 " + statData, null, new String[0]); }
/*      */       
/* 1049 */       MMOItems.print(null, "§c-- ExSH", null, new String[0]);
/* 1050 */       for (StatData statData : getExternalData()) { if (statData == null) continue;  MMOItems.print(null, "§e ++§7 " + statData, null, new String[0]); }
/*      */       
/* 1052 */       MMOItems.print(null, "§d-- Modifiers", null, new String[0]);
/* 1053 */       for (UUID uUID : getAllModifiers()) { StatData statData = getModifiersBonus(uUID); if (statData == null) continue;  MMOItems.print(null, "§d ++§7 " + statData, null, new String[0]); }
/*      */     
/*      */     } 
/*      */   }
/*      */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\StatHistory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */