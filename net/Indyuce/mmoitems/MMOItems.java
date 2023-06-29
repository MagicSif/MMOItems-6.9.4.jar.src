/*     */ package net.Indyuce.mmoitems;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackMessage;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import java.net.URLConnection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import javax.annotation.Nullable;
/*     */ import net.Indyuce.mmoitems.api.DeathItemsHandler;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*     */ import net.Indyuce.mmoitems.comp.MMOItemsRewardTypes;
/*     */ import net.Indyuce.mmoitems.comp.McMMONonRPGHook;
/*     */ import net.Indyuce.mmoitems.comp.WorldEditSupport;
/*     */ import net.Indyuce.mmoitems.comp.eco.VaultSupport;
/*     */ import net.Indyuce.mmoitems.comp.enchants.CrazyEnchantsStat;
/*     */ import net.Indyuce.mmoitems.comp.enchants.EnchantPlugin;
/*     */ import net.Indyuce.mmoitems.comp.enchants.MythicEnchantsSupport;
/*     */ import net.Indyuce.mmoitems.comp.enchants.advanced_enchants.AdvancedEnchantmentsHook;
/*     */ import net.Indyuce.mmoitems.comp.inventory.PlayerInventory;
/*     */ import net.Indyuce.mmoitems.comp.inventory.PlayerInventoryHandler;
/*     */ import net.Indyuce.mmoitems.comp.mmocore.MMOCoreMMOLoader;
/*     */ import net.Indyuce.mmoitems.comp.mmoinventory.MMOInventorySupport;
/*     */ import net.Indyuce.mmoitems.comp.mythicmobs.LootsplosionListener;
/*     */ import net.Indyuce.mmoitems.comp.mythicmobs.MythicMobsCompatibility;
/*     */ import net.Indyuce.mmoitems.comp.rpg.HeroesHook;
/*     */ import net.Indyuce.mmoitems.comp.rpg.RPGHandler;
/*     */ import net.Indyuce.mmoitems.manager.BlockManager;
/*     */ import net.Indyuce.mmoitems.manager.ConfigManager;
/*     */ import net.Indyuce.mmoitems.manager.CraftingManager;
/*     */ import net.Indyuce.mmoitems.manager.DropTableManager;
/*     */ import net.Indyuce.mmoitems.manager.EntityManager;
/*     */ import net.Indyuce.mmoitems.manager.ItemManager;
/*     */ import net.Indyuce.mmoitems.manager.LayoutManager;
/*     */ import net.Indyuce.mmoitems.manager.LoreFormatManager;
/*     */ import net.Indyuce.mmoitems.manager.PluginUpdateManager;
/*     */ import net.Indyuce.mmoitems.manager.RecipeManager;
/*     */ import net.Indyuce.mmoitems.manager.SetManager;
/*     */ import net.Indyuce.mmoitems.manager.SkillManager;
/*     */ import net.Indyuce.mmoitems.manager.StatManager;
/*     */ import net.Indyuce.mmoitems.manager.TemplateManager;
/*     */ import net.Indyuce.mmoitems.manager.TierManager;
/*     */ import net.Indyuce.mmoitems.manager.TypeManager;
/*     */ import net.Indyuce.mmoitems.manager.UpgradeManager;
/*     */ import net.Indyuce.mmoitems.manager.WorldGenManager;
/*     */ import net.Indyuce.mmoitems.manager.data.PlayerDataManager;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.PluginUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class MMOItems extends JavaPlugin {
/*  67 */   private final PluginUpdateManager pluginUpdateManager = new PluginUpdateManager(); public static MMOItems plugin;
/*  68 */   private final CraftingManager stationRecipeManager = new CraftingManager();
/*  69 */   private final LoreFormatManager formatManager = new LoreFormatManager();
/*  70 */   private final TemplateManager templateManager = new TemplateManager();
/*  71 */   private final SkillManager skillManager = new SkillManager();
/*  72 */   private final EntityManager entityManager = new EntityManager();
/*  73 */   private final RecipeManager recipeManager = new RecipeManager();
/*  74 */   private final LayoutManager layoutManager = new LayoutManager();
/*  75 */   private final TypeManager typeManager = new TypeManager();
/*  76 */   private final ItemManager itemManager = new ItemManager();
/*  77 */   private final PlayerInventoryHandler inventory = new PlayerInventoryHandler();
/*  78 */   private final List<EnchantPlugin<? extends Enchantment>> enchantPlugins = new ArrayList<>();
/*  79 */   private final StatManager statManager = new StatManager();
/*     */   
/*     */   private PlayerDataManager playerDataManager;
/*     */   private DropTableManager dropTableManager;
/*     */   private WorldGenManager worldGenManager;
/*     */   private UpgradeManager upgradeManager;
/*     */   private ConfigManager configManager;
/*     */   private BlockManager blockManager;
/*     */   private TierManager tierManager;
/*     */   private SetManager setManager;
/*     */   private VaultSupport vaultSupport;
/*  90 */   private final List<RPGHandler> rpgPlugins = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasLoadedSuccessfully;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MMOItems() {
/* 103 */     plugin = this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLoad() {
/* 108 */     getLogger().log(Level.INFO, "Plugin file is called '" + getFile().getName() + "'");
/*     */     
/* 110 */     PluginUtils.isDependencyPresent("WorldEdit", paramVoid -> {
/*     */           try {
/*     */             new WorldEditSupport();
/*     */             getLogger().log(Level.INFO, "Hooked onto WorldEdit");
/* 114 */           } catch (Exception exception) {
/*     */             getLogger().log(Level.WARNING, "Could not initialize support with WorldEdit 7: ", exception);
/*     */           } 
/*     */         });
/*     */ 
/*     */     
/* 120 */     saveDefaultConfig();
/* 121 */     this.configManager = new ConfigManager();
/*     */     
/* 123 */     this.statManager.load();
/* 124 */     this.typeManager.reload();
/* 125 */     this.templateManager.preloadTemplates();
/*     */     
/* 127 */     PluginUtils.isDependencyPresent("MMOCore", paramVoid -> new MMOCoreMMOLoader());
/* 128 */     PluginUtils.isDependencyPresent("mcMMO", paramVoid -> this.statManager.register(McMMOHook.disableMcMMORepair));
/* 129 */     PluginUtils.isDependencyPresent("AdvancedEnchantments", paramVoid -> {
/*     */           this.statManager.register(AdvancedEnchantmentsHook.ADVANCED_ENCHANTMENTS);
/*     */           this.statManager.register(AdvancedEnchantmentsHook.DISABLE_ADVANCED_ENCHANTMENTS);
/*     */         });
/* 133 */     PluginUtils.isDependencyPresent("MythicEnchants", paramVoid -> this.enchantPlugins.add(new MythicEnchantsSupport()));
/* 134 */     PluginUtils.isDependencyPresent("Heroes", paramVoid -> this.statManager.register(HeroesHook.MAX_STAMINA));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 139 */     loadConfig0(); (new SpigotPlugin(39267, this)).checkForUpdate();
/* 140 */     new MMOItemsMetrics();
/* 141 */     MMOItemUIFilter.register();
/*     */     
/* 143 */     RecipeBrowserGUI.registerNativeRecipes();
/* 144 */     this.skillManager.initialize(false);
/*     */     
/* 146 */     int i = getConfig().contains("config-version", true) ? getConfig().getInt("config-version") : -1;
/* 147 */     int j = getConfig().getDefaults().getInt("config-version");
/* 148 */     if (i != j) {
/* 149 */       getLogger().warning("You may be using an outdated config.yml!");
/* 150 */       getLogger().warning("(Your config version: '" + i + "' | Expected config version: '" + j + "')");
/*     */     } 
/*     */ 
/*     */     
/* 154 */     PluginUtils.hookDependencyIfPresent("MythicMobs", paramVoid -> {
/*     */           new MythicMobsCompatibility();
/*     */           if (getConfig().getBoolean("lootsplosion.enabled"))
/*     */             Bukkit.getPluginManager().registerEvents((Listener)new LootsplosionListener(), (Plugin)this); 
/*     */         });
/* 159 */     PluginUtils.hookDependencyIfPresent("MMOInventory", paramVoid -> new MMOInventorySupport());
/*     */     
/* 161 */     findRpgPlugins();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     this.statManager.loadElements();
/* 168 */     this.formatManager.reload();
/* 169 */     this.tierManager = new TierManager();
/* 170 */     this.setManager = new SetManager();
/* 171 */     this.upgradeManager = new UpgradeManager();
/* 172 */     this.templateManager.postloadTemplates();
/*     */     
/* 174 */     this.dropTableManager = new DropTableManager();
/* 175 */     this.worldGenManager = new WorldGenManager();
/* 176 */     this.blockManager = new BlockManager();
/* 177 */     this.statManager.reload(false);
/*     */     
/* 179 */     PluginUtils.hookDependencyIfPresent("Vault", paramVoid -> this.vaultSupport = new VaultSupport());
/*     */     
/* 181 */     getLogger().log(Level.INFO, "Loading crafting stations, please wait..");
/* 182 */     this.layoutManager.reload();
/* 183 */     this.stationRecipeManager.reload();
/*     */ 
/*     */     
/* 186 */     NumericStatFormula.reload();
/* 187 */     MMOItemReforger.reload();
/*     */     
/* 189 */     Bukkit.getPluginManager().registerEvents((Listener)this.entityManager, (Plugin)this);
/* 190 */     Bukkit.getPluginManager().registerEvents((Listener)this.dropTableManager, (Plugin)this);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 195 */       Class.forName("net.Indyuce.mmoitems.MMOItemsBukkit").getConstructor(new Class[] { MMOItems.class }).newInstance(new Object[] { this });
/* 196 */     } catch (Throwable throwable) {
/* 197 */       throw new RuntimeException("Cannot run an API build on Spigot!");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     Bukkit.getScheduler().runTaskTimer((Plugin)this, () -> { for (Player player : Bukkit.getOnlinePlayers()) PlayerData.get((OfflinePlayer)player).getInventory().updateCheck();  }100L, 
/*     */ 
/*     */         
/* 208 */         getConfig().getInt("inventory-update-delay"));
/*     */     
/* 210 */     PluginUtils.isDependencyPresent("mcMMO", paramVoid -> Bukkit.getPluginManager().registerEvents((Listener)new McMMONonRPGHook(), (Plugin)this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     getInventory().register((PlayerInventory)new DefaultPlayerInventory());
/* 217 */     PluginUtils.hookDependencyIfPresent("RPGInventory", paramVoid -> getInventory().register((PlayerInventory)new RPGInventoryHook()));
/* 218 */     if (plugin.getConfig().getBoolean("iterate-whole-inventory")) {
/* 219 */       getInventory().register((PlayerInventory)new OrnamentPlayerInventory());
/*     */     }
/* 221 */     PluginUtils.hookDependencyIfPresent("CrazyEnchantments", paramVoid -> getStats().register((ItemStat)new CrazyEnchantsStat()));
/* 222 */     PluginUtils.hookDependencyIfPresent("AdvancedEnchantments", paramVoid -> Bukkit.getPluginManager().registerEvents((Listener)new AdvancedEnchantmentsHook(), (Plugin)this));
/* 223 */     PluginUtils.hookDependencyIfPresent("PlaceholderAPI", paramVoid -> (new MMOItemsPlaceholders()).register());
/*     */     
/* 225 */     if (Bukkit.getPluginManager().getPlugin("BossShopPro") != null) {
/* 226 */       getLogger().log(Level.INFO, "Hooked onto BossShopPro");
/* 227 */       (new BukkitRunnable()
/*     */         {
/*     */           public void run() {
/*     */             try {
/* 231 */               (new MMOItemsRewardTypes()).register();
/* 232 */             } catch (NullPointerException nullPointerException) {
/* 233 */               MMOItems.this.getLogger().log(Level.INFO, "Could not Hook onto BossShopPro");
/*     */             } 
/*     */           }
/* 236 */         }).runTaskLater((Plugin)this, 1L);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     this.playerDataManager = new PlayerDataManager();
/* 246 */     this.playerDataManager.initialize(EventPriority.NORMAL, EventPriority.HIGHEST);
/*     */ 
/*     */     
/* 249 */     getLogger().log(Level.INFO, "Loading recipes, please wait...");
/* 250 */     this.recipeManager.loadRecipes();
/*     */ 
/*     */     
/* 253 */     MMOItemsCommandTreeRoot mMOItemsCommandTreeRoot = new MMOItemsCommandTreeRoot();
/* 254 */     getCommand("mmoitems").setExecutor((CommandExecutor)mMOItemsCommandTreeRoot);
/* 255 */     getCommand("mmoitems").setTabCompleter((TabCompleter)mMOItemsCommandTreeRoot);
/*     */ 
/*     */     
/* 258 */     this.hasLoadedSuccessfully = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 264 */     if (!this.hasLoadedSuccessfully) {
/*     */       return;
/*     */     }
/*     */     
/* 268 */     this.playerDataManager.saveAll(false);
/*     */ 
/*     */     
/* 271 */     DeathItemsHandler.getActive().forEach(paramDeathItemsHandler -> paramDeathItemsHandler.giveItems(true));
/*     */ 
/*     */     
/* 274 */     for (Player player : Bukkit.getOnlinePlayers()) {
/* 275 */       if (player.getOpenInventory() != null && player.getOpenInventory().getTopInventory().getHolder() instanceof net.Indyuce.mmoitems.gui.PluginInventory) {
/* 276 */         player.closeInventory();
/*     */       }
/*     */     } 
/* 279 */     this.worldGenManager.unload();
/*     */   }
/*     */   
/*     */   public String getPrefix() {
/* 283 */     return ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "MMOItems" + ChatColor.DARK_GRAY + "] " + ChatColor.GRAY;
/*     */   }
/*     */   
/*     */   public File getJarFile() {
/* 287 */     return plugin.getFile();
/*     */   }
/*     */   
/*     */   public CraftingManager getCrafting() {
/* 291 */     return this.stationRecipeManager;
/*     */   }
/*     */   
/*     */   public LayoutManager getLayouts() {
/* 295 */     return this.layoutManager;
/*     */   }
/*     */   
/*     */   public SetManager getSets() {
/* 299 */     return this.setManager;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public RPGHandler getRPG() {
/* 304 */     return getMainRPG();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public RPGHandler getMainRPG() {
/* 309 */     Validate.isTrue(!this.rpgPlugins.isEmpty(), "No RPG plugin was found");
/* 310 */     return this.rpgPlugins.get(0);
/*     */   }
/*     */   
/*     */   public List<RPGHandler> getRPGs() {
/* 314 */     return this.rpgPlugins;
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
/*     */   public void findRpgPlugins() {
/* 327 */     Validate.isTrue(this.rpgPlugins.isEmpty(), "RPG hooks have already been computed");
/*     */ 
/*     */     
/* 330 */     this.rpgPlugins.add(new DefaultHook());
/*     */ 
/*     */     
/* 333 */     String str = plugin.getConfig().getString("preferred-rpg-provider");
/*     */ 
/*     */     
/* 336 */     for (RPGHandler.PluginEnum pluginEnum : RPGHandler.PluginEnum.values()) {
/* 337 */       if (Bukkit.getPluginManager().getPlugin(pluginEnum.getName()) != null) {
/*     */         try {
/* 339 */           RPGHandler rPGHandler1 = pluginEnum.load();
/* 340 */           this.rpgPlugins.add(rPGHandler1);
/* 341 */           getLogger().log(Level.INFO, "Hooked onto " + pluginEnum.getName());
/*     */ 
/*     */           
/* 344 */           if (str.equalsIgnoreCase(pluginEnum.name())) {
/* 345 */             Collections.swap(this.rpgPlugins, 0, this.rpgPlugins.size() - 1);
/* 346 */             getLogger().log(Level.INFO, "Now using " + pluginEnum.getName() + " as RPG core plugin");
/*     */           }
/*     */         
/* 349 */         } catch (Exception exception) {
/* 350 */           plugin.getLogger().log(Level.WARNING, "Could not initialize RPG plugin compatibility with " + pluginEnum.getName() + ":");
/* 351 */           exception.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/* 355 */     RPGHandler rPGHandler = this.rpgPlugins.get(0);
/* 356 */     if (this.rpgPlugins.get(0) instanceof Listener) {
/* 357 */       Bukkit.getPluginManager().registerEvents((Listener)rPGHandler, (Plugin)this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRPG(@NotNull RPGHandler paramRPGHandler) {
/* 368 */     Validate.notNull(paramRPGHandler, "RPGHandler cannot be null");
/*     */ 
/*     */     
/* 371 */     if (getMainRPG() instanceof Listener && isEnabled()) {
/* 372 */       HandlerList.unregisterAll((Plugin)getMainRPG());
/*     */     }
/* 374 */     this.rpgPlugins.add(0, paramRPGHandler);
/* 375 */     getLogger().log(Level.INFO, "Now using " + paramRPGHandler.getClass().getSimpleName() + " as RPG provider");
/*     */ 
/*     */     
/* 378 */     if (paramRPGHandler instanceof Listener && isEnabled())
/* 379 */       Bukkit.getPluginManager().registerEvents((Listener)paramRPGHandler, (Plugin)this); 
/*     */   }
/*     */   
/*     */   public PluginUpdateManager getUpdates() {
/* 383 */     return this.pluginUpdateManager;
/*     */   }
/*     */   
/*     */   public PlayerInventoryHandler getInventory() {
/* 387 */     return this.inventory;
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
/*     */   public void registerPlayerInventory(PlayerInventory paramPlayerInventory) {
/* 400 */     getInventory().register(paramPlayerInventory);
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
/*     */   @Deprecated
/*     */   public void setPlayerInventory(PlayerInventory paramPlayerInventory) {
/* 423 */     getInventory().unregisterAll();
/*     */ 
/*     */     
/* 426 */     getInventory().register(paramPlayerInventory);
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
/*     */   public void registerEnchantPlugin(EnchantPlugin<? extends Enchantment> paramEnchantPlugin) {
/* 438 */     Validate.notNull(paramEnchantPlugin, "Enchant plugin cannot be null");
/* 439 */     this.enchantPlugins.add(paramEnchantPlugin);
/*     */   }
/*     */   
/*     */   public PlayerDataManager getPlayerDataManager() {
/* 443 */     return this.playerDataManager;
/*     */   }
/*     */   
/*     */   public StatManager getStats() {
/* 447 */     return this.statManager;
/*     */   }
/*     */   
/*     */   public TierManager getTiers() {
/* 451 */     return this.tierManager;
/*     */   }
/*     */   
/*     */   public EntityManager getEntities() {
/* 455 */     return this.entityManager;
/*     */   }
/*     */   
/*     */   public DropTableManager getDropTables() {
/* 459 */     return this.dropTableManager;
/*     */   }
/*     */   
/*     */   public SkillManager getSkills() {
/* 463 */     return this.skillManager;
/*     */   }
/*     */   
/*     */   public BlockManager getCustomBlocks() {
/* 467 */     return this.blockManager;
/*     */   }
/*     */   
/*     */   public WorldGenManager getWorldGen() {
/* 471 */     return this.worldGenManager;
/*     */   }
/*     */   
/*     */   public RecipeManager getRecipes() {
/* 475 */     return this.recipeManager;
/*     */   }
/*     */   
/*     */   public ConfigManager getLanguage() {
/* 479 */     return this.configManager;
/*     */   }
/*     */   
/*     */   public TypeManager getTypes() {
/* 483 */     return this.typeManager;
/*     */   }
/*     */   
/*     */   public UpgradeManager getUpgrades() {
/* 487 */     return this.upgradeManager;
/*     */   }
/*     */   
/*     */   public TemplateManager getTemplates() {
/* 491 */     return this.templateManager;
/*     */   }
/*     */   
/*     */   public LoreFormatManager getFormats() {
/* 495 */     return this.formatManager;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ItemManager getItems() {
/* 500 */     return this.itemManager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPermissions() {
/* 507 */     return (this.vaultSupport != null && this.vaultSupport.getPermissions() != null);
/*     */   }
/*     */   
/*     */   public List<EnchantPlugin<? extends Enchantment>> getEnchantPlugins() {
/* 511 */     return this.enchantPlugins;
/*     */   }
/*     */   
/*     */   public boolean hasEconomy() {
/* 515 */     return (this.vaultSupport != null && this.vaultSupport.getEconomy() != null);
/*     */   }
/*     */   
/*     */   public VaultSupport getVault() {
/* 519 */     return this.vaultSupport;
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
/*     */   @Nullable
/*     */   public MMOItem getMMOItem(@Nullable Type paramType, @Nullable String paramString, @Nullable PlayerData paramPlayerData) {
/* 532 */     if (paramType == null || paramString == null) {
/* 533 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 537 */     MMOItemTemplate mMOItemTemplate = getTemplates().getTemplate(paramType, paramString);
/* 538 */     if (mMOItemTemplate == null) return null;
/*     */ 
/*     */     
/* 541 */     return mMOItemTemplate.newBuilder(paramPlayerData).build();
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
/*     */   @Nullable
/*     */   public ItemStack getItem(@Nullable Type paramType, @Nullable String paramString, @NotNull PlayerData paramPlayerData) {
/* 554 */     MMOItem mMOItem = getMMOItem(paramType, paramString, paramPlayerData);
/* 555 */     if (mMOItem == null) return null;
/*     */ 
/*     */     
/* 558 */     return mMOItem.newBuilder().build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MMOItem getMMOItem(@Nullable Type paramType, @Nullable String paramString, int paramInt, @Nullable ItemTier paramItemTier) {
/* 569 */     if (paramType == null || paramString == null) {
/* 570 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 574 */     MMOItemTemplate mMOItemTemplate = getTemplates().getTemplate(paramType, paramString);
/* 575 */     if (mMOItemTemplate == null) return null;
/*     */ 
/*     */     
/* 578 */     return mMOItemTemplate.newBuilder(paramInt, paramItemTier).build();
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
/*     */   @Nullable
/*     */   public ItemStack getItem(@Nullable Type paramType, @Nullable String paramString, int paramInt, @Nullable ItemTier paramItemTier) {
/* 591 */     MMOItem mMOItem = getMMOItem(paramType, paramString, paramInt, paramItemTier);
/* 592 */     if (mMOItem == null) return null;
/*     */ 
/*     */     
/* 595 */     return mMOItem.newBuilder().build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MMOItem getMMOItem(@Nullable Type paramType, @Nullable String paramString) {
/* 607 */     return getMMOItem(paramType, paramString, 0, (ItemTier)null);
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
/*     */   @Nullable
/*     */   public ItemStack getItem(@Nullable String paramString1, @Nullable String paramString2) {
/* 620 */     if (paramString1 == null || paramString2 == null) {
/* 621 */       return null;
/*     */     }
/* 623 */     return getItem(getTypes().get(paramString1), paramString2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getItem(@Nullable Type paramType, @Nullable String paramString) {
/* 635 */     if (paramType == null || paramString == null) {
/* 636 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 640 */     MMOItem mMOItem = getMMOItem(paramType, paramString);
/* 641 */     if (mMOItem == null) {
/* 642 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 646 */     return mMOItem.newBuilder().build();
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
/*     */   @Nullable
/*     */   public static Type getType(@Nullable ItemStack paramItemStack) {
/* 660 */     return getType(NBTItem.get(paramItemStack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Type getType(@Nullable NBTItem paramNBTItem) {
/* 671 */     return plugin.getTypes().get(getTypeName(paramNBTItem));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getTypeName(@Nullable ItemStack paramItemStack) {
/* 683 */     return getTypeName(NBTItem.get(paramItemStack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getTypeName(@Nullable NBTItem paramNBTItem) {
/* 694 */     if (paramNBTItem == null) {
/* 695 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 699 */     if (!paramNBTItem.hasType()) {
/* 700 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 704 */     return paramNBTItem.getType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getID(@Nullable ItemStack paramItemStack) {
/* 716 */     return getID(NBTItem.get(paramItemStack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getID(@Nullable NBTItem paramNBTItem) {
/* 727 */     if (paramNBTItem == null) {
/* 728 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 732 */     return paramNBTItem.getString("MMOITEMS_ITEM_ID");
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
/*     */   public static void print(@Nullable Level paramLevel, @Nullable String paramString1, @Nullable String paramString2, @NotNull String... paramVarArgs) {
/* 744 */     if (paramString1 == null) {
/* 745 */       paramString1 = "< null >";
/*     */     }
/* 747 */     if (paramLevel != null) {
/* 748 */       plugin.getLogger().log(paramLevel, FriendlyFeedbackProvider.quickForConsole((FriendlyFeedbackPalette)FFPMMOItems.get(), paramString1, paramVarArgs));
/*     */     } else {
/* 750 */       FriendlyFeedbackMessage friendlyFeedbackMessage1 = new FriendlyFeedbackMessage("", paramString2);
/* 751 */       FriendlyFeedbackMessage friendlyFeedbackMessage2 = FriendlyFeedbackProvider.generateMessage(friendlyFeedbackMessage1, paramString1, paramVarArgs);
/* 752 */       getConsole().sendMessage(friendlyFeedbackMessage2.forConsole((FriendlyFeedbackPalette)FFPMMOItems.get()));
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
/*     */   public static void log(@Nullable String paramString, @NotNull String... paramVarArgs) {
/* 766 */     print((Level)null, paramString, (String)null, paramVarArgs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static ConsoleCommandSender getConsole() {
/* 775 */     return plugin.getServer().getConsoleSender();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\MMOItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */