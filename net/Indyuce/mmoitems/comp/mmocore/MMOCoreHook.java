/*     */ package net.Indyuce.mmoitems.comp.mmocore;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.event.SynchronizedDataLoadEvent;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.Locale;
/*     */ import net.Indyuce.mmocore.MMOCore;
/*     */ import net.Indyuce.mmocore.api.event.PlayerChangeClassEvent;
/*     */ import net.Indyuce.mmocore.api.event.PlayerLevelUpEvent;
/*     */ import net.Indyuce.mmocore.api.event.PlayerResourceUpdateEvent;
/*     */ import net.Indyuce.mmocore.api.player.PlayerData;
/*     */ import net.Indyuce.mmocore.api.player.attribute.PlayerAttribute;
/*     */ import net.Indyuce.mmocore.api.player.stats.StatType;
/*     */ import net.Indyuce.mmocore.experience.EXPSource;
/*     */ import net.Indyuce.mmocore.experience.Profession;
/*     */ import net.Indyuce.mmocore.experience.source.RepairItemExperienceSource;
/*     */ import net.Indyuce.mmocore.manager.profession.ExperienceSourceManager;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.event.item.ItemCustomRepairEvent;
/*     */ import net.Indyuce.mmoitems.api.event.item.RepairItemEvent;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.stat.ExtraAttribute;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.stat.RequiredAttribute;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.stat.RequiredProfession;
/*     */ import net.Indyuce.mmoitems.comp.rpg.RPGHandler;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MMOCoreHook
/*     */   implements RPGHandler, Listener
/*     */ {
/*     */   public MMOCoreHook() {
/*  43 */     for (PlayerAttribute playerAttribute : MMOCore.plugin.attributeManager.getAll()) {
/*  44 */       MMOItems.plugin.getStats().register((ItemStat)new RequiredAttribute(playerAttribute));
/*  45 */       MMOItems.plugin.getStats().register((ItemStat)new ExtraAttribute(playerAttribute));
/*     */     } 
/*     */     
/*  48 */     for (Profession profession : MMOCore.plugin.professionManager.getAll()) {
/*     */ 
/*     */       
/*  51 */       MMOItems.plugin.getStats().register((ItemStat)new DoubleStat((StatType.ADDITIONAL_EXPERIENCE.name() + '_' + profession.getId())
/*  52 */             .replace('-', '_').replace(' ', '_').toUpperCase(Locale.ROOT), VersionMaterial.EXPERIENCE_BOTTLE
/*  53 */             .toMaterial(), profession.getName() + ' ' + "Additional Experience (MMOCore)", new String[] { "Additional MMOCore profession " + profession
/*  54 */               .getName() + " experience in %." }, new String[] { "!block", "all" }, new org.bukkit.Material[0]));
/*  55 */       MMOItems.plugin.getStats().register((ItemStat)new RequiredProfession(profession));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshStats(PlayerData paramPlayerData) {}
/*     */ 
/*     */   
/*     */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/*  65 */     return new MMOCoreRPGPlayer(paramPlayerData);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void updateInventoryOnLevelUp(PlayerLevelUpEvent paramPlayerLevelUpEvent) {
/*  70 */     PlayerData.get((OfflinePlayer)paramPlayerLevelUpEvent.getPlayer()).getInventory().scheduleUpdate();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void updateInventoryOnClassChange(PlayerChangeClassEvent paramPlayerChangeClassEvent) {
/*  75 */     PlayerData.get((OfflinePlayer)paramPlayerChangeClassEvent.getPlayer()).getInventory().scheduleUpdate();
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
/*     */   @EventHandler
/*     */   public void updateInventoryOnLoad(SynchronizedDataLoadEvent paramSynchronizedDataLoadEvent) {
/*  88 */     if (paramSynchronizedDataLoadEvent.getManager().getOwningPlugin().equals(MMOCore.plugin) && 
/*  89 */       PlayerData.has(paramSynchronizedDataLoadEvent.getHolder().getPlayer()))
/*  90 */       PlayerData.get((OfflinePlayer)paramSynchronizedDataLoadEvent.getHolder().getPlayer()).getInventory().scheduleUpdate(); 
/*     */   }
/*     */   
/*     */   public static class MMOCoreRPGPlayer extends RPGPlayer {
/*     */     private final PlayerData data;
/*     */     
/*     */     public MMOCoreRPGPlayer(PlayerData param1PlayerData) {
/*  97 */       super(param1PlayerData);
/*     */       
/*  99 */       this.data = PlayerData.get(param1PlayerData.getUniqueId());
/*     */     }
/*     */     
/*     */     public PlayerData getData() {
/* 103 */       return this.data;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getLevel() {
/* 108 */       return this.data.getLevel();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getClassName() {
/* 113 */       return this.data.getProfess().getName();
/*     */     }
/*     */ 
/*     */     
/*     */     public double getMana() {
/* 118 */       return this.data.getMana();
/*     */     }
/*     */ 
/*     */     
/*     */     public double getStamina() {
/* 123 */       return this.data.getStamina();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setMana(double param1Double) {
/* 128 */       this.data.setMana(param1Double);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setStamina(double param1Double) {
/* 133 */       this.data.setStamina(param1Double);
/*     */     }
/*     */ 
/*     */     
/*     */     public void giveMana(double param1Double) {
/* 138 */       this.data.giveMana(param1Double, PlayerResourceUpdateEvent.UpdateReason.OTHER);
/*     */     }
/*     */ 
/*     */     
/*     */     public void giveStamina(double param1Double) {
/* 143 */       this.data.giveStamina(param1Double, PlayerResourceUpdateEvent.UpdateReason.OTHER);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*     */   public void handleVanillaRepairs(RepairItemEvent paramRepairItemEvent) {
/* 152 */     ExperienceSourceManager experienceSourceManager = MMOCore.plugin.experience.getManager(RepairItemExperienceSource.class);
/* 153 */     if (experienceSourceManager == null) {
/*     */       return;
/*     */     }
/* 156 */     ItemStack itemStack = paramRepairItemEvent.getTargetItem().getItem();
/* 157 */     if (!MMOCore.plugin.smithingManager.hasExperience(itemStack.getType())) {
/*     */       return;
/*     */     }
/* 160 */     Player player = paramRepairItemEvent.getPlayer();
/* 161 */     PlayerData playerData = PlayerData.get((OfflinePlayer)player);
/* 162 */     int i = Math.min(paramRepairItemEvent.getRepaired(), ((Damageable)itemStack.getItemMeta()).getDamage());
/*     */     
/* 164 */     for (RepairItemExperienceSource repairItemExperienceSource : experienceSourceManager.getSources()) {
/* 165 */       if (repairItemExperienceSource.matches(playerData, itemStack)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 171 */         double d = MMOCore.plugin.smithingManager.getBaseExperience(itemStack.getType()) * i / 100.0D;
/* 172 */         repairItemExperienceSource.getDispenser().giveExperience(playerData, d, playerData.getPlayer().getLocation(), EXPSource.SOURCE);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*     */   public void handleCustomRepairs(ItemCustomRepairEvent paramItemCustomRepairEvent) {
/* 178 */     ExperienceSourceManager experienceSourceManager = MMOCore.plugin.experience.getManager(RepairItemExperienceSource.class);
/* 179 */     if (experienceSourceManager == null) {
/*     */       return;
/*     */     }
/* 182 */     ItemStack itemStack = paramItemCustomRepairEvent.getSourceItem().getNBTItem().getItem();
/* 183 */     if (!MMOCore.plugin.smithingManager.hasExperience(itemStack.getType())) {
/*     */       return;
/*     */     }
/* 186 */     Player player = paramItemCustomRepairEvent.getPlayer();
/* 187 */     PlayerData playerData = PlayerData.get((OfflinePlayer)player);
/* 188 */     int i = Math.min(paramItemCustomRepairEvent.getDurabilityIncrease(), paramItemCustomRepairEvent.getSourceItem().getMaxDurability() - paramItemCustomRepairEvent.getSourceItem().getDurability());
/*     */     
/* 190 */     for (RepairItemExperienceSource repairItemExperienceSource : experienceSourceManager.getSources()) {
/* 191 */       if (repairItemExperienceSource.matches(playerData, itemStack)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 197 */         double d = MMOCore.plugin.smithingManager.getBaseExperience(itemStack.getType()) * i / 100.0D;
/* 198 */         repairItemExperienceSource.getDispenser().giveExperience(playerData, d, playerData.getPlayer().getLocation(), EXPSource.SOURCE);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\MMOCoreHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */