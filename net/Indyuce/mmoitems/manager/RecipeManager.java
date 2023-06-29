/*     */ package net.Indyuce.mmoitems.manager;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.crafting.recipes.MythicRecipeBlueprint;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.util.Ref;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackMessage;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.recipe.workbench.ingredients.AirIngredient;
/*     */ import net.Indyuce.mmoitems.api.recipe.workbench.ingredients.MMOItemIngredient;
/*     */ import net.Indyuce.mmoitems.api.recipe.workbench.ingredients.VanillaIngredient;
/*     */ import net.Indyuce.mmoitems.api.recipe.workbench.ingredients.WorkbenchIngredient;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.RecipeBrowserGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.RecipeRegistry;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.registry.burninglegacy.BurningRecipeInformation;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.CookingRecipe;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ import org.bukkit.inventory.RecipeChoice;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeManager
/*     */   implements Reloadable
/*     */ {
/*  51 */   private final ArrayList<Recipe> loadedLegacyRecipes = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   private final ArrayList<MythicRecipeBlueprint> customRecipes = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private final ArrayList<MythicRecipeBlueprint> booklessRecipes = new ArrayList<>();
/*     */   @NotNull
/*  65 */   private final ArrayList<NamespacedKey> blacklistedFromAutomaticDiscovery = new ArrayList<>();
/*     */   private boolean book;
/*     */   @Nullable
/*     */   private ArrayList<NamespacedKey> generatedNamespacedKeys;
/*     */   
/*     */   public void loadRecipes() {
/*  71 */     this.book = MMOItems.plugin.getConfig().getBoolean("recipes.use-recipe-book");
/*     */ 
/*     */     
/*  74 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*  75 */     friendlyFeedbackProvider.activatePrefix(true, "Custom Crafting");
/*     */ 
/*     */     
/*  78 */     for (Type type : MMOItems.plugin.getTypes().getAll()) {
/*     */ 
/*     */       
/*  81 */       FileConfiguration fileConfiguration = type.getConfigFile().getConfig();
/*     */ 
/*     */       
/*  84 */       for (MMOItemTemplate mMOItemTemplate : MMOItems.plugin.getTemplates().getTemplates(type)) {
/*     */ 
/*     */         
/*  87 */         if (fileConfiguration.contains(mMOItemTemplate.getId() + ".base.crafting")) {
/*     */ 
/*     */           
/*  90 */           ConfigurationSection configurationSection = RecipeMakerGUI.getSection((ConfigurationSection)fileConfiguration, mMOItemTemplate.getId() + ".base.crafting");
/*     */ 
/*     */           
/*  93 */           for (String str : RecipeBrowserGUI.getRegisteredRecipes()) {
/*     */ 
/*     */             
/*  96 */             if (configurationSection.contains(str)) {
/*     */ 
/*     */               
/*  99 */               RecipeRegistry recipeRegistry = RecipeBrowserGUI.getRegisteredRecipe(str);
/*     */ 
/*     */               
/* 102 */               ConfigurationSection configurationSection1 = RecipeMakerGUI.getSection(configurationSection, str);
/*     */ 
/*     */               
/* 105 */               for (String str1 : configurationSection1.getKeys(false)) {
/*     */ 
/*     */                 
/* 108 */                 NamespacedKey namespacedKey = getRecipeKey(mMOItemTemplate.getType(), mMOItemTemplate.getId(), str, str1);
/*     */ 
/*     */                 
/* 111 */                 Ref ref = new Ref(namespacedKey);
/*     */ 
/*     */                 
/* 114 */                 FriendlyFeedbackProvider friendlyFeedbackProvider1 = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/* 115 */                 friendlyFeedbackProvider1.activatePrefix(true, "Recipe of $u" + mMOItemTemplate.getType() + " " + mMOItemTemplate.getId());
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*     */                 try {
/* 121 */                   MythicRecipeBlueprint mythicRecipeBlueprint = recipeRegistry.sendToMythicLib(mMOItemTemplate, configurationSection1, str1, ref, friendlyFeedbackProvider1);
/* 122 */                   ((ref.getValue() != null) ? this.customRecipes : this.booklessRecipes).add(mythicRecipeBlueprint);
/*     */                 
/*     */                 }
/* 125 */                 catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */                   
/* 128 */                   if (!illegalArgumentException.getMessage().isEmpty()) {
/*     */ 
/*     */                     
/* 131 */                     MMOItems.print(null, "Cannot register custom recipe '$u{2}$b' for $e{0} {1}$b;$f {3}", "Custom Crafting", new String[] { type.getId(), mMOItemTemplate.getId(), str1, illegalArgumentException.getMessage() });
/*     */ 
/*     */                     
/* 134 */                     friendlyFeedbackProvider1.sendTo(FriendlyFeedbackCategory.ERROR, MMOItems.getConsole());
/* 135 */                     friendlyFeedbackProvider1.sendTo(FriendlyFeedbackCategory.FAILURE, MMOItems.getConsole());
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 146 */     friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.ERROR, MMOItems.getConsole());
/* 147 */     friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.FAILURE, MMOItems.getConsole());
/*     */ 
/*     */     
/* 150 */     Bukkit.getScheduler().runTask((Plugin)MMOItems.plugin, () -> getBukkitRecipes().forEach(Bukkit::addRecipe));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerBurningRecipe(@NotNull BurningRecipeType paramBurningRecipeType, @NotNull MMOItem paramMMOItem, @NotNull BurningRecipeInformation paramBurningRecipeInformation, int paramInt, @NotNull NamespacedKey paramNamespacedKey, boolean paramBoolean) {
/* 156 */     ItemStack itemStack = paramMMOItem.newBuilder().build();
/* 157 */     itemStack.setAmount(paramInt);
/*     */ 
/*     */     
/* 160 */     CookingRecipe<?> cookingRecipe = paramBurningRecipeType.provideRecipe(paramNamespacedKey, itemStack, paramBurningRecipeInformation.getChoice().toBukkit(), paramBurningRecipeInformation.getExp(), paramBurningRecipeInformation.getBurnTime());
/*     */ 
/*     */     
/* 163 */     this.loadedLegacyRecipes.add(cookingRecipe);
/*     */     
/* 165 */     if (paramBoolean) {
/* 166 */       this.blacklistedFromAutomaticDiscovery.add(paramNamespacedKey);
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<Recipe> getBukkitRecipes() {
/* 172 */     return this.loadedLegacyRecipes;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<MythicRecipeBlueprint> getCustomRecipes() {
/* 177 */     return this.customRecipes;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<MythicRecipeBlueprint> getBooklessRecipes() {
/* 182 */     return this.booklessRecipes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<NamespacedKey> getNamespacedKeys() {
/* 189 */     if (this.generatedNamespacedKeys != null) {
/* 190 */       return this.generatedNamespacedKeys;
/*     */     }
/*     */     
/* 193 */     this.generatedNamespacedKeys = new ArrayList<>();
/* 194 */     this.customRecipes.forEach(paramMythicRecipeBlueprint -> this.generatedNamespacedKeys.add(paramMythicRecipeBlueprint.getNk()));
/* 195 */     this.loadedLegacyRecipes.forEach(paramRecipe -> this.generatedNamespacedKeys.add(((Keyed)paramRecipe).getKey()));
/*     */     
/* 197 */     return this.generatedNamespacedKeys;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getRecipeKey(@NotNull Type paramType, @NotNull String paramString1, @NotNull String paramString2, @NotNull String paramString3) {
/* 202 */     return new NamespacedKey((Plugin)MMOItems.plugin, paramString2 + "_" + paramType.getId() + "_" + paramString1 + "_" + paramString3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reload() {
/* 209 */     Bukkit.getScheduler().runTask((Plugin)MMOItems.plugin, () -> {
/*     */           for (NamespacedKey namespacedKey : getNamespacedKeys()) {
/*     */             if (namespacedKey == null) {
/*     */               continue;
/*     */             }
/*     */ 
/*     */             
/*     */             try {
/*     */               Bukkit.removeRecipe(namespacedKey);
/* 218 */             } catch (Throwable throwable) {
/*     */               MMOItems.print(null, "Could not register crafting book recipe for $r{0}$b:$f {1}", "MMOItems Custom Crafting", new String[] { namespacedKey.getKey(), throwable.getMessage() });
/*     */             } 
/*     */           } 
/*     */           this.loadedLegacyRecipes.clear();
/*     */           this.blacklistedFromAutomaticDiscovery.clear();
/*     */           this.customRecipes.forEach(());
/*     */           this.customRecipes.clear();
/*     */           for (MythicRecipeBlueprint mythicRecipeBlueprint : this.booklessRecipes) {
/*     */             mythicRecipeBlueprint.disable();
/*     */           }
/*     */           this.booklessRecipes.clear();
/*     */           this.generatedNamespacedKeys = null;
/*     */           loadRecipes();
/*     */           if (this.book) {
/*     */             for (Player player : Bukkit.getOnlinePlayers()) {
/*     */               refreshRecipeBook(player);
/*     */             }
/*     */           }
/*     */         });
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
/*     */   public void refreshRecipeBook(Player paramPlayer) {
/* 265 */     if (!this.book) {
/* 266 */       for (NamespacedKey namespacedKey : paramPlayer.getDiscoveredRecipes()) {
/* 267 */         if ("mmoitems".equals(namespacedKey.getNamespace())) {
/* 268 */           paramPlayer.undiscoverRecipe(namespacedKey);
/*     */         }
/*     */       } 
/*     */       return;
/*     */     } 
/* 273 */     if (MythicLib.plugin.getVersion().isStrictlyHigher(new int[] { 1, 16 })) {
/*     */ 
/*     */       
/* 276 */       for (NamespacedKey namespacedKey : paramPlayer.getDiscoveredRecipes()) {
/* 277 */         if ("mmoitems".equals(namespacedKey.getNamespace()) && !getNamespacedKeys().contains(namespacedKey)) {
/* 278 */           paramPlayer.undiscoverRecipe(namespacedKey);
/*     */         }
/*     */       } 
/* 281 */       for (NamespacedKey namespacedKey : getNamespacedKeys()) {
/* 282 */         if (namespacedKey == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 286 */         boolean bool = false;
/* 287 */         for (NamespacedKey namespacedKey1 : this.blacklistedFromAutomaticDiscovery) {
/*     */           
/* 289 */           if (namespacedKey.equals(namespacedKey1)) {
/* 290 */             bool = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 294 */         if (bool) {
/*     */           continue;
/*     */         }
/*     */         
/*     */         try {
/* 299 */           if (!paramPlayer.hasDiscoveredRecipe(namespacedKey)) {
/* 300 */             paramPlayer.discoverRecipe(namespacedKey);
/*     */           }
/* 302 */         } catch (Throwable throwable) {
/* 303 */           MMOItems.print(null, "Could not register crafting book recipe for $r{0}$b:$f {1}", "MMOItems Custom Crafting", new String[] { namespacedKey.getKey(), throwable.getMessage() });
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 312 */     for (NamespacedKey namespacedKey : getNamespacedKeys()) {
/* 313 */       if (namespacedKey == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 318 */       boolean bool = false;
/* 319 */       for (NamespacedKey namespacedKey1 : this.blacklistedFromAutomaticDiscovery) {
/*     */         
/* 321 */         if (namespacedKey.equals(namespacedKey1)) {
/* 322 */           bool = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 326 */       if (bool) {
/*     */         continue;
/*     */       }
/*     */       
/*     */       try {
/* 331 */         paramPlayer.discoverRecipe(namespacedKey);
/* 332 */       } catch (Throwable throwable) {
/* 333 */         MMOItems.print(null, "Could not register crafting book recipe for $r{0}$b:$f {1}", "MMOItems Custom Crafting", new String[] { namespacedKey.getKey(), throwable.getMessage() });
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static WorkbenchIngredient getWorkbenchIngredient(@NotNull String paramString) {
/* 342 */     ProvidedUIFilter providedUIFilter = ProvidedUIFilter.getFromString(RecipeMakerGUI.poofFromLegacy(paramString), null);
/*     */ 
/*     */     
/* 345 */     if (providedUIFilter == null) {
/* 346 */       return (WorkbenchIngredient)new AirIngredient();
/*     */     }
/*     */ 
/*     */     
/* 350 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*     */ 
/*     */     
/* 353 */     if (!providedUIFilter.isValid(friendlyFeedbackProvider))
/*     */     {
/*     */ 
/*     */       
/* 357 */       throw new IllegalArgumentException(SilentNumbers.collapseList(SilentNumbers.transcribeList(friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR), paramObject -> ((FriendlyFeedbackMessage)paramObject).forConsole((FriendlyFeedbackPalette)FFPMMOItems.get())), ". "));
/*     */     }
/*     */ 
/*     */     
/* 361 */     int i = providedUIFilter.getAmount(0);
/*     */ 
/*     */     
/* 364 */     if (providedUIFilter.getParent() instanceof net.Indyuce.mmoitems.api.crafting.MMOItemUIFilter) {
/*     */ 
/*     */       
/* 367 */       Type type = MMOItems.plugin.getTypes().getOrThrow(providedUIFilter.getArgument());
/*     */ 
/*     */       
/* 370 */       MMOItemTemplate mMOItemTemplate = MMOItems.plugin.getTemplates().getTemplateOrThrow(type, providedUIFilter.getData());
/*     */ 
/*     */       
/* 373 */       return (WorkbenchIngredient)new MMOItemIngredient(type, mMOItemTemplate.getId(), i);
/*     */     } 
/*     */     
/* 376 */     if (providedUIFilter.getParent() instanceof io.lumine.mythic.lib.api.crafting.uifilters.VanillaUIFilter)
/*     */     {
/* 378 */       return (WorkbenchIngredient)new VanillaIngredient(Material.valueOf(providedUIFilter.getArgument().toUpperCase().replace("-", "_").replace(" ", "_")), i);
/*     */     }
/*     */     
/* 381 */     throw new IllegalArgumentException("Unsupported ingredient, you may only specify vanilla or mmoitems.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum BurningRecipeType
/*     */   {
/* 391 */     FURNACE((String)org.bukkit.inventory.FurnaceRecipe::new),
/* 392 */     SMOKER((String)org.bukkit.inventory.SmokingRecipe::new),
/* 393 */     CAMPFIRE((String)org.bukkit.inventory.CampfireRecipe::new),
/* 394 */     BLAST((String)org.bukkit.inventory.BlastingRecipe::new);
/*     */     
/*     */     private final RecipeManager.RecipeProvider provider;
/*     */     
/*     */     BurningRecipeType(RecipeManager.RecipeProvider param1RecipeProvider) {
/* 399 */       this.provider = param1RecipeProvider;
/*     */     }
/*     */     
/*     */     public CookingRecipe<?> provideRecipe(NamespacedKey param1NamespacedKey, ItemStack param1ItemStack, RecipeChoice param1RecipeChoice, float param1Float, int param1Int) {
/* 403 */       return this.provider.provide(param1NamespacedKey, param1ItemStack, param1RecipeChoice, param1Float, param1Int);
/*     */     }
/*     */     
/*     */     public String getPath() {
/* 407 */       return name().toLowerCase();
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface RecipeProvider {
/*     */     CookingRecipe<?> provide(NamespacedKey param1NamespacedKey, ItemStack param1ItemStack, RecipeChoice param1RecipeChoice, float param1Float, int param1Int);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\RecipeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */