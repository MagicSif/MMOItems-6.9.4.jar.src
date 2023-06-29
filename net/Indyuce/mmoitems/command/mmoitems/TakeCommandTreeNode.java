/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.Random;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class TakeCommandTreeNode
/*    */   extends CommandTreeNode {
/* 20 */   private static final Random random = new Random();
/*    */   
/*    */   public TakeCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 23 */     super(paramCommandTreeNode, "take");
/*    */     
/* 25 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/* 26 */     addParameter(MMOItemsCommandTreeRoot.ID_2);
/* 27 */     addParameter(Parameter.PLAYER);
/* 28 */     addParameter(Parameter.AMOUNT);
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 33 */     if (paramArrayOfString.length != 5) {
/* 34 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/*    */ 
/*    */     
/*    */     try {
/* 39 */       Type type = MMOItems.plugin.getTypes().getOrThrow(paramArrayOfString[1].toUpperCase().replace("-", "_"));
/* 40 */       String str = paramArrayOfString[2].toUpperCase().replace("-", "_");
/* 41 */       Player player = Bukkit.getPlayer(paramArrayOfString[3]);
/* 42 */       Validate.notNull(player, "Could not find player called '" + paramArrayOfString[3] + "'.");
/*    */       
/* 44 */       int i = Integer.parseInt(paramArrayOfString[4]);
/* 45 */       for (byte b = 0; b < player.getInventory().getSize() && i > 0; b++) {
/* 46 */         ItemStack itemStack = player.getInventory().getItem(b);
/* 47 */         if (itemStack != null && itemStack.getType() != Material.AIR) {
/*    */ 
/*    */           
/* 50 */           NBTItem nBTItem = NBTItem.get(itemStack);
/* 51 */           String str1 = nBTItem.getType();
/* 52 */           if (type.getId().equals(str1) && nBTItem.getString("MMOITEMS_ITEM_ID").equals(str)) {
/* 53 */             int j = Math.min(i, itemStack.getAmount());
/* 54 */             i -= j;
/* 55 */             itemStack.setAmount(itemStack.getAmount() - j);
/* 56 */             player.getInventory().setItem(b, itemStack);
/*    */           } 
/*    */         } 
/*    */       } 
/* 60 */       return CommandTreeNode.CommandResult.SUCCESS;
/*    */     }
/* 62 */     catch (IllegalArgumentException illegalArgumentException) {
/* 63 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + illegalArgumentException.getMessage());
/* 64 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\TakeCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */