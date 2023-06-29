/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Particle;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class NetherSpirit
/*    */   implements StaffAttackHandler
/*    */ {
/*    */   public void handle(final PlayerMetadata caster, final double damage, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot, final double range) {
/* 25 */     (new BukkitRunnable() {
/* 26 */         final Vector vec = caster.getPlayer().getEyeLocation().getDirection().multiply(0.3D);
/* 27 */         final Location loc = caster.getPlayer().getEyeLocation();
/* 28 */         int ti = 0;
/*    */         
/*    */         public void run() {
/* 31 */           if (this.ti++ % 2 == 0)
/* 32 */             this.loc.getWorld().playSound(this.loc, Sound.BLOCK_FIRE_AMBIENT, 2.0F, 2.0F); 
/* 33 */           List list = MMOUtils.getNearbyChunkEntities(this.loc);
/* 34 */           for (byte b = 0; b < 3; b++) {
/* 35 */             this.loc.add(this.vec);
/* 36 */             if (this.loc.getBlock().getType().isSolid()) {
/* 37 */               cancel();
/*    */               
/*    */               break;
/*    */             } 
/* 41 */             this.loc.getWorld().spawnParticle(Particle.FLAME, this.loc, 2, 0.07D, 0.07D, 0.07D, 0.0D);
/* 42 */             this.loc.getWorld().spawnParticle(Particle.SMOKE_NORMAL, this.loc, 0);
/* 43 */             for (Entity entity : list) {
/* 44 */               if (UtilityMethods.canTarget(caster.getPlayer(), this.loc, entity, InteractionType.OFFENSE_ACTION)) {
/* 45 */                 caster.attack((LivingEntity)entity, damage, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE });
/* 46 */                 this.loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, this.loc, 0);
/* 47 */                 cancel(); return;
/*    */               } 
/*    */             } 
/*    */           } 
/* 51 */           if (this.ti >= range)
/* 52 */             cancel(); 
/*    */         }
/* 54 */       }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\staff\NetherSpirit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */