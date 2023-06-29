/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ItemListCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public ItemListCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 16 */     super(paramCommandTreeNode, "itemlist");
/*    */     
/* 18 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 23 */     if (paramArrayOfString.length < 2) {
/* 24 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 26 */     if (!Type.isValid(paramArrayOfString[1])) {
/* 27 */       paramCommandSender.sendMessage(MMOItems.plugin
/* 28 */           .getPrefix() + ChatColor.RED + "There is no item type called " + paramArrayOfString[1].toUpperCase().replace("-", "_"));
/* 29 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Type " + ChatColor.GREEN + "/mi list type " + ChatColor.GRAY + "to see all the available item types.");
/*    */       
/* 31 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 34 */     Type type = Type.get(paramArrayOfString[1]);
/* 35 */     paramCommandSender.sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
/* 36 */     paramCommandSender.sendMessage(ChatColor.GREEN + "List of all items in " + type.getId().toLowerCase() + ".yml:");
/* 37 */     FileConfiguration fileConfiguration = type.getConfigFile().getConfig();
/*    */     
/* 39 */     if (paramCommandSender instanceof Player) {
/* 40 */       for (String str1 : fileConfiguration.getKeys(false))
/*    */       {
/*    */         
/* 43 */         String str2 = fileConfiguration.getConfigurationSection(str1).contains("name") ? (" " + ChatColor.WHITE + "(" + MythicLib.plugin.parseColors(fileConfiguration.getString(str1 + ".name")) + ChatColor.WHITE + ")") : "";
/* 44 */         MythicLib.plugin.getVersion().getWrapper().sendJson((Player)paramCommandSender, "{\"text\":\"* " + ChatColor.GREEN + str1 + str2 + "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/mi edit " + type
/*    */             
/* 46 */             .getId() + " " + str1 + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Click to edit " + (
/* 47 */             str2.equals("") ? str1 : MythicLib.plugin.parseColors(fileConfiguration.getString(str1 + ".name"))) + ChatColor.WHITE + ".\",\"color\":\"white\"}}}");
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 52 */       for (String str : fileConfiguration.getKeys(false))
/* 53 */         paramCommandSender.sendMessage("* " + ChatColor.GREEN + str + (
/* 54 */             fileConfiguration.getConfigurationSection(str).contains("name") ? (
/* 55 */             " " + ChatColor.WHITE + "(" + MythicLib.plugin.parseColors(fileConfiguration.getString(str + ".name")) + ChatColor.WHITE + ")") : 
/* 56 */             "")); 
/*    */     } 
/* 58 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\ItemListCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */