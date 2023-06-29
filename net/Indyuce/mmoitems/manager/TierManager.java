/*     */ package net.Indyuce.mmoitems.manager;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ConfigFile;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class TierManager implements Reloadable {
/*  21 */   private final Map<String, ItemTier> tiers = new HashMap<>();
/*     */   
/*     */   public TierManager() {
/*  24 */     reload();
/*     */   }
/*     */   
/*     */   public void reload() {
/*  28 */     this.tiers.clear();
/*     */ 
/*     */     
/*  31 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*  32 */     friendlyFeedbackProvider.activatePrefix(true, "Tiers");
/*     */     
/*  34 */     ConfigFile configFile = new ConfigFile("item-tiers");
/*  35 */     for (String str : configFile.getConfig().getKeys(false)) {
/*     */ 
/*     */       
/*  38 */       ConfigurationSection configurationSection = RecipeMakerGUI.getSection((ConfigurationSection)configFile.getConfig(), str);
/*     */ 
/*     */       
/*     */       try {
/*  42 */         register(new ItemTier(configurationSection));
/*     */       
/*     */       }
/*  45 */       catch (IllegalArgumentException illegalArgumentException) {
/*     */ 
/*     */         
/*  48 */         friendlyFeedbackProvider.log(FriendlyFeedbackCategory.ERROR, "Cannot register tier '$u{0}$b';$f {1}", new String[] { str, illegalArgumentException.getMessage() });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  53 */     friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.ERROR, MMOItems.getConsole());
/*  54 */     friendlyFeedbackProvider.sendTo(FriendlyFeedbackCategory.FAILURE, MMOItems.getConsole());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void register(@NotNull ItemTier paramItemTier) {
/*  62 */     this.tiers.put(paramItemTier.getId(), paramItemTier);
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
/*     */   public boolean has(@Nullable String paramString) {
/*  75 */     if (paramString == null) return false;
/*     */ 
/*     */     
/*  78 */     return this.tiers.containsKey(paramString);
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
/*     */   @NotNull
/*     */   public ItemTier getOrThrow(@Nullable String paramString) {
/*  93 */     Validate.isTrue(this.tiers.containsKey(paramString), FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), "Could not find tier with ID '$r{0}$b'", new String[] { paramString }));
/*     */ 
/*     */     
/*  96 */     return this.tiers.get(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemTier get(@Nullable String paramString) {
/* 105 */     if (paramString == null) return null; 
/* 106 */     return this.tiers.get(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Collection<ItemTier> getAll() {
/* 113 */     return this.tiers.values();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public ItemTier findTier(@NotNull MMOItem paramMMOItem) {
/* 124 */     return paramMMOItem.getTier();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\TierManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */