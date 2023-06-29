/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ConfigFile;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*    */ import net.Indyuce.mmoitems.gui.edition.ItemEdition;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CopyCommandTreeNode extends CommandTreeNode {
/*    */   public CopyCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 17 */     super(paramCommandTreeNode, "copy");
/*    */     
/* 19 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/* 20 */     addParameter(MMOItemsCommandTreeRoot.ID_2);
/* 21 */     addParameter(new Parameter("<new-id>", (paramCommandTreeExplorer, paramList) -> {
/*    */           
/*    */           }));
/*    */   }
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 27 */     if (paramArrayOfString.length < 4) {
/* 28 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 30 */     if (!Type.isValid(paramArrayOfString[1])) {
/* 31 */       paramCommandSender.sendMessage(MMOItems.plugin
/* 32 */           .getPrefix() + ChatColor.RED + "There is no item type called " + paramArrayOfString[1].toUpperCase().replace("-", "_") + ".");
/* 33 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Type " + ChatColor.GREEN + "/mi list type " + ChatColor.GRAY + "to see all the available item types.");
/*    */       
/* 35 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 38 */     Type type = Type.get(paramArrayOfString[1]);
/* 39 */     ConfigFile configFile = type.getConfigFile();
/* 40 */     String str1 = paramArrayOfString[2].toUpperCase().replace("-", "_");
/* 41 */     if (!configFile.getConfig().contains(str1)) {
/* 42 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "There is no item called " + str1 + ".");
/* 43 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 46 */     String str2 = paramArrayOfString[3].toUpperCase();
/* 47 */     if (configFile.getConfig().contains(str2)) {
/* 48 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "There is already an item called " + str2 + "!");
/* 49 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 52 */     configFile.getConfig().set(str2, configFile.getConfig().getConfigurationSection(str1));
/* 53 */     configFile.save();
/* 54 */     MMOItems.plugin.getTemplates().requestTemplateUpdate(type, str2);
/*    */     
/* 56 */     if (paramCommandSender instanceof Player)
/* 57 */       (new ItemEdition((Player)paramCommandSender, MMOItems.plugin.getTemplates().getTemplate(type, str2))).open(); 
/* 58 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GREEN + "You successfully copied " + str1 + " to " + str2 + "!");
/* 59 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\CopyCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */