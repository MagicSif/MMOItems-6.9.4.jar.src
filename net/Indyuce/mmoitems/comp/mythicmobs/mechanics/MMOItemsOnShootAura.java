/*     */ package net.Indyuce.mmoitems.comp.mythicmobs.mechanics;
/*     */ 
/*     */ import io.lumine.mythic.api.adapters.AbstractEntity;
/*     */ import io.lumine.mythic.api.config.MythicLineConfig;
/*     */ import io.lumine.mythic.api.mobs.GenericCaster;
/*     */ import io.lumine.mythic.api.skills.IParentSkill;
/*     */ import io.lumine.mythic.api.skills.ITargetedEntitySkill;
/*     */ import io.lumine.mythic.api.skills.Skill;
/*     */ import io.lumine.mythic.api.skills.SkillCaster;
/*     */ import io.lumine.mythic.api.skills.SkillMetadata;
/*     */ import io.lumine.mythic.api.skills.SkillResult;
/*     */ import io.lumine.mythic.api.skills.placeholders.PlaceholderString;
/*     */ import io.lumine.mythic.bukkit.BukkitAdapter;
/*     */ import io.lumine.mythic.bukkit.MythicBukkit;
/*     */ import io.lumine.mythic.bukkit.utils.Events;
/*     */ import io.lumine.mythic.bukkit.utils.terminable.Terminable;
/*     */ import io.lumine.mythic.core.mobs.ActiveMob;
/*     */ import io.lumine.mythic.core.skills.SkillExecutor;
/*     */ import io.lumine.mythic.core.skills.auras.Aura;
/*     */ import io.lumine.mythic.core.skills.placeholders.PlaceholderMeta;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.event.item.SpecialWeaponAttackEvent;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.Gauntlet;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Crossbow;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Lute;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Musket;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Staff;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Whip;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class MMOItemsOnShootAura
/*     */   extends Aura implements ITargetedEntitySkill {
/*     */   @NotNull
/*     */   PlaceholderString skillName;
/*     */   @NotNull
/*  43 */   final ArrayList<UseItemTypes> auraWeapons = new ArrayList<>(); @NotNull
/*     */   String weaponTypes; @Nullable
/*     */   Skill metaskill;
/*     */   public MMOItemsOnShootAura(SkillExecutor paramSkillExecutor, String paramString, MythicLineConfig paramMythicLineConfig) {
/*  47 */     super(paramSkillExecutor, paramString, paramMythicLineConfig);
/*  48 */     this.skillName = paramMythicLineConfig.getPlaceholderString(new String[] { "skill", "s", "ondamagedskill", "ondamaged", "od", "onhitskill", "onhit", "oh", "meta", "m", "mechanics", "$", "()" }, "skill not found", new String[0]);
/*  49 */     this.weaponTypes = paramMythicLineConfig.getString(new String[] { "weapons", "weapon", "w" }, "MUSKET", new String[0]);
/*  50 */     this.metaskill = GetSkill(this.skillName.get());
/*  51 */     this.cancelEvent = paramMythicLineConfig.getBoolean(new String[] { "cancelevent", "ce" }, false);
/*     */ 
/*     */     
/*  54 */     ArrayList<String> arrayList = new ArrayList();
/*  55 */     if (this.weaponTypes.contains(",")) {
/*  56 */       arrayList.addAll(Arrays.asList(this.weaponTypes.split(",")));
/*     */     } else {
/*  58 */       arrayList.add(this.weaponTypes);
/*     */     } 
/*     */     
/*  61 */     for (String str : arrayList) {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/*  66 */         UseItemTypes useItemTypes = UseItemTypes.valueOf(str.toUpperCase());
/*     */ 
/*     */         
/*  69 */         this.auraWeapons.add(useItemTypes);
/*     */       }
/*  71 */       catch (IllegalArgumentException illegalArgumentException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  76 */     if (this.metaskill == null)
/*     */     {
/*     */ 
/*     */       
/*  80 */       (new BukkitRunnable()
/*     */         {
/*     */           public void run()
/*     */           {
/*  84 */             MMOItemsOnShootAura.this.metaskill = MMOItemsOnShootAura.GetSkill(MMOItemsOnShootAura.this.skillName.get());
/*     */           }
/*  87 */         }).runTaskLater((Plugin)MMOItems.plugin, 1L);
/*     */     }
/*     */   }
/*     */   
/*     */   boolean cancelEvent;
/*     */   boolean forceAsPower;
/*     */   
/*     */   public SkillResult castAtEntity(SkillMetadata paramSkillMetadata, AbstractEntity paramAbstractEntity) {
/*     */     GenericCaster genericCaster;
/*  96 */     if (MythicBukkit.inst().getMobManager().isActiveMob(paramAbstractEntity)) {
/*     */ 
/*     */ 
/*     */       
/* 100 */       ActiveMob activeMob = MythicBukkit.inst().getMobManager().getMythicMobInstance(paramAbstractEntity);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 107 */       genericCaster = new GenericCaster(paramAbstractEntity);
/*     */     } 
/*     */     
/* 110 */     new Tracker((SkillCaster)genericCaster, paramSkillMetadata, paramAbstractEntity);
/* 111 */     return SkillResult.SUCCESS;
/*     */   }
/*     */   
/*     */   private class Tracker extends Aura.AuraTracker implements IParentSkill, Runnable {
/*     */     public Tracker(SkillCaster param1SkillCaster, SkillMetadata param1SkillMetadata, AbstractEntity param1AbstractEntity) {
/* 116 */       super(MMOItemsOnShootAura.this, param1SkillCaster, param1AbstractEntity, param1SkillMetadata);
/* 117 */       start();
/*     */     }
/*     */     
/*     */     public void auraStart() {
/* 121 */       registerAuraComponent((Terminable)Events.subscribe(SpecialWeaponAttackEvent.class).filter(param1SpecialWeaponAttackEvent -> {
/*     */               if (!param1SpecialWeaponAttackEvent.getPlayer().getUniqueId().equals(((AbstractEntity)this.entity.get()).getUniqueId())) {
/*     */                 return false;
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               if (MMOItemsOnShootAura.this.auraWeapons.size() == 0) {
/*     */                 return true;
/*     */               }
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*     */               for (MMOItemsOnShootAura.UseItemTypes useItemTypes : MMOItemsOnShootAura.this.auraWeapons) {
/*     */                 if (useItemTypes.getInst().isInstance(param1SpecialWeaponAttackEvent.getWeapon())) {
/*     */                   return true;
/*     */                 }
/*     */               } 
/*     */ 
/*     */ 
/*     */               
/*     */               return false;
/* 145 */             }).handler(param1SpecialWeaponAttackEvent -> {
/*     */               SkillMetadata skillMetadata = this.skillMetadata.deepClone();
/*     */ 
/*     */               
/*     */               if (MMOItemsOnShootAura.this.metaskill == null) {
/*     */                 MMOItemsOnShootAura.this.metaskill = MMOItemsOnShootAura.GetSkill(MMOItemsOnShootAura.this.skillName.get((PlaceholderMeta)skillMetadata, skillMetadata.getCaster().getEntity()));
/*     */               }
/*     */ 
/*     */               
/*     */               AbstractEntity abstractEntity = BukkitAdapter.adapt((Entity)param1SpecialWeaponAttackEvent.getTarget());
/*     */ 
/*     */               
/*     */               skillMetadata.setTrigger(abstractEntity);
/*     */ 
/*     */               
/*     */               if (executeAuraSkill(Optional.ofNullable(MMOItemsOnShootAura.this.metaskill), skillMetadata)) {
/*     */                 consumeCharge();
/*     */ 
/*     */                 
/*     */                 if (MMOItemsOnShootAura.this.cancelEvent) {
/*     */                   param1SpecialWeaponAttackEvent.setCancelled(true);
/*     */                 }
/*     */               } 
/*     */             }));
/*     */       
/* 170 */       executeAuraSkill(MMOItemsOnShootAura.this.onStartSkill, this.skillMetadata);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Skill GetSkill(String paramString) {
/* 177 */     if (SkillExists(paramString)) {
/*     */       
/* 179 */       Optional<Skill> optional = MythicBukkit.inst().getSkillManager().getSkill(paramString);
/* 180 */       if (optional == null) {
/* 181 */         return null;
/*     */       }
/* 183 */       if (optional.isPresent()) {
/* 184 */         return optional.get();
/*     */       }
/*     */     } 
/*     */     
/* 188 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean SkillExists(String paramString) {
/* 193 */     if (paramString == null) {
/* 194 */       return false;
/*     */     }
/*     */     
/* 197 */     Optional<Skill> optional = MythicBukkit.inst().getSkillManager().getSkill(paramString);
/*     */ 
/*     */     
/* 200 */     if (optional.isPresent()) {
/*     */       
/*     */       try {
/*     */         
/* 204 */         Skill skill = optional.get();
/*     */ 
/*     */         
/* 207 */         return true;
/*     */       }
/* 209 */       catch (Exception exception) {
/*     */ 
/*     */         
/* 212 */         return false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 219 */     return false;
/*     */   }
/*     */   
/*     */   enum UseItemTypes
/*     */   {
/* 224 */     CROSSBOW((String)Crossbow.class),
/* 225 */     GAUNTLET((String)Gauntlet.class),
/* 226 */     LUTE((String)Lute.class),
/* 227 */     MUSKET((String)Musket.class),
/* 228 */     STAFF((String)Staff.class),
/* 229 */     WHIP((String)Whip.class);
/*     */     
/*     */     @NotNull
/*     */     final Class inst;
/*     */     
/*     */     @NotNull
/*     */     public Class getInst() {
/* 236 */       return this.inst;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     UseItemTypes(Class param1Class) {
/* 243 */       this.inst = param1Class;
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mythicmobs\mechanics\MMOItemsOnShootAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */