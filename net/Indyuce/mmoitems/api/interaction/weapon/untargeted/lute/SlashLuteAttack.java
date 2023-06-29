/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.lute;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.util.SoundReader;
/*    */ import net.Indyuce.mmoitems.stat.data.ProjectileParticlesData;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SlashLuteAttack
/*    */   implements LuteAttackHandler {
/*    */   public void handle(final PlayerMetadata caster, double paramDouble1, NBTItem paramNBTItem, final double range, @NotNull Vector paramVector, @NotNull final SoundReader sound, @NotNull final ProjectileParticlesData projParticle) {
/* 23 */     (new BukkitRunnable() {
/* 24 */         final Vector vec = caster.getPlayer().getEyeLocation().getDirection();
/* 25 */         final Location loc = caster.getPlayer().getLocation().add(0.0D, 1.3D, 0.0D);
/* 26 */         double ti = 1.0D;
/*    */         
/*    */         public void run() {
/* 29 */           if ((this.ti += 0.6D) > 5.0D) cancel();
/*    */           
/* 31 */           sound.play(this.loc, 2.0F, (float)(0.5D + this.ti / range));
/* 32 */           for (byte b = -30; b < 30; b += 3) {
/* 33 */             if (LuteAttackHandler.RANDOM.nextBoolean()) {
/* 34 */               this.loc.setDirection(this.vec);
/* 35 */               this.loc.setYaw(this.loc.getYaw() + b);
/* 36 */               this.loc.setPitch(caster.getPlayer().getEyeLocation().getPitch());
/*    */               
/* 38 */               projParticle.shootParticle(this.loc.clone().add(this.loc.getDirection().multiply(1.5D * this.ti)));
/*    */             } 
/*    */           }  }
/* 41 */       }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*    */     
/* 43 */     for (Entity entity : MMOUtils.getNearbyChunkEntities(caster.getPlayer().getLocation())) {
/* 44 */       if (entity.getLocation().distanceSquared(caster.getPlayer().getLocation()) < 40.0D && caster
/* 45 */         .getPlayer().getEyeLocation().getDirection().angle(entity.getLocation().toVector().subtract(caster.getPlayer().getLocation().toVector())) < 0.5235987755982988D && 
/* 46 */         UtilityMethods.canTarget(caster.getPlayer(), entity, InteractionType.OFFENSE_ACTION))
/* 47 */         caster.attack((LivingEntity)entity, paramDouble1, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE }); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\lute\SlashLuteAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */