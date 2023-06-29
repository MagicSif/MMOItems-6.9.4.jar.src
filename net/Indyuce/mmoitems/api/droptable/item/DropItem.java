/*    */ package net.Indyuce.mmoitems.api.droptable.item;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class DropItem
/*    */ {
/*    */   private final double drop;
/*    */   private final int min;
/*    */   private final int max;
/* 15 */   protected static final Random random = new Random();
/*    */   
/*    */   public DropItem(double paramDouble, int paramInt1, int paramInt2) {
/* 18 */     this.drop = paramDouble;
/* 19 */     this.min = paramInt1;
/* 20 */     this.max = paramInt2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DropItem(String paramString) {
/* 27 */     String[] arrayOfString1 = paramString.split(",");
/* 28 */     this.drop = Double.parseDouble(arrayOfString1[0]) / 100.0D;
/*    */     
/* 30 */     String[] arrayOfString2 = arrayOfString1[1].split("-");
/* 31 */     this.min = Integer.parseInt(arrayOfString2[0]);
/* 32 */     this.max = (arrayOfString2.length > 1) ? Integer.parseInt(arrayOfString2[1]) : this.min;
/*    */   }
/*    */   
/*    */   public boolean rollDrop() {
/* 36 */     return (random.nextDouble() < this.drop);
/*    */   }
/*    */   
/*    */   public int rollAmount() {
/* 40 */     return (this.max > this.min) ? (this.min + random.nextInt(this.max - this.min + 1)) : this.min;
/*    */   }
/*    */   
/*    */   public ItemStack getItem(@Nullable PlayerData paramPlayerData) {
/* 44 */     return getItem(paramPlayerData, rollAmount());
/*    */   }
/*    */   
/*    */   public abstract ItemStack getItem(@Nullable PlayerData paramPlayerData, int paramInt);
/*    */   
/*    */   public abstract String getKey();
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\droptable\item\DropItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */