/*    */ package net.Indyuce.mmoitems.listener.reforging;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.api.event.MMOItemReforgeEvent;
/*    */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RFGKeepLore
/*    */   implements Listener
/*    */ {
/*    */   @EventHandler
/*    */   public void onReforge(MMOItemReforgeEvent paramMMOItemReforgeEvent) {
/* 27 */     if (!paramMMOItemReforgeEvent.getOptions().shouldKeepLore()) {
/*    */       return;
/*    */     }
/*    */     
/* 31 */     StringListData stringListData1 = (StringListData)paramMMOItemReforgeEvent.getOldMMOItem().getData(ItemStats.LORE);
/* 32 */     if (stringListData1 == null) {
/*    */       return;
/*    */     }
/* 35 */     ArrayList<String> arrayList = extractLore(stringListData1.getList(), paramMMOItemReforgeEvent.getOptions().getKeepCase());
/*    */ 
/*    */     
/* 38 */     if (arrayList.size() == 0) {
/*    */       return;
/*    */     }
/* 41 */     StatHistory statHistory = StatHistory.from(paramMMOItemReforgeEvent.getNewMMOItem(), ItemStats.LORE);
/*    */ 
/*    */     
/* 44 */     StringListData stringListData2 = (StringListData)statHistory.getOriginalData();
/* 45 */     for (String str : arrayList) stringListData2.getList().add(str);
/*    */ 
/*    */     
/* 48 */     statHistory.setOriginalData((StatData)stringListData2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   ArrayList<String> extractLore(@NotNull List<String> paramList, @NotNull String paramString) {
/* 63 */     ArrayList<String> arrayList = new ArrayList();
/*    */ 
/*    */     
/* 66 */     for (String str : paramList) {
/*    */ 
/*    */ 
/*    */       
/* 70 */       if (str.startsWith(paramString))
/*    */       {
/* 72 */         arrayList.add(str);
/*    */       }
/*    */     } 
/*    */ 
/*    */     
/* 77 */     return arrayList;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\reforging\RFGKeepLore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */