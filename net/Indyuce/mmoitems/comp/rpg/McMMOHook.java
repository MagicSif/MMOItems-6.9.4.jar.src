/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import com.gmail.nossr50.api.ExperienceAPI;
/*    */ import com.gmail.nossr50.api.exceptions.McMMOPlayerNotFoundException;
/*    */ import com.gmail.nossr50.events.experience.McMMOPlayerLevelDownEvent;
/*    */ import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.stat.type.DisableStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class McMMOHook
/*    */   implements RPGHandler, Listener
/*    */ {
/* 26 */   public static final ItemStat disableMcMMORepair = (ItemStat)new DisableStat("MCMMO_REPAIR", Material.IRON_BLOCK, "Disable McMMO Repair", new String[] { "Players can't repair this with McMMO." });
/*    */ 
/*    */   
/*    */   @EventHandler(ignoreCancelled = true)
/*    */   public void a(McMMOPlayerLevelUpEvent paramMcMMOPlayerLevelUpEvent) {
/* 31 */     PlayerData.get((OfflinePlayer)paramMcMMOPlayerLevelUpEvent.getPlayer()).getInventory().scheduleUpdate();
/*    */   }
/*    */   
/*    */   @EventHandler(ignoreCancelled = true)
/*    */   public void b(McMMOPlayerLevelDownEvent paramMcMMOPlayerLevelDownEvent) {
/* 36 */     PlayerData.get((OfflinePlayer)paramMcMMOPlayerLevelDownEvent.getPlayer()).getInventory().scheduleUpdate();
/*    */   }
/*    */ 
/*    */   
/*    */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/* 41 */     return new McMMOPlayer(paramPlayerData);
/*    */   }
/*    */   
/*    */   public void refreshStats(PlayerData paramPlayerData) {}
/*    */   
/*    */   public static class McMMOPlayer
/*    */     extends RPGPlayer
/*    */   {
/*    */     public McMMOPlayer(PlayerData param1PlayerData) {
/* 50 */       super(param1PlayerData);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public int getLevel() {
/*    */       try {
/* 61 */         return ExperienceAPI.getPowerLevel(getPlayer());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */       }
/* 70 */       catch (McMMOPlayerNotFoundException mcMMOPlayerNotFoundException) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 76 */         return 0;
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 82 */       return "";
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 87 */       return getPlayer().getFoodLevel();
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 92 */       return 0.0D;
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 97 */       getPlayer().setFoodLevel((int)param1Double);
/*    */     }
/*    */     
/*    */     public void setStamina(double param1Double) {}
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\McMMOHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */