/*    */ package net.Indyuce.mmoitems.command.mmoitems.revid;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ 
/*    */ public class RevisionIDCommandTreeNode extends CommandTreeNode {
/*    */   static {
/*  9 */     TYPE_OR_ALL = new Parameter("<type>", (paramCommandTreeExplorer, paramList) -> {
/*    */           MMOItems.plugin.getTypes().getAll().forEach(());
/*    */           paramList.add("ALL");
/*    */         });
/*    */   } public static final Parameter TYPE_OR_ALL;
/*    */   public RevisionIDCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 15 */     super(paramCommandTreeNode, "revid");
/*    */     
/* 17 */     addChild(new RevIDActionCommandTreeNode(this, "increase", paramInteger -> Integer.valueOf(Math.min(paramInteger.intValue() + 1, 2147483647))));
/* 18 */     addChild(new RevIDActionCommandTreeNode(this, "decrease", paramInteger -> Integer.valueOf(Math.max(paramInteger.intValue() - 1, 1))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 25 */     return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\revid\RevisionIDCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */