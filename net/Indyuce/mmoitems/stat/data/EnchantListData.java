/*     */ package net.Indyuce.mmoitems.stat.data;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class EnchantListData
/*     */   implements StatData, Mergeable {
/*  18 */   private final Map<Enchantment, Integer> enchants = new HashMap<>();
/*     */   
/*     */   public Set<Enchantment> getEnchants() {
/*  21 */     return this.enchants.keySet();
/*     */   }
/*     */   
/*     */   public int getLevel(@NotNull Enchantment paramEnchantment) {
/*  25 */     Integer integer = this.enchants.get(paramEnchantment);
/*  26 */     return (integer == null) ? 0 : integer.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addEnchant(Enchantment paramEnchantment, int paramInt) {
/*  32 */     if (paramInt <= 0) { this.enchants.remove(paramEnchantment); } else { this.enchants.put(paramEnchantment, Integer.valueOf(paramInt)); }
/*     */   
/*     */   } public void clear() {
/*  35 */     this.enchants.clear();
/*     */   }
/*     */   
/*     */   public boolean equals(Object paramObject) {
/*  39 */     if (!(paramObject instanceof EnchantListData)) return false; 
/*  40 */     if (((EnchantListData)paramObject).enchants.size() != this.enchants.size()) return false;
/*     */     
/*  42 */     for (Enchantment enchantment : getEnchants()) {
/*     */ 
/*     */       
/*  45 */       if (getLevel(enchantment) != ((EnchantListData)paramObject).getLevel(enchantment)) return false; 
/*  46 */     }  return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void merge(StatData paramStatData) {
/*  51 */     Validate.isTrue(paramStatData instanceof EnchantListData, "Cannot merge two different stat data types");
/*     */     
/*  53 */     EnchantListData enchantListData = (EnchantListData)paramStatData;
/*  54 */     for (Enchantment enchantment : enchantListData.getEnchants()) {
/*  55 */       addEnchant(enchantment, Math.max(((Integer)this.enchants.getOrDefault(enchantment, Integer.valueOf(0))).intValue(), enchantListData.getLevel(enchantment)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StatData cloneData() {
/*  62 */     EnchantListData enchantListData = new EnchantListData();
/*     */ 
/*     */     
/*  65 */     for (Enchantment enchantment : this.enchants.keySet()) enchantListData.addEnchant(enchantment, ((Integer)this.enchants.getOrDefault(enchantment, Integer.valueOf(0))).intValue());
/*     */ 
/*     */     
/*  68 */     return enchantListData;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  73 */     for (Iterator<Integer> iterator = this.enchants.values().iterator(); iterator.hasNext(); ) { int i = ((Integer)iterator.next()).intValue();
/*  74 */       if (i > 0)
/*  75 */         return false;  }
/*  76 */      return true;
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
/*     */   public void identifyTrueOriginalEnchantments(@NotNull MMOItem paramMMOItem) {
/* 107 */     if (paramMMOItem.hasData(ItemStats.DISABLE_ENCHANTING) && paramMMOItem.hasData(ItemStats.DISABLE_REPAIRING)) {
/*     */ 
/*     */       
/* 110 */       clear();
/*     */       
/*     */       return;
/*     */     } 
/* 114 */     if (!paramMMOItem.hasData(ItemStats.ENCHANTS)) paramMMOItem.setData(ItemStats.ENCHANTS, new EnchantListData());
/*     */     
/* 116 */     EnchantListData enchantListData1 = (EnchantListData)paramMMOItem.getData(ItemStats.ENCHANTS);
/*     */ 
/*     */     
/* 119 */     if (enchantListData1.getEnchants().size() == 0) {
/*     */ 
/*     */ 
/*     */       
/* 123 */       paramMMOItem.mergeData(ItemStats.ENCHANTS, this, null);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 128 */     EnchantListData enchantListData2 = new EnchantListData();
/*     */ 
/*     */     
/* 131 */     paramMMOItem.hasData(ItemStats.MATERIAL); MaterialData materialData = (MaterialData)paramMMOItem.getData(ItemStats.MATERIAL); Material material = materialData.getMaterial();
/*     */ 
/*     */     
/* 134 */     for (Enchantment enchantment : getEnchants()) {
/*     */ 
/*     */ 
/*     */       
/* 138 */       int i = getLevel(enchantment);
/* 139 */       int j = enchantListData1.getLevel(enchantment);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 144 */       if (j > enchantment.getMaxLevel() || !enchantment.getItemTarget().includes(material)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 151 */       if (j < i)
/*     */       {
/* 153 */         enchantListData2.addEnchant(enchantment, i);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     if (!enchantListData2.isClear())
/*     */     {
/*     */       
/* 166 */       paramMMOItem.mergeData(ItemStats.ENCHANTS, enchantListData2, null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\EnchantListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */