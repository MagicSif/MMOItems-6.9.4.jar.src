/*     */ package net.Indyuce.mmoitems.listener;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.event.armorequip.ArmorEquipEvent;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*     */ import java.util.Iterator;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.DeathItemsHandler;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.interaction.util.InteractItem;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.Weapon;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.DeathDowngrading;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
/*     */ import org.bukkit.event.entity.ProjectileLaunchEvent;
/*     */ import org.bukkit.event.player.PlayerItemHeldEvent;
/*     */ import org.bukkit.event.player.PlayerRespawnEvent;
/*     */ import org.bukkit.event.player.PlayerSwapHandItemsEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerListener
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
/*     */   public void onDeathForUpgradeLoss(@NotNull PlayerDeathEvent paramPlayerDeathEvent) {
/*  41 */     if (!PlayerData.has(paramPlayerDeathEvent.getEntity())) {
/*     */       return;
/*     */     }
/*  44 */     (new DelayedDeathDowngrade(paramPlayerDeathEvent)).runTaskLater((Plugin)MMOItems.plugin, 3L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void keepItemsOnDeath(PlayerDeathEvent paramPlayerDeathEvent) {
/*  55 */     if (paramPlayerDeathEvent.getKeepInventory())
/*     */       return; 
/*  57 */     Player player = paramPlayerDeathEvent.getEntity();
/*  58 */     DeathItemsHandler deathItemsHandler = new DeathItemsHandler(player);
/*     */     
/*  60 */     Iterator<ItemStack> iterator = paramPlayerDeathEvent.getDrops().iterator();
/*  61 */     while (iterator.hasNext()) {
/*  62 */       ItemStack itemStack = iterator.next();
/*  63 */       NBTItem nBTItem = NBTItem.get(itemStack);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  70 */       if (((MMOItems.plugin.getLanguage()).keepSoulboundOnDeath && nBTItem.getBoolean("MMOITEMS_DISABLE_DEATH_DROP")) || (nBTItem
/*  71 */         .hasTag("MMOITEMS_SOULBOUND") && nBTItem.getString("MMOITEMS_SOULBOUND").contains(player.getUniqueId().toString()))) {
/*  72 */         iterator.remove();
/*  73 */         deathItemsHandler.registerItem(itemStack);
/*     */       } 
/*     */     } 
/*     */     
/*  77 */     deathItemsHandler.registerIfNecessary();
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onRespawn(PlayerRespawnEvent paramPlayerRespawnEvent) {
/*  82 */     DeathItemsHandler.readAndRemove(paramPlayerRespawnEvent.getPlayer());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void onArmorEquip(ArmorEquipEvent paramArmorEquipEvent) {
/*  87 */     if (paramArmorEquipEvent.getNewArmorPiece() == null) {
/*     */       return;
/*     */     }
/*  90 */     if (!PlayerData.get((OfflinePlayer)paramArmorEquipEvent.getPlayer()).getRPG().canUse(NBTItem.get(paramArmorEquipEvent.getNewArmorPiece()), true)) {
/*  91 */       paramArmorEquipEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void registerTridents(ProjectileLaunchEvent paramProjectileLaunchEvent) {
/* 104 */     if (!(paramProjectileLaunchEvent.getEntity() instanceof org.bukkit.entity.Trident) || !(paramProjectileLaunchEvent.getEntity().getShooter() instanceof Player)) {
/*     */       return;
/*     */     }
/* 107 */     InteractItem interactItem = new InteractItem((Player)paramProjectileLaunchEvent.getEntity().getShooter(), Material.TRIDENT);
/* 108 */     if (!interactItem.hasItem()) {
/*     */       return;
/*     */     }
/* 111 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(interactItem.getItem());
/* 112 */     Type type = Type.get(nBTItem.getType());
/* 113 */     PlayerData playerData = PlayerData.get((OfflinePlayer)paramProjectileLaunchEvent.getEntity().getShooter());
/*     */     
/* 115 */     if (type != null) {
/* 116 */       Weapon weapon = new Weapon(playerData, nBTItem);
/* 117 */       if (!weapon.checkItemRequirements() || !weapon.checkAndApplyWeaponCosts()) {
/* 118 */         paramProjectileLaunchEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 122 */       MMOItems.plugin.getEntities().registerCustomProjectile(nBTItem, playerData.getStats().newTemporary(EquipmentSlot.fromBukkit(interactItem.getSlot())), (Entity)paramProjectileLaunchEvent.getEntity(), 1.0D);
/*     */     } 
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
/*     */   @Deprecated
/*     */   @EventHandler
/*     */   public void registerInventoryUpdates1(PlayerSwapHandItemsEvent paramPlayerSwapHandItemsEvent) {
/* 137 */     PlayerData.get((OfflinePlayer)paramPlayerSwapHandItemsEvent.getPlayer()).getInventory().scheduleUpdate();
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
/*     */   @Deprecated
/*     */   @EventHandler
/*     */   public void registerInventoryUpdates2(PlayerItemHeldEvent paramPlayerItemHeldEvent) {
/* 151 */     PlayerData.get((OfflinePlayer)paramPlayerItemHeldEvent.getPlayer()).getInventory().scheduleUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class DelayedDeathDowngrade
/*     */     extends BukkitRunnable
/*     */   {
/*     */     @NotNull
/*     */     final PlayerDeathEvent event;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     DelayedDeathDowngrade(@NotNull PlayerDeathEvent param1PlayerDeathEvent) {
/* 174 */       this.event = param1PlayerDeathEvent;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 181 */       DeathDowngrading.playerDeathDowngrade(this.event.getEntity());
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\PlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */