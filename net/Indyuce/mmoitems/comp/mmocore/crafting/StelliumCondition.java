/*    */ package net.Indyuce.mmoitems.comp.mmocore.crafting;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.crafting.condition.Condition;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ 
/*    */ public class StelliumCondition
/*    */   extends Condition {
/*    */   public StelliumCondition(MMOLineConfig paramMMOLineConfig) {
/* 11 */     super("mana");
/*    */     
/* 13 */     paramMMOLineConfig.validate(new String[] { "amount" });
/* 14 */     this.amount = paramMMOLineConfig.getDouble("amount");
/*    */   }
/*    */   private final double amount;
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 19 */     return (PlayerData.get(paramPlayerData.getUniqueId()).getStellium() >= this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 24 */     return paramString.replace("#stellium#", "" + this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 29 */     PlayerData.get(paramPlayerData.getUniqueId()).giveStellium(-this.amount);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\crafting\StelliumCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */