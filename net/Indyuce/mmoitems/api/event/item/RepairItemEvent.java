/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ 
/*    */ public class RepairItemEvent
/*    */   extends PlayerDataEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */   
/*    */   private final VolatileMMOItem consumable;
/*    */ 
/*    */ 
/*    */   
/*    */   private final NBTItem target;
/*    */ 
/*    */   
/*    */   private int repaired;
/*    */ 
/*    */ 
/*    */   
/*    */   public RepairItemEvent(PlayerData paramPlayerData, VolatileMMOItem paramVolatileMMOItem, NBTItem paramNBTItem, int paramInt) {
/* 29 */     super(paramPlayerData);
/*    */     
/* 31 */     this.consumable = paramVolatileMMOItem;
/* 32 */     this.target = paramNBTItem;
/* 33 */     this.repaired = paramInt;
/*    */   }
/*    */   
/*    */   public VolatileMMOItem getConsumable() {
/* 37 */     return this.consumable;
/*    */   }
/*    */   
/*    */   public NBTItem getTargetItem() {
/* 41 */     return this.target;
/*    */   }
/*    */   
/*    */   public int getRepaired() {
/* 45 */     return this.repaired;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public double getRepairedPercent() {
/* 50 */     boolean bool = this.target.hasTag("MMOITEMS_DURABILITY");
/* 51 */     double d = bool ? this.target.getDouble("MMOITEMS_MAX_DURABILITY") : this.target.getItem().getType().getMaxDurability();
/* 52 */     return getRepaired() / d;
/*    */   }
/*    */   
/*    */   public void setRepaired(int paramInt) {
/* 56 */     this.repaired = Math.max(0, paramInt);
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 60 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 64 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\RepairItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */