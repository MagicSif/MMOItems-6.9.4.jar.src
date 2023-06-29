/*    */ package net.Indyuce.mmoitems.command.mmoitems.stations;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.crafting.CraftingStation;
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
/* 17 */     paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------[" + ChatColor.LIGHT_PURPLE + " Crafting Stations " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "]-----------------");
/*    */     
/* 19 */     for (CraftingStation craftingStation : MMOItems.plugin.getCrafting().getAll())
/* 20 */       paramCommandSender.sendMessage(ChatColor.GRAY + "- " + ChatColor.WHITE + craftingStation.getId()); 
/* 21 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\stations\ListCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */