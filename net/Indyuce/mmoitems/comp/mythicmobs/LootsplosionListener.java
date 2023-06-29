/*     */ package net.Indyuce.mmoitems.comp.mythicmobs;
/*     */ 
/*     */ import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.ItemSpawnEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class LootsplosionListener implements Listener {
/*  27 */   private static final Random random = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   private final boolean colored = MMOItems.plugin.getConfig().getBoolean("lootsplosion.color");
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH)
/*     */   public void b(MythicMobDeathEvent paramMythicMobDeathEvent) {
/*  37 */     if (paramMythicMobDeathEvent.getMob().getVariables().has("Lootsplosion")) {
/*  38 */       new LootsplosionHandler(paramMythicMobDeathEvent);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public class LootsplosionHandler
/*     */     implements Listener
/*     */   {
/*     */     private final List<ItemStack> drops;
/*     */     
/*     */     private final double offset;
/*     */     
/*     */     public LootsplosionHandler(MythicMobDeathEvent param1MythicMobDeathEvent) {
/*  51 */       this.offset = param1MythicMobDeathEvent.getEntity().getHeight() / 2.0D;
/*  52 */       this.drops = new ArrayList<>(param1MythicMobDeathEvent.getDrops());
/*     */       
/*  54 */       Bukkit.getPluginManager().registerEvents(this, (Plugin)MMOItems.plugin);
/*     */     }
/*     */     
/*     */     private void close() {
/*  58 */       ItemSpawnEvent.getHandlerList().unregister(this);
/*     */     }
/*     */     
/*     */     @EventHandler
/*     */     public void a(ItemSpawnEvent param1ItemSpawnEvent) {
/*  63 */       Item item = param1ItemSpawnEvent.getEntity();
/*  64 */       if (!this.drops.contains(item.getItemStack())) {
/*  65 */         close();
/*     */         
/*     */         return;
/*     */       } 
/*  69 */       this.drops.remove(item.getItemStack());
/*  70 */       item.teleport(item.getLocation().add(0.0D, this.offset, 0.0D));
/*  71 */       item.setVelocity(LootsplosionListener.this.randomVector());
/*     */       
/*  73 */       if (LootsplosionListener.this.colored)
/*  74 */         Bukkit.getScheduler().runTask((Plugin)MMOItems.plugin, () -> {
/*     */               NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(param1Item.getItemStack());
/*     */               if (nBTItem.hasTag("MMOITEMS_TIER")) {
/*     */                 ItemTier itemTier = MMOItems.plugin.getTiers().get(nBTItem.getString("MMOITEMS_TIER"));
/*     */                 if (itemTier.hasColor())
/*     */                   new LootsplosionListener.LootColor(param1Item, itemTier.getColor()); 
/*     */               } 
/*     */             }); 
/*     */     }
/*     */   }
/*     */   
/*     */   private Vector randomVector() {
/*  86 */     double d1 = MMOItems.plugin.getConfig().getDouble("lootsplosion.offset");
/*  87 */     double d2 = MMOItems.plugin.getConfig().getDouble("lootsplosion.height");
/*  88 */     return new Vector(Math.cos(random.nextDouble() * Math.PI * 2.0D) * d1, d2, Math.sin(random.nextDouble() * Math.PI * 2.0D) * d1);
/*     */   }
/*     */   
/*     */   public class LootColor
/*     */     extends BukkitRunnable {
/*     */     private final Item item;
/*     */     private final Color color;
/*  95 */     private int j = 0;
/*     */     
/*     */     public LootColor(Item param1Item, ChatColor param1ChatColor) {
/*  98 */       this.item = param1Item;
/*  99 */       this.color = MMOUtils.toRGB(param1ChatColor);
/*     */       
/* 101 */       runTaskTimer((Plugin)MMOItems.plugin, 0L, 1L);
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 106 */       if (this.j++ > 100 || this.item.isDead() || this.item.isOnGround()) {
/* 107 */         cancel();
/*     */         
/*     */         return;
/*     */       } 
/* 111 */       this.item.getWorld().spawnParticle(Particle.REDSTONE, this.item.getLocation(), 1, new Particle.DustOptions(this.color, 1.3F));
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mythicmobs\LootsplosionListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */