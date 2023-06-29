/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted.lute;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import java.util.List;
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
/*    */ public class WaveLuteAttack
/*    */   implements LuteAttackHandler
/*    */ {
/*    */   public void handle(final PlayerMetadata caster, final double damage, NBTItem paramNBTItem, final double range, @NotNull final Vector weight, @NotNull final SoundReader sound, @NotNull final ProjectileParticlesData projParticle) {
/* 25 */     (new BukkitRunnable() {
/* 26 */         final Vector vec = caster.getPlayer().getEyeLocation().getDirection().multiply(0.4D);
/* 27 */         final Location loc = caster.getPlayer().getEyeLocation();
/* 28 */         int ti = 0;
/*    */         
/*    */         public void run() {
/* 31 */           if (this.ti++ > range) cancel();
/*    */           
/* 33 */           List list = MMOUtils.getNearbyChunkEntities(this.loc);
/* 34 */           for (byte b = 0; b < 3; b++) {
/* 35 */             this.loc.add(this.vec.add(weight));
/* 36 */             if (this.loc.getBlock().getType().isSolid()) {
/* 37 */               cancel();
/*    */               
/*    */               break;
/*    */             } 
/*    */             
/* 42 */             Vector vector = MMOUtils.rotateFunc(new Vector(0.5D, 0.0D, 0.0D), this.loc);
/* 43 */             projParticle.shootParticle(this.loc.clone().add(vector.clone().multiply(Math.sin(this.ti / 2.0D))));
/* 44 */             projParticle.shootParticle(this.loc.clone().add(vector.clone().multiply(-1)));
/*    */             
/* 46 */             if (b == 0) sound.play(this.loc, 2.0F, (float)(0.5D + this.ti / range));
/*    */             
/* 48 */             for (Entity entity : list) {
/* 49 */               if (UtilityMethods.canTarget(caster.getPlayer(), this.loc, entity, InteractionType.OFFENSE_ACTION)) {
/* 50 */                 caster.attack((LivingEntity)entity, damage, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE });
/* 51 */                 cancel();
/*    */                 return;
/*    */               } 
/*    */             } 
/*    */           }  }
/* 56 */       }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\lute\WaveLuteAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */