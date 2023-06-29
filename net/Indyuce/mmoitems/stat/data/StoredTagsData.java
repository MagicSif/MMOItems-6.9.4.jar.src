/*     */ package net.Indyuce.mmoitems.stat.data;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class StoredTagsData
/*     */   implements StatData, Mergeable<StoredTagsData>
/*     */ {
/*  15 */   private final List<ItemTag> tags = new ArrayList<>();
/*     */   
/*  17 */   private static final List<String> IGNORED_TAGS = Arrays.asList(new String[] { "Unbreakable", "BlockEntityTag", "display", "Enchantments", "HideFlags", "Damage", "AttributeModifiers", "SkullOwner", "CanDestroy", "PickupDelay", "Age" });
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  22 */   private static final List<String> SAVED_MMOITEMS_TAGS = Arrays.asList(new String[] { "MMOITEMS_SKIN_ID" });
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public StoredTagsData(ItemStack paramItemStack) {
/*  27 */     this(NBTItem.get(paramItemStack));
/*     */   }
/*     */   public StoredTagsData(List<ItemTag> paramList) {
/*  30 */     this.tags.addAll(paramList);
/*     */   }
/*     */   public StoredTagsData(NBTItem paramNBTItem) {
/*  33 */     for (String str : paramNBTItem.getTags()) {
/*     */ 
/*     */       
/*  36 */       if (str.startsWith("MMOITEMS_") && !SAVED_MMOITEMS_TAGS.contains(str)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  43 */       if (IGNORED_TAGS.contains(str) || str.startsWith("HSTRY_")) {
/*     */         continue;
/*     */       }
/*     */       
/*  47 */       switch (getTagType(paramNBTItem.getTypeId(str))) {
/*     */         case "double":
/*  49 */           this.tags.add(new ItemTag(str, Double.valueOf(paramNBTItem.getDouble(str))));
/*     */         
/*     */         case "int":
/*  52 */           this.tags.add(new ItemTag(str, Integer.valueOf(paramNBTItem.getInteger(str))));
/*     */         
/*     */         case "byte":
/*  55 */           this.tags.add(new ItemTag(str, Boolean.valueOf(paramNBTItem.getBoolean(str))));
/*     */         
/*     */         case "string":
/*  58 */           this.tags.add(new ItemTag(str, paramNBTItem.getString(str)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTag(ItemTag paramItemTag) {
/*  67 */     this.tags.add(paramItemTag);
/*     */   }
/*     */   
/*     */   public List<ItemTag> getTags() {
/*  71 */     return this.tags;
/*     */   }
/*     */   
/*     */   private String getTagType(int paramInt) {
/*  75 */     switch (paramInt) {
/*     */       case 0:
/*  77 */         return "end";
/*     */       case 1:
/*  79 */         return "byte";
/*     */       case 2:
/*  81 */         return "short";
/*     */       case 3:
/*  83 */         return "int";
/*     */       case 4:
/*  85 */         return "long";
/*     */       case 5:
/*  87 */         return "float";
/*     */       case 6:
/*  89 */         return "double";
/*     */       case 7:
/*  91 */         return "bytearray";
/*     */       case 8:
/*  93 */         return "string";
/*     */       case 9:
/*  95 */         return "list";
/*     */       case 10:
/*  97 */         return "compound";
/*     */       case 11:
/*  99 */         return "intarray";
/*     */     } 
/* 101 */     return "unknown";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(StoredTagsData paramStoredTagsData) {
/* 107 */     this.tags.addAll(paramStoredTagsData.tags);
/*     */   }
/*     */   
/*     */   public StoredTagsData cloneData() {
/* 111 */     return new StoredTagsData(getTags());
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 115 */     return this.tags.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 120 */     if (!(paramObject instanceof StoredTagsData)) return false;
/*     */     
/* 122 */     if (((StoredTagsData)paramObject).getTags().size() != getTags().size()) return false;
/*     */     
/* 124 */     for (ItemTag itemTag : ((StoredTagsData)paramObject).getTags()) {
/*     */       
/* 126 */       if (itemTag == null)
/*     */         continue; 
/* 128 */       boolean bool = true;
/* 129 */       for (ItemTag itemTag1 : getTags()) {
/* 130 */         if (itemTag.equals(itemTag1)) { bool = false; break; } 
/* 131 */       }  if (bool) return false; 
/* 132 */     }  return true;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\StoredTagsData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */