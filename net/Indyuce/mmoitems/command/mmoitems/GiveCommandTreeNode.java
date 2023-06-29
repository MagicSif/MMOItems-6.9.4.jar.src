/*     */ package net.Indyuce.mmoitems.command.mmoitems;
/*     */ import io.lumine.mythic.lib.api.util.SmartGive;
/*     */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*     */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*     */ import io.lumine.mythic.lib.command.api.Parameter;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.util.RandomAmount;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.command.MMOItemsCommandTreeRoot;
/*     */ import net.Indyuce.mmoitems.stat.data.SoulboundData;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class GiveCommandTreeNode extends CommandTreeNode {
/*  29 */   private static final Random random = new Random();
/*     */   
/*     */   public GiveCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/*  32 */     super(paramCommandTreeNode, "give");
/*     */     
/*  34 */     addParameter(MMOItemsCommandTreeRoot.TYPE);
/*  35 */     addParameter(MMOItemsCommandTreeRoot.ID_2);
/*  36 */     addParameter(Parameter.PLAYER_OPTIONAL);
/*  37 */     addParameter(new Parameter("(min-max)", (paramCommandTreeExplorer, paramList) -> paramList.addAll(Arrays.asList(new String[] { "1-3", "1", "10", "32", "64" }))));
/*  38 */     addParameter(new Parameter("(unidentify-chance)", (paramCommandTreeExplorer, paramList) -> paramList.add("(unidentify-chance)")));
/*  39 */     addParameter(new Parameter("(drop-chance)", (paramCommandTreeExplorer, paramList) -> paramList.add("(drop-chance)")));
/*  40 */     addParameter(new Parameter("(soulbound-chance)", (paramCommandTreeExplorer, paramList) -> paramList.add("(soulbound-chance)")));
/*  41 */     addParameter(new Parameter("(silent)", (paramCommandTreeExplorer, paramList) -> paramList.addAll(Arrays.asList(new String[] { "silent", "s" }))));
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/*  46 */     if (paramArrayOfString.length < 3) {
/*  47 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*     */     }
/*     */     try {
/*  50 */       Validate.isTrue((paramArrayOfString.length > 3 || paramCommandSender instanceof Player), "Please specify a player.");
/*     */ 
/*     */       
/*  53 */       Player player = (paramArrayOfString.length > 3) ? Bukkit.getPlayer(paramArrayOfString[3]) : (Player)paramCommandSender;
/*  54 */       Validate.notNull(player, "Could not find player called '" + paramArrayOfString[(paramArrayOfString.length > 3) ? 3 : 2] + "'.");
/*     */ 
/*     */       
/*  57 */       Type type = MMOItems.plugin.getTypes().getOrThrow(paramArrayOfString[1].toUpperCase().replace("-", "_"));
/*  58 */       MMOItemTemplate mMOItemTemplate = MMOItems.plugin.getTemplates().getTemplateOrThrow(type, paramArrayOfString[2].toUpperCase().replace("-", "_"));
/*  59 */       RandomAmount randomAmount = (paramArrayOfString.length > 4) ? new RandomAmount(paramArrayOfString[4]) : new RandomAmount(1, 1);
/*  60 */       double d1 = (paramArrayOfString.length > 5) ? (Double.parseDouble(paramArrayOfString[5]) / 100.0D) : 0.0D;
/*  61 */       double d2 = (paramArrayOfString.length > 6) ? (Double.parseDouble(paramArrayOfString[6]) / 100.0D) : 1.0D;
/*  62 */       double d3 = (paramArrayOfString.length > 7) ? (Double.parseDouble(paramArrayOfString[7]) / 100.0D) : 0.0D;
/*  63 */       boolean bool = (paramArrayOfString.length > 8 && (paramArrayOfString[8].equalsIgnoreCase("silent") || paramArrayOfString[8].equalsIgnoreCase("s"))) ? true : false;
/*     */ 
/*     */       
/*  66 */       if (random.nextDouble() > d2) {
/*  67 */         return CommandTreeNode.CommandResult.SUCCESS;
/*     */       }
/*     */       
/*  70 */       MMOItem mMOItem = mMOItemTemplate.newBuilder(PlayerData.get((OfflinePlayer)player).getRPG()).build();
/*     */ 
/*     */       
/*  73 */       if (random.nextDouble() < d3) {
/*  74 */         mMOItem.setData(ItemStats.SOULBOUND, (StatData)new SoulboundData(player, 1));
/*     */       }
/*     */ 
/*     */       
/*  78 */       ItemStack itemStack = (random.nextDouble() < d1) ? type.getUnidentifiedTemplate().newBuilder(mMOItem.newBuilder().buildNBT()).build() : mMOItem.newBuilder().build();
/*     */ 
/*     */       
/*  81 */       Validate.isTrue((itemStack != null && itemStack.getType() != Material.AIR), "Couldn't find/generate the item called '" + mMOItemTemplate
/*  82 */           .getId() + "'. Check your console for potential item generation issues.");
/*  83 */       itemStack.setAmount(randomAmount.getRandomAmount());
/*     */ 
/*     */       
/*  86 */       if (!bool) {
/*  87 */         Message.RECEIVED_ITEM.format(ChatColor.YELLOW, new String[] { "#item#", MMOUtils.getDisplayName(itemStack), "#amount#", 
/*  88 */               (itemStack.getAmount() > 1) ? (" x" + itemStack.getAmount()) : "" }).send(player);
/*  89 */         if (!paramCommandSender.equals(player)) {
/*  90 */           paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.YELLOW + "Successfully gave " + ChatColor.GOLD + 
/*  91 */               MMOUtils.getDisplayName(itemStack) + ((itemStack.getAmount() > 1) ? (" x" + itemStack.getAmount()) : "") + ChatColor.YELLOW + " to " + ChatColor.GOLD + player
/*  92 */               .getName() + ChatColor.YELLOW + ".");
/*     */         }
/*     */       } 
/*     */       
/*  96 */       (new SmartGive(player)).give(new ItemStack[] { itemStack });
/*  97 */       return CommandTreeNode.CommandResult.SUCCESS;
/*     */     }
/*  99 */     catch (IllegalArgumentException illegalArgumentException) {
/* 100 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + illegalArgumentException.getMessage());
/* 101 */       return CommandTreeNode.CommandResult.FAILURE;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\GiveCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */