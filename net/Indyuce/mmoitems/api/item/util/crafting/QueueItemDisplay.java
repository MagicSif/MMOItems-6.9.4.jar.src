/*    */ package net.Indyuce.mmoitems.api.item.util.crafting;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.util.AdventureUtils;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.Iterator;
/*    */ import net.Indyuce.mmoitems.api.crafting.CraftingStatus;
/*    */ import net.Indyuce.mmoitems.api.item.util.ConfigItem;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class QueueItemDisplay extends ConfigItem {
/* 16 */   private static final long[] ms = new long[] { 1000L, 60000L, 3600000L, 86400000L };
/* 17 */   private static final String[] chars = new String[] { "s", "m", "h", "d" };
/*    */   
/*    */   public QueueItemDisplay() {
/* 20 */     super("QUEUE_ITEM_DISPLAY", Material.BARRIER, "&6&lQueue&f #name#", new String[] { "{ready}&7&oThis item was successfully crafted.", "{queue}&7&oThis item is in the crafting queue.", "{queue}", "{queue}&7Time Left: &c#left#", "", "{ready}&eClick to claim!", "{queue}&eClick to cancel." });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemBuilder newBuilder(CraftingStatus.CraftingQueue.CraftingInfo paramCraftingInfo, int paramInt) {
/* 26 */     return new ItemBuilder(paramCraftingInfo, paramInt);
/*    */   }
/*    */   
/*    */   public class ItemBuilder
/*    */   {
/*    */     private final CraftingStatus.CraftingQueue.CraftingInfo crafting;
/*    */     private final int position;
/* 33 */     private final String name = QueueItemDisplay.this.getName();
/* 34 */     private final List<String> lore = new ArrayList<>(QueueItemDisplay.this.getLore());
/*    */     
/*    */     public ItemBuilder(CraftingStatus.CraftingQueue.CraftingInfo param1CraftingInfo, int param1Int) {
/* 37 */       this.crafting = param1CraftingInfo;
/* 38 */       this.position = param1Int;
/*    */     }
/*    */     
/*    */     public ItemStack build() {
/* 42 */       HashMap<Object, Object> hashMap = new HashMap<>();
/*    */       
/* 44 */       for (null = this.lore.iterator(); null.hasNext(); ) {
/* 45 */         String str = null.next();
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 50 */         if (str.startsWith("{queue}")) {
/* 51 */           if (this.crafting.isReady()) {
/* 52 */             null.remove();
/*    */             
/*    */             continue;
/*    */           } 
/* 56 */           hashMap.put(str, str.replace("{queue}", ""));
/*    */         } 
/*    */         
/* 59 */         if (str.startsWith("{ready}")) {
/* 60 */           if (!this.crafting.isReady()) {
/* 61 */             null.remove();
/*    */             
/*    */             continue;
/*    */           } 
/* 65 */           hashMap.put(str, str.replace("{ready}", ""));
/*    */         } 
/*    */       } 
/*    */       
/* 69 */       for (String str : hashMap.keySet()) {
/* 70 */         this.lore.set(this.lore.indexOf(str), ((String)hashMap.get(str)).replace("#left#", QueueItemDisplay.this.formatDelay(this.crafting.getLeft())));
/*    */       }
/* 72 */       ItemStack itemStack = this.crafting.getRecipe().getPreviewItemStack();
/* 73 */       itemStack.setAmount(this.position);
/* 74 */       ItemMeta itemMeta = itemStack.getItemMeta();
/* 75 */       AdventureUtils.setDisplayName(itemMeta, this.name.replace("#name#", itemMeta.getDisplayName()));
/* 76 */       AdventureUtils.setLore(itemMeta, this.lore);
/* 77 */       itemMeta.addItemFlags(ItemFlag.values());
/* 78 */       itemStack.setItemMeta(itemMeta);
/*    */       
/* 80 */       return NBTItem.get(itemStack)
/* 81 */         .addTag(new ItemTag[] { new ItemTag("queueId", this.crafting.getUniqueId().toString())
/* 82 */           }).toItem();
/*    */     }
/*    */   }
/*    */   
/*    */   private String formatDelay(long paramLong) {
/* 87 */     StringBuilder stringBuilder = new StringBuilder();
/*    */     
/* 89 */     byte b = 0;
/* 90 */     for (int i = ms.length - 1; i >= 0 && b < 2; i--) {
/* 91 */       if (paramLong >= ms[i]) {
/* 92 */         stringBuilder.append(paramLong / ms[i]).append(chars[i]).append(" ");
/* 93 */         paramLong %= ms[i];
/* 94 */         b++;
/*    */       } 
/*    */     } 
/* 97 */     return (stringBuilder.length() == 0) ? "1s" : stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\crafting\QueueItemDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */