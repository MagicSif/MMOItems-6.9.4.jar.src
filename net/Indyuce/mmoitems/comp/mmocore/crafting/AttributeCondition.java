/*    */ package net.Indyuce.mmoitems.comp.mmocore.crafting;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import net.Indyuce.mmocore.MMOCore;
/*    */ import net.Indyuce.mmocore.api.player.PlayerData;
/*    */ import net.Indyuce.mmocore.api.player.attribute.PlayerAttribute;
/*    */ import net.Indyuce.mmoitems.api.crafting.condition.Condition;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.apache.commons.lang.Validate;
/*    */ 
/*    */ public class AttributeCondition extends Condition {
/*    */   private final PlayerAttribute attribute;
/*    */   
/*    */   public AttributeCondition(MMOLineConfig paramMMOLineConfig) {
/* 15 */     super("attribute");
/*    */     
/* 17 */     paramMMOLineConfig.validate(new String[] { "attribute", "points" });
/*    */     
/* 19 */     this.points = paramMMOLineConfig.getInt("points");
/*    */     
/* 21 */     String str = paramMMOLineConfig.getString("attribute").toLowerCase().replace("_", "-");
/* 22 */     Validate.isTrue(MMOCore.plugin.attributeManager.has(str), "Could not find attribute " + str);
/* 23 */     this.attribute = MMOCore.plugin.attributeManager.get(str);
/*    */   }
/*    */   private final int points;
/*    */   
/*    */   public String formatDisplay(String paramString) {
/* 28 */     return paramString.replace("#level#", "" + this.points).replace("#attribute#", this.attribute.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isMet(PlayerData paramPlayerData) {
/* 33 */     return (PlayerData.get(paramPlayerData.getUniqueId()).getAttributes().getAttribute(this.attribute) >= this.points);
/*    */   }
/*    */   
/*    */   public void whenCrafting(PlayerData paramPlayerData) {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\mmocore\crafting\AttributeCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */