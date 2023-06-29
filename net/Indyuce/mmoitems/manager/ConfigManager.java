/*     */ package net.Indyuce.mmoitems.manager;
/*     */ 
/*     */ import io.lumine.mythic.lib.UtilityMethods;
/*     */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.ReforgeOptions;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.util.ConfigItem;
/*     */ import net.Indyuce.mmoitems.api.item.util.ConfigItems;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.GemUpgradeScaling;
/*     */ import net.Indyuce.mmoitems.stat.LuteAttackEffectStat;
/*     */ import net.Indyuce.mmoitems.stat.StaffSpiritStat;
/*     */ import net.Indyuce.mmoitems.util.LanguageFile;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class ConfigManager
/*     */   implements Reloadable {
/*     */   private ConfigFile loreFormat;
/*     */   private ConfigFile stats;
/*     */   private ConfigFile dynLore;
/*  41 */   private final Map<TriggerType, String> triggerTypeNames = new HashMap<>();
/*  42 */   private final Map<PotionEffectType, String> potionNames = new HashMap<>();
/*     */   public boolean replaceMushroomDrops;
/*     */   public boolean worldGenEnabled;
/*     */   public boolean upgradeRequirementsCheck;
/*     */   public boolean keepSoulboundOnDeath;
/*     */   public boolean rerollOnItemUpdate;
/*     */   public boolean opStatsEnabled;
/*     */   public boolean disableRemovedItems;
/*  50 */   public final List<String> opStats = new ArrayList<>(); public String abilitySplitter; public double soulboundBaseDamage; public double soulboundPerLvlDamage; public double levelSpread; public NumericStatFormula defaultItemCapacity; public ReforgeOptions revisionOptions; public ReforgeOptions gemRevisionOptions; public ReforgeOptions phatLootsOptions;
/*     */   
/*     */   public ConfigManager() {
/*  53 */     mkdir("layouts");
/*  54 */     mkdir("item");
/*  55 */     mkdir("language");
/*  56 */     mkdir("language/lore-formats");
/*  57 */     mkdir("modifiers");
/*     */     
/*  59 */     File file = new File(MMOItems.plugin.getDataFolder() + "/crafting-stations");
/*  60 */     if (!file.exists()) {
/*  61 */       if (file.mkdir())
/*     */       { try {
/*  63 */           JarFile jarFile = new JarFile(MMOItems.plugin.getJarFile());
/*  64 */           for (Enumeration<JarEntry> enumeration = jarFile.entries(); enumeration.hasMoreElements(); ) {
/*  65 */             String str = ((JarEntry)enumeration.nextElement()).getName();
/*  66 */             if (str.startsWith("default/crafting-stations/") && str.length() > "default/crafting-stations/".length())
/*  67 */               Files.copy(MMOItems.plugin.getResource(str), (new File(MMOItems.plugin
/*  68 */                     .getDataFolder() + "/crafting-stations", str.split("/")[2])).toPath(), new java.nio.file.CopyOption[0]); 
/*     */           } 
/*  70 */           jarFile.close();
/*  71 */         } catch (IOException iOException) {
/*  72 */           MMOItems.plugin.getLogger().log(Level.WARNING, "Could not load default crafting stations.");
/*     */         }  }
/*  74 */       else { MMOItems.plugin.getLogger().log(Level.WARNING, "Could not create directory!"); }
/*     */     
/*     */     }
/*     */     
/*  78 */     for (DefaultFile defaultFile : DefaultFile.values()) {
/*  79 */       if (defaultFile.isAutomatic()) {
/*  80 */         defaultFile.checkFile();
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  86 */     MMOItems.plugin.getTypes().getAll().forEach(paramType -> paramType.getConfigFile().setup());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     reload();
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
/*     */   private void loadTranslations() {
/* 106 */     ConfigFile configFile = new ConfigFile("/language", "items");
/* 107 */     for (ConfigItem configItem : ConfigItems.values) {
/* 108 */       if (!configFile.getConfig().contains(configItem.getId())) {
/* 109 */         configFile.getConfig().createSection(configItem.getId());
/* 110 */         configItem.setup(configFile.getConfig().getConfigurationSection(configItem.getId()));
/*     */       } 
/* 112 */       configItem.update((ConfigurationSection)configFile.getConfig());
/*     */     } 
/* 114 */     configFile.save();
/*     */ 
/*     */     
/* 117 */     LanguageFile languageFile1 = new LanguageFile("messages");
/* 118 */     for (Message message : Message.values()) {
/* 119 */       String str = message.name().toLowerCase().replace("_", "-");
/* 120 */       if (!languageFile1.getConfig().contains(str)) {
/* 121 */         languageFile1.getConfig().set(str, message.getDefault());
/*     */       }
/* 123 */       message.setCurrent(languageFile1.getConfig().getString(str));
/*     */     } 
/* 125 */     languageFile1.save();
/*     */ 
/*     */     
/* 128 */     LanguageFile languageFile2 = new LanguageFile("potion-effects");
/* 129 */     for (PotionEffectType potionEffectType : PotionEffectType.values()) {
/* 130 */       this.potionNames.put(potionEffectType, languageFile2.computeTranslation(potionEffectType.getName().toLowerCase().replace("_", "-"), () -> UtilityMethods.caseOnWords(paramPotionEffectType.getName().toLowerCase().replace("_", " "))));
/*     */     }
/* 132 */     languageFile2.save();
/*     */ 
/*     */     
/* 135 */     LanguageFile languageFile3 = new LanguageFile("attack-effects");
/* 136 */     for (StaffSpiritStat.StaffSpirit staffSpirit : StaffSpiritStat.StaffSpirit.values()) {
/* 137 */       staffSpirit.setName(languageFile3.computeTranslation("staff-spirit." + staffSpirit.name().toLowerCase().replace("_", "-"), () -> UtilityMethods.caseOnWords(paramStaffSpirit.name().toLowerCase().replace("_", " "))));
/*     */     }
/*     */ 
/*     */     
/* 141 */     for (LuteAttackEffectStat.LuteAttackEffect luteAttackEffect : LuteAttackEffectStat.LuteAttackEffect.values()) {
/* 142 */       luteAttackEffect.setName(languageFile3.computeTranslation("lute-attack." + luteAttackEffect.name().toLowerCase().replace("_", "-"), () -> UtilityMethods.caseOnWords(paramLuteAttackEffect.name().toLowerCase().replace("_", " "))));
/*     */     }
/* 144 */     languageFile3.save();
/*     */ 
/*     */     
/* 147 */     this.triggerTypeNames.clear();
/* 148 */     FileConfiguration fileConfiguration = (new ConfigFile("/language", "abilities")).getConfig();
/* 149 */     for (TriggerType triggerType : TriggerType.values())
/* 150 */       this.triggerTypeNames.put(triggerType, fileConfiguration.getString("cast-mode." + triggerType.getLowerCaseId(), triggerType.getName())); 
/*     */   }
/*     */   
/*     */   public void reload() {
/* 154 */     MMOItems.plugin.reloadConfig();
/*     */     
/* 156 */     this.loreFormat = new ConfigFile("/language", "lore-format");
/* 157 */     this.stats = new ConfigFile("/language", "stats");
/* 158 */     this.dynLore = new ConfigFile("/language", "dynamic-lore");
/*     */     
/* 160 */     loadTranslations();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     this.replaceMushroomDrops = MMOItems.plugin.getConfig().getBoolean("custom-blocks.replace-mushroom-drops");
/* 167 */     this.worldGenEnabled = MMOItems.plugin.getConfig().getBoolean("custom-blocks.enable-world-gen");
/*     */     
/* 169 */     this.abilitySplitter = getStatFormat("ability-splitter");
/* 170 */     this.soulboundBaseDamage = MMOItems.plugin.getConfig().getDouble("soulbound.damage.base");
/* 171 */     this.soulboundPerLvlDamage = MMOItems.plugin.getConfig().getDouble("soulbound.damage.per-lvl");
/* 172 */     this.upgradeRequirementsCheck = MMOItems.plugin.getConfig().getBoolean("item-upgrade-requirements-check");
/* 173 */     GemUpgradeScaling.defaultValue = MMOItems.plugin.getConfig().getString("gem-upgrade-default", GemUpgradeScaling.SUBSEQUENT.getId());
/* 174 */     this.keepSoulboundOnDeath = MMOItems.plugin.getConfig().getBoolean("soulbound.keep-on-death");
/* 175 */     this.rerollOnItemUpdate = MMOItems.plugin.getConfig().getBoolean("item-revision.reroll-when-updated");
/* 176 */     this.levelSpread = MMOItems.plugin.getConfig().getDouble("item-level-spread");
/* 177 */     this.disableRemovedItems = MMOItems.plugin.getConfig().getBoolean("disable-removed-items");
/*     */     
/* 179 */     this.opStatsEnabled = MMOItems.plugin.getConfig().getBoolean("op-item-stats.enabled");
/* 180 */     this.opStats.clear();
/* 181 */     for (String str : MMOItems.plugin.getConfig().getStringList("op-item-stats.stats")) {
/* 182 */       this.opStats.add(UtilityMethods.enumName(str));
/*     */     }
/* 184 */     ConfigurationSection configurationSection1 = MMOItems.plugin.getConfig().getConfigurationSection("item-revision.keep-data");
/* 185 */     ConfigurationSection configurationSection2 = MMOItems.plugin.getConfig().getConfigurationSection("item-revision.phat-loots");
/* 186 */     ConfigurationSection configurationSection3 = MMOItems.plugin.getConfig().getConfigurationSection("item-revision.keep-gem-data");
/* 187 */     ReforgeOptions.dropRestoredGems = MMOItems.plugin.getConfig().getBoolean("item-revision.drop-extra-gems", true);
/* 188 */     this.revisionOptions = (configurationSection1 != null) ? new ReforgeOptions(configurationSection1) : new ReforgeOptions(new boolean[] { false, false, false, false, false, false, false, true });
/* 189 */     this.gemRevisionOptions = (configurationSection3 != null) ? new ReforgeOptions(configurationSection3) : new ReforgeOptions(new boolean[] { false, false, false, false, false, false, false, true });
/* 190 */     this.phatLootsOptions = (configurationSection2 != null) ? new ReforgeOptions(configurationSection2) : new ReforgeOptions(new boolean[] { false, false, false, false, false, false, false, true });
/*     */     
/* 192 */     List list = MMOItems.plugin.getConfig().getStringList("item-revision.disable-phat-loot");
/* 193 */     for (String str : list) {
/* 194 */       this.phatLootsOptions.addToBlacklist(str);
/*     */     }
/*     */     try {
/* 197 */       this.defaultItemCapacity = new NumericStatFormula(MMOItems.plugin.getConfig().getConfigurationSection("default-item-capacity"));
/* 198 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 199 */       this.defaultItemCapacity = new NumericStatFormula(5.0D, 0.05D, 0.1D, 0.3D);
/* 200 */       MMOItems.plugin.getLogger().log(Level.INFO, "An error occurred while trying to load default capacity formula for the item generator, using default: " + illegalArgumentException
/*     */           
/* 202 */           .getMessage());
/*     */     } 
/*     */     
/* 205 */     ConfigFile configFile = new ConfigFile("/language", "items");
/* 206 */     for (ConfigItem configItem : ConfigItems.values) {
/* 207 */       configItem.update(configFile.getConfig().getConfigurationSection(configItem.getId()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlacklisted(Material paramMaterial) {
/* 215 */     return MMOItems.plugin.getConfig().getStringList("block-blacklist").contains(paramMaterial.name());
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getStatFormat(String paramString) {
/* 220 */     String str = this.stats.getConfig().getString(paramString);
/* 221 */     return (str == null) ? ("<TranslationNotFound:" + paramString + ">") : str;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getMessage(String paramString) {
/* 226 */     return Message.valueOf(UtilityMethods.enumName(paramString)).getUpdated();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getCastingModeName(@NotNull TriggerType paramTriggerType) {
/* 231 */     return Objects.<String>requireNonNull(this.triggerTypeNames.get(paramTriggerType), "Trigger type name for '" + paramTriggerType.name() + "' not found");
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getModifierName(String paramString) {
/* 236 */     return UtilityMethods.caseOnWords(paramString.toLowerCase().replace("-", " ").replace("_", " "));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public List<String> getDefaultLoreFormat() {
/* 241 */     return this.loreFormat.getConfig().getStringList("lore-format");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getPotionEffectName(PotionEffectType paramPotionEffectType) {
/* 246 */     return Objects.<String>requireNonNull(this.potionNames.get(paramPotionEffectType), "Potion effect name for '" + paramPotionEffectType.getName() + "' not found");
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getLuteAttackEffectName(LuteAttackEffectStat.LuteAttackEffect paramLuteAttackEffect) {
/* 251 */     return paramLuteAttackEffect.getName();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public String getStaffSpiritName(StaffSpiritStat.StaffSpirit paramStaffSpirit) {
/* 256 */     return paramStaffSpirit.getName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public String getDynLoreFormat(String paramString) {
/* 264 */     return this.dynLore.getConfig().getString("format." + paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mkdir(String paramString) {
/* 274 */     File file = new File(MMOItems.plugin.getDataFolder() + "/" + paramString);
/* 275 */     if (!file.exists() && 
/* 276 */       !file.mkdir()) {
/* 277 */       MMOItems.plugin.getLogger().log(Level.WARNING, "Could not create directory!");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum DefaultFile
/*     */   {
/* 288 */     ITEM_TIERS("", "item-tiers"),
/* 289 */     ITEM_TYPES("", "item-types", true),
/* 290 */     DROPS("", "drops"),
/* 291 */     ITEM_SETS("", "item-sets"),
/* 292 */     GEN_TEMPLATES("", "gen-templates"),
/* 293 */     UPGRADE_TEMPLATES("", "upgrade-templates"),
/* 294 */     EXAMPLE_MODIFIERS("modifiers", "example-modifiers"),
/* 295 */     CUSTOM_STATS("", "custom-stats"),
/*     */ 
/*     */     
/* 298 */     ABILITIES("language", "abilities"),
/* 299 */     ATTACK_EFFECTS("language", "attack-effects"),
/* 300 */     CRAFTING_STATIONS("language", "crafting-stations"),
/* 301 */     ITEMS("language", "items"),
/* 302 */     LORE_FORMAT("language", "lore-format"),
/* 303 */     MESSAGES("language", "messages"),
/* 304 */     POTION_EFFECTS("language", "potion-effects"),
/* 305 */     STATS("language", "stats"),
/*     */ 
/*     */     
/* 308 */     DEFAULT_LAYOUT("layouts", "default"),
/* 309 */     EXPANDED_LAYOUT("layouts", "expanded"),
/*     */ 
/*     */     
/* 312 */     ARMOR,
/* 313 */     AXE,
/* 314 */     BLOCK,
/* 315 */     BOW,
/* 316 */     CATALYST,
/* 317 */     CONSUMABLE,
/* 318 */     CROSSBOW,
/* 319 */     DAGGER,
/* 320 */     GAUNTLET,
/* 321 */     GEM_STONE,
/* 322 */     GREATAXE,
/* 323 */     GREATHAMMER,
/* 324 */     GREATSTAFF,
/* 325 */     GREATSWORD,
/* 326 */     HALBERD,
/* 327 */     HAMMER,
/* 328 */     KATANA,
/* 329 */     LANCE,
/* 330 */     LONG_SWORD,
/* 331 */     MATERIAL,
/* 332 */     MISCELLANEOUS,
/* 333 */     MUSKET,
/* 334 */     OFF_CATALYST,
/* 335 */     ORNAMENT,
/* 336 */     SHIELD,
/* 337 */     SPEAR,
/* 338 */     STAFF,
/* 339 */     SWORD,
/* 340 */     TALISMAN,
/* 341 */     THRUSTING_SWORD,
/* 342 */     TOME,
/* 343 */     TOOL,
/* 344 */     WAND,
/* 345 */     WHIP;
/*     */ 
/*     */     
/*     */     private final String folderPath;
/*     */     
/*     */     private final String fileName;
/*     */     
/*     */     private final boolean manual;
/*     */ 
/*     */     
/*     */     DefaultFile() {
/* 356 */       this.fileName = name().toLowerCase() + ".yml";
/* 357 */       this.folderPath = "item";
/* 358 */       this.manual = false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     DefaultFile(String param1String1, String param1String2, boolean param1Boolean) {
/* 366 */       this.folderPath = param1String1;
/* 367 */       this.fileName = param1String2 + ".yml";
/* 368 */       this.manual = param1Boolean;
/*     */     }
/*     */     
/*     */     public boolean isAutomatic() {
/* 372 */       return !this.manual;
/*     */     }
/*     */     
/*     */     public File getFile() {
/* 376 */       return new File(MMOItems.plugin.getDataFolder() + (this.folderPath.equals("") ? "" : ("/" + this.folderPath)), this.fileName);
/*     */     }
/*     */     
/*     */     public void checkFile() {
/* 380 */       File file = getFile();
/* 381 */       if (!file.exists())
/*     */         try {
/* 383 */           if (!(new ConfigManager.YamlConverter(file)).convert()) {
/* 384 */             Files.copy(MMOItems.plugin.getResource("default/" + (this.folderPath.isEmpty() ? "" : (this.folderPath + "/")) + this.fileName), file.getAbsoluteFile().toPath(), new java.nio.file.CopyOption[0]);
/*     */           }
/*     */         }
/* 387 */         catch (IOException iOException) {
/* 388 */           iOException.printStackTrace();
/*     */         }  
/*     */     }
/*     */   }
/*     */   
/*     */   public static class YamlConverter
/*     */   {
/*     */     private final File file;
/*     */     private final String fileName;
/*     */     
/*     */     public YamlConverter(File param1File) {
/* 399 */       this.file = param1File;
/* 400 */       this.fileName = param1File.getName();
/*     */     }
/*     */     
/*     */     public boolean convert() {
/* 404 */       if (!this.file.exists() && 
/* 405 */         this.fileName.equalsIgnoreCase("block.yml") && (new File(MMOItems.plugin.getDataFolder(), "custom-blocks.yml")).exists())
/*     */       {
/* 407 */         if (this.file.createNewFile()) {
/* 408 */           YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(new File(MMOItems.plugin.getDataFolder(), "custom-blocks.yml"));
/*     */           
/* 410 */           for (String str : yamlConfiguration.getKeys(false)) {
/* 411 */             ConfigurationSection configurationSection = yamlConfiguration.getConfigurationSection(str);
/* 412 */             configurationSection.set("material", "STONE");
/* 413 */             configurationSection.set("block-id", Integer.valueOf(Integer.parseInt(str)));
/*     */ 
/*     */             
/* 416 */             for (String str1 : configurationSection.getKeys(false)) {
/* 417 */               Object object = configurationSection.get(str1);
/* 418 */               if (str1.equalsIgnoreCase("display-name")) {
/*     */ 
/*     */                 
/* 421 */                 configurationSection.set("display-name", null);
/* 422 */                 configurationSection.set("name", object);
/*     */               } 
/*     */             } 
/*     */           } 
/* 426 */           yamlConfiguration.save(this.file);
/* 427 */           MMOItems.plugin.getLogger().log(Level.CONFIG, "Successfully converted custom-blocks.yml");
/* 428 */           return true;
/*     */         } 
/*     */       }
/* 431 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\ConfigManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */