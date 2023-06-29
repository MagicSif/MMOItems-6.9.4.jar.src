/*     */ package net.Indyuce.mmoitems.api;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.stat.Enchants;
/*     */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.UpgradeInfo;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import net.Indyuce.mmoitems.stat.type.Upgradable;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class UpgradeTemplate
/*     */ {
/*     */   @NotNull
/*     */   private final String id;
/*     */   @NotNull
/*  31 */   private final Map<ItemStat, UpgradeInfo> perStatUpgradeInfos = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UpgradeTemplate(@NotNull ConfigurationSection paramConfigurationSection) {
/*  38 */     Validate.notNull(paramConfigurationSection, FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), "You must specify a config section.", new String[0]));
/*     */ 
/*     */     
/*  41 */     this.id = paramConfigurationSection.getName().toLowerCase().replace("_", "-").replace(" ", "-");
/*     */ 
/*     */     
/*  44 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*  45 */     friendlyFeedbackProvider.activatePrefix(true, "Upgrade Template $i&o" + paramConfigurationSection.getName());
/*     */ 
/*     */     
/*  48 */     for (String str1 : paramConfigurationSection.getKeys(false)) {
/*     */ 
/*     */ 
/*     */       
/*  52 */       String str2 = str1.toUpperCase().replace("-", "_");
/*     */ 
/*     */       
/*  55 */       ItemStat itemStat = MMOItems.plugin.getStats().get(str2);
/*  56 */       if (itemStat == null) { friendlyFeedbackProvider.log(FriendlyFeedbackCategory.ERROR, "Stat '$r{0}$b' $fnot found$b.", new String[] { str2 }); continue; }
/*  57 */        if (!(itemStat instanceof Upgradable)) { friendlyFeedbackProvider.log(FriendlyFeedbackCategory.ERROR, "Stat $r{0}$b is $fnot upgradeable$b.", new String[] { itemStat.getId() }); continue; }
/*  58 */        if (!(itemStat.getClearStatData() instanceof net.Indyuce.mmoitems.stat.data.type.Mergeable)) { friendlyFeedbackProvider.log(FriendlyFeedbackCategory.ERROR, "Stat Data used by $r{0}$b is $fnot mergeable$b, and thus it cannot be upgradeable. Contact the dev of this ItemStat.", new String[] { itemStat.getId() });
/*     */         
/*     */         continue; }
/*     */ 
/*     */       
/*     */       try {
/*  64 */         this.perStatUpgradeInfos.put(itemStat, ((Upgradable)itemStat).loadUpgradeInfo(paramConfigurationSection.get(str1)));
/*     */       
/*     */       }
/*  67 */       catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */         
/*  70 */         friendlyFeedbackProvider.log(FriendlyFeedbackCategory.ERROR, illegalArgumentException.getMessage(), new String[0]);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  75 */     friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.ERROR, MMOItems.getConsole());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getId() {
/*  85 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<ItemStat> getKeys() {
/*  92 */     return this.perStatUpgradeInfos.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UpgradeInfo getUpgradeInfo(@NotNull ItemStat paramItemStat) {
/*  99 */     return this.perStatUpgradeInfos.get(paramItemStat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgrade(@NotNull MMOItem paramMMOItem) {
/* 108 */     upgradeTo(paramMMOItem, paramMMOItem.getUpgradeLevel() + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void upgradeTo(@NotNull MMOItem paramMMOItem, int paramInt) {
/*     */     UpgradeData upgradeData;
/* 118 */     Enchants.separateEnchantments(paramMMOItem);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (paramMMOItem.hasData(ItemStats.UPGRADE))
/* 124 */     { upgradeData = (UpgradeData)paramMMOItem.getData(ItemStats.UPGRADE); }
/* 125 */     else { upgradeData = new UpgradeData(null, null, false, false, 0, 0, 100.0D); }
/* 126 */      upgradeData.setLevel(paramInt);
/* 127 */     paramMMOItem.setData(ItemStats.UPGRADE, (StatData)upgradeData);
/*     */ 
/*     */ 
/*     */     
/* 131 */     for (ItemStat itemStat : this.perStatUpgradeInfos.keySet()) {
/*     */ 
/*     */ 
/*     */       
/* 135 */       ((Upgradable)itemStat).preprocess(paramMMOItem);
/*     */ 
/*     */       
/* 138 */       StatHistory statHistory = StatHistory.from(paramMMOItem, itemStat);
/*     */ 
/*     */ 
/*     */       
/* 142 */       ((Upgradable)itemStat).midprocess(paramMMOItem);
/*     */ 
/*     */ 
/*     */       
/* 146 */       paramMMOItem.setData(itemStat, statHistory.recalculate(paramInt));
/*     */ 
/*     */ 
/*     */       
/* 150 */       ((Upgradable)itemStat).postprocess(paramMMOItem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isDisplayingUpgrades() {
/* 157 */     return MMOItems.plugin.getConfig().getBoolean("item-upgrading.display-stat-changes", false);
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
/*     */   @NotNull
/*     */   public static String getUpgradeChangeSuffix(@NotNull String paramString, boolean paramBoolean) {
/* 170 */     String str1 = Objects.<String>requireNonNull(MMOItems.plugin.getConfig().getString("item-upgrading.stat-change-suffix", " &8(<p>#stat#&8)"));
/* 171 */     String str2 = Objects.<String>requireNonNull(MMOItems.plugin.getConfig().getString("item-upgrading.stat-change-positive", "&a"));
/* 172 */     String str3 = Objects.<String>requireNonNull(MMOItems.plugin.getConfig().getString("item-upgrading.stat-change-negative", "&c"));
/*     */ 
/*     */     
/* 175 */     if (paramBoolean)
/*     */     {
/*     */       
/* 178 */       return str1.replace("<p>", str3).replace("#stat#", paramString);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 184 */     return str1.replace("<p>", str2).replace("#stat#", paramString);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\UpgradeTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */