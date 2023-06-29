/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import de.tobiyas.racesandclasses.eventprocessing.events.leveling.LevelDownEvent;
/*    */ import de.tobiyas.racesandclasses.eventprocessing.events.leveling.LevelUpEvent;
/*    */ import de.tobiyas.racesandclasses.playermanagement.player.RaCPlayer;
/*    */ import de.tobiyas.racesandclasses.playermanagement.player.RaCPlayerManager;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ public class RacesAndClassesHook
/*    */   implements RPGHandler, Listener {
/*    */   public void refreshStats(PlayerData paramPlayerData) {
/* 17 */     RaCPlayer raCPlayer = RaCPlayerManager.get().getPlayer(paramPlayerData.getUniqueId());
/* 18 */     raCPlayer.getManaManager().removeMaxManaBonus("MMOItems");
/* 19 */     raCPlayer.getManaManager().addMaxManaBonus("MMOItems", paramPlayerData.getStats().getStat(ItemStats.MAX_MANA));
/*    */   }
/*    */ 
/*    */   
/*    */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/* 24 */     return new RacePlayer(paramPlayerData);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void a(LevelUpEvent paramLevelUpEvent) {
/* 33 */     PlayerData.get((OfflinePlayer)paramLevelUpEvent.getPlayer()).getInventory().scheduleUpdate();
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void b(LevelDownEvent paramLevelDownEvent) {
/* 38 */     PlayerData.get((OfflinePlayer)paramLevelDownEvent.getPlayer()).getInventory().scheduleUpdate();
/*    */   }
/*    */   
/*    */   public static class RacePlayer extends RPGPlayer {
/*    */     public RacePlayer(PlayerData param1PlayerData) {
/* 43 */       super(param1PlayerData);
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLevel() {
/* 48 */       RaCPlayer raCPlayer = RaCPlayerManager.get().getPlayer(getPlayer().getUniqueId());
/* 49 */       return raCPlayer.getCurrentLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 54 */       RaCPlayer raCPlayer = RaCPlayerManager.get().getPlayer(getPlayer().getUniqueId());
/* 55 */       return raCPlayer.getclass().getDisplayName();
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 60 */       RaCPlayer raCPlayer = RaCPlayerManager.get().getPlayer(getPlayer().getUniqueId());
/* 61 */       return raCPlayer.getCurrentMana();
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 66 */       RaCPlayer raCPlayer = RaCPlayerManager.get().getPlayer(getPlayer().getUniqueId());
/* 67 */       return raCPlayer.getPlayer().getFoodLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 72 */       RaCPlayer raCPlayer = RaCPlayerManager.get().getPlayer(getPlayer().getUniqueId());
/* 73 */       raCPlayer.getManaManager().fillMana(param1Double - raCPlayer.getManaManager().getCurrentMana());
/*    */     }
/*    */ 
/*    */     
/*    */     public void setStamina(double param1Double) {
/* 78 */       RaCPlayer raCPlayer = RaCPlayerManager.get().getPlayer(getPlayer().getUniqueId());
/* 79 */       raCPlayer.getPlayer().setFoodLevel((int)param1Double);
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\RacesAndClassesHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */