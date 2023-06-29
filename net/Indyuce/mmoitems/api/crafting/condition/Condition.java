/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.crafting.ConditionalDisplay;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Condition
/*    */ {
/*    */   private final String id;
/*    */   
/*    */   public Condition(String paramString) {
/* 17 */     this.id = paramString;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 21 */     return this.id;
/*    */   }
/*    */   
/*    */   public ConditionalDisplay getDisplay() {
/* 25 */     return MMOItems.plugin.getCrafting().getConditionInfo(this.id).getDisplay();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract boolean isMet(PlayerData paramPlayerData);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract String formatDisplay(String paramString);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void whenCrafting(PlayerData paramPlayerData);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CheckedCondition evaluateCondition(PlayerData paramPlayerData) {
/* 53 */     return new CheckedCondition(this, isMet(paramPlayerData));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\Condition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */