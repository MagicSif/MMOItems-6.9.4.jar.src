/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Set;
/*    */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class AbilityListData implements StatData, Mergeable<AbilityListData> {
/*    */   @NotNull
/* 12 */   private final Set<AbilityData> abilities = new LinkedHashSet<>();
/*    */   
/*    */   public AbilityListData(@NotNull AbilityData... paramVarArgs) {
/* 15 */     add(paramVarArgs);
/*    */   }
/*    */   public AbilityListData(@NotNull Set<AbilityData> paramSet) {
/* 18 */     add(paramSet);
/*    */   }
/*    */   public void add(@NotNull AbilityData... paramVarArgs) {
/* 21 */     this.abilities.addAll(Arrays.asList(paramVarArgs));
/*    */   }
/*    */   public void add(@NotNull Set<AbilityData> paramSet) {
/* 24 */     this.abilities.addAll(paramSet);
/*    */   }
/*    */   @NotNull
/*    */   public Set<AbilityData> getAbilities() {
/* 28 */     return this.abilities;
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(@NotNull AbilityListData paramAbilityListData) {
/* 33 */     this.abilities.addAll(paramAbilityListData.abilities);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 38 */     if (!(paramObject instanceof AbilityListData)) return false;
/*    */ 
/*    */     
/* 41 */     if (getAbilities().size() != ((AbilityListData)paramObject).getAbilities().size()) return false;
/*    */ 
/*    */     
/* 44 */     for (AbilityData abilityData : ((AbilityListData)paramObject).getAbilities()) {
/*    */       
/* 46 */       if (abilityData == null)
/*    */         continue; 
/* 48 */       boolean bool = true;
/* 49 */       for (AbilityData abilityData1 : getAbilities()) { if (abilityData.equals(abilityData1)) { bool = false; break; }
/*    */          }
/*    */       
/* 52 */       if (bool) return false;
/*    */     
/*    */     } 
/* 55 */     return true;
/*    */   } @NotNull
/*    */   public AbilityListData cloneData() {
/* 58 */     return new AbilityListData(getAbilities());
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 62 */     return this.abilities.isEmpty();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\AbilityListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */