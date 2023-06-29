/*    */ package net.Indyuce.mmoitems.comp;
/*    */ 
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.black_ixx.bossshop.core.BSBuy;
/*    */ import org.black_ixx.bossshop.core.rewards.BSRewardType;
/*    */ import org.black_ixx.bossshop.managers.ClassManager;
/*    */ import org.black_ixx.bossshop.managers.misc.InputReader;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.inventory.ClickType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MMOItemsRewardTypes
/*    */   extends BSRewardType
/*    */ {
/*    */   public Object createObject(Object paramObject, boolean paramBoolean) {
/* 25 */     return InputReader.readStringList(paramObject);
/*    */   }
/*    */   
/*    */   public boolean validityCheck(String paramString, Object paramObject) {
/* 29 */     if (paramObject != null || !(paramObject instanceof java.util.List)) {
/* 30 */       return true;
/*    */     }
/* 32 */     ClassManager.manager.getBugFinder().severe("Couldn't load the MMOItems reward type" + paramString + ". The reward object needs to be a list of types & IDs (format: [ITEM_TYPE].[ITEM_ID]).");
/*    */     
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBuy(Player paramPlayer, BSBuy paramBSBuy, boolean paramBoolean, Object paramObject, ClickType paramClickType) {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void giveReward(Player paramPlayer, BSBuy paramBSBuy, Object paramObject, ClickType paramClickType) {
/* 49 */     for (String str : paramObject) {
/*    */       try {
/* 51 */         String[] arrayOfString = str.split("\\.");
/* 52 */         Type type = MMOItems.plugin.getTypes().get(arrayOfString[0].toUpperCase().replace("-", "_"));
/* 53 */         for (ItemStack itemStack : paramPlayer.getInventory().addItem(new ItemStack[] { MMOItems.plugin.getItem(type, arrayOfString[1], PlayerData.get((OfflinePlayer)paramPlayer))
/* 54 */             }).values())
/* 55 */           paramPlayer.getWorld().dropItem(paramPlayer.getLocation(), itemStack); 
/* 56 */       } catch (Exception exception) {
/* 57 */         ClassManager.manager.getBugFinder().severe("Couldn't load the MMOItems reward type" + str + ". Format: [ITEM_TYPE].[ITEM_ID]).");
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getDisplayReward(Player paramPlayer, BSBuy paramBSBuy, Object paramObject, ClickType paramClickType) {
/* 69 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public String[] createNames() {
/* 74 */     return new String[] { "mmoitem", "mmoitems" };
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mightNeedShopUpdate() {
/* 79 */     return false;
/*    */   }
/*    */   
/*    */   public void enableType() {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\MMOItemsRewardTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */