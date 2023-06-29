/*    */ package net.Indyuce.mmoitems.api.util.message;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FFPMMOItems
/*    */   extends FriendlyFeedbackPalette
/*    */ {
/*    */   @NotNull
/* 17 */   static FFPMMOItems instance = new FFPMMOItems(); @NotNull
/* 18 */   public static FFPMMOItems get() { return instance; }
/*    */   @NotNull
/* 20 */   public String getBodyFormat() { return "§x§a§5§b§5§a§7"; } @NotNull
/* 21 */   public String consoleBodyFormat() { return ChatColor.GRAY.toString(); }
/*    */   @NotNull
/* 23 */   public String getExampleFormat() { return "§x§e§0§f§5§9§3"; } @NotNull
/* 24 */   public String consoleExampleFormat() { return ChatColor.YELLOW.toString(); }
/*    */   @NotNull
/* 26 */   public String getInputFormat() { return "§x§7§d§c§7§5§8"; } @NotNull
/* 27 */   public String consoleInputFormat() { return ChatColor.GREEN.toString(); }
/*    */   @NotNull
/* 29 */   public String getResultFormat() { return "§x§5§c§e§0§0§4"; } @NotNull
/* 30 */   public String consoleResultFormat() { return ChatColor.GREEN.toString(); }
/*    */   @NotNull
/* 32 */   public String getSuccessFormat() { return "§x§2§5§f§7§c§6"; } @NotNull
/* 33 */   public String consoleSuccessFormat() { return ChatColor.AQUA.toString(); }
/*    */   @NotNull
/* 35 */   public String getFailureFormat() { return "§x§f§f§6§0§2§6"; } @NotNull
/* 36 */   public String consoleFailureFormat() { return ChatColor.RED.toString(); }
/*    */   @NotNull
/* 38 */   public String getRawPrefix() { return "§8[§eMMOItems#s§8] "; } @NotNull
/* 39 */   public String getRawPrefixConsole() { return "§8[§eMMOItems#s§8] "; }
/*    */   @NotNull
/* 41 */   public String getSubdivisionFormat() { return "§x§c§c§a§3§3§3§o"; } @NotNull
/* 42 */   public String consoleSubdivisionFormat() { return "§6§o"; }
/*    */ 
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\message\FFPMMOItems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */