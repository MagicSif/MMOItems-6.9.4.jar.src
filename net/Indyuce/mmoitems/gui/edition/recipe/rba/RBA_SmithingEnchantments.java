/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.rba;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import java.util.ArrayList;
/*    */ import net.Indyuce.mmoitems.api.crafting.recipe.SmithingCombinationType;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.type.RBA_ChooseableButton;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RBA_SmithingEnchantments
/*    */   extends RBA_ChooseableButton
/*    */ {
/*    */   @NotNull
/*    */   final ItemStack chooseableButton;
/*    */   public static final String SMITH_ENCHANTS = "enchantments";
/*    */   
/*    */   public RBA_SmithingEnchantments(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/* 26 */     super(paramRecipeMakerGUI);
/*    */     
/* 28 */     this
/*    */       
/* 30 */       .chooseableButton = ItemFactory.of(Material.ENCHANTING_TABLE).name("§aEnchantment Transfer").lore(SilentNumbers.chop("What will happen to the enchantments of the ingredients? Will enchanted ingredients produce an enchanted output item?", 65, "§7")).build(); } @NotNull
/*    */   public ItemStack getChooseableButton() {
/* 32 */     return this.chooseableButton;
/*    */   }
/*    */   @NotNull
/* 35 */   public String getChooseableConfigPath() { return "enchantments"; } @NotNull
/* 36 */   public ArrayList<String> getChooseableList() { return RBA_SmithingUpgrades.getSmithingList(); } @NotNull
/* 37 */   public String getDefaultValue() { return SmithingCombinationType.MAXIMUM.toString(); } @NotNull
/*    */   public String getChooseableDefinition(@NotNull String paramString) {
/* 39 */     SmithingCombinationType smithingCombinationType = SmithingCombinationType.MAXIMUM; 
/* 40 */     try { smithingCombinationType = SmithingCombinationType.valueOf(getCurrentChooseableValue()); } catch (IllegalArgumentException illegalArgumentException) {}
/*    */     
/* 42 */     switch (smithingCombinationType) {
/*    */       case EVEN:
/* 44 */         return "For each enchantment, will take the average of that enchantment's level across the ingredients.";
/*    */       case NONE:
/* 46 */         return "Will ignore the enchantments of any ingredients.";
/*    */       case MAXIMUM:
/* 48 */         return "Output will have the best enchantment from each ingredient";
/*    */       case MINIMUM:
/* 50 */         return "Output will have worst enchantment from each ingredient with that enchantment.";
/*    */       case ADDITIVE:
/* 52 */         return "The enchantments of all ingredients will add together.";
/*    */     } 
/* 54 */     return "Unknown behaviour. Add description in net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_SmithingEnchantments";
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RBA_SmithingEnchantments.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */