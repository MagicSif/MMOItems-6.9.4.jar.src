/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RequiredLevelData
/*    */   extends DoubleData
/*    */ {
/*    */   public RequiredLevelData(double paramDouble) {
/* 17 */     super(paramDouble);
/*    */   }
/*    */ 
/*    */   
/*    */   public void merge(DoubleData paramDoubleData) {
/* 22 */     boolean bool = MMOItems.plugin.getConfig().getBoolean("stat-merging.additive-levels", false);
/* 23 */     setValue(bool ? (paramDoubleData.getValue() + getValue()) : Math.max(paramDoubleData.getValue(), getValue()));
/*    */   }
/*    */ 
/*    */   
/*    */   public DoubleData cloneData() {
/* 28 */     return new RequiredLevelData(getValue());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\RequiredLevelData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */