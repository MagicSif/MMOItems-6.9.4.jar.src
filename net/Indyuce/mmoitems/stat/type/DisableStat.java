/*    */ package net.Indyuce.mmoitems.stat.type;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DisableStat
/*    */   extends BooleanStat
/*    */ {
/*    */   public DisableStat(String paramString1, Material paramMaterial, String paramString2, String... paramVarArgs) {
/* 13 */     super("DISABLE_" + paramString1, paramMaterial, paramString2, paramVarArgs, new String[] { "all" }, new Material[0]);
/*    */   }
/*    */   
/*    */   public DisableStat(String paramString1, Material paramMaterial, String paramString2, Material[] paramArrayOfMaterial, String... paramVarArgs) {
/* 17 */     super("DISABLE_" + paramString1, paramMaterial, paramString2, paramVarArgs, new String[] { "all" }, paramArrayOfMaterial);
/*    */   }
/*    */   
/*    */   public DisableStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String... paramVarArgs1) {
/* 21 */     super("DISABLE_" + paramString1, paramMaterial, paramString2, paramVarArgs1, paramArrayOfString1, new Material[0]);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\DisableStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */