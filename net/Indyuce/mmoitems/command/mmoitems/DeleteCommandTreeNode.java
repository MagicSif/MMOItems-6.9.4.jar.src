/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class DeleteCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public DeleteCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 13 */     super(paramCommandTreeNode, "delete");
/*    */     
/* 15 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/* 16 */     addParameter(MMOItemsCommandTreeRoot.ID_2);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 21 */     if (paramArrayOfString.length < 3) {
/* 22 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 24 */     if (!Type.isValid(paramArrayOfString[1])) {
/* 25 */       paramCommandSender.sendMessage(MMOItems.plugin
/* 26 */           .getPrefix() + ChatColor.RED + "There is no item type called " + paramArrayOfString[1].toUpperCase().replace("-", "_") + ".");
/* 27 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Type " + ChatColor.GREEN + "/mi list type" + ChatColor.RED + " to see all the available item types.");
/*    */       
/* 29 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 32 */     Type type = Type.get(paramArrayOfString[1]);
/* 33 */     String str = paramArrayOfString[2].toUpperCase().replace("-", "_");
/* 34 */     if (!MMOItems.plugin.getTemplates().hasTemplate(type, str)) {
/* 35 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "There is no item called " + str + ".");
/* 36 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 39 */     MMOItems.plugin.getTemplates().deleteTemplate(type, str);
/* 40 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GREEN + "You successfully deleted " + str + ".");
/* 41 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\DeleteCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */