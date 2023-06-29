/*    */ package net.Indyuce.mmoitems.comp.mmocore.load;
/*    */ 
/*    */ import io.lumine.mythic.lib.UtilityMethods;
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmocore.loot.LootBuilder;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ItemTier;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.ClassFilter;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.TemplateExplorer;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.TierFilter;
/*    */ import net.Indyuce.mmoitems.api.item.template.explorer.TypeFilter;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.stat.data.SoulboundData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class RandomItemDropItem
/*    */   extends ItemGenerationDropItem {
/*    */   private final boolean matchClass;
/*    */   private final String profess;
/*    */   private final Type type;
/*    */   private final ItemTier tier;
/*    */   
/*    */   public RandomItemDropItem(MMOLineConfig paramMMOLineConfig) {
/* 33 */     super(paramMMOLineConfig);
/*    */     
/* 35 */     this.matchClass = paramMMOLineConfig.getBoolean("match-class", false);
/* 36 */     this.profess = paramMMOLineConfig.getString("class", "");
/*    */     
/* 38 */     if (paramMMOLineConfig.contains("type")) {
/* 39 */       String str = paramMMOLineConfig.getString("type").toUpperCase().replace("-", "_").replace(" ", "_");
/* 40 */       Validate.isTrue(MMOItems.plugin.getTypes().has(str), "Could not find item type with ID '" + str + "'");
/* 41 */       this.type = MMOItems.plugin.getTypes().get(str);
/*    */     } else {
/* 43 */       this.type = null;
/*    */     } 
/* 45 */     if (paramMMOLineConfig.contains("tierset")) {
/* 46 */       String str = UtilityMethods.enumName(paramMMOLineConfig.getString("tierset"));
/* 47 */       Validate.isTrue(MMOItems.plugin.getTiers().has(str), "Could not find item tier");
/* 48 */       this.tier = MMOItems.plugin.getTiers().get(str);
/*    */     } else {
/* 50 */       this.tier = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void collect(LootBuilder paramLootBuilder) {
/* 55 */     RPGPlayer rPGPlayer = PlayerData.get(paramLootBuilder.getEntity().getUniqueId()).getRPG();
/*    */     
/* 57 */     TemplateExplorer templateExplorer = new TemplateExplorer();
/* 58 */     if (this.matchClass) {
/* 59 */       templateExplorer.applyFilter((Predicate)new ClassFilter(rPGPlayer));
/* 60 */     } else if (!this.profess.isEmpty()) {
/* 61 */       templateExplorer.applyFilter((Predicate)new ClassFilter(this.profess));
/*    */     } 
/* 63 */     if (this.type != null) {
/* 64 */       templateExplorer.applyFilter((Predicate)new TypeFilter(this.type));
/*    */     }
/* 66 */     if (this.tier != null) {
/* 67 */       templateExplorer.applyFilter((Predicate)new TierFilter(this.tier.getId()));
/*    */     }
/* 69 */     Optional<MMOItemTemplate> optional = templateExplorer.rollLoot();
/* 70 */     if (!optional.isPresent()) {
/*    */       return;
/*    */     }
/* 73 */     MMOItem mMOItem = rollMMOItem(optional.get(), rPGPlayer);
/*    */     
/* 75 */     if (rollSoulbound()) {
/* 76 */       mMOItem.setData(ItemStats.SOULBOUND, (StatData)new SoulboundData(rPGPlayer.getPlayer(), 1));
/*    */     }
/* 78 */     ItemStack itemStack = rollUnidentification(mMOItem);
/* 79 */     itemStack.setAmount(rollAmount());
/* 80 */     paramLootBuilder.addLoot(itemStack);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\load\RandomItemDropItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */