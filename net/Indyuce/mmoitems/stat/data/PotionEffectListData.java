/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PotionEffectListData
/*    */   implements StatData, Mergeable<PotionEffectListData>
/*    */ {
/* 14 */   private final List<PotionEffectData> effects = new ArrayList<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 25 */     if (!(paramObject instanceof PotionEffectListData)) return false;
/*    */     
/* 27 */     if (((PotionEffectListData)paramObject).getEffects().size() != getEffects().size()) return false;
/*    */     
/* 29 */     for (PotionEffectData potionEffectData : ((PotionEffectListData)paramObject).getEffects()) {
/*    */       
/* 31 */       if (potionEffectData == null) {
/*    */         continue;
/*    */       }
/* 34 */       boolean bool = true;
/* 35 */       for (PotionEffectData potionEffectData1 : getEffects()) {
/*    */         
/* 37 */         if (potionEffectData.equals(potionEffectData1)) {
/* 38 */           bool = false; break;
/*    */         } 
/* 40 */       }  if (bool) return false; 
/*    */     } 
/* 42 */     return true;
/*    */   }
/*    */   
/*    */   public PotionEffectListData(PotionEffectData... paramVarArgs) {
/* 46 */     add(paramVarArgs);
/*    */   }
/*    */   
/*    */   public void add(PotionEffectData... paramVarArgs) {
/* 50 */     this.effects.addAll(Arrays.asList(paramVarArgs));
/*    */   }
/*    */   
/*    */   public List<PotionEffectData> getEffects() {
/* 54 */     return this.effects;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 59 */     return this.effects.isEmpty();
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(PotionEffectListData paramPotionEffectListData) {
/* 64 */     this.effects.addAll(paramPotionEffectListData.effects);
/*    */   }
/*    */   @NotNull
/*    */   public PotionEffectListData cloneData() {
/* 68 */     return new PotionEffectListData(getEffects().<PotionEffectData>toArray(new PotionEffectData[0]));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\PotionEffectListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */