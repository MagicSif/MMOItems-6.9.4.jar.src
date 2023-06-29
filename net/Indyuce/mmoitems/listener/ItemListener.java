/*     */ package net.Indyuce.mmoitems.listener;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Collectors;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
/*     */ import net.Indyuce.mmoitems.api.util.MMOItemReforger;
/*     */ import net.Indyuce.mmoitems.listener.reforging.RFGKeepExternalSH;
/*     */ import net.Indyuce.mmoitems.listener.reforging.RFGKeepGems;
/*     */ import net.Indyuce.mmoitems.listener.reforging.RFGKeepModifications;
/*     */ import net.Indyuce.mmoitems.listener.reforging.RFGKeepName;
/*     */ import net.Indyuce.mmoitems.listener.reforging.RFGKeepRNG;
/*     */ import net.Indyuce.mmoitems.listener.reforging.RFGKeepSoulbound;
/*     */ import net.Indyuce.mmoitems.listener.reforging.RFGKeepUpgrades;
/*     */ import net.Indyuce.mmoitems.reforge.ReforgeReason;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*     */ import org.bukkit.event.inventory.CraftItemEvent;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.inventory.PrepareItemCraftEvent;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerJoinEvent;
/*     */ import org.bukkit.inventory.CraftingInventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ItemListener implements Listener {
/*     */   public ItemListener() {
/*  40 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepName(), (Plugin)MMOItems.plugin);
/*  41 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepLore(), (Plugin)MMOItems.plugin);
/*  42 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepEnchantments(), (Plugin)MMOItems.plugin);
/*  43 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepExternalSH(), (Plugin)MMOItems.plugin);
/*  44 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepGems(), (Plugin)MMOItems.plugin);
/*  45 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepModifications(), (Plugin)MMOItems.plugin);
/*  46 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepSoulbound(), (Plugin)MMOItems.plugin);
/*  47 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepUpgrades(), (Plugin)MMOItems.plugin);
/*  48 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepRNG(), (Plugin)MMOItems.plugin);
/*     */ 
/*     */     
/*  51 */     Bukkit.getPluginManager().registerEvents((Listener)new RFGKeepDurability(), (Plugin)MMOItems.plugin);
/*  52 */     Bukkit.getPluginManager().registerEvents((Listener)new RFFKeepAmount(), (Plugin)MMOItems.plugin);
/*  53 */     Bukkit.getPluginManager().registerEvents((Listener)new RFFKeepSkins(), (Plugin)MMOItems.plugin);
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   private void onItemCraftRepair(PrepareItemCraftEvent paramPrepareItemCraftEvent) {
/*  58 */     if (!(paramPrepareItemCraftEvent.getView().getPlayer() instanceof Player) || !paramPrepareItemCraftEvent.isRepair()) {
/*     */       return;
/*     */     }
/*  61 */     Player player = (Player)paramPrepareItemCraftEvent.getView().getPlayer();
/*  62 */     CraftingInventory craftingInventory = paramPrepareItemCraftEvent.getInventory();
/*  63 */     ItemStack itemStack1 = new ItemStack(Material.AIR);
/*  64 */     ItemStack itemStack2 = craftingInventory.getResult();
/*     */     
/*  66 */     craftingInventory.setResult(itemStack1);
/*  67 */     Bukkit.getScheduler().runTaskLater((Plugin)MMOItems.plugin, () -> { List<ItemStack> list = (List)Arrays.<ItemStack>stream(paramCraftingInventory.getMatrix()).filter(Objects::nonNull).filter(()).collect(Collectors.toList()); long l = list.stream().filter(()).count(); if (l == 0L) { paramCraftingInventory.setResult(paramItemStack1); paramPlayer.updateInventory(); return; }  if (l == 1L || !NBTItem.get(list.get(0)).getString("MMOITEMS_ITEM_ID").equals(NBTItem.get(list.get(1)).getString("MMOITEMS_ITEM_ID"))) { paramCraftingInventory.setResult(paramItemStack2); paramPlayer.updateInventory(); return; }  paramCraftingInventory.setResult(paramItemStack1); boolean bool1 = list.stream().allMatch(()); boolean bool2 = list.stream().allMatch(()); if (bool1) { paramCraftingInventory.setResult(paramItemStack2); } else if (bool2) { DurabilityItem durabilityItem = new DurabilityItem(paramPlayer, list.get(0)); int i = ((Integer)list.stream().map(()).map(DurabilityItem::getDurability).reduce(Integer.valueOf(0), Integer::sum)).intValue(); int j = durabilityItem.getMaxDurability() - Math.min(durabilityItem.getMaxDurability(), i); if (j > 0) durabilityItem.addDurability(j);  paramCraftingInventory.setResult(durabilityItem.toItem()); }  paramPlayer.updateInventory(); }1L);
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
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   private void itemPickup(EntityPickupItemEvent paramEntityPickupItemEvent) {
/* 120 */     if (!paramEntityPickupItemEvent.getEntity().getType().equals(EntityType.PLAYER))
/* 121 */       return;  ItemStack itemStack = modifyItem(paramEntityPickupItemEvent.getItem().getItemStack(), (Player)paramEntityPickupItemEvent.getEntity(), ReforgeReason.PICKUP);
/* 122 */     if (itemStack != null) paramEntityPickupItemEvent.getItem().setItemStack(itemStack); 
/*     */   }
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   private void itemCraft(CraftItemEvent paramCraftItemEvent) {
/* 127 */     if (!(paramCraftItemEvent.getWhoClicked() instanceof Player))
/* 128 */       return;  ItemStack itemStack = modifyItem(paramCraftItemEvent.getCurrentItem(), (Player)paramCraftItemEvent.getWhoClicked(), ReforgeReason.CRAFT);
/* 129 */     if (itemStack != null) paramCraftItemEvent.setCurrentItem(itemStack); 
/*     */   }
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   private void inventoryMove(InventoryClickEvent paramInventoryClickEvent) {
/* 134 */     if (paramInventoryClickEvent.getInventory().getType() != InventoryType.CRAFTING || !(paramInventoryClickEvent.getWhoClicked() instanceof Player))
/* 135 */       return;  ItemStack itemStack = modifyItem(paramInventoryClickEvent.getCurrentItem(), (Player)paramInventoryClickEvent.getWhoClicked(), ReforgeReason.CLICK);
/* 136 */     if (itemStack != null) paramInventoryClickEvent.setCurrentItem(itemStack); 
/*     */   }
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   private void dropItem(PlayerDropItemEvent paramPlayerDropItemEvent) {
/* 141 */     NBTItem nBTItem = NBTItem.get(paramPlayerDropItemEvent.getItemDrop().getItemStack());
/* 142 */     if (!MMOItems.plugin.getConfig().getBoolean("soulbound.can-drop") && nBTItem.hasTag("MMOITEMS_SOULBOUND"))
/* 143 */       paramPlayerDropItemEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*     */   public void playerJoin(PlayerJoinEvent paramPlayerJoinEvent) {
/* 148 */     Player player = paramPlayerJoinEvent.getPlayer();
/*     */     
/* 150 */     ItemStack itemStack = modifyItem(player.getEquipment().getHelmet(), player, ReforgeReason.JOIN);
/* 151 */     if (itemStack != null) player.getEquipment().setHelmet(itemStack); 
/* 152 */     itemStack = modifyItem(player.getEquipment().getChestplate(), player, ReforgeReason.JOIN);
/* 153 */     if (itemStack != null) player.getEquipment().setChestplate(itemStack); 
/* 154 */     itemStack = modifyItem(player.getEquipment().getLeggings(), player, ReforgeReason.JOIN);
/* 155 */     if (itemStack != null) player.getEquipment().setLeggings(itemStack); 
/* 156 */     itemStack = modifyItem(player.getEquipment().getBoots(), player, ReforgeReason.JOIN);
/* 157 */     if (itemStack != null) player.getEquipment().setBoots(itemStack);
/*     */     
/* 159 */     for (byte b = 0; b < 9; b++) {
/* 160 */       itemStack = modifyItem(player.getInventory().getItem(b), player, ReforgeReason.JOIN);
/* 161 */       if (itemStack != null) player.getInventory().setItem(b, itemStack);
/*     */     
/*     */     } 
/* 164 */     itemStack = modifyItem(player.getEquipment().getItemInOffHand(), player, ReforgeReason.JOIN);
/* 165 */     if (itemStack != null) player.getEquipment().setItemInOffHand(itemStack);
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private ItemStack modifyItem(@Nullable ItemStack paramItemStack, @NotNull Player paramPlayer, @NotNull ReforgeReason paramReforgeReason) {
/* 172 */     if (paramItemStack == null || !paramItemStack.hasItemMeta()) {
/* 173 */       return null;
/*     */     }
/*     */     
/* 176 */     MMOItemReforger mMOItemReforger = new MMOItemReforger(paramItemStack);
/* 177 */     if (!mMOItemReforger.hasTemplate()) {
/* 178 */       return null;
/*     */     }
/*     */     
/* 181 */     if ("VANILLA".equals(mMOItemReforger.getNBTItem().getString("MMOITEMS_ITEM_ID"))) {
/* 182 */       return null;
/*     */     }
/*     */     
/* 185 */     if (MMOItems.plugin.getConfig().getBoolean("item-revision.disable-on." + paramReforgeReason.name().toLowerCase())) {
/* 186 */       return null;
/*     */     }
/*     */     
/* 189 */     int i = mMOItemReforger.getTemplate().getRevisionId();
/* 190 */     byte b = mMOItemReforger.getNBTItem().hasTag(ItemStats.REVISION_ID.getNBTPath()) ? mMOItemReforger.getNBTItem().getInteger(ItemStats.REVISION_ID.getNBTPath()) : 1;
/* 191 */     if (i <= b) {
/* 192 */       return null;
/*     */     }
/*     */     
/* 195 */     if (!mMOItemReforger.reforge((MMOItems.plugin.getLanguage()).revisionOptions, paramPlayer)) {
/* 196 */       return null;
/*     */     }
/*     */     
/* 199 */     for (ItemStack itemStack : paramPlayer.getInventory().addItem((ItemStack[])mMOItemReforger
/* 200 */         .getReforgingOutput().toArray((Object[])new ItemStack[0])).values()) {
/*     */ 
/*     */       
/* 203 */       if (SilentNumbers.isAir(itemStack)) {
/*     */         continue;
/*     */       }
/*     */       
/* 207 */       paramPlayer.getWorld().dropItem(paramPlayer.getLocation(), itemStack);
/*     */     } 
/*     */ 
/*     */     
/* 211 */     return mMOItemReforger.getResult();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\ItemListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */