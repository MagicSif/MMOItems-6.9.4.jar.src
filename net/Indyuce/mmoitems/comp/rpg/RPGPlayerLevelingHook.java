/*    */ package net.Indyuce.mmoitems.comp.rpg;
/*    */ 
/*    */ import me.baks.rpl.api.API;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ 
/*    */ 
/*    */ public class RPGPlayerLevelingHook
/*    */   implements RPGHandler
/*    */ {
/*    */   public void refreshStats(PlayerData paramPlayerData) {}
/*    */   
/*    */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/* 14 */     return new RPGPlayerLevelingPlayer(paramPlayerData);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static class RPGPlayerLevelingPlayer
/*    */     extends RPGPlayer
/*    */   {
/*    */     public RPGPlayerLevelingPlayer(PlayerData param1PlayerData) {
/* 24 */       super(param1PlayerData);
/*    */     }
/*    */ 
/*    */     
/*    */     public int getLevel() {
/* 29 */       return (new API()).getPlayerLevel(getPlayer());
/*    */     }
/*    */ 
/*    */     
/*    */     public String getClassName() {
/* 34 */       return "";
/*    */     }
/*    */ 
/*    */     
/*    */     public double getMana() {
/* 39 */       return (new API()).getMana(getPlayer());
/*    */     }
/*    */ 
/*    */     
/*    */     public double getStamina() {
/* 44 */       return (new API()).getPower(getPlayer());
/*    */     }
/*    */ 
/*    */     
/*    */     public void setMana(double param1Double) {
/* 49 */       (new API()).setMana(getPlayer(), (int)param1Double);
/*    */     }
/*    */ 
/*    */     
/*    */     public void setStamina(double param1Double) {
/* 54 */       (new API()).setPower(getPlayer(), (int)param1Double);
/*    */     }
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\RPGPlayerLevelingHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */