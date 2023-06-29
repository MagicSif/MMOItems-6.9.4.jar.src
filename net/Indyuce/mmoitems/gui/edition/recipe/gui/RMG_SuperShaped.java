/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.gui;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_SuperShaped;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMG_RecipeInterpreter;
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
/*    */ 
/*    */ public class RMG_SuperShaped
/*    */   extends RecipeMakerGUI
/*    */ {
/*    */   @NotNull
/* 22 */   HashMap<Integer, Integer> inputLinks = new HashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   final RMGRI_SuperShaped interpreter;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RMG_SuperShaped(@NotNull Player paramPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull String paramString, @NotNull RecipeRegistry paramRecipeRegistry) {
/* 33 */     super(paramPlayer, paramMMOItemTemplate, paramString, paramRecipeRegistry);
/* 34 */     addButton((RecipeButtonAction)new RBA_InputOutput(this));
/*    */ 
/*    */     
/* 37 */     this.interpreter = new RMGRI_SuperShaped(getNameSection());
/*    */ 
/*    */     
/* 40 */     this.inputLinks.put(Integer.valueOf(11), Integer.valueOf(0));
/* 41 */     this.inputLinks.put(Integer.valueOf(12), Integer.valueOf(1));
/* 42 */     this.inputLinks.put(Integer.valueOf(13), Integer.valueOf(2));
/* 43 */     this.inputLinks.put(Integer.valueOf(14), Integer.valueOf(3));
/* 44 */     this.inputLinks.put(Integer.valueOf(15), Integer.valueOf(4));
/*    */     
/* 46 */     this.inputLinks.put(Integer.valueOf(20), Integer.valueOf(5));
/* 47 */     this.inputLinks.put(Integer.valueOf(21), Integer.valueOf(6));
/* 48 */     this.inputLinks.put(Integer.valueOf(22), Integer.valueOf(7));
/* 49 */     this.inputLinks.put(Integer.valueOf(23), Integer.valueOf(8));
/* 50 */     this.inputLinks.put(Integer.valueOf(24), Integer.valueOf(9));
/*    */     
/* 52 */     this.inputLinks.put(Integer.valueOf(29), Integer.valueOf(10));
/* 53 */     this.inputLinks.put(Integer.valueOf(30), Integer.valueOf(11));
/* 54 */     this.inputLinks.put(Integer.valueOf(31), Integer.valueOf(12));
/* 55 */     this.inputLinks.put(Integer.valueOf(32), Integer.valueOf(13));
/* 56 */     this.inputLinks.put(Integer.valueOf(33), Integer.valueOf(14));
/*    */     
/* 58 */     this.inputLinks.put(Integer.valueOf(38), Integer.valueOf(15));
/* 59 */     this.inputLinks.put(Integer.valueOf(39), Integer.valueOf(16));
/* 60 */     this.inputLinks.put(Integer.valueOf(40), Integer.valueOf(17));
/* 61 */     this.inputLinks.put(Integer.valueOf(41), Integer.valueOf(18));
/* 62 */     this.inputLinks.put(Integer.valueOf(42), Integer.valueOf(19));
/*    */     
/* 64 */     this.inputLinks.put(Integer.valueOf(47), Integer.valueOf(20));
/* 65 */     this.inputLinks.put(Integer.valueOf(48), Integer.valueOf(21));
/* 66 */     this.inputLinks.put(Integer.valueOf(49), Integer.valueOf(22));
/* 67 */     this.inputLinks.put(Integer.valueOf(50), Integer.valueOf(23));
/* 68 */     this.inputLinks.put(Integer.valueOf(51), Integer.valueOf(24));
/*    */   }
/*    */   public int getButtonsRow() {
/* 71 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void putRecipe(@NotNull Inventory paramInventory) {
/* 77 */     for (Integer integer : this.inputLinks.keySet()) paramInventory.setItem(integer.intValue(), getDisplay(isShowingInput(), ((Integer)this.inputLinks.get(integer)).intValue()));
/*    */   
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   int getInputSlot(int paramInt) {
/* 84 */     Integer integer = this.inputLinks.get(Integer.valueOf(paramInt));
/*    */ 
/*    */     
/* 87 */     return (integer != null) ? integer.intValue() : -1;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public RMG_RecipeInterpreter getInterpreter() {
/* 93 */     return (RMG_RecipeInterpreter)this.interpreter;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\gui\RMG_SuperShaped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */