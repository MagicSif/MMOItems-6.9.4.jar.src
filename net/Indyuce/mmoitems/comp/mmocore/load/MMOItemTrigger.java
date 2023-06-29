/*    */ package net.Indyuce.mmoitems.comp.mmocore.load;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import io.lumine.mythic.lib.api.util.SmartGive;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmocore.api.quest.trigger.Trigger;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class MMOItemTrigger extends Trigger {
/*    */   private final MMOItemTemplate template;
/*    */   
/*    */   public MMOItemTrigger(MMOLineConfig paramMMOLineConfig) {
/* 18 */     super(paramMMOLineConfig);
/*    */     
/* 20 */     paramMMOLineConfig.validate(new String[] { "type", "id" });
/*    */     
/* 22 */     String str1 = paramMMOLineConfig.getString("type").toUpperCase().replace("-", "_").replace(" ", "_");
/* 23 */     Validate.isTrue(MMOItems.plugin.getTypes().has(str1), "Could not find item type with ID '" + str1 + "'");
/* 24 */     Type type = MMOItems.plugin.getTypes().get(str1);
/*    */     
/* 26 */     String str2 = paramMMOLineConfig.getString("id").replace("-", "_").toUpperCase();
/* 27 */     Validate.isTrue(MMOItems.plugin.getTemplates().hasTemplate(type, str2), "Could not find MMOItem with ID '" + str2 + "'");
/* 28 */     this.template = MMOItems.plugin.getTemplates().getTemplate(type, str2);
/*    */     
/* 30 */     this.amount = Math.max(1, paramMMOLineConfig.getInt("amount", 1));
/*    */   }
/*    */   private final int amount;
/*    */   
/*    */   public void apply(PlayerData paramPlayerData) {
/* 35 */     ItemStack itemStack = this.template.newBuilder(PlayerData.get(paramPlayerData.getUniqueId()).getRPG()).build().newBuilder().build();
/* 36 */     itemStack.setAmount(this.amount);
/* 37 */     (new SmartGive(paramPlayerData.getPlayer())).give(new ItemStack[] { itemStack });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\load\MMOItemTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */