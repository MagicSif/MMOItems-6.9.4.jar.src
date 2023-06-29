/*    */ package net.Indyuce.mmoitems.manager.data;
/*    */ import io.lumine.mythic.lib.data.DefaultOfflineDataHolder;
/*    */ import io.lumine.mythic.lib.data.OfflineDataHolder;
/*    */ import io.lumine.mythic.lib.data.SynchronizedDataHolder;
/*    */ import io.lumine.mythic.lib.data.yaml.YAMLSynchronizedDataHandler;
/*    */ import java.util.ArrayList;
/*    */ import java.util.UUID;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.milkbowl.vault.permission.Permission;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class YAMLDataHandler extends YAMLSynchronizedDataHandler<PlayerData, DefaultOfflineDataHolder> {
/*    */   public YAMLDataHandler() {
/* 16 */     super((Plugin)MMOItems.plugin);
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveInSection(PlayerData paramPlayerData, ConfigurationSection paramConfigurationSection) {
/* 21 */     paramConfigurationSection.createSection("crafting-queue");
/* 22 */     paramConfigurationSection.set("permissions-from-items", new ArrayList(paramPlayerData.getPermissions()));
/* 23 */     paramPlayerData.getCrafting().save(paramConfigurationSection.getConfigurationSection("crafting-queue"));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadFromSection(PlayerData paramPlayerData, ConfigurationSection paramConfigurationSection) {
/* 29 */     if (paramConfigurationSection.contains("crafting-queue")) {
/* 30 */       paramPlayerData.getCrafting().load(paramPlayerData, paramConfigurationSection.getConfigurationSection("crafting-queue"));
/*    */     }
/* 32 */     if (MMOItems.plugin.hasPermissions() && paramConfigurationSection.contains("permissions-from-items")) {
/* 33 */       Permission permission = MMOItems.plugin.getVault().getPermissions();
/* 34 */       paramConfigurationSection.getStringList("permissions-from-items").forEach(paramString -> {
/*    */             if (paramPermission.has(paramPlayerData.getPlayer(), paramString)) {
/*    */               paramPermission.playerRemove(paramPlayerData.getPlayer(), paramString);
/*    */             }
/*    */           });
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void setup() {}
/*    */ 
/*    */   
/*    */   public DefaultOfflineDataHolder getOffline(@NotNull UUID paramUUID) {
/* 47 */     return new DefaultOfflineDataHolder(paramUUID);
/*    */   }
/*    */   
/*    */   public void close() {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\manager\data\YAMLDataHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */