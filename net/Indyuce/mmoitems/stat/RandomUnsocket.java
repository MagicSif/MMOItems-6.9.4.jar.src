/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.event.item.UnsocketGemStoneEvent;
/*     */ import net.Indyuce.mmoitems.api.interaction.Consumable;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.VolatileMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.GemSocketsData;
/*     */ import net.Indyuce.mmoitems.stat.data.GemstoneData;
/*     */ import net.Indyuce.mmoitems.stat.type.ConsumableItemInteraction;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import net.Indyuce.mmoitems.util.Pair;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomUnsocket
/*     */   extends DoubleStat
/*     */   implements ConsumableItemInteraction
/*     */ {
/*     */   public RandomUnsocket() {
/*  44 */     super("RANDOM_UNSOCKET", Material.BOWL, "Random Unsocket", new String[] { "Number of gems (rounded down)", "that will pop out of an item when", "this is applied." }, new String[] { "consumable" }, new Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean handleConsumableEffect(@NotNull InventoryClickEvent paramInventoryClickEvent, @NotNull PlayerData paramPlayerData, @NotNull Consumable paramConsumable, @NotNull NBTItem paramNBTItem, Type paramType) {
/*  55 */     VolatileMMOItem volatileMMOItem1 = paramConsumable.getMMOItem();
/*  56 */     if (!volatileMMOItem1.hasData(ItemStats.RANDOM_UNSOCKET)) return false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     if (paramType == null) return false;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     VolatileMMOItem volatileMMOItem2 = new VolatileMMOItem(paramNBTItem);
/*  67 */     if (!volatileMMOItem2.hasData(ItemStats.GEM_SOCKETS)) return false; 
/*  68 */     GemSocketsData gemSocketsData = (GemSocketsData)volatileMMOItem2.getData(ItemStats.GEM_SOCKETS);
/*  69 */     if (gemSocketsData == null || gemSocketsData.getGemstones().size() == 0) return false; 
/*  70 */     Player player = paramPlayerData.getPlayer();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     LiveMMOItem liveMMOItem = new LiveMMOItem(paramNBTItem);
/*  78 */     List<Pair> list = liveMMOItem.extractGemstones();
/*  79 */     if (list.isEmpty()) {
/*  80 */       Message.RANDOM_UNSOCKET_GEM_TOO_OLD.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramInventoryClickEvent.getCurrentItem()) }).send(player);
/*  81 */       return false;
/*     */     } 
/*     */     
/*  84 */     DoubleData doubleData = (DoubleData)paramConsumable.getMMOItem().getData(ItemStats.RANDOM_UNSOCKET);
/*  85 */     int i = 1; if (doubleData != null) i = SilentNumbers.floor(doubleData.getValue());
/*     */ 
/*     */ 
/*     */     
/*  89 */     UnsocketGemStoneEvent unsocketGemStoneEvent = new UnsocketGemStoneEvent(paramPlayerData, volatileMMOItem1, (MMOItem)liveMMOItem);
/*  90 */     Bukkit.getServer().getPluginManager().callEvent((Event)unsocketGemStoneEvent);
/*  91 */     if (unsocketGemStoneEvent.isCancelled()) return false;
/*     */ 
/*     */     
/*  94 */     ArrayList<ItemStack> arrayList = new ArrayList();
/*  95 */     while (i > 0 && list.size() > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 100 */       int j = SilentNumbers.floor(SilentNumbers.randomRange(0.0D, list.size()));
/* 101 */       if (j >= list.size()) j = list.size() - 1;
/*     */ 
/*     */       
/* 104 */       Pair pair = list.get(j);
/* 105 */       MMOItem mMOItem = (MMOItem)pair.getValue();
/* 106 */       GemstoneData gemstoneData = (GemstoneData)pair.getKey();
/*     */       
/* 108 */       list.remove(j);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 114 */         ItemStack itemStack = mMOItem.newBuilder().build();
/*     */ 
/*     */ 
/*     */         
/* 118 */         if (!SilentNumbers.isAir(itemStack)) {
/*     */           String str;
/*     */           
/* 121 */           arrayList.add(itemStack);
/*     */           
/* 123 */           if (gemstoneData.getSocketColor() != null) {
/*     */             
/* 125 */             str = gemstoneData.getSocketColor();
/*     */           } else {
/* 127 */             str = GemSocketsData.getUncoloredGemSlot();
/*     */           } 
/*     */           
/* 130 */           liveMMOItem.removeGemStone(gemstoneData.getHistoricUUID(), str);
/*     */ 
/*     */           
/* 133 */           i--;
/*     */ 
/*     */           
/* 136 */           Message.RANDOM_UNSOCKET_SUCCESS.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(paramInventoryClickEvent.getCurrentItem()), "#gem#", MMOUtils.getDisplayName(itemStack) }).send(player);
/*     */         } 
/*     */       } catch (Throwable throwable) {
/* 139 */         MMOItems.print(Level.WARNING, "Could not unsocket gem from item $u{0}$b: $f{1}", "Stat §eRandom Unsocket", new String[] { SilentNumbers.getItemName(paramInventoryClickEvent.getCurrentItem()), throwable.getMessage() });
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 144 */     liveMMOItem.setData(ItemStats.GEM_SOCKETS, StatHistory.from((MMOItem)liveMMOItem, ItemStats.GEM_SOCKETS).recalculate(liveMMOItem.getUpgradeLevel()));
/*     */     
/* 146 */     paramInventoryClickEvent.setCurrentItem(liveMMOItem.newBuilder().build());
/*     */ 
/*     */     
/* 149 */     for (ItemStack itemStack : player.getInventory().addItem(arrayList.<ItemStack>toArray(new ItemStack[0])).values()) player.getWorld().dropItem(player.getLocation(), itemStack); 
/* 150 */     player.playSound(player.getLocation(), Sound.BLOCK_IRON_DOOR_OPEN, 1.0F, 2.0F);
/* 151 */     return true;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RandomUnsocket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */