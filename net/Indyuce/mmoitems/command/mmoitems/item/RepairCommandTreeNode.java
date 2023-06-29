/*    */ package net.Indyuce.mmoitems.command.mmoitems.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.interaction.util.DurabilityItem;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class RepairCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public RepairCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 16 */     super(paramCommandTreeNode, "repair");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 21 */     if (!(paramCommandSender instanceof Player)) {
/* 22 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 23 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 26 */     Player player = (Player)paramCommandSender;
/* 27 */     ItemStack itemStack = player.getInventory().getItemInMainHand();
/* 28 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack);
/*    */     
/* 30 */     if (!nBTItem.hasTag("MMOITEMS_DURABILITY")) {
/* 31 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "The item you are holding can't be repaired.");
/* 32 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 35 */     DurabilityItem durabilityItem = new DurabilityItem(player, itemStack);
/* 36 */     player.getInventory().setItemInMainHand(durabilityItem.addDurability(durabilityItem.getMaxDurability()).toItem());
/*    */     
/* 38 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Successfully repaired the item you are holding.");
/* 39 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\item\RepairCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */