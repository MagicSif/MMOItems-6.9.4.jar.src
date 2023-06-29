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
/*    */ public class RBA_SmithingUpgrades
/*    */   extends RBA_ChooseableButton
/*    */ {
/*    */   @NotNull
/*    */   final ItemStack chooseableButton;
/*    */   public static final String SMITH_UPGRADES = "upgrades";
/*    */   static ArrayList<String> smithingList;
/*    */   
/*    */   public RBA_SmithingUpgrades(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/* 25 */     super(paramRecipeMakerGUI);
/*    */     
/* 27 */     this
/*    */       
/* 29 */       .chooseableButton = ItemFactory.of(Material.ANVIL).name("§aUpgrades Transfer").lore(SilentNumbers.chop("What will happen to the upgrades of the ingredients? Will upgraded ingredients produce an upgraded output item?", 65, "§7")).build(); } @NotNull
/*    */   public ItemStack getChooseableButton() {
/* 31 */     return this.chooseableButton;
/*    */   }
/*    */   @NotNull
/* 34 */   public String getChooseableConfigPath() { return "upgrades"; } @NotNull
/* 35 */   public ArrayList<String> getChooseableList() { return getSmithingList(); } @NotNull
/* 36 */   public String getDefaultValue() { return SmithingCombinationType.MAXIMUM.toString(); } @NotNull
/*    */   public String getChooseableDefinition(@NotNull String paramString) {
/* 38 */     SmithingCombinationType smithingCombinationType = SmithingCombinationType.MAXIMUM; 
/* 39 */     try { smithingCombinationType = SmithingCombinationType.valueOf(getCurrentChooseableValue()); } catch (IllegalArgumentException illegalArgumentException) {}
/*    */     
/* 41 */     switch (smithingCombinationType) {
/*    */       case EVEN:
/* 43 */         return "Will take the average of the upgrade levels of the combined items.";
/*    */       case NONE:
/* 45 */         return "Will ignore the upgrade levels of any ingredients.";
/*    */       case MAXIMUM:
/* 47 */         return "Output will have the upgrade level of the most upgraded ingredient.";
/*    */       case MINIMUM:
/* 49 */         return "Output will have the upgrade level of the least-upgraded upgradeable ingredient.";
/*    */       case ADDITIVE:
/* 51 */         return "The upgrade levels of the ingredients will be added, and the result will be the crafted item's level.";
/*    */     } 
/* 53 */     return "Unknown behaviour. Add description in net.Indyuce.mmoitems.gui.edition.recipe.rba.RBA_SmithingUpgrades";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   static ArrayList<String> getSmithingList() {
/* 62 */     if (smithingList != null) return smithingList; 
/* 63 */     smithingList = new ArrayList<>();
/* 64 */     for (SmithingCombinationType smithingCombinationType : SmithingCombinationType.values()) smithingList.add(smithingCombinationType.toString()); 
/* 65 */     return smithingList;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RBA_SmithingUpgrades.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */