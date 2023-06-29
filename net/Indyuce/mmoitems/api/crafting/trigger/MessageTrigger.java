/*    */ package net.Indyuce.mmoitems.api.crafting.trigger;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ 
/*    */ public class MessageTrigger
/*    */   extends Trigger {
/*    */   public MessageTrigger(MMOLineConfig paramMMOLineConfig) {
/* 11 */     super("message");
/*    */     
/* 13 */     paramMMOLineConfig.validate(new String[] { "format" });
/* 14 */     this.message = paramMMOLineConfig.getString("format");
/*    */   }
/*    */   private final String message;
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 19 */     if (!paramPlayerData.isOnline())
/* 20 */       return;  paramPlayerData.getPlayer().sendMessage(MythicLib.plugin.getPlaceholderParser().parse((OfflinePlayer)paramPlayerData.getPlayer(), this.message));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\trigger\MessageTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */