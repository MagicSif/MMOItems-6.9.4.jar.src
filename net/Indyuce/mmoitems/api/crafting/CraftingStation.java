/*     */ package net.Indyuce.mmoitems.api.crafting;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.PostLoadObject;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.IngredientInventory;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CheckedRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.CraftingRecipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.Recipe;
/*     */ import net.Indyuce.mmoitems.api.crafting.recipe.UpgradingRecipe;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CraftingStation
/*     */   extends PostLoadObject
/*     */ {
/*     */   private final String id;
/*     */   private final String name;
/*     */   private final Layout layout;
/*     */   private final Sound sound;
/*     */   private final StationItemOptions itemOptions;
/*     */   private final int maxQueueSize;
/*  37 */   private final Map<String, Recipe> recipes = new LinkedHashMap<>();
/*     */   
/*     */   private CraftingStation parent;
/*     */   
/*     */   public CraftingStation(String paramString, FileConfiguration paramFileConfiguration) {
/*  42 */     super((ConfigurationSection)paramFileConfiguration);
/*     */     
/*  44 */     this.id = paramString.toLowerCase().replace("_", "-").replace(" ", "-");
/*  45 */     this.name = paramFileConfiguration.getString("name", "Unnamed");
/*  46 */     this.layout = MMOItems.plugin.getLayouts().getLayout(paramFileConfiguration.getString("layout", "default"));
/*  47 */     this.sound = Sound.valueOf(paramFileConfiguration.getString("sound", "ENTITY_EXPERIENCE_ORB_PICKUP").toUpperCase());
/*     */     
/*  49 */     for (String str : paramFileConfiguration.getConfigurationSection("recipes").getKeys(false)) {
/*     */       try {
/*  51 */         registerRecipe(loadRecipe(paramFileConfiguration.getConfigurationSection("recipes." + str)));
/*  52 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  53 */         MMOItems.plugin.getLogger().log(Level.INFO, "An issue occurred registering recipe '" + str + "' from crafting station '" + paramString + "': " + illegalArgumentException
/*  54 */             .getMessage());
/*     */       } 
/*     */     } 
/*  57 */     this.itemOptions = new StationItemOptions(paramFileConfiguration.getConfigurationSection("items"));
/*  58 */     this.maxQueueSize = Math.max(1, Math.min(paramFileConfiguration.getInt("max-queue-size"), 64));
/*     */   }
/*     */   
/*     */   public CraftingStation(String paramString1, String paramString2, Layout paramLayout, Sound paramSound, StationItemOptions paramStationItemOptions, int paramInt, CraftingStation paramCraftingStation) {
/*  62 */     super(null);
/*     */     
/*  64 */     Validate.notNull(paramString1, "Crafting station ID must not be null");
/*  65 */     Validate.notNull(paramString2, "Crafting station name must not be null");
/*  66 */     Validate.notNull(paramSound, "Crafting station sound must not be null");
/*     */     
/*  68 */     this.id = paramString1.toLowerCase().replace("_", "-").replace(" ", "-");
/*  69 */     this.name = paramString2;
/*  70 */     this.layout = paramLayout;
/*  71 */     this.sound = paramSound;
/*  72 */     this.itemOptions = paramStationItemOptions;
/*  73 */     this.maxQueueSize = paramInt;
/*  74 */     this.parent = paramCraftingStation;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  78 */     return this.id;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getName() {
/*  83 */     return this.name;
/*     */   }
/*     */   
/*     */   public Layout getLayout() {
/*  87 */     return this.layout;
/*     */   }
/*     */   
/*     */   public Sound getSound() {
/*  91 */     return this.sound;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CraftingStation getParent() {
/*  96 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<Recipe> getRecipes() {
/* 104 */     if (this.parent == null) {
/* 105 */       return this.recipes.values();
/*     */     }
/*     */     
/* 108 */     ArrayList<Recipe> arrayList = new ArrayList(this.recipes.values());
/* 109 */     CraftingStation craftingStation = this.parent;
/* 110 */     while (craftingStation != null) {
/* 111 */       arrayList.addAll(craftingStation.recipes.values());
/* 112 */       craftingStation = craftingStation.parent;
/*     */     } 
/*     */     
/* 115 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasRecipe(String paramString) {
/* 123 */     return (this.recipes.containsKey(paramString) || (this.parent != null && this.parent.hasRecipe(paramString)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Recipe getRecipe(String paramString) {
/* 131 */     Recipe recipe = this.recipes.get(paramString);
/* 132 */     return (recipe == null && this.parent != null) ? this.parent.getRecipe(paramString) : recipe;
/*     */   }
/*     */   
/*     */   public int getMaxQueueSize() {
/* 136 */     return this.maxQueueSize;
/*     */   }
/*     */   
/*     */   public List<CheckedRecipe> getAvailableRecipes(PlayerData paramPlayerData, IngredientInventory paramIngredientInventory) {
/* 140 */     ArrayList<CheckedRecipe> arrayList = new ArrayList();
/*     */     
/* 142 */     for (Recipe recipe : getRecipes()) {
/* 143 */       CheckedRecipe checkedRecipe = recipe.evaluateRecipe(paramPlayerData, paramIngredientInventory);
/* 144 */       if ((checkedRecipe.areConditionsMet() || !checkedRecipe.getRecipe().hasOption(Recipe.RecipeOption.HIDE_WHEN_LOCKED)) && (checkedRecipe
/* 145 */         .allIngredientsHad() || !checkedRecipe.getRecipe().hasOption(Recipe.RecipeOption.HIDE_WHEN_NO_INGREDIENTS))) {
/* 146 */         arrayList.add(checkedRecipe);
/*     */       }
/*     */     } 
/* 149 */     return arrayList;
/*     */   }
/*     */   
/*     */   public StationItemOptions getItemOptions() {
/* 153 */     return this.itemOptions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerRecipe(Recipe paramRecipe) {
/* 164 */     this.recipes.put(paramRecipe.getId(), paramRecipe);
/*     */   }
/*     */   
/*     */   public int getMaxPage() {
/* 168 */     int i = getRecipes().size();
/* 169 */     return Math.max(1, (int)Math.ceil(i / getLayout().getRecipeSlots().size()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void whenPostLoaded(ConfigurationSection paramConfigurationSection) {
/* 174 */     if (paramConfigurationSection.contains("parent")) {
/* 175 */       String str = paramConfigurationSection.getString("parent").toLowerCase().replace(" ", "-").replace("_", "-");
/* 176 */       Validate.isTrue(!str.equals(this.id), "Station cannot use itself as parent");
/* 177 */       Validate.isTrue(MMOItems.plugin.getCrafting().hasStation(str), "Could not find parent station with ID '" + str + "'");
/* 178 */       this.parent = MMOItems.plugin.getCrafting().getStation(str);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Recipe loadRecipe(ConfigurationSection paramConfigurationSection) {
/* 188 */     return paramConfigurationSection.contains("output") ? (Recipe)new CraftingRecipe(paramConfigurationSection) : (Recipe)new UpgradingRecipe(paramConfigurationSection);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\CraftingStation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */