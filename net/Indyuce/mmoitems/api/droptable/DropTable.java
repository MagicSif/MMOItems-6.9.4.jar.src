/*     */ package net.Indyuce.mmoitems.api.droptable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.droptable.item.BlockDropItem;
/*     */ import net.Indyuce.mmoitems.api.droptable.item.DropItem;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class DropTable {
/*  18 */   private final List<String> subtablesList = new ArrayList<>();
/*  19 */   private final Map<String, Subtable> subtables = new HashMap<>();
/*     */   
/*  21 */   private static final Random random = new Random();
/*     */   
/*     */   public DropTable(ConfigurationSection paramConfigurationSection) {
/*  24 */     Validate.notNull(paramConfigurationSection, "Could not read the drop table config");
/*  25 */     for (String str : paramConfigurationSection.getKeys(false)) {
/*     */ 
/*     */       
/*     */       try {
/*  29 */         for (byte b = 0; b < paramConfigurationSection.getInt(str + ".coef"); b++) {
/*  30 */           this.subtablesList.add(str);
/*     */         }
/*     */         
/*  33 */         this.subtables.put(str, new Subtable(paramConfigurationSection.getConfigurationSection(str)));
/*     */       }
/*  35 */       catch (IllegalArgumentException illegalArgumentException) {
/*  36 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not read subtable '" + str + "' from drop table '" + paramConfigurationSection.getName() + "': " + illegalArgumentException.getMessage());
/*     */       } 
/*     */     } 
/*  39 */     Validate.notEmpty(this.subtablesList, "Your droptable must contain at least one subtable");
/*     */   }
/*     */   
/*     */   public String getRandomSubtable() {
/*  43 */     return this.subtablesList.get(random.nextInt(this.subtablesList.size()));
/*     */   }
/*     */   
/*     */   public List<ItemStack> read(@Nullable PlayerData paramPlayerData, boolean paramBoolean) {
/*  47 */     ArrayList<ItemStack> arrayList = new ArrayList();
/*     */     
/*  49 */     String str = getRandomSubtable();
/*  50 */     for (DropItem dropItem : getSubtable(str).getDropItems(paramBoolean)) {
/*  51 */       if (dropItem.rollDrop()) {
/*  52 */         ItemStack itemStack = dropItem.getItem(paramPlayerData);
/*  53 */         if (itemStack == null) {
/*  54 */           MMOItems.plugin.getLogger().log(Level.WARNING, "Couldn't read the subtable item " + dropItem.getKey()); continue;
/*     */         } 
/*  56 */         arrayList.add(itemStack);
/*     */       } 
/*     */     } 
/*  59 */     return arrayList;
/*     */   }
/*     */   
/*     */   public Collection<Subtable> getSubtables() {
/*  63 */     return this.subtables.values();
/*     */   }
/*     */   
/*     */   public Subtable getSubtable(String paramString) {
/*  67 */     return this.subtables.get(paramString);
/*     */   }
/*     */   
/*     */   public static class Subtable {
/*  71 */     private final List<DropItem> items = new ArrayList<>();
/*     */ 
/*     */     
/*     */     private final boolean disableSilkTouch;
/*     */ 
/*     */ 
/*     */     
/*     */     public Subtable(ConfigurationSection param1ConfigurationSection) {
/*  79 */       Validate.notNull(param1ConfigurationSection, "Could not read subtable config");
/*     */       
/*  81 */       Validate.isTrue(param1ConfigurationSection.contains("coef"), "Could not read subtable coefficient.");
/*  82 */       Validate.isTrue((param1ConfigurationSection.contains("items") || param1ConfigurationSection.contains("blocks")), "Could not find item/block list");
/*     */       
/*  84 */       if (param1ConfigurationSection.contains("items"))
/*  85 */         for (String str : param1ConfigurationSection.getConfigurationSection("items").getKeys(false)) {
/*  86 */           Type type = MMOItems.plugin.getTypes().getOrThrow(str.toUpperCase().replace("-", "_"));
/*  87 */           for (String str1 : param1ConfigurationSection.getConfigurationSection("items." + str).getKeys(false)) {
/*  88 */             this.items.add(new MMOItemDropItem(type, str1, param1ConfigurationSection.getString("items." + str + "." + str1)));
/*     */           }
/*     */         }  
/*  91 */       if (param1ConfigurationSection.contains("blocks")) {
/*  92 */         for (String str : param1ConfigurationSection.getConfigurationSection("blocks").getKeys(false)) {
/*  93 */           int i = Integer.parseInt(str);
/*  94 */           Validate.isTrue((i > 0 && i != 54 && i <= 160), i + " is not a valid block ID");
/*  95 */           this.items.add(new BlockDropItem(i, param1ConfigurationSection.getString("blocks." + str)));
/*     */         } 
/*     */       }
/*  98 */       this.disableSilkTouch = param1ConfigurationSection.getBoolean("disable-silk-touch");
/*     */     }
/*     */     
/*     */     public List<DropItem> getDropItems(boolean param1Boolean) {
/* 102 */       return (param1Boolean && this.disableSilkTouch) ? new ArrayList<>() : this.items;
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\droptable\DropTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */