/*    */ package net.Indyuce.mmoitems.command.mmoitems.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class ItemCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public ItemCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/*  9 */     super(paramCommandTreeNode, "item");
/*    */     
/* 11 */     addChild(new IdentifyCommandTreeNode(this));
/* 12 */     addChild(new UnidentifyCommandTreeNode(this));
/* 13 */     addChild(new RepairCommandTreeNode(this));
/* 14 */     addChild(new DeconstructCommandTreeNode(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 19 */     return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\item\ItemCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */