/*    */ package net.Indyuce.mmoitems.command.mmoitems.debug;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CheckTagCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public CheckTagCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 13 */     super(paramCommandTreeNode, "checktag");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 18 */     if (paramArrayOfString.length < 3) {
/* 19 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 21 */     if (!(paramCommandSender instanceof Player)) {
/* 22 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 23 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 26 */     Player player = (Player)paramCommandSender;
/* 27 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand());
/* 28 */     String str = nBTItem.hasTag(paramArrayOfString[2]) ? paramArrayOfString[2] : ("MMOITEMS_" + paramArrayOfString[2].toUpperCase().replace("-", "_"));
/* 29 */     player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
/* 30 */     player.sendMessage(ChatColor.AQUA + "Boolean = " + ChatColor.RESET + nBTItem.getBoolean(str));
/* 31 */     player.sendMessage(ChatColor.AQUA + "Double = " + ChatColor.RESET + nBTItem.getDouble(str));
/* 32 */     player.sendMessage(ChatColor.AQUA + "String = " + ChatColor.RESET + nBTItem.getString(str));
/* 33 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\debug\CheckTagCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */