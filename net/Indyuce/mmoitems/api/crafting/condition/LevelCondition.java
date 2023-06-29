/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LevelCondition
/*    */   extends Condition
/*    */ {
/*    */   private final int level;
/*    */   private final boolean consume;
/*    */   
/*    */   public LevelCondition(MMOLineConfig paramMMOLineConfig) {
/* 16 */     super("level");
/*    */     
/* 18 */     paramMMOLineConfig.validate(new String[] { "level" });
/* 19 */     this.level = paramMMOLineConfig.getInt("level");
/* 20 */     this.consume = paramMMOLineConfig.getBoolean("consume", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 25 */     return (paramPlayerData.getRPG().getLevel() >= this.level);
/*    */   }
/*    */ 
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 30 */     return paramString.replace("#level#", "" + this.level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 35 */     if (this.consume)
/* 36 */       paramPlayerData.getPlayer().setLevel(Math.max(0, paramPlayerData.getPlayer().getLevel() - this.level)); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\LevelCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */