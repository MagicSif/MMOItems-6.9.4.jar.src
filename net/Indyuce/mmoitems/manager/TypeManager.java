/*     */ package net.Indyuce.mmoitems.manager;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class TypeManager implements Reloadable {
/*  17 */   private final Map<String, Type> map = new LinkedHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reload() {
/*  24 */     this.map.clear();
/*     */ 
/*     */     
/*  27 */     for (Field field : Type.class.getFields()) {
/*     */       try {
/*  29 */         if (Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()) && field.get(null) instanceof Type)
/*  30 */           register((Type)field.get(null)); 
/*  31 */       } catch (Exception exception) {
/*  32 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Couldn't register type called '" + field.getName() + "': " + exception.getMessage());
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  40 */     ConfigManager.DefaultFile.ITEM_TYPES.checkFile();
/*     */     
/*  42 */     ConfigFile configFile = new ConfigFile("item-types");
/*  43 */     for (String str : configFile.getConfig().getKeys(false)) {
/*  44 */       if (!this.map.containsKey(str))
/*     */         try {
/*  46 */           register(new Type(this, configFile.getConfig().getConfigurationSection(str)));
/*  47 */         } catch (IllegalArgumentException illegalArgumentException) {
/*  48 */           MMOItems.plugin.getLogger().log(Level.WARNING, "Could not register type '" + str + "': " + illegalArgumentException.getMessage());
/*     */         }  
/*     */     } 
/*  51 */     for (Iterator<Type> iterator = this.map.values().iterator(); iterator.hasNext(); ) {
/*  52 */       Type type = iterator.next();
/*     */       
/*     */       try {
/*  55 */         type.load(configFile.getConfig().getConfigurationSection(type.getId()));
/*  56 */       } catch (IllegalArgumentException illegalArgumentException) {
/*  57 */         MMOItems.plugin.getLogger().log(Level.WARNING, "Could not register type '" + type.getId() + "': " + illegalArgumentException.getMessage());
/*  58 */         iterator.remove();
/*     */ 
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  67 */       type.getAvailableStats().clear();
/*  68 */       MMOItems.plugin.getStats().getAll().stream().filter(paramItemStat -> paramItemStat.isCompatible(paramType)).forEach(paramItemStat -> paramType.getAvailableStats().add(paramItemStat));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void register(Type paramType) {
/*  73 */     this.map.put(paramType.getId(), paramType);
/*     */   }
/*     */   
/*     */   public void registerAll(Type... paramVarArgs) {
/*  77 */     for (Type type : paramVarArgs) {
/*  78 */       register(type);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Type get(@Nullable String paramString) {
/*  87 */     if (paramString == null) return null; 
/*  88 */     return this.map.get(paramString);
/*     */   }
/*     */   @NotNull
/*     */   public Type getOrThrow(@Nullable String paramString) {
/*  92 */     Validate.isTrue(this.map.containsKey(paramString), "Could not find item type with ID '" + paramString + "'");
/*  93 */     return this.map.get(paramString);
/*     */   }
/*     */   
/*     */   public boolean has(String paramString) {
/*  97 */     return this.map.containsKey(paramString);
/*     */   }
/*     */   
/*     */   public Collection<Type> getAll() {
/* 101 */     return this.map.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getAllTypeNames() {
/* 108 */     ArrayList<String> arrayList = new ArrayList();
/* 109 */     for (Type type : getAll()) arrayList.add(type.getId()); 
/* 110 */     return arrayList;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\TypeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */