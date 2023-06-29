/*     */ package net.Indyuce.mmoitems.comp.mythicmobs.mechanics;
/*     */ 
/*     */ import io.lumine.mythic.api.adapters.AbstractEntity;
/*     */ import io.lumine.mythic.api.adapters.AbstractLocation;
/*     */ import io.lumine.mythic.api.adapters.SkillAdapter;
/*     */ import io.lumine.mythic.api.config.MythicLineConfig;
/*     */ import io.lumine.mythic.api.skills.ITargetedEntitySkill;
/*     */ import io.lumine.mythic.api.skills.ITargetedLocationSkill;
/*     */ import io.lumine.mythic.api.skills.SkillCaster;
/*     */ import io.lumine.mythic.api.skills.SkillMetadata;
/*     */ import io.lumine.mythic.api.skills.SkillResult;
/*     */ import io.lumine.mythic.api.skills.ThreadSafetyLevel;
/*     */ import io.lumine.mythic.api.skills.placeholders.PlaceholderDouble;
/*     */ import io.lumine.mythic.api.skills.placeholders.PlaceholderFloat;
/*     */ import io.lumine.mythic.api.skills.placeholders.PlaceholderInt;
/*     */ import io.lumine.mythic.bukkit.BukkitAdapter;
/*     */ import io.lumine.mythic.core.skills.SkillExecutor;
/*     */ import io.lumine.mythic.core.skills.SkillMechanic;
/*     */ import io.lumine.mythic.core.skills.placeholders.PlaceholderMeta;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.UIFilterManager;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.listener.ItemUse;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.AbstractArrow;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ import org.bukkit.util.Vector;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class MMOItemsArrowVolleyMechanic
/*     */   extends SkillMechanic
/*     */   implements ITargetedEntitySkill, ITargetedLocationSkill
/*     */ {
/*     */   @NotNull
/*     */   PlaceholderInt amount;
/*     */   @NotNull
/*     */   PlaceholderInt spread;
/*     */   @NotNull
/*     */   PlaceholderInt fireTicks;
/*     */   @NotNull
/*     */   PlaceholderInt removeDelay;
/*     */   
/*     */   public MMOItemsArrowVolleyMechanic(SkillExecutor paramSkillExecutor, String paramString, MythicLineConfig paramMythicLineConfig) {
/*  57 */     super(paramSkillExecutor, paramString, paramMythicLineConfig);
/*  58 */     this.threadSafetyLevel = ThreadSafetyLevel.SYNC_ONLY;
/*     */     
/*  60 */     this.amount = paramMythicLineConfig.getPlaceholderInteger(new String[] { "amount", "arrows", "a" }, 20, new String[0]);
/*  61 */     this.spread = paramMythicLineConfig.getPlaceholderInteger(new String[] { "spread", "s" }, 45, new String[0]);
/*  62 */     this.fireTicks = paramMythicLineConfig.getPlaceholderInteger(new String[] { "fireticks", "ft", "f" }, 0, new String[0]);
/*  63 */     this.removeDelay = paramMythicLineConfig.getPlaceholderInteger(new String[] { "removedelay", "rd", "r" }, 200, new String[0]);
/*  64 */     this.velocity = paramMythicLineConfig.getPlaceholderFloat(new String[] { "velocity", "v" }, 20.0F, new String[0]);
/*  65 */     this.scale = paramMythicLineConfig.getPlaceholderFloat(new String[] { "statsscale", "ss" }, 1.0F, new String[0]);
/*     */     
/*  67 */     this.fullEvent = paramMythicLineConfig.getBoolean(new String[] { "fullevent", "fe" }, false);
/*  68 */     this.scalePerArrow = paramMythicLineConfig.getBoolean(new String[] { "scaleperarrow", "spa" }, false);
/*  69 */     this.fromOrigin = paramMythicLineConfig.getBoolean(new String[] { "fromorigin", "fo" }, false);
/*  70 */     this.allowPickup = paramMythicLineConfig.getBoolean(new String[] { "allowpickup", "ap" }, false);
/*     */ 
/*     */     
/*  73 */     String str = paramMythicLineConfig.getString(new String[] { "arrowitem", "item", "ai" }, null, new String[0]);
/*     */     
/*  75 */     if (str != null) {
/*     */       
/*  77 */       ProvidedUIFilter providedUIFilter = UIFilterManager.getUIFilter(str.replace("<&sp>", " ").replace("\"", ""), null);
/*     */       
/*  79 */       if (providedUIFilter != null && 
/*  80 */         providedUIFilter.isValid(null) && providedUIFilter.getParent().fullyDefinesItem()) {
/*  81 */         providedUIFilter.setAmount(Integer.valueOf(1));
/*     */ 
/*     */         
/*  84 */         this.arrowItem = providedUIFilter.getItemStack(null);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     this.xOffset = paramMythicLineConfig.getPlaceholderDouble(new String[] { "startxoffset", "sxo" }, 0.0D, new String[0]);
/*  95 */     this.yOffset = paramMythicLineConfig.getPlaceholderDouble(new String[] { "startyoffset", "syo" }, 3.0D, new String[0]);
/*  96 */     this.zOffset = paramMythicLineConfig.getPlaceholderDouble(new String[] { "startzoffset", "szo" }, 0.0D, new String[0]);
/*  97 */     this.fOffset = paramMythicLineConfig.getPlaceholderDouble(new String[] { "startfoffset", "sfo" }, 0.0D, new String[0]);
/*  98 */     this.sOffset = paramMythicLineConfig.getPlaceholderDouble(new String[] { "startsoffset", "sso" }, 0.0D, new String[0]); } @NotNull
/*     */   PlaceholderFloat velocity; @NotNull
/*     */   PlaceholderFloat scale; @NotNull
/*     */   PlaceholderDouble xOffset; @NotNull
/*     */   PlaceholderDouble yOffset; @NotNull
/*     */   PlaceholderDouble zOffset; @NotNull
/*     */   PlaceholderDouble fOffset; @NotNull
/*     */   PlaceholderDouble sOffset; @Nullable
/* 106 */   ItemStack arrowItem; public SkillResult castAtLocation(SkillMetadata paramSkillMetadata, AbstractLocation paramAbstractLocation) { if (paramSkillMetadata.getCaster().getEntity().getBukkitEntity() instanceof Player) {
/*     */ 
/*     */       
/* 109 */       executeMIVolley(paramSkillMetadata.getCaster(), paramSkillMetadata, paramAbstractLocation, this.amount.get((PlaceholderMeta)paramSkillMetadata), this.velocity.get((PlaceholderMeta)paramSkillMetadata) * 0.1F, this.spread.get((PlaceholderMeta)paramSkillMetadata), this.fireTicks.get((PlaceholderMeta)paramSkillMetadata), this.removeDelay.get((PlaceholderMeta)paramSkillMetadata), this.scale);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 114 */       SkillAdapter.get().executeVolley(paramSkillMetadata.getCaster(), paramAbstractLocation, this.amount.get((PlaceholderMeta)paramSkillMetadata), this.velocity.get((PlaceholderMeta)paramSkillMetadata) * 0.1F, this.spread.get((PlaceholderMeta)paramSkillMetadata), this.fireTicks.get((PlaceholderMeta)paramSkillMetadata), this.removeDelay.get((PlaceholderMeta)paramSkillMetadata));
/*     */     } 
/* 116 */     return SkillResult.SUCCESS; }
/*     */   
/*     */   boolean fullEvent; boolean scalePerArrow; boolean fromOrigin;
/*     */   boolean allowPickup;
/*     */   static boolean syncEventBlock;
/*     */   
/*     */   public SkillResult castAtEntity(SkillMetadata paramSkillMetadata, AbstractEntity paramAbstractEntity) {
/* 123 */     if (paramSkillMetadata.getCaster().getEntity().getBukkitEntity() instanceof Player) {
/*     */ 
/*     */       
/* 126 */       executeMIVolley(paramSkillMetadata.getCaster(), paramSkillMetadata, paramAbstractEntity.getLocation(), this.amount.get((PlaceholderMeta)paramSkillMetadata, paramAbstractEntity), this.velocity.get((PlaceholderMeta)paramSkillMetadata) * 0.1F, this.spread.get((PlaceholderMeta)paramSkillMetadata), this.fireTicks.get((PlaceholderMeta)paramSkillMetadata), this.removeDelay.get((PlaceholderMeta)paramSkillMetadata), this.scale);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 131 */       SkillAdapter.get().executeVolley(paramSkillMetadata.getCaster(), paramAbstractEntity.getLocation(), this.amount.get((PlaceholderMeta)paramSkillMetadata, paramAbstractEntity), this.velocity.get((PlaceholderMeta)paramSkillMetadata) * 0.1F, this.spread.get((PlaceholderMeta)paramSkillMetadata), this.fireTicks.get((PlaceholderMeta)paramSkillMetadata), this.removeDelay.get((PlaceholderMeta)paramSkillMetadata));
/*     */     } 
/* 133 */     return SkillResult.SUCCESS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void executeMIVolley(@NotNull SkillCaster paramSkillCaster, @NotNull SkillMetadata paramSkillMetadata, @NotNull AbstractLocation paramAbstractLocation, int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3, @NotNull PlaceholderFloat paramPlaceholderFloat) {
/* 140 */     if (syncEventBlock) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 145 */     if (!(paramSkillCaster.getEntity().getBukkitEntity() instanceof Player)) {
/*     */       return;
/*     */     }
/* 148 */     Player player = (Player)paramSkillCaster.getEntity().getBukkitEntity();
/*     */ 
/*     */     
/* 151 */     Location location1 = BukkitAdapter.adapt(paramAbstractLocation);
/* 152 */     Location location2 = BukkitAdapter.adapt(this.fromOrigin ? paramSkillMetadata.getOrigin() : paramSkillCaster.getLocation()).clone();
/*     */ 
/*     */     
/* 155 */     Vector vector1 = location2.getDirection().normalize();
/* 156 */     Vector vector2 = (new Vector(-vector1.getZ(), 1.0E-5D, vector1.getX())).normalize();
/*     */     
/* 158 */     double d1 = this.fOffset.get((PlaceholderMeta)paramSkillMetadata);
/* 159 */     double d2 = this.sOffset.get((PlaceholderMeta)paramSkillMetadata);
/*     */     
/* 161 */     location2.setX(location2.getX() + this.xOffset.get((PlaceholderMeta)paramSkillMetadata) + vector1.getX() * d1 + vector2.getX() * d2);
/* 162 */     location2.setY(location2.getY() + this.yOffset.get((PlaceholderMeta)paramSkillMetadata) + vector1.getY() * d1 + vector2.getY() * d2);
/* 163 */     location2.setZ(location2.getZ() + this.zOffset.get((PlaceholderMeta)paramSkillMetadata) + vector1.getZ() * d1 + vector2.getZ() * d2);
/*     */ 
/*     */ 
/*     */     
/* 167 */     Vector vector3 = location1.toVector().subtract(location2.toVector()).normalize();
/*     */ 
/*     */     
/* 170 */     ItemStack itemStack1 = player.getInventory().getItemInMainHand().clone();
/* 171 */     ItemStack itemStack2 = (this.arrowItem != null) ? this.arrowItem.clone() : new ItemStack(Material.ARROW);
/*     */     
/* 173 */     ItemUse itemUse = new ItemUse();
/*     */ 
/*     */     
/* 176 */     float f = paramPlaceholderFloat.get((PlaceholderMeta)paramSkillMetadata);
/*     */ 
/*     */     
/* 179 */     ArrayList<Arrow> arrayList = new ArrayList();
/* 180 */     for (byte b = 0; b < paramInt1; b++) {
/*     */ 
/*     */       
/* 183 */       Arrow arrow = player.getWorld().spawnArrow(location2, vector3, paramFloat1, paramFloat2 / 10.0F);
/* 184 */       arrow.setVelocity(arrow.getVelocity());
/*     */       
/* 186 */       if (this.allowPickup) {
/* 187 */         arrow.setPickupStatus(AbstractArrow.PickupStatus.ALLOWED);
/*     */       } else {
/* 189 */         arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
/*     */       } 
/*     */ 
/*     */       
/* 193 */       arrow.setShooter((ProjectileSource)player);
/*     */ 
/*     */       
/* 196 */       syncEventBlock = true;
/* 197 */       EntityShootBowEvent entityShootBowEvent = new EntityShootBowEvent((LivingEntity)player, itemStack1, itemStack2, (Entity)arrow, EquipmentSlot.HAND, f, false);
/* 198 */       if (this.fullEvent) {
/* 199 */         Bukkit.getPluginManager().callEvent((Event)entityShootBowEvent);
/*     */       } else {
/* 201 */         itemUse.handleCustomBows(entityShootBowEvent);
/*     */       } 
/* 203 */       syncEventBlock = false;
/*     */ 
/*     */       
/* 206 */       if (entityShootBowEvent.isCancelled()) {
/* 207 */         arrow.remove();
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 212 */         if (paramInt2 > 0) {
/* 213 */           arrow.setFireTicks(paramInt2);
/*     */         }
/*     */ 
/*     */         
/* 217 */         arrayList.add(arrow);
/*     */ 
/*     */         
/* 220 */         if (this.scalePerArrow) {
/* 221 */           f = paramPlaceholderFloat.get((PlaceholderMeta)paramSkillMetadata);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 226 */     Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)MMOItems.plugin, () -> { for (Arrow arrow : paramArrayList) arrow.remove();  paramArrayList.clear(); }paramInt3);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mythicmobs\mechanics\MMOItemsArrowVolleyMechanic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */