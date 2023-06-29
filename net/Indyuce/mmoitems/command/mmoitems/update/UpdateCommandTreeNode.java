/*    */ package net.Indyuce.mmoitems.command.mmoitems.update;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class UpdateCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public UpdateCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/*  9 */     super(paramCommandTreeNode, "update");
/*    */     
/* 11 */     addChild(new ListCommandTreeNode(this));
/* 12 */     addChild(new ApplyCommandTreeNode(this));
/* 13 */     addChild(new InfoCommandTreeNode(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 18 */     return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitem\\update\UpdateCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */