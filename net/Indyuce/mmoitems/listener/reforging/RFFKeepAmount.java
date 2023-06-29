/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeFinishEvent;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RFFKeepAmount
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeFinishEvent paramMMOItemReforgeFinishEvent) {
/* 20 */     paramMMOItemReforgeFinishEvent.getFinishedItem().setAmount(paramMMOItemReforgeFinishEvent.getReforger().getNBTItem().getItem().getAmount());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFFKeepAmount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */