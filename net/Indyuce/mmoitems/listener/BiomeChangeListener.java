/*    */ package net.Indyuce.mmoitems.listener;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.block.Biome;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
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
/*    */ public class BiomeChangeListener
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler(priority = EventPriority.MONITOR)
/*    */   public void onPlayerMove(PlayerMoveEvent paramPlayerMoveEvent) {
/* 26 */     if (paramPlayerMoveEvent.isCancelled() || !PlayerData.has(paramPlayerMoveEvent.getPlayer()) || (paramPlayerMoveEvent.getFrom().getBlockX() == paramPlayerMoveEvent.getTo().getBlockX() && paramPlayerMoveEvent.getFrom().getBlockZ() == paramPlayerMoveEvent.getTo().getBlockZ()))
/*    */       return; 
/* 28 */     Biome biome1 = paramPlayerMoveEvent.getFrom().getBlock().getBiome();
/* 29 */     Biome biome2 = paramPlayerMoveEvent.getTo().getBlock().getBiome();
/* 30 */     if (biome2 != biome1)
/* 31 */       PlayerData.get((OfflinePlayer)paramPlayerMoveEvent.getPlayer()).getInventory().scheduleUpdate(); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\BiomeChangeListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */