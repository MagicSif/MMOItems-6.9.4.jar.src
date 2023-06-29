/*    */ package net.Indyuce.mmoitems.api.edition;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.edition.input.AnvilGUI;
/*    */ import net.Indyuce.mmoitems.api.edition.input.ChatEdition;
/*    */ import net.Indyuce.mmoitems.gui.PluginInventory;
/*    */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*    */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*    */ import org.bukkit.ChatColor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StatEdition
/*    */   implements Edition
/*    */ {
/*    */   private final EditionInventory inv;
/*    */   private final ItemStat stat;
/*    */   private final Object[] info;
/*    */   
/*    */   public StatEdition(EditionInventory paramEditionInventory, ItemStat paramItemStat, Object... paramVarArgs) {
/* 25 */     this.inv = paramEditionInventory;
/* 26 */     this.stat = paramItemStat;
/* 27 */     this.info = paramVarArgs;
/*    */   }
/*    */ 
/*    */   
/*    */   public PluginInventory getInventory() {
/* 32 */     return (PluginInventory)this.inv;
/*    */   }
/*    */   
/*    */   public ItemStat getStat() {
/* 36 */     return this.stat;
/*    */   }
/*    */   
/*    */   public Object[] getData() {
/* 40 */     return this.info;
/*    */   }
/*    */ 
/*    */   
/*    */   public void enable(String... paramVarArgs) {
/* 45 */     this.inv.getPlayer().closeInventory();
/*    */     
/* 47 */     this.inv.getPlayer().sendMessage(ChatColor.YELLOW + "" + ChatColor.STRIKETHROUGH + "-----------------------------------------------------");
/* 48 */     for (String str : paramVarArgs)
/* 49 */       this.inv.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + ChatColor.translateAlternateColorCodes('&', str)); 
/* 50 */     this.inv.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Type 'cancel' to abort editing.");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 56 */     if (MMOItems.plugin.getConfig().getBoolean("anvil-text-input") && MythicLib.plugin.getVersion().isBelowOrEqual(new int[] { 1, 13 })) {
/* 57 */       new AnvilGUI(this);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 62 */     new ChatEdition(this);
/* 63 */     this.inv.getPlayer().sendTitle(ChatColor.GOLD + "" + ChatColor.BOLD + "Item Edition", "See chat.", 10, 40, 10);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean processInput(String paramString) {
/* 70 */     if (paramString.equals("cancel")) {
/* 71 */       this.inv.open();
/* 72 */       return true;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/*    */     try {
/* 78 */       this.stat.whenInput(this.inv, paramString, this.info);
/*    */ 
/*    */       
/* 81 */       return true;
/* 82 */     } catch (RuntimeException runtimeException) {
/*    */       
/* 84 */       if (!runtimeException.getMessage().isEmpty()) this.inv.getFFP().log(FriendlyFeedbackCategory.ERROR, runtimeException.getMessage(), new String[0]);
/*    */ 
/*    */       
/* 87 */       this.inv.getFFP().sendTo(FriendlyFeedbackCategory.ERROR, this.inv.getPlayer());
/* 88 */       this.inv.getFFP().sendTo(FriendlyFeedbackCategory.FAILURE, this.inv.getPlayer());
/* 89 */       this.inv.getFFP().clearFeedback();
/*    */ 
/*    */       
/* 92 */       return false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean shouldGoBack() {
/* 98 */     return true;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\edition\StatEdition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */