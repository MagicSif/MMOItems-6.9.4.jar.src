/*     */ package net.Indyuce.mmoitems.api;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.api.util.MMOItemReforger;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ReforgeOptions
/*     */ {
/*     */   public static boolean dropRestoredGems;
/*     */   private final boolean keepName;
/*     */   private final boolean keepLore;
/*     */   private final boolean keepEnchantments;
/*     */   private final boolean keepSkins;
/*     */   private final boolean keepUpgrades;
/*     */   private final boolean keepGemStones;
/*     */   private final boolean keepSoulBind;
/*     */   private final boolean keepExternalSH;
/*     */   private final boolean keepModifications;
/*     */   @Nullable
/*     */   private final Boolean keepTier;
/*     */   private final boolean reRoll;
/*     */   private final boolean keepAdvancedEnchantments;
/*     */   @NotNull
/*  32 */   String keepCase = ChatColor.GRAY
/*  33 */     .toString();
/*     */   
/*     */   public void setKeepCase(@NotNull String paramString) {
/*  36 */     this.keepCase = paramString;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getKeepCase() {
/*  41 */     return this.keepCase;
/*     */   }
/*     */   @NotNull
/*  44 */   ArrayList<String> blacklistedItems = new ArrayList<>();
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
/*     */   public boolean isBlacklisted(@NotNull String paramString) {
/*  61 */     return this.blacklistedItems.contains(paramString);
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
/*     */   public void addToBlacklist(@NotNull String paramString) {
/*  76 */     this.blacklistedItems.add(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearBlacklist() {
/*  83 */     this.blacklistedItems.clear();
/*     */   }
/*     */   
/*     */   public ReforgeOptions(ConfigurationSection paramConfigurationSection) {
/*  87 */     this.keepName = paramConfigurationSection.getBoolean("display-name");
/*  88 */     this.keepLore = paramConfigurationSection.getBoolean("lore");
/*  89 */     this.keepEnchantments = paramConfigurationSection.getBoolean("enchantments");
/*  90 */     this.keepUpgrades = paramConfigurationSection.getBoolean("upgrades");
/*  91 */     this.keepGemStones = (paramConfigurationSection.getBoolean("gemstones", false) || paramConfigurationSection.getBoolean("gems", false));
/*  92 */     this.keepSkins = paramConfigurationSection.getBoolean("skins", false);
/*  93 */     this.keepSoulBind = paramConfigurationSection.getBoolean("soulbound");
/*  94 */     this.keepCase = paramConfigurationSection.getString("kept-lore-prefix", ChatColor.GRAY.toString());
/*  95 */     this.keepExternalSH = paramConfigurationSection.getBoolean("external-sh", true);
/*  96 */     this.keepModifications = paramConfigurationSection.getBoolean("modifications");
/*  97 */     this.reRoll = paramConfigurationSection.getBoolean("reroll");
/*  98 */     this.keepAdvancedEnchantments = paramConfigurationSection.getBoolean("advanced-enchantments");
/*  99 */     this.keepTier = paramConfigurationSection.contains("tier") ? Boolean.valueOf(paramConfigurationSection.getBoolean("tier", true)) : null;
/*     */   }
/*     */   
/*     */   public ReforgeOptions(boolean... paramVarArgs) {
/* 103 */     this.keepName = arr(paramVarArgs, 0);
/* 104 */     this.keepLore = arr(paramVarArgs, 1);
/* 105 */     this.keepEnchantments = arr(paramVarArgs, 2);
/* 106 */     this.keepUpgrades = arr(paramVarArgs, 3);
/* 107 */     this.keepGemStones = arr(paramVarArgs, 4);
/* 108 */     this.keepSoulBind = arr(paramVarArgs, 5);
/* 109 */     this.keepExternalSH = arr(paramVarArgs, 6);
/* 110 */     this.reRoll = arr(paramVarArgs, 7);
/* 111 */     this.keepModifications = arr(paramVarArgs, 8);
/* 112 */     this.keepAdvancedEnchantments = arr(paramVarArgs, 9);
/* 113 */     this.keepSkins = arr(paramVarArgs, 10);
/* 114 */     this.keepTier = Boolean.valueOf(arr(paramVarArgs, 11));
/*     */   }
/*     */   
/*     */   boolean arr(@NotNull boolean[] paramArrayOfboolean, int paramInt) {
/* 118 */     return (paramArrayOfboolean.length > paramInt && paramArrayOfboolean[paramInt]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldReRoll() {
/* 126 */     return this.reRoll;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepName() {
/* 133 */     return this.keepName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepMods() {
/* 140 */     return this.keepModifications;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepLore() {
/* 147 */     return this.keepLore;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepSkins() {
/* 154 */     return this.keepSkins;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepTier() {
/* 161 */     return (this.keepTier == null) ? MMOItemReforger.keepTiersWhenReroll : this.keepTier.booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepEnchantments() {
/* 170 */     return this.keepEnchantments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepAdvancedEnchants() {
/* 179 */     return this.keepAdvancedEnchantments;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepExternalSH() {
/* 186 */     return this.keepExternalSH;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepUpgrades() {
/* 193 */     return this.keepUpgrades;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepGemStones() {
/* 203 */     return this.keepGemStones;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldKeepSoulBind() {
/* 210 */     return this.keepSoulBind;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ReforgeOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */