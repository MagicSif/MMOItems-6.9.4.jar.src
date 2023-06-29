/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.registry;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.crafting.ingredients.MythicRecipeIngredient;
/*     */ import io.lumine.mythic.lib.api.crafting.outputs.MRORecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.outputs.MythicRecipeOutput;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipeBlueprint;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipeStation;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.ShapedRecipe;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.ShapelessRecipe;
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
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RMG_Shapeless;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class RMGRR_Shapeless implements RecipeRegistry {
/*     */   @NotNull
/*     */   public String getRecipeTypeName() {
/*  34 */     return "Shapeless"; } @NotNull
/*  35 */   public String getRecipeConfigPath() { return "shapeless"; }
/*     */    @NotNull
/*  37 */   final ItemStack displayListItem = RecipeMakerGUI.rename(new ItemStack(Material.OAK_LOG), FFPMMOItems.get().getExampleFormat() + "Shapeless Recipe"); @NotNull
/*  38 */   public ItemStack getDisplayListItem() { return this.displayListItem; }
/*     */   
/*     */   public void openForPlayer(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  41 */     (new RMG_Shapeless(paramEditionInventory.getPlayer(), paramEditionInventory.getEdited(), paramString, this)).open(paramEditionInventory.getPreviousPage());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MythicRecipeBlueprint sendToMythicLib(@NotNull MMOItemTemplate paramMMOItemTemplate, @NotNull ConfigurationSection paramConfigurationSection, @NotNull String paramString, @NotNull Ref<NamespacedKey> paramRef, @NotNull FriendlyFeedbackProvider paramFriendlyFeedbackProvider) {
/*  49 */     RecipeMakerGUI.moveInput(paramConfigurationSection, paramString);
/*     */ 
/*     */     
/*  52 */     ConfigurationSection configurationSection = RecipeMakerGUI.getSection(paramConfigurationSection, paramString);
/*  53 */     NamespacedKey namespacedKey = (NamespacedKey)paramRef.getValue();
/*  54 */     if (namespacedKey == null) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Illegal (Null) Namespace", new String[0]));
/*     */ 
/*     */     
/*  57 */     ArrayList<MythicRecipeIngredient> arrayList = new ArrayList();
/*  58 */     ArrayList arrayList1 = new ArrayList(configurationSection.getStringList("input"));
/*     */ 
/*     */     
/*  61 */     boolean bool = false;
/*  62 */     for (String str : arrayList1) {
/*     */ 
/*     */       
/*  65 */       if (str == null || "AIR".equals(str)) {
/*     */         continue;
/*     */       }
/*  68 */       ProvidedUIFilter providedUIFilter = RecipeMakerGUI.readIngredientFrom(str, paramFriendlyFeedbackProvider);
/*     */ 
/*     */       
/*  71 */       if (providedUIFilter.isAir()) {
/*     */         continue;
/*     */       }
/*  74 */       bool = true;
/*  75 */       arrayList.add(new MythicRecipeIngredient(providedUIFilter));
/*     */     } 
/*  77 */     if (!bool) throw new IllegalArgumentException(FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Shapeless recipe containing only AIR, $fignored$b.", new String[0])); 
/*  78 */     ShapelessRecipe shapelessRecipe = new ShapelessRecipe(namespacedKey.getKey(), arrayList);
/*     */ 
/*     */ 
/*     */     
/*  82 */     ShapedRecipe shapedRecipe1 = RMGRR_Shaped.shapedRecipeFromList(namespacedKey.getKey(), new ArrayList<>(configurationSection.getStringList("output")), paramFriendlyFeedbackProvider);
/*  83 */     int i = configurationSection.getInt("amount", 1);
/*  84 */     boolean bool1 = configurationSection.getBoolean("hidden", false);
/*     */ 
/*     */     
/*  87 */     ShapedRecipe shapedRecipe2 = ShapedRecipe.single(namespacedKey.getKey(), new ProvidedUIFilter[] { new ProvidedUIFilter((UIFilter)MMOItemUIFilter.get(), paramMMOItemTemplate.getType().getId(), paramMMOItemTemplate.getId(), Math.max(i, 1)) });
/*  88 */     MRORecipe mRORecipe = new MRORecipe(shapedRecipe2, shapedRecipe1);
/*     */ 
/*     */     
/*  91 */     MythicRecipeBlueprint mythicRecipeBlueprint = new MythicRecipeBlueprint((MythicRecipe)shapelessRecipe, (MythicRecipeOutput)mRORecipe, namespacedKey);
/*     */ 
/*     */     
/*  94 */     RandomStatData randomStatData = (RandomStatData)paramMMOItemTemplate.getBaseItemData().get(ItemStats.CRAFT_PERMISSION);
/*  95 */     if (randomStatData instanceof StringData) {
/*     */ 
/*     */       
/*  98 */       String str = ((StringData)randomStatData).getString();
/*     */ 
/*     */       
/* 101 */       if (str != null) mythicRecipeBlueprint.addRequiredPermission(str);
/*     */     
/*     */     } 
/* 104 */     mythicRecipeBlueprint.deploy(MythicRecipeStation.WORKBENCH, paramRef);
/*     */ 
/*     */     
/* 107 */     if (bool1) paramRef.setValue(null);
/*     */ 
/*     */     
/* 110 */     return mythicRecipeBlueprint;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\registry\RMGRR_Shapeless.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */