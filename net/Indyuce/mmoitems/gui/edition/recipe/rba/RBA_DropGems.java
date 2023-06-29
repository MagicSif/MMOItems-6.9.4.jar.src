/*    */ package net.Indyuce.mmoitems.gui.edition.recipe.rba;
/*    */ 
/*    */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*    */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*    */ import net.Indyuce.mmoitems.gui.edition.recipe.rba.type.RBA_BooleanButton;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RBA_DropGems
/*    */   extends RBA_BooleanButton
/*    */ {
/*    */   public static final String SMITH_GEMS = "drop-gems";
/*    */   @NotNull
/*    */   final ItemStack booleanButton;
/*    */   
/*    */   public RBA_DropGems(@NotNull RecipeMakerGUI paramRecipeMakerGUI) {
/* 24 */     super(paramRecipeMakerGUI);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 29 */     this.booleanButton = RecipeMakerGUI.addLore(ItemFactory.of(Material.EMERALD).name("§aDrop Gemstones").lore(SilentNumbers.chop("Usually, gemstones that dont fit the new item are lost. Enable this to make them drop (and be recovered) instead.", 65, "§7"))
/*    */         
/* 31 */         .build(), SilentNumbers.toArrayList((Object[])new String[] { "" })); } @NotNull
/* 32 */   public ItemStack getBooleanButton() { return this.booleanButton; }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getBooleanConfigPath() {
/*    */     return "drop-gems";
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RBA_DropGems.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */