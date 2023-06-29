/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.RequiredLevelData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomRequiredLevelData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RequiredLevel
/*     */   extends DoubleStat
/*     */   implements ItemRestriction
/*     */ {
/*     */   public RequiredLevel() {
/*  35 */     super("REQUIRED_LEVEL", VersionMaterial.EXPERIENCE_BOTTLE.toMaterial(), "Required Level", new String[] { "The level your item needs", "in order to be used." }, new String[] { "!block", "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull DoubleData paramDoubleData) {
/*  43 */     int i = (int)paramDoubleData.getValue();
/*  44 */     paramItemStackBuilder.getLore().insert("required-level", new String[] { formatNumericStat(i, new String[] { "{value}", String.valueOf(i) }) });
/*     */ 
/*     */     
/*  47 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramDoubleData));
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
/*  85 */       paramItemStackBuilder.getLore().insert("required-level", new String[] { formatNumericStat(d1, new String[] { "{value}", str }) });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull DoubleData paramDoubleData) {
/*  94 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  95 */     arrayList.add(new ItemTag(getNBTPath(), Double.valueOf(paramDoubleData.getValue())));
/*  96 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public RandomRequiredLevelData whenInitialized(Object paramObject) {
/* 101 */     return new RandomRequiredLevelData(paramObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 108 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 109 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 110 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.DOUBLE));
/*     */     }
/*     */     
/* 113 */     RequiredLevelData requiredLevelData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 116 */     if (requiredLevelData != null) {
/* 117 */       paramReadMMOItem.setData((ItemStat)this, (StatData)requiredLevelData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public RequiredLevelData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 127 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 130 */     if (itemTag != null)
/*     */     {
/*     */       
/* 133 */       return new RequiredLevelData(((Double)itemTag.getValue()).doubleValue());
/*     */     }
/*     */ 
/*     */     
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 142 */     int i = paramNBTItem.getInteger(ItemStats.REQUIRED_LEVEL.getNBTPath());
/* 143 */     if (paramRPGPlayer.getLevel() < i && !paramRPGPlayer.getPlayer().hasPermission("mmoitems.bypass.level")) {
/* 144 */       if (paramBoolean) {
/* 145 */         Message.NOT_ENOUGH_LEVELS.format(ChatColor.RED, new String[0]).send(paramRPGPlayer.getPlayer());
/* 146 */         paramRPGPlayer.getPlayer().playSound(paramRPGPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/*     */       } 
/* 148 */       return false;
/*     */     } 
/* 150 */     return true;
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
/*     */   @NotNull
/*     */   public RequiredLevelData getClearStatData() {
/* 214 */     return new RequiredLevelData(0.0D);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RequiredLevel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */