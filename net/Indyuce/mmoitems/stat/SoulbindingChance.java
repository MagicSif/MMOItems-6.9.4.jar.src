/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import java.util.Random;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.event.item.ApplySoulboundEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.stat.data.SoulboundData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
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
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class SoulbindingChance extends DoubleStat implements ConsumableItemInteraction {
/* 29 */   private static final Random random = new Random();
/*    */   
/*    */   public SoulbindingChance() {
/* 32 */     super("SOULBINDING_CHANCE", VersionMaterial.ENDER_EYE.toMaterial(), "Soulbinding Chance", new String[] { "Defines the chance your item has to", "link another item to your soul,", "preventing other players from using it." }, new String[] { "consumable" }, new org.bukkit.Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, @Nullable Type paramType) {
/* 39 */     Player player = paramPlayerData.getPlayer();
/*    */ 
/*    */     
/* 42 */     if (paramType == null) return false;
/*    */     
/* 44 */     double d = paramConsumable.getNBTItem().getStat("SOULBINDING_CHANCE");
/* 45 */     if (d <= 0.0D) {
/* 46 */       return false;
/*    */     }
/* 48 */     if (paramNBTItem.getItem().getAmount() > 1) {
/* 49 */       Message.CANT_BIND_STACKED.format(ChatColor.RED, new String[0]).send(player);
/* 50 */       return false;
/*    */     } 
/*    */     
/* 53 */     VolatileMMOItem volatileMMOItem = new VolatileMMOItem(paramNBTItem);
/* 54 */     if (volatileMMOItem.hasData(ItemStats.SOULBOUND)) {
/* 55 */       SoulboundData soulboundData = (SoulboundData)volatileMMOItem.getData(ItemStats.SOULBOUND);
/* 56 */       Message.CANT_BIND_ITEM.format(ChatColor.RED, new String[] { "#player#", soulboundData.getName(), "#level#", MMOUtils.intToRoman(soulboundData.getLevel()) }).send(player);
/* 57 */       return false;
/*    */     } 
/*    */     
/* 60 */     if (random.nextDouble() < d / 100.0D) {
/* 61 */       ApplySoulboundEvent applySoulboundEvent = new ApplySoulboundEvent(paramPlayerData, paramConsumable.getMMOItem(), paramNBTItem);
/* 62 */       Bukkit.getPluginManager().callEvent((Event)applySoulboundEvent);
/* 63 */       if (applySoulboundEvent.isCancelled()) {
/* 64 */         return false;
/*    */       }
/* 66 */       int i = (int)Math.max(1.0D, paramConsumable.getNBTItem().getStat("SOULBOUND_LEVEL")); LiveMMOItem liveMMOItem;
/* 67 */       (liveMMOItem = new LiveMMOItem(paramNBTItem)).setData(ItemStats.SOULBOUND, (StatData)new SoulboundData(player
/* 68 */             .getUniqueId(), player.getName(), i));
/* 69 */       paramNBTItem.getItem().setItemMeta(liveMMOItem.newBuilder().build().getItemMeta());
/* 70 */       Message.SUCCESSFULLY_BIND_ITEM
/* 71 */         .format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem()), "#level#", MMOUtils.intToRoman(i)
/* 72 */           }).send(player);
/* 73 */       player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/* 74 */       return true;
/*    */     } 
/*    */     
/* 77 */     Message.UNSUCCESSFUL_SOULBOUND.format(ChatColor.RED, new String[0]).send(player);
/* 78 */     player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.0F);
/* 79 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\SoulbindingChance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */