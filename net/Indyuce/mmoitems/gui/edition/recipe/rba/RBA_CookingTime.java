/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.rba;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*    */ import io.lumine.mythic.lib.api.util.ui.QuickNumberRange;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.type.RBA_DoubleButton;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class RBA_CookingTime extends RBA_DoubleButton {
/*    */   public static final String FURNACE_TIME = "time";
/*    */   public static final double DEFAULT = 200.0D;
/*    */   @NotNull
/*    */   final ItemStack doubleButton;
/*    */   
/*    */   public RBA_CookingTime(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/* 20 */     super(paramRecipeMakerGUI);
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
/* 32 */     this.doubleButton = RecipeMakerGUI.addLore(ItemFactory.of(Material.CLOCK).name("§cDuration").lore(SilentNumbers.chop("How long it takes this recipe to finish 'cooking' x)", 65, "§7"))
/*    */         
/* 34 */         .build(), SilentNumbers.toArrayList((Object[])new String[] { "" })); } @NotNull
/* 35 */   public ItemStack getDoubleButton() { return this.doubleButton; }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getDoubleConfigPath() {
/*    */     return "time";
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public QuickNumberRange getRange() {
/*    */     return new QuickNumberRange(Double.valueOf(0.0D), null);
/*    */   }
/*    */   
/*    */   public boolean requireInteger() {
/*    */     return true;
/*    */   }
/*    */   
/*    */   public double getDefaultValue() {
/*    */     return 200.0D;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RBA_CookingTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */