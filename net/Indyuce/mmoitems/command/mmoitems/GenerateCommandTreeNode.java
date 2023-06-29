/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ import io.lumine.mythic.lib.api.util.SmartGive;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import java.util.Random;
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ItemTier;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.ClassFilter;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.IDFilter;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.TemplateExplorer;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.TierFilter;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.TypeFilter;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class GenerateCommandTreeNode extends CommandTreeNode {
/* 28 */   private static final Random random = new Random();
/*    */   
/*    */   public GenerateCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 31 */     super(paramCommandTreeNode, "generate");
/*    */     
/* 33 */     addParameter(Parameter.PLAYER);
/* 34 */     addParameter(new Parameter("(extra-args)", (paramCommandTreeExplorer, paramList) -> paramList.addAll(Arrays.asList(new String[] { "-matchlevel", "-matchclass", "-level:", "-class:", "-type:", "-id:", "-tier:", "-tierset:", "-gimme" }))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/*    */     try {
/* 41 */       if (paramArrayOfString.length < 2) return CommandTreeNode.CommandResult.THROW_USAGE; 
/* 42 */       Player player1 = Bukkit.getPlayer(paramArrayOfString[1]);
/* 43 */       Validate.notNull(player1, "Could not find player called " + paramArrayOfString[1] + ".");
/*    */       
/* 45 */       GenerateCommandHandler generateCommandHandler = new GenerateCommandHandler(paramArrayOfString);
/*    */ 
/*    */       
/* 48 */       Player player2 = (generateCommandHandler.hasArgument("gimme") || generateCommandHandler.hasArgument("giveme")) ? ((paramCommandSender instanceof Player) ? (Player)paramCommandSender : null) : player1;
/* 49 */       Validate.notNull(player2, "You cannot use -gimme");
/*    */       
/* 51 */       RPGPlayer rPGPlayer = PlayerData.get((OfflinePlayer)player1).getRPG();
/*    */       
/* 53 */       int i = generateCommandHandler.hasArgument("level:") ? Integer.parseInt(generateCommandHandler.getValue("level")) : (generateCommandHandler.hasArgument("matchlevel") ? MMOItems.plugin.getTemplates().rollLevel(rPGPlayer.getLevel()) : (1 + random.nextInt(100)));
/*    */ 
/*    */       
/* 56 */       ItemTier itemTier = generateCommandHandler.hasArgument("tierset") ? null : (generateCommandHandler.hasArgument("tier:") ? MMOItems.plugin.getTiers().getOrThrow(generateCommandHandler.getValue("tier").toUpperCase().replace("-", "_")) : MMOItems.plugin.getTemplates().rollTier());
/*    */       
/* 58 */       TemplateExplorer templateExplorer = new TemplateExplorer();
/* 59 */       if (generateCommandHandler.hasArgument("matchclass"))
/* 60 */         templateExplorer.applyFilter((Predicate)new ClassFilter(rPGPlayer)); 
/* 61 */       if (generateCommandHandler.hasArgument("class:"))
/* 62 */         templateExplorer.applyFilter((Predicate)new ClassFilter(generateCommandHandler.getValue("class").replace("-", " ").replace("_", " "))); 
/* 63 */       String str = null;
/* 64 */       if (generateCommandHandler.hasArgument("tierset:")) {
/* 65 */         String str1 = UtilityMethods.enumName(generateCommandHandler.getValue("tierset"));
/* 66 */         Validate.isTrue(MMOItems.plugin.getTiers().has(str1), "Could not find tier with ID '" + str1 + "'");
/* 67 */         templateExplorer.applyFilter((Predicate)new TierFilter(str1));
/*    */       } 
/* 69 */       if (generateCommandHandler.hasArgument("type:")) {
/* 70 */         str = generateCommandHandler.getValue("type");
/* 71 */         Validate.isTrue(Type.isValid(str), "Could not find type with ID '" + str + "'");
/* 72 */         templateExplorer.applyFilter((Predicate)new TypeFilter(Type.get(str)));
/*    */       } 
/* 74 */       if (generateCommandHandler.hasArgument("id:")) {
/* 75 */         Validate.isTrue((str != null), "You have to specify a type if using the id option!");
/* 76 */         templateExplorer.applyFilter((Predicate)new IDFilter(generateCommandHandler.getValue("id")));
/*    */       } 
/*    */       
/* 79 */       Optional<MMOItemTemplate> optional = templateExplorer.rollLoot();
/* 80 */       Validate.isTrue(optional.isPresent(), "No item matched your criterias.");
/*    */       
/* 82 */       ItemStack itemStack = ((MMOItemTemplate)optional.get()).newBuilder(i, itemTier).build().newBuilder().build();
/* 83 */       Validate.isTrue((itemStack != null && itemStack.getType() != Material.AIR), "Could not generate item with ID '" + ((MMOItemTemplate)optional.get()).getId() + "'");
/* 84 */       (new SmartGive(player2)).give(new ItemStack[] { itemStack });
/* 85 */       return CommandTreeNode.CommandResult.SUCCESS;
/*    */     }
/* 87 */     catch (IllegalArgumentException illegalArgumentException) {
/* 88 */       paramCommandSender.sendMessage(ChatColor.RED + illegalArgumentException.getMessage());
/* 89 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\GenerateCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */