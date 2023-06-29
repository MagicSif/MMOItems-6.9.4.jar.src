/*    */ package net.Indyuce.mmoitems.api.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.player.cooldown.CooldownObject;
/*    */ import net.Indyuce.mmoitems.api.Type;
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
/*    */ public interface ItemReference
/*    */   extends CooldownObject
/*    */ {
/*    */   Type getType();
/*    */   
/*    */   String getId();
/*    */   
/*    */   default String getCooldownPath() {
/* 35 */     return "mmoitem_" + getId().toLowerCase();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\ItemReference.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */