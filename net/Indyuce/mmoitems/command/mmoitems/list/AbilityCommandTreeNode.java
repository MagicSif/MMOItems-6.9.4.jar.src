/*    */ package net.Indyuce.mmoitems.command.mmoitems.list;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class AbilityCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public AbilityCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 12 */     super(paramCommandTreeNode, "ability");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 17 */     paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------[" + ChatColor.LIGHT_PURPLE + " Abilities " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "]-----------------");
/*    */     
/* 19 */     paramCommandSender.sendMessage(ChatColor.WHITE + "Here are all the abilities you can bind to items.");
/* 20 */     paramCommandSender.sendMessage(ChatColor.WHITE + "The values inside brackets are " + ChatColor.UNDERLINE + "modifiers" + ChatColor.WHITE + " which allow you to change the ability values (cooldown, damage...)");
/*    */     
/* 22 */     for (RegisteredSkill registeredSkill : MMOItems.plugin.getSkills().getAll()) {
/* 23 */       String str = ChatColor.GRAY + String.join(ChatColor.WHITE + ", " + ChatColor.GRAY, registeredSkill.getHandler().getModifiers());
/* 24 */       str = ChatColor.WHITE + "(" + str + ChatColor.WHITE + ")";
/* 25 */       paramCommandSender.sendMessage("* " + ChatColor.LIGHT_PURPLE + registeredSkill.getName() + " " + str);
/*    */     } 
/* 27 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\list\AbilityCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */