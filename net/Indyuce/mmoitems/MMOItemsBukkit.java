/*    */ package net.Indyuce.mmoitems;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.comp.PhatLootsHook;
/*    */ import net.Indyuce.mmoitems.gui.listener.GuiListener;
/*    */ import net.Indyuce.mmoitems.listener.BiomeChangeListener;
/*    */ import net.Indyuce.mmoitems.listener.CustomBlockListener;
/*    */ import net.Indyuce.mmoitems.listener.DisableInteractions;
/*    */ import net.Indyuce.mmoitems.listener.DurabilityListener;
/*    */ import net.Indyuce.mmoitems.listener.ItemListener;
/*    */ import net.Indyuce.mmoitems.listener.PlayerListener;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class MMOItemsBukkit {
/*    */   public MMOItemsBukkit(MMOItems paramMMOItems) {
/* 18 */     Bukkit.getPluginManager().registerEvents((Listener)new ItemUse(), (Plugin)paramMMOItems);
/* 19 */     Bukkit.getPluginManager().registerEvents((Listener)new ItemListener(), (Plugin)paramMMOItems);
/* 20 */     Bukkit.getPluginManager().registerEvents((Listener)new PlayerListener(), (Plugin)paramMMOItems);
/* 21 */     Bukkit.getPluginManager().registerEvents((Listener)new CustomSoundListener(), (Plugin)paramMMOItems);
/* 22 */     Bukkit.getPluginManager().registerEvents((Listener)new DurabilityListener(), (Plugin)paramMMOItems);
/* 23 */     Bukkit.getPluginManager().registerEvents((Listener)new DisableInteractions(), (Plugin)paramMMOItems);
/* 24 */     Bukkit.getPluginManager().registerEvents((Listener)new GuiListener(), (Plugin)paramMMOItems);
/* 25 */     Bukkit.getPluginManager().registerEvents((Listener)new BiomeChangeListener(), (Plugin)paramMMOItems);
/* 26 */     Bukkit.getPluginManager().registerEvents((Listener)new CustomBlockListener(), (Plugin)paramMMOItems);
/* 27 */     if (Bukkit.getPluginManager().getPlugin("PhatLoots") != null) {
/* 28 */       Bukkit.getPluginManager().registerEvents((Listener)new PhatLootsHook(), (Plugin)paramMMOItems);
/*    */     }
/* 30 */     if (paramMMOItems.getConfig().getBoolean("dropped-items.tier-glow") || paramMMOItems.getConfig().getBoolean("dropped-items.hints"))
/* 31 */       Bukkit.getPluginManager().registerEvents((Listener)new DroppedItems(paramMMOItems.getConfig().getConfigurationSection("dropped-items")), (Plugin)paramMMOItems); 
/* 32 */     if ((paramMMOItems.getLanguage()).disableRemovedItems) {
/* 33 */       Bukkit.getPluginManager().registerEvents((Listener)new DisabledItemsListener(paramMMOItems), (Plugin)paramMMOItems);
/*    */     }
/* 35 */     Bukkit.getScheduler().runTaskTimer((Plugin)paramMMOItems, () -> Bukkit.getOnlinePlayers().forEach(()), 100L, 20L);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\MMOItemsBukkit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */