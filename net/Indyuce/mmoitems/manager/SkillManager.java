/*     */ package net.Indyuce.mmoitems.manager;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.skill.handler.SkillHandler;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.file.Files;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*     */ import net.Indyuce.mmoitems.skill.ShulkerMissile;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SkillManager
/*     */ {
/*  27 */   private final Map<String, RegisteredSkill> skills = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public RegisteredSkill getSkill(String paramString) {
/*  35 */     return this.skills.get(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public RegisteredSkill getSkillOrThrow(String paramString) {
/*  44 */     return Objects.<RegisteredSkill>requireNonNull(this.skills.get(paramString), "Could not find skill with ID '" + paramString + "'");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerSkill(RegisteredSkill paramRegisteredSkill) {
/*  51 */     this.skills.put(((RegisteredSkill)Objects.<RegisteredSkill>requireNonNull(paramRegisteredSkill, "Skill cannot be null")).getHandler().getId(), paramRegisteredSkill);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSkill(String paramString) {
/*  59 */     return this.skills.containsKey(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Collection<RegisteredSkill> getAll() {
/*  68 */     return this.skills.values();
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
/*     */   public void initialize(boolean paramBoolean) {
/*  81 */     if (paramBoolean) {
/*  82 */       this.skills.clear();
/*     */     }
/*  84 */     MythicLib.plugin.getSkills().registerSkillHandler((SkillHandler)new ShulkerMissile());
/*     */     
/*  86 */     File file = new File(MMOItems.plugin.getDataFolder() + "/skill");
/*  87 */     if (!file.exists()) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/*  92 */         file.mkdir();
/*     */ 
/*     */         
/*  95 */         for (SkillHandler skillHandler : MythicLib.plugin.getSkills().getHandlers()) {
/*  96 */           InputStream inputStream = MMOItems.plugin.getResource("default/skill/" + skillHandler.getLowerCaseId() + ".yml");
/*  97 */           if (inputStream != null) {
/*  98 */             Files.copy(inputStream, (new File(MMOItems.plugin.getDataFolder() + "/skill/" + skillHandler.getLowerCaseId() + ".yml")).getAbsoluteFile().toPath(), new java.nio.file.CopyOption[0]);
/*     */           }
/*     */         }
/*     */       
/* 102 */       } catch (IOException iOException) {
/* 103 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not save default ability configs: " + iOException.getMessage());
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 108 */     for (SkillHandler skillHandler : MythicLib.plugin.getSkills().getHandlers()) {
/* 109 */       ConfigFile configFile = new ConfigFile("/skill", skillHandler.getLowerCaseId());
/* 110 */       if (!configFile.exists()) {
/* 111 */         configFile.getConfig().set("name", MMOUtils.caseOnWords(skillHandler.getId().replace("_", " ").replace("-", " ").toLowerCase()));
/* 112 */         for (String str : skillHandler.getModifiers()) {
/* 113 */           configFile.getConfig().set("modifier." + str + ".name", MMOUtils.caseOnWords(str.replace("-", " ").toLowerCase()));
/* 114 */           configFile.getConfig().set("modifier." + str + ".default-value", Integer.valueOf(0));
/*     */         } 
/* 116 */         configFile.save();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 122 */         this.skills.put(skillHandler.getId(), new RegisteredSkill(skillHandler, (ConfigurationSection)configFile.getConfig()));
/*     */       
/*     */       }
/* 125 */       catch (RuntimeException runtimeException) {
/* 126 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not load skill '" + skillHandler.getId() + "': " + runtimeException.getMessage());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\SkillManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */