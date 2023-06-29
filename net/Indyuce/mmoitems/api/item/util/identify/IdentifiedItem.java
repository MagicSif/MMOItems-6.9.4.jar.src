/*    */ package net.Indyuce.mmoitems.api.item.util.identify;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.io.BukkitObjectInputStream;
/*    */ import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IdentifiedItem
/*    */ {
/*    */   private final NBTItem item;
/*    */   
/*    */   public IdentifiedItem(NBTItem paramNBTItem) {
/* 17 */     this.item = paramNBTItem;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack identify() {
/* 25 */     return deserialize(this.item.getString("MMOITEMS_UNIDENTIFIED_ITEM"));
/*    */   }
/*    */   
/*    */   private ItemStack deserialize(String paramString) {
/*    */     try {
/* 30 */       ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(paramString));
/* 31 */       BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(byteArrayInputStream);
/* 32 */       ItemStack itemStack = (ItemStack)bukkitObjectInputStream.readObject();
/* 33 */       bukkitObjectInputStream.close();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 43 */       NBTItem nBTItem = NBTItem.get(itemStack);
/* 44 */       if (nBTItem.hasType()) {
/*    */ 
/*    */         
/* 47 */         LiveMMOItem liveMMOItem = new LiveMMOItem(itemStack);
/* 48 */         return liveMMOItem.newBuilder().build();
/*    */       } 
/* 50 */       return itemStack;
/* 51 */     } catch (ClassNotFoundException|java.io.IOException classNotFoundException) {
/* 52 */       classNotFoundException.printStackTrace();
/* 53 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\identify\IdentifiedItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */