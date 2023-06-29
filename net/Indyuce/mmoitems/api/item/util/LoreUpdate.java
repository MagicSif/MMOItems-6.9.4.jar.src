/*    */ package net.Indyuce.mmoitems.api.item.util;
/*    */ 
/*    */ import io.lumine.mythic.lib.util.AdventureUtils;
/*    */ import java.util.List;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
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
/*    */ public class LoreUpdate
/*    */ {
/*    */   private final ItemStack item;
/*    */   private final String old;
/*    */   private final String replace;
/*    */   private final List<String> lore;
/*    */   
/*    */   public LoreUpdate(ItemStack paramItemStack, String paramString1, String paramString2) {
/* 37 */     this.item = paramItemStack;
/* 38 */     this.old = paramString1;
/* 39 */     this.replace = paramString2;
/* 40 */     this.lore = paramItemStack.getItemMeta().getLore();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack updateLore() {
/* 46 */     if (this.lore == null || this.lore.isEmpty()) {
/* 47 */       return this.item;
/*    */     }
/* 49 */     for (byte b = 0; b < this.lore.size(); b++) {
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
/* 61 */       if (((String)this.lore.get(b)).equalsIgnoreCase(this.old)) {
/* 62 */         this.lore.set(b, this.replace);
/*    */         
/* 64 */         ItemMeta itemMeta = this.item.getItemMeta();
/* 65 */         AdventureUtils.setLore(itemMeta, this.lore);
/* 66 */         this.item.setItemMeta(itemMeta);
/*    */         
/* 68 */         return this.item;
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 76 */     return this.item;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\LoreUpdate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */