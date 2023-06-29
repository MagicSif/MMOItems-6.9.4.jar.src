/*     */ package net.Indyuce.mmoitems.api.item.template;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.manager.TemplateManager;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TemplateModifier
/*     */ {
/*     */   private final String id;
/*     */   private final double chance;
/*     */   private final double weight;
/*     */   private final Map<ItemStat, RandomStatData> data;
/*     */   private final NameModifier nameModifier;
/*  29 */   private static final Random random = new Random();
/*     */   
/*     */   public TemplateModifier(ConfigurationSection paramConfigurationSection) {
/*  32 */     this(null, paramConfigurationSection);
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
/*     */   public TemplateModifier(TemplateManager paramTemplateManager, ConfigurationSection paramConfigurationSection) {
/*  46 */     Validate.notNull(paramConfigurationSection, "Could not read config");
/*  47 */     this.id = paramConfigurationSection.getName().toLowerCase().replace("_", "-");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     if (!paramConfigurationSection.contains("stats")) {
/*  54 */       Validate.notNull(paramTemplateManager, "Cannot create a private modifier outside an item template");
/*     */       
/*  56 */       Validate.isTrue(paramTemplateManager.hasModifier(this.id), "Could not find public modifier with ID '" + this.id + "'");
/*  57 */       TemplateModifier templateModifier = paramTemplateManager.getModifier(this.id);
/*     */       
/*  59 */       this.chance = Math.max(Math.min(paramConfigurationSection.getDouble("chance", templateModifier.chance), 1.0D), 0.0D);
/*  60 */       this.weight = paramConfigurationSection.getDouble("weight", templateModifier.weight);
/*  61 */       this.nameModifier = templateModifier.nameModifier;
/*  62 */       this.data = templateModifier.data;
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     this.data = new HashMap<>();
/*  67 */     this.chance = Math.max(Math.min(paramConfigurationSection.getDouble("chance", 1.0D), 1.0D), 0.0D);
/*  68 */     this.weight = paramConfigurationSection.getDouble("weight");
/*     */     
/*  70 */     Validate.isTrue((this.chance > 0.0D), "Chance must be greater than 0 otherwise useless");
/*  71 */     this
/*  72 */       .nameModifier = paramConfigurationSection.contains("suffix") ? new NameModifier(NameModifier.ModifierType.SUFFIX, paramConfigurationSection.get("suffix")) : (paramConfigurationSection.contains("prefix") ? new NameModifier(NameModifier.ModifierType.PREFIX, paramConfigurationSection.get("prefix")) : null);
/*     */     
/*  74 */     Validate.notNull(paramConfigurationSection.getConfigurationSection("stats"), "Could not find base item data");
/*  75 */     for (String str : paramConfigurationSection.getConfigurationSection("stats").getKeys(false)) {
/*     */       try {
/*  77 */         String str1 = str.toUpperCase().replace("-", "_");
/*  78 */         Validate.isTrue(MMOItems.plugin.getStats().has(str1), "Could not find stat with ID '" + str1 + "'");
/*     */         
/*  80 */         ItemStat itemStat = MMOItems.plugin.getStats().get(str1);
/*  81 */         this.data.put(itemStat, itemStat.whenInitialized(paramConfigurationSection.get("stats." + str)));
/*  82 */       } catch (IllegalArgumentException illegalArgumentException) {
/*     */         
/*  84 */         if (!illegalArgumentException.getMessage().isEmpty())
/*  85 */           MMOItems.plugin.getLogger().log(Level.INFO, "An error occurred while trying to load item gen modifier " + this.id + ": " + illegalArgumentException
/*  86 */               .getMessage()); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public String getId() {
/*  91 */     return this.id;
/*     */   }
/*     */   
/*     */   public double getWeight() {
/*  95 */     return this.weight;
/*     */   }
/*     */   
/*     */   public Map<ItemStat, RandomStatData> getItemData() {
/*  99 */     return this.data;
/*     */   }
/*     */   
/*     */   public NameModifier getNameModifier() {
/* 103 */     return this.nameModifier;
/*     */   }
/*     */   
/*     */   public boolean hasNameModifier() {
/* 107 */     return (this.nameModifier != null);
/*     */   }
/*     */   
/*     */   public boolean rollChance() {
/* 111 */     return (random.nextDouble() < this.chance);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\template\TemplateModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */