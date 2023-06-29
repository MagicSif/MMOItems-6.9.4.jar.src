/*    */ package net.Indyuce.mmoitems.api.edition.input;
/*    */ 
/*    */ import net.Indyuce.mmoitems.api.edition.Edition;
/*    */ import org.bukkit.entity.Player;
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
/*    */ public abstract class PlayerInputHandler
/*    */ {
/*    */   private final Edition edition;
/*    */   
/*    */   public PlayerInputHandler(Edition paramEdition) {
/* 21 */     this.edition = paramEdition;
/*    */   }
/*    */   
/*    */   public Player getPlayer() {
/* 25 */     return this.edition.getInventory().getPlayer();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void registerInput(String paramString) {
/* 36 */     if (!this.edition.processInput(paramString)) {
/*    */       return;
/*    */     }
/* 39 */     if (this.edition.shouldGoBack())
/* 40 */       this.edition.getInventory().open(); 
/* 41 */     close();
/*    */   }
/*    */   
/*    */   public abstract void close();
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\edition\input\PlayerInputHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */