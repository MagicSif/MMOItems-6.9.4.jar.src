/*    */ package net.Indyuce.mmoitems.api;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.ItemReference;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class ConfigFile {
/*    */   private final Plugin plugin;
/*    */   private final String path;
/*    */   private final String name;
/*    */   private final boolean exists;
/*    */   private final FileConfiguration config;
/*    */   
/*    */   public ConfigFile(String paramString) {
/* 21 */     this((Plugin)MMOItems.plugin, "", paramString);
/*    */   }
/*    */   
/*    */   public ConfigFile(Plugin paramPlugin, String paramString) {
/* 25 */     this(paramPlugin, "", paramString);
/*    */   }
/*    */   
/*    */   public ConfigFile(String paramString1, String paramString2) {
/* 29 */     this((Plugin)MMOItems.plugin, paramString1, paramString2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ConfigFile(Plugin paramPlugin, String paramString1, String paramString2) {
/* 40 */     this.plugin = paramPlugin;
/* 41 */     this.path = paramString1;
/* 42 */     this.name = paramString2;
/*    */     
/* 44 */     File file = new File(paramPlugin.getDataFolder() + paramString1, paramString2 + ".yml");
/* 45 */     this.exists = file.exists();
/* 46 */     this.config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public FileConfiguration getConfig() {
/* 51 */     return this.config;
/*    */   }
/*    */   
/*    */   public boolean exists() {
/* 55 */     return this.exists;
/*    */   }
/*    */   
/*    */   public void save() {
/*    */     try {
/* 60 */       this.config.save(new File(this.plugin.getDataFolder() + this.path, this.name + ".yml"));
/* 61 */     } catch (IOException iOException) {
/* 62 */       MMOItems.plugin.getLogger().log(Level.SEVERE, "Could not save " + this.name + ".yml: " + iOException.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setup() {
/*    */     try {
/* 69 */       if (!(new File(this.plugin.getDataFolder() + this.path)).exists()) {
/* 70 */         (new File(this.plugin.getDataFolder() + this.path)).mkdir();
/*    */       }
/* 72 */       if (!(new File(this.plugin.getDataFolder() + this.path, this.name + ".yml")).exists()) {
/* 73 */         (new File(this.plugin.getDataFolder() + this.path, this.name + ".yml")).createNewFile();
/*    */       }
/* 75 */     } catch (IOException iOException) {
/* 76 */       MMOItems.plugin.getLogger().log(Level.SEVERE, "Could not generate " + this.name + ".yml: " + iOException.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void registerTemplateEdition(ItemReference paramItemReference) {
/* 85 */     save();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 91 */     MMOItems.plugin.getTemplates().requestTemplateUpdate(paramItemReference.getType(), paramItemReference.getId());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ConfigFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */