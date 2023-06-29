/*    */ package net.Indyuce.mmoitems.api.edition.input;
/*    */ 
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.edition.Edition;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.inventory.InventoryOpenEvent;
/*    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatEdition
/*    */   extends PlayerInputHandler
/*    */   implements Listener
/*    */ {
/*    */   public ChatEdition(Edition paramEdition) {
/* 21 */     super(paramEdition);
/*    */     
/* 23 */     Bukkit.getPluginManager().registerEvents(this, (Plugin)MMOItems.plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/* 28 */     HandlerList.unregisterAll(this);
/*    */   }
/*    */   
/*    */   @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
/*    */   public void a(AsyncPlayerChatEvent paramAsyncPlayerChatEvent) {
/* 33 */     if (getPlayer() != null && paramAsyncPlayerChatEvent.getPlayer().equals(getPlayer())) {
/* 34 */       paramAsyncPlayerChatEvent.setCancelled(true);
/* 35 */       registerInput(paramAsyncPlayerChatEvent.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void b(InventoryOpenEvent paramInventoryOpenEvent) {
/* 42 */     if (paramInventoryOpenEvent.getPlayer().equals(getPlayer()))
/* 43 */       close(); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\edition\input\ChatEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */