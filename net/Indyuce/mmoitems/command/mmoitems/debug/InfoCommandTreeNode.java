/*    */ package net.Indyuce.mmoitems.command.mmoitems.debug;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class InfoCommandTreeNode extends CommandTreeNode {
/*    */   public InfoCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 14 */     super(paramCommandTreeNode, "info");
/*    */     
/* 16 */     addParameter(Parameter.PLAYER_OPTIONAL);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 21 */     Player player = (paramArrayOfString.length > 2) ? Bukkit.getPlayer(paramArrayOfString[2]) : ((paramCommandSender instanceof Player) ? (Player)paramCommandSender : null);
/* 22 */     if (player == null) {
/* 23 */       paramCommandSender.sendMessage(ChatColor.RED + "Couldn't find target player.");
/* 24 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 27 */     RPGPlayer rPGPlayer = PlayerData.get((OfflinePlayer)player).getRPG();
/* 28 */     paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------[" + ChatColor.LIGHT_PURPLE + " Player Information " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "]-----------------");
/*    */     
/* 30 */     paramCommandSender.sendMessage(ChatColor.WHITE + "Information about " + ChatColor.LIGHT_PURPLE + player.getName());
/* 31 */     paramCommandSender.sendMessage("");
/* 32 */     paramCommandSender.sendMessage(ChatColor.WHITE + "Player Class: " + ChatColor.LIGHT_PURPLE + rPGPlayer.getClassName());
/* 33 */     paramCommandSender.sendMessage(ChatColor.WHITE + "Player Level: " + ChatColor.LIGHT_PURPLE + rPGPlayer.getLevel());
/* 34 */     paramCommandSender.sendMessage(ChatColor.WHITE + "Player Mana: " + ChatColor.LIGHT_PURPLE + rPGPlayer.getMana());
/* 35 */     paramCommandSender.sendMessage(ChatColor.WHITE + "Player Stamina: " + ChatColor.LIGHT_PURPLE + rPGPlayer.getStamina());
/* 36 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\debug\InfoCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */