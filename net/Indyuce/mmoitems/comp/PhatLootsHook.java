/*    */ package net.Indyuce.mmoitems.comp;
/*    */ 
/*    */ import com.codisimus.plugins.phatloots.events.LootEvent;
/*    */ import com.codisimus.plugins.phatloots.events.MobDropLootEvent;
/*    */ import com.codisimus.plugins.phatloots.events.PlayerLootEvent;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.util.MMOItemReforger;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PhatLootsHook
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void OnLootBeLooted(MobDropLootEvent paramMobDropLootEvent) {
/* 22 */     handle((LootEvent)paramMobDropLootEvent);
/*    */   } @EventHandler
/*    */   public void OnLootBeLooted(PlayerLootEvent paramPlayerLootEvent) {
/* 25 */     handle((LootEvent)paramPlayerLootEvent);
/*    */   }
/*    */ 
/*    */   
/*    */   public void handle(LootEvent paramLootEvent) {
/* 30 */     for (ItemStack itemStack : paramLootEvent.getItemList()) {
/*    */ 
/*    */ 
/*    */       
/* 34 */       if (SilentNumbers.isAir(itemStack)) {
/*    */         continue;
/*    */       }
/*    */ 
/*    */       
/* 39 */       MMOItemReforger mMOItemReforger = new MMOItemReforger(itemStack);
/*    */ 
/*    */       
/* 42 */       if (!mMOItemReforger.reforge((MMOItems.plugin.getLanguage()).phatLootsOptions)) {
/*    */         continue;
/*    */       }
/*    */       
/* 46 */       if (mMOItemReforger.hasChanges()) {
/*    */ 
/*    */         
/* 49 */         ItemStack itemStack1 = mMOItemReforger.toStack();
/* 50 */         itemStack1.setAmount(itemStack.getAmount());
/* 51 */         ItemMeta itemMeta = itemStack1.getItemMeta();
/*    */ 
/*    */ 
/*    */         
/* 55 */         itemStack.setType(itemStack1.getType());
/* 56 */         itemStack.setItemMeta(itemMeta);
/* 57 */         itemStack.setData(itemStack1.getData());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\PhatLootsHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */