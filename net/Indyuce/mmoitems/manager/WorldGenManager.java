/*    */ package net.Indyuce.mmoitems.manager;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ConfigFile;
/*    */ import net.Indyuce.mmoitems.api.block.CustomBlock;
/*    */ import net.Indyuce.mmoitems.api.block.WorldGenTemplate;
/*    */ import net.Indyuce.mmoitems.api.world.MMOBlockPopulator;
/*    */ import net.Indyuce.mmoitems.listener.WorldGenerationListener;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class WorldGenManager implements Reloadable {
/* 21 */   private final Map<String, WorldGenTemplate> templates = new HashMap<>();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 28 */   private final Map<CustomBlock, WorldGenTemplate> assigned = new HashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   private WorldGenerationListener listener;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenManager() {
/* 38 */     reload();
/*    */   }
/*    */   
/*    */   public WorldGenTemplate getOrThrow(String paramString) {
/* 42 */     Validate.isTrue(this.templates.containsKey(paramString), "Could not find gen template with ID '" + paramString + "'");
/*    */     
/* 44 */     return this.templates.get(paramString);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void assign(CustomBlock paramCustomBlock, WorldGenTemplate paramWorldGenTemplate) {
/* 52 */     Validate.notNull(paramWorldGenTemplate, "Cannot assign a null template to a custom block");
/*    */     
/* 54 */     this.assigned.put(paramCustomBlock, paramWorldGenTemplate);
/*    */   }
/*    */ 
/*    */   
/*    */   public void reload() {
/* 59 */     if (this.listener != null) {
/* 60 */       HandlerList.unregisterAll((Listener)this.listener);
/*    */     }
/* 62 */     this.assigned.clear();
/* 63 */     this.templates.clear();
/*    */     
/* 65 */     FileConfiguration fileConfiguration = (new ConfigFile("gen-templates")).getConfig();
/* 66 */     for (String str : fileConfiguration.getKeys(false)) {
/*    */       try {
/* 68 */         WorldGenTemplate worldGenTemplate = new WorldGenTemplate(fileConfiguration.getConfigurationSection(str));
/* 69 */         this.templates.put(worldGenTemplate.getId(), worldGenTemplate);
/* 70 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 71 */         MMOItems.plugin.getLogger().log(Level.WARNING, "An error occurred when loading gen template '" + str + "': " + illegalArgumentException.getMessage());
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 76 */     if ((MMOItems.plugin.getLanguage()).worldGenEnabled) {
/* 77 */       this.listener = new WorldGenerationListener(this);
/* 78 */       Bukkit.getPluginManager().registerEvents((Listener)this.listener, (Plugin)MMOItems.plugin);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void unload() {
/* 83 */     if (this.listener != null)
/* 84 */       HandlerList.unregisterAll((Listener)this.listener); 
/*    */   }
/*    */   
/*    */   public MMOBlockPopulator populator(@NotNull World paramWorld) {
/* 88 */     return new MMOBlockPopulator(paramWorld, this);
/*    */   }
/*    */   
/*    */   public Map<CustomBlock, WorldGenTemplate> assigned() {
/* 92 */     return this.assigned;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\WorldGenManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */