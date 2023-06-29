/*     */ package net.Indyuce.mmoitems.command;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PluginHelp
/*     */ {
/*     */   private static final int commandsPerPage = 8;
/*     */   private final CommandSender sender;
/*     */   
/*     */   public PluginHelp(CommandSender paramCommandSender) {
/*  16 */     this.sender = paramCommandSender;
/*     */   }
/*     */   
/*     */   public void open(int paramInt) {
/*  20 */     if (paramInt < 1 || (paramInt - 1) * 8 >= (PluginCommand.values()).length)
/*     */       return; 
/*     */     int i;
/*  23 */     for (i = 0; i < 10; i++)
/*  24 */       this.sender.sendMessage(""); 
/*  25 */     this.sender.sendMessage("" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.DARK_GRAY + "[" + ChatColor.LIGHT_PURPLE + " MMOItems Help " + ChatColor.DARK_GRAY + "]" + ChatColor.STRIKETHROUGH + "-------------------");
/*     */ 
/*     */     
/*  28 */     if (this.sender instanceof Player) {
/*  29 */       i = (paramInt - 1) * 8;
/*  30 */       int j = paramInt * 8;
/*  31 */       byte b = 0;
/*     */       
/*  33 */       for (; i + b < j && i + b < (PluginCommand.values()).length && i + b > -1; b++) {
/*  34 */         PluginCommand.values()[i + b].sendAsJson((Player)this.sender);
/*     */       }
/*  36 */       while (b++ < 8) {
/*  37 */         this.sender.sendMessage("");
/*     */       }
/*  39 */       MythicLib.plugin.getVersion().getWrapper().sendJson((Player)this.sender, "[{\"text\":\"" + ChatColor.DARK_GRAY + ChatColor.STRIKETHROUGH + "------------------" + ChatColor.DARK_GRAY + "[\"},{\"text\":\"" + ChatColor.RED + "««\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/mi help " + (paramInt - 1) + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Previous Page\"}}},{\"text\":\"" + ChatColor.DARK_GRAY + "]" + ChatColor.STRIKETHROUGH + "---" + ChatColor.DARK_GRAY + "(" + ChatColor.GREEN + paramInt + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  44 */           getMaxPage() + ChatColor.DARK_GRAY + ")" + ChatColor.STRIKETHROUGH + "---" + ChatColor.DARK_GRAY + "[\"},{\"text\":\"" + ChatColor.GREEN + "»»\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/mi help " + (paramInt + 1) + "\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"Next Page\"}}},{\"text\":\"" + ChatColor.DARK_GRAY + "]" + ChatColor.STRIKETHROUGH + "-----------------\"}]");
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  50 */       for (PluginCommand pluginCommand : PluginCommand.values())
/*  51 */         pluginCommand.sendAsMessage(this.sender); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int getMaxPage() {
/*  56 */     return (int)Math.max(0.0D, Math.ceil((PluginCommand.values()).length / 8.0D));
/*     */   }
/*     */   
/*     */   public enum PluginCommand {
/*  60 */     OPTIONAL("()" + ChatColor.WHITE + " = Optional"),
/*  61 */     REQUIRED("<>" + ChatColor.WHITE + " = Required"),
/*  62 */     MULTIPLE_ARGS("..." + ChatColor.WHITE + " = Multiple Args"),
/*  63 */     HOVER_COMMAND(ChatColor.WHITE + "Hover a command to see its description."),
/*  64 */     TAB_INFO(ChatColor.WHITE + "Press [tab] while typing a command to auto-complete."),
/*  65 */     SPACE(""),
/*     */     
/*  67 */     HELP("mi help <page>", "Shows the help page."),
/*  68 */     GIVE("mi give <type> <item> (player) (min-max) (unident-chance) (drop-chance)", "&a/mi <type> <item> (player) (min-max) (unidentification-chance) (drop-chance)\\n&fGives an item to a player.\\nSupports drop chance, unidentification chance & random amounts."),
/*     */     
/*  70 */     GIVEALL("mi giveall <type> <item> <min-max> <unident-chance>", "Gives an item to all online players on the server."),
/*  71 */     BROWSE("mi browse (type)", "Allows you to browse through all the items you created."),
/*  72 */     GENERATE("mi generate <player> (extra-args)", "Generates a random item.\\nUse /mi generate to see all available parameters."),
/*  73 */     ABILITY("mi ability <ability> (player) (mod1) (val1) (mod2) (val2)...", "Forces a player to cast an ability."),
/*  74 */     DROP("mi drop <type> <item-id> <world-name> <x> <y> <z>...", "&a/mi drop <type> <item-id> <world-name> <x> <y> <z> <drop-chance> <[min]-[max]> <unidentified-chance>\\n&fDrops an item at the target location."),
/*     */     
/*  76 */     HEAL("mi heal", "Heals you & remove negative potion effects."),
/*  77 */     IDENTIFY("mi item identifify", "Identifies the item you are holding."),
/*  78 */     UNIDENTIFY("mi item unidentifify", "Unidentifies the item you are holding."),
/*  79 */     REPAIR("mi item repair", "Repairs the item you are holding."),
/*  80 */     DECONSTRUCT("mi item deconstruct", "Deconstructs the item you are holding."),
/*  81 */     INFO("mi debug info (player)", "Displays information about the specified player."),
/*  82 */     LIST("mi list", "Some useful things when editing items."),
/*  83 */     RELOAD("mi reload (adv-recipes/stations)", "Reloads a specific/every plugin system."),
/*  84 */     CREATE("mi create <type> <id>", "Creates a new item."),
/*  85 */     COPY("mi copy <type> <target-id> <new-item-id>", "Duplicates an existing item."),
/*  86 */     DELETE("mi delete <type> <id>", "Removes an item from the plugin."),
/*  87 */     EDIT("mi edit <type> <id>", "Brings up the item edition menu."),
/*  88 */     ITEMLIST("mi itemlist <type>", "Lists all items from a specific item type."),
/*  89 */     ALLITEMS("mi allitems", "Lists items from every type."),
/*  90 */     STATIONS_LIST("mi stations list", "Lists available crafting stations."),
/*  91 */     STATIONS_OPEN("mi stations open <station> (player)", "Opens a crafting station to a player"),
/*     */     
/*  93 */     UPDATEITEM("updateitem", "Updates the item you are holding."),
/*  94 */     UPDATEITEM_ITEM("updateitem <type> <id>", "Enables the item updater for a specific item.");
/*     */ 
/*     */     
/*     */     private final String usage;
/*     */ 
/*     */     
/*     */     private final String help;
/*     */ 
/*     */ 
/*     */     
/*     */     PluginCommand(String param1String1, String param1String2) {
/* 105 */       this.usage = param1String1;
/* 106 */       this.help = (param1String2 == null) ? null : ChatColor.translateAlternateColorCodes('&', param1String2);
/*     */     }
/*     */     
/*     */     private boolean isCommand() {
/* 110 */       return (this.help != null);
/*     */     }
/*     */     
/*     */     private void sendAsJson(Player param1Player) {
/* 114 */       if (isCommand()) {
/* 115 */         MythicLib.plugin.getVersion().getWrapper().sendJson(param1Player, "{\"text\":\"" + ChatColor.LIGHT_PURPLE + "►" + ChatColor.GRAY + " /" + this.usage + "\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"" + this.help + "\"}}}");
/*     */       } else {
/*     */         
/* 118 */         param1Player.sendMessage(ChatColor.LIGHT_PURPLE + this.usage);
/*     */       } 
/*     */     }
/*     */     private void sendAsMessage(CommandSender param1CommandSender) {
/* 122 */       if (isCommand()) {
/* 123 */         param1CommandSender.sendMessage(ChatColor.LIGHT_PURPLE + "- /" + this.usage + ChatColor.WHITE + " | " + this.help);
/*     */       } else {
/* 125 */         param1CommandSender.sendMessage(ChatColor.LIGHT_PURPLE + this.usage);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\PluginHelp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */