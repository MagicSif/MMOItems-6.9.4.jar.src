/*    */ package net.Indyuce.mmoitems.command.mmoitems.update;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.PluginUpdate;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class ApplyCommandTreeNode extends CommandTreeNode {
/*    */   public ApplyCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 13 */     super(paramCommandTreeNode, "apply");
/*    */     
/* 15 */     addParameter(new Parameter("<id>", (paramCommandTreeExplorer, paramList) -> MMOItems.plugin.getUpdates().getAll().forEach(())));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/*    */     int i;
/* 21 */     if (paramArrayOfString.length < 3) {
/* 22 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/*    */     
/*    */     try {
/* 26 */       i = Integer.parseInt(paramArrayOfString[2]);
/* 27 */     } catch (NumberFormatException numberFormatException) {
/* 28 */       paramCommandSender.sendMessage(ChatColor.RED + "Please specify a valid number.");
/* 29 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 32 */     if (!MMOItems.plugin.getUpdates().has(i)) {
/* 33 */       paramCommandSender.sendMessage(ChatColor.RED + "Could not find any config update with ID " + i);
/* 34 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 37 */     PluginUpdate pluginUpdate = MMOItems.plugin.getUpdates().get(i);
/* 38 */     paramCommandSender.sendMessage(ChatColor.YELLOW + "Applying config update " + i + "...");
/* 39 */     pluginUpdate.apply(paramCommandSender);
/* 40 */     paramCommandSender.sendMessage(ChatColor.YELLOW + "Config update " + i + " was successfully applied. Check the console for potential update error logs.");
/*    */     
/* 42 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitem\\update\ApplyCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */