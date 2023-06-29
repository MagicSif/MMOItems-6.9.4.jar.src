/*    */ package net.Indyuce.mmoitems.api.crafting.trigger;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import io.lumine.mythic.lib.api.util.SmartGive;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class VanillaTrigger extends Trigger {
/*    */   private final Material material;
/*    */   private final int amount;
/*    */   
/*    */   public VanillaTrigger(MMOLineConfig paramMMOLineConfig) {
/* 14 */     super("vanilla");
/*    */     
/* 16 */     paramMMOLineConfig.validate(new String[] { "type" });
/*    */     
/* 18 */     this.material = Material.valueOf(paramMMOLineConfig.getString("type").toUpperCase().replace("-", "_"));
/* 19 */     this.amount = paramMMOLineConfig.contains("amount") ? Math.max(1, paramMMOLineConfig.getInt("amount")) : 1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {
/* 24 */     if (!paramPlayerData.isOnline())
/* 25 */       return;  (new SmartGive(paramPlayerData.getPlayer())).give(new ItemStack[] { new ItemStack(this.material, this.amount) });
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\trigger\VanillaTrigger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */