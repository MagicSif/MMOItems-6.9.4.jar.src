/*    */ package net.Indyuce.mmoitems.command.mmoitems.debug;
/*    */ 
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.potion.PotionEffectType;
/*    */ 
/*    */ public class HealCommandTreeNode
/*    */   extends CommandTreeNode {
/*    */   public HealCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 13 */     super(paramCommandTreeNode, "heal");
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 18 */     if (!(paramCommandSender instanceof Player)) {
/* 19 */       paramCommandSender.sendMessage(ChatColor.RED + "This command is only for players.");
/* 20 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */     
/* 23 */     Player player = (Player)paramCommandSender;
/* 24 */     player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
/* 25 */     player.setFoodLevel(20);
/* 26 */     player.setFireTicks(0);
/* 27 */     player.setSaturation(12.0F);
/* 28 */     for (PotionEffectType potionEffectType : new PotionEffectType[] { PotionEffectType.POISON, PotionEffectType.BLINDNESS, PotionEffectType.CONFUSION, PotionEffectType.HUNGER, PotionEffectType.WEAKNESS, PotionEffectType.SLOW, PotionEffectType.SLOW_DIGGING })
/*    */     {
/* 30 */       player.removePotionEffect(potionEffectType); } 
/* 31 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\debug\HealCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */