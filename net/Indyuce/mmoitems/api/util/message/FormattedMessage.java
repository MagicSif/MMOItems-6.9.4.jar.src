/*    */ package net.Indyuce.mmoitems.api.util.message;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import net.Indyuce.mmocore.api.player.PlayerActivity;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FormattedMessage
/*    */ {
/*    */   private final boolean actionBar;
/*    */   @NotNull
/*    */   private String message;
/*    */   
/*    */   public FormattedMessage(Message paramMessage) {
/* 26 */     this.message = paramMessage.getUpdated();
/* 27 */     this.actionBar = MMOItems.plugin.getConfig().getBoolean("action-bar-display." + paramMessage.getActionBarConfigPath());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FormattedMessage(String paramString, boolean paramBoolean) {
/* 38 */     this.message = paramString;
/* 39 */     this.actionBar = paramBoolean;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public FormattedMessage format(ChatColor paramChatColor, String... paramVarArgs) {
/* 46 */     if (this.message.isEmpty()) {
/* 47 */       return this;
/*    */     }
/* 49 */     this.message = paramChatColor + this.message;
/* 50 */     for (byte b = 0; b < paramVarArgs.length; b += 2)
/* 51 */       this.message = this.message.replace(paramVarArgs[b], paramVarArgs[b + 1]); 
/* 52 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void send(Player paramPlayer) {
/* 62 */     if (this.message.isEmpty()) {
/*    */       return;
/*    */     }
/* 65 */     if (this.actionBar) {
/* 66 */       if (Bukkit.getPluginManager().isPluginEnabled("MMOCore")) {
/* 67 */         PlayerData.get((OfflinePlayer)paramPlayer).setLastActivity(PlayerActivity.ACTION_BAR_MESSAGE);
/*    */       }
/* 69 */       MythicLib.plugin.getVersion().getWrapper().sendActionBar(paramPlayer, this.message);
/*    */     } else {
/* 71 */       paramPlayer.sendMessage(this.message);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String toString() {
/* 76 */     return this.message;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\message\FormattedMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */