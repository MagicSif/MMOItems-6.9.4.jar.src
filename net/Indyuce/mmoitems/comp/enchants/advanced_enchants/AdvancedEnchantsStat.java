/*     */ package net.Indyuce.mmoitems.comp.enchants.advanced_enchants;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.InternalStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.advancedplugins.ae.enchanthandler.enchantments.AEnchants;
/*     */ import net.advancedplugins.ae.enchanthandler.enchantments.AdvancedEnchantment;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class AdvancedEnchantsStat
/*     */   extends ItemStat<RandomStatData<AdvancedEnchantMap>, AdvancedEnchantMap>
/*     */   implements InternalStat {
/*     */   private static final String AE_TAG = "ae_enchantment";
/*     */   
/*     */   public AdvancedEnchantsStat() {
/*  30 */     super("ADVANCED_ENCHANTS", VersionMaterial.EXPERIENCE_BOTTLE.toMaterial(), "Advanced Enchants", new String[] { "The AEnchants of this item. Format:", "§e[internal_name] [level]" }, new String[] { "!miscellaneous", "!block", "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public RandomStatData whenInitialized(Object paramObject) {
/*  35 */     throw new RuntimeException("Not supported");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull AdvancedEnchantMap paramAdvancedEnchantMap) {
/*  42 */     Map<String, Integer> map = paramAdvancedEnchantMap.enchants;
/*     */ 
/*     */     
/*  45 */     for (String str : map.keySet()) {
/*     */       
/*  47 */       Integer integer = map.get(str);
/*     */ 
/*     */       
/*  50 */       if (str == null || integer == null || str.isEmpty()) {
/*     */         continue;
/*     */       }
/*     */       
/*  54 */       AdvancedEnchantment advancedEnchantment = AEnchants.matchEnchant(str);
/*  55 */       if (advancedEnchantment == null) {
/*     */         return;
/*     */       }
/*     */       
/*  59 */       paramItemStackBuilder.getLore().insert(0, advancedEnchantment.getDisplay(integer.intValue()));
/*  60 */       paramItemStackBuilder.addItemTag(new ItemTag[] { getEnchantTag(str, integer.intValue()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull AdvancedEnchantMap paramAdvancedEnchantMap) {
/*  67 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  68 */     Map<String, Integer> map = paramAdvancedEnchantMap.enchants;
/*     */     
/*  70 */     for (String str : map.keySet()) {
/*  71 */       arrayList.add(getEnchantTag(str, ((Integer)map.get(str)).intValue()));
/*     */     }
/*  73 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomStatData<AdvancedEnchantMap>> paramOptional) {}
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AdvancedEnchantMap getClearStatData() {
/*  94 */     return new AdvancedEnchantMap();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemTag getEnchantTag(@NotNull String paramString, int paramInt) {
/* 105 */     return new ItemTag("ae_enchantment;" + paramString, Integer.valueOf(paramInt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 112 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 113 */     for (String str : paramReadMMOItem.getNBT().getTags()) {
/*     */ 
/*     */       
/* 116 */       if (str == null) {
/*     */         continue;
/*     */       }
/* 119 */       if (str.startsWith("ae_enchantment")) {
/*     */         
/* 121 */         ItemTag itemTag = ItemTag.getTagAtPath(str, paramReadMMOItem.getNBT(), SupportedNBTTagValues.DOUBLE);
/*     */ 
/*     */         
/* 124 */         if (itemTag != null)
/*     */         {
/* 126 */           arrayList.add(new ItemTag(str, Integer.valueOf(SilentNumbers.round(((Double)itemTag.getValue()).doubleValue()))));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 132 */     AdvancedEnchantMap advancedEnchantMap = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 135 */     if (advancedEnchantMap != null)
/*     */     {
/*     */       
/* 138 */       paramReadMMOItem.setData(this, advancedEnchantMap);
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AdvancedEnchantMap getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 144 */     AdvancedEnchantMap advancedEnchantMap = new AdvancedEnchantMap();
/*     */ 
/*     */     
/* 147 */     for (ItemTag itemTag : paramArrayList) {
/* 148 */       if (itemTag == null || !(itemTag.getValue() instanceof Integer)) {
/*     */         continue;
/*     */       }
/*     */       
/* 152 */       int i = itemTag.getPath().indexOf("ae_enchantment;");
/* 153 */       if (i < 0) {
/*     */         continue;
/*     */       }
/*     */       
/* 157 */       String str = itemTag.getPath().substring("ae_enchantment".length() + 1 + i);
/* 158 */       int j = ((Integer)itemTag.getValue()).intValue();
/*     */ 
/*     */       
/* 161 */       advancedEnchantMap.enchants.put(str, Integer.valueOf(j));
/*     */     } 
/*     */ 
/*     */     
/* 165 */     return (advancedEnchantMap.enchants.size() == 0) ? null : advancedEnchantMap;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\enchants\advanced_enchants\AdvancedEnchantsStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */