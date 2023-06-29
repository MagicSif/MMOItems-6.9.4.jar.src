/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.staff;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.version.VersionSound;
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
/*    */ public class SunfireSpirit
/*    */   implements StaffAttackHandler {
/*    */   public void handle(final PlayerMetadata caster, final double damage, NBTItem paramNBTItem, EquipmentSlot paramEquipmentSlot, final double range) {
/* 24 */     caster.getPlayer().getWorld().playSound(caster.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, 2.0F, 2.0F);
/* 25 */     (new BukkitRunnable() {
/* 26 */         final Location target = SunfireSpirit.this.getGround(caster.getPlayer().getTargetBlock(null, (int)range * 2).getLocation()).add(0.0D, 1.2D, 0.0D);
/* 27 */         final double a = StaffAttackHandler.RANDOM.nextDouble() * Math.PI * 2.0D;
/* 28 */         final Location loc = this.target.clone().add(Math.cos(this.a) * 4.0D, 10.0D, Math.sin(this.a) * 4.0D);
/* 29 */         final Vector vec = this.target.toVector().subtract(this.loc.toVector()).multiply(0.015D);
/* 30 */         double ti = 0.0D;
/*    */         
/*    */         public void run() {
/* 33 */           this.loc.getWorld().playSound(this.loc, Sound.BLOCK_FIRE_AMBIENT, 2.0F, 2.0F);
/* 34 */           for (byte b = 0; b < 4; b++) {
/* 35 */             this.ti += 0.015D;
/* 36 */             this.loc.add(this.vec);
/* 37 */             this.loc.getWorld().spawnParticle(Particle.FLAME, this.loc, 0, 0.03D, 0.0D, 0.03D, 0.0D);
/* 38 */             if (this.ti >= 1.0D) {
/* 39 */               this.loc.getWorld().spawnParticle(Particle.FLAME, this.loc, 24, 0.0D, 0.0D, 0.0D, 0.12D);
/* 40 */               this.loc.getWorld().spawnParticle(Particle.SMOKE_NORMAL, this.loc, 24, 0.0D, 0.0D, 0.0D, 0.12D);
/* 41 */               this.loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, this.loc, 0);
/* 42 */               this.loc.getWorld().playSound(this.loc, VersionSound.ENTITY_FIREWORK_ROCKET_BLAST.toSound(), 2.0F, 2.0F);
/* 43 */               for (Entity entity : MMOUtils.getNearbyChunkEntities(this.loc)) {
/* 44 */                 if (UtilityMethods.canTarget(caster.getPlayer(), entity, InteractionType.OFFENSE_ACTION) && entity.getLocation().distanceSquared(this.loc) <= 9.0D)
/* 45 */                   caster.attack((LivingEntity)entity, damage, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE }); 
/* 46 */               }  cancel();
/*    */               break;
/*    */             } 
/*    */           } 
/*    */         }
/* 51 */       }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\staff\SunfireSpirit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */