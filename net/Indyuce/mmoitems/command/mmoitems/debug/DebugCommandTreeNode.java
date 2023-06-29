/*    */ package net.Indyuce.mmoitems.command.mmoitems.debug;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class DebugCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public DebugCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/*  9 */     super(paramCommandTreeNode, "debug");
/*    */     
/* 11 */     addChild(new CheckStatCommandTreeNode(this));
/* 12 */     addChild(new CheckAttributeCommandTreeNode(this));
/* 13 */     addChild(new CheckTagCommandTreeNode(this));
/* 14 */     addChild(new SetTagCommandTreeNode(this));
/* 15 */     addChild(new CheckTagsCommandTreeNode(this));
/* 16 */     addChild(new InfoCommandTreeNode(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 21 */     return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\debug\DebugCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */