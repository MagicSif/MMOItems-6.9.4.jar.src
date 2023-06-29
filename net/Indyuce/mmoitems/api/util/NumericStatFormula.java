/*     */ package net.Indyuce.mmoitems.api.util;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.UpdatableRandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class NumericStatFormula
/*     */   implements RandomStatData<DoubleData>, UpdatableRandomStatData<DoubleData>
/*     */ {
/*     */   private final double base;
/*     */   private final double scale;
/*     */   private final double spread;
/*     */   private final double maxSpread;
/*  24 */   private static final Random RANDOM = new Random();
/*  25 */   private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.####");
/*     */   
/*  27 */   public static final NumericStatFormula ZERO = new NumericStatFormula(0.0D, 0.0D, 0.0D, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean useRelativeSpread;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericStatFormula(Object paramObject) {
/*  41 */     Validate.notNull(paramObject, "Config must not be null");
/*     */     
/*  43 */     if (paramObject instanceof String) {
/*  44 */       String[] arrayOfString = paramObject.toString().split(" ");
/*  45 */       this.base = Double.parseDouble(arrayOfString[0]);
/*  46 */       this.scale = (arrayOfString.length > 1) ? Double.parseDouble(arrayOfString[1]) : 0.0D;
/*  47 */       this.spread = (arrayOfString.length > 2) ? Double.parseDouble(arrayOfString[2]) : 0.0D;
/*  48 */       this.maxSpread = (arrayOfString.length > 3) ? Double.parseDouble(arrayOfString[3]) : 0.0D;
/*     */       
/*     */       return;
/*     */     } 
/*  52 */     if (paramObject instanceof Number) {
/*  53 */       this.base = Double.parseDouble(paramObject.toString());
/*  54 */       this.scale = 0.0D;
/*  55 */       this.spread = 0.0D;
/*  56 */       this.maxSpread = 0.0D;
/*     */       
/*     */       return;
/*     */     } 
/*  60 */     if (paramObject instanceof ConfigurationSection) {
/*  61 */       ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*  62 */       this.base = configurationSection.getDouble("base");
/*  63 */       this.scale = configurationSection.getDouble("scale");
/*  64 */       this.spread = configurationSection.getDouble("spread");
/*  65 */       this.maxSpread = configurationSection.getDouble("max-spread", 0.3D);
/*     */       
/*  67 */       Validate.isTrue((this.spread >= 0.0D), "Spread must be positive");
/*  68 */       Validate.isTrue((this.maxSpread >= 0.0D), "Max spread must be positive");
/*     */       
/*     */       return;
/*     */     } 
/*  72 */     throw new IllegalArgumentException("Must specify a config section, a string or a number");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericStatFormula(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
/*  91 */     this.base = paramDouble1;
/*  92 */     this.scale = paramDouble2;
/*  93 */     this.spread = paramDouble3;
/*  94 */     this.maxSpread = paramDouble4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getBase() {
/* 101 */     return this.base;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getScale() {
/* 109 */     return this.scale;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSpread() {
/* 114 */     return this.spread;
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
/*     */   public double getMaxSpread() {
/* 126 */     return this.maxSpread;
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
/*     */   public double calculate(double paramDouble) {
/* 151 */     return calculate(paramDouble, RANDOM.nextGaussian());
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
/*     */   public double calculate(double paramDouble1, double paramDouble2) {
/* 163 */     double d1 = this.base + this.scale * paramDouble1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     double d2 = Math.min(Math.max(paramDouble2 * this.spread, -this.maxSpread), this.maxSpread);
/*     */     
/* 172 */     return useRelativeSpread ? (d1 * (1.0D + d2)) : (d1 + d2);
/*     */   }
/*     */ 
/*     */   
/*     */   public DoubleData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 177 */     return new DoubleData(calculate(paramMMOItemBuilder.getLevel()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillConfigurationSection(ConfigurationSection paramConfigurationSection, String paramString, FormulaSaveOption paramFormulaSaveOption) {
/* 188 */     if (paramString == null) {
/* 189 */       throw new NullPointerException("Path was empty");
/*     */     }
/* 191 */     if (this.scale == 0.0D && this.spread == 0.0D && this.maxSpread == 0.0D) {
/* 192 */       paramConfigurationSection.set(paramString, (this.base == 0.0D && paramFormulaSaveOption == FormulaSaveOption.DELETE_IF_ZERO) ? null : Double.valueOf(this.base));
/*     */     } else {
/*     */       
/* 195 */       paramConfigurationSection.set(paramString + ".base", Double.valueOf(this.base));
/* 196 */       paramConfigurationSection.set(paramString + ".scale", Double.valueOf(this.scale));
/* 197 */       paramConfigurationSection.set(paramString + ".spread", Double.valueOf(this.spread));
/* 198 */       paramConfigurationSection.set(paramString + ".max-spread", Double.valueOf(this.maxSpread));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fillConfigurationSection(ConfigurationSection paramConfigurationSection, String paramString) {
/* 203 */     fillConfigurationSection(paramConfigurationSection, paramString, FormulaSaveOption.DELETE_IF_ZERO);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 209 */     if (this.scale == 0.0D && this.spread == 0.0D) {
/* 210 */       return DECIMAL_FORMAT.format(this.base);
/*     */     }
/* 212 */     if (this.scale == 0.0D) {
/* 213 */       return "[" + DECIMAL_FORMAT.format(this.base * (1.0D - this.maxSpread)) + " -> " + DECIMAL_FORMAT.format(this.base * (1.0D + this.maxSpread)) + "] (" + DECIMAL_FORMAT.format(this.spread * 100.0D) + "% Spread) (" + DECIMAL_FORMAT
/* 214 */         .format(this.base) + " Avg)";
/*     */     }
/* 216 */     return "{Base=" + DECIMAL_FORMAT.format(this.base) + ((this.scale != 0.0D) ? (",Scale=" + DECIMAL_FORMAT.format(this.scale)) : "") + ((this.spread != 0.0D) ? (",Spread=" + this.spread) : "") + (
/* 217 */       (this.maxSpread != 0.0D) ? (",Max=" + this.maxSpread) : "") + "}";
/*     */   }
/*     */   public static void reload() {
/* 220 */     useRelativeSpread = !MMOItems.plugin.getConfig().getBoolean("additive-spread-formula", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public DoubleData reroll(@NotNull ItemStat paramItemStat, @NotNull DoubleData paramDoubleData, int paramInt) {
/* 228 */     double d1 = getBase() + getScale() * paramInt;
/* 229 */     double d2 = paramDoubleData.getValue();
/* 230 */     double d3 = d2 - d1;
/* 231 */     double d4 = useRelativeSpread ? Math.abs(d3 / getSpread() * d1) : Math.abs(d3 / getSpread());
/* 232 */     double d5 = getMaxSpread() / getSpread();
/*     */ 
/*     */     
/* 235 */     if (d4 > d5 || d4 > 3.5D)
/*     */     {
/*     */       
/* 238 */       return new DoubleData(calculate(paramInt));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     return paramDoubleData.cloneData();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum FormulaSaveOption
/*     */   {
/* 256 */     DELETE_IF_ZERO,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 261 */     NONE;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\NumericStatFormula.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */