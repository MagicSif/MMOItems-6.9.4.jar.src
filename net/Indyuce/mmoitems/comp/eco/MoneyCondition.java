/*    */ package net.Indyuce.mmoitems.comp.eco;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.crafting.condition.Condition;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ 
/*    */ public class MoneyCondition
/*    */   extends Condition {
/*    */   public MoneyCondition(MMOLineConfig paramMMOLineConfig) {
/* 13 */     super("money");
/*    */     
/* 15 */     Validate.isTrue(MMOItems.plugin.hasEconomy(), "No economy plugin found");
/* 16 */     paramMMOLineConfig.validateKeys(new String[] { "amount" });
/* 17 */     this.amount = paramMMOLineConfig.getDouble("amount");
/*    */   }
/*    */   private final double amount;
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 22 */     return MMOItems.plugin.getVault().getEconomy().has((OfflinePlayer)paramPlayerData.getPlayer(), this.amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 27 */     return paramString.replace("#money#", String.valueOf(this.amount));
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 32 */     MMOItems.plugin.getVault().getEconomy().withdrawPlayer((OfflinePlayer)paramPlayerData.getPlayer(), this.amount);
/*    */   }
/*    */   
/*    */   public double getAmount() {
/* 36 */     return this.amount;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\eco\MoneyCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */