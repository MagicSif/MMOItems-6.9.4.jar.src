/*     */ package net.Indyuce.mmoitems.command.mmoitems;
/*     */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*     */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*     */ import io.lumine.mythic.lib.command.api.Parameter;
/*     */ import java.util.List;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.droptable.item.MMOItemDropItem;
/*     */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class DropCommandTreeNode extends CommandTreeNode {
/*     */   public DropCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/*  21 */     super(paramCommandTreeNode, "drop");
/*     */     
/*  23 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/*  24 */     addParameter(MMOItemsCommandTreeRoot.ID_2);
/*  25 */     addParameter(new Parameter("<world>", (paramCommandTreeExplorer, paramList) -> Bukkit.getWorlds().forEach(())));
/*  26 */     addParameter(new Parameter("<x>", (paramCommandTreeExplorer, paramList) -> paramList.add("<x>")));
/*  27 */     addParameter(new Parameter("<y>", (paramCommandTreeExplorer, paramList) -> paramList.add("<y>")));
/*  28 */     addParameter(new Parameter("<z>", (paramCommandTreeExplorer, paramList) -> paramList.add("<z>")));
/*  29 */     addParameter(new Parameter("<drop-chance>", (paramCommandTreeExplorer, paramList) -> paramList.add("<drop-chance>")));
/*  30 */     addParameter(new Parameter("<min-max>", (paramCommandTreeExplorer, paramList) -> paramList.add("1-3")));
/*  31 */     addParameter(new Parameter("<unidentify-chance>", (paramCommandTreeExplorer, paramList) -> paramList.add("<unidentify-chance>")));
/*     */   }
/*     */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/*     */     double d1, d2, d3, d4, d5;
/*     */     int i, j;
/*  36 */     if (paramArrayOfString.length != 10) {
/*  37 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*     */     }
/*  39 */     if (!Type.isValid(paramArrayOfString[1])) {
/*  40 */       paramCommandSender.sendMessage(MMOItems.plugin
/*  41 */           .getPrefix() + ChatColor.RED + "There is no item type called " + paramArrayOfString[1].toUpperCase().replace("-", "_") + ".");
/*  42 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Type " + ChatColor.GREEN + "/mi list type " + ChatColor.RED + "to see all the available item types.");
/*     */       
/*  44 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*  47 */     Type type = Type.get(paramArrayOfString[1].toUpperCase());
/*  48 */     String str = paramArrayOfString[2].toUpperCase().replace("-", "_");
/*  49 */     FileConfiguration fileConfiguration = type.getConfigFile().getConfig();
/*  50 */     if (!fileConfiguration.contains(str)) {
/*  51 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "There is no item called " + str + ".");
/*  52 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*  55 */     World world = Bukkit.getWorld(paramArrayOfString[3]);
/*  56 */     if (world == null) {
/*  57 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Couldn't find the world called " + paramArrayOfString[3] + ".");
/*  58 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  65 */       d1 = Double.parseDouble(paramArrayOfString[4]);
/*  66 */     } catch (Exception exception) {
/*  67 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + paramArrayOfString[4] + " is not a valid number.");
/*  68 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*     */     try {
/*  72 */       d2 = Double.parseDouble(paramArrayOfString[5]);
/*  73 */     } catch (Exception exception) {
/*  74 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + paramArrayOfString[5] + " is not a valid number.");
/*  75 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*     */     try {
/*  79 */       d3 = Double.parseDouble(paramArrayOfString[6]);
/*  80 */     } catch (Exception exception) {
/*  81 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + paramArrayOfString[6] + " is not a valid number.");
/*  82 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*     */     try {
/*  86 */       d4 = Double.parseDouble(paramArrayOfString[7]);
/*  87 */     } catch (Exception exception) {
/*  88 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + paramArrayOfString[7] + " is not a valid number.");
/*  89 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*     */     try {
/*  93 */       d5 = Double.parseDouble(paramArrayOfString[9]);
/*  94 */     } catch (Exception exception) {
/*  95 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + paramArrayOfString[9] + " is not a valid number.");
/*  96 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*  99 */     String[] arrayOfString = paramArrayOfString[8].split("-");
/* 100 */     if (arrayOfString.length != 2) {
/* 101 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "The drop quantity format is incorrect.");
/* 102 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Format: [min]-[max]");
/* 103 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*     */     try {
/* 107 */       i = Integer.parseInt(arrayOfString[0]);
/* 108 */     } catch (Exception exception) {
/* 109 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + arrayOfString[0] + " is not a valid number.");
/* 110 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/*     */     try {
/* 114 */       j = Integer.parseInt(arrayOfString[1]);
/* 115 */     } catch (Exception exception) {
/* 116 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + arrayOfString[1] + " is not a valid number.");
/* 117 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/* 120 */     ItemStack itemStack = (new MMOItemDropItem(type, str, d4 / 100.0D, d5 / 100.0D, i, j)).getItem(null);
/* 121 */     if (itemStack == null || itemStack.getType() == Material.AIR) {
/* 122 */       paramCommandSender.sendMessage(MMOItems.plugin
/* 123 */           .getPrefix() + ChatColor.RED + "An error occurred while attempting to generate the item called " + str + ".");
/* 124 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "See console for more information!");
/* 125 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */     
/* 128 */     world.dropItem(new Location(world, d1, d2, d3), itemStack);
/* 129 */     return CommandTreeNode.CommandResult.SUCCESS;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\DropCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */