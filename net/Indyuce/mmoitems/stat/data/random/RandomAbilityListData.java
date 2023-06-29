/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.LinkedHashSet;
/*    */ import java.util.Set;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.AbilityData;
/*    */ import net.Indyuce.mmoitems.stat.data.AbilityListData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ 
/*    */ public class RandomAbilityListData implements RandomStatData<AbilityListData> {
/* 12 */   private final Set<RandomAbilityData> abilities = new LinkedHashSet<>();
/*    */   
/*    */   public RandomAbilityListData(RandomAbilityData... paramVarArgs) {
/* 15 */     add(paramVarArgs);
/*    */   }
/*    */   
/*    */   public void add(RandomAbilityData... paramVarArgs) {
/* 19 */     this.abilities.addAll(Arrays.asList(paramVarArgs));
/*    */   }
/*    */   
/*    */   public Set<RandomAbilityData> getAbilities() {
/* 23 */     return this.abilities;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityListData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 28 */     AbilityListData abilityListData = new AbilityListData(new AbilityData[0]);
/* 29 */     this.abilities.forEach(paramRandomAbilityData -> paramAbilityListData.add(new AbilityData[] { paramRandomAbilityData.randomize(paramMMOItemBuilder) }));
/* 30 */     return abilityListData;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomAbilityListData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */