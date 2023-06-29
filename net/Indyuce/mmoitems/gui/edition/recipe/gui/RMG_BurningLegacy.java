/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.gui;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_BurningLegacy;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMG_RecipeInterpreter;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_CookingTime;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_Experience;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_HideFromBook;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RecipeButtonAction;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RecipeRegistry;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RMG_BurningLegacy
/*    */   extends RecipeMakerGUI
/*    */ {
/*    */   @NotNull
/* 26 */   HashMap<Integer, Integer> inputLinks = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   final RMGRI_BurningLegacy interpreter;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RMG_BurningLegacy(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull String paramString, @NotNull RecipeRegistry paramRecipeRegistry) {
/* 38 */     super(paramPlayer, paramMMOItemTemplate, paramString, paramRecipeRegistry);
/* 39 */     addButton((RecipeButtonAction)new RBA_HideFromBook(this));
/* 40 */     addButton((RecipeButtonAction)new RBA_Experience(this));
/* 41 */     addButton((RecipeButtonAction)new RBA_CookingTime(this));
/*    */ 
/*    */     
/* 44 */     if (!isShowingInput()) switchInput();
/*    */ 
/*    */     
/* 47 */     this.interpreter = new RMGRI_BurningLegacy(getNameSection());
/*    */ 
/*    */     
/* 50 */     this.inputLinks.put(Integer.valueOf(40), Integer.valueOf(0));
/*    */   }
/*    */   public int getButtonsRow() {
/* 53 */     return 2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void putRecipe(@NotNull Inventory paramInventory) {
/* 59 */     for (Integer integer : this.inputLinks.keySet()) paramInventory.setItem(integer.intValue(), getDisplay(isShowingInput(), ((Integer)this.inputLinks.get(integer)).intValue()));
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   int getInputSlot(int paramInt) {
/* 66 */     Integer integer = this.inputLinks.get(Integer.valueOf(paramInt));
/*    */ 
/*    */     
/* 69 */     return (integer != null) ? integer.intValue() : -1;
/*    */   }
/*    */   @NotNull
/*    */   public RMG_RecipeInterpreter getInterpreter() {
/* 73 */     return (RMG_RecipeInterpreter)this.interpreter;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\gui\RMG_BurningLegacy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */