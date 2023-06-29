/*     */ package net.Indyuce.mmoitems.api.interaction;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.SmartGive;
/*     */ import io.lumine.mythic.lib.comp.flags.CustomFlag;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.event.item.ConsumableConsumedEvent;
/*     */ import net.Indyuce.mmoitems.api.item.util.LoreUpdate;
/*     */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*     */ import net.Indyuce.mmoitems.stat.type.PlayerConsumable;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class Consumable extends UseItem {
/*     */   public Consumable(Player paramPlayer, NBTItem paramNBTItem) {
/*  26 */     super(paramPlayer, paramNBTItem);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkItemRequirements() {
/*  31 */     return (MythicLib.plugin.getFlags().isFlagAllowed(this.player, CustomFlag.MI_CONSUMABLES) && this.playerData.getRPG().canUse(getNBTItem(), true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean useOnItem(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull NBTItem paramNBTItem) {
/*  42 */     if (paramInventoryClickEvent.getClickedInventory() != paramInventoryClickEvent.getWhoClicked().getInventory()) {
/*  43 */       return false;
/*     */     }
/*     */     
/*  46 */     Type type = MMOItems.getType(paramNBTItem);
/*     */     
/*  48 */     for (ConsumableItemInteraction consumableItemInteraction : MMOItems.plugin.getStats().getConsumableActions()) {
/*  49 */       if (consumableItemInteraction.handleConsumableEffect(paramInventoryClickEvent, this.playerData, this, paramNBTItem, type))
/*  50 */         return true; 
/*     */     } 
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ConsumableConsumeResult useOnPlayer(EquipmentSlot paramEquipmentSlot, boolean paramBoolean) {
/*  60 */     NBTItem nBTItem = getNBTItem();
/*     */ 
/*     */     
/*  63 */     if (nBTItem.getBoolean(ItemStats.INEDIBLE.getNBTPath())) {
/*  64 */       return ConsumableConsumeResult.CANCEL;
/*     */     }
/*     */     
/*  67 */     ConsumableConsumedEvent consumableConsumedEvent = new ConsumableConsumedEvent(this.playerData, this.mmoitem, this);
/*  68 */     Bukkit.getPluginManager().callEvent((Event)consumableConsumedEvent);
/*  69 */     if (consumableConsumedEvent.isCancelled()) {
/*  70 */       return ConsumableConsumeResult.CANCEL;
/*     */     }
/*     */     
/*  73 */     for (PlayerConsumable playerConsumable : MMOItems.plugin.getStats().getPlayerConsumables()) {
/*  74 */       playerConsumable.onConsume(this.mmoitem, this.player, paramBoolean);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     int i = nBTItem.getInteger(ItemStats.MAX_CONSUME.getNBTPath());
/*  84 */     if (i > 1) {
/*  85 */       i--;
/*  86 */       nBTItem.addTag(new ItemTag[] { new ItemTag(ItemStats.MAX_CONSUME.getNBTPath(), Integer.valueOf(i)) });
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  91 */       String str1 = MythicLib.inst().parseColors(MMOItems.plugin.getLanguage().getStatFormat("max-consume"));
/*  92 */       String str2 = str1.replace("{value}", String.valueOf(i + 1));
/*  93 */       String str3 = str1.replace("{value}", String.valueOf(i));
/*  94 */       ItemStack itemStack1 = (new LoreUpdate(nBTItem.toItem(), str2, str3)).updateLore();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  99 */       ItemStack itemStack2 = nBTItem.getItem();
/* 100 */       if (itemStack2.getAmount() > 1) {
/* 101 */         itemStack1.setAmount(1);
/* 102 */         this.player.getInventory().setItem(paramEquipmentSlot, itemStack1);
/* 103 */         itemStack2.setAmount(itemStack2.getAmount() - 1);
/* 104 */         (new SmartGive(this.player)).give(new ItemStack[] { itemStack2 });
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 110 */         this.player.getInventory().setItem(paramEquipmentSlot, itemStack1);
/*     */       } 
/* 112 */       return ConsumableConsumeResult.NOT_CONSUME;
/*     */     } 
/*     */     
/* 115 */     return (!consumableConsumedEvent.isConsumed() || nBTItem.getBoolean(ItemStats.DISABLE_RIGHT_CLICK_CONSUME.getNBTPath())) ? ConsumableConsumeResult.NOT_CONSUME : ConsumableConsumeResult.CONSUME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasVanillaEating() {
/* 123 */     return ((getItem().getType().isEdible() || getItem().getType() == Material.POTION || getItem().getType() == Material.MILK_BUCKET) && 
/* 124 */       getNBTItem().hasTag("MMOITEMS_VANILLA_EATING"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ConsumableConsumeResult
/*     */   {
/* 134 */     CANCEL,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     CONSUME,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     NOT_CONSUME;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\Consumable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */