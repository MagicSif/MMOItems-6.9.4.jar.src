/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted;
/*    */ 
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum UntargetedWeaponType
/*    */ {
/* 14 */   RIGHT_CLICK,
/* 15 */   LEFT_CLICK;
/*    */   
/*    */   public boolean corresponds(@NotNull Action paramAction) {
/* 18 */     return (this == RIGHT_CLICK) ? (
/* 19 */       (paramAction == Action.RIGHT_CLICK_AIR || paramAction == Action.RIGHT_CLICK_BLOCK)) : (
/* 20 */       (paramAction == Action.LEFT_CLICK_AIR || paramAction == Action.LEFT_CLICK_BLOCK));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\UntargetedWeaponType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */