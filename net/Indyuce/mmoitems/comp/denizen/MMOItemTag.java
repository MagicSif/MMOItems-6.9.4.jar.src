/*    */ package net.Indyuce.mmoitems.comp.denizen;
/*    */ 
/*    */ import com.denizenscript.denizen.objects.ItemTag;
/*    */ import com.denizenscript.denizencore.objects.ObjectTag;
/*    */ import com.denizenscript.denizencore.tags.Attribute;
/*    */ import com.denizenscript.denizencore.tags.ObjectTagProcessor;
/*    */ 
/*    */ @Deprecated
/*    */ public class MMOItemTag extends SimpleTag {
/*    */   private final ItemTag item;
/*    */   
/*    */   @Deprecated
/*    */   public MMOItemTag(ItemTag paramItemTag) {
/* 14 */     this.item = paramItemTag;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isUnique() {
/* 19 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getObjectType() {
/* 24 */     return "MMOItem";
/*    */   }
/*    */ 
/*    */   
/*    */   public String identify() {
/* 29 */     return "mmoitem@" + this.item.identify();
/*    */   }
/*    */ 
/*    */   
/*    */   public String identifySimple() {
/* 34 */     return identify();
/*    */   }
/*    */   
/* 37 */   public static ObjectTagProcessor<MMOItemTag> tagProcessor = new ObjectTagProcessor();
/*    */ 
/*    */   
/*    */   public ObjectTag getObjectAttribute(Attribute paramAttribute) {
/* 41 */     return tagProcessor.getObjectAttribute(this, paramAttribute);
/*    */   }
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
/* 55 */     tagProcessor.registerTag("item", (paramAttribute, paramMMOItemTag) -> paramMMOItemTag.item, new String[0]);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\denizen\MMOItemTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */