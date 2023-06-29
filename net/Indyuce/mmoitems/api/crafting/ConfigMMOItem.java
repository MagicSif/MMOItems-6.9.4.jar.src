/*    */ package net.Indyuce.mmoitems.api.crafting;
/*    */ 
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConfigMMOItem
/*    */ {
/*    */   private final MMOItemTemplate template;
/*    */   private final int amount;
/*    */   private ItemStack preview;
/*    */   
/*    */   public ConfigMMOItem(ConfigurationSection paramConfigurationSection) {
/* 23 */     Validate.notNull(paramConfigurationSection, "Could not read MMOItem config");
/*    */     
/* 25 */     Validate.isTrue((paramConfigurationSection.contains("type") && paramConfigurationSection.contains("id")), "Config must contain type and ID");
/* 26 */     Type type = MMOItems.plugin.getTypes().getOrThrow(paramConfigurationSection.getString("type").toUpperCase().replace("-", "_").replace(" ", "_"));
/* 27 */     this.template = MMOItems.plugin.getTemplates().getTemplateOrThrow(type, paramConfigurationSection.getString("id"));
/*    */     
/* 29 */     this.amount = Math.max(1, paramConfigurationSection.getInt("amount"));
/*    */   }
/*    */   
/*    */   public ConfigMMOItem(MMOItemTemplate paramMMOItemTemplate, int paramInt) {
/* 33 */     Validate.notNull(paramMMOItemTemplate, "Could not register recipe output");
/*    */     
/* 35 */     this.template = paramMMOItemTemplate;
/* 36 */     this.amount = Math.max(1, paramInt);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack generate(@NotNull RPGPlayer paramRPGPlayer) {
/* 48 */     ItemStack itemStack = this.template.newBuilder(paramRPGPlayer).build().newBuilder().build();
/* 49 */     itemStack.setAmount(this.amount);
/* 50 */     return itemStack;
/*    */   }
/*    */   
/*    */   public MMOItemTemplate getTemplate() {
/* 54 */     return this.template;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getPreview() {
/* 61 */     return (this.preview == null) ? (this.preview = (new MMOItemBuilder(this.template, 0, null, true)).build().newBuilder().build(true)).clone() : this.preview.clone();
/*    */   }
/*    */   public int getAmount() {
/* 64 */     return this.amount;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ConfigMMOItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */