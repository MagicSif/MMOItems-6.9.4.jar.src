/*    */ package net.Indyuce.mmoitems.command.mmoitems.debug;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CheckStatCommandTreeNode extends CommandTreeNode {
/*    */   public CheckStatCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 14 */     super(paramCommandTreeNode, "checkstat");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 19 */     if (paramArrayOfString.length < 3) {
/* 20 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 22 */     if (!(paramCommandSender instanceof Player)) {
/* 23 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 24 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 27 */     ItemStat itemStat = MMOItems.plugin.getStats().get(paramArrayOfString[2].toUpperCase().replace("-", "_"));
/* 28 */     if (itemStat == null) {
/* 29 */       paramCommandSender.sendMessage(ChatColor.RED + "Couldn't find the stat called " + paramArrayOfString[2].toUpperCase().replace("-", "_") + ".");
/* 30 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 33 */     Player player = (Player)paramCommandSender;
/* 34 */     player.sendMessage("Found stat with ID " + itemStat.getId() + " = " + PlayerData.get((OfflinePlayer)paramCommandSender).getStats().getStat(itemStat));
/* 35 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\debug\CheckStatCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */