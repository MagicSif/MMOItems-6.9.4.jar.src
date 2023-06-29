/*    */ package net.Indyuce.mmoitems.api.recipe.workbench.ingredients;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ 
/*    */ public class MMOItemIngredient
/*    */   extends WorkbenchIngredient {
/*    */   private final Type type;
/*    */   private final String id;
/*    */   
/*    */   public MMOItemIngredient(Type paramType, String paramString, int paramInt) {
/* 15 */     super(paramInt);
/*    */     
/* 17 */     this.type = paramType;
/* 18 */     this.id = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean corresponds(ItemStack paramItemStack) {
/* 23 */     NBTItem nBTItem = NBTItem.get(paramItemStack);
/* 24 */     return (this.type.equals(Type.get(nBTItem.getType())) && nBTItem.getString("MMOITEMS_ITEM_ID").equalsIgnoreCase(this.id));
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack generateItem() {
/* 29 */     return MMOItems.plugin.getItem(this.type, this.id);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RecipeChoice toBukkit() {
/* 35 */     return (RecipeChoice)new RecipeChoice.ExactChoice(generateItem());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\recipe\workbench\ingredients\MMOItemIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */