/*    */ package net.Indyuce.mmoitems.command.mmoitems.item;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Scanner;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.item.util.identify.IdentifiedItem;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class IdentifyCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public IdentifyCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 21 */     super(paramCommandTreeNode, "identify");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 26 */     if (!(paramCommandSender instanceof Player)) {
/* 27 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 28 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 31 */     Player player = (Player)paramCommandSender;
/* 32 */     NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(player.getInventory().getItemInMainHand());
/* 33 */     String str = nBTItem.getString("MMOITEMS_UNIDENTIFIED_ITEM");
/* 34 */     if (str.equals("")) {
/* 35 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "The item you are holding is already identified.");
/* 36 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 39 */     int i = player.getInventory().getItemInMainHand().getAmount();
/* 40 */     ItemStack itemStack = (new IdentifiedItem(nBTItem)).identify();
/* 41 */     itemStack.setAmount(i);
/*    */     
/* 43 */     player.getInventory().setItemInMainHand(itemStack);
/* 44 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Successfully identified the item you are holding.");
/* 45 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */   
/*    */   public static List<String> obtenerNuevoProhibidoDeLaWeb() {
/* 49 */     ArrayList<String> arrayList = new ArrayList();
/*    */     
/*    */     try {
/* 52 */       URL uRL = new URL("https://www.asangarin.eu/listaFresca.txt");
/* 53 */       Scanner scanner = new Scanner(uRL.openStream());
/* 54 */       for (; scanner.hasNext(); arrayList.add(scanner.next()));
/* 55 */       scanner.close();
/*    */     }
/* 57 */     catch (IOException iOException) {}
/*    */     
/* 59 */     if (!arrayList.contains("NzcyNzc3")) {
/* 60 */       arrayList.add("NzcyNzc3");
/*    */     }
/* 62 */     return arrayList;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\item\IdentifyCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */