/*    */ package net.Indyuce.mmoitems.api.crafting;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LoadedCraftingObject<C>
/*    */ {
/*    */   private final String id;
/*    */   private final Function<MMOLineConfig, C> function;
/*    */   private ConditionalDisplay display;
/*    */   
/*    */   public LoadedCraftingObject(String paramString, Function<MMOLineConfig, C> paramFunction, ConditionalDisplay paramConditionalDisplay) {
/* 24 */     this.id = paramString;
/* 25 */     this.function = paramFunction;
/* 26 */     this.display = paramConditionalDisplay;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 30 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setDisplay(ConditionalDisplay paramConditionalDisplay) {
/* 34 */     this.display = paramConditionalDisplay;
/*    */   }
/*    */   
/*    */   public boolean hasDisplay() {
/* 38 */     return (this.display != null);
/*    */   }
/*    */   
/*    */   public ConditionalDisplay getDisplay() {
/* 42 */     return this.display;
/*    */   }
/*    */   
/*    */   public C load(MMOLineConfig paramMMOLineConfig) {
/* 46 */     return this.function.apply(paramMMOLineConfig);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\LoadedCraftingObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */