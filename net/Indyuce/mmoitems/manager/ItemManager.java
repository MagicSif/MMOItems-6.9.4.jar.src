/*    */ package net.Indyuce.mmoitems.manager;
/*    */ 
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemManager
/*    */ {
/*    */   @Deprecated
/*    */   public MMOItem getMMOItem(Type paramType, String paramString) {
/* 26 */     return MMOItems.plugin.getMMOItem(paramType, paramString);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public ItemStack getItem(Type paramType, String paramString) {
/* 37 */     return MMOItems.plugin.getItem(paramType, paramString);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\ItemManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */