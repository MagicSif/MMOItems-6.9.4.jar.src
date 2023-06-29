/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class ItemEquipEvent extends PlayerDataEvent implements Cancellable {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final ItemStack item;
/*    */   
/*    */   public ItemEquipEvent(Player paramPlayer, ItemStack paramItemStack) {
/* 17 */     super(PlayerData.get((OfflinePlayer)paramPlayer));
/*    */     
/* 19 */     this.item = paramItemStack;
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 23 */     return this.item;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 27 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 31 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\ItemEquipEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */