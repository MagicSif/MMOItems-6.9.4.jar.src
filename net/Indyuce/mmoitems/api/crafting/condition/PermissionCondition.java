/*    */ package net.Indyuce.mmoitems.api.crafting.condition;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ 
/*    */ public class PermissionCondition
/*    */   extends GenericCondition {
/*    */   private final List<String> permissions;
/*    */   
/*    */   public PermissionCondition(MMOLineConfig paramMMOLineConfig) {
/* 13 */     super("permission", paramMMOLineConfig);
/*    */     
/* 15 */     paramMMOLineConfig.validate(new String[] { "list" });
/* 16 */     this.permissions = Arrays.asList(paramMMOLineConfig.getString("list").split(","));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 21 */     for (String str : this.permissions) {
/* 22 */       if (!paramPlayerData.getPlayer().hasPermission(str))
/* 23 */         return false; 
/* 24 */     }  return true;
/*    */   }
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\condition\PermissionCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */