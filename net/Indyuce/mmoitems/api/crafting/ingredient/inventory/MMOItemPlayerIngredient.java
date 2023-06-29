/*    */ package net.Indyuce.mmoitems.api.crafting.ingredient.inventory;
/*    */ 
/*    */ import com.google.gson.JsonParser;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
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
/*    */ public class MMOItemPlayerIngredient
/*    */   extends PlayerIngredient
/*    */ {
/*    */   private final String type;
/*    */   private final String id;
/*    */   private final int upgradeLevel;
/*    */   
/*    */   public MMOItemPlayerIngredient(NBTItem paramNBTItem) {
/* 24 */     super(paramNBTItem);
/*    */     
/* 26 */     this.type = paramNBTItem.getString("MMOITEMS_ITEM_TYPE");
/* 27 */     this.id = paramNBTItem.getString("MMOITEMS_ITEM_ID");
/*    */     
/* 29 */     String str = paramNBTItem.getString("MMOITEMS_UPGRADE");
/* 30 */     this.upgradeLevel = !str.isEmpty() ? (new JsonParser()).parse(str).getAsJsonObject().get("Level").getAsInt() : 0;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 34 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 38 */     return this.id;
/*    */   }
/*    */   
/*    */   public int getUpgradeLevel() {
/* 42 */     return this.upgradeLevel;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\inventory\MMOItemPlayerIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */