/*    */ package net.Indyuce.mmoitems.command.mmoitems.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class UnidentifyCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public UnidentifyCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 15 */     super(paramCommandTreeNode, "unidentify");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 20 */     if (!(paramCommandSender instanceof Player)) {
/* 21 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 22 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 25 */     Player player = (Player)paramCommandSender;
/* 26 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand());
/* 27 */     if (nBTItem.getType() == null) {
/* 28 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Couldn't unidentify the item you are holding.");
/* 29 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 32 */     if (nBTItem.getBoolean("MMOITEMS_UNIDENTIFIED")) {
/* 33 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "The item you are holding is already unidentified.");
/* 34 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 37 */     player.getInventory().setItemInMainHand(Type.get(nBTItem.getType()).getUnidentifiedTemplate().newBuilder(nBTItem).build());
/* 38 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Successfully unidentified the item you are holding.");
/* 39 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\item\UnidentifyCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */