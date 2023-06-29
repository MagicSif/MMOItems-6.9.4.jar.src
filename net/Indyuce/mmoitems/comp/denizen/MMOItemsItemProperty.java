/*    */ package net.Indyuce.mmoitems.comp.denizen;
/*    */ 
/*    */ import com.denizenscript.denizen.objects.ItemTag;
/*    */ import com.denizenscript.denizencore.objects.Mechanism;
/*    */ import com.denizenscript.denizencore.objects.ObjectTag;
/*    */ import com.denizenscript.denizencore.objects.core.ElementTag;
/*    */ import com.denizenscript.denizencore.objects.properties.Property;
/*    */ import com.denizenscript.denizencore.objects.properties.PropertyParser;
/*    */ import com.denizenscript.denizencore.tags.Attribute;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import net.Indyuce.mmoitems.api.item.util.identify.IdentifiedItem;
/*    */ 
/*    */ public class MMOItemsItemProperty
/*    */   implements Property {
/*    */   public MMOItemsItemProperty(ItemTag paramItemTag) {
/* 16 */     this.item = paramItemTag;
/*    */   }
/*    */   private final ItemTag item;
/*    */   
/*    */   public String getPropertyString() {
/* 21 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getPropertyId() {
/* 26 */     return "MMOItemsItem";
/*    */   }
/*    */   
/*    */   public boolean isMMOItem() {
/* 30 */     return NBTItem.get(this.item.getItemStack()).hasTag("MMOITEMS_TYPE");
/*    */   }
/*    */   
/*    */   public MMOItemTag getMMOItem() {
/* 34 */     return new MMOItemTag(this.item);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void adjust(Mechanism paramMechanism) {}
/*    */ 
/*    */   
/*    */   public static boolean describes(ObjectTag paramObjectTag) {
/* 43 */     return paramObjectTag instanceof ItemTag;
/*    */   }
/*    */   
/*    */   public static MMOItemsItemProperty getFrom(ObjectTag paramObjectTag) {
/* 47 */     return (paramObjectTag instanceof ItemTag) ? new MMOItemsItemProperty((ItemTag)paramObjectTag) : null;
/*    */   }
/*    */   
/* 50 */   public static final String[] handledMechs = new String[0];
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
/*    */   public static void registerTags() {
/* 64 */     PropertyParser.registerTag("is_mmoitem", (paramAttribute, paramMMOItemsItemProperty) -> new ElementTag(paramMMOItemsItemProperty.isMMOItem()), new String[0]);
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
/* 75 */     PropertyParser.registerTag("is_unidentified_item", (paramAttribute, paramMMOItemsItemProperty) -> new ElementTag(NBTItem.get(paramMMOItemsItemProperty.item.getItemStack()).hasTag("MMOITEMS_UNIDENTIFIED_ITEM")), new String[0]);
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
/* 86 */     PropertyParser.registerTag("identify", (paramAttribute, paramMMOItemsItemProperty) -> new MMOItemTag(new ItemTag((new IdentifiedItem(NBTItem.get(paramMMOItemsItemProperty.item.getItemStack()))).identify())), new String[0]);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\denizen\MMOItemsItemProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */