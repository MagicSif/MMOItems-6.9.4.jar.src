/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.interaction.projectile.EntityData;
/*    */ import net.Indyuce.mmoitems.skill.ShulkerMissile;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.ShulkerBullet;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class VoidSpirit implements StaffAttackHandler {
/*    */   public void handle(PlayerMetadata paramPlayerMetadata, double paramDouble1, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot, final double range) {
/* 20 */     final Vector vec = paramPlayerMetadata.getPlayer().getEyeLocation().getDirection();
/* 21 */     paramPlayerMetadata.getPlayer().getWorld().playSound(paramPlayerMetadata.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, 2.0F, 2.0F);
/* 22 */     final ShulkerBullet shulkerBullet = (ShulkerBullet)paramPlayerMetadata.getPlayer().getWorld().spawnEntity(paramPlayerMetadata.getPlayer().getLocation().add(0.0D, 1.0D, 0.0D), EntityType.valueOf("SHULKER_BULLET"));
/* 23 */     shulkerBullet.setShooter((ProjectileSource)paramPlayerMetadata.getPlayer());
/* 24 */     (new BukkitRunnable() {
/* 25 */         double ti = 0.0D;
/*    */         
/*    */         public void run() {
/* 28 */           this.ti += 0.1D;
/* 29 */           if (shulkerBullet.isDead() || this.ti >= range / 4.0D) {
/* 30 */             shulkerBullet.remove();
/* 31 */             cancel();
/*    */           } 
/* 33 */           shulkerBullet.setVelocity(vec);
/*    */         }
/* 35 */       }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/* 36 */     MMOItems.plugin.getEntities().registerCustomEntity((Entity)shulkerBullet, (EntityData)new ShulkerMissile.ShulkerMissileEntityData(paramPlayerMetadata, new DamageMetadata(paramDouble1, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE }), 0.0D, paramNBTItem));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\staff\VoidSpirit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */