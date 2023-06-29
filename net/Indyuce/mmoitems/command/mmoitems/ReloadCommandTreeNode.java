/*     */ package net.Indyuce.mmoitems.command.mmoitems;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*     */ import java.util.function.Consumer;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.util.MMOItemReforger;
/*     */ import net.Indyuce.mmoitems.api.util.NumericStatFormula;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ public class ReloadCommandTreeNode
/*     */   extends CommandTreeNode {
/*     */   public ReloadCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/*  15 */     super(paramCommandTreeNode, "reload");
/*     */     
/*  17 */     addChild(new SubReloadCommandTreeNode("recipes", this, this::reloadRecipes));
/*  18 */     addChild(new SubReloadCommandTreeNode("stations", this, this::reloadStations));
/*  19 */     addChild(new SubReloadCommandTreeNode("skills", this, this::reloadSkills));
/*  20 */     addChild(new SubReloadCommandTreeNode("all", this, paramCommandSender -> {
/*     */             reloadMain(paramCommandSender);
/*     */             reloadRecipes(paramCommandSender);
/*     */             reloadStations(paramCommandSender);
/*     */             reloadSkills(paramCommandSender);
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/*  30 */     reloadMain(paramCommandSender);
/*  31 */     return CommandTreeNode.CommandResult.SUCCESS;
/*     */   }
/*     */   
/*     */   public class SubReloadCommandTreeNode extends CommandTreeNode {
/*     */     private final Consumer<CommandSender> action;
/*     */     
/*     */     public SubReloadCommandTreeNode(String param1String, CommandTreeNode param1CommandTreeNode, Consumer<CommandSender> param1Consumer) {
/*  38 */       super(param1CommandTreeNode, param1String);
/*  39 */       this.action = param1Consumer;
/*     */     }
/*     */ 
/*     */     
/*     */     public CommandTreeNode.CommandResult execute(CommandSender param1CommandSender, String[] param1ArrayOfString) {
/*  44 */       this.action.accept(param1CommandSender);
/*  45 */       return CommandTreeNode.CommandResult.SUCCESS;
/*     */     }
/*     */   }
/*     */   
/*     */   public void reloadSkills(CommandSender paramCommandSender) {
/*  50 */     MythicLib.plugin.getSkills().initialize(true);
/*  51 */     MMOItems.plugin.getSkills().initialize(true);
/*  52 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reloaded " + MMOItems.plugin.getSkills().getAll().size() + " skills.");
/*     */   }
/*     */   
/*     */   public void reloadMain(CommandSender paramCommandSender) {
/*  56 */     MMOItems.plugin.getLanguage().reload();
/*  57 */     MMOItems.plugin.getDropTables().reload();
/*  58 */     MMOItems.plugin.getTypes().reload();
/*  59 */     MMOItems.plugin.getTiers().reload();
/*  60 */     MMOItems.plugin.getSets().reload();
/*  61 */     MMOItems.plugin.getUpgrades().reload();
/*  62 */     MMOItems.plugin.getWorldGen().reload();
/*  63 */     MMOItems.plugin.getCustomBlocks().reload();
/*  64 */     MMOItems.plugin.getLayouts().reload();
/*  65 */     MMOItems.plugin.getFormats().reload();
/*  66 */     MMOItems.plugin.getTemplates().reload();
/*  67 */     MMOItems.plugin.getStats().reload(true);
/*  68 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + MMOItems.plugin.getName() + " " + MMOItems.plugin
/*  69 */         .getDescription().getVersion() + " reloaded.");
/*  70 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "- " + ChatColor.RED + MMOItems.plugin
/*  71 */         .getTypes().getAll().size() + ChatColor.GRAY + " Item Types");
/*  72 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "- " + ChatColor.RED + MMOItems.plugin
/*  73 */         .getTiers().getAll().size() + ChatColor.GRAY + " Item Tiers");
/*  74 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "- " + ChatColor.RED + MMOItems.plugin
/*  75 */         .getSets().getAll().size() + ChatColor.GRAY + " Item Sets");
/*  76 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "- " + ChatColor.RED + MMOItems.plugin
/*  77 */         .getUpgrades().getAll().size() + ChatColor.GRAY + " Upgrade Templates");
/*     */ 
/*     */     
/*  80 */     NumericStatFormula.reload();
/*  81 */     MMOItemReforger.reload();
/*     */   }
/*     */   
/*     */   public void reloadRecipes(CommandSender paramCommandSender) {
/*  85 */     MMOItems.plugin.getRecipes().reload();
/*  86 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reloaded recipes.");
/*  87 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "- " + ChatColor.RED + (MMOItems.plugin
/*  88 */         .getRecipes().getBukkitRecipes().size() + MMOItems.plugin
/*  89 */         .getRecipes().getBooklessRecipes().size() + MMOItems.plugin
/*  90 */         .getRecipes().getCustomRecipes().size()) + ChatColor.GRAY + " Recipes");
/*     */   }
/*     */ 
/*     */   
/*     */   public void reloadStations(CommandSender paramCommandSender) {
/*  95 */     MMOItems.plugin.getLayouts().reload();
/*  96 */     MMOItems.plugin.getCrafting().reload();
/*  97 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "Successfully reloaded the crafting stations..");
/*  98 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "- " + ChatColor.RED + MMOItems.plugin
/*  99 */         .getCrafting().getAll().size() + ChatColor.GRAY + " Crafting Stations");
/* 100 */     paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + "- " + ChatColor.RED + MMOItems.plugin
/* 101 */         .getCrafting().countRecipes() + ChatColor.GRAY + " Recipes");
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\ReloadCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */