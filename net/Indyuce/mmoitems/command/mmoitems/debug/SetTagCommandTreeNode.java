/*    */ package net.Indyuce.mmoitems.command.mmoitems.debug;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class SetTagCommandTreeNode extends CommandTreeNode {
/*    */   public SetTagCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 13 */     super(paramCommandTreeNode, "settag");
/*    */     
/* 15 */     addParameter(new Parameter("<path>", (paramCommandTreeExplorer, paramList) -> paramList.add("TagPath")));
/* 16 */     addParameter(new Parameter("<value>", (paramCommandTreeExplorer, paramList) -> paramList.add("TagValue")));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 21 */     if (paramArrayOfString.length < 4) {
/* 22 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 24 */     if (!(paramCommandSender instanceof Player)) {
/* 25 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 26 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/*    */     try {
/* 30 */       Player player = (Player)paramCommandSender;
/* 31 */       player.getInventory().setItemInMainHand(MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand())
/* 32 */           .addTag(new ItemTag[] { new ItemTag(paramArrayOfString[2].toUpperCase().replace("-", "_"), paramArrayOfString[3].replace("%%", " ")) }).toItem());
/* 33 */       player.sendMessage("Successfully set tag.");
/* 34 */       return CommandTreeNode.CommandResult.SUCCESS;
/*    */     }
/* 36 */     catch (Exception exception) {
/* 37 */       paramCommandSender.sendMessage("Couldn't set tag.");
/* 38 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\debug\SetTagCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */