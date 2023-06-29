/*     */ package net.Indyuce.mmoitems.listener;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.player.PlayerItemDamageEvent;
/*     */ import org.bukkit.event.player.PlayerItemMendEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DurabilityListener
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void itemDamage(PlayerItemDamageEvent paramPlayerItemDamageEvent) {
/*  31 */     DurabilityItem durabilityItem = new DurabilityItem(paramPlayerItemDamageEvent.getPlayer(), paramPlayerItemDamageEvent.getItem());
/*  32 */     if (!durabilityItem.isValid()) {
/*     */       return;
/*     */     }
/*     */     
/*  36 */     durabilityItem.decreaseDurability(paramPlayerItemDamageEvent.getDamage());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     ItemStack itemStack = durabilityItem.toItem();
/*  43 */     if (itemStack == null) {
/*  44 */       paramPlayerItemDamageEvent.setDamage(999);
/*     */       
/*     */       return;
/*     */     } 
/*  48 */     paramPlayerItemDamageEvent.setCancelled(true);
/*  49 */     paramPlayerItemDamageEvent.getItem().setItemMeta(itemStack.getItemMeta());
/*     */   }
/*     */   
/*  52 */   private static final List<EntityDamageEvent.DamageCause> IGNORED_CAUSES = Arrays.asList(new EntityDamageEvent.DamageCause[] { EntityDamageEvent.DamageCause.DROWNING, EntityDamageEvent.DamageCause.SUICIDE, EntityDamageEvent.DamageCause.FALL, EntityDamageEvent.DamageCause.VOID, EntityDamageEvent.DamageCause.FIRE_TICK, EntityDamageEvent.DamageCause.SUFFOCATION, EntityDamageEvent.DamageCause.POISON, EntityDamageEvent.DamageCause.WITHER, EntityDamageEvent.DamageCause.STARVATION, EntityDamageEvent.DamageCause.MAGIC });
/*     */   
/*  54 */   private static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[] { EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET };
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void playerDamage(EntityDamageEvent paramEntityDamageEvent) {
/*  58 */     if (paramEntityDamageEvent.getEntityType() != EntityType.PLAYER || IGNORED_CAUSES.contains(paramEntityDamageEvent.getCause())) {
/*     */       return;
/*     */     }
/*  61 */     Player player = (Player)paramEntityDamageEvent.getEntity();
/*  62 */     int i = Math.max((int)paramEntityDamageEvent.getDamage() / 4, 1);
/*  63 */     for (EquipmentSlot equipmentSlot : ARMOR_SLOTS) {
/*  64 */       if (hasItem(player, equipmentSlot))
/*  65 */         handleUndamageableItem(player.getInventory().getItem(equipmentSlot), player, equipmentSlot, i); 
/*     */     } 
/*     */   }
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void playerMeleeAttack(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
/*  70 */     if (paramEntityDamageByEntityEvent.getDamage() == 0.0D || paramEntityDamageByEntityEvent.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK || !(paramEntityDamageByEntityEvent.getEntity() instanceof org.bukkit.entity.LivingEntity) || 
/*  71 */       !(paramEntityDamageByEntityEvent.getDamager() instanceof Player) || paramEntityDamageByEntityEvent.getEntity().hasMetadata("NPC") || paramEntityDamageByEntityEvent.getDamager().hasMetadata("NPC"))
/*     */       return; 
/*  73 */     Player player = (Player)paramEntityDamageByEntityEvent.getDamager();
/*  74 */     ItemStack itemStack = player.getInventory().getItemInMainHand();
/*     */     
/*  76 */     handleUndamageableItem(itemStack, player, EquipmentSlot.HAND, 1);
/*     */   }
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void playerBowAttack(EntityShootBowEvent paramEntityShootBowEvent) {
/*  81 */     if (!(paramEntityShootBowEvent.getEntity() instanceof Player))
/*     */       return; 
/*  83 */     Player player = (Player)paramEntityShootBowEvent.getEntity();
/*  84 */     ItemStack itemStack = paramEntityShootBowEvent.getBow();
/*     */     
/*  86 */     handleUndamageableItem(itemStack, player, EquipmentSlot.HAND, 1);
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*     */   public void mendEvent(PlayerItemMendEvent paramPlayerItemMendEvent) {
/*  91 */     DurabilityItem durabilityItem = new DurabilityItem(paramPlayerItemMendEvent.getPlayer(), paramPlayerItemMendEvent.getItem());
/*  92 */     if (durabilityItem.isValid()) {
/*  93 */       paramPlayerItemMendEvent.getItem().setItemMeta(durabilityItem.addDurability(paramPlayerItemMendEvent.getRepairAmount()).toItem().getItemMeta());
/*  94 */       paramPlayerItemMendEvent.setCancelled(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void handleUndamageableItem(ItemStack paramItemStack, Player paramPlayer, EquipmentSlot paramEquipmentSlot, int paramInt) {
/* 103 */     if (paramItemStack.getType().getMaxDurability() != 0) {
/*     */       return;
/*     */     }
/* 106 */     DurabilityItem durabilityItem = new DurabilityItem(paramPlayer, paramItemStack);
/* 107 */     if (!durabilityItem.isValid()) {
/*     */       return;
/*     */     }
/* 110 */     durabilityItem.decreaseDurability(paramInt);
/*     */     
/* 112 */     ItemStack itemStack = durabilityItem.toItem();
/* 113 */     if (itemStack == null) {
/* 114 */       paramPlayer.getInventory().setItem(paramEquipmentSlot, null);
/* 115 */       paramPlayer.getWorld().playSound(paramPlayer.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
/*     */       
/*     */       return;
/*     */     } 
/* 119 */     paramPlayer.getInventory().getItem(paramEquipmentSlot).setItemMeta(itemStack.getItemMeta());
/*     */   }
/*     */   
/*     */   private boolean hasItem(Player paramPlayer, EquipmentSlot paramEquipmentSlot) {
/* 123 */     return (paramPlayer.getInventory().getItem(paramEquipmentSlot) != null && paramPlayer.getInventory().getItem(paramEquipmentSlot).getType() != Material.AIR);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\DurabilityListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */