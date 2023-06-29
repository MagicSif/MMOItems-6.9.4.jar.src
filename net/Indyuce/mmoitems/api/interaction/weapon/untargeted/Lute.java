/*    */ package net.Indyuce.mmoitems.api.interaction.weapon.untargeted;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*    */ import io.lumine.mythic.lib.damage.DamageType;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.version.VersionSound;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.util.SoundReader;
/*    */ import net.Indyuce.mmoitems.stat.LuteAttackEffectStat;
/*    */ import net.Indyuce.mmoitems.stat.data.ProjectileParticlesData;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.scheduler.BukkitRunnable;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ public class Lute
/*    */   extends UntargetedWeapon
/*    */ {
/*    */   public Lute(Player paramPlayer, NBTItem paramNBTItem) {
/* 28 */     super(paramPlayer, paramNBTItem, UntargetedWeaponType.RIGHT_CLICK);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canAttack(EquipmentSlot paramEquipmentSlot) {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyAttackEffect(final PlayerMetadata stats, EquipmentSlot paramEquipmentSlot) {
/* 38 */     final double attackDamage = requireNonZero(stats.getStat("ATTACK_DAMAGE"), 7.0D);
/* 39 */     final double range = requireNonZero(stats.getStat("RANGE"), MMOItems.plugin.getConfig().getDouble("default.range"));
/* 40 */     final Vector weight = new Vector(0.0D, -0.003D * stats.getStat("NOTE_WEIGHT"), 0.0D);
/*    */     
/* 42 */     LuteAttackEffectStat.LuteAttackEffect luteAttackEffect = LuteAttackEffectStat.LuteAttackEffect.get(getNBTItem());
/*    */     
/* 44 */     final SoundReader sound = new SoundReader(getNBTItem().getString("MMOITEMS_LUTE_ATTACK_SOUND"), VersionSound.BLOCK_NOTE_BLOCK_BELL.toSound());
/*    */     
/* 46 */     final ProjectileParticlesData projParticle = getNBTItem().hasTag("MMOITEMS_PROJECTILE_PARTICLES") ? new ProjectileParticlesData(getNBTItem().getString("MMOITEMS_PROJECTILE_PARTICLES")) : ProjectileParticlesData.DEFAULT;
/*    */ 
/*    */     
/* 49 */     if (luteAttackEffect != null) {
/* 50 */       luteAttackEffect.getAttack().handle(stats, d1, getNBTItem(), d2, vector, soundReader, projectileParticlesData);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 55 */     (new BukkitRunnable() {
/* 56 */         final Vector vec = Lute.this.getPlayer().getEyeLocation().getDirection().multiply(0.4D);
/* 57 */         final Location loc = Lute.this.getPlayer().getEyeLocation();
/* 58 */         int ti = 0;
/*    */         
/*    */         public void run() {
/* 61 */           if (this.ti++ > range) {
/* 62 */             cancel();
/*    */           }
/*    */           
/* 65 */           projParticle.shootParticle(this.loc);
/*    */ 
/*    */           
/* 68 */           sound.play(this.loc, 2.0F, (float)(0.5D + this.ti / range));
/*    */ 
/*    */           
/* 71 */           List list = MMOUtils.getNearbyChunkEntities(this.loc);
/* 72 */           for (byte b = 0; b < 3; b++) {
/* 73 */             this.loc.add(this.vec.add(weight));
/* 74 */             if (this.loc.getBlock().getType().isSolid()) {
/* 75 */               cancel();
/*    */               
/*    */               break;
/*    */             } 
/* 79 */             for (Entity entity : list) {
/* 80 */               if (UtilityMethods.canTarget(Lute.this.getPlayer(), this.loc, entity, InteractionType.OFFENSE_ACTION)) {
/* 81 */                 stats.attack((LivingEntity)entity, attackDamage, new DamageType[] { DamageType.WEAPON, DamageType.MAGIC, DamageType.PROJECTILE });
/* 82 */                 cancel();
/*    */                 return;
/*    */               } 
/*    */             } 
/*    */           }  }
/* 87 */       }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\weapo\\untargeted\Lute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */