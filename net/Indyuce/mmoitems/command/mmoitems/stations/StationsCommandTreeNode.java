/*    */ package net.Indyuce.mmoitems.command.mmoitems.stations;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class StationsCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public StationsCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/*  9 */     super(paramCommandTreeNode, "stations");
/*    */     
/* 11 */     addChild(new OpenCommandTreeNode(this));
/* 12 */     addChild(new ListCommandTreeNode(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 17 */     return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\stations\StationsCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */