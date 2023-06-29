/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ConfigFile;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.gui.edition.ItemEdition;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CreateCommandTreeNode extends CommandTreeNode {
/*    */   public CreateCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 16 */     super(paramCommandTreeNode, "create");
/*    */     
/* 18 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/* 19 */     addParameter(new Parameter("<id>", (paramCommandTreeExplorer, paramList) -> paramList.add("ITEM_ID")));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 24 */     if (paramArrayOfString.length < 3) {
/* 25 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 27 */     if (!Type.isValid(paramArrayOfString[1])) {
/* 28 */       paramCommandSender.sendMessage(MMOItems.plugin
/* 29 */           .getPrefix() + ChatColor.RED + "There is no item type called " + paramArrayOfString[1].toUpperCase().replace("-", "_") + ".");
/* 30 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Type " + ChatColor.GREEN + "/mi list type" + ChatColor.RED + " to see all the available item types.");
/*    */       
/* 32 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 35 */     Type type = Type.get(paramArrayOfString[1]);
/* 36 */     String str = paramArrayOfString[2].toUpperCase().replace("-", "_");
/* 37 */     ConfigFile configFile = type.getConfigFile();
/* 38 */     if (configFile.getConfig().contains(str)) {
/* 39 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "There is already an item called " + str + ".");
/* 40 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 43 */     configFile.getConfig().set(str + ".base.material", type.getItem().getType().name());
/* 44 */     configFile.save();
/* 45 */     MMOItems.plugin.getTemplates().requestTemplateUpdate(type, str);
/*    */     
/* 47 */     if (paramCommandSender instanceof Player)
/* 48 */       (new ItemEdition((Player)paramCommandSender, MMOItems.plugin.getTemplates().getTemplate(type, str))).open(); 
/* 49 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GREEN + "You successfully created " + str + "!");
/* 50 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\CreateCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */