/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.GemStone;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class ApplyGemStoneEvent extends PlayerDataEvent {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */   
/*    */   private final VolatileMMOItem gemStone;
/*    */ 
/*    */ 
/*    */   
/*    */   private final MMOItem targetItem;
/*    */ 
/*    */   
/*    */   private GemStone.ResultType result;
/*    */ 
/*    */ 
/*    */   
/*    */   public ApplyGemStoneEvent(PlayerData paramPlayerData, VolatileMMOItem paramVolatileMMOItem, MMOItem paramMMOItem, GemStone.ResultType paramResultType) {
/* 28 */     super(paramPlayerData);
/*    */     
/* 30 */     this.gemStone = paramVolatileMMOItem;
/* 31 */     this.targetItem = paramMMOItem;
/* 32 */     this.result = paramResultType;
/*    */   }
/*    */   
/*    */   public VolatileMMOItem getGemStone() {
/* 36 */     return this.gemStone;
/*    */   }
/*    */   
/*    */   public MMOItem getTargetItem() {
/* 40 */     return this.targetItem;
/*    */   }
/*    */   
/*    */   public GemStone.ResultType getResult() {
/* 44 */     return this.result;
/*    */   }
/*    */   
/*    */   public void setResult(GemStone.ResultType paramResultType) {
/* 48 */     Validate.notNull(paramResultType, "Result cannot be null");
/* 49 */     this.result = paramResultType;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 53 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 57 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\ApplyGemStoneEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */