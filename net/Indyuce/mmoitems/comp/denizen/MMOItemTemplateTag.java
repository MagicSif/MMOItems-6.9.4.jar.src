/*     */ package net.Indyuce.mmoitems.comp.denizen;
/*     */ 
/*     */ import com.denizenscript.denizen.objects.ItemTag;
/*     */ import com.denizenscript.denizen.objects.PlayerTag;
/*     */ import com.denizenscript.denizencore.objects.ObjectTag;
/*     */ import com.denizenscript.denizencore.objects.core.ElementTag;
/*     */ import com.denizenscript.denizencore.objects.core.MapTag;
/*     */ import com.denizenscript.denizencore.tags.Attribute;
/*     */ import com.denizenscript.denizencore.tags.ObjectTagProcessor;
/*     */ import com.denizenscript.denizencore.tags.TagContext;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ 
/*     */ public class MMOItemTemplateTag
/*     */   extends SimpleTag {
/*     */   private final Type type;
/*     */   private final String id;
/*  23 */   private static final Random random = new Random();
/*     */   
/*     */   public MMOItemTemplateTag(Type paramType, String paramString) {
/*  26 */     this.type = paramType;
/*  27 */     this.id = paramString;
/*     */   }
/*     */   
/*     */   public MMOItemTemplate getTemplate() {
/*  31 */     return MMOItems.plugin.getTemplates().getTemplate(this.type, this.id);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isUnique() {
/*  36 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getObjectType() {
/*  41 */     return "MMOItemTemplate";
/*     */   }
/*     */ 
/*     */   
/*     */   public String identify() {
/*  46 */     return "mmoitem_template@" + this.type.getId() + "." + this.id;
/*     */   }
/*     */ 
/*     */   
/*     */   public String identifySimple() {
/*  51 */     return identify();
/*     */   }
/*     */   
/*  54 */   public static ObjectTagProcessor<MMOItemTemplateTag> tagProcessor = new ObjectTagProcessor();
/*     */ 
/*     */   
/*     */   public ObjectTag getObjectAttribute(Attribute paramAttribute) {
/*  58 */     return tagProcessor.getObjectAttribute(this, paramAttribute);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerTags() {
/*  64 */     tagProcessor.registerTag("item_type", (paramAttribute, paramMMOItemTemplateTag) -> new ElementTag(paramMMOItemTemplateTag.type.getName()), new String[0]);
/*     */ 
/*     */     
/*  67 */     tagProcessor.registerTag("item_id", (paramAttribute, paramMMOItemTemplateTag) -> new ElementTag(paramMMOItemTemplateTag.id), new String[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     tagProcessor.registerTag("generate", (paramAttribute, paramMMOItemTemplateTag) -> {
/*     */           if (!paramAttribute.hasContext(1)) {
/*     */             return (ObjectTag)new ItemTag(paramMMOItemTemplateTag.getTemplate().newBuilder().build().newBuilder().build());
/*     */           }
/*     */           
/*     */           MapTag mapTag = (MapTag)paramAttribute.contextAsType(1, MapTag.class);
/*     */           if (mapTag == null) {
/*     */             paramAttribute.echoError("Invalid MapTag input");
/*     */             return null;
/*     */           } 
/*     */           ObjectTag objectTag1 = mapTag.getObject("player");
/*     */           if (objectTag1 != null && !(objectTag1 instanceof PlayerTag)) {
/*     */             paramAttribute.echoError("Bad player input type");
/*     */             return null;
/*     */           } 
/*     */           ObjectTag objectTag2 = mapTag.getObject("level");
/*     */           int i = -1;
/*     */           if (objectTag2 != null) {
/*     */             try {
/*     */               i = Integer.parseInt(objectTag2.toString());
/*  99 */             } catch (IllegalArgumentException illegalArgumentException) {
/*     */               paramAttribute.echoError("Bad level input: " + objectTag2 + " is not a valid integer");
/*     */               
/*     */               return null;
/*     */             } 
/*     */           }
/*     */           ObjectTag objectTag3 = mapTag.getObject("match-level");
/* 106 */           boolean bool = (objectTag3 != null && Boolean.parseBoolean(objectTag3.toString())) ? true : false;
/*     */           
/*     */           ObjectTag objectTag4 = mapTag.getObject("tier");
/*     */           ItemTier itemTier1 = null;
/*     */           if (objectTag4 != null) {
/*     */             try {
/*     */               itemTier1 = MMOItems.plugin.getTiers().getOrThrow(objectTag4.toString().toUpperCase().replace("-", "_"));
/* 113 */             } catch (IllegalArgumentException illegalArgumentException) {
/*     */               paramAttribute.echoError(illegalArgumentException.getMessage());
/*     */             } 
/*     */           }
/*     */           
/* 118 */           int j = (i >= 0) ? i : ((bool && objectTag1 != null) ? MMOItems.plugin.getTemplates().rollLevel(PlayerData.get((OfflinePlayer)((PlayerTag)objectTag1).getPlayerEntity()).getRPG().getLevel()) : (1 + random.nextInt(100)));
/*     */           ItemTier itemTier2 = (itemTier1 != null) ? itemTier1 : MMOItems.plugin.getTemplates().rollTier();
/*     */           return (ObjectTag)new ItemTag(paramMMOItemTemplateTag.getTemplate().newBuilder(j, itemTier2).build().newBuilder().build());
/*     */         }new String[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static MMOItemTemplateTag valueOf(String paramString, TagContext paramTagContext) {
/* 130 */     if (paramString == null) return null;
/*     */     
/*     */     try {
/* 133 */       String[] arrayOfString = paramString.substring("mmoitem_template@".length()).split("\\.");
/* 134 */       String str1 = arrayOfString[0];
/* 135 */       String str2 = arrayOfString[1];
/*     */       
/* 137 */       Type type = MMOItems.plugin.getTypes().getOrThrow(str1);
/* 138 */       MMOItems.plugin.getTemplates().getTemplateOrThrow(type, str2);
/*     */       
/* 140 */       return new MMOItemTemplateTag(type, str2);
/* 141 */     } catch (Exception exception) {
/* 142 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\denizen\MMOItemTemplateTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */