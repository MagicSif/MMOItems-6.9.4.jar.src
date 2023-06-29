/*    */ package net.Indyuce.mmoitems.comp.denizen;
/*    */ 
/*    */ import com.denizenscript.denizen.objects.ItemTag;
/*    */ import com.denizenscript.denizencore.objects.ObjectFetcher;
/*    */ import com.denizenscript.denizencore.objects.ObjectTag;
/*    */ import com.denizenscript.denizencore.objects.core.MapTag;
/*    */ import com.denizenscript.denizencore.objects.properties.PropertyParser;
/*    */ import com.denizenscript.denizencore.tags.Attribute;
/*    */ import com.denizenscript.denizencore.tags.TagManager;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DenizenHook
/*    */ {
/*    */   public DenizenHook() {
/* 23 */     ObjectFetcher.registerWithObjectFetcher(MMOItemTemplateTag.class, MMOItemTemplateTag.tagProcessor);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 28 */     PropertyParser.registerProperty(MMOItemsItemProperty.class, ItemTag.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 36 */     TagManager.registerTagHandler("mmoitem_template", paramAttribute -> {
/*    */           if (!paramAttribute.hasContext(1)) {
/*    */             paramAttribute.echoError("Please provide an item type and ID.");
/*    */             return null;
/*    */           } 
/*    */           MapTag mapTag = (MapTag)paramAttribute.contextAsType(1, MapTag.class);
/*    */           if (mapTag == null) {
/*    */             paramAttribute.echoError("Invalid MapTag input");
/*    */             return null;
/*    */           } 
/*    */           ObjectTag objectTag1 = mapTag.getObject("type");
/*    */           ObjectTag objectTag2 = mapTag.getObject("id");
/*    */           if (objectTag1 == null || objectTag2 == null) {
/*    */             paramAttribute.echoError("Invalid MapTag input - missing 'type' or 'id'");
/*    */             return null;
/*    */           } 
/*    */           String str1 = objectTag1.toString().replace("-", "_").toUpperCase();
/*    */           Type type = MMOItems.plugin.getTypes().get(str1);
/*    */           if (type == null) {
/*    */             paramAttribute.echoError("Invalid type - cannot find type with name '" + str1 + "'");
/*    */             return null;
/*    */           } 
/*    */           String str2 = objectTag2.toString().replace("-", "_").toUpperCase();
/*    */           if (!MMOItems.plugin.getTemplates().hasTemplate(type, str2)) {
/*    */             paramAttribute.echoError("Invalid template ID - cannot find template with name '" + str2 + "'");
/*    */             return null;
/*    */           } 
/*    */           return new MMOItemTemplateTag(type, str2);
/*    */         });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\denizen\DenizenHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */