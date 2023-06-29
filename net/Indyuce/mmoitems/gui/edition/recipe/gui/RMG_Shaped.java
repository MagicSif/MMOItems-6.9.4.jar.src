/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.gui;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_Shaped;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMG_RecipeInterpreter;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_HideFromBook;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_InputOutput;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.RecipeButtonAction;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RecipeRegistry;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RMG_Shaped
/*    */   extends RecipeMakerGUI
/*    */ {
/*    */   @NotNull
/* 22 */   HashMap<Integer, Integer> inputLinks = new HashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   final RMGRI_Shaped interpreter;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RMG_Shaped(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull String paramString, @NotNull RecipeRegistry paramRecipeRegistry) {
/* 33 */     super(paramPlayer, paramMMOItemTemplate, paramString, paramRecipeRegistry);
/* 34 */     addButton((RecipeButtonAction)new RBA_InputOutput(this));
/* 35 */     addButton((RecipeButtonAction)new RBA_HideFromBook(this));
/*    */ 
/*    */     
/* 38 */     this.interpreter = new RMGRI_Shaped(getNameSection());
/*    */ 
/*    */     
/* 41 */     this.inputLinks.put(Integer.valueOf(30), Integer.valueOf(0));
/* 42 */     this.inputLinks.put(Integer.valueOf(31), Integer.valueOf(1));
/* 43 */     this.inputLinks.put(Integer.valueOf(32), Integer.valueOf(2));
/*    */     
/* 45 */     this.inputLinks.put(Integer.valueOf(39), Integer.valueOf(3));
/* 46 */     this.inputLinks.put(Integer.valueOf(40), Integer.valueOf(4));
/* 47 */     this.inputLinks.put(Integer.valueOf(41), Integer.valueOf(5));
/*    */     
/* 49 */     this.inputLinks.put(Integer.valueOf(48), Integer.valueOf(6));
/* 50 */     this.inputLinks.put(Integer.valueOf(49), Integer.valueOf(7));
/* 51 */     this.inputLinks.put(Integer.valueOf(50), Integer.valueOf(8));
/*    */   }
/*    */   public int getButtonsRow() {
/* 54 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void putRecipe(@NotNull Inventory paramInventory) {
/* 60 */     for (Integer integer : this.inputLinks.keySet()) paramInventory.setItem(integer.intValue(), getDisplay(isShowingInput(), ((Integer)this.inputLinks.get(integer)).intValue()));
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   int getInputSlot(int paramInt) {
/* 67 */     Integer integer = this.inputLinks.get(Integer.valueOf(paramInt));
/*    */ 
/*    */     
/* 70 */     return (integer != null) ? integer.intValue() : -1;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public RMG_RecipeInterpreter getInterpreter() {
/* 76 */     return (RMG_RecipeInterpreter)this.interpreter;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\gui\RMG_Shaped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */