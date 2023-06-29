/*    */ package net.Indyuce.mmoitems.command.mmoitems.update;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.PluginUpdate;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class ListCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public ListCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 12 */     super(paramCommandTreeNode, "list");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 17 */     paramCommandSender.sendMessage(ChatColor.LIGHT_PURPLE + "Available Config Updates");
/* 18 */     for (PluginUpdate pluginUpdate : MMOItems.plugin.getUpdates().getAll())
/* 19 */       paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "- Update " + pluginUpdate.getId()); 
/* 20 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitem\\update\ListCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */