/*     */ package net.Indyuce.mmoitems.stat.type;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.RequiredLevelData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomRequiredLevelData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import org.bukkit.Material;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class RequiredLevelStat
/*     */   extends DoubleStat
/*     */   implements ItemRestriction, GemStoneStat
/*     */ {
/*     */   private final String idKey;
/*     */   
/*     */   public RequiredLevelStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString) {
/*  29 */     super("REQUIRED_" + paramString1, paramMaterial, "Required " + paramString2, paramArrayOfString, new String[] { "!block", "all" }, new Material[0]);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  35 */     this.idKey = paramString1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/*  42 */     int i = (int)paramDoubleData.getValue();
/*  43 */     String str = MMOItems.plugin.getLanguage().getStatFormat(getPath()).replace("{value}", String.valueOf(i));
/*  44 */     paramItemStackBuilder.getLore().insert(getPath(), new String[] { str });
/*     */ 
/*     */     
/*  47 */     paramItemStackBuilder.addItemTag(new ItemTag[] { new ItemTag(getNBTPath(), Integer.valueOf(i)) });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData, @NotNull NumericStatFormula paramNumericStatFormula) {
/*  54 */     double d1 = paramNumericStatFormula.calculate(0.0D, -2.5D);
/*  55 */     double d2 = paramNumericStatFormula.calculate(0.0D, 2.5D);
/*     */ 
/*     */     
/*  58 */     if (d2 < 0.0D && !handleNegativeStats()) {
/*     */       return;
/*     */     }
/*  61 */     if (d1 < 0.0D && !handleNegativeStats()) {
/*  62 */       d1 = 0.0D;
/*     */     }
/*  64 */     if (d1 < paramNumericStatFormula.getBase() - paramNumericStatFormula.getMaxSpread()) {
/*  65 */       d1 = paramNumericStatFormula.getBase() - paramNumericStatFormula.getMaxSpread();
/*     */     }
/*  67 */     if (d2 > paramNumericStatFormula.getBase() + paramNumericStatFormula.getMaxSpread()) {
/*  68 */       d2 = paramNumericStatFormula.getBase() + paramNumericStatFormula.getMaxSpread();
/*     */     }
/*     */ 
/*     */     
/*  72 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramDoubleData));
/*     */ 
/*     */     
/*  75 */     if (d1 != 0.0D || d2 != 0.0D) {
/*     */       String str;
/*     */       
/*  78 */       if (SilentNumbers.round(d1, 2) == SilentNumbers.round(d2, 2)) {
/*  79 */         str = SilentNumbers.readableRounding(d1, 0);
/*     */       } else {
/*  81 */         str = SilentNumbers.removeDecimalZeros(String.valueOf(d1)) + "-" + SilentNumbers.removeDecimalZeros(String.valueOf(d2));
/*     */       } 
/*     */ 
/*     */       
/*  85 */       paramItemStackBuilder.getLore().insert(getPath(), new String[] { formatNumericStat(d1, new String[] { "{value}", str }) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull DoubleData paramDoubleData) {
/*  92 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  93 */     arrayList.add(new ItemTag(getNBTPath(), Double.valueOf(paramDoubleData.getValue())));
/*  94 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public RandomRequiredLevelData whenInitialized(Object paramObject) {
/*  99 */     return new RandomRequiredLevelData(paramObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 106 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 107 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 108 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.DOUBLE));
/*     */     }
/*     */     
/* 111 */     RequiredLevelData requiredLevelData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 114 */     if (requiredLevelData != null) {
/* 115 */       paramReadMMOItem.setData(this, (StatData)requiredLevelData);
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public RequiredLevelData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 121 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/* 122 */     if (itemTag != null) {
/* 123 */       return new RequiredLevelData(((Double)itemTag.getValue()).doubleValue());
/*     */     }
/* 125 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public RequiredLevelData getClearStatData() {
/* 130 */     return new RequiredLevelData(0.0D);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\RequiredLevelStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */