/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*    */ import net.Indyuce.mmoitems.gui.edition.ItemEdition;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class EditCommandTreeNode extends CommandTreeNode {
/*    */   public EditCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 14 */     super(paramCommandTreeNode, "edit");
/*    */     
/* 16 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/* 17 */     addParameter(MMOItemsCommandTreeRoot.ID_2);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 22 */     if (paramArrayOfString.length < 3) {
/* 23 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 25 */     if (!(paramCommandSender instanceof Player)) {
/* 26 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 27 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 30 */     if (!Type.isValid(paramArrayOfString[1])) {
/* 31 */       paramCommandSender.sendMessage(MMOItems.plugin
/* 32 */           .getPrefix() + ChatColor.RED + "There is no item type called " + paramArrayOfString[1].toUpperCase().replace("-", "_") + ".");
/* 33 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Type " + ChatColor.GREEN + "/mi list type" + ChatColor.RED + " to see all the available item types.");
/*    */       
/* 35 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 38 */     Type type = Type.get(paramArrayOfString[1]);
/* 39 */     String str = paramArrayOfString[2].toUpperCase().replace("-", "_");
/* 40 */     if (!MMOItems.plugin.getTemplates().hasTemplate(type, str)) {
/* 41 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Could not find a template called '" + str + "'.");
/* 42 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 45 */     (new ItemEdition((Player)paramCommandSender, MMOItems.plugin.getTemplates().getTemplate(type, str))).open();
/* 46 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\EditCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */