/*    */ package net.Indyuce.mmoitems.command.mmoitems.stations;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.crafting.CraftingStation;
/*    */ import net.Indyuce.mmoitems.gui.CraftingStationView;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class OpenCommandTreeNode extends CommandTreeNode {
/*    */   public OpenCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 15 */     super(paramCommandTreeNode, "open");
/*    */     
/* 17 */     addParameter(new Parameter("<station>", (paramCommandTreeExplorer, paramList) -> MMOItems.plugin.getCrafting().getStations().forEach(())));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 23 */     if (paramArrayOfString.length < 3) {
/* 24 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 26 */     if (!MMOItems.plugin.getCrafting().hasStation(paramArrayOfString[2])) {
/* 27 */       paramCommandSender.sendMessage(ChatColor.RED + "There is no station called " + paramArrayOfString[2] + ".");
/* 28 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 31 */     Player player = (paramArrayOfString.length > 3) ? Bukkit.getPlayer(paramArrayOfString[3]) : ((paramCommandSender instanceof Player) ? (Player)paramCommandSender : null);
/* 32 */     if (player == null) {
/* 33 */       paramCommandSender.sendMessage(ChatColor.RED + "Please specify a valid player.");
/* 34 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 37 */     (new CraftingStationView(player, MMOItems.plugin.getCrafting().getStation(paramArrayOfString[2]), 1)).open();
/* 38 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\stations\OpenCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */