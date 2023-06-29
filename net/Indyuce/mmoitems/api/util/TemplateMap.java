/*     */ package net.Indyuce.mmoitems.api.util;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TemplateMap<C>
/*     */ {
/*  23 */   private final Map<Type, Submap> typeMap = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasValue(@Nullable Type paramType, @Nullable String paramString) {
/*  33 */     if (paramType == null || paramString == null) return false; 
/*  34 */     return (this.typeMap.containsKey(paramType) && (this.typeMap.get(paramType)).idMap.containsKey(paramString));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public C getValue(@Nullable Type paramType, @Nullable String paramString) {
/*  45 */     if (paramType == null || paramString == null) return null; 
/*  46 */     Submap submap = this.typeMap.get(paramType);
/*  47 */     if (submap == null) return null; 
/*  48 */     return (C)submap.idMap.get(paramString);
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
/*     */   public void removeValue(@Nullable Type paramType, @Nullable String paramString) {
/*  60 */     if (paramType == null || paramString == null)
/*  61 */       return;  if (this.typeMap.containsKey(paramType)) {
/*  62 */       (this.typeMap.get(paramType)).idMap.remove(paramString);
/*     */     }
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
/*     */   public void setValue(@NotNull Type paramType, @NotNull String paramString, @NotNull C paramC) {
/*  76 */     Validate.notNull(paramC, "Value cannot be null");
/*     */     
/*  78 */     if (!this.typeMap.containsKey(paramType))
/*  79 */       this.typeMap.put(paramType, new Submap()); 
/*  80 */     (this.typeMap.get(paramType)).idMap.put(paramString, paramC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forEach(@NotNull Consumer<C> paramConsumer) {
/*  90 */     this.typeMap.values().forEach(paramSubmap -> paramSubmap.idMap.values().forEach(paramConsumer));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Collection<C> collectValues() {
/*  96 */     HashSet<C> hashSet = new HashSet();
/*  97 */     this.typeMap.values().forEach(paramSubmap -> paramSet.addAll(paramSubmap.idMap.values()));
/*  98 */     return hashSet;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Collection<C> collectValues(@NotNull Type paramType) {
/* 108 */     return this.typeMap.containsKey(paramType) ? (this.typeMap.get(paramType)).idMap.values() : new HashSet<>();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 114 */     this.typeMap.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class Submap
/*     */   {
/*     */     private Submap() {}
/*     */ 
/*     */ 
/*     */     
/* 125 */     private final Map<String, C> idMap = new LinkedHashMap<>();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\TemplateMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */