/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.rba;
/*    */ 
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ public abstract class RecipeButtonAction
/*    */ {
/*    */   @NotNull
/*    */   final RecipeMakerGUI inv;
/*    */   
/*    */   @NotNull
/*    */   public RecipeMakerGUI getInv() {
/* 28 */     return this.inv;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeButtonAction(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/* 35 */     this.inv = paramRecipeMakerGUI;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract boolean runPrimary();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void primaryProcessInput(@NotNull String paramString, Object... paramVarArgs);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract boolean runSecondary();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void secondaryProcessInput(@NotNull String paramString, Object... paramVarArgs);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public abstract ItemStack getButton();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void clickSFX() {
/* 95 */     getInv().getPlayer().playSound(getInv().getPlayer().getLocation(), Sound.UI_BUTTON_CLICK, 1.0F, 1.0F);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RecipeButtonAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */