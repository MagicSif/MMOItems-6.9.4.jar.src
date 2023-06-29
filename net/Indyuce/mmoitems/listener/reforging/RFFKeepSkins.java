/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeFinishEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.ItemSkin;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RFFKeepSkins
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeFinishEvent paramMMOItemReforgeFinishEvent) {
/* 24 */     if (!paramMMOItemReforgeFinishEvent.getOptions().shouldKeepSkins()) {
/*    */       return;
/*    */     }
/*    */     
/* 28 */     String str = paramMMOItemReforgeFinishEvent.getReforger().getNBTItem().getString("MMOITEMS_SKIN_ID");
/* 29 */     if (str != null && !str.isEmpty()) {
/* 30 */       NBTItem nBTItem = NBTItem.get(paramMMOItemReforgeFinishEvent.getFinishedItem());
/* 31 */       ItemStack itemStack = ItemSkin.applySkin(nBTItem, new VolatileMMOItem(paramMMOItemReforgeFinishEvent.getReforger().getNBTItem()));
/* 32 */       paramMMOItemReforgeFinishEvent.setFinishedItem(itemStack);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFFKeepSkins.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */