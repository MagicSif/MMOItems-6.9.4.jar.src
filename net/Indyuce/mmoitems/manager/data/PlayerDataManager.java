/*    */ package net.Indyuce.mmoitems.manager.data;
/*    */ import io.lumine.mythic.lib.api.player.MMOPlayerData;
/*    */ import io.lumine.mythic.lib.data.DefaultOfflineDataHolder;
/*    */ import io.lumine.mythic.lib.data.SynchronizedDataHolder;
/*    */ import io.lumine.mythic.lib.data.SynchronizedDataManager;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PlayerDataManager extends SynchronizedDataManager<PlayerData, DefaultOfflineDataHolder> {
/*    */   public PlayerDataManager() {
/* 13 */     super((JavaPlugin)MMOItems.plugin, (SynchronizedDataHandler)new YAMLDataHandler());
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerData newPlayerData(@NotNull MMOPlayerData paramMMOPlayerData) {
/* 18 */     return new PlayerData(paramMMOPlayerData);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object newProfileDataModule() {
/* 23 */     return new DefaultProfileDataModule((JavaPlugin)MMOItems.plugin);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\data\PlayerDataManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */