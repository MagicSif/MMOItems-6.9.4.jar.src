/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ 
/*    */ public class ManaCondition extends Condition {
/*    */   private final double amount;
/*    */   
/*    */   public ManaCondition(MMOLineConfig paramMMOLineConfig) {
/* 10 */     super("mana");
/*    */     
/* 12 */     paramMMOLineConfig.validate(new String[] { "amount" });
/* 13 */     this.amount = paramMMOLineConfig.getDouble("amount");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 18 */     return (paramPlayerData.getRPG().getMana() >= this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 23 */     return paramString.replace("#mana#", "" + this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 28 */     paramPlayerData.getRPG().giveMana(-this.amount);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\ManaCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */