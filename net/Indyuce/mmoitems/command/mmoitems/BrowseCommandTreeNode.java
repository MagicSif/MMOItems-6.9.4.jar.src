/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*    */ import net.Indyuce.mmoitems.gui.ItemBrowser;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class BrowseCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public BrowseCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 15 */     super(paramCommandTreeNode, "browse");
/*    */     
/* 17 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 22 */     if (!(paramCommandSender instanceof Player)) {
/* 23 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 24 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 27 */     if (paramArrayOfString.length < 2) {
/* 28 */       (new ItemBrowser((Player)paramCommandSender)).open();
/* 29 */       return CommandTreeNode.CommandResult.SUCCESS;
/*    */     } 
/*    */     
/* 32 */     if (!Type.isValid(paramArrayOfString[1])) {
/* 33 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Please specify a valid item type.");
/* 34 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 37 */     (new ItemBrowser((Player)paramCommandSender, Type.get(paramArrayOfString[1]))).open();
/* 38 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\BrowseCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */