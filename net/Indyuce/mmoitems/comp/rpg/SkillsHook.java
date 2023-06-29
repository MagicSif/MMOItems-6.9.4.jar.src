/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import me.leothepro555.skills.database.managers.PlayerInfo;
/*    */ import me.leothepro555.skills.events.SkillLevelUpEvent;
/*    */ import me.leothepro555.skills.main.Skills;
/*    */ import me.leothepro555.skilltype.ScalingType;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ public class SkillsHook
/*    */   implements RPGHandler, Listener {
/*    */   @EventHandler
/*    */   public void a(SkillLevelUpEvent paramSkillLevelUpEvent) {
/* 17 */     OfflinePlayer offlinePlayer = paramSkillLevelUpEvent.getPlayer();
/* 18 */     if (offlinePlayer.isOnline()) {
/* 19 */       PlayerData.get(offlinePlayer).getInventory().scheduleUpdate();
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
/* 38 */       PlayerInfo playerInfo = Skills.get().getPlayerDataManager().loadPlayerInfo((OfflinePlayer)getPlayer());
/* 39 */       return playerInfo.getLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 44 */       PlayerInfo playerInfo = Skills.get().getPlayerDataManager().loadPlayerInfo((OfflinePlayer)getPlayer());
/* 45 */       return playerInfo.getSkill().getLanguageName().getDefault();
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 50 */       PlayerInfo playerInfo = Skills.get().getPlayerDataManager().loadPlayerInfo((OfflinePlayer)getPlayer());
/* 51 */       return playerInfo.getActiveStatType(ScalingType.ENERGY);
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 56 */       return getPlayer().getFoodLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 61 */       PlayerInfo playerInfo = Skills.get().getPlayerDataManager().loadPlayerInfo((OfflinePlayer)getPlayer());
/* 62 */       playerInfo.setActiveStatType(ScalingType.ENERGY, param1Double);
/*    */     }
/*    */ 
/*    */     
/*    */     public void setStamina(double param1Double) {
/* 67 */       getPlayer().setFoodLevel((int)param1Double);
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\SkillsHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */