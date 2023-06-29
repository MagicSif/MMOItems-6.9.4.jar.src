/*    */ package net.Indyuce.mmoitems.api.player;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmptyRPGPlayer
/*    */   extends RPGPlayer
/*    */ {
/*    */   public EmptyRPGPlayer(PlayerData paramPlayerData) {
/* 12 */     super(paramPlayerData);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLevel() {
/* 17 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getClassName() {
/* 22 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public double getMana() {
/* 27 */     return 0.0D;
/*    */   }
/*    */ 
/*    */   
/*    */   public double getStamina() {
/* 32 */     return 0.0D;
/*    */   }
/*    */   
/*    */   public void setMana(double paramDouble) {}
/*    */   
/*    */   public void setStamina(double paramDouble) {}
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\player\EmptyRPGPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */