/*    */ package net.Indyuce.mmoitems.comp.mmocore.load;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import java.util.Optional;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmocore.experience.dispenser.ExperienceDispenser;
/*    */ import net.Indyuce.mmocore.experience.source.type.SpecificExperienceSource;
/*    */ import net.Indyuce.mmocore.manager.profession.ExperienceSourceManager;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.block.CustomBlock;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.enchantments.Enchantment;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.block.BlockBreakEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class MineMIBlockExperienceSource extends SpecificExperienceSource<Integer> {
/*    */   private final int id;
/*    */   private final boolean silkTouch;
/*    */   private final boolean playerPlaced;
/*    */   
/*    */   public MineMIBlockExperienceSource(ExperienceDispenser paramExperienceDispenser, MMOLineConfig paramMMOLineConfig) {
/* 25 */     super(paramExperienceDispenser, paramMMOLineConfig);
/*    */     
/* 27 */     paramMMOLineConfig.validate(new String[] { "id" });
/* 28 */     this.id = paramMMOLineConfig.getInt("id", 1);
/* 29 */     this.silkTouch = paramMMOLineConfig.getBoolean("silk-touch", true);
/* 30 */     this.playerPlaced = paramMMOLineConfig.getBoolean("player-placed", false);
/*    */   }
/*    */ 
/*    */   
/*    */   public ExperienceSourceManager<MineMIBlockExperienceSource> newManager() {
/* 35 */     return new ExperienceSourceManager<MineMIBlockExperienceSource>()
/*    */       {
/*    */         @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
/*    */         public void a(BlockBreakEvent param1BlockBreakEvent) {
/* 39 */           if (param1BlockBreakEvent.getPlayer().getGameMode() != GameMode.SURVIVAL) {
/*    */             return;
/*    */           }
/* 42 */           PlayerData playerData = PlayerData.get((OfflinePlayer)param1BlockBreakEvent.getPlayer());
/* 43 */           Optional<CustomBlock> optional = MMOItems.plugin.getCustomBlocks().getFromBlock(param1BlockBreakEvent.getBlock().getBlockData());
/* 44 */           if (!optional.isPresent()) {
/*    */             return;
/*    */           }
/* 47 */           for (MineMIBlockExperienceSource mineMIBlockExperienceSource : getSources()) {
/* 48 */             if ((mineMIBlockExperienceSource.silkTouch && MineMIBlockExperienceSource.this.hasSilkTouch(param1BlockBreakEvent.getPlayer().getInventory().getItemInMainHand())) || (
/* 49 */               !mineMIBlockExperienceSource.playerPlaced && param1BlockBreakEvent.getBlock().hasMetadata("player_placed"))) {
/*    */               continue;
/*    */             }
/* 52 */             if (mineMIBlockExperienceSource.matches(playerData, Integer.valueOf(((CustomBlock)optional.get()).getId())))
/* 53 */               mineMIBlockExperienceSource.giveExperience(playerData, 1.0D, param1BlockBreakEvent.getBlock().getLocation()); 
/*    */           } 
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   private boolean hasSilkTouch(ItemStack paramItemStack) {
/* 60 */     return (paramItemStack != null && paramItemStack.hasItemMeta() && paramItemStack.getItemMeta().hasEnchant(Enchantment.SILK_TOUCH));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matchesParameter(PlayerData paramPlayerData, Integer paramInteger) {
/* 65 */     return (this.id == paramInteger.intValue());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\load\MineMIBlockExperienceSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */