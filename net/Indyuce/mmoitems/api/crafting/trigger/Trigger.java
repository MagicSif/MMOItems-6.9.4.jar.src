/*    */ package net.Indyuce.mmoitems.api.crafting.trigger;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ 
/*    */ public abstract class Trigger {
/*    */   private final String id;
/*    */   
/*    */   public Trigger(String paramString) {
/*  9 */     this.id = paramString;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 13 */     return this.id;
/*    */   }
/*    */   
/*    */   public abstract void whenCrafting(PlayerData paramPlayerData);
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\trigger\Trigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */