/*    */ package net.Indyuce.mmoitems.command.mmoitems.list;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.stat.StaffSpiritStat;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class StaffSpiritCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public StaffSpiritCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 11 */     super(paramCommandTreeNode, "spirit");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 16 */     paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------[" + ChatColor.LIGHT_PURPLE + " Staff Spirits " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "]-----------------");
/*    */     
/* 18 */     for (StaffSpiritStat.StaffSpirit staffSpirit : StaffSpiritStat.StaffSpirit.values()) {
/* 19 */       String str = staffSpirit.hasLore() ? (" " + ChatColor.WHITE + ">> " + ChatColor.GRAY + "" + ChatColor.ITALIC + staffSpirit.getDefaultLore()) : "";
/* 20 */       paramCommandSender.sendMessage("* " + ChatColor.LIGHT_PURPLE + staffSpirit.name() + str);
/*    */     } 
/* 22 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\list\StaffSpiritCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */