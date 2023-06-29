/*    */ package net.Indyuce.mmoitems.api.edition;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.edition.input.AnvilGUI;
/*    */ import net.Indyuce.mmoitems.api.edition.input.ChatEdition;
/*    */ import net.Indyuce.mmoitems.gui.ItemBrowser;
/*    */ import net.Indyuce.mmoitems.gui.PluginInventory;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class NewItemEdition
/*    */   implements Edition {
/*    */   public NewItemEdition(ItemBrowser paramItemBrowser) {
/* 17 */     this.inv = paramItemBrowser;
/*    */   }
/*    */   private final ItemBrowser inv;
/*    */   
/*    */   public PluginInventory getInventory() {
/* 22 */     return (PluginInventory)this.inv;
/*    */   }
/*    */ 
/*    */   
/*    */   public void enable(String... paramVarArgs) {
/* 27 */     this.inv.getPlayer().closeInventory();
/*    */     
/* 29 */     this.inv.getPlayer().sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
/* 30 */     this.inv.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Write in the chat, the id of the new item.");
/* 31 */     this.inv.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Type 'cancel' to abort editing.");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 37 */     if (MMOItems.plugin.getConfig().getBoolean("anvil-text-input") && MythicLib.plugin.getVersion().isBelowOrEqual(new int[] { 1, 13 })) {
/* 38 */       new AnvilGUI(this);
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */ 
/*    */     
/* 45 */     new ChatEdition(this);
/* 46 */     this.inv.getPlayer().sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "Item Creation", "See chat.", 10, 40, 10);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean processInput(String paramString) {
/* 51 */     if (paramString.equals("cancel")) {
/* 52 */       return true;
/*    */     }
/* 54 */     Bukkit.getScheduler().runTask((Plugin)MMOItems.plugin, () -> Bukkit.dispatchCommand((CommandSender)this.inv.getPlayer(), "mmoitems create " + this.inv.getType().getId() + " " + paramString.toUpperCase().replace(" ", "_").replace("-", "_")));
/*    */     
/* 56 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldGoBack() {
/* 61 */     return false;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\edition\NewItemEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */