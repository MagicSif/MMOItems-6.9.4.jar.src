/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import java.util.Random;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.event.item.BreakSoulboundEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.stat.data.SoulboundData;
/*    */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class SoulbindingBreakChance
/*    */   extends DoubleStat implements ConsumableItemInteraction {
/* 28 */   private static final Random random = new Random();
/*    */   
/*    */   public SoulbindingBreakChance() {
/* 31 */     super("SOULBOUND_BREAK_CHANCE", VersionMaterial.ENDER_EYE.toMaterial(), "Soulbound Break Chance", new String[] { "The chance of breaking an item's", "soulbound when drag & drop'd on it.", "This chance is lowered depending", "on the soulbound's level." }, new String[] { "consumable" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, Type paramType) {
/* 39 */     Player player = paramPlayerData.getPlayer();
/*    */     
/* 41 */     double d = paramConsumable.getNBTItem().getStat("SOULBOUND_BREAK_CHANCE");
/* 42 */     if (d <= 0.0D) {
/* 43 */       return false;
/*    */     }
/* 45 */     VolatileMMOItem volatileMMOItem = new VolatileMMOItem(paramNBTItem);
/* 46 */     if (!volatileMMOItem.hasData(ItemStats.SOULBOUND)) {
/* 47 */       Message.NO_SOULBOUND.format(ChatColor.RED, new String[0]).send(player);
/* 48 */       player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
/* 49 */       return false;
/*    */     } 
/*    */ 
/*    */     
/* 53 */     SoulboundData soulboundData = (SoulboundData)volatileMMOItem.getData(ItemStats.SOULBOUND);
/* 54 */     if (Math.max(1.0D, paramConsumable.getNBTItem().getStat(ItemStats.SOULBOUND_LEVEL.getId())) < soulboundData.getLevel()) {
/* 55 */       Message.LOW_SOULBOUND_LEVEL.format(ChatColor.RED, new String[] { "#level#", MMOUtils.intToRoman(soulboundData.getLevel()) }).send(player);
/* 56 */       return false;
/*    */     } 
/*    */     
/* 59 */     if (random.nextDouble() < d / 100.0D) {
/* 60 */       BreakSoulboundEvent breakSoulboundEvent = new BreakSoulboundEvent(paramPlayerData, paramConsumable.getMMOItem(), paramNBTItem);
/* 61 */       Bukkit.getPluginManager().callEvent((Event)breakSoulboundEvent);
/* 62 */       if (breakSoulboundEvent.isCancelled())
/* 63 */         return false; 
/*    */       LiveMMOItem liveMMOItem;
/* 65 */       (liveMMOItem = new LiveMMOItem(paramNBTItem)).removeData(ItemStats.SOULBOUND);
/* 66 */       paramNBTItem.getItem().setItemMeta(liveMMOItem.newBuilder().build().getItemMeta());
/* 67 */       Message.SUCCESSFULLY_BREAK_BIND.format(ChatColor.YELLOW, new String[] { "#level#", MMOUtils.intToRoman(soulboundData.getLevel()) }).send(player);
/* 68 */       player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 2.0F);
/*    */     } else {
/*    */       
/* 71 */       Message.UNSUCCESSFUL_SOULBOUND_BREAK.format(ChatColor.RED, new String[0]).send(player);
/* 72 */       player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 0.0F);
/*    */     } 
/*    */     
/* 75 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\SoulbindingBreakChance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */