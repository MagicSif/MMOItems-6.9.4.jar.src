/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ 
/*    */ public class FoodCondition extends Condition {
/*    */   private final int amount;
/*    */   
/*    */   public FoodCondition(MMOLineConfig paramMMOLineConfig) {
/* 10 */     super("food");
/*    */     
/* 12 */     paramMMOLineConfig.validate(new String[] { "amount" });
/* 13 */     this.amount = paramMMOLineConfig.getInt("amount");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 18 */     if (!paramPlayerData.isOnline()) return false; 
/* 19 */     return (paramPlayerData.getPlayer().getFoodLevel() >= this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 24 */     return paramString.replace("#food#", "" + this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 29 */     if (!paramPlayerData.isOnline())
/* 30 */       return;  paramPlayerData.getPlayer().setFoodLevel(Math.max(0, paramPlayerData.getPlayer().getFoodLevel() - this.amount));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\FoodCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */