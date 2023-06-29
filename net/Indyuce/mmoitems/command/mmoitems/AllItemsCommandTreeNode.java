/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ 
/*    */ public class AllItemsCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public AllItemsCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 14 */     super(paramCommandTreeNode, "allitems");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 19 */     paramCommandSender.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
/* 20 */     paramCommandSender.sendMessage(ChatColor.GREEN + "List of all MMOItems:");
/* 21 */     for (Type type : MMOItems.plugin.getTypes().getAll()) {
/* 22 */       FileConfiguration fileConfiguration = type.getConfigFile().getConfig();
/* 23 */       for (String str : fileConfiguration.getKeys(false))
/* 24 */         paramCommandSender.sendMessage("* " + ChatColor.GREEN + str + (
/* 25 */             fileConfiguration.getConfigurationSection(str).contains("name") ? (
/* 26 */             " " + ChatColor.WHITE + "(" + MythicLib.plugin.parseColors(fileConfiguration.getString(str + ".name")) + ChatColor.WHITE + ")") : 
/* 27 */             "")); 
/*    */     } 
/* 29 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\AllItemsCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */