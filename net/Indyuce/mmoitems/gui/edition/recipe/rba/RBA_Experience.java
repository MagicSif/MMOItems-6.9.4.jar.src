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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RBA_Experience
/*    */   extends RBA_DoubleButton
/*    */ {
/*    */   public static final String FURNACE_EXPERIENCE = "exp";
/*    */   public static final double DEFAULT = 0.35D;
/*    */   @NotNull
/*    */   final ItemStack doubleButton;
/*    */   
/*    */   public RBA_Experience(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/* 25 */     super(paramRecipeMakerGUI);
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
/* 37 */     this.doubleButton = RecipeMakerGUI.addLore(ItemFactory.of(Material.EXPERIENCE_BOTTLE).name("§aExperience").lore(SilentNumbers.chop("This recipe gives experience when crafted, how much?", 65, "§7"))
/*    */         
/* 39 */         .build(), SilentNumbers.toArrayList((Object[])new String[] { "" })); } @NotNull
/* 40 */   public ItemStack getDoubleButton() { return this.doubleButton; }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getDoubleConfigPath() {
/*    */     return "exp";
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public QuickNumberRange getRange() {
/*    */     return new QuickNumberRange(Double.valueOf(0.0D), null);
/*    */   }
/*    */   
/*    */   public boolean requireInteger() {
/*    */     return false;
/*    */   }
/*    */   
/*    */   public double getDefaultValue() {
/*    */     return 0.35D;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RBA_Experience.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */