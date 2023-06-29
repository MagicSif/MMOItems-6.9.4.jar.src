/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.registry;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.ingredients.ShapedIngredient;
/*     */ import io.lumine.mythic.lib.api.crafting.outputs.MRORecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.outputs.MythicRecipeOutput;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipeBlueprint;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipeStation;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.ShapedRecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.uifilters.UIFilter;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.util.Ref;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.api.crafting.MMOItemUIFilter;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RMG_MegaShaped;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_MegaShaped;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class RMGRR_MegaShaped implements RecipeRegistry {
/*     */   @NotNull
/*  31 */   public String getRecipeConfigPath() { return "megashaped"; } @NotNull
/*  32 */   public String getRecipeTypeName() { return "Mega Shaped"; }
/*     */    @NotNull
/*  34 */   final ItemStack displayListItem = RecipeMakerGUI.rename(new ItemStack(Material.JUKEBOX), FFPMMOItems.get().getExampleFormat() + "Mega Shaped Recipe"); @NotNull
/*  35 */   public ItemStack getDisplayListItem() { return this.displayListItem; }
/*     */   
/*     */   public void openForPlayer(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  38 */     (new RMG_MegaShaped(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited(), paramString, this)).open(paramEditionInventory.getPreviousPage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MythicRecipeBlueprint sendToMythicLib(@NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull ConfigurationSection paramConfigurationSection, @NotNull String paramString, @NotNull Ref<NamespacedKey> paramRef, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/*  46 */     ConfigurationSection configurationSection = RecipeMakerGUI.moveInput(paramConfigurationSection, paramString);
/*     */     
/*  48 */     NamespacedKey namespacedKey = (NamespacedKey)paramRef.getValue();
/*  49 */     if (namespacedKey == null) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Illegal (Null) Namespace", new String[0]));
/*     */ 
/*     */     
/*  52 */     ShapedRecipe shapedRecipe1 = megaShapedRecipeFromList(namespacedKey.getKey(), new ArrayList<>(configurationSection.getStringList("input")), paramFriendlyFeedbackProvider);
/*  53 */     if (shapedRecipe1 == null) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Shaped recipe containing only AIR, $fignored$b.", new String[0]));
/*     */ 
/*     */     
/*  56 */     ShapedRecipe shapedRecipe2 = megaShapedRecipeFromList(namespacedKey.getKey(), new ArrayList<>(configurationSection.getStringList("output")), paramFriendlyFeedbackProvider);
/*  57 */     int i = configurationSection.getInt("amount", 1);
/*     */ 
/*     */     
/*  60 */     ShapedRecipe shapedRecipe3 = ShapedRecipe.single(namespacedKey.getKey(), new ProvidedUIFilter[] { new ProvidedUIFilter((UIFilter)MMOItemUIFilter.get(), paramMMOItemTemplate.getType().getId(), paramMMOItemTemplate.getId(), Math.max(i, 1)) });
/*  61 */     MRORecipe mRORecipe = new MRORecipe(shapedRecipe3, shapedRecipe2);
/*     */ 
/*     */     
/*  64 */     MythicRecipeBlueprint mythicRecipeBlueprint = new MythicRecipeBlueprint((MythicRecipe)shapedRecipe1, (MythicRecipeOutput)mRORecipe, namespacedKey);
/*     */ 
/*     */     
/*  67 */     mythicRecipeBlueprint.deploy(MythicRecipeStation.WORKBENCH, null);
/*     */ 
/*     */     
/*  70 */     paramRef.setValue(null);
/*     */ 
/*     */     
/*  73 */     return mythicRecipeBlueprint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static ShapedRecipe megaShapedRecipeFromList(@NotNull String paramString, @NotNull ArrayList<String> paramArrayList, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 103 */     ArrayList<ShapedIngredient> arrayList = new ArrayList();
/* 104 */     boolean bool = false;
/* 105 */     byte b = 0;
/*     */ 
/*     */ 
/*     */     
/* 109 */     for (String str1 : paramArrayList) {
/*     */ 
/*     */ 
/*     */       
/* 113 */       String arrayOfString[], str2 = RMGRI_MegaShaped.updateRow(str1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 129 */       if (str2.contains("|")) {
/*     */ 
/*     */         
/* 132 */         arrayOfString = str2.split("\\|");
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 138 */         arrayOfString = str2.split(" ");
/*     */       } 
/*     */ 
/*     */       
/* 142 */       if (arrayOfString.length != 6) throw new IllegalArgumentException("Invalid mega crafting table row $u" + str2 + "$b ($fNot exactly 6 ingredients wide$b).");
/*     */ 
/*     */       
/* 145 */       ProvidedUIFilter providedUIFilter1 = RecipeMakerGUI.readIngredientFrom(arrayOfString[0], paramFriendlyFeedbackProvider);
/* 146 */       ProvidedUIFilter providedUIFilter2 = RecipeMakerGUI.readIngredientFrom(arrayOfString[1], paramFriendlyFeedbackProvider);
/* 147 */       ProvidedUIFilter providedUIFilter3 = RecipeMakerGUI.readIngredientFrom(arrayOfString[2], paramFriendlyFeedbackProvider);
/* 148 */       ProvidedUIFilter providedUIFilter4 = RecipeMakerGUI.readIngredientFrom(arrayOfString[3], paramFriendlyFeedbackProvider);
/* 149 */       ProvidedUIFilter providedUIFilter5 = RecipeMakerGUI.readIngredientFrom(arrayOfString[4], paramFriendlyFeedbackProvider);
/* 150 */       ProvidedUIFilter providedUIFilter6 = RecipeMakerGUI.readIngredientFrom(arrayOfString[5], paramFriendlyFeedbackProvider);
/* 151 */       if (!providedUIFilter1.isAir()) bool = true; 
/* 152 */       if (!providedUIFilter2.isAir()) bool = true; 
/* 153 */       if (!providedUIFilter3.isAir()) bool = true; 
/* 154 */       if (!providedUIFilter4.isAir()) bool = true; 
/* 155 */       if (!providedUIFilter5.isAir()) bool = true; 
/* 156 */       if (!providedUIFilter6.isAir()) bool = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 170 */       ShapedIngredient shapedIngredient1 = new ShapedIngredient(providedUIFilter1, 0, -b);
/* 171 */       ShapedIngredient shapedIngredient2 = new ShapedIngredient(providedUIFilter2, 1, -b);
/* 172 */       ShapedIngredient shapedIngredient3 = new ShapedIngredient(providedUIFilter3, 2, -b);
/* 173 */       ShapedIngredient shapedIngredient4 = new ShapedIngredient(providedUIFilter4, 3, -b);
/* 174 */       ShapedIngredient shapedIngredient5 = new ShapedIngredient(providedUIFilter5, 4, -b);
/* 175 */       ShapedIngredient shapedIngredient6 = new ShapedIngredient(providedUIFilter6, 5, -b);
/*     */ 
/*     */       
/* 178 */       arrayList.add(shapedIngredient1);
/* 179 */       arrayList.add(shapedIngredient2);
/* 180 */       arrayList.add(shapedIngredient3);
/* 181 */       arrayList.add(shapedIngredient4);
/* 182 */       arrayList.add(shapedIngredient5);
/* 183 */       arrayList.add(shapedIngredient6);
/*     */ 
/*     */       
/* 186 */       b++;
/*     */     } 
/* 188 */     if (!bool) return null;
/*     */ 
/*     */     
/* 191 */     return ShapedRecipe.unsharpen(new ShapedRecipe(paramString, arrayList));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\registry\RMGRR_MegaShaped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */