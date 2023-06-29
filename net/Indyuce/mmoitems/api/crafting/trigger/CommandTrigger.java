/*    */ package net.Indyuce.mmoitems.api.crafting.trigger;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class CommandTrigger extends Trigger {
/*    */   private String command;
/*    */   
/*    */   public CommandTrigger(MMOLineConfig paramMMOLineConfig) {
/* 13 */     super("command");
/*    */     
/* 15 */     paramMMOLineConfig.validate(new String[] { "format" });
/* 16 */     this.sender = paramMMOLineConfig.getString("sender", "PLAYER").toUpperCase();
/* 17 */     this.command = paramMMOLineConfig.getString("format");
/*    */   }
/*    */   private final String sender;
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 22 */     if (!paramPlayerData.isOnline())
/* 23 */       return;  dispatchCommand(paramPlayerData.getPlayer(), this.sender.equals("CONSOLE"), this.sender.equals("OP"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void dispatchCommand(Player paramPlayer, boolean paramBoolean1, boolean paramBoolean2) {
/* 29 */     String str = this.command.replaceAll("(?i)%player%", paramPlayer.getName());
/*    */     
/* 31 */     if (paramBoolean1) {
/* 32 */       Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), str);
/*    */       
/*    */       return;
/*    */     } 
/* 36 */     if (paramBoolean2 && !paramPlayer.isOp()) {
/* 37 */       paramPlayer.setOp(true);
/*    */       try {
/* 39 */         Bukkit.dispatchCommand((CommandSender)paramPlayer, str);
/* 40 */       } catch (Exception exception) {}
/* 41 */       paramPlayer.setOp(false);
/*    */     } else {
/* 43 */       Bukkit.dispatchCommand((CommandSender)paramPlayer, str);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\trigger\CommandTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */