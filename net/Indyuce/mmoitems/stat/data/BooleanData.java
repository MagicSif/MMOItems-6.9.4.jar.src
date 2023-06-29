/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ 
/*    */ public class BooleanData implements StatData {
/*    */   private final boolean state;
/*    */   
/*    */   public BooleanData(boolean paramBoolean) {
/*  9 */     this.state = paramBoolean;
/*    */   }
/*    */   
/*    */   public boolean isEnabled() {
/* 13 */     return this.state;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEmpty() {
/* 18 */     return !this.state;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 23 */     return "" + this.state;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\BooleanData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */