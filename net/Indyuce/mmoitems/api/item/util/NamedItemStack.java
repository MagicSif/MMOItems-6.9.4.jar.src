/*    */ package net.Indyuce.mmoitems.api.item.util;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class NamedItemStack
/*    */   extends ItemStack {
/*    */   public NamedItemStack(Material paramMaterial, String paramString) {
/* 11 */     super(paramMaterial);
/*    */     
/* 13 */     ItemMeta itemMeta = getItemMeta();
/* 14 */     itemMeta.setDisplayName(MythicLib.plugin.parseColors(paramString));
/* 15 */     setItemMeta(itemMeta);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\NamedItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */