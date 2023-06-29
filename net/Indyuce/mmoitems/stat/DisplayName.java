/*     */ package net.Indyuce.mmoitems.stat;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.comp.adventure.AdventureParser;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.NameData;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import net.Indyuce.mmoitems.stat.type.StringStat;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class DisplayName extends StringStat implements GemStoneStat {
/*     */   public DisplayName() {
/*  26 */     super("NAME", VersionMaterial.OAK_SIGN.toMaterial(), "Display Name", new String[] { "The item display name." }, new String[] { "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull StringData paramStringData) {
/*  32 */     ItemTier itemTier = paramItemStackBuilder.getMMOItem().getTier();
/*  33 */     AdventureParser adventureParser = MythicLib.plugin.getAdventureParser();
/*  34 */     String str = paramStringData.toString();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  39 */     str = str.replace("<tier-name>", (itemTier != null) ? adventureParser.stripColors(itemTier.getUnparsedName()) : "").replace("<tier-color>", (itemTier != null) ? adventureParser.lastColor(itemTier.getUnparsedName(), true) : "&f").replace("<tier-color-cleaned>", (itemTier != null) ? adventureParser.lastColor(itemTier.getUnparsedName(), false) : "");
/*     */ 
/*     */ 
/*     */     
/*  43 */     str = cropUpgrade(str);
/*  44 */     if (paramItemStackBuilder.getMMOItem().hasUpgradeTemplate()) {
/*  45 */       str = appendUpgradeLevel(str, paramItemStackBuilder.getMMOItem().getUpgradeLevel());
/*     */     }
/*  47 */     paramItemStackBuilder.getMeta().setDisplayName(str);
/*     */ 
/*     */     
/*  50 */     StatHistory.from(paramItemStackBuilder.getMMOItem(), (ItemStat)this);
/*     */ 
/*     */     
/*  53 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramStringData));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   String cropUpgrade(@NotNull String paramString) {
/*  58 */     String str1 = MMOItems.plugin.getConfig().getString("item-upgrading.name-suffix", " &8(&e+#lvl#&8)");
/*  59 */     if (str1 == null || str1.isEmpty()) {
/*  60 */       return paramString;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     int i = str1.indexOf("#lvl#");
/*  68 */     if (i < 0)
/*  69 */       return paramString; 
/*  70 */     String str2 = str1.substring(0, i);
/*  71 */     String str3 = str1.substring(i + "#lvl#".length());
/*  72 */     String str4 = str2.replace("+", "-");
/*  73 */     String str5 = str3.replace("+", "-");
/*     */ 
/*     */     
/*  76 */     if (paramString.contains(str2)) {
/*     */ 
/*     */       
/*  79 */       int j = paramString.indexOf(str2);
/*  80 */       int k = paramString.lastIndexOf(str3);
/*     */ 
/*     */       
/*  83 */       if (k < 0) {
/*  84 */         k = paramString.length();
/*     */       } else {
/*  86 */         k += str3.length();
/*     */       } 
/*     */ 
/*     */       
/*  90 */       String str6 = paramString.substring(0, j);
/*  91 */       String str7 = paramString.substring(k);
/*     */ 
/*     */       
/*  94 */       paramString = str6 + str7;
/*     */     } 
/*     */ 
/*     */     
/*  98 */     if (paramString.contains(str4)) {
/*     */ 
/*     */       
/* 101 */       int j = paramString.indexOf(str4);
/* 102 */       int k = paramString.lastIndexOf(str5);
/*     */ 
/*     */       
/* 105 */       if (k < 0) {
/* 106 */         k = paramString.length();
/*     */       } else {
/* 108 */         k += str5.length();
/*     */       } 
/*     */ 
/*     */       
/* 112 */       String str6 = paramString.substring(0, j);
/* 113 */       String str7 = paramString.substring(k);
/*     */ 
/*     */       
/* 116 */       paramString = str6 + str7;
/*     */     } 
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
/* 139 */     return paramString;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String appendUpgradeLevel(@NotNull String paramString, int paramInt) {
/* 144 */     String str = MMOItems.plugin.getConfig().getString("item-upgrading.name-suffix");
/* 145 */     if (str != null && paramInt != 0) {
/* 146 */       String str1 = levelPrefix(str, paramInt);
/* 147 */       return paramString + str1;
/*     */     } 
/* 149 */     return paramString;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String levelPrefix(@NotNull String paramString, int paramInt) {
/* 154 */     return paramString
/* 155 */       .replace("#lvl#", String.valueOf(paramInt))
/* 156 */       .replace("+-", "-");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringData paramStringData) {
/* 167 */     if (paramStringData instanceof NameData) {
/*     */       
/* 169 */       ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */       
/* 172 */       arrayList.add(new ItemTag(getNBTPath(), ((NameData)paramStringData).getMainName()));
/* 173 */       if (((NameData)paramStringData).hasPrefixes()) {
/* 174 */         arrayList.add(((NameData)paramStringData).compressPrefixes(getNBTPath() + "_PRE"));
/*     */       }
/* 176 */       if (((NameData)paramStringData).hasSuffixes()) {
/* 177 */         arrayList.add(((NameData)paramStringData).compressSuffixes(getNBTPath() + "_SUF"));
/*     */       }
/*     */       
/* 180 */       return arrayList;
/*     */     } 
/*     */ 
/*     */     
/* 184 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 191 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 192 */     boolean bool = false;
/* 193 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING);
/*     */ 
/*     */ 
/*     */     
/* 197 */     if (itemTag != null) {
/*     */ 
/*     */       
/* 200 */       ItemTag itemTag1 = ItemTag.getTagAtPath(getNBTPath() + "_PRE", paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING);
/* 201 */       ItemTag itemTag2 = ItemTag.getTagAtPath(getNBTPath() + "_SUF", paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING);
/* 202 */       arrayList.add(itemTag);
/* 203 */       arrayList.add(itemTag1);
/* 204 */       arrayList.add(itemTag2);
/*     */ 
/*     */       
/* 207 */       if (paramReadMMOItem.getNBT().getItem().getItemMeta().hasDisplayName()) {
/* 208 */         bool = true;
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 214 */       if (!paramReadMMOItem.getNBT().getItem().getItemMeta().hasDisplayName()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 221 */       arrayList.add(new ItemTag(getNBTPath(), cropUpgrade(paramReadMMOItem.getNBT().getItem().getItemMeta().getDisplayName())));
/*     */     } 
/*     */ 
/*     */     
/* 225 */     NameData nameData = (NameData)getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 228 */     if (nameData != null) {
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
/* 239 */       String str = null;
/* 240 */       if (bool) {
/*     */ 
/*     */         
/* 243 */         str = paramReadMMOItem.getNBT().getItem().getItemMeta().getDisplayName();
/* 244 */         String str1 = ChatColor.stripColor(str);
/*     */ 
/*     */ 
/*     */         
/* 248 */         if (!str.equals(str1)) {
/*     */           
/* 250 */           str = null;
/*     */         }
/*     */         else {
/*     */           
/* 254 */           nameData.setString(str);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 260 */       paramReadMMOItem.setData((ItemStat)this, (StatData)nameData);
/*     */ 
/*     */       
/* 263 */       if (bool && str != null)
/*     */       {
/*     */         
/* 266 */         if (paramReadMMOItem.getStatHistory((ItemStat)this) == null) {
/*     */ 
/*     */           
/* 269 */           ItemTag itemTag1 = ItemTag.getTagAtPath("HSTRY_" + getId(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING);
/*     */           
/* 271 */           if (itemTag1 != null) {
/*     */ 
/*     */             
/* 274 */             StatHistory statHistory = StatHistory.fromNBTString((MMOItem)paramReadMMOItem, (String)itemTag1.getValue());
/*     */ 
/*     */             
/* 277 */             if (statHistory != null) {
/*     */ 
/*     */               
/* 280 */               NameData nameData1 = (NameData)statHistory.getOriginalData();
/*     */ 
/*     */               
/* 283 */               nameData1.setString(str);
/*     */ 
/*     */               
/* 286 */               paramReadMMOItem.setStatHistory((ItemStat)this, statHistory);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StringData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 302 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 305 */     if (itemTag != null) {
/*     */ 
/*     */       
/* 308 */       String str = (String)itemTag.getValue();
/*     */ 
/*     */       
/* 311 */       NameData nameData = new NameData(str);
/*     */       
/* 313 */       nameData.readPrefixes(ItemTag.getTagAtPath(getNBTPath() + "_PRE", paramArrayList));
/* 314 */       nameData.readSuffixes(ItemTag.getTagAtPath(getNBTPath() + "_SUF", paramArrayList));
/*     */       
/* 316 */       return (StringData)nameData;
/*     */     } 
/*     */ 
/*     */     
/* 320 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StringData getClearStatData() {
/* 326 */     return (StringData)new NameData("");
/*     */   }
/*     */ 
/*     */   
/*     */   public StringData whenInitialized(Object paramObject) {
/* 331 */     return (StringData)new NameData(paramObject.toString());
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\DisplayName.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */