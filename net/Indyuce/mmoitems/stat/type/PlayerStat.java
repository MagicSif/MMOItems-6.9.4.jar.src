/*    */ package net.Indyuce.mmoitems.stat.type;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class PlayerStat
/*    */   extends DoubleStat
/*    */ {
/*    */   public PlayerStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString) {
/* 13 */     this(paramString1, paramMaterial, paramString2, paramArrayOfString, new String[] { "!miscellaneous", "!block", "all" }, true, new Material[0]);
/*    */   }
/*    */   
/*    */   public PlayerStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, Material... paramVarArgs) {
/* 17 */     this(paramString1, paramMaterial, paramString2, paramArrayOfString1, paramArrayOfString2, true, paramVarArgs);
/*    */   }
/*    */   
/*    */   public PlayerStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, boolean paramBoolean, Material... paramVarArgs) {
/* 21 */     super(paramString1, paramMaterial, paramString2, paramArrayOfString1, paramArrayOfString2, paramBoolean, paramVarArgs);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\PlayerStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */