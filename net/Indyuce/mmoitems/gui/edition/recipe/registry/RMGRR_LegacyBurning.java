/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.registry;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipeBlueprint;
/*    */ import io.lumine.mythic.lib.api.util.Ref;
/*    */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.recipe.workbench.ingredients.WorkbenchIngredient;
/*    */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RMG_BurningLegacy;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy.BurningRecipeInformation;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy.CraftingType;
/*    */ import net.Indyuce.mmoitems.manager.RecipeManager;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.configuration.ConfigurationSection;
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
/*    */ public abstract class RMGRR_LegacyBurning
/*    */   implements RecipeRegistry
/*    */ {
/*    */   @NotNull
/*    */   public abstract CraftingType getLegacyBurningType();
/*    */   
/*    */   @NotNull
/*    */   public static String capitalizeFirst(@NotNull String paramString) {
/* 40 */     return paramString.substring(0, 1).toUpperCase() + paramString.substring(1);
/*    */   } @NotNull
/* 42 */   public String getRecipeConfigPath() { return getLegacyBurningType().name().toLowerCase(); } @NotNull
/*    */   public String getRecipeTypeName() {
/* 44 */     return "§8{§4§oL§8} " + capitalizeFirst(getRecipeConfigPath());
/*    */   }
/*    */   
/*    */   @NotNull
/* 48 */   private final ItemStack displayListItem = RecipeMakerGUI.rename(getLegacyBurningType().getItem(), FFPMMOItems.get().getExampleFormat() + capitalizeFirst(getRecipeConfigPath()) + " Recipe");
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getDisplayListItem() {
/* 53 */     return this.displayListItem;
/*    */   }
/*    */   public void openForPlayer(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/* 56 */     (new RMG_BurningLegacy(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited(), paramString, this)).open(paramEditionInventory.getPreviousPage());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MythicRecipeBlueprint sendToMythicLib(@NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull ConfigurationSection paramConfigurationSection, @NotNull String paramString, @NotNull Ref<NamespacedKey> paramRef, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 66 */     Validate.isTrue((paramRef.getValue() != null));
/*    */ 
/*    */     
/* 69 */     ConfigurationSection configurationSection = RecipeMakerGUI.getSection(paramConfigurationSection, paramString);
/*    */ 
/*    */     
/* 72 */     String str = configurationSection.getString("item");
/* 73 */     if (str == null) throw new IllegalArgumentException("Missing input ingredient"); 
/* 74 */     WorkbenchIngredient workbenchIngredient = RecipeManager.getWorkbenchIngredient(str);
/*    */ 
/*    */     
/* 77 */     int i = configurationSection.getInt("amount", 1);
/* 78 */     double d = configurationSection.getDouble("exp", 0.35D);
/* 79 */     int j = configurationSection.getInt("time", SilentNumbers.round(200.0D));
/* 80 */     boolean bool = configurationSection.getBoolean("hidden", false);
/*    */ 
/*    */     
/* 83 */     BurningRecipeInformation burningRecipeInformation = new BurningRecipeInformation(workbenchIngredient, (float)d, j);
/*    */ 
/*    */     
/* 86 */     MMOItems.plugin.getRecipes().registerBurningRecipe(
/* 87 */         getLegacyBurningType().getBurningType(), paramMMOItemTemplate
/* 88 */         .newBuilder(0, null).build(), burningRecipeInformation, i, (NamespacedKey)paramRef
/* 89 */         .getValue(), bool);
/*    */     
/* 91 */     throw new IllegalArgumentException("");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\registry\RMGRR_LegacyBurning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */