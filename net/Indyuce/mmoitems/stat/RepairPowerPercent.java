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
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class RepairPowerPercent extends DoubleStat implements ConsumableItemInteraction {
/*    */   public RepairPowerPercent() {
/* 26 */     super("REPAIR_PERCENT", Material.DAMAGED_ANVIL, "Repair Percentage", new String[] { "The percentage of total durability to repair", "When dropped onto an item." }, new String[] { "consumable" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static final String REPAIR_TYPE_TAG = "MMOITEMS_REPAIR_TYPE";
/*    */ 
/*    */   
/*    */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, @Nullable Type paramType) {
/* 35 */     double d1 = paramConsumable.getNBTItem().getStat(ItemStats.REPAIR_PERCENT.getId());
/* 36 */     if (d1 <= 0.0D) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     Player player = paramPlayerData.getPlayer();
/* 41 */     String str1 = paramConsumable.getNBTItem().getString("MMOITEMS_REPAIR_TYPE");
/* 42 */     String str2 = paramNBTItem.getString("MMOITEMS_REPAIR_TYPE");
/* 43 */     if (!MMOUtils.checkReference(str1, str2)) {
/* 44 */       Message.UNABLE_TO_REPAIR.format(ChatColor.RED, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem()) }).send(player);
/* 45 */       player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/* 46 */       return false;
/*    */     } 
/*    */     
/* 49 */     boolean bool = paramNBTItem.hasTag("MMOITEMS_DURABILITY");
/* 50 */     double d2 = bool ? paramNBTItem.getDouble("MMOITEMS_MAX_DURABILITY") : paramNBTItem.getItem().getType().getMaxDurability();
/* 51 */     int i = (int)(d1 * d2 / 100.0D);
/*    */ 
/*    */     
/* 54 */     if (bool) {
/*    */       
/* 56 */       DurabilityItem durabilityItem = new DurabilityItem(player, paramNBTItem);
/* 57 */       if (durabilityItem.getDurability() < durabilityItem.getMaxDurability()) {
/* 58 */         paramNBTItem.getItem().setItemMeta(durabilityItem.addDurability(i).toItem().getItemMeta());
/* 59 */         Message.REPAIRED_ITEM
/* 60 */           .format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramNBTItem.getItem()), "#amount#", String.valueOf(i)
/* 61 */             }).send(player);
/* 62 */         CustomSoundListener.playSound(paramConsumable.getItem(), CustomSound.ON_CONSUME, player);
/*    */       } 
/* 64 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 68 */     return RepairUtils.repairVanillaItem(paramPlayerData, paramNBTItem, paramConsumable, i);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RepairPowerPercent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */