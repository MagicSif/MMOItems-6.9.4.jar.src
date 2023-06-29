/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.StringListStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class NBTTags
/*     */   extends StringListStat
/*     */ {
/*     */   public NBTTags() {
/*  28 */     super("CUSTOM_NBT", Material.NAME_TAG, "NBT Tags", new String[] { "Custom NBT Tags." }, new String[] { "all" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public StringListData whenInitialized(Object paramObject) {
/*  34 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/*  35 */     return new StringListData((List)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  40 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  41 */       (new StatEdition(paramEditionInventory, ItemStats.NBT_TAGS, new Object[0])).enable(new String[] { "Write in the chat the NBT tag you want to add.", ChatColor.AQUA + "Format: {Tag Name} {Tag Value}" });
/*     */     }
/*     */     
/*  44 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  45 */       paramEditionInventory.getEditedSection().contains("custom-nbt")) {
/*  46 */       List<String> list = paramEditionInventory.getEditedSection().getStringList("custom-nbt");
/*  47 */       if (list.size() < 1) {
/*     */         return;
/*     */       }
/*  50 */       String str = list.get(list.size() - 1);
/*  51 */       list.remove(str);
/*  52 */       paramEditionInventory.getEditedSection().set("custom-nbt", list);
/*  53 */       paramEditionInventory.registerTemplateEdition();
/*  54 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed '" + str + "'.");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  61 */     Validate.isTrue(((paramString.split(" ")).length > 1), "Use this format: {Tag Name} {Tag Value}");
/*     */     
/*  63 */     List<String> list = paramEditionInventory.getEditedSection().contains("custom-nbt") ? paramEditionInventory.getEditedSection().getStringList("custom-nbt") : new ArrayList();
/*  64 */     list.add(paramString);
/*     */     
/*  66 */     paramEditionInventory.getEditedSection().set("custom-nbt", list);
/*  67 */     paramEditionInventory.registerTemplateEdition();
/*  68 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "StringListStat successfully added.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/*  73 */     if (paramOptional.isPresent()) {
/*  74 */       paramList.add(ChatColor.GRAY + "Current Value:");
/*  75 */       StringListData stringListData = paramOptional.get();
/*  76 */       stringListData.getList().forEach(paramString -> paramList.add(ChatColor.GRAY + paramString));
/*     */     } else {
/*     */       
/*  79 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/*  81 */     paramList.add("");
/*  82 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a tag.");
/*  83 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last tag.");
/*     */   }
/*     */   
/*  86 */   static String extraneousTag = "EXTMI_";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringListData paramStringListData) {
/*  97 */     JsonArray jsonArray = new JsonArray();
/*     */ 
/*     */     
/* 100 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */     
/* 102 */     HashMap<Object, Object> hashMap1 = new HashMap<>();
/* 103 */     HashMap<Object, Object> hashMap2 = new HashMap<>();
/*     */ 
/*     */     
/* 106 */     for (String str1 : paramStringListData.getList()) {
/*     */ 
/*     */       
/* 109 */       if (str1.startsWith(extraneousTag)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 116 */       jsonArray.add(str1);
/*     */ 
/*     */       
/* 119 */       String str2 = str1.substring(0, str1.indexOf(' '));
/* 120 */       String str3 = str1.substring(str1.indexOf(' ') + 1);
/*     */       
/* 122 */       hashMap1.put(str2, extraneousTag + str1);
/* 123 */       hashMap2.put(str2, new ItemTag(str2, calculateObjectType(str3)));
/*     */     } 
/*     */ 
/*     */     
/* 127 */     for (String str1 : paramStringListData.getList()) {
/*     */ 
/*     */       
/* 130 */       if (!str1.startsWith(extraneousTag)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       String str2 = str1.substring(extraneousTag.length());
/*     */ 
/*     */       
/* 140 */       String str3 = str2.substring(0, str2.indexOf(' '));
/* 141 */       String str4 = str2.substring(str2.indexOf(' ') + 1);
/*     */ 
/*     */       
/* 144 */       if (hashMap1.containsKey(str3)) {
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
/* 156 */         hashMap1.put(str3, str1);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       if (str4.isEmpty()) {
/*     */ 
/*     */         
/* 170 */         hashMap2.remove(str3);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 176 */         hashMap2.put(str3, new ItemTag(str3, calculateObjectType(str4)));
/*     */       } 
/*     */ 
/*     */       
/* 180 */       hashMap1.remove(str3);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 185 */     for (String str : hashMap2.keySet()) {
/*     */ 
/*     */       
/* 188 */       arrayList.add((ItemTag)hashMap2.get(str));
/*     */ 
/*     */       
/* 191 */       jsonArray.add((String)hashMap1.get(str));
/*     */     } 
/*     */ 
/*     */     
/* 195 */     arrayList.add(new ItemTag(getNBTPath(), jsonArray.toString()));
/*     */ 
/*     */     
/* 198 */     return arrayList;
/*     */   }
/*     */   
/*     */   public Object calculateObjectType(String paramString) {
/* 202 */     if (paramString.equalsIgnoreCase("true"))
/* 203 */       return Boolean.valueOf(true); 
/* 204 */     if (paramString.equalsIgnoreCase("false"))
/* 205 */       return Boolean.valueOf(false); 
/*     */     try {
/* 207 */       return Integer.valueOf(Integer.parseInt(paramString));
/* 208 */     } catch (NumberFormatException numberFormatException) {
/*     */       try {
/* 210 */         return Double.valueOf(Double.parseDouble(paramString));
/* 211 */       } catch (NumberFormatException numberFormatException1) {
/* 212 */         if (paramString.contains("[") && paramString.contains("]")) {
/* 213 */           ArrayList<String> arrayList = new ArrayList();
/* 214 */           for (String str : paramString.replace("[", "").replace("]", "").split(","))
/* 215 */             arrayList.add(str.replace("\"", "")); 
/* 216 */           return arrayList;
/*     */         } 
/* 218 */         return paramString;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\NBTTags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */