/*    */ package net.Indyuce.mmoitems.manager;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.crafting.Layout;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ 
/*    */ public class LayoutManager implements Reloadable {
/* 14 */   private final Map<String, Layout> layouts = new HashMap<>();
/*    */   
/*    */   public void reload() {
/* 17 */     this.layouts.clear();
/* 18 */     for (File file : (new File(MMOItems.plugin.getDataFolder() + "/layouts")).listFiles()) {
/*    */       try {
/* 20 */         Layout layout = new Layout(file.getName().substring(0, file.getName().length() - 4), (FileConfiguration)YamlConfiguration.loadConfiguration(file));
/* 21 */         this.layouts.put(layout.getId(), layout);
/* 22 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 23 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not load layout '" + file.getName() + "': " + illegalArgumentException.getMessage());
/*    */       } 
/*    */     } 
/*    */   }
/*    */   public boolean hasLayout(String paramString) {
/* 28 */     return this.layouts.containsKey(paramString);
/*    */   }
/*    */   
/*    */   public Collection<Layout> getLayouts() {
/* 32 */     return this.layouts.values();
/*    */   }
/*    */   
/*    */   public Layout getLayout(String paramString) {
/* 36 */     return this.layouts.getOrDefault(paramString, this.layouts.get("default"));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\LayoutManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */