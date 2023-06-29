/*    */ package net.Indyuce.mmoitems.api.event.item;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.event.PlayerDataEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.weapon.Weapon;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SpecialWeaponAttackEvent
/*    */   extends PlayerDataEvent
/*    */   implements Cancellable
/*    */ {
/*    */   private final Weapon weapon;
/*    */   private final LivingEntity target;
/*    */   private boolean cancelled;
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SpecialWeaponAttackEvent(@NotNull PlayerData paramPlayerData, @NotNull Weapon paramWeapon, @NotNull LivingEntity paramLivingEntity) {
/* 28 */     super(paramPlayerData);
/*    */     
/* 30 */     this.weapon = paramWeapon;
/* 31 */     this.target = paramLivingEntity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Weapon getWeapon() {
/* 39 */     return this.weapon;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getTarget() {
/* 47 */     return this.target;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 52 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean paramBoolean) {
/* 57 */     this.cancelled = paramBoolean;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 63 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 67 */     return handlers;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\event\item\SpecialWeaponAttackEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */