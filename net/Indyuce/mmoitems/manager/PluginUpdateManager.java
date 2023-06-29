/*     */ package net.Indyuce.mmoitems.manager;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.skill.handler.SkillHandler;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.PluginUpdate;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ public class PluginUpdateManager {
/*  27 */   private final Map<Integer, PluginUpdate> updates = new HashMap<>();
/*     */ 
/*     */   
/*     */   public PluginUpdateManager() {
/*  31 */     register(new PluginUpdate(1, new String[] { "Applies a fix for skull textures values in 4.7.1.", "Texture values data storage changed in 4.7.1 due to the UUID change." }, paramCommandSender -> {
/*     */             for (Type type : MMOItems.plugin.getTypes().getAll()) {
/*     */               ConfigFile configFile = type.getConfigFile();
/*     */               
/*     */               for (String str : configFile.getConfig().getKeys(false)) {
/*     */                 ConfigurationSection configurationSection = configFile.getConfig().getConfigurationSection(str);
/*     */                 
/*     */                 if (configurationSection.contains("skull-texture") && configurationSection.get("skull-texture") instanceof String) {
/*     */                   configurationSection.set("skull-texture.value", configurationSection.getString("skull-texture"));
/*     */                   
/*     */                   configurationSection.set("skull-texture.uuid", UUID.randomUUID().toString());
/*     */                 } 
/*     */               } 
/*     */               
/*     */               configFile.save();
/*     */             } 
/*     */           }));
/*     */     
/*  49 */     register(new PluginUpdate(3, new String[] { "5.3.2: converts all your crafting station recipes to the newest config format.", "&cWarning, running this update will get rid of your # config file comments." }, paramCommandSender -> {
/*     */             for (File file : (new File(MMOItems.plugin.getDataFolder() + "/crafting-stations")).listFiles()) {
/*     */               YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file); if (yamlConfiguration.contains("recipes")) {
/*     */                 for (String str : yamlConfiguration.getConfigurationSection("recipes").getKeys(false)) {
/*     */                   try {
/*     */                     List list1 = yamlConfiguration.getStringList("recipes." + str + ".ingredients"); ArrayList<String> arrayList = new ArrayList(); for (String str1 : list1) {
/*     */                       String[] arrayOfString = str1.split(" "); if (arrayOfString[0].equals("mmoitem")) {
/*     */                         String str2 = "mmoitem{type=" + arrayOfString[1] + ",id=" + arrayOfString[2]; if (arrayOfString.length > 3)
/*     */                           str2 = str2 + ",amount=" + arrayOfString[3];  if (arrayOfString.length > 4)
/*     */                           str2 = str2 + ",display=\"" + arrayOfString[4].replace("_", " ") + "\""; 
/*     */                         arrayList.add(str2 + "}");
/*     */                         continue;
/*     */                       } 
/*     */                       if (arrayOfString[0].equals("vanilla")) {
/*     */                         String str2 = "vanilla{type=" + arrayOfString[1];
/*     */                         if (arrayOfString.length > 2 && !arrayOfString[2].equals("."))
/*     */                           str2 = str2 + ",name=\"" + arrayOfString[2] + "\""; 
/*     */                         if (arrayOfString.length > 3)
/*     */                           str2 = str2 + ",amount=" + arrayOfString[3]; 
/*     */                         if (arrayOfString.length > 4)
/*     */                           str2 = str2 + ",display=\"" + arrayOfString[4].replace("_", " ") + "\""; 
/*     */                         arrayList.add(str2 + "}");
/*     */                         continue;
/*     */                       } 
/*     */                       MMOItems.plugin.getLogger().log(Level.INFO, "Config Update 3: Could not match ingredient from '" + str1 + "' from recipe '" + str + "', added it anyway.");
/*     */                       arrayList.add(str1);
/*     */                     } 
/*     */                     yamlConfiguration.set("recipes." + str + ".ingredients", arrayList);
/*     */                     List list2 = yamlConfiguration.getStringList("recipes." + str + ".conditions");
/*     */                     arrayList = new ArrayList<>();
/*     */                     for (String str1 : list2) {
/*     */                       String[] arrayOfString = str1.split(" ");
/*     */                       if (arrayOfString[0].equalsIgnoreCase("class")) {
/*     */                         arrayList.add("class{list=\"" + str1.replace(arrayOfString[0] + " ", "").replace(" ", ",") + "\"}");
/*     */                         continue;
/*     */                       } 
/*     */                       if (arrayOfString[0].equalsIgnoreCase("perms")) {
/*     */                         arrayList.add("permission{list=\"" + str1.replace(arrayOfString[0] + " ", "").replace(" ", ",") + "\"}");
/*     */                         continue;
/*     */                       } 
/*     */                       if (arrayOfString[0].equalsIgnoreCase("food") || arrayOfString[0].equals("mana") || arrayOfString[0].equals("stamina")) {
/*     */                         arrayList.add(arrayOfString[0] + "{amount=" + arrayOfString[1] + "}");
/*     */                         continue;
/*     */                       } 
/*     */                       if (arrayOfString[0].equalsIgnoreCase("level")) {
/*     */                         arrayList.add("level{level=" + arrayOfString[1] + "}");
/*     */                         continue;
/*     */                       } 
/*     */                       if (arrayOfString[0].equalsIgnoreCase("profession")) {
/*     */                         arrayList.add("profession{profession=" + arrayOfString[1] + ",level=" + arrayOfString[2] + "}");
/*     */                         continue;
/*     */                       } 
/*     */                       if (arrayOfString[0].equalsIgnoreCase("exp")) {
/*     */                         arrayList.add("exp{profession=" + arrayOfString[1] + ",amount=" + arrayOfString[2] + "}");
/*     */                         continue;
/*     */                       } 
/*     */                       MMOItems.plugin.getLogger().log(Level.INFO, "Config Update 3: Could not match condition from '" + str1 + "' from recipe '" + str + "', added it anyway.");
/*     */                       arrayList.add(str1);
/*     */                     } 
/*     */                     yamlConfiguration.set("recipes." + str + ".conditions", arrayList);
/* 109 */                   } catch (Exception exception) {
/*     */                     MMOItems.plugin.getLogger().log(Level.INFO, "Config Update 3: Could not convert recipe with key '" + str + "': " + exception.getMessage());
/*     */                   } 
/*     */                 } 
/*     */                 
/*     */                 try {
/*     */                   yamlConfiguration.save(file);
/* 116 */                 } catch (IOException iOException) {
/*     */                   MMOItems.plugin.getLogger().log(Level.INFO, "Config Update 3: Could not save config '" + file.getName() + "': " + iOException.getMessage());
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           }));
/*     */ 
/*     */     
/* 124 */     register(new PluginUpdate(2, new String[] { "Enables the item updater for every item.", "&cNot recommended unless you know what you are doing.", "&e(No longer available)" }, paramCommandSender -> {
/*     */             paramCommandSender.sendMessage(ChatColor.RED + "This command is no longer available.");
/*     */ 
/*     */             
/*     */             paramCommandSender.sendMessage(ChatColor.RED + "Please refer to the Revision System on the wiki.");
/*     */           }));
/*     */     
/* 131 */     register(new PluginUpdate(5, new String[] { "Transition to trigger types in 6.6.3", "Only scans through item configs" }, paramCommandSender -> {
/*     */             for (Type type : MMOItems.plugin.getTypes().getAll()) {
/*     */               ConfigFile configFile = type.getConfigFile();
/*     */               for (String str : configFile.getConfig().getKeys(false)) {
/*     */                 ConfigurationSection configurationSection = configFile.getConfig().getConfigurationSection(str);
/*     */                 if (!configurationSection.contains("base.ability")) {
/*     */                   continue;
/*     */                 }
/*     */                 for (String str1 : configFile.getConfig().getConfigurationSection(str + ".base.ability").getKeys(false)) {
/*     */                   String str2 = configFile.getConfig().getString(str + ".base.ability." + str1 + ".mode");
/*     */                   if (str2 == null) {
/*     */                     return;
/*     */                   }
/*     */                   String str3 = str2.equalsIgnoreCase("ON_HIT") ? "ATTACK" : (str2.equalsIgnoreCase("WHEN_HIT") ? "DAMAGED" : str2);
/*     */                   configFile.getConfig().set(str + ".base.ability." + str1 + ".mode", str3);
/*     */                 } 
/*     */               } 
/*     */               configFile.save();
/*     */             } 
/*     */           }));
/* 151 */     register(new PluginUpdate(4, new String[] { "Transforms all your current MMOItems into item templates and fixes some stat formats which have been changed.", "&cIt is REALLY important to save a backup before using this config update!" }, paramCommandSender -> {
/*     */             for (Type type : MMOItems.plugin.getTypes().getAll()) {
/*     */               ConfigFile configFile = type.getConfigFile();
/*     */               
/*     */               for (String str : configFile.getConfig().getKeys(false)) {
/*     */                 if (configFile.getConfig().getConfigurationSection(str).contains("base")) {
/*     */                   continue;
/*     */                 }
/*     */                 
/*     */                 configFile.getConfig().createSection(str + ".base", configFile.getConfig().getConfigurationSection(str).getValues(false));
/*     */                 
/*     */                 for (String str1 : configFile.getConfig().getConfigurationSection(str).getKeys(false)) {
/*     */                   if (!str1.equals("base")) {
/*     */                     configFile.getConfig().set(str + "." + str1, null);
/*     */                   }
/*     */                 } 
/*     */                 
/*     */                 rename(configFile.getConfig().getConfigurationSection(str + ".base"), "regeneration", "health-regeneration");
/*     */                 
/*     */                 rename(configFile.getConfig().getConfigurationSection(str + ".base"), "element.light", "element.lightness");
/*     */                 
/*     */                 if (configFile.getConfig().getConfigurationSection(str + ".base").contains("consume-sound")) {
/*     */                   rename(configFile.getConfig().getConfigurationSection(str + ".base"), "consume-sound", "sounds.on-consume.sound");
/*     */                   
/*     */                   configFile.getConfig().set(str + ".base.sounds.on-consume.volume", Double.valueOf(1.0D));
/*     */                   
/*     */                   configFile.getConfig().set(str + ".base.sounds.on-consume.pitch", Double.valueOf(1.0D));
/*     */                 } 
/*     */                 
/*     */                 if (configFile.getConfig().getConfigurationSection(str + ".base").contains("effects")) {
/*     */                   for (String str1 : configFile.getConfig().getConfigurationSection(str + ".base.effects").getKeys(false)) {
/*     */                     String[] arrayOfString = configFile.getConfig().getString(str + ".base.effects." + str1).split(",");
/*     */                     
/*     */                     if (arrayOfString.length > 1) {
/*     */                       configFile.getConfig().set(str + ".base.new-effects." + str1 + ".duration", Double.valueOf(Double.parseDouble(arrayOfString[0])));
/*     */                       
/*     */                       configFile.getConfig().set(str + ".base.new-effects." + str1 + ".amplifier", Double.valueOf(Double.parseDouble(arrayOfString[1])));
/*     */                     } 
/*     */                   } 
/*     */                   
/*     */                   configFile.getConfig().set(str + ".base.effects", null);
/*     */                   
/*     */                   rename(configFile.getConfig().getConfigurationSection(str + ".base"), "new-effects", "effects");
/*     */                 } 
/*     */                 
/*     */                 if (configFile.getConfig().getConfigurationSection(str + ".base").contains("restore")) {
/*     */                   configFile.getConfig().set(str + ".base.restore-health", Double.valueOf(configFile.getConfig().getDouble(str + ".base.restore.health")));
/*     */                   
/*     */                   configFile.getConfig().set(str + ".base.restore-food", Double.valueOf(configFile.getConfig().getDouble(str + ".base.restore.food")));
/*     */                   
/*     */                   configFile.getConfig().set(str + ".base.restore-saturation", Double.valueOf(configFile.getConfig().getDouble(str + ".base.restore.saturation")));
/*     */                   
/*     */                   configFile.getConfig().set(str + ".base.restore", null);
/*     */                 } 
/*     */                 
/*     */                 for (String str1 : configFile.getConfig().getConfigurationSection(str + ".base").getKeys(false)) {
/*     */                   String str2 = configFile.getConfig().getString(str + ".base." + str1);
/*     */                   
/*     */                   if (str2 != null) {
/*     */                     try {
/*     */                       String[] arrayOfString = str2.split("=");
/*     */                       Validate.isTrue((arrayOfString.length == 2));
/*     */                       double d1 = Double.parseDouble(arrayOfString[0]);
/*     */                       double d2 = Double.parseDouble(arrayOfString[1]);
/*     */                       double d3 = (d1 + d2) / 2.0D;
/*     */                       double d4 = Math.max(Math.abs(d1), Math.abs(d2));
/*     */                       double d5 = (d4 - d3) / d4;
/*     */                       configFile.getConfig().set(str + ".base." + str1 + ".base", Double.valueOf(d3));
/*     */                       configFile.getConfig().set(str + ".base." + str1 + ".spread", Double.valueOf(d5 / 3.0D));
/*     */                       configFile.getConfig().set(str + ".base." + str1 + ".max-spread", Double.valueOf(d5));
/* 221 */                     } catch (Exception exception) {}
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/*     */               configFile.save();
/*     */             } 
/*     */           }));
/*     */     
/* 231 */     register(new PluginUpdate(6, new String[] { "MMOItems 6.7 introduced individual config files for skills. This update reads your previous language folder and applies it to the new individual config files.", "This can also be used to apply an old plugin translation" }, paramCommandSender -> {
/*     */             FileConfiguration fileConfiguration = (new ConfigFile("/language", "abilities")).getConfig();
/*     */             
/*     */             for (RegisteredSkill registeredSkill : MMOItems.plugin.getSkills().getAll()) {
/*     */               ConfigFile configFile = new ConfigFile("/skill", registeredSkill.getHandler().getLowerCaseId());
/*     */               
/*     */               FileConfiguration fileConfiguration1 = configFile.getConfig();
/*     */               
/*     */               fileConfiguration1.set("name", Objects.requireNonNullElse(fileConfiguration.getString("ability." + registeredSkill.getHandler().getLowerCaseId()), registeredSkill.getName()));
/*     */               
/*     */               for (String str : registeredSkill.getHandler().getModifiers()) {
/*     */                 fileConfiguration1.set("modifier." + str + ".name", Objects.requireNonNullElse(fileConfiguration.getString("modifier." + str), registeredSkill.getParameterName(str)));
/*     */               }
/*     */               
/*     */               configFile.save();
/*     */             } 
/*     */             
/*     */             paramCommandSender.sendMessage("Config updates successfully applied, reloading skills..");
/*     */             
/*     */             MMOItems.plugin.getSkills().initialize(true);
/*     */           }));
/* 252 */     register(new PluginUpdate(7, new String[] { "MI 6.7 introduced a 'timer' skill modifier for all skills.", "This update registers that modifier in every of your skills.", "Has to be ran after &6/mi update apply 6&7." }, paramCommandSender -> {
/*     */             for (SkillHandler skillHandler : MythicLib.plugin.getSkills().getHandlers()) {
/*     */               ConfigFile configFile = new ConfigFile("/skill", skillHandler.getLowerCaseId());
/*     */               configFile.getConfig().set("modifier.timer.name", "Timer");
/*     */               configFile.getConfig().set("modifier.timer.default-value", Double.valueOf(0.0D));
/*     */               configFile.save();
/*     */             } 
/*     */             paramCommandSender.sendMessage("Config updates successfully applied, reloading skills..");
/*     */             MMOItems.plugin.getSkills().initialize(true);
/*     */           }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(PluginUpdate paramPluginUpdate) {
/* 269 */     this.updates.put(Integer.valueOf(paramPluginUpdate.getId()), paramPluginUpdate);
/*     */   }
/*     */   
/*     */   public boolean has(int paramInt) {
/* 273 */     return this.updates.containsKey(Integer.valueOf(paramInt));
/*     */   }
/*     */   
/*     */   public PluginUpdate get(int paramInt) {
/* 277 */     return this.updates.get(Integer.valueOf(paramInt));
/*     */   }
/*     */   
/*     */   public Collection<PluginUpdate> getAll() {
/* 281 */     return this.updates.values();
/*     */   }
/*     */   
/*     */   private void rename(ConfigurationSection paramConfigurationSection, String paramString1, String paramString2) {
/* 285 */     if (paramConfigurationSection.contains(paramString1)) {
/* 286 */       Object object = paramConfigurationSection.get(paramString1);
/* 287 */       paramConfigurationSection.set(paramString1, null);
/* 288 */       paramConfigurationSection.set(paramString2, object);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\PluginUpdateManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */