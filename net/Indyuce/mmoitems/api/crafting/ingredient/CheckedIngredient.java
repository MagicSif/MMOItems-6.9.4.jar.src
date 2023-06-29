/*    */ package net.Indyuce.mmoitems.api.crafting.ingredient;
/*    */ 
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.PlayerIngredient;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public class CheckedIngredient
/*    */ {
/*    */   @NotNull
/*    */   private final Ingredient ingredient;
/*    */   @Nullable
/*    */   private final Set<PlayerIngredient> found;
/*    */   private final boolean isHad;
/*    */   
/*    */   public CheckedIngredient(@NotNull Ingredient paramIngredient, @Nullable Set<PlayerIngredient> paramSet) {
/* 29 */     this.ingredient = paramIngredient;
/* 30 */     this.found = paramSet;
/* 31 */     this.isHad = (getTotalAmount() >= paramIngredient.getAmount());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isHad() {
/* 38 */     return this.isHad;
/*    */   }
/*    */   
/*    */   public int getTotalAmount() {
/* 42 */     int i = 0;
/* 43 */     for (PlayerIngredient playerIngredient : this.found)
/* 44 */       i += playerIngredient.getAmount(); 
/* 45 */     return i;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void takeAway() {
/* 52 */     reduceItem(this.ingredient.getAmount());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void reduceItem(int paramInt) {
/* 63 */     Iterator<PlayerIngredient> iterator = this.found.iterator();
/* 64 */     while (iterator.hasNext() && paramInt > 0) {
/* 65 */       ItemStack itemStack = ((PlayerIngredient)iterator.next()).getItem();
/*    */ 
/*    */       
/* 68 */       if (itemStack.getAmount() <= 0) {
/* 69 */         iterator.remove();
/*    */         
/*    */         continue;
/*    */       } 
/*    */       
/* 74 */       int i = Math.min(itemStack.getAmount(), paramInt);
/*    */       
/* 76 */       paramInt -= i;
/* 77 */       itemStack.setAmount(itemStack.getAmount() - i);
/*    */     } 
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Ingredient getIngredient() {
/* 83 */     return this.ingredient;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Set<PlayerIngredient> getFound() {
/* 88 */     return this.found;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String format() {
/* 93 */     return this.ingredient.formatDisplay(this.ingredient.getDisplay().format(this.isHad));
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\CheckedIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */