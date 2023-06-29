/*    */ package net.Indyuce.mmoitems.command.mmoitems.debug;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CheckTagsCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public CheckTagsCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 12 */     super(paramCommandTreeNode, "checktags");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 17 */     if (!(paramCommandSender instanceof Player)) {
/* 18 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 19 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 22 */     Player player = (Player)paramCommandSender;
/* 23 */     player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
/* 24 */     for (String str : MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand()).getTags())
/* 25 */       player.sendMessage("- " + str); 
/* 26 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\debug\CheckTagsCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */