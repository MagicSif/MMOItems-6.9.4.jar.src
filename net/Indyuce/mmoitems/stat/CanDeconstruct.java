/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ItemTier;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.event.item.DeconstructItemEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.Consumable;
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
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class CanDeconstruct
/*    */   extends BooleanStat implements ConsumableItemInteraction {
/*    */   public CanDeconstruct() {
/* 28 */     super("CAN_DECONSTRUCT", Material.PAPER, "Can Deconstruct?", new String[] { "Players can deconstruct their item", "using this consumable, creating", "another random item." }, new String[] { "consumable" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, Type paramType) {
/* 35 */     String str = paramNBTItem.getString("MMOITEMS_TIER");
/* 36 */     if (str.equals("") || !paramConsumable.getNBTItem().getBoolean("MMOITEMS_CAN_DECONSTRUCT")) {
/* 37 */       return false;
/*    */     }
/* 39 */     ItemTier itemTier = MMOItems.plugin.getTiers().get(str);
/* 40 */     List list = itemTier.getDeconstructedLoot(paramPlayerData);
/* 41 */     if (list.isEmpty()) {
/* 42 */       return false;
/*    */     }
/* 44 */     DeconstructItemEvent deconstructItemEvent = new DeconstructItemEvent(paramPlayerData, paramConsumable.getMMOItem(), paramNBTItem, list);
/* 45 */     Bukkit.getPluginManager().callEvent((Event)deconstructItemEvent);
/* 46 */     if (deconstructItemEvent.isCancelled()) {
/* 47 */       return false;
/*    */     }
/* 49 */     Player player = paramPlayerData.getPlayer();
/* 50 */     Message.SUCCESSFULLY_DECONSTRUCTED.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramInventoryClickEvent.getCurrentItem()) }).send(player);
/* 51 */     paramInventoryClickEvent.getCurrentItem().setAmount(paramInventoryClickEvent.getCurrentItem().getAmount() - 1);
/* 52 */     for (ItemStack itemStack : player.getInventory().addItem((ItemStack[])list.toArray((Object[])new ItemStack[0])).values())
/* 53 */       player.getWorld().dropItem(player.getLocation(), itemStack); 
/* 54 */     player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/* 55 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CanDeconstruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */