/*     */ package net.Indyuce.mmoitems.listener;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.event.PlayerAttackEvent;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.damage.MeleeAttackMetadata;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*     */ import org.bukkit.inventory.ItemStack;
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
/*     */ public class DisabledItemsListener
/*     */   implements Listener
/*     */ {
/*     */   private final MMOItems plugin;
/*     */   
/*     */   public DisabledItemsListener(MMOItems paramMMOItems) {
/*  39 */     this.plugin = paramMMOItems;
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void rightClickEffects(PlayerInteractEvent paramPlayerInteractEvent) {
/*  44 */     if (!paramPlayerInteractEvent.hasItem())
/*     */       return; 
/*  46 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramPlayerInteractEvent.getItem());
/*  47 */     if (MMOUtils.hasBeenRemoved(nBTItem))
/*  48 */       paramPlayerInteractEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void meleeAttacks(PlayerAttackEvent paramPlayerAttackEvent) {
/*  53 */     if (!(paramPlayerAttackEvent.getAttack() instanceof MeleeAttackMetadata))
/*     */       return; 
/*  55 */     ItemStack itemStack = paramPlayerAttackEvent.getPlayer().getInventory().getItem(((MeleeAttackMetadata)paramPlayerAttackEvent.getAttack()).getHand().toBukkit());
/*  56 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack);
/*  57 */     if (MMOUtils.hasBeenRemoved(nBTItem))
/*  58 */       paramPlayerAttackEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void specialToolAbilities(BlockBreakEvent paramBlockBreakEvent) {
/*  63 */     Player player = paramBlockBreakEvent.getPlayer();
/*  64 */     if (player.getGameMode() == GameMode.CREATIVE) {
/*     */       return;
/*     */     }
/*  67 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand());
/*  68 */     if (MMOUtils.hasBeenRemoved(nBTItem))
/*  69 */       paramBlockBreakEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void rightClickWeaponInteractions(PlayerInteractEntityEvent paramPlayerInteractEntityEvent) {
/*  74 */     Player player = paramPlayerInteractEntityEvent.getPlayer();
/*  75 */     if (!(paramPlayerInteractEntityEvent.getRightClicked() instanceof org.bukkit.entity.LivingEntity)) {
/*     */       return;
/*     */     }
/*  78 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand());
/*  79 */     if (MMOUtils.hasBeenRemoved(nBTItem))
/*  80 */       paramPlayerInteractEntityEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void gemStonesAndItemStacks(InventoryClickEvent paramInventoryClickEvent) {
/*  85 */     Player player = (Player)paramInventoryClickEvent.getWhoClicked();
/*  86 */     if (paramInventoryClickEvent.getAction() != InventoryAction.SWAP_WITH_CURSOR) {
/*     */       return;
/*     */     }
/*  89 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramInventoryClickEvent.getCursor());
/*  90 */     if (MMOUtils.hasBeenRemoved(nBTItem))
/*  91 */       paramInventoryClickEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void handleCustomBows(EntityShootBowEvent paramEntityShootBowEvent) {
/*  96 */     if (!(paramEntityShootBowEvent.getProjectile() instanceof org.bukkit.entity.Arrow) || !(paramEntityShootBowEvent.getEntity() instanceof Player)) {
/*     */       return;
/*     */     }
/*  99 */     NBTItem nBTItem = NBTItem.get(paramEntityShootBowEvent.getBow());
/* 100 */     if (MMOUtils.hasBeenRemoved(nBTItem))
/* 101 */       paramEntityShootBowEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST)
/*     */   public void handleVanillaEatenConsumables(PlayerItemConsumeEvent paramPlayerItemConsumeEvent) {
/* 106 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramPlayerItemConsumeEvent.getItem());
/* 107 */     if (MMOUtils.hasBeenRemoved(nBTItem))
/* 108 */       paramPlayerItemConsumeEvent.setCancelled(true); 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\DisabledItemsListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */