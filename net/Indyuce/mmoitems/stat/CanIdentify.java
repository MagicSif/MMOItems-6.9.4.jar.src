/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.event.item.IdentifyItemEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*    */ import net.Indyuce.mmoitems.api.item.util.identify.IdentifiedItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CanIdentify extends BooleanStat implements ConsumableItemInteraction {
/*    */   public CanIdentify() {
/* 24 */     super("CAN_IDENTIFY", Material.PAPER, "Can Identify?", new String[] { "Players can identify & make their", "item usable using this consumable." }, new String[] { "consumable" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, Type paramType) {
/* 30 */     if (paramType != null) {
/* 31 */       return false;
/*    */     }
/* 33 */     if (!paramConsumable.getNBTItem().getBoolean("MMOITEMS_CAN_IDENTIFY") || !paramNBTItem.hasTag("MMOITEMS_UNIDENTIFIED_ITEM")) {
/* 34 */       return false;
/*    */     }
/* 36 */     Player player = paramPlayerData.getPlayer();
/* 37 */     if (paramNBTItem.getItem().getAmount() > 1) {
/* 38 */       Message.CANNOT_IDENTIFY_STACKED_ITEMS.format(ChatColor.RED, new String[0]).send(player);
/* 39 */       return false;
/*    */     } 
/*    */     
/* 42 */     IdentifyItemEvent identifyItemEvent = new IdentifyItemEvent(paramPlayerData, paramConsumable.getMMOItem(), paramNBTItem);
/* 43 */     Bukkit.getPluginManager().callEvent((Event)identifyItemEvent);
/* 44 */     if (identifyItemEvent.isCancelled()) {
/* 45 */       return false;
/*    */     }
/* 47 */     paramInventoryClickEvent.setCurrentItem((new IdentifiedItem(paramNBTItem)).identify());
/* 48 */     Message.SUCCESSFULLY_IDENTIFIED.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem()) }).send(player);
/* 49 */     player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/* 50 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CanIdentify.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */