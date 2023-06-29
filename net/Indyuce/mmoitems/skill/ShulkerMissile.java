/*     */ package net.Indyuce.mmoitems.skill;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.comp.interaction.InteractionType;
/*     */ import io.lumine.mythic.lib.damage.DamageMetadata;
/*     */ import io.lumine.mythic.lib.damage.DamageType;
/*     */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*     */ import io.lumine.mythic.lib.skill.SkillMetadata;
/*     */ import io.lumine.mythic.lib.skill.result.SkillResult;
/*     */ import io.lumine.mythic.lib.skill.result.def.VectorSkillResult;
/*     */ import javax.annotation.Nullable;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.interaction.projectile.EntityData;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.ShulkerBullet;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.util.Vector;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class ShulkerMissile extends SkillHandler<VectorSkillResult> implements Listener {
/*     */   public ShulkerMissile() {
/*  35 */     super("SHULKER_MISSILE");
/*     */     
/*  37 */     registerModifiers(new String[] { "damage", "effect-duration", "duration" });
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public VectorSkillResult getResult(SkillMetadata paramSkillMetadata) {
/*  43 */     return new VectorSkillResult(paramSkillMetadata);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenCast(final VectorSkillResult result, final SkillMetadata skillMeta) {
/*  48 */     final double duration = skillMeta.getParameter("duration");
/*     */     
/*  50 */     final Player caster = skillMeta.getCaster().getPlayer();
/*     */     
/*  52 */     (new BukkitRunnable() {
/*  53 */         double n = 0.0D;
/*     */         
/*     */         public void run() {
/*  56 */           if (this.n++ > 3.0D) {
/*  57 */             cancel();
/*     */             
/*     */             return;
/*     */           } 
/*  61 */           final Vector vec = result.getTarget();
/*  62 */           caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_WITHER_SHOOT, 2.0F, 2.0F);
/*  63 */           final ShulkerBullet shulkerBullet = (ShulkerBullet)caster.getWorld().spawnEntity(caster.getLocation().add(0.0D, 1.0D, 0.0D), EntityType.SHULKER_BULLET);
/*     */           
/*  65 */           shulkerBullet.setShooter((ProjectileSource)caster);
/*     */           
/*  67 */           MMOItems.plugin.getEntities().registerCustomEntity((Entity)shulkerBullet, new ShulkerMissile.ShulkerMissileEntityData(skillMeta.getCaster(), new DamageMetadata(skillMeta.getParameter("damage"), new DamageType[] { DamageType.SKILL, DamageType.MAGIC, DamageType.PROJECTILE }), skillMeta.getParameter("effect-duration"), null));
/*     */           
/*  69 */           (new BukkitRunnable() {
/*  70 */               double ti = 0.0D;
/*     */               
/*     */               public void run() {
/*  73 */                 if (shulkerBullet.isDead() || this.ti++ >= duration * 20.0D) {
/*  74 */                   shulkerBullet.remove();
/*  75 */                   cancel();
/*     */                 } else {
/*  77 */                   shulkerBullet.setVelocity(vec);
/*     */                 }  }
/*  79 */             }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*     */         }
/*  81 */       }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 3L);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void a(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
/*  86 */     if (paramEntityDamageByEntityEvent.getDamager() instanceof ShulkerBullet && paramEntityDamageByEntityEvent.getEntity() instanceof LivingEntity) {
/*  87 */       ShulkerBullet shulkerBullet = (ShulkerBullet)paramEntityDamageByEntityEvent.getDamager();
/*  88 */       final LivingEntity entity = (LivingEntity)paramEntityDamageByEntityEvent.getEntity();
/*  89 */       if (!MMOItems.plugin.getEntities().isCustomEntity((Entity)shulkerBullet)) {
/*     */         return;
/*     */       }
/*  92 */       final ShulkerMissileEntityData data = (ShulkerMissileEntityData)MMOItems.plugin.getEntities().getEntityData((Entity)shulkerBullet);
/*  93 */       if (!UtilityMethods.canTarget(shulkerMissileEntityData.caster.getPlayer(), null, (Entity)livingEntity, shulkerMissileEntityData.isWeaponAttack() ? InteractionType.OFFENSE_ACTION : InteractionType.OFFENSE_SKILL)) {
/*  94 */         paramEntityDamageByEntityEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/*  98 */       paramEntityDamageByEntityEvent.setDamage(shulkerMissileEntityData.damage.getDamage());
/*     */       
/* 100 */       (new BukkitRunnable() {
/* 101 */           final Location loc = entity.getLocation();
/* 102 */           double y = 0.0D;
/*     */ 
/*     */ 
/*     */           
/*     */           public void run() {
/* 107 */             if (this.y == 0.0D) {
/* 108 */               entity.removePotionEffect(PotionEffectType.LEVITATION);
/* 109 */               entity.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, (int)(data.duration * 20.0D), 0));
/*     */             } 
/*     */             
/* 112 */             for (byte b = 0; b < 3; b++) {
/* 113 */               this.y += 0.04D;
/* 114 */               for (byte b1 = 0; b1 < 2; b1++) {
/* 115 */                 double d = this.y * Math.PI * 1.3D + b1 * Math.PI;
/* 116 */                 this.loc.getWorld().spawnParticle(Particle.REDSTONE, this.loc.clone().add(Math.cos(d), this.y, Math.sin(d)), 1, new Particle.DustOptions(Color.MAROON, 1.0F));
/*     */               } 
/*     */             } 
/*     */             
/* 120 */             if (this.y >= 2.0D)
/* 121 */               cancel(); 
/*     */           }
/* 123 */         }).runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static class ShulkerMissileEntityData
/*     */     implements EntityData {
/*     */     private final PlayerMetadata caster;
/*     */     private final DamageMetadata damage;
/*     */     private final double duration;
/*     */     @Nullable
/*     */     private final NBTItem weapon;
/*     */     
/*     */     public ShulkerMissileEntityData(PlayerMetadata param1PlayerMetadata, DamageMetadata param1DamageMetadata, double param1Double, NBTItem param1NBTItem) {
/* 136 */       this.caster = param1PlayerMetadata;
/* 137 */       this.damage = param1DamageMetadata;
/* 138 */       this.duration = param1Double;
/* 139 */       this.weapon = param1NBTItem;
/*     */     }
/*     */     
/*     */     public boolean isWeaponAttack() {
/* 143 */       return this.damage.hasType(DamageType.WEAPON);
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\skill\ShulkerMissile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */