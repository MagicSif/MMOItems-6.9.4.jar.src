/*     */ package net.Indyuce.mmoitems.api.util;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.player.inventory.EquippedItem;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.data.UpgradeData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.Contract;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class DeathDowngrading
/*     */ {
/*  29 */   private static final Random RANDOM = new Random();
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
/*     */   public static void playerDeathDowngrade(@NotNull Player paramPlayer) {
/*  44 */     PlayerData playerData = PlayerData.get((OfflinePlayer)paramPlayer);
/*     */ 
/*     */     
/*  47 */     double d = playerData.getStats().getStat(ItemStats.DOWNGRADE_ON_DEATH_CHANCE);
/*     */     
/*  49 */     if (d <= 0.0D) {
/*     */       return;
/*     */     }
/*  52 */     playerData.updateInventory();
/*  53 */     List<EquippedItem> list = playerData.getInventory().getEquipped();
/*  54 */     for (Iterator<EquippedItem> iterator = list.iterator(); iterator.hasNext(); ) {
/*  55 */       EquippedItem equippedItem = iterator.next();
/*  56 */       if (equippedItem == null || !canDeathDowngrade((MMOItem)equippedItem.getCached())) {
/*  57 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */     
/*  61 */     if (list.size() == 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  67 */     while (d >= 100.0D && list.size() > 0) {
/*     */ 
/*     */       
/*  70 */       d -= 100.0D;
/*     */ 
/*     */       
/*  73 */       int i = RANDOM.nextInt(list.size());
/*  74 */       EquippedItem equippedItem = list.get(i);
/*     */ 
/*     */       
/*  77 */       equippedItem.setItem(downgrade(new LiveMMOItem(equippedItem.getNBT()), paramPlayer));
/*  78 */       list.remove(i);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  84 */     if (d > 0.0D && list.size() > 0 && RANDOM.nextInt(100) < d) {
/*     */ 
/*     */       
/*  87 */       int i = RANDOM.nextInt(list.size());
/*  88 */       EquippedItem equippedItem = list.get(i);
/*     */ 
/*     */       
/*  91 */       equippedItem.setItem(downgrade(new LiveMMOItem(equippedItem.getNBT()), paramPlayer));
/*  92 */       list.remove(i);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static ArrayList<ItemStack> downgradeItems(@NotNull List<ItemStack> paramList, @NotNull Player paramPlayer, double paramDouble) {
/* 111 */     ArrayList<ItemStack> arrayList1 = new ArrayList();
/* 112 */     ArrayList<ItemStack> arrayList2 = new ArrayList();
/*     */ 
/*     */     
/* 115 */     for (ItemStack itemStack : paramList) {
/*     */ 
/*     */       
/* 118 */       if (SilentNumbers.isAir(itemStack)) {
/*     */         continue;
/*     */       }
/* 121 */       if (canDeathDowngrade(itemStack)) {
/*     */ 
/*     */         
/* 124 */         arrayList2.add(itemStack);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 129 */       arrayList1.add(itemStack);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 134 */     while (paramDouble >= 100.0D && arrayList2.size() > 0) {
/*     */ 
/*     */       
/* 137 */       paramDouble -= 100.0D;
/*     */ 
/*     */       
/* 140 */       int i = RANDOM.nextInt(arrayList2.size());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 145 */       ItemStack itemStack = arrayList2.get(i);
/*     */ 
/*     */       
/* 148 */       arrayList1.add(downgrade(new LiveMMOItem(itemStack), paramPlayer));
/*     */ 
/*     */       
/* 151 */       arrayList2.remove(i);
/*     */     } 
/*     */ 
/*     */     
/* 155 */     if (paramDouble > 0.0D && arrayList2.size() > 0 && RANDOM.nextInt(100) < paramDouble) {
/*     */ 
/*     */       
/* 158 */       int i = RANDOM.nextInt(arrayList2.size());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 163 */       ItemStack itemStack = arrayList2.get(i);
/*     */ 
/*     */       
/* 166 */       arrayList1.add(downgrade(new LiveMMOItem(itemStack), paramPlayer));
/*     */ 
/*     */       
/* 169 */       arrayList2.remove(i);
/*     */     } 
/*     */ 
/*     */     
/* 173 */     arrayList1.addAll(arrayList2);
/*     */ 
/*     */     
/* 176 */     return arrayList1;
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
/*     */   public static ItemStack downgrade(@NotNull ItemStack paramItemStack, @NotNull Player paramPlayer) {
/* 191 */     if (SilentNumbers.isAir(paramItemStack) || !paramItemStack.getType().isItem()) return paramItemStack;
/*     */ 
/*     */     
/* 194 */     NBTItem nBTItem = NBTItem.get(paramItemStack);
/* 195 */     if (!nBTItem.hasType()) return paramItemStack;
/*     */ 
/*     */     
/* 198 */     return downgrade(new LiveMMOItem(nBTItem), paramPlayer);
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
/*     */   @NotNull
/*     */   public static ItemStack downgrade(@NotNull LiveMMOItem paramLiveMMOItem, @NotNull Player paramPlayer) {
/* 212 */     paramLiveMMOItem.getUpgradeTemplate().upgradeTo((MMOItem)paramLiveMMOItem, paramLiveMMOItem.getUpgradeLevel() - 1);
/*     */ 
/*     */     
/* 215 */     ItemStack itemStack = paramLiveMMOItem.newBuilder().build();
/*     */ 
/*     */     
/* 218 */     DurabilityItem durabilityItem = new DurabilityItem(paramPlayer, paramLiveMMOItem.newBuilder().buildNBT());
/*     */ 
/*     */     
/* 221 */     if (durabilityItem.getDurability() != durabilityItem.getMaxDurability()) {
/* 222 */       durabilityItem.addDurability(durabilityItem.getMaxDurability());
/* 223 */       itemStack.setItemMeta(durabilityItem.toItem().getItemMeta());
/*     */     } 
/*     */     
/* 226 */     Message.DEATH_DOWNGRADING.format(ChatColor.RED, new String[] { "#item#", MMOUtils.getDisplayName(paramLiveMMOItem.getNBT().getItem()) }).send(paramPlayer);
/*     */ 
/*     */     
/* 229 */     return itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double getDeathDowngradeChance(@NotNull Player paramPlayer) {
/* 240 */     PlayerData playerData = PlayerData.get((OfflinePlayer)paramPlayer);
/*     */ 
/*     */     
/* 243 */     return playerData.getStats().getStat(ItemStats.DOWNGRADE_ON_DEATH_CHANCE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Contract("null->false")
/*     */   public static boolean canDeathDowngrade(@Nullable ItemStack paramItemStack) {
/* 255 */     if (SilentNumbers.isAir(paramItemStack) || !paramItemStack.getType().isItem()) return false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 260 */     NBTItem nBTItem = NBTItem.get(paramItemStack);
/* 261 */     if (!nBTItem.hasType()) return false;
/*     */ 
/*     */     
/* 264 */     return canDeathDowngrade((MMOItem)new VolatileMMOItem(nBTItem));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Contract("null->false")
/*     */   public static boolean canDeathDowngrade(@Nullable MMOItem paramMMOItem) {
/* 275 */     if (paramMMOItem == null) return false;
/*     */ 
/*     */     
/* 278 */     if (!paramMMOItem.hasData(ItemStats.DOWNGRADE_ON_DEATH))
/*     */     {
/* 280 */       return false;
/*     */     }
/*     */     
/* 283 */     if (!paramMMOItem.hasData(ItemStats.UPGRADE))
/*     */     {
/* 285 */       return false; } 
/* 286 */     if (!paramMMOItem.hasUpgradeTemplate())
/*     */     {
/* 288 */       return false;
/*     */     }
/*     */     
/* 291 */     UpgradeData upgradeData = (UpgradeData)paramMMOItem.getData(ItemStats.UPGRADE);
/*     */ 
/*     */     
/* 294 */     return (upgradeData.getLevel() > upgradeData.getMin());
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\DeathDowngrading.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */