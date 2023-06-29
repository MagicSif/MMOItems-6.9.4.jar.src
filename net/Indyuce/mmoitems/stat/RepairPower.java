/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.CustomSound;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*    */ import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.listener.CustomSoundListener;
/*    */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*    */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import net.Indyuce.mmoitems.util.RepairUtils;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class RepairPower
/*    */   extends DoubleStat implements ConsumableItemInteraction {
/*    */   public RepairPower() {
/* 26 */     super("REPAIR", Material.ANVIL, "Repair Power", new String[] { "The flat amount of durability your item", "can repair when set an item." }, new String[] { "consumable" }, new Material[0]);
/*    */   }
/*    */ 
/*    */   
/*    */   private static final String REPAIR_TYPE_TAG = "MMOITEMS_REPAIR_TYPE";
/*    */ 
/*    */   
/*    */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, Type paramType) {
/* 34 */     int i = (int)paramConsumable.getNBTItem().getStat(ItemStats.REPAIR.getId());
/* 35 */     if (i <= 0) {
/* 36 */       return false;
/*    */     }
/*    */     
/* 39 */     Player player = paramPlayerData.getPlayer();
/* 40 */     String str1 = paramConsumable.getNBTItem().getString("MMOITEMS_REPAIR_TYPE");
/* 41 */     String str2 = paramNBTItem.getString("MMOITEMS_REPAIR_TYPE");
/* 42 */     if (!MMOUtils.checkReference(str1, str2)) {
/* 43 */       Message.UNABLE_TO_REPAIR.format(ChatColor.RED, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem()) }).send(player);
/* 44 */       player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/* 45 */       return false;
/*    */     } 
/*    */ 
/*    */     
/* 49 */     if (paramNBTItem.hasTag("MMOITEMS_DURABILITY")) {
/*    */       
/* 51 */       DurabilityItem durabilityItem = new DurabilityItem(player, paramNBTItem);
/* 52 */       if (durabilityItem.getDurability() < durabilityItem.getMaxDurability()) {
/* 53 */         paramNBTItem.getItem().setItemMeta(durabilityItem.addDurability(i).toItem().getItemMeta());
/* 54 */         Message.REPAIRED_ITEM
/* 55 */           .format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem()), "#amount#", String.valueOf(i)
/* 56 */             }).send(player);
/* 57 */         CustomSoundListener.playSound(paramConsumable.getItem(), CustomSound.ON_CONSUME, player);
/*    */       } 
/* 59 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 63 */     return RepairUtils.repairVanillaItem(paramPlayerData, paramNBTItem, paramConsumable, i);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RepairPower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */