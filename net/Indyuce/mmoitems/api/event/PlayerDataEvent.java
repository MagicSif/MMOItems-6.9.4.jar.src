/*    */ package net.Indyuce.mmoitems.api.event;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.player.PlayerEvent;
/*    */ 
/*    */ public abstract class PlayerDataEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable {
/*    */   private final PlayerData playerData;
/*    */   private boolean cancelled;
/*    */   
/*    */   public PlayerDataEvent(PlayerData paramPlayerData) {
/* 14 */     super(paramPlayerData.getPlayer());
/*    */     
/* 16 */     this.playerData = paramPlayerData;
/*    */   }
/*    */   
/*    */   public PlayerData getPlayerData() {
/* 20 */     return this.playerData;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 25 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean paramBoolean) {
/* 30 */     this.cancelled = paramBoolean;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\PlayerDataEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */