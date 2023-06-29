/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import me.robin.battlelevels.api.BattleLevelsAPI;
/*    */ import me.robin.battlelevels.events.PlayerLevelUpEvent;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ public class BattleLevelsHook
/*    */   implements RPGHandler, Listener {
/*    */   @EventHandler
/*    */   public void a(PlayerLevelUpEvent paramPlayerLevelUpEvent) {
/* 15 */     PlayerData.get((OfflinePlayer)paramPlayerLevelUpEvent.getPlayer()).getInventory().scheduleUpdate();
/*    */   }
/*    */ 
/*    */   
/*    */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/* 20 */     return new BattleLevelsPlayer(paramPlayerData);
/*    */   }
/*    */   
/*    */   public void refreshStats(PlayerData paramPlayerData) {}
/*    */   
/*    */   public static class BattleLevelsPlayer
/*    */     extends RPGPlayer
/*    */   {
/*    */     public BattleLevelsPlayer(PlayerData param1PlayerData) {
/* 29 */       super(param1PlayerData);
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLevel() {
/* 34 */       return BattleLevelsAPI.getLevel(getPlayer().getUniqueId());
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 39 */       return "";
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 44 */       return getPlayer().getFoodLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 49 */       return 0.0D;
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 54 */       getPlayer().setFoodLevel((int)param1Double);
/*    */     }
/*    */     
/*    */     public void setStamina(double param1Double) {}
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\BattleLevelsHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */