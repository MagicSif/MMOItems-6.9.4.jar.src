/*     */ package net.Indyuce.mmoitems.api.item.template;
/*     */ import io.lumine.mythic.lib.api.util.PostLoadObject;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.ItemReference;
/*     */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class MMOItemTemplate extends PostLoadObject implements ItemReference {
/*     */   private final Type type;
/*  30 */   private final Map<ItemStat, RandomStatData> base = new HashMap<>(); private final String id; private final int revId;
/*     */   @NotNull
/*  32 */   private final Map<String, TemplateModifier> modifiers = new LinkedHashMap<>();
/*  33 */   private final Set<TemplateOption> options = new HashSet<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOItemTemplate(Type paramType, String paramString) {
/*  44 */     super(null);
/*     */     
/*  46 */     this.type = paramType;
/*  47 */     this.id = paramString;
/*  48 */     this.revId = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOItemTemplate(Type paramType, ConfigurationSection paramConfigurationSection) {
/*  58 */     super(paramConfigurationSection);
/*  59 */     Validate.notNull(paramConfigurationSection, "Could not load template config");
/*     */     
/*  61 */     this.type = paramType;
/*  62 */     this.id = paramConfigurationSection.getName().toUpperCase().replace("-", "_").replace(" ", "_");
/*  63 */     this.revId = paramConfigurationSection.getInt("base.revision-id", 1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void whenPostLoaded(ConfigurationSection paramConfigurationSection) {
/*  69 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*  70 */     friendlyFeedbackProvider.activatePrefix(true, getType().getId() + " " + getId());
/*     */     
/*  72 */     if (paramConfigurationSection.contains("option"))
/*  73 */       for (TemplateOption templateOption : TemplateOption.values()) {
/*  74 */         if (paramConfigurationSection.getBoolean("option." + templateOption.name().toLowerCase().replace("_", "-")))
/*  75 */           this.options.add(templateOption); 
/*     */       }  
/*  77 */     if (paramConfigurationSection.contains("modifiers"))
/*  78 */       for (String str : paramConfigurationSection.getConfigurationSection("modifiers").getKeys(false)) {
/*     */         
/*     */         try {
/*  81 */           TemplateModifier templateModifier = new TemplateModifier(MMOItems.plugin.getTemplates(), paramConfigurationSection.getConfigurationSection("modifiers." + str));
/*  82 */           this.modifiers.put(templateModifier.getId(), templateModifier);
/*  83 */         } catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */           
/*  86 */           friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Could not load modifier '$f{0}$b': {1}", new String[] { str, illegalArgumentException.getMessage() });
/*     */         } 
/*     */       }  
/*  89 */     Validate.notNull(paramConfigurationSection.getConfigurationSection("base"), FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), "Could not find base item data", new String[0]));
/*  90 */     for (String str : paramConfigurationSection.getConfigurationSection("base").getKeys(false)) {
/*     */       try {
/*  92 */         String str1 = str.toUpperCase().replace("-", "_");
/*  93 */         Validate.isTrue(MMOItems.plugin.getStats().has(str1), FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), "Could not find stat with ID '$i{0}$b'", new String[] { str1 }));
/*     */         
/*  95 */         ItemStat itemStat = MMOItems.plugin.getStats().get(str1);
/*  96 */         RandomStatData randomStatData = itemStat.whenInitialized(paramConfigurationSection.get("base." + str));
/*  97 */         if (randomStatData != null) {
/*  98 */           this.base.put(itemStat, randomStatData);
/*     */         }
/* 100 */       } catch (IllegalArgumentException illegalArgumentException) {
/*     */         
/* 102 */         if (!illegalArgumentException.getMessage().isEmpty())
/*     */         {
/*     */           
/* 105 */           friendlyFeedbackProvider.log(FriendlyFeedbackCategory.INFORMATION, "Could not load base item data '$f{0}$b': {1}", new String[] { str, illegalArgumentException.getMessage() });
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 110 */     friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.INFORMATION, MMOItems.getConsole());
/*     */   }
/*     */   
/*     */   public Map<ItemStat, RandomStatData> getBaseItemData() {
/* 114 */     return this.base;
/*     */   }
/*     */   @NotNull
/*     */   public Map<String, TemplateModifier> getModifiers() {
/* 118 */     return this.modifiers;
/*     */   }
/*     */   
/*     */   public boolean hasModifier(String paramString) {
/* 122 */     return this.modifiers.containsKey(paramString);
/*     */   }
/*     */   
/*     */   public TemplateModifier getModifier(String paramString) {
/* 126 */     return this.modifiers.get(paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public Type getType() {
/* 131 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getId() {
/* 136 */     return this.id;
/*     */   }
/*     */   
/*     */   public int getRevisionId() {
/* 140 */     return this.revId;
/*     */   }
/*     */   
/*     */   public boolean hasOption(TemplateOption paramTemplateOption) {
/* 144 */     return this.options.contains(paramTemplateOption);
/*     */   }
/*     */   
/*     */   public MMOItemBuilder newBuilder(@Nullable Player paramPlayer) {
/* 148 */     if (paramPlayer != null) return newBuilder(PlayerData.get((OfflinePlayer)paramPlayer).getRPG()); 
/* 149 */     return newBuilder((RPGPlayer)null);
/*     */   }
/*     */   public MMOItemBuilder newBuilder() {
/* 152 */     return newBuilder((RPGPlayer)null);
/*     */   }
/*     */   public MMOItemBuilder newBuilder(@Nullable PlayerData paramPlayerData) {
/* 155 */     if (paramPlayerData != null) return newBuilder(paramPlayerData.getRPG()); 
/* 156 */     return newBuilder((RPGPlayer)null);
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
/*     */   public MMOItemBuilder newBuilder(@Nullable RPGPlayer paramRPGPlayer) {
/* 169 */     return newBuilder(paramRPGPlayer, false);
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
/*     */   public MMOItemBuilder newBuilder(@Nullable RPGPlayer paramRPGPlayer, boolean paramBoolean) {
/* 185 */     if (paramRPGPlayer == null) {
/* 186 */       return newBuilder(0, (ItemTier)null);
/*     */     }
/*     */ 
/*     */     
/* 190 */     boolean bool = hasOption(TemplateOption.LEVEL_ITEM) ? MMOItems.plugin.getTemplates().rollLevel(paramRPGPlayer.getLevel()) : false;
/* 191 */     ItemTier itemTier = hasOption(TemplateOption.TIERED) ? MMOItems.plugin.getTemplates().rollTier() : null;
/* 192 */     return new MMOItemBuilder(this, bool, itemTier, paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOItemBuilder newBuilder(int paramInt, @Nullable ItemTier paramItemTier) {
/* 201 */     return new MMOItemBuilder(this, paramInt, paramItemTier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum TemplateOption
/*     */   {
/* 210 */     ROLL_MODIFIER_CHECK_ORDER,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     TIERED,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     LEVEL_ITEM;
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
/*     */   @Deprecated
/*     */   public int getCraftedAmount() {
/* 250 */     return 1;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\template\MMOItemTemplate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */