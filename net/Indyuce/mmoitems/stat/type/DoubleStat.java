/*     */ package net.Indyuce.mmoitems.stat.type;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackMessage;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.PlusMinusPercent;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import io.lumine.mythic.lib.manager.StatManager;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.regex.Pattern;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.UpgradeTemplate;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.UpgradeInfo;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class DoubleStat
/*     */   extends ItemStat<NumericStatFormula, DoubleData>
/*     */   implements Upgradable, Previewable<NumericStatFormula, DoubleData> {
/*     */   private final boolean moreIsBetter;
/*  44 */   private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.####");
/*     */   
/*     */   public DoubleStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString) {
/*  47 */     this(paramString1, paramMaterial, paramString2, paramArrayOfString, new String[] { "!miscellaneous", "!block", "all" }, true, new Material[0]);
/*     */   }
/*     */   
/*     */   public DoubleStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, Material... paramVarArgs) {
/*  51 */     this(paramString1, paramMaterial, paramString2, paramArrayOfString1, paramArrayOfString2, true, paramVarArgs);
/*     */   }
/*     */   
/*     */   public DoubleStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean, Material... paramVarArgs) {
/*  55 */     super(paramString1, paramMaterial, paramString2, paramArrayOfString1, paramArrayOfString2, paramVarArgs);
/*     */     
/*  57 */     this.moreIsBetter = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleNegativeStats() {
/*  64 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double multiplyWhenDisplaying() {
/*  71 */     return 1.0D;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean moreIsBetter() {
/*  78 */     return this.moreIsBetter;
/*     */   }
/*     */ 
/*     */   
/*     */   public NumericStatFormula whenInitialized(Object paramObject) {
/*  83 */     if (paramObject instanceof Number) {
/*  84 */       return new NumericStatFormula(Double.parseDouble(paramObject.toString()), 0.0D, 0.0D, 0.0D);
/*     */     }
/*  86 */     if (paramObject instanceof org.bukkit.configuration.ConfigurationSection) {
/*  87 */       return new NumericStatFormula(paramObject);
/*     */     }
/*  89 */     throw new IllegalArgumentException("Must specify a number or a config section");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/*  96 */     double d1 = paramDoubleData.getValue();
/*     */ 
/*     */     
/*  99 */     if (d1 < 0.0D && !handleNegativeStats()) {
/*     */       return;
/*     */     }
/* 102 */     double d2 = 0.0D;
/*     */ 
/*     */     
/* 105 */     if (UpgradeTemplate.isDisplayingUpgrades() && paramItemStackBuilder.getMMOItem().getUpgradeLevel() != 0) {
/*     */ 
/*     */       
/* 108 */       StatHistory statHistory = paramItemStackBuilder.getMMOItem().getStatHistory(this);
/* 109 */       if (statHistory != null) {
/*     */ 
/*     */ 
/*     */         
/* 113 */         DoubleData doubleData = (DoubleData)statHistory.recalculateUnupgraded();
/*     */ 
/*     */         
/* 116 */         d2 = d1 - doubleData.getValue();
/*     */       } 
/*     */     } 
/* 119 */     if (d1 != 0.0D || d2 != 0.0D) {
/* 120 */       String str = formatPath(getId(), MMOItems.plugin.getLanguage().getStatFormat(getPath()), this.moreIsBetter, d1 * multiplyWhenDisplaying());
/* 121 */       if (d2 != 0.0D)
/* 122 */         str = str + UpgradeTemplate.getUpgradeChangeSuffix(plus(d2 * multiplyWhenDisplaying()) + (MythicLib.plugin.getMMOConfig()).decimals.format(d2 * multiplyWhenDisplaying()), !isGood(d2 * multiplyWhenDisplaying())); 
/* 123 */       paramItemStackBuilder.getLore().insert(getPath(), new String[] { str });
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     if (paramDoubleData.getValue() != 0.0D) paramItemStackBuilder.addItemTag(getAppliedNBT(paramDoubleData)); 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public static String formatPath(@NotNull String paramString, boolean paramBoolean, double paramDouble) {
/* 138 */     return formatPath("ATTACK_DAMAGE", paramString, paramBoolean, paramDouble);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String formatPath(@NotNull String paramString1, @NotNull String paramString2, boolean paramBoolean, double paramDouble) {
/* 143 */     String str1 = StatManager.format(paramString1, paramDouble);
/* 144 */     String str2 = getColorPrefix((paramDouble < 0.0D && paramBoolean));
/* 145 */     return paramString2
/* 146 */       .replace("<plus>{value}", str2 + ((paramDouble > 0.0D) ? "+" : "") + str1)
/* 147 */       .replace("{value}", str2 + str1)
/* 148 */       .replace("<plus>", (paramDouble > 0.0D) ? "+" : "");
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public static String formatPath(@NotNull String paramString, boolean paramBoolean, double paramDouble1, double paramDouble2) {
/* 154 */     return formatPath("ATTACK_DAMAGE", paramString, paramBoolean, paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String formatPath(@NotNull String paramString1, @NotNull String paramString2, boolean paramBoolean, double paramDouble1, double paramDouble2) {
/* 159 */     String str1 = StatManager.format(paramString1, paramDouble1), str2 = StatManager.format(paramString1, paramDouble2);
/* 160 */     String str3 = getColorPrefix((paramDouble1 < 0.0D && paramBoolean)), str4 = getColorPrefix((paramDouble2 < 0.0D && paramBoolean));
/* 161 */     return paramString2
/* 162 */       .replace("<plus>", "")
/* 163 */       .replace("{value}", str3 + (
/* 164 */         (paramDouble1 > 0.0D) ? "+" : "") + str1 + MMOItems.plugin
/* 165 */         .getConfig().getString("stats-displaying.range-dash", "⎓") + str4 + ((
/* 166 */         paramDouble1 < 0.0D && paramDouble2 > 0.0D) ? "+" : "") + str2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenPreviewed(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData, @NotNull NumericStatFormula paramNumericStatFormula) {
/* 171 */     Validate.isTrue(paramDoubleData instanceof DoubleData, "Current Data is not Double Data");
/* 172 */     Validate.isTrue(paramNumericStatFormula instanceof NumericStatFormula, "Template Data is not Numeric Stat Formula");
/*     */ 
/*     */ 
/*     */     
/* 176 */     double d1 = paramNumericStatFormula.calculate(0.0D, -2.5D);
/* 177 */     double d2 = paramNumericStatFormula.calculate(0.0D, 2.5D);
/*     */ 
/*     */     
/* 180 */     if (d2 < 0.0D && !handleNegativeStats())
/* 181 */       return;  if (d1 < 0.0D && !handleNegativeStats()) d1 = 0.0D;
/*     */     
/* 183 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramDoubleData));
/*     */ 
/*     */     
/* 186 */     if (d1 != 0.0D || d2 != 0.0D) {
/*     */       String str;
/*     */       
/* 189 */       if (SilentNumbers.round(d1, 2) == SilentNumbers.round(d2, 2)) {
/* 190 */         str = formatPath(getId(), MMOItems.plugin.getLanguage().getStatFormat(getPath()), moreIsBetter(), d2 * multiplyWhenDisplaying());
/*     */       } else {
/*     */         
/* 193 */         str = formatPath(getId(), MMOItems.plugin.getLanguage().getStatFormat(getPath()), moreIsBetter(), d1 * multiplyWhenDisplaying(), d2 * multiplyWhenDisplaying());
/*     */       } 
/*     */       
/* 196 */       paramItemStackBuilder.getLore().insert(getPath(), new String[] { str });
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String getColorPrefix(boolean paramBoolean) {
/* 202 */     return Objects.<String>requireNonNull(MMOItems.plugin.getConfig().getString("stats-displaying.color-" + (paramBoolean ? "negative" : "positive"), ""));
/*     */   } @NotNull
/*     */   String plus(double paramDouble) {
/* 205 */     return (paramDouble >= 0.0D) ? "+" : "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGood(double paramDouble) {
/* 216 */     return moreIsBetter() ? ((paramDouble >= 0.0D)) : ((paramDouble <= 0.0D));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull DoubleData paramDoubleData) {
/* 224 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 227 */     arrayList.add(new ItemTag(getNBTPath(), Double.valueOf(paramDoubleData.getValue())));
/*     */ 
/*     */     
/* 230 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 237 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 240 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 241 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.DOUBLE));
/*     */     }
/*     */     
/* 244 */     DoubleData doubleData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 247 */     if (doubleData != null)
/*     */     {
/*     */       
/* 250 */       paramReadMMOItem.setData(this, (StatData)doubleData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public DoubleData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 258 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 261 */     if (itemTag != null)
/*     */     {
/*     */       
/* 264 */       return new DoubleData(SilentNumbers.round(((Double)itemTag.getValue()).doubleValue(), 4));
/*     */     }
/*     */ 
/*     */     
/* 268 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/* 273 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/* 274 */       paramEditionInventory.getEditedSection().set(getPath(), null);
/* 275 */       paramEditionInventory.registerTemplateEdition();
/* 276 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + getName() + ChatColor.GRAY + ".");
/*     */       return;
/*     */     } 
/* 279 */     (new StatEdition(paramEditionInventory, this, new Object[0])).enable(new String[] { "Write in the chat the numeric value you want.", "Second Format: {Base} {Scaling Value} {Spread} {Max Spread}", "Third Format: {Min Value} -> {Max Value}" });
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
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*     */     double d1, d2, d3, d4;
/* 293 */     if (paramString.contains("->")) {
/* 294 */       String[] arrayOfString = paramString.replace(" ", "").split(Pattern.quote("->"));
/* 295 */       Validate.isTrue((arrayOfString.length > 1), "You must specify two (both min and max) values");
/*     */       
/* 297 */       double d5 = Double.parseDouble(arrayOfString[0]), d6 = Double.parseDouble(arrayOfString[1]);
/* 298 */       Validate.isTrue((d6 > d5), "Max value must be greater than min value");
/*     */       
/* 300 */       d1 = MMOUtils.truncation((d5 == -d6) ? ((d6 - d5) * 0.05D) : ((d5 + d6) / 2.0D), 3);
/* 301 */       d2 = 0.0D;
/* 302 */       d4 = MMOUtils.truncation((d6 - d5) / 2.0D * d1, 3);
/* 303 */       d3 = MMOUtils.truncation(0.8D * d4, 3);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 308 */       String[] arrayOfString = paramString.split(" ");
/* 309 */       d1 = MMOUtils.parseDouble(arrayOfString[0]);
/* 310 */       d2 = (arrayOfString.length > 1) ? MMOUtils.parseDouble(arrayOfString[1]) : 0.0D;
/* 311 */       d3 = (arrayOfString.length > 2) ? MMOUtils.parseDouble(arrayOfString[2]) : 0.0D;
/* 312 */       d4 = (arrayOfString.length > 3) ? MMOUtils.parseDouble(arrayOfString[3]) : 0.0D;
/*     */     } 
/*     */ 
/*     */     
/* 316 */     if (d2 == 0.0D && d3 == 0.0D && d4 == 0.0D) {
/* 317 */       paramEditionInventory.getEditedSection().set(getPath(), Double.valueOf(d1));
/*     */     } else {
/*     */       
/* 320 */       paramEditionInventory.getEditedSection().set(getPath() + ".base", Double.valueOf(d1));
/* 321 */       paramEditionInventory.getEditedSection().set(getPath() + ".scale", (d2 == 0.0D) ? null : Double.valueOf(d2));
/* 322 */       paramEditionInventory.getEditedSection().set(getPath() + ".spread", (d3 == 0.0D) ? null : Double.valueOf(d3));
/* 323 */       paramEditionInventory.getEditedSection().set(getPath() + ".max-spread", (d4 == 0.0D) ? null : Double.valueOf(d4));
/*     */     } 
/*     */     
/* 326 */     paramEditionInventory.registerTemplateEdition();
/* 327 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + getName() + " successfully changed to {" + d1 + " - " + d2 + " - " + d3 + " - " + d4 + "}");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<NumericStatFormula> paramOptional) {
/* 333 */     if (paramOptional.isPresent()) {
/* 334 */       NumericStatFormula numericStatFormula = paramOptional.get();
/* 335 */       paramList.add(ChatColor.GRAY + "Base Value: " + ChatColor.GREEN + DECIMAL_FORMAT.format(numericStatFormula.getBase()) + (
/* 336 */           (numericStatFormula.getScale() != 0.0D) ? (ChatColor.GRAY + " (+" + ChatColor.GREEN + DECIMAL_FORMAT.format(numericStatFormula.getScale()) + ChatColor.GRAY + ")") : ""));
/* 337 */       if (numericStatFormula.getSpread() > 0.0D) {
/* 338 */         paramList.add(ChatColor.GRAY + "Spread: " + ChatColor.GREEN + DECIMAL_FORMAT.format(numericStatFormula.getSpread() * 100.0D) + "%" + ChatColor.GRAY + " (Max: " + ChatColor.GREEN + DECIMAL_FORMAT
/* 339 */             .format(numericStatFormula.getMaxSpread() * 100.0D) + "%" + ChatColor.GRAY + ")");
/*     */       }
/*     */     } else {
/* 342 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.GREEN + "---");
/*     */     } 
/* 344 */     paramList.add("");
/* 345 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to change this value.");
/* 346 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove this value.");
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public DoubleData getClearStatData() {
/* 351 */     return new DoubleData(0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public UpgradeInfo loadUpgradeInfo(@Nullable Object paramObject) {
/* 359 */     return DoubleUpgradeInfo.GetFrom(paramObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StatData apply(@NotNull StatData paramStatData, @NotNull UpgradeInfo paramUpgradeInfo, int paramInt) {
/* 367 */     int i = paramInt;
/* 368 */     if (paramStatData instanceof DoubleData && paramUpgradeInfo instanceof DoubleUpgradeInfo) {
/*     */ 
/*     */       
/* 371 */       double d = ((DoubleData)paramStatData).getValue();
/*     */ 
/*     */       
/* 374 */       if (i > 0) {
/*     */ 
/*     */         
/* 377 */         while (i > 0)
/*     */         {
/*     */           
/* 380 */           d = ((DoubleUpgradeInfo)paramUpgradeInfo).getPMP().apply(d);
/*     */ 
/*     */           
/* 383 */           i--;
/*     */         }
/*     */       
/*     */       }
/* 387 */       else if (i < 0) {
/*     */ 
/*     */         
/* 390 */         while (i < 0) {
/*     */ 
/*     */           
/* 393 */           d = ((DoubleUpgradeInfo)paramUpgradeInfo).getPMP().reverse(d);
/*     */ 
/*     */           
/* 396 */           i++;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 401 */       ((DoubleData)paramStatData).setValue(d);
/*     */     } 
/*     */ 
/*     */     
/* 405 */     return paramStatData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DoubleUpgradeInfo
/*     */     implements UpgradeInfo
/*     */   {
/*     */     @NotNull
/*     */     PlusMinusPercent pmp;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public static DoubleUpgradeInfo GetFrom(@Nullable Object param1Object) {
/* 423 */       Validate.notNull(param1Object, FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), "Upgrade operation must not be null", new String[0]));
/*     */ 
/*     */       
/* 426 */       String str = param1Object.toString();
/* 427 */       if (str.isEmpty()) {
/* 428 */         throw new IllegalArgumentException(
/* 429 */             FriendlyFeedbackProvider.quickForConsole(FFPMMOItems.get(), "Upgrade operation is empty", new String[0]));
/*     */       }
/*     */ 
/*     */       
/* 433 */       char c = str.charAt(0); if (c == 's') { str = str.substring(1); } else if (c != '+' && c != '-' && c != 'n') { str = '+' + str; }
/*     */ 
/*     */       
/* 436 */       FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/* 437 */       PlusMinusPercent plusMinusPercent = PlusMinusPercent.getFromString(str, friendlyFeedbackProvider);
/* 438 */       if (plusMinusPercent == null) {
/* 439 */         throw new IllegalArgumentException(((FriendlyFeedbackMessage)friendlyFeedbackProvider
/* 440 */             .getFeedbackOf(FriendlyFeedbackCategory.ERROR).get(0)).forConsole(friendlyFeedbackProvider.getPalette()));
/*     */       }
/*     */ 
/*     */       
/* 444 */       return new DoubleUpgradeInfo(plusMinusPercent);
/*     */     }
/*     */     public DoubleUpgradeInfo(@NotNull PlusMinusPercent param1PlusMinusPercent) {
/* 447 */       this.pmp = param1PlusMinusPercent;
/*     */     }
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public PlusMinusPercent getPMP() {
/* 453 */       return this.pmp;
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\DoubleStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */