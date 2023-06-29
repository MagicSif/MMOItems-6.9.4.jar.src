/*    */ package net.Indyuce.mmoitems.manager;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.file.YamlConfiguration;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class LoreFormatManager
/*    */   implements Reloadable {
/* 16 */   private final Map<String, List<String>> formats = new HashMap<>();
/*    */   
/*    */   public void reload() {
/* 19 */     this.formats.clear();
/* 20 */     File file = new File(MMOItems.plugin.getDataFolder() + "/language/lore-formats");
/* 21 */     for (File file1 : file.listFiles()) {
/*    */       try {
/* 23 */         YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file1);
/* 24 */         Validate.isTrue(yamlConfiguration.isList("lore-format"), "Invalid lore-format! (" + file1.getName() + ")");
/* 25 */         this.formats.put(file1.getName().substring(0, file1.getName().length() - 4), yamlConfiguration.getStringList("lore-format"));
/* 26 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 27 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not load layout '" + file1.getName() + "': " + illegalArgumentException.getMessage());
/*    */       } 
/*    */     } 
/*    */   }
/*    */   public boolean hasFormat(@NotNull String paramString) {
/* 32 */     return this.formats.containsKey(paramString);
/*    */   }
/*    */   
/*    */   public Collection<List<String>> getFormats() {
/* 36 */     return this.formats.values();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<String> getFormat(@NotNull String... paramVarArgs) {
/* 52 */     for (String str : paramVarArgs) {
/* 53 */       List<String> list = this.formats.get(str);
/* 54 */       if (list != null) {
/* 55 */         return list;
/*    */       }
/*    */     } 
/*    */     
/* 59 */     return MMOItems.plugin.getLanguage().getDefaultLoreFormat();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\LoreFormatManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */