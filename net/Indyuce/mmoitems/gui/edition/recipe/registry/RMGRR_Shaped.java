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
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.crafting.MMOItemUIFilter;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RMG_Shaped;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_Shaped;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class RMGRR_Shaped implements RecipeRegistry {
/*     */   @NotNull
/*     */   public String getRecipeConfigPath() {
/*  35 */     return "shaped"; } @NotNull
/*  36 */   public String getRecipeTypeName() { return "Shaped"; }
/*     */    @NotNull
/*  38 */   final ItemStack displayListItem = RecipeMakerGUI.rename(new ItemStack(Material.CRAFTING_TABLE), FFPMMOItems.get().getExampleFormat() + "Shaped Recipe"); @NotNull
/*  39 */   public ItemStack getDisplayListItem() { return this.displayListItem; }
/*     */   
/*     */   public void openForPlayer(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  42 */     (new RMG_Shaped(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited(), paramString, this)).open(paramEditionInventory.getPreviousPage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MythicRecipeBlueprint sendToMythicLib(@NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull ConfigurationSection paramConfigurationSection, @NotNull String paramString, @NotNull Ref<NamespacedKey> paramRef, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/*  50 */     ConfigurationSection configurationSection = RecipeMakerGUI.moveInput(paramConfigurationSection, paramString);
/*     */     
/*  52 */     NamespacedKey namespacedKey = (NamespacedKey)paramRef.getValue();
/*  53 */     if (namespacedKey == null) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Illegal (Null) Namespace", new String[0]));
/*     */ 
/*     */     
/*  56 */     ShapedRecipe shapedRecipe1 = shapedRecipeFromList(namespacedKey.getKey(), new ArrayList<>(configurationSection.getStringList("input")), paramFriendlyFeedbackProvider);
/*  57 */     if (shapedRecipe1 == null) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Shaped recipe containing only AIR, $fignored$b.", new String[0]));
/*     */ 
/*     */     
/*  60 */     ShapedRecipe shapedRecipe2 = shapedRecipeFromList(namespacedKey.getKey(), new ArrayList<>(configurationSection.getStringList("output")), paramFriendlyFeedbackProvider);
/*  61 */     int i = configurationSection.getInt("amount", 1);
/*  62 */     boolean bool = configurationSection.getBoolean("hidden", false);
/*     */ 
/*     */     
/*  65 */     ShapedRecipe shapedRecipe3 = ShapedRecipe.single(namespacedKey.getKey(), new ProvidedUIFilter[] { new ProvidedUIFilter((UIFilter)MMOItemUIFilter.get(), paramMMOItemTemplate.getType().getId(), paramMMOItemTemplate.getId(), Math.max(i, 1)) });
/*  66 */     MRORecipe mRORecipe = new MRORecipe(shapedRecipe3, shapedRecipe2);
/*     */ 
/*     */     
/*  69 */     MythicRecipeBlueprint mythicRecipeBlueprint = new MythicRecipeBlueprint((MythicRecipe)shapedRecipe1, (MythicRecipeOutput)mRORecipe, namespacedKey);
/*     */ 
/*     */     
/*  72 */     RandomStatData randomStatData = (RandomStatData)paramMMOItemTemplate.getBaseItemData().get(ItemStats.CRAFT_PERMISSION);
/*  73 */     if (randomStatData instanceof StringData) {
/*     */ 
/*     */       
/*  76 */       String str = ((StringData)randomStatData).getString();
/*     */ 
/*     */       
/*  79 */       if (str != null) mythicRecipeBlueprint.addRequiredPermission(str);
/*     */     
/*     */     } 
/*  82 */     mythicRecipeBlueprint.deploy(MythicRecipeStation.WORKBENCH, paramRef);
/*     */ 
/*     */     
/*  85 */     if (bool) paramRef.setValue(null);
/*     */ 
/*     */     
/*  88 */     return mythicRecipeBlueprint;
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
/*     */   @Nullable
/*     */   public static ShapedRecipe shapedRecipeFromList(@NotNull String paramString, @NotNull ArrayList<String> paramArrayList, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 114 */     ArrayList<ShapedIngredient> arrayList = new ArrayList();
/* 115 */     boolean bool = false;
/* 116 */     byte b = 0;
/*     */ 
/*     */ 
/*     */     
/* 120 */     for (String str1 : paramArrayList) {
/*     */ 
/*     */ 
/*     */       
/* 124 */       String arrayOfString[], str2 = RMGRI_Shaped.updateRow(str1);
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
/* 140 */       if (str2.contains("|")) {
/*     */ 
/*     */         
/* 143 */         arrayOfString = str2.split("\\|");
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 149 */         arrayOfString = str2.split(" ");
/*     */       } 
/*     */ 
/*     */       
/* 153 */       if (arrayOfString.length != 3) throw new IllegalArgumentException("Invalid crafting table row $u" + str2 + "$b ($fNot exactly 3 ingredients wide$b).");
/*     */ 
/*     */       
/* 156 */       ProvidedUIFilter providedUIFilter1 = RecipeMakerGUI.readIngredientFrom(arrayOfString[0], paramFriendlyFeedbackProvider);
/* 157 */       ProvidedUIFilter providedUIFilter2 = RecipeMakerGUI.readIngredientFrom(arrayOfString[1], paramFriendlyFeedbackProvider);
/* 158 */       ProvidedUIFilter providedUIFilter3 = RecipeMakerGUI.readIngredientFrom(arrayOfString[2], paramFriendlyFeedbackProvider);
/* 159 */       if (!providedUIFilter1.isAir()) bool = true; 
/* 160 */       if (!providedUIFilter2.isAir()) bool = true; 
/* 161 */       if (!providedUIFilter3.isAir()) bool = true;
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
/* 175 */       ShapedIngredient shapedIngredient1 = new ShapedIngredient(providedUIFilter1, 0, -b);
/* 176 */       ShapedIngredient shapedIngredient2 = new ShapedIngredient(providedUIFilter2, 1, -b);
/* 177 */       ShapedIngredient shapedIngredient3 = new ShapedIngredient(providedUIFilter3, 2, -b);
/*     */ 
/*     */       
/* 180 */       arrayList.add(shapedIngredient1);
/* 181 */       arrayList.add(shapedIngredient2);
/* 182 */       arrayList.add(shapedIngredient3);
/*     */ 
/*     */       
/* 185 */       b++;
/*     */     } 
/* 187 */     if (!bool) return null;
/*     */ 
/*     */     
/* 190 */     return ShapedRecipe.unsharpen(new ShapedRecipe(paramString, arrayList));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\registry\RMGRR_Shaped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */