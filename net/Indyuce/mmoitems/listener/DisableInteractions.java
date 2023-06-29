/*     */ package net.Indyuce.mmoitems.listener;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.enchantment.EnchantItemEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.inventory.CraftItemEvent;
/*     */ import org.bukkit.event.inventory.FurnaceSmeltEvent;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DisableInteractions
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void itemDropping(PlayerDropItemEvent paramPlayerDropItemEvent) {
/*  37 */     ItemStack itemStack = paramPlayerDropItemEvent.getItemDrop().getItemStack();
/*     */     
/*  39 */     if (isDisabled(NBTItem.get(itemStack), "drop")) {
/*  40 */       paramPlayerDropItemEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void anvilInteractions(InventoryClickEvent paramInventoryClickEvent) {
/*  46 */     Inventory inventory = paramInventoryClickEvent.getClickedInventory();
/*  47 */     if (inventory == null || inventory.getType() != InventoryType.ANVIL || paramInventoryClickEvent.getSlotType() != InventoryType.SlotType.RESULT) {
/*     */       return;
/*     */     }
/*  50 */     if (isDisabled(NBTItem.get(paramInventoryClickEvent.getCurrentItem()), "repair")) {
/*  51 */       paramInventoryClickEvent.setCancelled(true);
/*  52 */     } else if (inventory.getItem(1) != null && isDisabled(NBTItem.get(inventory.getItem(1)), "repair")) {
/*  53 */       paramInventoryClickEvent.setCancelled(true);
/*     */     } 
/*     */   }
/*     */   @EventHandler
/*     */   public void grindstoneInteractions(InventoryClickEvent paramInventoryClickEvent) {
/*  58 */     if (MythicLib.plugin.getVersion().isBelowOrEqual(new int[] { 1, 13 })) {
/*     */       return;
/*     */     }
/*  61 */     Inventory inventory = paramInventoryClickEvent.getClickedInventory();
/*  62 */     if (inventory == null || inventory.getType() != InventoryType.GRINDSTONE || paramInventoryClickEvent.getSlotType() != InventoryType.SlotType.RESULT) {
/*     */       return;
/*     */     }
/*  65 */     if (isDisabled(NBTItem.get(inventory.getItem(0)), "repair") || isDisabled(NBTItem.get(inventory.getItem(1)), "repair"))
/*  66 */       paramInventoryClickEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void smithingTableInteractions(InventoryClickEvent paramInventoryClickEvent) {
/*  71 */     if (MythicLib.plugin.getVersion().isBelowOrEqual(new int[] { 1, 15 })) {
/*     */       return;
/*     */     }
/*  74 */     Inventory inventory = paramInventoryClickEvent.getClickedInventory();
/*  75 */     if (inventory == null || inventory.getType() != InventoryType.SMITHING || paramInventoryClickEvent.getSlotType() != InventoryType.SlotType.RESULT) {
/*     */       return;
/*     */     }
/*  78 */     if (isDisabled(NBTItem.get(inventory.getItem(0)), "smith") || isDisabled(NBTItem.get(inventory.getItem(1)), "smith"))
/*  79 */       paramInventoryClickEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void enchantTablesInteractions(EnchantItemEvent paramEnchantItemEvent) {
/*  84 */     if (isDisabled(NBTItem.get(paramEnchantItemEvent.getItem()), "enchant"))
/*  85 */       paramEnchantItemEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void furnaceInteractions(FurnaceSmeltEvent paramFurnaceSmeltEvent) {
/*  90 */     if (isDisabled(NBTItem.get(paramFurnaceSmeltEvent.getSource()), "smelt")) {
/*  91 */       paramFurnaceSmeltEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOW)
/*     */   public void clickInteractions(PlayerInteractEvent paramPlayerInteractEvent) {
/* 102 */     if (!paramPlayerInteractEvent.hasItem()) {
/*     */       return;
/*     */     }
/* 105 */     NBTItem nBTItem = NBTItem.get(paramPlayerInteractEvent.getItem());
/* 106 */     if (nBTItem.getBoolean("MMOITEMS_DISABLE_INTERACTION") || nBTItem.hasTag("MMOITEMS_UNIDENTIFIED_ITEM")) {
/* 107 */       paramPlayerInteractEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOW)
/*     */   public void miningInteractions(BlockBreakEvent paramBlockBreakEvent) {
/* 115 */     NBTItem nBTItem = NBTItem.get(paramBlockBreakEvent.getPlayer().getInventory().getItemInMainHand());
/* 116 */     if (nBTItem.hasTag("MMOITEMS_UNIDENTIFIED_ITEM"))
/* 117 */       paramBlockBreakEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void entityInteractions(PlayerInteractEntityEvent paramPlayerInteractEntityEvent) {
/* 122 */     if (paramPlayerInteractEntityEvent.getRightClicked() instanceof org.bukkit.entity.ArmorStand) {
/*     */       return;
/*     */     }
/* 125 */     NBTItem nBTItem = NBTItem.get((paramPlayerInteractEntityEvent.getHand() == EquipmentSlot.OFF_HAND) ? paramPlayerInteractEntityEvent.getPlayer().getInventory().getItemInOffHand() : 
/* 126 */         paramPlayerInteractEntityEvent.getPlayer().getInventory().getItemInMainHand());
/* 127 */     if (nBTItem.getBoolean("MMOITEMS_DISABLE_INTERACTION"))
/* 128 */       paramPlayerInteractEntityEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void consumeInteractions(PlayerItemConsumeEvent paramPlayerItemConsumeEvent) {
/* 133 */     NBTItem nBTItem = NBTItem.get(paramPlayerItemConsumeEvent.getItem());
/* 134 */     if (nBTItem.getBoolean("MMOITEMS_DISABLE_INTERACTION"))
/* 135 */       paramPlayerItemConsumeEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void workbenchInteractions(CraftItemEvent paramCraftItemEvent) {
/* 140 */     if (paramCraftItemEvent.getRecipe() instanceof Keyed && (
/* 141 */       (Keyed)paramCraftItemEvent.getRecipe()).getKey().getNamespace().equals("mmoitems")) {
/* 142 */       String str = NBTItem.get(paramCraftItemEvent.getCurrentItem()).getString("MMOITEMS_CRAFT_PERMISSION");
/* 143 */       if (!str.isEmpty() && !paramCraftItemEvent.getWhoClicked().hasPermission(str)) {
/* 144 */         paramCraftItemEvent.setCancelled(true);
/*     */       }
/*     */       return;
/*     */     } 
/* 148 */     for (ItemStack itemStack : paramCraftItemEvent.getInventory().getMatrix()) {
/* 149 */       if (isDisabled(NBTItem.get(itemStack), "craft")) {
/* 150 */         paramCraftItemEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 155 */     if (MMOItems.plugin.getConfig().getStringList("disable-vanilla-recipes").contains(paramCraftItemEvent.getCurrentItem().getType().name()))
/* 156 */       paramCraftItemEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void shootBowInteractions(EntityShootBowEvent paramEntityShootBowEvent) {
/* 161 */     if (!(paramEntityShootBowEvent.getEntity() instanceof Player)) {
/*     */       return;
/*     */     }
/* 164 */     DurabilityItem durabilityItem = new DurabilityItem(((Player)paramEntityShootBowEvent.getEntity()).getPlayer(), paramEntityShootBowEvent.getBow());
/*     */ 
/*     */     
/* 167 */     if (durabilityItem.isBroken()) {
/* 168 */       paramEntityShootBowEvent.setCancelled(true);
/*     */     }
/* 170 */     Player player = (Player)paramEntityShootBowEvent.getEntity();
/* 171 */     int i = firstArrow(player);
/* 172 */     if (i < 0) {
/*     */       return;
/*     */     }
/* 175 */     ItemStack itemStack = player.getInventory().getItem(i);
/* 176 */     if (itemStack == null) {
/*     */       return;
/*     */     }
/*     */     
/* 180 */     NBTItem nBTItem = NBTItem.get(itemStack);
/* 181 */     if (nBTItem.hasType() && (MMOItems.plugin.getConfig().getBoolean("disable-interactions.arrow-shooting") || nBTItem
/* 182 */       .getBoolean("MMOITEMS_DISABLE_ARROW_SHOOTING")))
/* 183 */       paramEntityShootBowEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOW)
/*     */   public void attackInteractions(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
/* 188 */     if (paramEntityDamageByEntityEvent.getDamage() == 0.0D || paramEntityDamageByEntityEvent.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK || !(paramEntityDamageByEntityEvent.getEntity() instanceof org.bukkit.entity.LivingEntity) || 
/* 189 */       !(paramEntityDamageByEntityEvent.getDamager() instanceof Player) || paramEntityDamageByEntityEvent.getEntity().hasMetadata("NPC") || paramEntityDamageByEntityEvent.getDamager().hasMetadata("NPC"))
/*     */       return; 
/* 191 */     Player player = (Player)paramEntityDamageByEntityEvent.getDamager();
/* 192 */     ItemStack itemStack = player.getInventory().getItemInMainHand();
/*     */     
/* 194 */     DurabilityItem durabilityItem = new DurabilityItem(player, itemStack);
/*     */ 
/*     */     
/* 197 */     if (durabilityItem.isBroken()) {
/* 198 */       paramEntityDamageByEntityEvent.setCancelled(true);
/*     */     }
/*     */     
/* 201 */     if (durabilityItem.getNBTItem().hasTag("MMOITEMS_UNIDENTIFIED_ITEM")) {
/* 202 */       paramEntityDamageByEntityEvent.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private int firstArrow(Player paramPlayer) {
/* 208 */     if (paramPlayer.getInventory().getItemInOffHand() != null && paramPlayer.getInventory().getItemInOffHand().getType().name().contains("ARROW")) {
/* 209 */       return 40;
/*     */     }
/*     */     
/* 212 */     ItemStack[] arrayOfItemStack = paramPlayer.getInventory().getStorageContents();
/* 213 */     for (byte b = 0; b < arrayOfItemStack.length; b++) {
/* 214 */       ItemStack itemStack = arrayOfItemStack[b];
/* 215 */       if (itemStack != null && itemStack.getType().name().contains("ARROW"))
/* 216 */         return b; 
/*     */     } 
/* 218 */     return -1;
/*     */   }
/*     */   
/*     */   private boolean isDisabled(NBTItem paramNBTItem, String paramString) {
/* 222 */     return ((paramNBTItem.hasType() && MMOItems.plugin.getConfig().getBoolean("disable-interactions." + paramString)) || paramNBTItem
/* 223 */       .getBoolean("MMOITEMS_DISABLE_" + paramString.toUpperCase().replace("-", "_") + "ING"));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\DisableInteractions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */