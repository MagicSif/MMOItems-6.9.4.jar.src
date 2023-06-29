/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.UntargetedWeapon;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class UntargetedWeaponUseEvent
/*    */   extends PlayerDataEvent
/*    */   implements Cancellable {
/*    */   private final UntargetedWeapon weapon;
/*    */   private boolean cancelled;
/* 15 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UntargetedWeaponUseEvent(@NotNull PlayerData paramPlayerData, @NotNull UntargetedWeapon paramUntargetedWeapon) {
/* 24 */     super(paramPlayerData);
/*    */     
/* 26 */     this.weapon = paramUntargetedWeapon;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public UntargetedWeapon getWeapon() {
/* 34 */     return this.weapon;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 39 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean paramBoolean) {
/* 44 */     this.cancelled = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 50 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 54 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\UntargetedWeaponUseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */