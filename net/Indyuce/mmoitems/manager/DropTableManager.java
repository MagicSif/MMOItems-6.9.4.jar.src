/*     */ package net.Indyuce.mmoitems.manager;
/*     */ import io.lumine.mythic.lib.UtilityMethods;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.block.CustomBlock;
/*     */ import net.Indyuce.mmoitems.api.droptable.DropTable;
/*     */ import net.Indyuce.mmoitems.api.event.ItemDropEvent;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class DropTableManager implements Listener, Reloadable {
/*  34 */   private final Map<EntityType, DropTable> monsters = new HashMap<>();
/*  35 */   private final Map<Material, DropTable> blocks = new HashMap<>();
/*  36 */   private final Map<Integer, DropTable> customBlocks = new HashMap<>();
/*     */   
/*     */   public DropTableManager() {
/*  39 */     reload();
/*     */   }
/*     */   
/*     */   public void reload() {
/*  43 */     this.monsters.clear();
/*  44 */     this.blocks.clear();
/*  45 */     this.customBlocks.clear();
/*     */     
/*  47 */     FileConfiguration fileConfiguration = (new ConfigFile("drops")).getConfig();
/*  48 */     if (fileConfiguration.contains("monsters"))
/*  49 */       for (String str : fileConfiguration.getConfigurationSection("monsters").getKeys(false)) {
/*     */         try {
/*  51 */           EntityType entityType = EntityType.valueOf(str.toUpperCase().replace("-", "_").replace(" ", "_"));
/*  52 */           this.monsters.put(entityType, new DropTable(fileConfiguration.getConfigurationSection("monsters." + str)));
/*  53 */         } catch (IllegalArgumentException illegalArgumentException) {
/*  54 */           MMOItems.plugin.getLogger().log(Level.WARNING, "Could not read drop table with mob type '" + str + "': " + illegalArgumentException
/*  55 */               .getMessage());
/*     */         } 
/*     */       }  
/*  58 */     if (fileConfiguration.contains("blocks"))
/*  59 */       for (String str : fileConfiguration.getConfigurationSection("blocks").getKeys(false)) {
/*     */         try {
/*  61 */           Material material = Material.valueOf(str.toUpperCase().replace("-", "_").replace(" ", "_"));
/*  62 */           this.blocks.put(material, new DropTable(fileConfiguration.getConfigurationSection("blocks." + str)));
/*  63 */         } catch (IllegalArgumentException illegalArgumentException) {
/*  64 */           MMOItems.plugin.getLogger().log(Level.WARNING, "Could not read drop table with material '" + str + "': " + illegalArgumentException
/*  65 */               .getMessage());
/*     */         } 
/*     */       }  
/*  68 */     if (fileConfiguration.contains("customblocks"))
/*  69 */       for (String str : fileConfiguration.getConfigurationSection("customblocks").getKeys(false)) {
/*     */         try {
/*  71 */           int i = Integer.parseInt(str);
/*  72 */           this.customBlocks.put(Integer.valueOf(i), new DropTable(fileConfiguration.getConfigurationSection("customblocks." + str)));
/*  73 */         } catch (IllegalArgumentException illegalArgumentException) {
/*  74 */           MMOItems.plugin.getLogger().log(Level.WARNING, "Could not read drop table with custom block '" + str + "': " + illegalArgumentException
/*  75 */               .getMessage());
/*     */         } 
/*     */       }  
/*     */   }
/*     */   @EventHandler
/*     */   public void entityDrops(EntityDeathEvent paramEntityDeathEvent) {
/*  81 */     LivingEntity livingEntity = paramEntityDeathEvent.getEntity();
/*  82 */     Player player = livingEntity.getKiller();
/*  83 */     if (player != null && player.hasMetadata("NPC")) {
/*     */       return;
/*     */     }
/*  86 */     if (this.monsters.containsKey(livingEntity.getType())) {
/*  87 */       List list = ((DropTable)this.monsters.get(livingEntity.getType())).read((player != null) ? PlayerData.get((OfflinePlayer)player) : null, false);
/*  88 */       ItemDropEvent itemDropEvent = new ItemDropEvent((LivingEntity)player, list, (Entity)livingEntity);
/*  89 */       Bukkit.getPluginManager().callEvent((Event)itemDropEvent);
/*  90 */       if (itemDropEvent.isCancelled()) {
/*     */         return;
/*     */       }
/*  93 */       paramEntityDeathEvent.getDrops().addAll(list);
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*     */   public void blockDrops(BlockBreakEvent paramBlockBreakEvent) {
/*  99 */     Player player = paramBlockBreakEvent.getPlayer();
/* 100 */     if (player == null || player.getGameMode() == GameMode.CREATIVE) {
/*     */       return;
/*     */     }
/* 103 */     Block block = paramBlockBreakEvent.getBlock();
/* 104 */     Optional<CustomBlock> optional = MMOItems.plugin.getCustomBlocks().getFromBlock(block.getBlockData());
/*     */ 
/*     */     
/* 107 */     if (optional.isPresent()) {
/* 108 */       CustomBlock customBlock = optional.get();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 114 */       if (this.customBlocks.containsKey(Integer.valueOf(customBlock.getId())) && MMOUtils.getPickaxePower(player) >= customBlock.getRequiredPower()) {
/* 115 */         PlayerData playerData = PlayerData.get((OfflinePlayer)player);
/* 116 */         List list = ((DropTable)this.customBlocks.get(Integer.valueOf(customBlock.getId()))).read(playerData, hasSilkTouchTool(player));
/* 117 */         ItemDropEvent itemDropEvent = new ItemDropEvent((LivingEntity)player, list, customBlock);
/* 118 */         Bukkit.getPluginManager().callEvent((Event)itemDropEvent);
/* 119 */         if (itemDropEvent.isCancelled()) {
/*     */           return;
/*     */         }
/* 122 */         Bukkit.getScheduler().runTaskLater((Plugin)MMOItems.plugin, () -> { for (ItemStack itemStack : paramList) UtilityMethods.dropItemNaturally(paramBlock.getLocation(), itemStack);  }2L);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 130 */     else if (this.blocks.containsKey(block.getType())) {
/* 131 */       Material material = block.getType();
/* 132 */       List list = ((DropTable)this.blocks.get(material)).read(PlayerData.get((OfflinePlayer)player), hasSilkTouchTool(player));
/* 133 */       ItemDropEvent itemDropEvent = new ItemDropEvent((LivingEntity)player, list, block);
/* 134 */       Bukkit.getPluginManager().callEvent((Event)itemDropEvent);
/* 135 */       if (itemDropEvent.isCancelled()) {
/*     */         return;
/*     */       }
/* 138 */       Bukkit.getScheduler().runTaskLater((Plugin)MMOItems.plugin, () -> { for (ItemStack itemStack : paramList) UtilityMethods.dropItemNaturally(paramBlock.getLocation(), itemStack);  }2L);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSilkTouchTool(Player paramPlayer) {
/* 146 */     ItemStack itemStack = paramPlayer.getInventory().getItemInMainHand();
/* 147 */     return (itemStack != null && itemStack.getType() != Material.AIR && itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\DropTableManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */