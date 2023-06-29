/*     */ package net.Indyuce.mmoitems.listener;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import net.Indyuce.mmoitems.api.CustomSound;
/*     */ import net.Indyuce.mmoitems.api.util.SoundReader;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.event.entity.EntityPickupItemEvent;
/*     */ import org.bukkit.event.inventory.CraftItemEvent;
/*     */ import org.bukkit.event.inventory.FurnaceSmeltEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemBreakEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class CustomSoundListener
/*     */   implements Listener {
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void a(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
/*  31 */     if (!(paramEntityDamageByEntityEvent.getDamager() instanceof Player) || !(paramEntityDamageByEntityEvent.getEntity() instanceof org.bukkit.entity.LivingEntity))
/*     */       return; 
/*  33 */     Player player = (Player)paramEntityDamageByEntityEvent.getDamager();
/*  34 */     playSound(player.getInventory().getItemInMainHand(), CustomSound.ON_ATTACK, player);
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void b(EntityPickupItemEvent paramEntityPickupItemEvent) {
/*  39 */     if (paramEntityPickupItemEvent.getEntityType().equals(EntityType.PLAYER))
/*  40 */       playSound(paramEntityPickupItemEvent.getItem().getItemStack(), CustomSound.ON_PICKUP, (Player)paramEntityPickupItemEvent.getEntity()); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void c(BlockBreakEvent paramBlockBreakEvent) {
/*  45 */     playSound(paramBlockBreakEvent.getPlayer().getInventory().getItemInMainHand(), CustomSound.ON_BLOCK_BREAK, paramBlockBreakEvent.getPlayer());
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void d(PlayerInteractEvent paramPlayerInteractEvent) {
/*  50 */     if (paramPlayerInteractEvent.getAction() == Action.PHYSICAL || !paramPlayerInteractEvent.hasItem())
/*     */       return; 
/*  52 */     if (paramPlayerInteractEvent.getAction().name().contains("RIGHT_CLICK")) {
/*  53 */       playSound(paramPlayerInteractEvent.getItem(), CustomSound.ON_RIGHT_CLICK, paramPlayerInteractEvent.getPlayer());
/*     */     }
/*  55 */     if (paramPlayerInteractEvent.getAction().name().contains("LEFT_CLICK"))
/*  56 */       playSound(paramPlayerInteractEvent.getItem(), CustomSound.ON_LEFT_CLICK, paramPlayerInteractEvent.getPlayer()); 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void e(CraftItemEvent paramCraftItemEvent) {
/*  61 */     playSound(paramCraftItemEvent.getInventory().getResult(), CustomSound.ON_CRAFT, paramCraftItemEvent.getWhoClicked().getLocation());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void f(FurnaceSmeltEvent paramFurnaceSmeltEvent) {
/*  66 */     playSound(paramFurnaceSmeltEvent.getResult(), CustomSound.ON_CRAFT, paramFurnaceSmeltEvent.getBlock().getLocation());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void g(PlayerItemConsumeEvent paramPlayerItemConsumeEvent) {
/*  71 */     playSound(paramPlayerItemConsumeEvent.getItem(), CustomSound.ON_CONSUME, paramPlayerItemConsumeEvent.getPlayer());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void h1(PlayerItemBreakEvent paramPlayerItemBreakEvent) {
/*  76 */     playSound(paramPlayerItemBreakEvent.getBrokenItem(), CustomSound.ON_ITEM_BREAK, paramPlayerItemBreakEvent.getPlayer());
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.MONITOR)
/*     */   public void i(BlockPlaceEvent paramBlockPlaceEvent) {
/*  81 */     playSound(paramBlockPlaceEvent.getItemInHand(), CustomSound.ON_PLACED, paramBlockPlaceEvent.getPlayer());
/*     */   }
/*     */   
/*     */   public static void playSound(@Nullable ItemStack paramItemStack, @NotNull CustomSound paramCustomSound, @NotNull Player paramPlayer) {
/*  85 */     playSound(paramItemStack, paramCustomSound, paramPlayer.getLocation(), (Sound)null);
/*     */   }
/*     */   
/*     */   public static void playSound(@Nullable ItemStack paramItemStack, @NotNull CustomSound paramCustomSound, @NotNull Player paramPlayer, @Nullable Sound paramSound) {
/*  89 */     playSound(paramItemStack, paramCustomSound, paramPlayer.getLocation(), paramSound);
/*     */   }
/*     */   
/*     */   public static void playSound(@Nullable ItemStack paramItemStack, @NotNull CustomSound paramCustomSound, @NotNull Location paramLocation) {
/*  93 */     playSound(paramItemStack, paramCustomSound, paramLocation, (Sound)null);
/*     */   }
/*     */   
/*     */   public static void playSound(@Nullable ItemStack paramItemStack, @NotNull CustomSound paramCustomSound, @NotNull Location paramLocation, @Nullable Sound paramSound) {
/*  97 */     if (paramItemStack == null)
/*     */       return; 
/*  99 */     NBTItem nBTItem = NBTItem.get(paramItemStack);
/* 100 */     String str = nBTItem.getString("MMOITEMS_SOUND_" + paramCustomSound.name());
/* 101 */     if (str == null || str.isEmpty()) {
/* 102 */       if (paramSound != null) paramLocation.getWorld().playSound(paramLocation, paramSound, 1.0F, 1.0F);
/*     */       
/*     */       return;
/*     */     } 
/* 106 */     SoundReader soundReader = new SoundReader(str, paramSound);
/* 107 */     soundReader.play(paramLocation, (float)nBTItem.getDouble("MMOITEMS_SOUND_" + paramCustomSound.name() + "_VOL"), (float)nBTItem.getDouble("MMOITEMS_SOUND_" + paramCustomSound.name() + "_PIT"));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\CustomSoundListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */