/*    */ package net.Indyuce.mmoitems.command.mmoitems.list;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class TypeCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public TypeCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 12 */     super(paramCommandTreeNode, "type");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 17 */     paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------[" + ChatColor.LIGHT_PURPLE + " Item Types " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + ChatColor.LIGHT_PURPLE + " Item Types " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "]-----------------");
/*    */ 
/*    */     
/* 20 */     for (Type type : MMOItems.plugin.getTypes().getAll())
/* 21 */       paramCommandSender.sendMessage("* " + ChatColor.LIGHT_PURPLE + type.getName() + " (" + type.getId() + ")"); 
/* 22 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\list\TypeCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */