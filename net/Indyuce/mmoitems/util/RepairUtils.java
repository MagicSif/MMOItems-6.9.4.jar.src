/*    */ package net.Indyuce.mmoitems.util;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.CustomSound;
/*    */ import net.Indyuce.mmoitems.api.event.item.RepairItemEvent;
/*    */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.listener.CustomSoundListener;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.inventory.meta.Damageable;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RepairUtils
/*    */ {
/*    */   public RepairUtils() {
/* 25 */     throw new IllegalStateException("Utility class");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static boolean repairVanillaItem(@NotNull PlayerData paramPlayerData, @NotNull NBTItem paramNBTItem, @NotNull Consumable paramConsumable, int paramInt) {
/* 39 */     Player player = paramPlayerData.getPlayer();
/* 40 */     if (paramNBTItem.getBoolean("Unbreakable") || 
/* 41 */       !paramNBTItem.getItem().hasItemMeta() || 
/* 42 */       !(paramNBTItem.getItem().getItemMeta() instanceof Damageable) || ((Damageable)paramNBTItem
/* 43 */       .getItem().getItemMeta()).getDamage() <= 0) {
/* 44 */       return false;
/*    */     }
/* 46 */     RepairItemEvent repairItemEvent = new RepairItemEvent(paramPlayerData, paramConsumable.getMMOItem(), paramNBTItem, paramInt);
/* 47 */     Bukkit.getPluginManager().callEvent((Event)repairItemEvent);
/* 48 */     if (repairItemEvent.isCancelled()) {
/* 49 */       return false;
/*    */     }
/* 51 */     paramInt = repairItemEvent.getRepaired();
/* 52 */     Damageable damageable = (Damageable)paramNBTItem.getItem().getItemMeta();
/* 53 */     damageable.setDamage(Math.max(0, damageable.getDamage() - paramInt));
/* 54 */     paramNBTItem.getItem().setItemMeta((ItemMeta)damageable);
/* 55 */     Message.REPAIRED_ITEM.format(ChatColor.YELLOW, new String[] { "#item#", 
/* 56 */           MMOUtils.getDisplayName(paramNBTItem.getItem()), "#amount#", 
/* 57 */           String.valueOf(paramInt)
/* 58 */         }).send(player);
/* 59 */     CustomSoundListener.playSound(paramConsumable.getItem(), CustomSound.ON_CONSUME, player);
/* 60 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitem\\util\RepairUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */