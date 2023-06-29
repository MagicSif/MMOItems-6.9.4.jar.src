/*     */ package net.Indyuce.mmoitems.stat.type;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ public class NameData extends StringData implements Mergeable<StringData> { @NotNull ArrayList<String> prefixes; @NotNull ArrayList<String> suffixes; public void readPrefixes(@Nullable ItemTag paramItemTag) { if (paramItemTag == null)
/*     */       return;  for (String str : ItemTag.getStringListFromTag(paramItemTag)) { if (str == null)
/*     */         continue;  addPrefix(str); }  } @NotNull public String getMainName() { return getString(); } public boolean hasPrefixes() { return (this.prefixes.size() > 0); } public boolean hasSuffixes() { return (this.suffixes.size() > 0); } @NotNull public String bake() { StringBuilder stringBuilder = new StringBuilder(); for (String str : getPrefixes()) { if (stringBuilder.length() > 0)
/*     */         stringBuilder.append(" ");  stringBuilder.append(str); }  if (stringBuilder.length() > 0)
/*     */       stringBuilder.append(" ");  stringBuilder.append(getMainName()); for (String str : getSuffixes()) { if (stringBuilder.length() > 0)
/*     */         stringBuilder.append(" ");  stringBuilder.append(str); }
/*     */      return stringBuilder.toString(); } @NotNull public String bakePrefix() { StringBuilder stringBuilder = new StringBuilder(); for (String str : getPrefixes()) { if (stringBuilder.length() > 0)
/*     */         stringBuilder.append(" ");  stringBuilder.append(str); }
/*     */      return stringBuilder.toString(); } @NotNull public String bakeSuffix() { StringBuilder stringBuilder = new StringBuilder(); for (String str : getSuffixes()) { if (stringBuilder.length() > 0)
/*     */         stringBuilder.append(" ");  stringBuilder.append(str); }
/*  18 */      return stringBuilder.toString(); } public NameData(@NotNull String paramString) { super(paramString);
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
/* 101 */     this.prefixes = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 106 */     this.suffixes = new ArrayList<>(); }
/* 107 */   public String toString() { return bake(); } @NotNull public ItemTag compressPrefixes(@NotNull String paramString) { return ItemTag.fromStringList(paramString, getPrefixes()); } @NotNull public ItemTag compressSuffixes(@NotNull String paramString) { return ItemTag.fromStringList(paramString, getSuffixes()); } public void readSuffixes(@Nullable ItemTag paramItemTag) { if (paramItemTag == null) return;  for (String str : ItemTag.getStringListFromTag(paramItemTag)) { if (str == null) continue;  addSuffix(str); }  } public void addPrefix(@NotNull String paramString) { this.prefixes.add(paramString); } public void clearPrefixes() { this.prefixes.clear(); } @NotNull public ArrayList<String> getPrefixes() { return this.prefixes; } public void addSuffix(@NotNull String paramString) { this.suffixes.add(paramString); }
/* 108 */   public void clearSuffixes() { this.suffixes.clear(); } @NotNull
/* 109 */   public ArrayList<String> getSuffixes() { return this.suffixes; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void merge(StringData paramStringData) {
/* 115 */     if (paramStringData instanceof NameData) {
/*     */ 
/*     */       
/* 118 */       if (!((NameData)paramStringData).getMainName().isEmpty()) setString(((NameData)paramStringData).getMainName());
/*     */ 
/*     */       
/* 121 */       for (String str : ((NameData)paramStringData).getPrefixes()) addPrefix(str); 
/* 122 */       for (String str : ((NameData)paramStringData).getSuffixes()) addSuffix(str);
/*     */     
/* 124 */     } else if (paramStringData instanceof StringData) {
/*     */ 
/*     */       
/* 127 */       if (paramStringData.toString().isEmpty())
/* 128 */         return;  setString(paramStringData.toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StringData cloneData() {
/* 136 */     NameData nameData = new NameData(getMainName());
/* 137 */     for (String str : getPrefixes()) nameData.addPrefix(str); 
/* 138 */     for (String str : getSuffixes()) nameData.addSuffix(str);
/*     */     
/* 140 */     return nameData;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 145 */     return (super.isEmpty() && this.prefixes.isEmpty() && this.suffixes.isEmpty());
/*     */   } }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\NameData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */