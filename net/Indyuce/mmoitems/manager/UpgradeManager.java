/*    */ package net.Indyuce.mmoitems.manager;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.Indyuce.mmoitems.api.ConfigFile;
/*    */ import net.Indyuce.mmoitems.api.UpgradeTemplate;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class UpgradeManager
/*    */   implements Reloadable
/*    */ {
/* 16 */   private final Map<String, UpgradeTemplate> templates = new HashMap<>();
/*    */   
/*    */   public UpgradeManager() {
/* 19 */     reload();
/*    */   }
/*    */   
/*    */   public void reload() {
/* 23 */     this.templates.clear();
/*    */     
/* 25 */     FileConfiguration fileConfiguration = (new ConfigFile("upgrade-templates")).getConfig();
/* 26 */     for (String str : fileConfiguration.getKeys(false))
/*    */     {
/*    */       
/* 29 */       registerTemplate(new UpgradeTemplate(fileConfiguration.getConfigurationSection(str)));
/*    */     }
/*    */   }
/*    */   
/*    */   public Collection<UpgradeTemplate> getAll() {
/* 34 */     return this.templates.values();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public UpgradeTemplate getTemplate(@NotNull String paramString) {
/* 42 */     return this.templates.get(paramString);
/*    */   }
/*    */   
/*    */   public boolean hasTemplate(String paramString) {
/* 46 */     return this.templates.containsKey(paramString);
/*    */   }
/*    */   
/*    */   public void registerTemplate(UpgradeTemplate paramUpgradeTemplate) {
/* 50 */     this.templates.put(paramUpgradeTemplate.getId(), paramUpgradeTemplate);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\UpgradeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */