/*     */ package net.Indyuce.mmoitems.manager;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.crafting.ConditionalDisplay;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStation;
/*     */ import net.Indyuce.mmoitems.api.crafting.LoadedCraftingObject;
/*     */ import net.Indyuce.mmoitems.api.crafting.condition.Condition;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.Ingredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.IngredientType;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.PlayerIngredient;
/*     */ import net.Indyuce.mmoitems.api.crafting.trigger.Trigger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CraftingManager
/*     */   implements Reloadable
/*     */ {
/*  38 */   private final List<IngredientType> ingredients = new ArrayList<>();
/*  39 */   private final Map<String, LoadedCraftingObject<Condition>> conditions = new HashMap<>();
/*  40 */   private final Map<String, LoadedCraftingObject<Trigger>> triggers = new HashMap<>();
/*     */   
/*  42 */   private final Map<String, CraftingStation> stations = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftingManager() {
/*  47 */     registerCondition("level", net.Indyuce.mmoitems.api.crafting.condition.LevelCondition::new, new ConditionalDisplay("&a✔ Requires Level #level#", "&c✖ Requires Level #level#"));
/*  48 */     registerCondition("permission", net.Indyuce.mmoitems.api.crafting.condition.PermissionCondition::new, new ConditionalDisplay("&a✔ #display#", "&c✖ #display#"));
/*  49 */     registerCondition("placeholder", net.Indyuce.mmoitems.api.crafting.condition.PlaceholderCondition::new, new ConditionalDisplay("&a✔ #display#", "&c✖ #display#"));
/*  50 */     registerCondition("mana", net.Indyuce.mmoitems.api.crafting.condition.ManaCondition::new, new ConditionalDisplay("&a✔ Requires #mana# Mana", "&c✖ Requires #mana# Mana"));
/*  51 */     registerCondition("stamina", net.Indyuce.mmoitems.api.crafting.condition.StaminaCondition::new, new ConditionalDisplay("&a✔ Requires #stamina# Stamina", "&c✖ Requires #stamina# Stamina"));
/*  52 */     registerCondition("food", net.Indyuce.mmoitems.api.crafting.condition.FoodCondition::new, new ConditionalDisplay("&a✔ Requires #food# Food", "&c✖ Requires #food# Food"));
/*  53 */     registerCondition("class", net.Indyuce.mmoitems.api.crafting.condition.ClassCondition::new, new ConditionalDisplay("&a✔ Required Class: #class#", "&c✖ Required Class: #class#"));
/*     */ 
/*     */     
/*  56 */     registerTrigger("command", net.Indyuce.mmoitems.api.crafting.trigger.CommandTrigger::new);
/*  57 */     registerTrigger("message", net.Indyuce.mmoitems.api.crafting.trigger.MessageTrigger::new);
/*  58 */     registerTrigger("sound", net.Indyuce.mmoitems.api.crafting.trigger.SoundTrigger::new);
/*  59 */     registerTrigger("vanilla", net.Indyuce.mmoitems.api.crafting.trigger.VanillaTrigger::new);
/*  60 */     registerTrigger("mmoitem", net.Indyuce.mmoitems.api.crafting.trigger.MMOItemTrigger::new);
/*     */ 
/*     */     
/*  63 */     registerIngredient("vanilla", net.Indyuce.mmoitems.api.crafting.ingredient.VanillaIngredient::new, new ConditionalDisplay("&8✔ &7#amount# #item#", "&c✖ &7#amount# #item#"), paramNBTItem -> true, net.Indyuce.mmoitems.api.crafting.ingredient.inventory.VanillaPlayerIngredient::new);
/*  64 */     registerIngredient("mmoitem", net.Indyuce.mmoitems.api.crafting.ingredient.MMOItemIngredient::new, new ConditionalDisplay("&8✔ &7#amount# #level##item#", "&c✖ &7#amount# #level##item#"), NBTItem::hasType, net.Indyuce.mmoitems.api.crafting.ingredient.inventory.MMOItemPlayerIngredient::new);
/*     */ 
/*     */     
/*  67 */     if (Bukkit.getPluginManager().getPlugin("MythicMobs") != null)
/*     */     {
/*     */ 
/*     */       
/*  71 */       registerTrigger("mmskill", net.Indyuce.mmoitems.comp.mythicmobs.crafting.MythicMobsSkillTrigger::new);
/*     */     }
/*     */   }
/*     */   
/*     */   public void reload() {
/*  76 */     this.stations.clear();
/*     */     
/*  78 */     ConfigFile configFile = new ConfigFile("/language", "crafting-stations");
/*     */     
/*  80 */     for (LoadedCraftingObject<Condition> loadedCraftingObject : getConditions()) {
/*  81 */       String str = "condition." + loadedCraftingObject.getId();
/*  82 */       if (!configFile.getConfig().contains(str)) {
/*  83 */         configFile.getConfig().createSection(str);
/*  84 */         loadedCraftingObject.getDisplay().setup(configFile.getConfig().getConfigurationSection(str));
/*     */       } 
/*     */       
/*  87 */       loadedCraftingObject.setDisplay(new ConditionalDisplay(configFile.getConfig().getConfigurationSection(str)));
/*     */     } 
/*     */     
/*  90 */     for (IngredientType ingredientType : getIngredients()) {
/*  91 */       String str = "ingredient." + ingredientType.getId();
/*  92 */       if (!configFile.getConfig().contains(str)) {
/*  93 */         configFile.getConfig().createSection(str);
/*  94 */         ingredientType.getDisplay().setup(configFile.getConfig().getConfigurationSection(str));
/*     */       } 
/*     */       
/*  97 */       ingredientType.setDisplay(new ConditionalDisplay(configFile.getConfig().getConfigurationSection(str)));
/*     */     } 
/*     */     
/* 100 */     configFile.save();
/*     */     
/* 102 */     for (File file : (new File(MMOItems.plugin.getDataFolder() + "/crafting-stations")).listFiles()) {
/*     */       try {
/* 104 */         CraftingStation craftingStation = new CraftingStation(file.getName().substring(0, file.getName().length() - 4), (FileConfiguration)YamlConfiguration.loadConfiguration(file));
/* 105 */         this.stations.put(craftingStation.getId(), craftingStation);
/* 106 */       } catch (IllegalArgumentException|NullPointerException illegalArgumentException) {
/* 107 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not load station '" + file.getName() + "': " + illegalArgumentException.getMessage());
/*     */       } 
/*     */     } 
/* 110 */     for (CraftingStation craftingStation : this.stations.values()) {
/*     */       try {
/* 112 */         craftingStation.postLoad();
/* 113 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 114 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not post-load station '" + craftingStation
/* 115 */             .getId() + "': " + illegalArgumentException.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public int countRecipes() {
/* 120 */     int i = 0;
/* 121 */     for (CraftingStation craftingStation : this.stations.values())
/* 122 */       i += craftingStation.getRecipes().size(); 
/* 123 */     return i;
/*     */   }
/*     */   
/*     */   public boolean hasStation(String paramString) {
/* 127 */     return this.stations.containsKey(paramString);
/*     */   }
/*     */   
/*     */   public Collection<CraftingStation> getStations() {
/* 131 */     return this.stations.values();
/*     */   }
/*     */   
/*     */   public CraftingStation getStation(String paramString) {
/* 135 */     return this.stations.get(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Ingredient getIngredient(MMOLineConfig paramMMOLineConfig) {
/* 144 */     String str = paramMMOLineConfig.getKey();
/*     */     
/* 146 */     for (IngredientType ingredientType : this.ingredients) {
/* 147 */       if (ingredientType.getId().equals(str))
/* 148 */         return (Ingredient)ingredientType.load(paramMMOLineConfig); 
/*     */     } 
/* 150 */     throw new IllegalArgumentException("Could not match ingredient");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Condition getCondition(MMOLineConfig paramMMOLineConfig) {
/* 161 */     return (Condition)getConditionInfo(paramMMOLineConfig.getKey()).load(paramMMOLineConfig);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public LoadedCraftingObject<Condition> getConditionInfo(String paramString) {
/* 166 */     return Objects.<LoadedCraftingObject<Condition>>requireNonNull(this.conditions.get(paramString), "Could not match condition");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Trigger getTrigger(MMOLineConfig paramMMOLineConfig) {
/* 177 */     return (Trigger)getTriggerInfo(paramMMOLineConfig.getKey()).load(paramMMOLineConfig);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public LoadedCraftingObject<Trigger> getTriggerInfo(String paramString) {
/* 182 */     return Objects.<LoadedCraftingObject<Trigger>>requireNonNull(this.triggers.get(paramString), "Could not match trigger");
/*     */   }
/*     */   
/*     */   public List<IngredientType> getIngredients() {
/* 186 */     return this.ingredients;
/*     */   }
/*     */   
/*     */   public Collection<LoadedCraftingObject<Condition>> getConditions() {
/* 190 */     return this.conditions.values();
/*     */   }
/*     */   
/*     */   public Collection<LoadedCraftingObject<Trigger>> getTriggers() {
/* 194 */     return this.triggers.values();
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
/*     */   public void registerIngredient(String paramString, Function<MMOLineConfig, Ingredient> paramFunction, ConditionalDisplay paramConditionalDisplay, Predicate<NBTItem> paramPredicate, Function<NBTItem, PlayerIngredient> paramFunction1) {
/* 210 */     this.ingredients.add(0, new IngredientType(paramString, paramFunction, paramConditionalDisplay, paramPredicate, paramFunction1));
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
/*     */   public void registerCondition(String paramString, Function<MMOLineConfig, Condition> paramFunction, @NotNull ConditionalDisplay paramConditionalDisplay) {
/* 222 */     LoadedCraftingObject<Condition> loadedCraftingObject = new LoadedCraftingObject(paramString, paramFunction, Objects.<ConditionalDisplay>requireNonNull(paramConditionalDisplay, "Conditional display cannot be null"));
/* 223 */     this.conditions.put(loadedCraftingObject.getId(), loadedCraftingObject);
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
/*     */   public void registerTrigger(String paramString, Function<MMOLineConfig, Trigger> paramFunction) {
/* 235 */     LoadedCraftingObject<Trigger> loadedCraftingObject = new LoadedCraftingObject(paramString, paramFunction, null);
/* 236 */     this.triggers.put(loadedCraftingObject.getId(), loadedCraftingObject);
/*     */   }
/*     */   
/*     */   public Collection<CraftingStation> getAll() {
/* 240 */     return this.stations.values();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\CraftingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */