/*    */ package net.Indyuce.mmoitems.stat.type;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AttackWeaponStat
/*    */   extends DoubleStat
/*    */ {
/*    */   private final Attribute attribute;
/*    */   
/*    */   public AttackWeaponStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString, Attribute paramAttribute) {
/* 17 */     super(paramString1, paramMaterial, paramString2, paramArrayOfString, new String[] { "!consumable", "!block", "!miscellaneous", "all" }, new Material[0]);
/*    */     
/* 19 */     this.attribute = paramAttribute;
/*    */   }
/*    */   
/*    */   public Attribute getAttribute() {
/* 23 */     return this.attribute;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getOffset(PlayerData paramPlayerData) {
/* 45 */     return paramPlayerData.getPlayer().getAttribute(this.attribute).getBaseValue();
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\AttackWeaponStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */