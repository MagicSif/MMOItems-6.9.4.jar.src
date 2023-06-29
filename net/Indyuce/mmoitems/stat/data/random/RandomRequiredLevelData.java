/*    */ package net.Indyuce.mmoitems.stat.data.random;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*    */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*    */ import net.Indyuce.mmoitems.stat.data.RequiredLevelData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ 
/*    */ 
/*    */ public class RandomRequiredLevelData
/*    */   extends NumericStatFormula
/*    */ {
/*    */   public RandomRequiredLevelData(Object paramObject) {
/* 14 */     super(paramObject);
/*    */   }
/*    */   
/*    */   public RandomRequiredLevelData(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/* 18 */     super(paramDouble1, paramDouble2, paramDouble3, paramDouble4);
/*    */   }
/*    */ 
/*    */   
/*    */   public RequiredLevelData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 23 */     return new RequiredLevelData(calculate(paramMMOItemBuilder.getLevel()));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\random\RandomRequiredLevelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */