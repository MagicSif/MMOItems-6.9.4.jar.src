/*    */ package net.Indyuce.mmoitems.api.crafting.ingredient;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*    */ import io.lumine.mythic.lib.api.item.NBTItem;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Predicate;
/*    */ import net.Indyuce.mmoitems.api.crafting.ConditionalDisplay;
/*    */ import net.Indyuce.mmoitems.api.crafting.LoadedCraftingObject;
/*    */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.PlayerIngredient;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class IngredientType
/*    */   extends LoadedCraftingObject<Ingredient>
/*    */ {
/*    */   private final Predicate<NBTItem> check;
/*    */   private final Function<NBTItem, PlayerIngredient> readIngredient;
/*    */   
/*    */   public IngredientType(String paramString, Function<MMOLineConfig, Ingredient> paramFunction, ConditionalDisplay paramConditionalDisplay, Predicate<NBTItem> paramPredicate, Function<NBTItem, PlayerIngredient> paramFunction1) {
/* 22 */     super(paramString, paramFunction, paramConditionalDisplay);
/*    */     
/* 24 */     this.check = paramPredicate;
/* 25 */     this.readIngredient = paramFunction1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(NBTItem paramNBTItem) {
/* 32 */     return this.check.test(paramNBTItem);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PlayerIngredient readPlayerIngredient(NBTItem paramNBTItem) {
/* 42 */     return this.readIngredient.apply(paramNBTItem);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\IngredientType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */