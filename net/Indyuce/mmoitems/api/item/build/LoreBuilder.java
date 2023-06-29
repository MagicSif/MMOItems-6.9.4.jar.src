/*     */ package net.Indyuce.mmoitems.api.item.build;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public class LoreBuilder
/*     */ {
/*  26 */   private final List<String> lore = new ArrayList<>();
/*  27 */   private final List<String> end = new ArrayList<>();
/*  28 */   private final Map<String, String> placeholders = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoreBuilder(Collection<String> paramCollection) {
/*  36 */     this.lore.addAll(paramCollection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(int paramInt, String paramString) {
/*  46 */     this.lore.add(paramInt, paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(String paramString, String... paramVarArgs) {
/*  58 */     int i = this.lore.indexOf("#" + paramString + "#");
/*  59 */     if (i < 0) {
/*     */       return;
/*     */     }
/*  62 */     for (byte b = 0; b < paramVarArgs.length; b++)
/*  63 */       this.lore.add(i + 1, paramVarArgs[paramVarArgs.length - b - 1]); 
/*  64 */     this.lore.remove(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(String paramString, List<String> paramList) {
/*  76 */     int i = this.lore.indexOf("#" + paramString + "#");
/*  77 */     if (i < 0) {
/*     */       return;
/*     */     }
/*  80 */     Lists.reverse(paramList).forEach(paramString -> this.lore.add(paramInt + 1, paramString));
/*  81 */     this.lore.remove(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerPlaceholder(String paramString, Object paramObject) {
/*  93 */     this.placeholders.put(paramString, paramObject.toString());
/*     */   }
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
/*     */   public String applySpecialPlaceholders(String paramString) {
/* 106 */     while (paramString.contains("{") && paramString.substring(paramString.indexOf("{")).contains("}")) {
/* 107 */       String str = paramString.substring(paramString.indexOf("{") + 1, paramString.indexOf("}"));
/* 108 */       paramString = paramString.replace("{" + str + "}", this.placeholders.getOrDefault(str, "PHE"));
/*     */     } 
/*     */     
/* 111 */     return paramString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void end(@NotNull String paramString) {
/* 120 */     this.end.add(paramString);
/*     */   }
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
/*     */   public List<String> build() {
/*     */     byte b;
/* 134 */     for (b = 0; b < this.lore.size(); ) {
/* 135 */       int j = this.lore.size() - b - 1;
/* 136 */       String str = this.lore.get(j);
/*     */ 
/*     */       
/* 139 */       if (str.startsWith("#")) {
/* 140 */         this.lore.remove(j);
/*     */         continue;
/*     */       } 
/* 143 */       if (str.startsWith("{bar}") && (j == this.lore.size() - 1 || isBar(this.lore.get(j + 1)))) {
/* 144 */         this.lore.remove(j);
/*     */         continue;
/*     */       } 
/* 147 */       b++;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     b = -1;
/* 154 */     for (String str1 : this.lore) {
/* 155 */       String str3; b++;
/* 156 */       String str2 = StringUtils.substringBetween(str1, "MATH%", "%");
/* 157 */       if (str2 == null) {
/*     */         continue;
/*     */       }
/*     */       try {
/* 161 */         str3 = (MythicLib.plugin.getMMOConfig()).decimals.format(((Double)MythicLib.plugin.getFormulaParser().eval(str2)).doubleValue());
/* 162 */       } catch (Exception exception) {
/* 163 */         str3 = "<InvalidFormula>";
/*     */       } 
/* 165 */       this.lore.set(b, str1.replaceAll("MATH\\%[^%]*\\%", str3));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     for (int i = 0; i < this.lore.size(); ) {
/*     */ 
/*     */       
/* 177 */       String str = ((String)this.lore.get(i)).replace("{bar}", "").replace("{sbar}", "");
/*     */ 
/*     */       
/* 180 */       if (str.contains("\\n")) {
/* 181 */         String[] arrayOfString = str.split("\\\\n");
/* 182 */         for (int j = arrayOfString.length - 1; j >= 0; j--) {
/* 183 */           this.lore.add(i, arrayOfString[j]);
/*     */         }
/*     */         
/* 186 */         this.lore.remove(i + arrayOfString.length);
/*     */ 
/*     */         
/* 189 */         i += arrayOfString.length;
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 194 */       this.lore.set(i++, str);
/*     */     } 
/*     */     
/* 197 */     this.lore.addAll(this.end);
/* 198 */     return this.lore;
/*     */   }
/*     */   
/*     */   private boolean isBar(String paramString) {
/* 202 */     return (paramString.startsWith("{bar}") || paramString.startsWith("{sbar}"));
/*     */   }
/*     */   
/*     */   public List<String> getLore() {
/* 206 */     return this.lore;
/*     */   }
/*     */   
/*     */   public void setLore(List<String> paramList) {
/* 210 */     this.lore.clear();
/* 211 */     this.lore.addAll(paramList);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\build\LoreBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */