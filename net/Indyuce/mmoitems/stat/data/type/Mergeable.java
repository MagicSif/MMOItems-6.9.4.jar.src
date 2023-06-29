/*    */ package net.Indyuce.mmoitems.stat.data.type;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface Mergeable<S extends StatData>
/*    */   extends StatData
/*    */ {
/*    */   void merge(S paramS);
/*    */   
/*    */   @NotNull
/*    */   S cloneData();
/*    */   
/*    */   @Deprecated
/*    */   default boolean isClear() {
/* 38 */     return isEmpty();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\type\Mergeable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */