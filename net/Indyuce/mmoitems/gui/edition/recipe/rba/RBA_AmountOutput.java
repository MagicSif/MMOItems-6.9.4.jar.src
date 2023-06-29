/*     */ package net.Indyuce.mmoitems.gui.edition.recipe.rba;
/*     */ 
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.util.ItemFactory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.Objects;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.gui.edition.recipe.gui.RecipeMakerGUI;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.BannerMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RBA_AmountOutput
/*     */   extends RecipeButtonAction
/*     */ {
/*     */   @NotNull
/*     */   public final String[] amountLog;
/*     */   @NotNull
/*     */   final ItemStack button;
/*     */   public static final String AMOUNT_INGREDIENTS = "amount";
/*     */   
/*     */   public RBA_AmountOutput(@NotNull RecipeMakerGUI paramRecipeMakerGUI, @NotNull ItemStack paramItemStack) {
/*  37 */     super(paramRecipeMakerGUI);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  56 */     this
/*     */       
/*  58 */       .amountLog = new String[] { FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "Write in the chat the amount of output of this recipe.", new String[0]), FriendlyFeedbackProvider.quickForPlayer((FriendlyFeedbackPalette)FFPMMOItems.get(), "It must be an integer number, ex $e4$b.", new String[0]) };
/*     */     this.button = RecipeMakerGUI.rename(ItemFactory.of(paramItemStack.getType()).lore(SilentNumbers.chop("The amount of items produced every time the player crafts.", 65, "§7")).build(), "§cChoose Output Amount");
/*     */     ItemMeta itemMeta1 = Objects.<ItemMeta>requireNonNull(this.button.getItemMeta());
/*     */     ItemMeta itemMeta2 = Objects.<ItemMeta>requireNonNull(paramItemStack.getItemMeta());
/*     */     if (MythicLib.plugin.getVersion().isStrictlyHigher(new int[] { 1, 13 }) && itemMeta2.hasCustomModelData())
/*     */       itemMeta1.setCustomModelData(Integer.valueOf(itemMeta2.getCustomModelData())); 
/*     */     if (itemMeta2 instanceof LeatherArmorMeta)
/*     */       ((LeatherArmorMeta)itemMeta1).setColor(((LeatherArmorMeta)itemMeta2).getColor()); 
/*     */     if (itemMeta2 instanceof BannerMeta)
/*     */       ((BannerMeta)itemMeta1).setPatterns(((BannerMeta)itemMeta2).getPatterns()); 
/*     */     itemMeta1.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE, ItemFlag.HIDE_POTION_EFFECTS });
/*     */     this.button.setItemMeta(itemMeta1); } public boolean runPrimary() {
/*  70 */     (new StatEdition((EditionInventory)this.inv, ItemStats.CRAFTING, new Object[] { Integer.valueOf(2), this })).enable(this.amountLog);
/*     */ 
/*     */     
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void primaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {
/*  88 */     Integer integer = SilentNumbers.IntegerParse(paramString);
/*  89 */     if (integer == null) throw new IllegalArgumentException("Expected an integer number instead of $u" + paramString); 
/*  90 */     if (integer.intValue() > 64) throw new IllegalArgumentException("Max stack size is $e64$b, Minecraft doesnt support $u" + paramString); 
/*  91 */     if (integer.intValue() <= 0) throw new IllegalArgumentException("Min output stack size is $e0$b, you specified $u" + paramString);
/*     */ 
/*     */     
/*  94 */     getInv().getNameSection().set("amount", integer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean runSecondary() {
/* 106 */     getInv().getNameSection().set("amount", null);
/* 107 */     clickSFX();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     this.inv.registerTemplateEdition();
/*     */ 
/*     */     
/* 116 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOutputAmount() {
/* 122 */     return getInv().getNameSection().getInt("amount", 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void secondaryProcessInput(@NotNull String paramString, Object... paramVarArgs) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getButton() {
/* 140 */     ItemStack itemStack = this.button.clone();
/* 141 */     itemStack.setAmount(getOutputAmount());
/*     */ 
/*     */     
/* 144 */     return RecipeMakerGUI.addLore(itemStack, SilentNumbers.toArrayList((Object[])new String[] { "", ChatColor.YELLOW + "►" + " Right click to reset to 1.", ChatColor.YELLOW + "►" + " Left click to edit amount." }));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\gui\edition\recipe\rba\RBA_AmountOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */