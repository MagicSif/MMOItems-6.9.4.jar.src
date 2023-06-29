/*    */ package net.Indyuce.mmoitems.command.mmoitems.list;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.stat.LuteAttackEffectStat;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class LuteAttackCommandTreeNode extends CommandTreeNode {
/*    */   public LuteAttackCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 10 */     super(paramCommandTreeNode, "lute");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 15 */     paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------[" + ChatColor.LIGHT_PURPLE + " Lute Attack Effects " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "]-----------------");
/*    */     
/* 17 */     for (LuteAttackEffectStat.LuteAttackEffect luteAttackEffect : LuteAttackEffectStat.LuteAttackEffect.values())
/* 18 */       paramCommandSender.sendMessage("* " + ChatColor.LIGHT_PURPLE + luteAttackEffect.name()); 
/* 19 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\list\LuteAttackCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */