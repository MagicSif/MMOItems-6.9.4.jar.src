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
/*    */ import org.bukkit.Color;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Particle;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class ManaSpirit
/*    */   implements StaffAttackHandler
/*    */ {
/*    */   public void handle(final PlayerMetadata caster, final double damage, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot, final double range) {
/* 26 */     (new BukkitRunnable() {
/* 27 */         final Vector vec = caster.getPlayer().getEyeLocation().getDirection().multiply(0.4D);
/* 28 */         final Location loc = caster.getPlayer().getEyeLocation();
/* 29 */         int ti = 0;
/* 30 */         final double r = 0.2D;
/*    */         
/*    */         public void run() {
/* 33 */           if (this.ti++ > range) {
/* 34 */             cancel();
/*    */           }
/* 36 */           if (this.ti % 2 == 0)
/* 37 */             this.loc.getWorld().playSound(this.loc, Sound.BLOCK_SNOW_BREAK, 2.0F, 2.0F); 
/* 38 */           List list = MMOUtils.getNearbyChunkEntities(this.loc);
/* 39 */           for (byte b = 0; b < 3; b++) {
/* 40 */             this.loc.add(this.vec);
/* 41 */             if (this.loc.getBlock().getType().isSolid()) {
/* 42 */               cancel();
/*    */               
/*    */               break;
/*    */             } 
/* 46 */             for (double d = 0.0D; d < 6.283185307179586D; d += 0.8975979010256552D) {
/* 47 */               Vector vector = MMOUtils.rotateFunc(new Vector(0.2D * Math.cos(d), 0.2D * Math.sin(d), 0.0D), this.loc);
/* 48 */               if (StaffAttackHandler.RANDOM.nextDouble() <= 0.6D)
/* 49 */                 this.loc.getWorld().spawnParticle(Particle.REDSTONE, this.loc.clone().add(vector), 1, new Particle.DustOptions(Color.AQUA, 1.0F)); 
/*    */             } 
/* 51 */             for (Entity entity : list) {
/* 52 */               if (UtilityMethods.canTarget(caster.getPlayer(), this.loc, entity, InteractionType.OFFENSE_ACTION)) {
/* 53 */                 caster.attack((LivingEntity)entity, damage, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE });
/* 54 */                 this.loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, this.loc, 0);
/* 55 */                 cancel();
/*    */                 return;
/*    */               } 
/*    */             } 
/*    */           }  }
/* 60 */       }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\staff\ManaSpirit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */