/*    */ package net.Indyuce.mmoitems.manager;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ConfigFile;
/*    */ import net.Indyuce.mmoitems.api.ItemSet;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ public class SetManager
/*    */   implements Reloadable {
/* 14 */   private final Map<String, ItemSet> itemSets = new HashMap<>();
/*    */   
/*    */   public SetManager() {
/* 17 */     reload();
/*    */   }
/*    */   
/*    */   public void reload() {
/* 21 */     this.itemSets.clear();
/*    */     
/* 23 */     ConfigFile configFile = new ConfigFile("item-sets");
/* 24 */     for (String str : configFile.getConfig().getKeys(false)) {
/*    */       try {
/* 26 */         ConfigurationSection configurationSection = configFile.getConfig().getConfigurationSection(str);
/* 27 */         if (configurationSection == null)
/* 28 */           throw new IllegalStateException("Item set '%s' is not a valid configuration section.".formatted(new Object[] { str })); 
/* 29 */         this.itemSets.put(str, new ItemSet(configurationSection));
/* 30 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 31 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not load item set '%s': %s".formatted(new Object[] { str, illegalArgumentException.getMessage() }));
/*    */       } 
/*    */     } 
/*    */   }
/*    */   public void register(ItemSet paramItemSet) {
/* 36 */     this.itemSets.put(paramItemSet.getId(), paramItemSet);
/*    */   }
/*    */   
/*    */   public boolean has(String paramString) {
/* 40 */     return this.itemSets.containsKey(paramString);
/*    */   }
/*    */   
/*    */   public Collection<ItemSet> getAll() {
/* 44 */     return this.itemSets.values();
/*    */   }
/*    */   
/*    */   public ItemSet get(String paramString) {
/* 48 */     return this.itemSets.getOrDefault(paramString, null);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\SetManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */