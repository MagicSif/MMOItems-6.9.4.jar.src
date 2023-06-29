/*     */ package net.Indyuce.mmoitems.listener;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.event.PlayerAttackEvent;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*     */ import io.lumine.mythic.lib.damage.MeleeAttackMetadata;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.TypeSet;
/*     */ import net.Indyuce.mmoitems.api.event.item.SpecialWeaponAttackEvent;
/*     */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*     */ import net.Indyuce.mmoitems.api.interaction.GemStone;
/*     */ import net.Indyuce.mmoitems.api.interaction.ItemSkin;
/*     */ import net.Indyuce.mmoitems.api.interaction.Tool;
/*     */ import net.Indyuce.mmoitems.api.interaction.UseItem;
/*     */ import net.Indyuce.mmoitems.api.interaction.projectile.ProjectileData;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.Gauntlet;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.Weapon;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.Staff;
/*     */ import net.Indyuce.mmoitems.api.interaction.weapon.untargeted.UntargetedWeapon;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.AbstractArrow;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEntityEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.event.player.PlayerItemConsumeEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class ItemUse implements Listener {
/*     */   @EventHandler
/*     */   public void rightClickEffects(PlayerInteractEvent paramPlayerInteractEvent) {
/*  48 */     if (!paramPlayerInteractEvent.hasItem()) {
/*     */       return;
/*     */     }
/*     */     
/*  52 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramPlayerInteractEvent.getItem());
/*  53 */     if (!nBTItem.hasType()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     Player player = paramPlayerInteractEvent.getPlayer();
/*  61 */     UseItem useItem = UseItem.getItem(player, nBTItem, nBTItem.getType());
/*  62 */     if (useItem instanceof Consumable && ((Consumable)useItem).hasVanillaEating()) {
/*     */       return;
/*     */     }
/*     */     
/*  66 */     if (!useItem.checkItemRequirements()) {
/*  67 */       paramPlayerInteractEvent.setUseItemInHand(Event.Result.DENY);
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  72 */     if (paramPlayerInteractEvent.getAction().name().contains("RIGHT_CLICK")) {
/*  73 */       String str = getCooldownReference(useItem.getMMOItem());
/*  74 */       if (useItem.getPlayerData().getMMOPlayerData().getCooldownMap().isOnCooldown(str)) {
/*  75 */         double d = useItem.getPlayerData().getMMOPlayerData().getCooldownMap().getCooldown(str);
/*  76 */         Message.ITEM_ON_COOLDOWN
/*  77 */           .format(ChatColor.RED, new String[] { "#left#", (MythicLib.plugin.getMMOConfig()).decimal.format(d), "#s#", (d >= 2.0D) ? "s" : ""
/*  78 */             }).send(player);
/*  79 */         paramPlayerInteractEvent.setUseItemInHand(Event.Result.DENY);
/*     */         
/*     */         return;
/*     */       } 
/*  83 */       if (useItem instanceof Consumable) {
/*  84 */         paramPlayerInteractEvent.setUseItemInHand(Event.Result.DENY);
/*  85 */         Consumable.ConsumableConsumeResult consumableConsumeResult = ((Consumable)useItem).useOnPlayer(paramPlayerInteractEvent.getHand(), false);
/*  86 */         if (consumableConsumeResult == Consumable.ConsumableConsumeResult.CANCEL) {
/*     */           return;
/*     */         }
/*  89 */         if (consumableConsumeResult == Consumable.ConsumableConsumeResult.CONSUME) {
/*  90 */           paramPlayerInteractEvent.getItem().setAmount(paramPlayerInteractEvent.getItem().getAmount() - 1);
/*     */         }
/*     */       } 
/*  93 */       useItem.getPlayerData().getMMOPlayerData().getCooldownMap().applyCooldown(str, useItem.getNBTItem().getStat("ITEM_COOLDOWN"));
/*  94 */       useItem.executeCommands();
/*     */     } 
/*     */ 
/*     */     
/*  98 */     if (useItem instanceof UntargetedWeapon) {
/*  99 */       UntargetedWeapon untargetedWeapon = (UntargetedWeapon)useItem;
/* 100 */       if (untargetedWeapon.getWeaponType().corresponds(paramPlayerInteractEvent.getAction()))
/* 101 */         untargetedWeapon.handleTargetFreeAttack(EquipmentSlot.fromBukkit(paramPlayerInteractEvent.getHand())); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getCooldownReference(VolatileMMOItem paramVolatileMMOItem) {
/* 106 */     String str = paramVolatileMMOItem.getNBT().getString("MMOITEMS_COOLDOWN_REFERENCE");
/* 107 */     return (str != null && !str.isEmpty()) ? str : paramVolatileMMOItem.getCooldownPath();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(ignoreCancelled = true)
/*     */   public void meleeAttacks(PlayerAttackEvent paramPlayerAttackEvent) {
/* 114 */     if (!(paramPlayerAttackEvent.getAttack() instanceof MeleeAttackMetadata)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     Player player = paramPlayerAttackEvent.getPlayer();
/* 122 */     PlayerData playerData = PlayerData.get((OfflinePlayer)player);
/* 123 */     ItemStack itemStack = player.getInventory().getItem(((MeleeAttackMetadata)paramPlayerAttackEvent.getAttack()).getHand().toBukkit());
/* 124 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack);
/*     */     
/* 126 */     if (nBTItem.hasType() && Type.get(nBTItem.getType()) != Type.BLOCK) {
/* 127 */       Weapon weapon = new Weapon(playerData, nBTItem);
/*     */       
/* 129 */       if (weapon.getMMOItem().getType().getItemSet() == TypeSet.RANGE) {
/* 130 */         paramPlayerAttackEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 134 */       if (!weapon.checkItemRequirements()) {
/* 135 */         paramPlayerAttackEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 139 */       if (!weapon.handleTargetedAttack(paramPlayerAttackEvent.getAttack(), paramPlayerAttackEvent.getEntity())) {
/* 140 */         paramPlayerAttackEvent.setCancelled(true);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
/*     */   public void specialToolAbilities(BlockBreakEvent paramBlockBreakEvent) {
/* 154 */     Player player = paramBlockBreakEvent.getPlayer();
/* 155 */     Block block = paramBlockBreakEvent.getBlock();
/* 156 */     if (player.getGameMode() == GameMode.CREATIVE) {
/*     */       return;
/*     */     }
/* 159 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand());
/* 160 */     if (!nBTItem.hasType()) {
/*     */       return;
/*     */     }
/* 163 */     Tool tool = new Tool(player, nBTItem);
/* 164 */     if (!tool.checkItemRequirements()) {
/* 165 */       paramBlockBreakEvent.setCancelled(true);
/*     */       
/*     */       return;
/*     */     } 
/* 169 */     if (tool.miningEffects(block))
/* 170 */       paramBlockBreakEvent.setCancelled(true); 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void rightClickWeaponInteractions(PlayerInteractEntityEvent paramPlayerInteractEntityEvent) {
/* 175 */     Player player = paramPlayerInteractEntityEvent.getPlayer();
/* 176 */     if (!(paramPlayerInteractEntityEvent.getRightClicked() instanceof LivingEntity)) {
/*     */       return;
/*     */     }
/* 179 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand());
/* 180 */     if (!nBTItem.hasType()) {
/*     */       return;
/*     */     }
/* 183 */     LivingEntity livingEntity = (LivingEntity)paramPlayerInteractEntityEvent.getRightClicked();
/* 184 */     if (!UtilityMethods.canTarget(player, (Entity)livingEntity, InteractionType.OFFENSE_ACTION)) {
/*     */       return;
/*     */     }
/* 187 */     UseItem useItem = UseItem.getItem(player, nBTItem, nBTItem.getType());
/* 188 */     if (!useItem.checkItemRequirements()) {
/*     */       return;
/*     */     }
/*     */     
/* 192 */     if (useItem instanceof Staff) {
/* 193 */       SpecialWeaponAttackEvent specialWeaponAttackEvent = new SpecialWeaponAttackEvent(useItem.getPlayerData(), (Weapon)useItem, livingEntity);
/* 194 */       Bukkit.getPluginManager().callEvent((Event)specialWeaponAttackEvent);
/* 195 */       if (!specialWeaponAttackEvent.isCancelled()) {
/* 196 */         ((Staff)useItem).specialAttack(livingEntity);
/*     */       }
/*     */     } 
/*     */     
/* 200 */     if (useItem instanceof Gauntlet) {
/* 201 */       SpecialWeaponAttackEvent specialWeaponAttackEvent = new SpecialWeaponAttackEvent(useItem.getPlayerData(), (Weapon)useItem, livingEntity);
/* 202 */       Bukkit.getPluginManager().callEvent((Event)specialWeaponAttackEvent);
/* 203 */       if (!specialWeaponAttackEvent.isCancelled()) {
/* 204 */         ((Gauntlet)useItem).specialAttack(livingEntity);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @EventHandler
/*     */   public void gemStonesAndItemStacks(InventoryClickEvent paramInventoryClickEvent) {
/* 211 */     Player player = (Player)paramInventoryClickEvent.getWhoClicked();
/* 212 */     if (paramInventoryClickEvent.getAction() != InventoryAction.SWAP_WITH_CURSOR) {
/*     */       return;
/*     */     }
/* 215 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramInventoryClickEvent.getCursor());
/* 216 */     if (!nBTItem.hasType()) {
/*     */       return;
/*     */     }
/* 219 */     UseItem useItem = UseItem.getItem(player, nBTItem, nBTItem.getType());
/* 220 */     if (!useItem.checkItemRequirements()) {
/*     */       return;
/*     */     }
/* 223 */     if (useItem instanceof ItemSkin) {
/* 224 */       NBTItem nBTItem1 = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramInventoryClickEvent.getCurrentItem());
/* 225 */       if (!nBTItem1.hasType()) {
/*     */         return;
/*     */       }
/* 228 */       ItemSkin.ApplyResult applyResult = ((ItemSkin)useItem).applyOntoItem(nBTItem1, Type.get(nBTItem1.getType()));
/* 229 */       if (applyResult.getType() == ItemSkin.ResultType.NONE) {
/*     */         return;
/*     */       }
/* 232 */       paramInventoryClickEvent.setCancelled(true);
/* 233 */       nBTItem.getItem().setAmount(nBTItem.getItem().getAmount() - 1);
/*     */       
/* 235 */       if (applyResult.getType() == ItemSkin.ResultType.FAILURE) {
/*     */         return;
/*     */       }
/* 238 */       paramInventoryClickEvent.setCurrentItem(applyResult.getResult());
/*     */     } 
/*     */     
/* 241 */     if (useItem instanceof GemStone) {
/* 242 */       NBTItem nBTItem1 = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramInventoryClickEvent.getCurrentItem());
/* 243 */       if (!nBTItem1.hasType()) {
/*     */         return;
/*     */       }
/* 246 */       GemStone.ApplyResult applyResult = ((GemStone)useItem).applyOntoItem(nBTItem1, Type.get(nBTItem1.getType()));
/* 247 */       if (applyResult.getType() == GemStone.ResultType.NONE) {
/*     */         return;
/*     */       }
/* 250 */       paramInventoryClickEvent.setCancelled(true);
/* 251 */       nBTItem.getItem().setAmount(nBTItem.getItem().getAmount() - 1);
/*     */       
/* 253 */       if (applyResult.getType() == GemStone.ResultType.FAILURE) {
/*     */         return;
/*     */       }
/* 256 */       paramInventoryClickEvent.setCurrentItem(applyResult.getResult());
/*     */     } 
/*     */     
/* 259 */     if (useItem instanceof Consumable && paramInventoryClickEvent.getCurrentItem() != null && paramInventoryClickEvent.getCurrentItem().getType() != Material.AIR && (
/* 260 */       (Consumable)useItem).useOnItem(paramInventoryClickEvent, MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramInventoryClickEvent.getCurrentItem()))) {
/* 261 */       paramInventoryClickEvent.setCancelled(true);
/* 262 */       paramInventoryClickEvent.getCursor().setAmount(paramInventoryClickEvent.getCursor().getAmount() - 1);
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
/*     */   @EventHandler
/*     */   public void handleCustomBows(EntityShootBowEvent paramEntityShootBowEvent) {
/* 276 */     if (!(paramEntityShootBowEvent.getProjectile() instanceof AbstractArrow) || !(paramEntityShootBowEvent.getEntity() instanceof Player)) {
/*     */       return;
/*     */     }
/* 279 */     NBTItem nBTItem = NBTItem.get(paramEntityShootBowEvent.getBow());
/* 280 */     Type type = Type.get(nBTItem.getType());
/*     */     
/* 282 */     if (type != null) {
/* 283 */       PlayerData playerData = PlayerData.get((OfflinePlayer)paramEntityShootBowEvent.getEntity());
/* 284 */       Weapon weapon = new Weapon(playerData, nBTItem);
/* 285 */       if (!weapon.checkItemRequirements() || !weapon.checkAndApplyWeaponCosts()) {
/* 286 */         paramEntityShootBowEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 291 */       ItemStack itemStack = playerData.getPlayer().getInventory().getItemInMainHand();
/* 292 */       EquipmentSlot equipmentSlot = itemStack.isSimilar(paramEntityShootBowEvent.getBow()) ? EquipmentSlot.MAIN_HAND : EquipmentSlot.OFF_HAND;
/* 293 */       ProjectileData projectileData = MMOItems.plugin.getEntities().registerCustomProjectile(nBTItem, playerData.getStats().newTemporary(equipmentSlot), paramEntityShootBowEvent.getProjectile(), paramEntityShootBowEvent.getForce());
/* 294 */       AbstractArrow abstractArrow = (AbstractArrow)paramEntityShootBowEvent.getProjectile();
/*     */ 
/*     */       
/* 297 */       double d = projectileData.getShooter().getStat("ARROW_VELOCITY");
/* 298 */       if (d > 0.0D) {
/* 299 */         abstractArrow.setVelocity(abstractArrow.getVelocity().multiply(d));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*     */   public void handleVanillaEatenConsumables(PlayerItemConsumeEvent paramPlayerItemConsumeEvent) {
/* 309 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramPlayerItemConsumeEvent.getItem());
/* 310 */     if (!nBTItem.hasType()) {
/*     */       return;
/*     */     }
/* 313 */     Player player = paramPlayerItemConsumeEvent.getPlayer();
/* 314 */     UseItem useItem = UseItem.getItem(player, nBTItem, nBTItem.getType());
/* 315 */     if (!useItem.checkItemRequirements()) {
/* 316 */       paramPlayerItemConsumeEvent.setCancelled(true);
/*     */       
/*     */       return;
/*     */     } 
/* 320 */     if (useItem instanceof Consumable) {
/*     */       
/* 322 */       String str = getCooldownReference(useItem.getMMOItem());
/* 323 */       if (useItem.getPlayerData().getMMOPlayerData().getCooldownMap().isOnCooldown(str)) {
/* 324 */         double d = useItem.getPlayerData().getMMOPlayerData().getCooldownMap().getCooldown(str);
/* 325 */         Message.ITEM_ON_COOLDOWN
/* 326 */           .format(ChatColor.RED, new String[] { "#left#", (MythicLib.plugin.getMMOConfig()).decimal.format(d), "#s#", (d >= 2.0D) ? "s" : ""
/* 327 */             }).send(player);
/* 328 */         paramPlayerItemConsumeEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/* 332 */       Consumable.ConsumableConsumeResult consumableConsumeResult = ((Consumable)useItem).useOnPlayer(paramPlayerItemConsumeEvent.getItem().equals(player.getInventory().getItemInMainHand()) ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND, true);
/*     */ 
/*     */       
/* 335 */       if (consumableConsumeResult == Consumable.ConsumableConsumeResult.CANCEL) {
/* 336 */         paramPlayerItemConsumeEvent.setCancelled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 341 */       if (consumableConsumeResult == Consumable.ConsumableConsumeResult.NOT_CONSUME) {
/* 342 */         paramPlayerItemConsumeEvent.setCancelled(true);
/*     */       }
/* 344 */       useItem.getPlayerData().getMMOPlayerData().getCooldownMap().applyCooldown(str, useItem.getNBTItem().getStat("ITEM_COOLDOWN"));
/* 345 */       useItem.executeCommands();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\ItemUse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */