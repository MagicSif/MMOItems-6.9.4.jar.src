/*     */ package net.Indyuce.mmoitems.listener.reforging;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ReforgeOptions;
/*     */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.util.MMOItemReforger;
/*     */ import net.Indyuce.mmoitems.stat.data.GemSocketsData;
/*     */ import net.Indyuce.mmoitems.stat.data.GemstoneData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
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
/*     */ public class RFGKeepGems
/*     */   implements Listener
/*     */ {
/*     */   @EventHandler
/*     */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/*  35 */     if (!paramMMOItemReforgeEvent.getOptions().shouldKeepGemStones()) {
/*     */       return;
/*     */     }
/*     */     
/*  39 */     GemSocketsData gemSocketsData1 = (GemSocketsData)paramMMOItemReforgeEvent.getOldMMOItem().getData(ItemStats.GEM_SOCKETS);
/*     */ 
/*     */     
/*  42 */     if (gemSocketsData1 == null) {
/*     */       return;
/*     */     }
/*  45 */     GemSocketsData gemSocketsData2 = (GemSocketsData)paramMMOItemReforgeEvent.getNewMMOItem().getData(ItemStats.GEM_SOCKETS);
/*     */ 
/*     */     
/*  48 */     ArrayList<GemstoneData> arrayList = new ArrayList();
/*     */ 
/*     */     
/*  51 */     if (gemSocketsData2 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  57 */       ArrayList<GemstoneData> arrayList1 = new ArrayList();
/*  58 */       ArrayList arrayList2 = new ArrayList(gemSocketsData2.getEmptySlots());
/*  59 */       ArrayList arrayList3 = new ArrayList(gemSocketsData1.getGemstones());
/*     */ 
/*     */       
/*  62 */       for (GemstoneData gemstoneData : arrayList3) {
/*     */ 
/*     */ 
/*     */         
/*  66 */         if (arrayList2.size() == 0) {
/*     */ 
/*     */ 
/*     */           
/*  70 */           arrayList.add(gemstoneData);
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/*  77 */         String str1 = gemstoneData.getSocketColor();
/*  78 */         if (str1 == null) str1 = GemSocketsData.getUncoloredGemSlot(); 
/*  79 */         String str2 = null;
/*     */ 
/*     */         
/*  82 */         for (String str : arrayList2) { if (str.equals(GemSocketsData.getUncoloredGemSlot()) || str1.equals(str)) str2 = str;
/*     */            }
/*     */         
/*  85 */         if (str2 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  90 */           MMOItem mMOItem = paramMMOItemReforgeEvent.getOldMMOItem().extractGemstone(gemstoneData);
/*  91 */           if (mMOItem == null) {
/*     */ 
/*     */ 
/*     */             
/*  95 */             arrayList.add(gemstoneData);
/*     */             
/*     */             continue;
/*     */           } 
/*  99 */           MMOItemReforger mMOItemReforger = new MMOItemReforger(mMOItem.newBuilder().build());
/* 100 */           if (!mMOItemReforger.canReforge()) {
/*     */ 
/*     */ 
/*     */             
/* 104 */             arrayList.add(gemstoneData);
/*     */             continue;
/*     */           } 
/* 107 */           mMOItemReforger.setPlayer(paramMMOItemReforgeEvent.getPlayer());
/* 108 */           if (!mMOItemReforger.reforge((MMOItems.plugin.getLanguage()).gemRevisionOptions)) {
/*     */ 
/*     */ 
/*     */             
/* 112 */             arrayList.add(gemstoneData);
/*     */ 
/*     */             
/*     */             continue;
/*     */           } 
/*     */           
/* 118 */           paramMMOItemReforgeEvent.getReforger().getReforgingOutput().addAll(mMOItemReforger.getReforgingOutput());
/*     */ 
/*     */           
/* 121 */           LiveMMOItem liveMMOItem = new LiveMMOItem(mMOItemReforger.getResult());
/* 122 */           GemstoneData gemstoneData1 = new GemstoneData(liveMMOItem, str2, gemstoneData.getHistoricUUID());
/* 123 */           gemstoneData1.setLevel(gemstoneData.getLevel());
/*     */ 
/*     */           
/* 126 */           arrayList2.remove(str2);
/*     */ 
/*     */           
/* 129 */           arrayList1.add(gemstoneData1);
/*     */ 
/*     */           
/* 132 */           for (ItemStat itemStat : liveMMOItem.getStats()) {
/*     */ 
/*     */             
/* 135 */             if (!(itemStat instanceof net.Indyuce.mmoitems.stat.type.GemStoneStat)) {
/*     */ 
/*     */               
/* 138 */               StatData statData = liveMMOItem.getData(itemStat);
/*     */ 
/*     */               
/* 141 */               if (statData instanceof Mergeable) {
/*     */ 
/*     */                 
/* 144 */                 if (statData instanceof net.Indyuce.mmoitems.stat.data.EnchantListData && ((Mergeable)statData).isClear()) {
/*     */                   continue;
/*     */                 }
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 151 */                 StatHistory statHistory1 = StatHistory.from(paramMMOItemReforgeEvent.getNewMMOItem(), itemStat);
/* 152 */                 statHistory1.registerGemstoneData(gemstoneData1.getHistoricUUID(), statData);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 165 */         arrayList.add(gemstoneData);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 170 */       GemSocketsData gemSocketsData = new GemSocketsData(arrayList2);
/* 171 */       for (GemstoneData gemstoneData : arrayList1) { if (gemstoneData == null) continue;  gemSocketsData.add(gemstoneData); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 177 */       StatHistory statHistory = StatHistory.from(paramMMOItemReforgeEvent.getNewMMOItem(), ItemStats.GEM_SOCKETS);
/* 178 */       statHistory.setOriginalData((StatData)gemSocketsData);
/* 179 */       paramMMOItemReforgeEvent.getNewMMOItem().setData(ItemStats.GEM_SOCKETS, statHistory.recalculate(paramMMOItemReforgeEvent.getNewMMOItem().getUpgradeLevel()));
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 188 */       arrayList.addAll(gemSocketsData1.getGemstones());
/*     */     } 
/*     */     
/* 191 */     if (ReforgeOptions.dropRestoredGems)
/* 192 */       for (GemstoneData gemstoneData : arrayList) {
/*     */ 
/*     */         
/* 195 */         MMOItem mMOItem = paramMMOItemReforgeEvent.getOldMMOItem().extractGemstone(gemstoneData);
/* 196 */         if (mMOItem == null) {
/*     */ 
/*     */           
/* 199 */           MMOItems.print(null, "$bGemstone $r{0} {1} $bno longer exists, it was$f deleted$b from $u{2}$b's {3}$b. ", "RevID", new String[] { gemstoneData.getMMOItemType(), gemstoneData.getMMOItemID(), (paramMMOItemReforgeEvent.getPlayer() == null) ? "null" : paramMMOItemReforgeEvent.getPlayer().getName(), SilentNumbers.getItemName(paramMMOItemReforgeEvent.getReforger().getStack(), false) });
/*     */           
/*     */           continue;
/*     */         } 
/* 203 */         MMOItemReforger mMOItemReforger = new MMOItemReforger(mMOItem.newBuilder().build());
/* 204 */         if (mMOItemReforger.canReforge()) {
/*     */ 
/*     */           
/* 207 */           mMOItemReforger.setPlayer(paramMMOItemReforgeEvent.getPlayer());
/* 208 */           mMOItemReforger.reforge((MMOItems.plugin.getLanguage()).gemRevisionOptions);
/*     */ 
/*     */           
/* 211 */           paramMMOItemReforgeEvent.getReforger().getReforgingOutput().addAll(mMOItemReforger.getReforgingOutput());
/*     */ 
/*     */           
/* 214 */           paramMMOItemReforgeEvent.getReforger().addReforgingOutput(mMOItemReforger.getResult());
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 220 */         paramMMOItemReforgeEvent.getReforger().addReforgingOutput(mMOItem.newBuilder().build());
/*     */       }  
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepGems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */