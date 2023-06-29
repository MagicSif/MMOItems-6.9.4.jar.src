/*     */ package net.Indyuce.mmoitems.manager;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.PlayerConsumable;
/*     */ import net.Indyuce.mmoitems.stat.type.StringListStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*     */ import net.Indyuce.mmoitems.util.ElementStatType;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class StatManager {
/*  24 */   private final Map<String, ItemStat<?, ?>> stats = new LinkedHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   private final List<DoubleStat> numeric = new ArrayList<>();
/*  32 */   private final List<ItemRestriction> itemRestriction = new ArrayList<>();
/*  33 */   private final List<ConsumableItemInteraction> consumableActions = new ArrayList<>();
/*  34 */   private final List<PlayerConsumable> playerConsumables = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load() {
/*  41 */     for (Field field : ItemStats.class.getFields()) {
/*     */       try {
/*  43 */         if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.get(null) instanceof ItemStat)
/*  44 */           register((ItemStat<?, ?>)field.get(null)); 
/*  45 */       } catch (IllegalArgumentException|IllegalAccessException illegalArgumentException) {
/*  46 */         MMOItems.plugin.getLogger().log(Level.WARNING, String.format("Couldn't register stat called '%s'", new Object[] { field.getName() }), illegalArgumentException.getMessage());
/*     */       } 
/*     */     } 
/*     */     
/*  50 */     loadCustom();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void reload(boolean paramBoolean) {
/*  61 */     if (paramBoolean) this.numeric.removeIf(paramDoubleStat -> paramDoubleStat instanceof FictiveNumericStat);
/*     */ 
/*     */     
/*  64 */     loadElements();
/*     */ 
/*     */     
/*  67 */     loadCustom();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void loadCustom() {
/*  77 */     ConfigManager.DefaultFile.CUSTOM_STATS.checkFile();
/*  78 */     ConfigFile configFile = new ConfigFile("custom-stats");
/*  79 */     ConfigurationSection configurationSection = configFile.getConfig().getConfigurationSection("custom-stats");
/*  80 */     Validate.notNull(configurationSection, "Custom stats section is null");
/*  81 */     Objects.requireNonNull(configurationSection); Objects.requireNonNull(configurationSection); configurationSection.getKeys(true).stream().filter(configurationSection::isConfigurationSection).map(configurationSection::getConfigurationSection).filter(Objects::nonNull).forEach(this::registerCustomStat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void loadElements() {
/*  91 */     for (ElementStatType elementStatType : ElementStatType.values()) {
/*  92 */       for (Element element : MythicLib.plugin.getElements().getAll())
/*  93 */         this.numeric.add(new FictiveNumericStat(element, elementStatType)); 
/*     */     } 
/*     */   }
/*     */   @NotNull
/*     */   public Collection<ItemStat<?, ?>> getAll() {
/*  98 */     return this.stats.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<DoubleStat> getNumericStats() {
/* 109 */     return this.numeric;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<ItemRestriction> getItemRestrictionStats() {
/* 118 */     return this.itemRestriction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<ConsumableItemInteraction> getConsumableActions() {
/* 127 */     return this.consumableActions;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<PlayerConsumable> getPlayerConsumables() {
/* 136 */     return this.playerConsumables;
/*     */   }
/*     */   
/*     */   public boolean has(String paramString) {
/* 140 */     return this.stats.containsKey(paramString);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ItemStat<?, ?> get(String paramString) {
/* 145 */     ItemStat<?, ?> itemStat = this.stats.getOrDefault(paramString, null);
/* 146 */     if (itemStat == null) {
/* 147 */       itemStat = this.numeric.stream().filter(paramDoubleStat -> paramDoubleStat.getId().equals(paramString)).findFirst().orElse(null);
/*     */     }
/* 149 */     return itemStat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void register(String paramString, ItemStat<?, ?> paramItemStat) {
/* 159 */     register(paramItemStat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(@NotNull ItemStat<?, ?> paramItemStat) {
/* 170 */     if (!paramItemStat.isEnabled())
/*     */       return; 
/* 172 */     this.stats.put(paramItemStat.getId(), paramItemStat);
/*     */     
/* 174 */     if (paramItemStat instanceof DoubleStat && !(paramItemStat instanceof net.Indyuce.mmoitems.stat.type.GemStoneStat) && paramItemStat.isCompatible(Type.GEM_STONE))
/* 175 */       this.numeric.add((DoubleStat)paramItemStat); 
/* 176 */     if (paramItemStat instanceof ItemRestriction) this.itemRestriction.add((ItemRestriction)paramItemStat); 
/* 177 */     if (paramItemStat instanceof ConsumableItemInteraction) this.consumableActions.add((ConsumableItemInteraction)paramItemStat); 
/* 178 */     if (paramItemStat instanceof PlayerConsumable) this.playerConsumables.add((PlayerConsumable)paramItemStat);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     if (MMOItems.plugin.getTypes() != null) {
/* 188 */       Objects.requireNonNull(paramItemStat); MMOItems.plugin.getTypes().getAll().stream().filter(paramItemStat::isCompatible).forEach(paramType -> paramType.getAvailableStats().add(paramItemStat));
/*     */     }  } private void registerCustomStat(@NotNull ConfigurationSection paramConfigurationSection) { Class<DoubleStat> clazz3; Class<BooleanStat> clazz2;
/*     */     Class<StringStat> clazz1;
/*     */     Class<StringListStat> clazz;
/* 192 */     String str1 = paramConfigurationSection.getString("name");
/* 193 */     String str2 = paramConfigurationSection.getString("type");
/*     */     
/* 195 */     Validate.notNull(paramConfigurationSection, "Cannot register a custom stat from a null section");
/* 196 */     Validate.notNull(str1, "Cannot register a custom stat without a name");
/* 197 */     Validate.notNull(str2, "Cannot register a custom stat without a type");
/*     */ 
/*     */     
/* 200 */     switch (str2.toLowerCase()) {
/*     */       case "double":
/* 202 */         clazz3 = DoubleStat.class;
/*     */         break;
/*     */       case "boolean":
/* 205 */         clazz2 = BooleanStat.class;
/*     */         break;
/*     */       case "text":
/* 208 */         clazz1 = StringStat.class;
/*     */         break;
/*     */       case "text-list":
/* 211 */         clazz = StringListStat.class;
/*     */         break;
/*     */       default:
/* 214 */         throw new RuntimeException("Cannot register a custom stat of type " + str2);
/*     */     } 
/*     */     
/* 217 */     String str3 = String.format("custom_%s", new Object[] { str1.replace(" ", "_") }).toUpperCase();
/*     */ 
/*     */     
/* 220 */     String[] arrayOfString = new String[0];
/* 221 */     if (paramConfigurationSection.isList("lore")) { arrayOfString = (String[])paramConfigurationSection.getStringList("lore").toArray((Object[])new String[0]); }
/* 222 */     else if (paramConfigurationSection.isString("lore")) { arrayOfString = new String[] { paramConfigurationSection.getString("lore") }; }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 227 */       ItemStat<?, ?> itemStat = clazz.getConstructor(new Class[] { String.class, Material.class, String.class, String[].class, String[].class, Material[].class }).newInstance(new Object[] { str3, Material.PAPER, str1, arrayOfString, { "!miscellaneous", "!block", "all" }, new Material[0] });
/* 228 */       register(itemStat);
/* 229 */     } catch (InstantiationException|IllegalAccessException|java.lang.reflect.InvocationTargetException|NoSuchMethodException instantiationException) {
/*     */       
/* 231 */       throw new RuntimeException("Unable to create a custom stat of type " + str2, instantiationException);
/*     */     }  }
/*     */ 
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\StatManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */