/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import org.bukkit.Material;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class BrowserDisplayIDX extends DoubleStat {
/*     */   public BrowserDisplayIDX() {
/*  21 */     super("BROWSER_IDX", Material.GHAST_TEAR, "Browser Index", new String[] { "Used to display similar items together,", "neatly in the GUI §a/mmoitems browse", "", "Items with the same index are grouped." }, new String[] { "all" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {}
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static HashMap<Double, ArrayList<MMOItemTemplate>> select(@NotNull Collection<MMOItemTemplate> paramCollection) {
/*  37 */     HashMap<Object, Object> hashMap = new HashMap<>();
/*     */ 
/*     */     
/*  40 */     for (MMOItemTemplate mMOItemTemplate : paramCollection) {
/*  41 */       if (mMOItemTemplate == null)
/*     */         continue; 
/*  43 */       Double double_ = null;
/*  44 */       if (mMOItemTemplate.getType().getAvailableStats().contains(ItemStats.BROWSER_DISPLAY_IDX)) {
/*  45 */         NumericStatFormula numericStatFormula = (NumericStatFormula)mMOItemTemplate.getBaseItemData().get(ItemStats.BROWSER_DISPLAY_IDX);
/*     */ 
/*     */         
/*  48 */         if (numericStatFormula != null && numericStatFormula.getBase() != 0.0D) double_ = Double.valueOf(numericStatFormula.getBase());
/*     */       
/*     */       } 
/*     */       
/*  52 */       ArrayList<MMOItemTemplate> arrayList = (ArrayList)hashMap.get(double_);
/*  53 */       if (arrayList == null) arrayList = new ArrayList();
/*     */ 
/*     */       
/*  56 */       arrayList.add(mMOItemTemplate);
/*  57 */       hashMap.put(double_, arrayList);
/*     */     } 
/*     */     
/*  60 */     return (HashMap)hashMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static MMOItemTemplate getAt(int paramInt, @NotNull HashMap<Double, ArrayList<MMOItemTemplate>> paramHashMap) {
/*  71 */     Map.Entry<Double, ArrayList<MMOItemTemplate>> entry = null;
/*     */ 
/*     */     
/*  74 */     for (Map.Entry<Double, ArrayList<MMOItemTemplate>> entry1 : paramHashMap.entrySet()) {
/*     */ 
/*     */       
/*  77 */       if (entry1.getKey() == null) { entry = entry1;
/*     */         continue; }
/*     */       
/*  80 */       ArrayList arrayList1 = (ArrayList)entry1.getValue();
/*  81 */       for (; arrayList1.size() % 4 != 0; arrayList1.add(null));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  86 */       for (MMOItemTemplate mMOItemTemplate : arrayList1) {
/*     */ 
/*     */         
/*  89 */         if (paramInt == 0) return mMOItemTemplate; 
/*  90 */         paramInt--;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  95 */     if (entry == null) return null;
/*     */ 
/*     */     
/*  98 */     ArrayList arrayList = entry.getValue();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     for (MMOItemTemplate mMOItemTemplate : arrayList) {
/*     */ 
/*     */       
/* 106 */       if (paramInt == 0) return mMOItemTemplate; 
/* 107 */       paramInt--;
/*     */     } 
/*     */ 
/*     */     
/* 111 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\BrowserDisplayIDX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */