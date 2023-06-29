/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheckedCondition
/*    */ {
/*    */   private final Condition condition;
/*    */   private final boolean met;
/*    */   
/*    */   public CheckedCondition(Condition paramCondition, boolean paramBoolean) {
/* 16 */     this.condition = paramCondition;
/* 17 */     this.met = paramBoolean;
/*    */   }
/*    */   
/*    */   public boolean isMet() {
/* 21 */     return this.met;
/*    */   }
/*    */   
/*    */   public Condition getCondition() {
/* 25 */     return this.condition;
/*    */   }
/*    */   
/*    */   public String format() {
/* 29 */     return this.condition.formatDisplay(this.condition.getDisplay().format(this.met));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\CheckedCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */