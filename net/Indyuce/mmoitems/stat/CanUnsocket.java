/*    */ package net.Indyuce.mmoitems.stat;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.util.message.Message;
/*    */ import net.Indyuce.mmoitems.stat.data.GemSocketsData;
/*    */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*    */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.InventoryClickEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CanUnsocket
/*    */   extends BooleanStat
/*    */   implements ConsumableItemInteraction
/*    */ {
/*    */   public CanUnsocket() {
/* 34 */     super("CAN_UNSOCKET", Material.PAPER, "Can Unsocket?", new String[] { "This item, when used on another item, if", "that other item has Gem Stones", "may be used to remove those Gems." }, new String[] { "consumable" }, new Material[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, Type paramType) {
/* 45 */     if (paramType == null) return false;
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 50 */     VolatileMMOItem volatileMMOItem = new VolatileMMOItem(paramNBTItem);
/* 51 */     if (!volatileMMOItem.hasData(ItemStats.GEM_SOCKETS)) return false; 
/* 52 */     GemSocketsData gemSocketsData = (GemSocketsData)volatileMMOItem.getData(ItemStats.GEM_SOCKETS);
/* 53 */     if (gemSocketsData == null || gemSocketsData.getGemstones().size() == 0) return false; 
/* 54 */     Player player = paramPlayerData.getPlayer();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 61 */     LiveMMOItem liveMMOItem = new LiveMMOItem(paramNBTItem);
/* 62 */     List list = liveMMOItem.extractGemstones();
/* 63 */     if (list.isEmpty()) {
/* 64 */       Message.RANDOM_UNSOCKET_GEM_TOO_OLD.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramInventoryClickEvent.getCurrentItem()) }).send(player);
/* 65 */       return false;
/*    */     } 
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\CanUnsocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */