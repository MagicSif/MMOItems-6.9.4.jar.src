/*    */ package net.Indyuce.mmoitems.command.mmoitems;
/*    */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*    */ import io.lumine.mythic.lib.api.player.MMOPlayerData;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeExplorer;
/*    */ import io.lumine.mythic.lib.command.api.CommandTreeNode;
/*    */ import io.lumine.mythic.lib.command.api.Parameter;
/*    */ import io.lumine.mythic.lib.player.PlayerMetadata;
/*    */ import io.lumine.mythic.lib.skill.trigger.TriggerMetadata;
/*    */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*    */ import net.Indyuce.mmoitems.stat.data.AbilityData;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class AbilityCommandTreeNode extends CommandTreeNode {
/*    */   public AbilityCommandTreeNode(CommandTreeNode paramCommandTreeNode) {
/* 20 */     super(paramCommandTreeNode, "ability");
/*    */     
/* 22 */     addParameter(new Parameter("<ability>", (paramCommandTreeExplorer, paramList) -> MMOItems.plugin.getSkills().getAll().forEach(())));
/*    */     
/* 24 */     addParameter(Parameter.PLAYER_OPTIONAL);
/*    */     
/* 26 */     for (byte b = 0; b < 10; b++) {
/* 27 */       addParameter(new Parameter("<modifier>", (paramCommandTreeExplorer, paramList) -> {
/*    */               try {
/*    */                 RegisteredSkill registeredSkill = MMOItems.plugin.getSkills().getSkillOrThrow(paramCommandTreeExplorer.getArguments()[1].toUpperCase().replace("-", "_"));
/*    */                 paramList.addAll(registeredSkill.getHandler().getModifiers());
/* 31 */               } catch (Exception exception) {}
/*    */             }));
/*    */       
/* 34 */       addParameter(new Parameter("<value>", (paramCommandTreeExplorer, paramList) -> paramList.add("0")));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public CommandTreeNode.CommandResult execute(CommandSender paramCommandSender, String[] paramArrayOfString) {
/* 40 */     if (paramArrayOfString.length < 2) {
/* 41 */       return CommandTreeNode.CommandResult.THROW_USAGE;
/*    */     }
/* 43 */     if (paramArrayOfString.length < 3 && !(paramCommandSender instanceof Player)) {
/* 44 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Please specify a player to use this command.");
/* 45 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */ 
/*    */     
/* 49 */     Player player = (paramArrayOfString.length > 2) ? Bukkit.getPlayer(paramArrayOfString[2]) : (Player)paramCommandSender;
/* 50 */     if (player == null) {
/* 51 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Couldn't find player called " + paramArrayOfString[2] + ".");
/* 52 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */ 
/*    */     
/* 56 */     String str = paramArrayOfString[1].toUpperCase().replace("-", "_");
/* 57 */     if (!MMOItems.plugin.getSkills().hasSkill(str)) {
/* 58 */       paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Couldn't find ability " + str + ".");
/* 59 */       return CommandTreeNode.CommandResult.FAILURE;
/*    */     } 
/*    */ 
/*    */     
/* 63 */     AbilityData abilityData = new AbilityData(MMOItems.plugin.getSkills().getSkill(str), TriggerType.CAST);
/* 64 */     for (byte b = 3; b < paramArrayOfString.length - 1; b += 2) {
/* 65 */       String str1 = paramArrayOfString[b];
/* 66 */       String str2 = paramArrayOfString[b + 1];
/*    */       
/*    */       try {
/* 69 */         abilityData.setModifier(str1, Double.parseDouble(str2));
/* 70 */       } catch (Exception exception) {
/* 71 */         paramCommandSender.sendMessage(MMOItems.plugin.getPrefix() + ChatColor.RED + "Wrong format: {" + str1 + " " + str2 + "}");
/* 72 */         return CommandTreeNode.CommandResult.FAILURE;
/*    */       } 
/*    */     } 
/*    */     
/* 76 */     PlayerMetadata playerMetadata = MMOPlayerData.get((OfflinePlayer)player).getStatMap().cache(EquipmentSlot.MAIN_HAND);
/* 77 */     abilityData.cast(new TriggerMetadata(playerMetadata, null, null));
/* 78 */     return CommandTreeNode.CommandResult.SUCCESS;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\command\mmoitems\AbilityCommandTreeNode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */