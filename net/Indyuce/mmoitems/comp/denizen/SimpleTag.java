/*    */ package net.Indyuce.mmoitems.comp.denizen;
/*    */ 
/*    */ import com.denizenscript.denizencore.objects.ObjectTag;
/*    */ 
/*    */ 
/*    */ public abstract class SimpleTag
/*    */   implements ObjectTag
/*    */ {
/*    */   private String prefix;
/*    */   
/*    */   public String getPrefix() {
/* 12 */     return this.prefix;
/*    */   }
/*    */ 
/*    */   
/*    */   public ObjectTag setPrefix(String paramString) {
/* 17 */     this.prefix = paramString;
/* 18 */     return this;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\denizen\SimpleTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */