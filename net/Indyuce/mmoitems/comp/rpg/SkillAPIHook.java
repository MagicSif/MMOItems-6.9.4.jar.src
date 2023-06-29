/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import com.sucy.skill.SkillAPI;
/*    */ import com.sucy.skill.api.event.PlayerLevelUpEvent;
/*    */ import com.sucy.skill.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ public class SkillAPIHook implements RPGHandler, Listener {
/*    */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/* 14 */     return new SkillAPIPlayer(paramPlayerData);
/*    */   }
/*    */   
/*    */   @EventHandler
/*    */   public void b(PlayerLevelUpEvent paramPlayerLevelUpEvent) {
/* 19 */     PlayerData.get((OfflinePlayer)paramPlayerLevelUpEvent.getPlayerData().getPlayer()).getInventory().scheduleUpdate();
/*    */   }
/*    */   
/*    */   public void refreshStats(PlayerData paramPlayerData) {}
/*    */   
/*    */   public static class SkillAPIPlayer
/*    */     extends RPGPlayer
/*    */   {
/*    */     public SkillAPIPlayer(PlayerData param1PlayerData) {
/* 28 */       super(param1PlayerData);
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLevel() {
/* 33 */       PlayerData playerData = SkillAPI.getPlayerData((OfflinePlayer)getPlayer());
/* 34 */       return playerData.hasClass() ? playerData.getMainClass().getLevel() : 0;
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 39 */       PlayerData playerData = SkillAPI.getPlayerData((OfflinePlayer)getPlayer());
/* 40 */       return playerData.hasClass() ? playerData.getMainClass().getData().getName() : "";
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 45 */       PlayerData playerData = SkillAPI.getPlayerData((OfflinePlayer)getPlayer());
/* 46 */       return playerData.hasClass() ? playerData.getMana() : 0.0D;
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 51 */       return getPlayer().getFoodLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 56 */       PlayerData playerData = SkillAPI.getPlayerData((OfflinePlayer)getPlayer());
/* 57 */       if (playerData.hasClass()) {
/* 58 */         playerData.setMana(param1Double);
/*    */       }
/*    */     }
/*    */     
/*    */     public void setStamina(double param1Double) {
/* 63 */       getPlayer().setFoodLevel((int)param1Double);
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\SkillAPIHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */