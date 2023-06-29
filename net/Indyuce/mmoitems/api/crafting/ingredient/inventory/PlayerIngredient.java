/*    */ package net.Indyuce.mmoitems.api.crafting.ingredient.inventory;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PlayerIngredient
/*    */ {
/*    */   private final ItemStack item;
/*    */   
/*    */   public PlayerIngredient(NBTItem paramNBTItem) {
/* 49 */     this.item = paramNBTItem.getItem();
/*    */   }
/*    */   
/*    */   public ItemStack getItem() {
/* 53 */     return this.item;
/*    */   }
/*    */   
/*    */   public int getAmount() {
/* 57 */     return this.item.getAmount();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\inventory\PlayerIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */