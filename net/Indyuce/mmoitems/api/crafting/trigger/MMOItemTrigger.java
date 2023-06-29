/*    */ package net.Indyuce.mmoitems.api.crafting.trigger;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import io.lumine.mythic.lib.api.util.SmartGive;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import net.Indyuce.mmoitems.api.Type;
/*    */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class MMOItemTrigger
/*    */   extends Trigger {
/*    */   private final MMOItemTemplate template;
/*    */   private final int amount;
/*    */   
/*    */   public MMOItemTrigger(MMOLineConfig paramMMOLineConfig) {
/* 19 */     super("mmoitem");
/*    */     
/* 21 */     paramMMOLineConfig.validate(new String[] { "type", "id" });
/*    */     
/* 23 */     Type type = MMOItems.plugin.getTypes().getOrThrow(paramMMOLineConfig.getString("type").toUpperCase().replace("-", "_").replace(" ", "_"));
/* 24 */     String str = paramMMOLineConfig.getString("id").replace("-", "_").toUpperCase();
/* 25 */     Validate.isTrue(MMOItems.plugin.getTemplates().hasTemplate(type, str), "Could not find MMOItem with ID '" + str + "'");
/*    */     
/* 27 */     this.template = MMOItems.plugin.getTemplates().getTemplate(type, str);
/* 28 */     this.amount = paramMMOLineConfig.contains("amount") ? Math.max(1, paramMMOLineConfig.getInt("amount")) : 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 33 */     if (!paramPlayerData.isOnline())
/* 34 */       return;  ItemStack itemStack = this.template.newBuilder(paramPlayerData.getRPG()).build().newBuilder().build();
/* 35 */     if (itemStack == null || itemStack.getType() == Material.AIR) {
/*    */       return;
/*    */     }
/* 38 */     itemStack.setAmount(this.amount);
/* 39 */     if (itemStack != null && itemStack.getType() != Material.AIR)
/* 40 */       (new SmartGive(paramPlayerData.getPlayer())).give(new ItemStack[] { itemStack }); 
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\trigger\MMOItemTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */