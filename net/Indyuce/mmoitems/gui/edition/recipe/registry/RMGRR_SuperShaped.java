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
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RMG_SuperShaped;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.interpreter.RMGRI_SuperShaped;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class RMGRR_SuperShaped implements RecipeRegistry {
/*     */   @NotNull
/*     */   public String getRecipeConfigPath() {
/*  35 */     return "supershaped"; } @NotNull
/*  36 */   public String getRecipeTypeName() { return "Super Shaped"; }
/*     */    @NotNull
/*  38 */   final ItemStack displayListItem = RecipeMakerGUI.rename(new ItemStack(Material.NOTE_BLOCK), FFPMMOItems.get().getExampleFormat() + "Super Shaped Recipe"); @NotNull
/*  39 */   public ItemStack getDisplayListItem() { return this.displayListItem; }
/*     */   
/*     */   public void openForPlayer(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  42 */     (new RMG_SuperShaped(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited(), paramString, this)).open(paramEditionInventory.getPreviousPage());
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
/*  56 */     ShapedRecipe shapedRecipe1 = superShapedRecipeFromList(namespacedKey.getKey(), new ArrayList<>(configurationSection.getStringList("input")), paramFriendlyFeedbackProvider);
/*  57 */     if (shapedRecipe1 == null) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Shaped recipe containing only AIR, $fignored$b.", new String[0]));
/*     */ 
/*     */     
/*  60 */     ShapedRecipe shapedRecipe2 = superShapedRecipeFromList(namespacedKey.getKey(), new ArrayList<>(configurationSection.getStringList("output")), paramFriendlyFeedbackProvider);
/*  61 */     int i = configurationSection.getInt("amount", 1);
/*     */ 
/*     */     
/*  64 */     ShapedRecipe shapedRecipe3 = ShapedRecipe.single(namespacedKey.getKey(), new ProvidedUIFilter[] { new ProvidedUIFilter((UIFilter)MMOItemUIFilter.get(), paramMMOItemTemplate.getType().getId(), paramMMOItemTemplate.getId(), Math.max(i, 1)) });
/*  65 */     MRORecipe mRORecipe = new MRORecipe(shapedRecipe3, shapedRecipe2);
/*     */ 
/*     */     
/*  68 */     MythicRecipeBlueprint mythicRecipeBlueprint = new MythicRecipeBlueprint((MythicRecipe)shapedRecipe1, (MythicRecipeOutput)mRORecipe, namespacedKey);
/*     */ 
/*     */     
/*  71 */     RandomStatData randomStatData = (RandomStatData)paramMMOItemTemplate.getBaseItemData().get(ItemStats.CRAFT_PERMISSION);
/*  72 */     if (randomStatData instanceof StringData) {
/*     */ 
/*     */       
/*  75 */       String str = ((StringData)randomStatData).getString();
/*     */ 
/*     */       
/*  78 */       if (str != null) mythicRecipeBlueprint.addRequiredPermission(str);
/*     */     
/*     */     } 
/*  81 */     mythicRecipeBlueprint.deploy(MythicRecipeStation.WORKBENCH, null);
/*     */ 
/*     */     
/*  84 */     paramRef.setValue(null);
/*     */ 
/*     */     
/*  87 */     return mythicRecipeBlueprint;
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
/*     */   @Nullable
/*     */   public static ShapedRecipe superShapedRecipeFromList(@NotNull String paramString, @NotNull ArrayList<String> paramArrayList, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/* 116 */     ArrayList<ShapedIngredient> arrayList = new ArrayList();
/* 117 */     boolean bool = false;
/* 118 */     byte b = 0;
/*     */ 
/*     */ 
/*     */     
/* 122 */     for (String str1 : paramArrayList) {
/*     */ 
/*     */ 
/*     */       
/* 126 */       String arrayOfString[], str2 = RMGRI_SuperShaped.updateRow(str1);
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
/* 142 */       if (str2.contains("|")) {
/*     */ 
/*     */         
/* 145 */         arrayOfString = str2.split("\\|");
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 151 */         arrayOfString = str2.split(" ");
/*     */       } 
/*     */ 
/*     */       
/* 155 */       if (arrayOfString.length != 5) throw new IllegalArgumentException("Invalid super crafting table row $u" + str2 + "$b ($fNot exactly 5 ingredients wide$b).");
/*     */ 
/*     */       
/* 158 */       ProvidedUIFilter providedUIFilter1 = RecipeMakerGUI.readIngredientFrom(arrayOfString[0], paramFriendlyFeedbackProvider);
/* 159 */       ProvidedUIFilter providedUIFilter2 = RecipeMakerGUI.readIngredientFrom(arrayOfString[1], paramFriendlyFeedbackProvider);
/* 160 */       ProvidedUIFilter providedUIFilter3 = RecipeMakerGUI.readIngredientFrom(arrayOfString[2], paramFriendlyFeedbackProvider);
/* 161 */       ProvidedUIFilter providedUIFilter4 = RecipeMakerGUI.readIngredientFrom(arrayOfString[3], paramFriendlyFeedbackProvider);
/* 162 */       ProvidedUIFilter providedUIFilter5 = RecipeMakerGUI.readIngredientFrom(arrayOfString[4], paramFriendlyFeedbackProvider);
/* 163 */       if (!providedUIFilter1.isAir()) bool = true; 
/* 164 */       if (!providedUIFilter2.isAir()) bool = true; 
/* 165 */       if (!providedUIFilter3.isAir()) bool = true; 
/* 166 */       if (!providedUIFilter4.isAir()) bool = true; 
/* 167 */       if (!providedUIFilter5.isAir()) bool = true;
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
/* 181 */       ShapedIngredient shapedIngredient1 = new ShapedIngredient(providedUIFilter1, 0, -b);
/* 182 */       ShapedIngredient shapedIngredient2 = new ShapedIngredient(providedUIFilter2, 1, -b);
/* 183 */       ShapedIngredient shapedIngredient3 = new ShapedIngredient(providedUIFilter3, 2, -b);
/* 184 */       ShapedIngredient shapedIngredient4 = new ShapedIngredient(providedUIFilter4, 3, -b);
/* 185 */       ShapedIngredient shapedIngredient5 = new ShapedIngredient(providedUIFilter5, 4, -b);
/*     */ 
/*     */       
/* 188 */       arrayList.add(shapedIngredient1);
/* 189 */       arrayList.add(shapedIngredient2);
/* 190 */       arrayList.add(shapedIngredient3);
/* 191 */       arrayList.add(shapedIngredient4);
/* 192 */       arrayList.add(shapedIngredient5);
/*     */ 
/*     */       
/* 195 */       b++;
/*     */     } 
/* 197 */     if (!bool) return null;
/*     */ 
/*     */     
/* 200 */     return ShapedRecipe.unsharpen(new ShapedRecipe(paramString, arrayList));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\registry\RMGRR_SuperShaped.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */