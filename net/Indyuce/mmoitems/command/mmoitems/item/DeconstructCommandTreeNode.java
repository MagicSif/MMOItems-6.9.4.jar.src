/*    */ package net.Indyuce.mmoitems.command.mmoitems.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ItemTier;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class DeconstructCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public DeconstructCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 19 */     super(paramCommandTreeNode, "deconstruct");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 24 */     if (!(paramCommandSender instanceof Player)) {
/* 25 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 26 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 29 */     Player player = (Player)paramCommandSender;
/* 30 */     ItemStack itemStack = player.getInventory().getItemInMainHand();
/* 31 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack);
/* 32 */     String str = nBTItem.getString("MMOITEMS_TIER");
/* 33 */     if (str.equals("")) {
/* 34 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "The item you are holding has no tier.");
/* 35 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 38 */     ItemTier itemTier = MMOItems.plugin.getTiers().get(str);
/* 39 */     if (itemTier == null) {
/* 40 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "The item tier no longer exists.");
/* 41 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 44 */     PlayerData playerData = PlayerData.get((OfflinePlayer)player);
/* 45 */     List list = itemTier.getDeconstructedLoot(playerData);
/* 46 */     if (list.isEmpty()) {
/* 47 */       paramCommandSender.sendMessage(MMOItems.plugin
/* 48 */           .getPrefix() + "There we no items to be yielded from the deconstruction.");
/* 49 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 52 */     itemStack.setAmount(itemStack.getAmount() - 1);
/* 53 */     player.getInventory().setItemInMainHand(itemStack);
/* 54 */     for (ItemStack itemStack1 : player.getInventory().addItem((ItemStack[])list.toArray((Object[])new ItemStack[0])).values()) {
/* 55 */       player.getWorld().dropItem(player.getLocation(), itemStack1);
/*    */     }
/* 57 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Successfully deconstructed the item you are holding.");
/* 58 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\item\DeconstructCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */