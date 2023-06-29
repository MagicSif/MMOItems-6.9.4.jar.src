/*     */ package net.Indyuce.mmoitems.manager;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.item.template.TemplateModifier;
/*     */ import net.Indyuce.mmoitems.api.util.TemplateMap;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class TemplateManager implements Reloadable {
/*  29 */   private final TemplateMap<MMOItemTemplate> templates = new TemplateMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  35 */   private final Map<String, TemplateModifier> modifiers = new HashMap<>();
/*     */   
/*  37 */   private static final Random random = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTemplate(@Nullable Type paramType, @Nullable String paramString) {
/*  46 */     if (paramType == null || paramString == null) return false; 
/*  47 */     return this.templates.hasValue(paramType, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasTemplate(@Nullable NBTItem paramNBTItem) {
/*  56 */     if (paramNBTItem == null) return false; 
/*  57 */     return hasTemplate(Type.get(paramNBTItem.getType()), paramNBTItem.getString("MMOITEMS_ITEM_ID"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MMOItemTemplate getTemplate(@Nullable Type paramType, @Nullable String paramString) {
/*  69 */     if (paramType == null || paramString == null) return null; 
/*  70 */     return (MMOItemTemplate)this.templates.getValue(paramType, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MMOItemTemplate getTemplate(@Nullable NBTItem paramNBTItem) {
/*  81 */     if (paramNBTItem == null) return null; 
/*  82 */     return getTemplate(Type.get(paramNBTItem.getType()), paramNBTItem.getString("MMOITEMS_ITEM_ID"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MMOItemTemplate getTemplateOrThrow(@Nullable Type paramType, @Nullable String paramString) {
/*  93 */     Validate.isTrue((paramType != null && hasTemplate(paramType, paramString)), "Could not find a template with ID '" + paramString + "'");
/*  94 */     return (MMOItemTemplate)this.templates.getValue(paramType, paramString);
/*     */   }
/*     */   @NotNull
/*     */   public Collection<MMOItemTemplate> getTemplates(@NotNull Type paramType) {
/*  98 */     return this.templates.collectValues(paramType);
/*     */   } @NotNull
/*     */   public ArrayList<String> getTemplateNames(@NotNull Type paramType) {
/* 101 */     ArrayList<String> arrayList = new ArrayList();
/* 102 */     for (MMOItemTemplate mMOItemTemplate : this.templates.collectValues(paramType)) arrayList.add(mMOItemTemplate.getId()); 
/* 103 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerTemplate(@NotNull MMOItemTemplate paramMMOItemTemplate) {
/* 112 */     Validate.notNull(paramMMOItemTemplate, "MMOItem template cannot be null");
/*     */     
/* 114 */     this.templates.setValue(paramMMOItemTemplate.getType(), paramMMOItemTemplate.getId(), paramMMOItemTemplate);
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
/*     */   public void unregisterTemplate(@NotNull Type paramType, @NotNull String paramString) {
/* 126 */     this.templates.removeValue(paramType, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteTemplate(@NotNull Type paramType, @NotNull String paramString) {
/* 137 */     unregisterTemplate(paramType, paramString);
/*     */     
/* 139 */     ConfigFile configFile = paramType.getConfigFile();
/* 140 */     configFile.getConfig().set(paramString, null);
/* 141 */     configFile.save();
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
/*     */   public MMOItemTemplate requestTemplateUpdate(@NotNull Type paramType, @NotNull String paramString) {
/* 157 */     this.templates.removeValue(paramType, paramString);
/*     */     
/*     */     try {
/* 160 */       MMOItemTemplate mMOItemTemplate = new MMOItemTemplate(paramType, paramType.getConfigFile().getConfig().getConfigurationSection(paramString));
/* 161 */       mMOItemTemplate.postLoad();
/* 162 */       registerTemplate(mMOItemTemplate);
/* 163 */       return mMOItemTemplate;
/*     */     }
/* 165 */     catch (IllegalArgumentException illegalArgumentException) {
/* 166 */       MMOItems.plugin.getLogger().log(Level.INFO, "An error occurred while trying to reload item gen template '" + paramString + "': " + illegalArgumentException
/* 167 */           .getMessage());
/* 168 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<MMOItemTemplate> collectTemplates() {
/* 176 */     return this.templates.collectValues();
/*     */   } public boolean hasModifier(String paramString) {
/* 178 */     return this.modifiers.containsKey(paramString);
/*     */   } public TemplateModifier getModifier(String paramString) {
/* 180 */     return this.modifiers.get(paramString);
/*     */   } public Collection<TemplateModifier> getModifiers() {
/* 182 */     return this.modifiers.values();
/*     */   }
/*     */   
/*     */   public ItemTier rollTier() {
/* 186 */     double d = 0.0D;
/* 187 */     for (ItemTier itemTier : MMOItems.plugin.getTiers().getAll()) {
/* 188 */       if (d >= 1.0D || random.nextDouble() < itemTier.getGenerationChance() / (1.0D - d)) {
/* 189 */         return itemTier;
/*     */       }
/* 191 */       d += itemTier.getGenerationChance();
/*     */     } 
/*     */ 
/*     */     
/* 195 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int rollLevel(int paramInt) {
/* 206 */     double d1 = (MMOItems.plugin.getLanguage()).levelSpread;
/* 207 */     double d2 = random.nextGaussian() * d1 * 0.7D + paramInt;
/*     */ 
/*     */ 
/*     */     
/* 211 */     d2 = Math.max(Math.min(d2, paramInt + d1), Math.max(1.0D, paramInt - d1));
/*     */     
/* 213 */     return (int)d2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void preloadTemplates() {
/* 223 */     for (Type type : MMOItems.plugin.getTypes().getAll()) {
/* 224 */       FileConfiguration fileConfiguration = type.getConfigFile().getConfig();
/* 225 */       for (String str : fileConfiguration.getKeys(false)) {
/*     */         try {
/* 227 */           registerTemplate(new MMOItemTemplate(type, fileConfiguration.getConfigurationSection(str)));
/* 228 */         } catch (IllegalArgumentException illegalArgumentException) {
/* 229 */           MMOItems.plugin.getLogger().log(Level.INFO, "Could not preload item template '" + str + "': " + illegalArgumentException.getMessage());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void postloadTemplates() {
/* 239 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/* 240 */     friendlyFeedbackProvider.activatePrefix(true, "Item Templates");
/* 241 */     friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Loading template modifiers, please wait..", new String[0]);
/* 242 */     for (File file : (new File(MMOItems.plugin.getDataFolder() + "/modifiers")).listFiles()) {
/* 243 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
/* 244 */       friendlyFeedbackProvider.activatePrefix(true, "Item Templates §8($r" + file.getPath() + "§8)");
/* 245 */       for (String str : yamlConfiguration.getKeys(false)) {
/*     */         try {
/* 247 */           TemplateModifier templateModifier = new TemplateModifier(yamlConfiguration.getConfigurationSection(str));
/* 248 */           this.modifiers.put(templateModifier.getId(), templateModifier);
/* 249 */         } catch (IllegalArgumentException illegalArgumentException) {
/* 250 */           friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Could not load template modifier '" + str + "': " + illegalArgumentException.getMessage(), new String[0]);
/*     */         } 
/*     */       } 
/*     */     } 
/* 254 */     friendlyFeedbackProvider.activatePrefix(true, "Item Templates");
/* 255 */     friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Loading item templates, please wait...", new String[0]);
/* 256 */     this.templates.forEach(paramMMOItemTemplate -> {
/*     */           try {
/*     */             paramMMOItemTemplate.postLoad();
/* 259 */           } catch (IllegalArgumentException illegalArgumentException) {
/*     */             paramFriendlyFeedbackProvider.activatePrefix(true, "Item Templates §8($r" + paramMMOItemTemplate.getType().getId() + "§8)");
/*     */             
/*     */             paramFriendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Could not load item template '" + paramMMOItemTemplate.getId() + "': " + illegalArgumentException.getMessage(), new String[0]);
/*     */           } 
/*     */         });
/*     */     
/* 266 */     friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.INFORMATION, MMOItems.getConsole());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reload() {
/* 276 */     this.templates.clear();
/* 277 */     this.modifiers.clear();
/*     */     
/* 279 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/* 280 */     friendlyFeedbackProvider.activatePrefix(true, "Item Templates");
/* 281 */     friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Loading template modifiers, please wait..", new String[0]);
/*     */     
/* 283 */     for (File file : (new File(MMOItems.plugin.getDataFolder() + "/modifiers")).listFiles()) {
/* 284 */       YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
/* 285 */       friendlyFeedbackProvider.activatePrefix(true, "Item Templates §8($r" + file.getPath() + "§8)");
/* 286 */       for (String str : yamlConfiguration.getKeys(false)) {
/*     */         try {
/* 288 */           TemplateModifier templateModifier = new TemplateModifier(yamlConfiguration.getConfigurationSection(str));
/* 289 */           this.modifiers.put(templateModifier.getId(), templateModifier);
/* 290 */         } catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */           
/* 293 */           friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Could not load template modifier '" + str + "': " + illegalArgumentException.getMessage(), new String[0]);
/*     */         } 
/*     */       } 
/*     */     } 
/* 297 */     friendlyFeedbackProvider.activatePrefix(true, "Item Templates");
/* 298 */     friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Loading item templates, please wait...", new String[0]);
/* 299 */     for (Type type : MMOItems.plugin.getTypes().getAll()) {
/* 300 */       FileConfiguration fileConfiguration = type.getConfigFile().getConfig();
/* 301 */       friendlyFeedbackProvider.activatePrefix(true, "Item Templates §8($r" + type.getId() + "§8)");
/* 302 */       for (String str : fileConfiguration.getKeys(false)) {
/*     */         try {
/* 304 */           MMOItemTemplate mMOItemTemplate = new MMOItemTemplate(type, fileConfiguration.getConfigurationSection(str));
/* 305 */           mMOItemTemplate.postLoad();
/* 306 */           registerTemplate(mMOItemTemplate);
/*     */         }
/* 308 */         catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */           
/* 311 */           friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Could not load item template '" + str + "': " + illegalArgumentException.getMessage(), new String[0]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 316 */     friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.INFORMATION, MMOItems.getConsole());
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\TemplateManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */