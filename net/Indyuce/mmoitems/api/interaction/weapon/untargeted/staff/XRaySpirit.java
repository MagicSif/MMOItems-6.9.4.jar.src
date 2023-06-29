/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.util.RayTrace;
/*    */ import org.bukkit.Color;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ public class XRaySpirit
/*    */   implements StaffAttackHandler {
/*    */   public void handle(PlayerMetadata paramPlayerMetadata, double paramDouble1, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot, double paramDouble2) {
/* 17 */     paramPlayerMetadata.getPlayer().getWorld().playSound(paramPlayerMetadata.getPlayer().getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 2.0F, 2.0F);
/*    */     
/* 19 */     RayTrace rayTrace = new RayTrace(paramPlayerMetadata.getPlayer(), paramEquipmentSlot, paramDouble2, paramEntity -> UtilityMethods.canTarget(paramPlayerMetadata.getPlayer(), paramEntity, InteractionType.OFFENSE_ACTION));
/* 20 */     if (rayTrace.hasHit())
/* 21 */       paramPlayerMetadata.attack(rayTrace.getHit(), paramDouble1, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE }); 
/* 22 */     rayTrace.draw(0.5D, Color.BLACK);
/* 23 */     paramPlayerMetadata.getPlayer().getWorld().playSound(paramPlayerMetadata.getPlayer().getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 0.4F, 2.0F);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\staff\XRaySpirit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */