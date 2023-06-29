/*    */ package net.Indyuce.mmoitems.comp.mythicmobs.crafting;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.PlayerIngredient;
/*    */ 
/*    */ @Deprecated
/*    */ public class MythicItemPlayerIngredient
/*    */   extends PlayerIngredient
/*    */ {
/*    */   private final String type;
/*    */   private final String id;
/*    */   
/*    */   public MythicItemPlayerIngredient(NBTItem paramNBTItem) {
/* 14 */     super(paramNBTItem);
/*    */     
/* 16 */     this.type = paramNBTItem.getString("MYTHIC_TYPE").toLowerCase();
/*    */ 
/*    */     
/* 19 */     this.id = null;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 23 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 27 */     return this.id;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mythicmobs\crafting\MythicItemPlayerIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */