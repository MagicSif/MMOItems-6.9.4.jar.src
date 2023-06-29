/*    */ package net.Indyuce.mmoitems.listener.option;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ItemTier;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.entity.ItemSpawnEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class DroppedItems implements Listener {
/*    */   private final boolean tierGlow;
/*    */   
/*    */   public DroppedItems(ConfigurationSection paramConfigurationSection) {
/* 18 */     this.tierGlow = paramConfigurationSection.getBoolean("tier-glow");
/* 19 */     this.hints = paramConfigurationSection.getBoolean("hints");
/*    */   }
/*    */ 
/*    */   
/*    */   private final boolean hints;
/*    */ 
/*    */   
/*    */   @EventHandler
/*    */   public void applyOnSpawn(ItemSpawnEvent paramItemSpawnEvent) {
/* 28 */     ItemStack itemStack = paramItemSpawnEvent.getEntity().getItemStack();
/* 29 */     ItemTier itemTier = MMOItems.plugin.getTiers().get(NBTItem.get(itemStack).getString("MMOITEMS_TIER"));
/* 30 */     if (itemTier == null) {
/*    */       return;
/*    */     }
/* 33 */     if (this.hints && itemTier.isHintEnabled()) {
/* 34 */       paramItemSpawnEvent.getEntity().setCustomNameVisible(true);
/* 35 */       paramItemSpawnEvent.getEntity().setCustomName(itemStack.getItemMeta().getDisplayName());
/*    */     } 
/*    */     
/* 38 */     if (this.tierGlow && itemTier.hasColor())
/* 39 */       MythicLib.plugin.getGlowing().setGlowing((Entity)paramItemSpawnEvent.getEntity(), itemTier.getColor()); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\option\DroppedItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */