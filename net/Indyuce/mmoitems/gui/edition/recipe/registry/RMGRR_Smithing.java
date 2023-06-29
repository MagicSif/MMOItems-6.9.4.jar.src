/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.registry;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.ingredients.MythicRecipeIngredient;
/*     */ import io.lumine.mythic.lib.api.crafting.outputs.MythicRecipeOutput;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipeBlueprint;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipeStation;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.ShapedRecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.ShapelessRecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.util.Ref;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CustomSmithingRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.SmithingCombinationType;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_Smithing;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class RMGRR_Smithing implements RecipeRegistry {
/*     */   @NotNull
/*  29 */   public String getRecipeTypeName() { return "Smithing"; } @NotNull
/*  30 */   public String getRecipeConfigPath() { return "smithing"; }
/*     */    @NotNull
/*  32 */   final ItemStack displayListItem = RecipeMakerGUI.rename(new ItemStack(Material.SMITHING_TABLE), FFPMMOItems.get().getExampleFormat() + "Smithing Recipe"); @NotNull
/*  33 */   public ItemStack getDisplayListItem() { return this.displayListItem; }
/*     */   
/*     */   public void openForPlayer(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  36 */     (new RMG_Smithing(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited(), paramString, this)).open(paramEditionInventory.getPreviousPage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MythicRecipeBlueprint sendToMythicLib(@NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull ConfigurationSection paramConfigurationSection, @NotNull String paramString, @NotNull Ref<NamespacedKey> paramRef, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/*  44 */     RecipeMakerGUI.moveInput(paramConfigurationSection, paramString);
/*     */ 
/*     */     
/*  47 */     ConfigurationSection configurationSection = RecipeMakerGUI.getSection(paramConfigurationSection, paramString);
/*  48 */     NamespacedKey namespacedKey = (NamespacedKey)paramRef.getValue();
/*  49 */     if (namespacedKey == null) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Illegal (Null) Namespace", new String[0]));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     String str1 = RMGRI_Smithing.updateIngredients(configurationSection.getString("input"));
/*  55 */     String[] arrayOfString1 = str1.split("\\|");
/*     */ 
/*     */     
/*  58 */     ProvidedUIFilter providedUIFilter1 = RecipeMakerGUI.readIngredientFrom(arrayOfString1[0], paramFriendlyFeedbackProvider);
/*  59 */     ProvidedUIFilter providedUIFilter2 = RecipeMakerGUI.readIngredientFrom(arrayOfString1[1], paramFriendlyFeedbackProvider);
/*  60 */     if (providedUIFilter1.isAir() || providedUIFilter2.isAir()) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Smithing recipe containing AIR, $fignored$b.", new String[0]));
/*     */ 
/*     */     
/*  63 */     MythicRecipeIngredient mythicRecipeIngredient1 = new MythicRecipeIngredient(providedUIFilter1);
/*  64 */     MythicRecipeIngredient mythicRecipeIngredient2 = new MythicRecipeIngredient(providedUIFilter2);
/*     */ 
/*     */     
/*  67 */     ShapelessRecipe shapelessRecipe1 = new ShapelessRecipe(namespacedKey.getKey(), new MythicRecipeIngredient[] { mythicRecipeIngredient1 });
/*  68 */     ShapelessRecipe shapelessRecipe2 = new ShapelessRecipe(namespacedKey.getKey(), new MythicRecipeIngredient[] { mythicRecipeIngredient2 });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     String str2 = RMGRI_Smithing.updateIngredients(configurationSection.getString("output"));
/*  74 */     String[] arrayOfString2 = str2.split("\\|");
/*     */ 
/*     */     
/*  77 */     ProvidedUIFilter providedUIFilter3 = RecipeMakerGUI.readIngredientFrom(arrayOfString2[0], paramFriendlyFeedbackProvider);
/*  78 */     ProvidedUIFilter providedUIFilter4 = RecipeMakerGUI.readIngredientFrom(arrayOfString2[1], paramFriendlyFeedbackProvider);
/*     */ 
/*     */     
/*  81 */     ShapedRecipe shapedRecipe1 = providedUIFilter3.isAir() ? null : ShapedRecipe.single(namespacedKey.getKey(), new ProvidedUIFilter[] { providedUIFilter3 });
/*  82 */     ShapedRecipe shapedRecipe2 = providedUIFilter4.isAir() ? null : ShapedRecipe.single(namespacedKey.getKey(), new ProvidedUIFilter[] { providedUIFilter4 });
/*     */ 
/*     */ 
/*     */     
/*  86 */     int i = configurationSection.getInt("amount", 1);
/*  87 */     boolean bool = configurationSection.getBoolean("drop-gems", false);
/*  88 */     SmithingCombinationType smithingCombinationType1 = readSCT(configurationSection.getString("enchantments"));
/*  89 */     SmithingCombinationType smithingCombinationType2 = readSCT(configurationSection.getString("upgrades"));
/*     */ 
/*     */     
/*  92 */     CustomSmithingRecipe customSmithingRecipe = new CustomSmithingRecipe(paramMMOItemTemplate, bool, smithingCombinationType1, smithingCombinationType2, i);
/*  93 */     customSmithingRecipe.setMainInputConsumption((MythicRecipe)shapedRecipe1);
/*  94 */     customSmithingRecipe.setIngotInputConsumption((MythicRecipe)shapedRecipe2);
/*     */ 
/*     */     
/*  97 */     MythicRecipeBlueprint mythicRecipeBlueprint = new MythicRecipeBlueprint((MythicRecipe)shapelessRecipe1, (MythicRecipeOutput)customSmithingRecipe, namespacedKey);
/*  98 */     mythicRecipeBlueprint.addSideCheck("ingot", (MythicRecipe)shapelessRecipe2);
/*     */ 
/*     */     
/* 101 */     mythicRecipeBlueprint.deploy(MythicRecipeStation.SMITHING, paramRef);
/*     */ 
/*     */     
/* 104 */     return mythicRecipeBlueprint;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   SmithingCombinationType readSCT(@Nullable String paramString) {
/* 110 */     if (paramString == null) return SmithingCombinationType.MAXIMUM;
/*     */ 
/*     */     
/* 113 */     try { return SmithingCombinationType.valueOf(paramString); } catch (IllegalArgumentException illegalArgumentException) { return SmithingCombinationType.MAXIMUM; }
/*     */   
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\registry\RMGRR_Smithing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */