/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ import io.lumine.mythic.lib.api.util.SmartGive;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.droptable.item.MMOItemDropItem;
/*    */ import net.Indyuce.mmoitems.api.util.RandomAmount;
/*    */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class GiveAllCommandTreeNode extends CommandTreeNode {
/*    */   public GiveAllCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 22 */     super(paramCommandTreeNode, "giveall");
/*    */     
/* 24 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/* 25 */     addParameter(MMOItemsCommandTreeRoot.ID_2);
/* 26 */     addParameter(new Parameter("<min-max>", (paramCommandTreeExplorer, paramList) -> paramList.add("1-3")));
/* 27 */     addParameter(new Parameter("<unidentify-chance>", (paramCommandTreeExplorer, paramList) -> paramList.add("<unidentify-chance>")));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/*    */     try {
/* 33 */       Validate.isTrue((paramArrayOfString.length > 4), "Usage: /mi giveall <type> <item-id> <min-max> <unidentified-chance>");
/*    */ 
/*    */       
/* 36 */       Type type = MMOItems.plugin.getTypes().getOrThrow(paramArrayOfString[1]);
/* 37 */       ItemStack itemStack = (new MMOItemDropItem(type, paramArrayOfString[2], 1.0D, Double.parseDouble(paramArrayOfString[4]) / 100.0D, new RandomAmount(paramArrayOfString[3]))).getItem(null);
/* 38 */       Validate.isTrue((itemStack != null && itemStack.getType() != Material.AIR), "Couldn't find/generate the item called '" + paramArrayOfString[1].toUpperCase() + "'. Check your console for potential item generation issues.");
/*    */ 
/*    */       
/* 41 */       for (Player player : Bukkit.getOnlinePlayers()) {
/* 42 */         (new SmartGive(player)).give(new ItemStack[] { itemStack });
/* 43 */       }  return CommandTreeNode.CommandResult.SUCCESS;
/*    */     }
/* 45 */     catch (IllegalArgumentException illegalArgumentException) {
/* 46 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + illegalArgumentException.getMessage());
/* 47 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\GiveAllCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */