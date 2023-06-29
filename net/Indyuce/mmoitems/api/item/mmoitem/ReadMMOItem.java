/*    */ package net.Indyuce.mmoitems.api.item.mmoitem;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.Damageable;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ReadMMOItem
/*    */   extends MMOItem
/*    */ {
/*    */   @NotNull
/*    */   private final NBTItem item;
/*    */   
/*    */   public ReadMMOItem(@NotNull NBTItem paramNBTItem) {
/* 22 */     super(Type.get(paramNBTItem.getType()), paramNBTItem.getString("MMOITEMS_ITEM_ID"));
/*    */     
/* 24 */     this.item = paramNBTItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public int getDamage() {
/* 32 */     if (hasData(ItemStats.MAX_DURABILITY)) {
/* 33 */       return getNBT().hasTag("MMOITEMS_DURABILITY") ? (getNBT().getInteger("MMOITEMS_MAX_DURABILITY") - getNBT().getInteger("MMOITEMS_DURABILITY")) : 0;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 39 */     ItemStack itemStack = getNBT().getItem();
/*    */ 
/*    */     
/* 42 */     if (itemStack.hasItemMeta()) {
/* 43 */       ItemMeta itemMeta = itemStack.getItemMeta();
/*    */ 
/*    */       
/* 46 */       if (itemMeta instanceof Damageable)
/*    */       {
/*    */         
/* 49 */         return ((Damageable)itemMeta).getDamage();
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 54 */     return 0;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public NBTItem getNBT() {
/* 59 */     return this.item;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\mmoitem\ReadMMOItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */