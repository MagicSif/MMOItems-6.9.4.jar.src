/*    */ package net.Indyuce.mmoitems.comp.mmocore.load;
/*    */ 
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import java.util.Optional;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmocore.experience.dispenser.ExperienceDispenser;
/*    */ import net.Indyuce.mmocore.experience.source.type.SpecificExperienceSource;
/*    */ import net.Indyuce.mmocore.manager.profession.ExperienceSourceManager;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.block.BlockCookEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class SmeltMMOItemExperienceSource extends SpecificExperienceSource<NBTItem> {
/*    */   private final String type;
/*    */   
/*    */   public SmeltMMOItemExperienceSource(ExperienceDispenser paramExperienceDispenser, MMOLineConfig paramMMOLineConfig) {
/* 23 */     super(paramExperienceDispenser, paramMMOLineConfig);
/*    */     
/* 25 */     paramMMOLineConfig.validate(new String[] { "type", "id" });
/* 26 */     this.type = paramMMOLineConfig.getString("type").replace("-", "_").replace(" ", "_").toUpperCase();
/* 27 */     this.id = paramMMOLineConfig.getString("id").replace("-", "_").replace(" ", "_").toUpperCase();
/*    */   }
/*    */   private final String id;
/*    */   
/*    */   public ExperienceSourceManager<SmeltMMOItemExperienceSource> newManager() {
/* 32 */     return new ExperienceSourceManager<SmeltMMOItemExperienceSource>()
/*    */       {
/*    */         @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
/*    */         public void a(BlockCookEvent param1BlockCookEvent) {
/* 36 */           Optional<OfflinePlayer> optional = (Optional)SmeltMMOItemExperienceSource.this.getNearbyPlayer(param1BlockCookEvent.getBlock().getLocation());
/* 37 */           if (!optional.isPresent()) {
/*    */             return;
/*    */           }
/* 40 */           ItemStack itemStack = param1BlockCookEvent.getResult();
/* 41 */           NBTItem nBTItem = MythicLib.plugin.getVersion().getWrapper().getNBTItem(itemStack);
/* 42 */           if (!nBTItem.hasType()) {
/*    */             return;
/*    */           }
/* 45 */           PlayerData playerData = PlayerData.get(optional.get());
/* 46 */           for (SmeltMMOItemExperienceSource smeltMMOItemExperienceSource : getSources()) {
/* 47 */             if (smeltMMOItemExperienceSource.matches(playerData, nBTItem))
/* 48 */               smeltMMOItemExperienceSource.giveExperience(playerData, 1.0D, param1BlockCookEvent.getBlock().getLocation().add(0.5D, 1.0D, 0.5D)); 
/*    */           } 
/*    */         }
/*    */       };
/*    */   }
/*    */   private Optional<Player> getNearbyPlayer(Location paramLocation) {
/* 54 */     return paramLocation.getWorld().getPlayers().stream().filter(paramPlayer -> (paramPlayer.getLocation().distanceSquared(paramLocation) < 100.0D)).findAny();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matchesParameter(PlayerData paramPlayerData, NBTItem paramNBTItem) {
/* 59 */     return (paramNBTItem.getString("MMOITEMS_ITEM_TYPE").equals(this.type) && paramNBTItem.getString("MMOITEMS_ITEM_ID").equals(this.id));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\load\SmeltMMOItemExperienceSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */