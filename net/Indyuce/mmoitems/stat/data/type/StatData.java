/*    */ package net.Indyuce.mmoitems.stat.data.type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface StatData
/*    */ {
/*    */   default boolean isEmpty() {
/* 17 */     return (this instanceof Mergeable && ((Mergeable<StatData>)this).isClear());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\type\StatData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */