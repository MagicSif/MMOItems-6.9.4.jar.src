/*    */ package net.Indyuce.mmoitems.comp.mmocore.load;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmocore.loot.LootBuilder;
/*    */ import net.Indyuce.mmoitems.ItemStats;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import net.Indyuce.mmoitems.stat.data.SoulboundData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class ItemTemplateDropItem extends ItemGenerationDropItem {
/*    */   private final MMOItemTemplate template;
/*    */   
/*    */   public ItemTemplateDropItem(MMOLineConfig paramMMOLineConfig) {
/* 21 */     super(paramMMOLineConfig);
/*    */     
/* 23 */     paramMMOLineConfig.validate(new String[] { "type", "id" });
/*    */     
/* 25 */     String str1 = paramMMOLineConfig.getString("type").toUpperCase().replace("-", "_").replace(" ", "_");
/* 26 */     Validate.isTrue(MMOItems.plugin.getTypes().has(str1), "Could not find item type with ID '" + str1 + "'");
/* 27 */     Type type = MMOItems.plugin.getTypes().get(str1);
/*    */     
/* 29 */     String str2 = paramMMOLineConfig.getString("id").replace("-", "_").toUpperCase();
/* 30 */     Validate.isTrue(MMOItems.plugin.getTemplates().hasTemplate(type, str2), "Could not find MMOItem with ID '" + str2 + "'");
/* 31 */     this.template = MMOItems.plugin.getTemplates().getTemplate(type, str2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void collect(LootBuilder paramLootBuilder) {
/* 36 */     RPGPlayer rPGPlayer = PlayerData.get(paramLootBuilder.getEntity().getUniqueId()).getRPG();
/* 37 */     MMOItem mMOItem = rollMMOItem(this.template, rPGPlayer);
/*    */     
/* 39 */     if (rollSoulbound()) {
/* 40 */       mMOItem.setData(ItemStats.SOULBOUND, (StatData)new SoulboundData(rPGPlayer.getPlayer(), 1));
/*    */     }
/* 42 */     ItemStack itemStack = rollUnidentification(mMOItem);
/* 43 */     itemStack.setAmount(rollAmount());
/* 44 */     paramLootBuilder.addLoot(itemStack);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\load\ItemTemplateDropItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */