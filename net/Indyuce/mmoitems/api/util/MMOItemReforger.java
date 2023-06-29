/*     */ package net.Indyuce.mmoitems.api.util;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Objects;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.ReforgeOptions;
/*     */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*     */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeFinishEvent;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MMOItemReforger
/*     */ {
/*     */   @NotNull
/*     */   private final NBTItem nbtItem;
/*     */   @Nullable
/*     */   private ItemStack result;
/*     */   @Nullable
/*     */   private RPGPlayer player;
/*     */   @Nullable
/*     */   LiveMMOItem oldMMOItem;
/*     */   @Nullable
/*     */   private final MMOItemTemplate template;
/*     */   @Nullable
/*     */   private MMOItem freshMMOItem;
/*     */   @NotNull
/*     */   final ArrayList<ItemStack> reforgingOutput;
/*     */   int generationItemLevel;
/*     */   
/*     */   public MMOItemReforger(@NotNull ItemStack paramItemStack) {
/*  54 */     this(NBTItem.get(paramItemStack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public MMOItemReforger(@Nullable ItemStack paramItemStack, @NotNull NBTItem paramNBTItem) {
/*  65 */     this(paramNBTItem);
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
/*     */   @NotNull
/*     */   public NBTItem getNBTItem() {
/*     */     return this.nbtItem;
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
/*     */   @NotNull
/*     */   public ItemStack getStack() {
/*     */     return this.nbtItem.getItem();
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
/*     */   @Nullable
/*     */   public ItemStack getResult() {
/*     */     return this.result;
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
/*     */   public void setResult(@Nullable ItemStack paramItemStack) {
/*     */     this.result = paramItemStack;
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
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public ItemMeta getMeta() {
/*     */     return getStack().getItemMeta();
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
/*     */   @Nullable
/*     */   public RPGPlayer getPlayer() {
/*     */     return this.player;
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
/*     */   public MMOItemReforger(@NotNull NBTItem paramNBTItem) {
/* 264 */     this.reforgingOutput = new ArrayList<>();
/*     */     this.nbtItem = paramNBTItem;
/*     */     Validate.isTrue((paramNBTItem.getItem().getItemMeta() != null), "ItemStack has no ItemMeta, cannot be reforged.");
/*     */     this.template = paramNBTItem.hasType() ? MMOItems.plugin.getTemplates().getTemplate(paramNBTItem) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setPlayer(@Nullable Player paramPlayer) {
/*     */     setPlayer((paramPlayer == null) ? null : PlayerData.get((OfflinePlayer)paramPlayer).getRPG());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addReforgingOutput(@Nullable ItemStack paramItemStack) {
/* 282 */     if (!SilentNumbers.isAir(paramItemStack) && paramItemStack.getType().isItem())
/*     */     {
/* 284 */       this.reforgingOutput.add(paramItemStack);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setPlayer(@Nullable RPGPlayer paramRPGPlayer) {
/*     */     this.player = paramRPGPlayer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearReforgingOutput() {
/* 299 */     this.reforgingOutput.clear();
/*     */   } @NotNull
/*     */   public LiveMMOItem getOldMMOItem() {
/*     */     return this.oldMMOItem;
/*     */   }
/*     */   @NotNull
/*     */   public MMOItemTemplate getTemplate() {
/*     */     return Objects.<MMOItemTemplate>requireNonNull(this.template, "No item template was found");
/*     */   }
/*     */   @NotNull
/*     */   public MMOItem getFreshMMOItem() {
/*     */     return this.freshMMOItem;
/*     */   }
/*     */   public void setFreshMMOItem(@NotNull MMOItem paramMMOItem) {
/*     */     this.freshMMOItem = paramMMOItem;
/*     */   }
/*     */   @NotNull
/*     */   public ArrayList<ItemStack> getReforgingOutput() {
/* 317 */     return this.reforgingOutput;
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public boolean canReforge() {
/*     */     return hasTemplate();
/*     */   }
/*     */   
/*     */   public boolean hasTemplate() {
/*     */     return (this.template != null);
/*     */   }
/*     */   
/*     */   public int getGenerationItemLevel() {
/* 331 */     return this.generationItemLevel;
/*     */   }
/*     */   
/*     */   public boolean reforge(@NotNull ReforgeOptions paramReforgeOptions) {
/* 335 */     return reforge(paramReforgeOptions, (RPGPlayer)null);
/*     */   }
/*     */   
/*     */   public boolean reforge(@NotNull ReforgeOptions paramReforgeOptions, @Nullable Player paramPlayer) {
/* 339 */     return reforge(paramReforgeOptions, (paramPlayer == null) ? null : PlayerData.get((OfflinePlayer)paramPlayer).getRPG());
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
/*     */   public boolean reforge(@NotNull ReforgeOptions paramReforgeOptions, @Nullable RPGPlayer paramRPGPlayer) {
/* 359 */     if (!hasTemplate()) return false;
/*     */ 
/*     */     
/* 362 */     this.oldMMOItem = new LiveMMOItem(getNBTItem());
/*     */ 
/*     */     
/* 365 */     if (paramReforgeOptions.isBlacklisted(getOldMMOItem().getId())) return false;
/*     */     
/* 367 */     this.player = paramRPGPlayer;
/*     */ 
/*     */     
/* 370 */     this.generationItemLevel = getOldMMOItem().hasData(ItemStats.ITEM_LEVEL) ? (int)((DoubleData)getOldMMOItem().getData(ItemStats.ITEM_LEVEL)).getValue() : 0;
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
/* 382 */     ItemTier itemTier = (paramReforgeOptions.shouldKeepTier() && getOldMMOItem().hasData(ItemStats.TIER)) ? MMOItems.plugin.getTiers().get(getOldMMOItem().getData(ItemStats.TIER).toString()) : null;
/*     */ 
/*     */     
/* 385 */     setFreshMMOItem(getTemplate().newBuilder(this.generationItemLevel, itemTier).build());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 390 */     MMOItemReforgeEvent mMOItemReforgeEvent = new MMOItemReforgeEvent(this, paramReforgeOptions);
/* 391 */     Bukkit.getPluginManager().callEvent((Event)mMOItemReforgeEvent);
/*     */ 
/*     */     
/* 394 */     if (mMOItemReforgeEvent.isCancelled()) return false;
/*     */ 
/*     */     
/* 397 */     for (StatHistory statHistory : getFreshMMOItem().getStatHistories())
/*     */     {
/* 399 */       getFreshMMOItem().setData(statHistory.getItemStat(), statHistory.recalculate(getFreshMMOItem().getUpgradeLevel()));
/*     */     }
/* 401 */     if (getFreshMMOItem().hasUpgradeTemplate())
/*     */     {
/* 403 */       for (ItemStat itemStat : getFreshMMOItem().getUpgradeTemplate().getKeys()) {
/*     */ 
/*     */         
/* 406 */         StatHistory statHistory = StatHistory.from(getFreshMMOItem(), itemStat);
/*     */ 
/*     */         
/* 409 */         getFreshMMOItem().setData(statHistory.getItemStat(), statHistory.recalculate(getFreshMMOItem().getUpgradeLevel()));
/*     */       } 
/*     */     }
/*     */     
/* 413 */     this.result = getFreshMMOItem().newBuilder().build();
/*     */ 
/*     */     
/* 416 */     MMOItemReforgeFinishEvent mMOItemReforgeFinishEvent = new MMOItemReforgeFinishEvent(this.result, this, paramReforgeOptions);
/* 417 */     Bukkit.getPluginManager().callEvent((Event)mMOItemReforgeFinishEvent);
/*     */ 
/*     */     
/* 420 */     setResult(mMOItemReforgeFinishEvent.getFinishedItem());
/*     */ 
/*     */ 
/*     */     
/* 424 */     return !mMOItemReforgeFinishEvent.isCancelled();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean keepTiersWhenReroll = true;
/*     */   public static boolean gemstonesRevIDWhenUnsocket = false;
/*     */   
/*     */   public static void reload() {
/* 432 */     keepTiersWhenReroll = MMOItems.plugin.getConfig().getBoolean("item-revision.keep-tiers");
/* 433 */     gemstonesRevIDWhenUnsocket = MMOItems.plugin.getConfig().getBoolean("item-revision.regenerate-gems-when-unsocketed", false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void update(@Nullable Player paramPlayer, @NotNull ReforgeOptions paramReforgeOptions) {
/* 440 */     reforge(paramReforgeOptions, paramPlayer);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void update(@Nullable RPGPlayer paramRPGPlayer, @NotNull ReforgeOptions paramReforgeOptions) {
/* 445 */     reforge(paramReforgeOptions, paramRPGPlayer);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   void regenerate(@Nullable RPGPlayer paramRPGPlayer) {
/* 450 */     reforge(new ReforgeOptions(new boolean[] { false, false, false, false, false, false, false, true }, ), paramRPGPlayer);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   int regenerate(@Nullable RPGPlayer paramRPGPlayer, @NotNull MMOItemTemplate paramMMOItemTemplate) {
/* 455 */     reforge(new ReforgeOptions(new boolean[] { false, false, false, false, false, false, false, true }, ), paramRPGPlayer);
/* 456 */     return 0;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void reforge(@Nullable Player paramPlayer, @NotNull ReforgeOptions paramReforgeOptions) {
/* 461 */     reforge(paramReforgeOptions, paramPlayer);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void reforge(@Nullable RPGPlayer paramRPGPlayer, @NotNull ReforgeOptions paramReforgeOptions) {
/* 466 */     reforge(paramReforgeOptions, paramRPGPlayer);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ItemStack toStack() {
/* 471 */     return getResult();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public boolean hasChanges() {
/* 476 */     return (getResult() != null);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public ArrayList<MMOItem> getDestroyedGems() {
/* 482 */     return new ArrayList<>();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\MMOItemReforger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */