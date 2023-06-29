/*     */ package net.Indyuce.mmoitems.api.interaction;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.event.item.ApplyGemStoneEvent;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.Enchants;
/*     */ import net.Indyuce.mmoitems.stat.GemUpgradeScaling;
/*     */ import net.Indyuce.mmoitems.stat.data.GemSocketsData;
/*     */ import net.Indyuce.mmoitems.stat.data.GemstoneData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class GemStone
/*     */   extends UseItem
/*     */ {
/*     */   public GemStone(Player paramPlayer, NBTItem paramNBTItem) {
/*  33 */     super(paramPlayer, paramNBTItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ApplyResult applyOntoItem(@NotNull NBTItem paramNBTItem, @NotNull Type paramType) {
/*  43 */     LiveMMOItem liveMMOItem = new LiveMMOItem(paramNBTItem);
/*  44 */     return applyOntoItem((MMOItem)liveMMOItem, paramType, MMOUtils.getDisplayName(paramNBTItem.getItem()), true, false);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ApplyResult applyOntoItem(@NotNull MMOItem paramMMOItem, @NotNull Type paramType, @NotNull String paramString, boolean paramBoolean1, boolean paramBoolean2) {
/*  50 */     if (!paramMMOItem.hasData(ItemStats.GEM_SOCKETS)) {
/*  51 */       return new ApplyResult(ResultType.NONE);
/*     */     }
/*  53 */     String str1 = getNBTItem().getString(ItemStats.GEM_COLOR.getNBTPath());
/*     */     
/*  55 */     GemSocketsData gemSocketsData = (GemSocketsData)paramMMOItem.getData(ItemStats.GEM_SOCKETS);
/*  56 */     String str2 = gemSocketsData.getEmptySocket(str1);
/*  57 */     if (str2 == null) {
/*  58 */       return new ApplyResult(ResultType.NONE);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     String str3 = getNBTItem().getString(ItemStats.ITEM_TYPE_RESTRICTION.getNBTPath());
/*  65 */     if (!str3.equals("") && (!paramType.isWeapon() || !str3.contains("WEAPON")) && 
/*  66 */       !str3.contains(paramType.getItemSet().name()) && !str3.contains(paramType.getId())) {
/*  67 */       return new ApplyResult(ResultType.NONE);
/*     */     }
/*     */     
/*  70 */     double d = getNBTItem().getStat(ItemStats.SUCCESS_RATE.getId());
/*     */     
/*  72 */     if (d == 0.0D) {
/*  73 */       d = 100.0D;
/*     */     }
/*     */ 
/*     */     
/*  77 */     ApplyGemStoneEvent applyGemStoneEvent = new ApplyGemStoneEvent(this.playerData, this.mmoitem, paramMMOItem, (RANDOM.nextDouble() > d / 100.0D) ? ResultType.FAILURE : ResultType.SUCCESS);
/*  78 */     Bukkit.getPluginManager().callEvent((Event)applyGemStoneEvent);
/*     */ 
/*     */     
/*  81 */     if (applyGemStoneEvent.isCancelled() || applyGemStoneEvent.getResult() == ResultType.NONE) {
/*  82 */       return new ApplyResult(ResultType.NONE);
/*     */     }
/*     */     
/*  85 */     if (applyGemStoneEvent.getResult() == ResultType.FAILURE) {
/*  86 */       if (!paramBoolean2) {
/*  87 */         this.player.playSound(this.player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0F, 1.0F);
/*  88 */         Message.GEM_STONE_BROKE.format(ChatColor.RED, new String[] { "#gem#", MMOUtils.getDisplayName(getItem()), "#item#", paramString }).send(this.player);
/*     */       } 
/*     */       
/*  91 */       return new ApplyResult(ResultType.FAILURE);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     Enchants.separateEnchantments(paramMMOItem);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     LiveMMOItem liveMMOItem = new LiveMMOItem(getNBTItem());
/* 105 */     GemstoneData gemstoneData = new GemstoneData(liveMMOItem, str2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     StatHistory statHistory = StatHistory.from(paramMMOItem, ItemStats.GEM_SOCKETS);
/*     */ 
/*     */     
/* 116 */     if (((GemSocketsData)statHistory.getOriginalData()).getEmptySocket(str1) != null) {
/*     */ 
/*     */ 
/*     */       
/* 120 */       ((GemSocketsData)statHistory.getOriginalData()).apply(str1, gemstoneData);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 125 */       boolean bool = false;
/* 126 */       for (UUID uUID : statHistory.getAllModifiers()) {
/*     */ 
/*     */         
/* 129 */         GemSocketsData gemSocketsData1 = (GemSocketsData)statHistory.getModifiersBonus(uUID);
/* 130 */         if (gemSocketsData1 != null && gemSocketsData1.getEmptySocket(str1) != null) {
/*     */ 
/*     */ 
/*     */           
/* 134 */           bool = true;
/* 135 */           gemSocketsData1.apply(str1, gemstoneData);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 140 */       if (!bool)
/*     */       {
/* 142 */         for (StatData statData : statHistory.getExternalData()) {
/*     */ 
/*     */           
/* 145 */           GemSocketsData gemSocketsData1 = (GemSocketsData)statData;
/* 146 */           if (gemSocketsData1 != null && gemSocketsData1.getEmptySocket(str1) != null) {
/*     */ 
/*     */ 
/*     */             
/* 150 */             gemSocketsData1.apply(str1, gemstoneData);
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 159 */     paramMMOItem.setData(ItemStats.GEM_SOCKETS, statHistory.recalculate(paramMMOItem.getUpgradeLevel()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     Integer integer = null;
/* 166 */     String str4 = liveMMOItem.hasData(ItemStats.GEM_UPGRADE_SCALING) ? liveMMOItem.getData(ItemStats.GEM_UPGRADE_SCALING).toString() : GemUpgradeScaling.defaultValue;
/*     */     
/* 168 */     switch (str4) {
/*     */       case "HISTORIC":
/* 170 */         integer = Integer.valueOf(0);
/*     */         break;
/*     */       case "SUBSEQUENT":
/* 173 */         integer = Integer.valueOf(paramMMOItem.getUpgradeLevel());
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     gemstoneData.setLevel(integer);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     for (ItemStat itemStat : liveMMOItem.getStats()) {
/*     */ 
/*     */       
/* 188 */       if (!(itemStat instanceof net.Indyuce.mmoitems.stat.type.GemStoneStat)) {
/*     */ 
/*     */         
/* 191 */         StatData statData = liveMMOItem.getData(itemStat);
/*     */ 
/*     */         
/* 194 */         if (statData instanceof net.Indyuce.mmoitems.stat.data.type.Mergeable)
/*     */         {
/*     */ 
/*     */           
/* 198 */           paramMMOItem.mergeData(itemStat, statData, gemstoneData.getHistoricUUID());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 203 */     if (!paramBoolean2) {
/* 204 */       this.player.playSound(this.player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 2.0F);
/* 205 */       Message.GEM_STONE_APPLIED.format(ChatColor.YELLOW, new String[] { "#gem#", MMOUtils.getDisplayName(getItem()), "#item#", paramString }).send(this.player);
/*     */     } 
/*     */     
/* 208 */     if (paramBoolean1) {
/* 209 */       return new ApplyResult(paramMMOItem.newBuilder().build());
/*     */     }
/*     */     
/* 212 */     return new ApplyResult(paramMMOItem, ResultType.SUCCESS);
/*     */   }
/*     */   
/*     */   public static class ApplyResult {
/*     */     @NotNull
/*     */     private final GemStone.ResultType type;
/*     */     @Nullable
/*     */     private final ItemStack result;
/*     */     @Nullable
/*     */     private final MMOItem resultAsMMOItem;
/*     */     
/*     */     public ApplyResult(@NotNull GemStone.ResultType param1ResultType) {
/* 224 */       this((ItemStack)null, param1ResultType);
/*     */     }
/*     */     
/*     */     public ApplyResult(@Nullable ItemStack param1ItemStack) {
/* 228 */       this(param1ItemStack, GemStone.ResultType.SUCCESS);
/*     */     }
/*     */     
/*     */     public ApplyResult(@Nullable ItemStack param1ItemStack, @NotNull GemStone.ResultType param1ResultType) {
/* 232 */       this.type = param1ResultType;
/* 233 */       this.result = param1ItemStack;
/* 234 */       this.resultAsMMOItem = null;
/*     */     }
/*     */     
/*     */     public ApplyResult(@Nullable MMOItem param1MMOItem, @NotNull GemStone.ResultType param1ResultType) {
/* 238 */       this.type = param1ResultType;
/* 239 */       this.result = null;
/* 240 */       this.resultAsMMOItem = param1MMOItem;
/*     */     }
/*     */     
/*     */     @NotNull
/*     */     public GemStone.ResultType getType() {
/* 245 */       return this.type;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public ItemStack getResult() {
/* 250 */       return this.result;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public MMOItem getResultAsMMOItem() {
/* 255 */       return this.resultAsMMOItem;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ResultType
/*     */   {
/* 265 */     FAILURE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 272 */     NONE,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 277 */     SUCCESS;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\interaction\GemStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */