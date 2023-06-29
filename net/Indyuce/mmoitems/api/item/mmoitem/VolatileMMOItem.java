/*    */ package net.Indyuce.mmoitems.api.item.mmoitem;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import java.util.logging.Level;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VolatileMMOItem
/*    */   extends ReadMMOItem
/*    */ {
/*    */   public VolatileMMOItem(NBTItem paramNBTItem) {
/* 30 */     super(paramNBTItem);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasData(@NotNull ItemStat paramItemStat) {
/* 41 */     if (!super.hasData(paramItemStat)) {
/*    */       
/*    */       try {
/*    */         
/* 45 */         paramItemStat.whenLoaded(this);
/*    */       
/*    */       }
/* 48 */       catch (RuntimeException runtimeException) {
/*    */ 
/*    */         
/* 51 */         MMOItems.plugin.getLogger().log(Level.WARNING, ChatColor.GRAY + "Could not load stat '" + ChatColor.GOLD + paramItemStat
/*    */             
/* 53 */             .getId() + ChatColor.GRAY + "'item data from '" + ChatColor.RED + 
/* 54 */             getId() + ChatColor.GRAY + "': " + ChatColor.YELLOW + runtimeException
/* 55 */             .getMessage());
/*    */       } 
/*    */     }
/* 58 */     return super.hasData(paramItemStat);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStackBuilder newBuilder() {
/* 64 */     throw new UnsupportedOperationException("Cannot build a VolatileMMOItem");
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\mmoitem\VolatileMMOItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */