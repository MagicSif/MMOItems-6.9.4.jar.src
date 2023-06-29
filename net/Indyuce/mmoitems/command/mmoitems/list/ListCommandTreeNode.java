/*    */ package net.Indyuce.mmoitems.command.mmoitems.list;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class ListCommandTreeNode extends CommandTreeNode {
/*    */   public ListCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 11 */     super(paramCommandTreeNode, "list");
/*    */     
/* 13 */     addChild(new AbilityCommandTreeNode(this));
/* 14 */     addChild(new LuteAttackCommandTreeNode(this));
/* 15 */     addChild(new StaffSpiritCommandTreeNode(this));
/* 16 */     addChild(new TypeCommandTreeNode(this));
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 21 */     paramCommandSender.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------[" + ChatColor.LIGHT_PURPLE + " MMOItems: lists " + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "]-----------------");
/*    */     
/* 23 */     paramCommandSender.sendMessage(ChatColor.LIGHT_PURPLE + "/mi list type " + ChatColor.WHITE + "shows all item types (sword, axe...)");
/* 24 */     paramCommandSender.sendMessage(ChatColor.LIGHT_PURPLE + "/mi list spirit " + ChatColor.WHITE + "shows all available staff spirits");
/* 25 */     paramCommandSender.sendMessage(ChatColor.LIGHT_PURPLE + "/mi list lute " + ChatColor.WHITE + "shows all available lute attack effects");
/* 26 */     paramCommandSender.sendMessage(ChatColor.LIGHT_PURPLE + "/mi list ability " + ChatColor.WHITE + "shows all available abilities");
/* 27 */     if (paramCommandSender instanceof Player) {
/* 28 */       paramCommandSender.sendMessage("");
/* 29 */       paramCommandSender.sendMessage("Spigot Javadoc Links:");
/* 30 */       MythicLib.plugin.getVersion().getWrapper().sendJson((Player)paramCommandSender, "[{\"text\":\"" + ChatColor.UNDERLINE + ChatColor.GREEN + "Materials/Blocks\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.GREEN + "Click to open webpage.\"}]}}},{\"text\":\" " + ChatColor.LIGHT_PURPLE + "- \"},{\"text\":\"" + ChatColor.UNDERLINE + ChatColor.GREEN + "Potion Effects\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://hub.spigotmc.org/javadocs/spigot/org/bukkit/potion/PotionEffectType.html\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.GREEN + "Click to open webpage.\"}]}}},{\"text\":\" " + ChatColor.LIGHT_PURPLE + "- \"},{\"text\":\"" + ChatColor.UNDERLINE + ChatColor.GREEN + "Sounds\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.GREEN + "Click to open webpage.\"}]}}}]");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 39 */       MythicLib.plugin.getVersion().getWrapper().sendJson((Player)paramCommandSender, "[{\"text\":\"" + ChatColor.UNDERLINE + ChatColor.GREEN + "Entities/Mobs\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/EntityType.html\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.GREEN + "Click to open webpage.\"}]}}},{\"text\":\" " + ChatColor.LIGHT_PURPLE + "- \"},{\"text\":\"" + ChatColor.UNDERLINE + ChatColor.GREEN + "Enchantments\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://hub.spigotmc.org/javadocs/spigot/org/bukkit/enchantments/Enchantment.html\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.GREEN + "Click to open webpage.\"}]}}},{\"text\":\" " + ChatColor.LIGHT_PURPLE + "- \"},{\"text\":\"" + ChatColor.UNDERLINE + ChatColor.GREEN + "Particles\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Particles.html\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"" + ChatColor.GREEN + "Click to open webpage.\"}]}}}]");
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\list\ListCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */