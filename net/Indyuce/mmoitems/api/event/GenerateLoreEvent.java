/*    */ package net.Indyuce.mmoitems.api.event;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.item.build.LoreBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class GenerateLoreEvent
/*    */   extends Event
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final MMOItem item;
/*    */   private final List<String> lore;
/*    */   private final List<String> parsed;
/*    */   private final LoreBuilder builder;
/*    */   
/*    */   public GenerateLoreEvent(MMOItem paramMMOItem, LoreBuilder paramLoreBuilder, List<String> paramList1, List<String> paramList2) {
/* 23 */     this.item = paramMMOItem;
/* 24 */     this.lore = paramList1;
/* 25 */     this.parsed = paramList2;
/* 26 */     this.builder = paramLoreBuilder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MMOItem getItem() {
/* 33 */     return this.item;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LoreBuilder getBuilder() {
/* 40 */     return this.builder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> getLore() {
/* 47 */     return this.lore;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List<String> getParsedLore() {
/* 54 */     return this.parsed;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFinalLore(List<String> paramList) {
/* 66 */     this.parsed.clear();
/* 67 */     this.parsed.addAll(paramList);
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers() {
/* 71 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 75 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\GenerateLoreEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */