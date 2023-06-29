/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class DeconstructItemEvent
/*    */   extends PlayerDataEvent
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final VolatileMMOItem consumable;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private final NBTItem deconstructed;
/*    */ 
/*    */ 
/*    */   
/*    */   private final List<ItemStack> loot;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DeconstructItemEvent(PlayerData paramPlayerData, VolatileMMOItem paramVolatileMMOItem, NBTItem paramNBTItem, List<ItemStack> paramList) {
/* 34 */     super(paramPlayerData);
/*    */     
/* 36 */     this.consumable = paramVolatileMMOItem;
/* 37 */     this.deconstructed = paramNBTItem;
/* 38 */     this.loot = paramList;
/*    */   }
/*    */   
/*    */   public VolatileMMOItem getConsumable() {
/* 42 */     return this.consumable;
/*    */   }
/*    */   
/*    */   public NBTItem getDeconstructedItem() {
/* 46 */     return this.deconstructed;
/*    */   }
/*    */   
/*    */   public List<ItemStack> getLoot() {
/* 50 */     return this.loot;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 54 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 58 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\DeconstructItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */