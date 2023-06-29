/*     */ package net.Indyuce.mmoitems.listener;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.util.Optional;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.block.CustomBlock;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockIgniteEvent;
/*     */ import org.bukkit.event.block.BlockPhysicsEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class CustomBlockListener
/*     */   implements Listener {
/*     */   public CustomBlockListener() {
/*  30 */     if ((MMOItems.plugin.getLanguage()).replaceMushroomDrops)
/*  31 */       Bukkit.getPluginManager().registerEvents(new MushroomReplacer(), (Plugin)MMOItems.plugin); 
/*     */   }
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void a(BlockPhysicsEvent paramBlockPhysicsEvent) {
/*  36 */     if (MMOItems.plugin.getCustomBlocks().isMushroomBlock(paramBlockPhysicsEvent.getChangedType())) {
/*  37 */       paramBlockPhysicsEvent.setCancelled(true);
/*  38 */       paramBlockPhysicsEvent.getBlock().getState().update(true, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
/*     */   public void b(BlockBreakEvent paramBlockBreakEvent) {
/*  44 */     if (paramBlockBreakEvent.getPlayer().getGameMode() == GameMode.CREATIVE) {
/*     */       return;
/*     */     }
/*  47 */     Optional<CustomBlock> optional = MMOItems.plugin.getCustomBlocks().getFromBlock(paramBlockBreakEvent.getBlock().getBlockData());
/*  48 */     if (!optional.isPresent()) {
/*     */       return;
/*     */     }
/*  51 */     CustomBlock customBlock = optional.get();
/*  52 */     int i = MMOUtils.getPickaxePower(paramBlockBreakEvent.getPlayer());
/*  53 */     if (i < customBlock.getRequiredPower()) {
/*  54 */       if (customBlock.requirePowerToBreak()) {
/*  55 */         paramBlockBreakEvent.setCancelled(true);
/*     */       } else {
/*  57 */         paramBlockBreakEvent.setDropItems(false);
/*  58 */         paramBlockBreakEvent.setExpToDrop(0);
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*  63 */     paramBlockBreakEvent.setDropItems(false);
/*  64 */     paramBlockBreakEvent.setExpToDrop((paramBlockBreakEvent.getPlayer().getGameMode() == GameMode.CREATIVE) ? 0 : customBlock.rollExperience());
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   private static int getPickaxePower(Player paramPlayer) {
/*  69 */     return MMOUtils.getPickaxePower(paramPlayer);
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGHEST)
/*     */   public void c(BlockPlaceEvent paramBlockPlaceEvent) {
/*  74 */     if (!paramBlockPlaceEvent.isCancelled() && !isMushroomBlock(paramBlockPlaceEvent.getBlockPlaced().getType())) {
/*  75 */       NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramBlockPlaceEvent.getItemInHand());
/*  76 */       int i = nBTItem.getInteger("MMOITEMS_BLOCK_ID");
/*  77 */       if (i > 160 || i < 1 || i == 54)
/*     */         return; 
/*  79 */       if (MMOItems.plugin.getCustomBlocks().getBlock(i) == null) {
/*  80 */         MMOItems.plugin.getLogger().log(Level.SEVERE, "Could not load custom block '" + i + "':  Block is not registered.");
/*  81 */         MMOItems.plugin.getLogger().log(Level.SEVERE, "Try reloading the plugin to solve the issue.");
/*  82 */         paramBlockPlaceEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/*  86 */       CustomBlock customBlock = MMOItems.plugin.getCustomBlocks().getBlock(i);
/*  87 */       Block block = paramBlockPlaceEvent.getBlockPlaced();
/*  88 */       block.setType(customBlock.getState().getType(), false);
/*  89 */       block.setBlockData(customBlock.getState().getBlockData(), false);
/*     */       
/*  91 */       BlockPlaceEvent blockPlaceEvent = new BlockPlaceEvent(block, block.getState(), paramBlockPlaceEvent.getBlockAgainst(), paramBlockPlaceEvent.getItemInHand(), paramBlockPlaceEvent.getPlayer(), true, EquipmentSlot.HAND);
/*     */       
/*  93 */       Bukkit.getServer().getPluginManager().callEvent((Event)blockPlaceEvent);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void d(BlockIgniteEvent paramBlockIgniteEvent) {
/*  99 */     if (paramBlockIgniteEvent.getCause() == BlockIgniteEvent.IgniteCause.LAVA || paramBlockIgniteEvent.getCause() == BlockIgniteEvent.IgniteCause.SPREAD) {
/* 100 */       BlockFace[] arrayOfBlockFace = { BlockFace.UP, BlockFace.DOWN, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.WEST, BlockFace.EAST };
/* 101 */       for (BlockFace blockFace : arrayOfBlockFace) {
/* 102 */         if (MMOItems.plugin.getCustomBlocks().getFromBlock(paramBlockIgniteEvent.getBlock().getRelative(blockFace).getBlockData()).isPresent())
/* 103 */           paramBlockIgniteEvent.setCancelled(true); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private boolean isMushroomBlock(Material paramMaterial) {
/* 108 */     return (paramMaterial == Material.BROWN_MUSHROOM_BLOCK || paramMaterial == Material.MUSHROOM_STEM || paramMaterial == Material.RED_MUSHROOM_BLOCK);
/*     */   }
/*     */   
/*     */   public static class MushroomReplacer implements Listener {
/*     */     @EventHandler(ignoreCancelled = true)
/*     */     public void d(BlockBreakEvent param1BlockBreakEvent) {
/* 114 */       if (MMOItems.plugin.getCustomBlocks().isMushroomBlock(param1BlockBreakEvent.getBlock().getType()) && MMOItems.plugin
/* 115 */         .getDropTables().hasSilkTouchTool(param1BlockBreakEvent.getPlayer()))
/* 116 */         param1BlockBreakEvent.setDropItems(false); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\CustomBlockListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */