/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.util.RayTrace;
/*    */ import io.lumine.mythic.lib.version.VersionSound;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Particle;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ public class LightningSpirit implements StaffAttackHandler {
/*    */   public void handle(PlayerMetadata paramPlayerMetadata, double paramDouble1, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot, double paramDouble2) {
/* 17 */     paramPlayerMetadata.getPlayer().getWorld().playSound(paramPlayerMetadata.getPlayer().getLocation(), VersionSound.ENTITY_FIREWORK_ROCKET_BLAST.toSound(), 2.0F, 2.0F);
/*    */     
/* 19 */     RayTrace rayTrace = new RayTrace(paramPlayerMetadata.getPlayer(), paramEquipmentSlot, paramDouble2, paramEntity -> UtilityMethods.canTarget(paramPlayerMetadata.getPlayer(), paramEntity, InteractionType.OFFENSE_ACTION));
/*    */     
/* 21 */     if (rayTrace.hasHit())
/* 22 */       paramPlayerMetadata.attack(rayTrace.getHit(), paramDouble1, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE }); 
/* 23 */     rayTrace.draw(0.5D, paramLocation -> paramLocation.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, paramLocation, 0));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\staff\LightningSpirit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */