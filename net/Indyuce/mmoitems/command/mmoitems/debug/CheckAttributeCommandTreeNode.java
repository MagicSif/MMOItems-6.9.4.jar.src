/*    */ package net.Indyuce.mmoitems.command.mmoitems.debug;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.attribute.AttributeInstance;
/*    */ import org.bukkit.attribute.AttributeModifier;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CheckAttributeCommandTreeNode extends CommandTreeNode {
/* 17 */   private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.####");
/*    */   
/*    */   public CheckAttributeCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 20 */     super(paramCommandTreeNode, "checkattribute");
/*    */     
/* 22 */     addParameter(new Parameter("<attribute>", (paramCommandTreeExplorer, paramList) -> Arrays.<Attribute>asList(Attribute.values()).forEach(())));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 28 */     if (!(paramCommandSender instanceof Player)) {
/* 29 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 30 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 33 */     if (paramArrayOfString.length < 3) {
/* 34 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 36 */     Player player = (Player)paramCommandSender;
/*    */     try {
/* 38 */       AttributeInstance attributeInstance = player.getAttribute(Attribute.valueOf(paramArrayOfString[2].toUpperCase().replace("-", "_")));
/* 39 */       paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------------------------------------");
/* 40 */       paramCommandSender.sendMessage(ChatColor.AQUA + "Default Value = " + ChatColor.RESET + attributeInstance.getDefaultValue());
/* 41 */       paramCommandSender.sendMessage(ChatColor.AQUA + "Base Value = " + ChatColor.RESET + attributeInstance.getBaseValue());
/* 42 */       paramCommandSender.sendMessage(ChatColor.AQUA + "Value = " + ChatColor.RESET + attributeInstance.getValue());
/* 43 */       for (AttributeModifier attributeModifier : attributeInstance.getModifiers())
/* 44 */         paramCommandSender.sendMessage(attributeModifier
/* 45 */             .getName() + " " + DECIMAL_FORMAT.format(attributeModifier.getAmount()) + " " + attributeModifier.getOperation() + " " + attributeModifier.getSlot()); 
/* 46 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 47 */       player.sendMessage("Couldn't find attribute.");
/*    */     } 
/* 49 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\debug\CheckAttributeCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */