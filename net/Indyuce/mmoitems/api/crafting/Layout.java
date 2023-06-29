/*    */ package net.Indyuce.mmoitems.api.crafting;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ 
/*    */ 
/*    */ public class Layout
/*    */ {
/*    */   private final String id;
/*    */   private final List<Integer> recipeSlots;
/*    */   private final List<Integer> queueSlots;
/*    */   private final int size;
/*    */   
/*    */   public Layout(String paramString, FileConfiguration paramFileConfiguration) {
/* 16 */     this.id = paramString;
/* 17 */     this.size = paramFileConfiguration.getInt("slots");
/*    */     
/* 19 */     ConfigurationSection configurationSection = paramFileConfiguration.getConfigurationSection("layout");
/*    */ 
/*    */     
/* 22 */     this.recipeSlots = configurationSection.getIntegerList("recipe-slots");
/* 23 */     this.queueSlots = configurationSection.getIntegerList("queue-slots");
/*    */ 
/*    */     
/* 26 */     this.recipePreviousSlot = configurationSection.getInt("recipe-previous-slot", 18);
/* 27 */     this.recipeNextSlot = configurationSection.getInt("recipe-next-slot", 26);
/* 28 */     this.queuePreviousSlot = configurationSection.getInt("queue-previous-slot", 37);
/* 29 */     this.queueNextSlot = configurationSection.getInt("queue-next-slot", 43);
/*    */   }
/*    */   private final int recipePreviousSlot; private final int recipeNextSlot; private final int queuePreviousSlot; private final int queueNextSlot;
/*    */   
/*    */   public String getId() {
/* 34 */     return this.id;
/*    */   }
/*    */   
/*    */   public int getSize() {
/* 38 */     return this.size;
/*    */   }
/*    */   
/*    */   public List<Integer> getRecipeSlots() {
/* 42 */     return this.recipeSlots;
/*    */   }
/*    */   
/*    */   public List<Integer> getQueueSlots() {
/* 46 */     return this.queueSlots;
/*    */   }
/*    */   
/*    */   public int getRecipePreviousSlot() {
/* 50 */     return this.recipePreviousSlot;
/*    */   }
/*    */   
/*    */   public int getRecipeNextSlot() {
/* 54 */     return this.recipeNextSlot;
/*    */   }
/*    */   
/*    */   public int getQueuePreviousSlot() {
/* 58 */     return this.queuePreviousSlot;
/*    */   }
/*    */   
/*    */   public int getQueueNextSlot() {
/* 62 */     return this.queueNextSlot;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\Layout.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */