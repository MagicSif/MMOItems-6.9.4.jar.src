/*    */ package net.Indyuce.mmoitems.command.mmoitems.revid;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import java.util.ArrayList;
/*    */ import java.util.function.Function;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ConfigFile;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.ItemReference;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class RevIDActionCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   private final String cmdType;
/*    */   private final Function<Integer, Integer> modifier;
/*    */   
/*    */   public RevIDActionCommandTreeNode(CommandTreeNode paramCommandTreeNode, String paramString, Function<Integer, Integer> paramFunction) {
/* 20 */     super(paramCommandTreeNode, paramString);
/*    */     
/* 22 */     this.cmdType = paramString;
/* 23 */     this.modifier = paramFunction;
/* 24 */     addParameter(RevisionIDCommandTreeNode.TYPE_OR_ALL);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 29 */     if (paramArrayOfString.length < 3) return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     
/* 31 */     if (!Type.isValid(paramArrayOfString[2]) && !paramArrayOfString[2].equalsIgnoreCase("all")) {
/* 32 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "There is no item type called " + paramArrayOfString[2].toUpperCase().replace("-", "_") + ".");
/* 33 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Type " + ChatColor.GREEN + "/mi list type" + ChatColor.RED + " to see all the available item types.");
/* 34 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 37 */     Type type = paramArrayOfString[2].equalsIgnoreCase("all") ? null : Type.get(paramArrayOfString[2]);
/* 38 */     ArrayList arrayList = new ArrayList((type == null) ? MMOItems.plugin.getTemplates().collectTemplates() : MMOItems.plugin.getTemplates().getTemplates(type));
/* 39 */     byte b = 0;
/* 40 */     for (MMOItemTemplate mMOItemTemplate : arrayList) {
/* 41 */       ConfigFile configFile = mMOItemTemplate.getType().getConfigFile();
/* 42 */       if (!configFile.getConfig().isConfigurationSection(mMOItemTemplate.getId() + ".base")) {
/* 43 */         b++; continue;
/*    */       } 
/* 45 */       configFile.getConfig().getConfigurationSection(mMOItemTemplate.getId() + ".base").set("revision-id", this.modifier.apply(Integer.valueOf(mMOItemTemplate.getRevisionId())));
/* 46 */       configFile.registerTemplateEdition((ItemReference)mMOItemTemplate);
/*    */     } 
/*    */ 
/*    */     
/* 50 */     if (b > 0) { paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Couldn't find ConfigurationSection for " + b + " of the specified items."); }
/* 51 */     else { paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.GREEN + "Successfully " + this.cmdType + "d Rev IDs" + ((type != null) ? (" for " + type.getName()) : "") + "!"); }
/* 52 */      return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\revid\RevIDActionCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */