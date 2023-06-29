/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.player.PlayerLevelChangeEvent;
/*    */ 
/*    */ 
/*    */ public class DefaultHook
/*    */   implements RPGHandler, Listener
/*    */ {
/*    */   public void refreshStats(PlayerData paramPlayerData) {}
/*    */   
/*    */   @EventHandler
/*    */   public void a(PlayerLevelChangeEvent paramPlayerLevelChangeEvent) {
/* 18 */     PlayerData.get((OfflinePlayer)paramPlayerLevelChangeEvent.getPlayer()).getInventory().scheduleUpdate();
/*    */   }
/*    */ 
/*    */   
/*    */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/* 23 */     return new DefaultRPGPlayer(paramPlayerData);
/*    */   }
/*    */   
/*    */   public static class DefaultRPGPlayer extends RPGPlayer {
/*    */     public DefaultRPGPlayer(PlayerData param1PlayerData) {
/* 28 */       super(param1PlayerData);
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLevel() {
/* 33 */       return getPlayer().getLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 38 */       return "";
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 43 */       return getPlayer().getFoodLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 48 */       return 0.0D;
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 53 */       getPlayer().setFoodLevel((int)param1Double);
/*    */     }
/*    */     
/*    */     public void setStamina(double param1Double) {}
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\DefaultHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */