/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class GenericCondition
/*    */   extends Condition
/*    */ {
/*    */   public final String display;
/*    */   
/*    */   public GenericCondition(String paramString, MMOLineConfig paramMMOLineConfig) {
/* 17 */     super(paramString);
/*    */     
/* 19 */     this.display = paramMMOLineConfig.getString("display", "<No display chosen>");
/*    */   }
/*    */ 
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 24 */     return paramString.replace("#display#", this.display);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\GenericCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */