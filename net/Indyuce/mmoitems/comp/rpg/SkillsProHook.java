/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.skills.api.events.SkillLevelUpEvent;
/*    */ import org.skills.data.managers.SkilledPlayer;
/*    */ import org.skills.main.SkillsPro;
/*    */ 
/*    */ public class SkillsProHook implements RPGHandler, Listener {
/*    */   @EventHandler
/*    */   public void a(SkillLevelUpEvent paramSkillLevelUpEvent) {
/* 17 */     Player player = paramSkillLevelUpEvent.getPlayer();
/* 18 */     if (player.isOnline()) {
/* 19 */       PlayerData.get((OfflinePlayer)player).getInventory().scheduleUpdate();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void refreshStats(PlayerData paramPlayerData) {}
/*    */ 
/*    */   
/*    */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/* 28 */     return new SkillsPlayer(paramPlayerData);
/*    */   }
/*    */   
/*    */   public static class SkillsPlayer extends RPGPlayer {
/*    */     public SkillsPlayer(PlayerData param1PlayerData) {
/* 33 */       super(param1PlayerData);
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLevel() {
/* 38 */       SkilledPlayer skilledPlayer = (SkilledPlayer)SkillsPro.get().getPlayerDataManager().getData(getPlayerData().getUniqueId());
/* 39 */       return skilledPlayer.getLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 44 */       SkilledPlayer skilledPlayer = (SkilledPlayer)SkillsPro.get().getPlayerDataManager().getData(getPlayerData().getUniqueId());
/* 45 */       return ChatColor.stripColor(skilledPlayer.getSkill().getDisplayName());
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 50 */       SkilledPlayer skilledPlayer = (SkilledPlayer)SkillsPro.get().getPlayerDataManager().getData(getPlayerData().getUniqueId());
/* 51 */       return skilledPlayer.getEnergy();
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 56 */       return getPlayer().getFoodLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 61 */       SkilledPlayer skilledPlayer = (SkilledPlayer)SkillsPro.get().getPlayerDataManager().getData(getPlayerData().getUniqueId());
/* 62 */       skilledPlayer.setEnergy(param1Double);
/*    */     }
/*    */ 
/*    */     
/*    */     public void setStamina(double param1Double) {
/* 67 */       getPlayer().setFoodLevel((int)param1Double);
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\SkillsProHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */