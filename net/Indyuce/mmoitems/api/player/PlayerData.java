/*     */ package net.Indyuce.mmoitems.api.player;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*     */ import io.lumine.mythic.lib.api.player.MMOPlayerData;
/*     */ import io.lumine.mythic.lib.damage.AttackMetadata;
/*     */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*     */ import io.lumine.mythic.lib.player.cooldown.CooldownObject;
/*     */ import io.lumine.mythic.lib.player.modifier.ModifierSource;
/*     */ import io.lumine.mythic.lib.player.modifier.PlayerModifier;
/*     */ import io.lumine.mythic.lib.player.skill.PassiveSkill;
/*     */ import io.lumine.mythic.lib.skill.Skill;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ItemSet;
/*     */ import net.Indyuce.mmoitems.api.crafting.CraftingStatus;
/*     */ import net.Indyuce.mmoitems.api.event.RefreshInventoryEvent;
/*     */ import net.Indyuce.mmoitems.api.item.ItemReference;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*     */ import net.Indyuce.mmoitems.api.player.inventory.InventoryUpdateHandler;
/*     */ import net.Indyuce.mmoitems.comp.rpg.RPGHandler;
/*     */ import net.Indyuce.mmoitems.particle.api.ParticleRunnable;
/*     */ import net.Indyuce.mmoitems.stat.data.AbilityData;
/*     */ import net.Indyuce.mmoitems.stat.data.AbilityListData;
/*     */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*     */ import net.Indyuce.mmoitems.stat.data.PotionEffectData;
/*     */ import net.milkbowl.vault.permission.Permission;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PlayerData extends SynchronizedDataHolder implements Closeable {
/*     */   @NotNull
/*     */   private final MMOPlayerData mmoData;
/*  49 */   private final InventoryUpdateHandler inventory = new InventoryUpdateHandler(this); private RPGPlayer rpgPlayer;
/*  50 */   private final CraftingStatus craftingStatus = new CraftingStatus();
/*     */ 
/*     */   
/*  53 */   private final Map<PotionEffectType, PotionEffect> permanentEffects = new HashMap<>();
/*  54 */   private final Set<ParticleRunnable> itemParticles = new HashSet<>();
/*  55 */   private ParticleRunnable overridingItemParticles = null; private boolean encumbered = false;
/*     */   @Nullable
/*  57 */   private ItemSet.SetBonuses setBonuses = null;
/*     */   
/*     */   private final PlayerStats stats;
/*  60 */   private final Set<String> permissions = new HashSet<>();
/*     */   
/*     */   public PlayerData(@NotNull MMOPlayerData paramMMOPlayerData) {
/*  63 */     super(paramMMOPlayerData);
/*     */     
/*  65 */     this.mmoData = paramMMOPlayerData;
/*  66 */     this.rpgPlayer = MMOItems.plugin.getMainRPG().getInfo(this);
/*  67 */     this.stats = new PlayerStats(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  72 */     cancelRunnables();
/*     */   }
/*     */   
/*     */   public MMOPlayerData getMMOPlayerData() {
/*  76 */     return this.mmoData;
/*     */   }
/*     */   
/*     */   public UUID getUniqueId() {
/*  80 */     return this.mmoData.getUniqueId();
/*     */   }
/*     */   
/*     */   public boolean isOnline() {
/*  84 */     return this.mmoData.isOnline();
/*     */   }
/*     */   
/*     */   public Player getPlayer() {
/*  88 */     return this.mmoData.getPlayer();
/*     */   }
/*     */   
/*     */   public RPGPlayer getRPG() {
/*  92 */     return this.rpgPlayer;
/*     */   }
/*     */   
/*     */   public void cancelRunnables() {
/*  96 */     this.itemParticles.forEach(BukkitRunnable::cancel);
/*  97 */     if (this.overridingItemParticles != null)
/*  98 */       this.overridingItemParticles.cancel(); 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean areHandsFull() {
/* 103 */     return isEncumbered();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEncumbered() {
/* 113 */     NBTItem nBTItem1 = MythicLib.plugin.getVersion().getWrapper().getNBTItem(getPlayer().getInventory().getItemInMainHand());
/* 114 */     NBTItem nBTItem2 = MythicLib.plugin.getVersion().getWrapper().getNBTItem(getPlayer().getInventory().getItemInOffHand());
/*     */ 
/*     */     
/* 117 */     boolean bool1 = nBTItem1.getBoolean(ItemStats.TWO_HANDED.getNBTPath());
/* 118 */     boolean bool2 = nBTItem2.getBoolean(ItemStats.TWO_HANDED.getNBTPath());
/*     */ 
/*     */     
/* 121 */     boolean bool3 = (nBTItem1.getItem() != null && nBTItem1.getItem().getType() != Material.AIR && !nBTItem1.getBoolean(ItemStats.HANDWORN.getNBTPath())) ? true : false;
/* 122 */     boolean bool4 = (nBTItem2.getItem() != null && nBTItem2.getItem().getType() != Material.AIR && !nBTItem2.getBoolean(ItemStats.HANDWORN.getNBTPath())) ? true : false;
/*     */ 
/*     */     
/* 125 */     return ((bool1 && bool4) || (bool3 && bool2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRPGPlayer(RPGPlayer paramRPGPlayer) {
/* 134 */     this.rpgPlayer = paramRPGPlayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateInventory() {
/* 139 */     if (!this.mmoData.isOnline()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     this.inventory.getEquipped().clear();
/* 147 */     this.permanentEffects.clear();
/* 148 */     cancelRunnables();
/* 149 */     this.mmoData.getPassiveSkillMap().removeModifiers("MMOItemsItem");
/* 150 */     this.itemParticles.clear();
/* 151 */     this.overridingItemParticles = null;
/* 152 */     if (MMOItems.plugin.hasPermissions()) {
/* 153 */       Permission permission = MMOItems.plugin.getVault().getPermissions();
/* 154 */       this.permissions.forEach(paramString -> {
/*     */             if (paramPermission.has(getPlayer(), paramString)) {
/*     */               paramPermission.playerRemove(getPlayer(), paramString);
/*     */             }
/*     */           });
/*     */     } 
/* 160 */     this.permissions.clear();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     this.encumbered = isEncumbered();
/*     */ 
/*     */     
/* 169 */     for (EquippedItem equippedItem : MMOItems.plugin.getInventory().getInventory(getPlayer())) {
/* 170 */       NBTItem nBTItem = equippedItem.getNBT();
/* 171 */       if (nBTItem.getItem() == null || nBTItem.getItem().getType() == Material.AIR) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 178 */       if (!equippedItem.isPlacementLegal() || !getRPG().canUse(nBTItem, false, false)) {
/*     */         continue;
/*     */       }
/* 181 */       equippedItem.cacheItem();
/* 182 */       this.inventory.getEquipped().add(equippedItem);
/*     */     } 
/*     */ 
/*     */     
/* 186 */     Bukkit.getPluginManager().callEvent((Event)new RefreshInventoryEvent(this.inventory.getEquipped(), getPlayer(), this));
/*     */     
/* 188 */     for (Iterator<EquippedItem> iterator = this.inventory.getEquipped().iterator(); iterator.hasNext(); ) { EquippedItem equippedItem = iterator.next();
/* 189 */       VolatileMMOItem volatileMMOItem = equippedItem.getCached();
/*     */ 
/*     */       
/* 192 */       if (volatileMMOItem.hasData(ItemStats.ABILITIES)) {
/* 193 */         for (AbilityData abilityData : ((AbilityListData)volatileMMOItem.getData(ItemStats.ABILITIES)).getAbilities()) {
/* 194 */           ModifierSource modifierSource1 = equippedItem.getCached().getType().getModifierSource();
/* 195 */           this.mmoData.getPassiveSkillMap().addModifier((PlayerModifier)new PassiveSkill("MMOItemsItem", (Skill)abilityData, equippedItem.getSlot(), modifierSource1));
/*     */         } 
/*     */       }
/*     */       
/* 199 */       ModifierSource modifierSource = volatileMMOItem.getType().getModifierSource();
/* 200 */       EquipmentSlot equipmentSlot = equippedItem.getSlot();
/* 201 */       if (!EquipmentSlot.MAIN_HAND.isCompatible(modifierSource, equipmentSlot)) {
/*     */         continue;
/*     */       }
/*     */       
/* 205 */       if (volatileMMOItem.hasData(ItemStats.PERM_EFFECTS)) {
/* 206 */         ((PotionEffectListData)volatileMMOItem.getData(ItemStats.PERM_EFFECTS)).getEffects().forEach(paramPotionEffectData -> {
/*     */               if (getPermanentPotionEffectAmplifier(paramPotionEffectData.getType()) < paramPotionEffectData.getLevel() - 1) {
/*     */                 this.permanentEffects.put(paramPotionEffectData.getType(), paramPotionEffectData.toEffect());
/*     */               }
/*     */             });
/*     */       }
/* 212 */       if (volatileMMOItem.hasData(ItemStats.ITEM_PARTICLES)) {
/* 213 */         ParticleData particleData = (ParticleData)volatileMMOItem.getData(ItemStats.ITEM_PARTICLES);
/*     */         
/* 215 */         if (particleData.getType().hasPriority()) {
/* 216 */           if (this.overridingItemParticles == null)
/* 217 */             this.overridingItemParticles = particleData.start(this); 
/*     */         } else {
/* 219 */           this.itemParticles.add(particleData.start(this));
/*     */         } 
/*     */       } 
/*     */       
/* 223 */       if (MMOItems.plugin.hasPermissions() && volatileMMOItem.hasData(ItemStats.GRANTED_PERMISSIONS)) {
/* 224 */         Permission permission = MMOItems.plugin.getVault().getPermissions();
/* 225 */         this.permissions.addAll(((StringListData)volatileMMOItem.getData(ItemStats.GRANTED_PERMISSIONS)).getList());
/* 226 */         this.permissions.forEach(paramString -> {
/*     */               if (!paramPermission.has(getPlayer(), paramString)) {
/*     */                 paramPermission.playerAdd(getPlayer(), paramString);
/*     */               }
/*     */             });
/*     */       }  }
/*     */ 
/*     */     
/* 234 */     HashMap<Object, Object> hashMap = new HashMap<>();
/* 235 */     for (EquippedItem equippedItem : this.inventory.getEquipped()) {
/* 236 */       String str = equippedItem.getCached().getNBT().getString("MMOITEMS_ITEM_SET");
/* 237 */       ItemSet itemSet = MMOItems.plugin.getSets().get(str);
/* 238 */       if (itemSet == null) {
/*     */         continue;
/*     */       }
/* 241 */       hashMap.put(itemSet, Integer.valueOf(((Integer)hashMap.getOrDefault(itemSet, Integer.valueOf(0))).intValue() + 1));
/*     */     } 
/*     */ 
/*     */     
/* 245 */     this.setBonuses = null;
/* 246 */     for (Map.Entry<Object, Object> entry : hashMap.entrySet()) {
/*     */       
/* 248 */       if (this.setBonuses == null) {
/*     */         
/* 250 */         this.setBonuses = ((ItemSet)entry.getKey()).getBonuses(((Integer)entry.getValue()).intValue());
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 255 */       this.setBonuses.merge(((ItemSet)entry.getKey()).getBonuses(((Integer)entry.getValue()).intValue()));
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 260 */     if (this.setBonuses != null) {
/* 261 */       if (MMOItems.plugin.hasPermissions()) {
/* 262 */         Permission permission = MMOItems.plugin.getVault().getPermissions();
/* 263 */         this.permissions.addAll(this.setBonuses.getPermissions());
/* 264 */         for (String str : this.setBonuses.getPermissions()) {
/* 265 */           if (!permission.has(getPlayer(), str))
/* 266 */             permission.playerAdd(getPlayer(), str); 
/*     */         } 
/* 268 */       }  for (AbilityData abilityData : this.setBonuses.getAbilities())
/* 269 */         this.mmoData.getPassiveSkillMap().addModifier((PlayerModifier)new PassiveSkill("MMOItemsItem", (Skill)abilityData, EquipmentSlot.OTHER, ModifierSource.OTHER)); 
/* 270 */       for (ParticleData particleData : this.setBonuses.getParticles())
/* 271 */         this.itemParticles.add(particleData.start(this)); 
/* 272 */       for (PotionEffect potionEffect : this.setBonuses.getPotionEffects()) {
/* 273 */         if (getPermanentPotionEffectAmplifier(potionEffect.getType()) < potionEffect.getAmplifier()) {
/* 274 */           this.permanentEffects.put(potionEffect.getType(), potionEffect);
/*     */         }
/*     */       } 
/*     */     } 
/* 278 */     this.stats.updateStats();
/*     */ 
/*     */     
/* 281 */     MMOItems.plugin.getRPGs().forEach(paramRPGHandler -> paramRPGHandler.refreshStats(this));
/*     */ 
/*     */     
/* 284 */     this.inventory.helmet = getPlayer().getInventory().getHelmet();
/* 285 */     this.inventory.chestplate = getPlayer().getInventory().getChestplate();
/* 286 */     this.inventory.leggings = getPlayer().getInventory().getLeggings();
/* 287 */     this.inventory.boots = getPlayer().getInventory().getBoots();
/* 288 */     this.inventory.hand = getPlayer().getInventory().getItemInMainHand();
/* 289 */     this.inventory.offhand = getPlayer().getInventory().getItemInOffHand();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateStats() {
/* 295 */     this.permanentEffects.values().forEach(paramPotionEffect -> getPlayer().addPotionEffect(paramPotionEffect));
/*     */ 
/*     */     
/* 298 */     if (this.encumbered)
/* 299 */       getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 1, true, false)); 
/*     */   }
/*     */   
/*     */   public InventoryUpdateHandler getInventory() {
/* 303 */     return this.inventory;
/*     */   }
/*     */   
/*     */   public ItemSet.SetBonuses getSetBonuses() {
/* 307 */     return this.setBonuses;
/*     */   }
/*     */   
/*     */   public boolean hasSetBonuses() {
/* 311 */     return (this.setBonuses != null);
/*     */   }
/*     */   
/*     */   public CraftingStatus getCrafting() {
/* 315 */     return this.craftingStatus;
/*     */   }
/*     */   
/*     */   public int getPermanentPotionEffectAmplifier(PotionEffectType paramPotionEffectType) {
/* 319 */     return this.permanentEffects.containsKey(paramPotionEffectType) ? ((PotionEffect)this.permanentEffects.get(paramPotionEffectType)).getAmplifier() : -1;
/*     */   }
/*     */   
/*     */   public Set<String> getPermissions() {
/* 323 */     return this.permissions;
/*     */   }
/*     */   
/*     */   public Collection<PotionEffect> getPermanentPotionEffects() {
/* 327 */     return this.permanentEffects.values();
/*     */   }
/*     */   
/*     */   public PlayerStats getStats() {
/* 331 */     return this.stats;
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
/*     */   @Deprecated
/*     */   public void cast(@Nullable AttackMetadata paramAttackMetadata, @Nullable LivingEntity paramLivingEntity, @NotNull AbilityData paramAbilityData) {
/* 345 */     PlayerMetadata playerMetadata = getMMOPlayerData().getStatMap().cache(EquipmentSlot.MAIN_HAND);
/* 346 */     paramAbilityData.cast(new TriggerMetadata(playerMetadata, (Entity)paramLivingEntity, paramAttackMetadata));
/*     */   }
/*     */   
/*     */   public boolean isOnCooldown(CooldownType paramCooldownType) {
/* 350 */     return this.mmoData.getCooldownMap().isOnCooldown(paramCooldownType.name());
/*     */   }
/*     */   
/*     */   public void applyCooldown(CooldownType paramCooldownType, double paramDouble) {
/* 354 */     this.mmoData.getCooldownMap().applyCooldown(paramCooldownType.name(), paramDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean isOnCooldown(ItemReference paramItemReference) {
/* 362 */     return this.mmoData.getCooldownMap().isOnCooldown((CooldownObject)paramItemReference);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void applyItemCooldown(ItemReference paramItemReference, double paramDouble) {
/* 370 */     this.mmoData.getCooldownMap().applyCooldown((CooldownObject)paramItemReference, paramDouble);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public double getItemCooldown(ItemReference paramItemReference) {
/* 378 */     return this.mmoData.getCooldownMap().getInfo((CooldownObject)paramItemReference).getRemaining() / 1000.0D;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static PlayerData get(@NotNull OfflinePlayer paramOfflinePlayer) {
/* 383 */     return get(paramOfflinePlayer.getUniqueId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean has(Player paramPlayer) {
/* 392 */     return has(paramPlayer.getUniqueId());
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
/*     */   public static boolean has(UUID paramUUID) {
/* 404 */     return MMOItems.plugin.getPlayerDataManager().isLoaded(paramUUID);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static PlayerData get(UUID paramUUID) {
/* 409 */     return (PlayerData)MMOItems.plugin.getPlayerDataManager().get(paramUUID);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public static Collection<PlayerData> getLoaded() {
/* 415 */     return MMOItems.plugin.getPlayerDataManager().getLoaded();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum CooldownType
/*     */   {
/* 423 */     BASIC_ATTACK,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 428 */     ELEMENTAL_ATTACK,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 433 */     SPECIAL_ATTACK,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 443 */     BOUNCING_CRACK,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 449 */     SET_TYPE_ATTACK;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\player\PlayerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */