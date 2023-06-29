/*    */ package net.Indyuce.mmoitems.comp.mmocore.load;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmocore.loot.droptable.dropitem.DropItem;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.ItemTier;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class ItemGenerationDropItem
/*    */   extends DropItem
/*    */ {
/*    */   protected final int level;
/*    */   protected final ItemTier tier;
/*    */   private final double unidentified;
/*    */   private final double soulbound;
/*    */   
/*    */   public ItemGenerationDropItem(MMOLineConfig paramMMOLineConfig) {
/* 25 */     super(paramMMOLineConfig);
/*    */     
/* 27 */     this.level = paramMMOLineConfig.getInt("level", 0);
/*    */     
/* 29 */     if (paramMMOLineConfig.contains("tier")) {
/* 30 */       String str = paramMMOLineConfig.getString("tier").toUpperCase().replace("-", "_").replace(" ", "_");
/* 31 */       Validate.isTrue(MMOItems.plugin.getTiers().has(str), "Could not find item tier with ID '" + str + "'");
/* 32 */       this.tier = MMOItems.plugin.getTiers().get(str);
/*    */     } else {
/* 34 */       this.tier = null;
/*    */     } 
/* 36 */     this.unidentified = paramMMOLineConfig.getDouble("unidentified", 0.0D);
/* 37 */     this.soulbound = paramMMOLineConfig.getDouble("soulbound", 0.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MMOItem rollMMOItem(MMOItemTemplate paramMMOItemTemplate, RPGPlayer paramRPGPlayer) {
/* 43 */     boolean bool = (this.level > 0) ? this.level : (paramMMOItemTemplate.hasOption(MMOItemTemplate.TemplateOption.LEVEL_ITEM) ? MMOItems.plugin.getTemplates().rollLevel(paramRPGPlayer.getLevel()) : false);
/* 44 */     ItemTier itemTier = (this.tier != null) ? this.tier : (paramMMOItemTemplate.hasOption(MMOItemTemplate.TemplateOption.TIERED) ? MMOItems.plugin.getTemplates().rollTier() : null);
/* 45 */     return (new MMOItemBuilder(paramMMOItemTemplate, bool, itemTier)).build();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public ItemStack rollUnidentification(MMOItem paramMMOItem) {
/* 50 */     return (random.nextDouble() < this.unidentified) ? paramMMOItem.getType().getUnidentifiedTemplate().newBuilder(paramMMOItem.newBuilder().buildNBT()).build() : 
/* 51 */       paramMMOItem.newBuilder().build();
/*    */   }
/*    */   
/*    */   public boolean rollSoulbound() {
/* 55 */     return (random.nextDouble() < this.soulbound);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\load\ItemGenerationDropItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */