/*     */ package net.Indyuce.mmoitems.manager;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.event.PlayerAttackEvent;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.damage.ProjectileAttackMetadata;
/*     */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.WeakHashMap;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.interaction.projectile.ArrowParticles;
/*     */ import net.Indyuce.mmoitems.api.interaction.projectile.EntityData;
/*     */ import net.Indyuce.mmoitems.api.interaction.projectile.ProjectileData;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.AbstractArrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.entity.ProjectileHitEvent;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityManager
/*     */   implements Listener
/*     */ {
/*  36 */   private final Map<Integer, EntityData> entities = new HashMap<>();
/*     */   
/*  38 */   private final Map<Integer, ProjectileData> projectiles = new WeakHashMap<>();
/*     */   
/*     */   @Deprecated
/*     */   public void registerCustomProjectile(@NotNull NBTItem paramNBTItem, @NotNull PlayerMetadata paramPlayerMetadata, @NotNull Entity paramEntity, boolean paramBoolean, double paramDouble) {
/*  42 */     registerCustomProjectile(paramNBTItem, paramPlayerMetadata, paramEntity, paramDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ProjectileData registerCustomProjectile(@NotNull NBTItem paramNBTItem, @NotNull PlayerMetadata paramPlayerMetadata, @NotNull Entity paramEntity, double paramDouble) {
/*  58 */     ProjectileData projectileData = new ProjectileData(paramPlayerMetadata, paramNBTItem, paramDouble);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     if (paramEntity instanceof AbstractArrow && paramNBTItem.hasTag("MMOITEMS_ARROW_PARTICLES")) {
/*  66 */       new ArrowParticles((AbstractArrow)paramEntity, paramNBTItem);
/*     */     }
/*  68 */     this.projectiles.put(Integer.valueOf(paramEntity.getEntityId()), projectileData);
/*  69 */     return projectileData;
/*     */   }
/*     */   
/*     */   public void registerCustomEntity(Entity paramEntity, EntityData paramEntityData) {
/*  73 */     this.entities.put(Integer.valueOf(paramEntity.getEntityId()), paramEntityData);
/*     */   }
/*     */   
/*     */   public boolean isCustomProjectile(Projectile paramProjectile) {
/*  77 */     return this.projectiles.containsKey(Integer.valueOf(paramProjectile.getEntityId()));
/*     */   }
/*     */   
/*     */   public boolean isCustomEntity(Entity paramEntity) {
/*  81 */     return this.entities.containsKey(Integer.valueOf(paramEntity.getEntityId()));
/*     */   }
/*     */   
/*     */   public ProjectileData getProjectileData(Projectile paramProjectile) {
/*  85 */     return Objects.<ProjectileData>requireNonNull(this.projectiles.get(Integer.valueOf(paramProjectile.getEntityId())), "Provided entity is not a custom projectile");
/*     */   }
/*     */   
/*     */   public EntityData getEntityData(Entity paramEntity) {
/*  89 */     return Objects.<EntityData>requireNonNull(this.entities.get(Integer.valueOf(paramEntity.getEntityId())), "Provided entity is not a custom entity");
/*     */   }
/*     */   
/*     */   public void unregisterCustomProjectile(Projectile paramProjectile) {
/*  93 */     this.projectiles.remove(Integer.valueOf(paramProjectile.getEntityId()));
/*     */   }
/*     */   
/*     */   public void unregisterCustomEntity(Entity paramEntity) {
/*  97 */     this.entities.remove(Integer.valueOf(paramEntity.getEntityId()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
/*     */   public void customProjectileDamage(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
/* 111 */     if (!(paramEntityDamageByEntityEvent.getDamager() instanceof Projectile) || !(paramEntityDamageByEntityEvent.getEntity() instanceof org.bukkit.entity.LivingEntity) || paramEntityDamageByEntityEvent.getEntity().hasMetadata("NPC")) {
/*     */       return;
/*     */     }
/* 114 */     Projectile projectile = (Projectile)paramEntityDamageByEntityEvent.getDamager();
/* 115 */     ProjectileData projectileData = this.projectiles.get(Integer.valueOf(projectile.getEntityId()));
/* 116 */     if (projectileData == null) {
/*     */       return;
/*     */     }
/*     */     
/* 120 */     double d = projectileData.getDamage();
/*     */ 
/*     */     
/* 123 */     if (projectile instanceof AbstractArrow && projectileData.getSourceItem().getItem().hasItemMeta() && projectileData
/* 124 */       .getSourceItem().getItem().getItemMeta().getEnchants().containsKey(Enchantment.ARROW_DAMAGE)) {
/* 125 */       d *= 1.25D + 0.25D * projectileData.getSourceItem().getItem().getItemMeta().getEnchantLevel(Enchantment.ARROW_DAMAGE);
/*     */     }
/* 127 */     paramEntityDamageByEntityEvent.setDamage(d);
/*     */   }
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void onHitEffects(PlayerAttackEvent paramPlayerAttackEvent) {
/* 132 */     if (!(paramPlayerAttackEvent.getAttack() instanceof ProjectileAttackMetadata)) {
/*     */       return;
/*     */     }
/* 135 */     ProjectileAttackMetadata projectileAttackMetadata = (ProjectileAttackMetadata)paramPlayerAttackEvent.getAttack();
/* 136 */     ProjectileData projectileData = this.projectiles.get(Integer.valueOf(projectileAttackMetadata.getProjectile().getEntityId()));
/* 137 */     if (projectileData == null) {
/*     */       return;
/*     */     }
/*     */     
/* 141 */     projectileData.applyPotionEffects(paramPlayerAttackEvent.getEntity());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void unregisterProjectileData(ProjectileHitEvent paramProjectileHitEvent) {
/* 146 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MMOItems.plugin, () -> unregisterCustomProjectile(paramProjectileHitEvent.getEntity()));
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void unregisterEntityData(EntityDeathEvent paramEntityDeathEvent) {
/* 151 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MMOItems.plugin, () -> unregisterCustomEntity((Entity)paramEntityDeathEvent.getEntity()));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\EntityManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */