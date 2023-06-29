/*    */ package net.Indyuce.mmoitems.api.item.mmoitem;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.inventory.ItemStack;
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
/*    */ public class LiveMMOItem
/*    */   extends ReadMMOItem
/*    */ {
/*    */   public LiveMMOItem(ItemStack paramItemStack) {
/* 27 */     this(MythicLib.plugin.getVersion().getWrapper().getNBTItem(paramItemStack));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public LiveMMOItem(NBTItem paramNBTItem) {
/* 38 */     super(paramNBTItem);
/*    */ 
/*    */     
/* 41 */     for (ItemStat itemStat : getType().getAvailableStats()) {
/*    */ 
/*    */       
/*    */       try {
/*    */ 
/*    */         
/* 47 */         itemStat.whenLoaded(this);
/*    */ 
/*    */         
/* 50 */         if (getStatHistory(itemStat) == null) {
/*    */ 
/*    */           
/* 53 */           ItemTag itemTag = ItemTag.getTagAtPath("HSTRY_" + itemStat.getId(), getNBT(), SupportedNBTTagValues.STRING);
/*    */           
/* 55 */           if (itemTag != null)
/*    */           {
/* 57 */             StatHistory statHistory = StatHistory.fromNBTString(this, (String)itemTag.getValue());
/*    */ 
/*    */             
/* 60 */             if (statHistory != null) setStatHistory(itemStat, statHistory);
/*    */           
/*    */           }
/*    */         
/*    */         } 
/* 65 */       } catch (IllegalArgumentException illegalArgumentException) {
/* 66 */         MMOItems.plugin.getLogger().log(Level.WARNING, ChatColor.GRAY + "Could not load stat '" + ChatColor.GOLD + itemStat
/*    */             
/* 68 */             .getId() + ChatColor.GRAY + "'item data from '" + ChatColor.RED + 
/* 69 */             getId() + ChatColor.GRAY + "': " + ChatColor.YELLOW + illegalArgumentException
/* 70 */             .getMessage());
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\mmoitem\LiveMMOItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */