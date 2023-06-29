/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.command.PluginHelp;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class HelpCommandTreeNode extends CommandTreeNode {
/*    */   public HelpCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 10 */     super(paramCommandTreeNode, "help");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 15 */     if (paramArrayOfString.length < 2) {
/* 16 */       (new PluginHelp(paramCommandSender)).open(1);
/* 17 */       return CommandTreeNode.CommandResult.SUCCESS;
/*    */     } 
/*    */     
/*    */     try {
/* 21 */       (new PluginHelp(paramCommandSender)).open(Integer.parseInt(paramArrayOfString[1]));
/* 22 */       return CommandTreeNode.CommandResult.SUCCESS;
/*    */     }
/* 24 */     catch (NumberFormatException numberFormatException) {
/* 25 */       paramCommandSender.sendMessage(ChatColor.RED + paramArrayOfString[1] + " is not a valid number.");
/* 26 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\HelpCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */