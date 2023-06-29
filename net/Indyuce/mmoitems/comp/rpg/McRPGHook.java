/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import us.eunoians.mcrpg.api.events.mcrpg.McRPGPlayerLevelChangeEvent;
/*    */ import us.eunoians.mcrpg.players.PlayerManager;
/*    */ 
/*    */ public class McRPGHook
/*    */   implements RPGHandler, Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void a(McRPGPlayerLevelChangeEvent paramMcRPGPlayerLevelChangeEvent) {
/* 15 */     PlayerData.get(paramMcRPGPlayerLevelChangeEvent.getMcRPGPlayer().getOfflineMcRPGPlayer()).getInventory().scheduleUpdate();
/*    */   }
/*    */ 
/*    */   
/*    */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/* 20 */     return new McRPGPlayer(paramPlayerData);
/*    */   }
/*    */   
/*    */   public void refreshStats(PlayerData paramPlayerData) {}
/*    */   
/*    */   public static class McRPGPlayer
/*    */     extends RPGPlayer
/*    */   {
/*    */     public McRPGPlayer(PlayerData param1PlayerData) {
/* 29 */       super(param1PlayerData);
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLevel() {
/*    */       try {
/* 35 */         return PlayerManager.getPlayer(getPlayer().getUniqueId()).getPowerLevel();
/* 36 */       } catch (Exception exception) {
/* 37 */         return 0;
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 43 */       return "";
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 48 */       return getPlayer().getFoodLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 53 */       return 0.0D;
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 58 */       getPlayer().setFoodLevel((int)param1Double);
/*    */     }
/*    */     
/*    */     public void setStamina(double param1Double) {}
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\McRPGHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */