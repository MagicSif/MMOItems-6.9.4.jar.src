/*    */ package net.Indyuce.mmoitems.api.crafting;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.item.util.ConfigItem;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class StationItemOptions
/*    */ {
/*    */   private final ItemStack fill;
/*    */   
/*    */   public StationItemOptions(ConfigurationSection paramConfigurationSection) {
/* 13 */     this.fill = (new ConfigItem(paramConfigurationSection.getConfigurationSection("fill"))).getItem();
/* 14 */     this.noRecipe = (new ConfigItem(paramConfigurationSection.getConfigurationSection("no-recipe"))).getItem();
/* 15 */     this.noQueueItem = (new ConfigItem(paramConfigurationSection.getConfigurationSection("no-queue-item"))).getItem();
/*    */   }
/*    */   private final ItemStack noRecipe; private final ItemStack noQueueItem;
/*    */   public ItemStack getFill() {
/* 19 */     return this.fill;
/*    */   }
/*    */   
/*    */   public ItemStack getNoRecipe() {
/* 23 */     return this.noRecipe;
/*    */   }
/*    */   
/*    */   public ItemStack getNoQueueItem() {
/* 27 */     return this.noQueueItem;
/*    */   }
/*    */   
/*    */   public boolean hasFill() {
/* 31 */     return (this.fill.getType() != Material.AIR);
/*    */   }
/*    */   
/*    */   public boolean hasNoRecipe() {
/* 35 */     return (this.noRecipe.getType() != Material.AIR);
/*    */   }
/*    */   
/*    */   public boolean hasNoQueueItem() {
/* 39 */     return (this.noQueueItem.getType() != Material.AIR);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\StationItemOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */