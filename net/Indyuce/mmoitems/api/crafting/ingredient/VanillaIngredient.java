/*     */ package net.Indyuce.mmoitems.api.crafting.ingredient;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.MMOLineConfig;
/*     */ import io.lumine.mythic.lib.api.crafting.uimanager.ProvidedUIFilter;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackCategory;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackMessage;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackPalette;
/*     */ import io.lumine.mythic.lib.api.util.ui.FriendlyFeedbackProvider;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import net.Indyuce.mmoitems.api.crafting.ingredient.inventory.VanillaPlayerIngredient;
/*     */ import net.Indyuce.mmoitems.api.util.message.FFPMMOItems;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class VanillaIngredient extends Ingredient<VanillaPlayerIngredient> {
/*     */   @NotNull
/*     */   final ProvidedUIFilter filter;
/*     */   @NotNull
/*     */   final Material material;
/*     */   
/*     */   @NotNull
/*  23 */   public ProvidedUIFilter getFilter() { return this.filter; } @NotNull
/*     */   final String display; @Nullable
/*     */   final String displayName; @NotNull
/*  26 */   public Material getMaterial() { return this.material; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean vanillaBackward = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VanillaIngredient(MMOLineConfig paramMMOLineConfig) {
/*  42 */     super("vanilla", paramMMOLineConfig);
/*     */     
/*  44 */     FriendlyFeedbackProvider friendlyFeedbackProvider = new FriendlyFeedbackProvider((FriendlyFeedbackPalette)FFPMMOItems.get());
/*     */ 
/*     */     
/*  47 */     paramMMOLineConfig.validate(new String[] { "type" });
/*  48 */     String str = paramMMOLineConfig.getString("type", "");
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  53 */     if (str.contains(" ")) {
/*  54 */       this.vanillaBackward = false;
/*     */ 
/*     */ 
/*     */       
/*  58 */       ProvidedUIFilter providedUIFilter = UIFilterManager.getUIFilter(str, friendlyFeedbackProvider);
/*  59 */       if (providedUIFilter == null)
/*     */       {
/*     */         
/*  62 */         throw new IllegalArgumentException(SilentNumbers.collapseList(SilentNumbers.transcribeList(friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR), paramObject -> (paramObject instanceof FriendlyFeedbackMessage) ? ((FriendlyFeedbackMessage)paramObject).forConsole((FriendlyFeedbackPalette)FFPMMOItems.get()) : ""), ""));
/*     */       }
/*     */ 
/*     */       
/*  66 */       this.filter = providedUIFilter;
/*     */ 
/*     */       
/*  69 */       if (!this.filter.isValid(friendlyFeedbackProvider))
/*     */       {
/*     */         
/*  72 */         throw new IllegalArgumentException(SilentNumbers.collapseList(SilentNumbers.transcribeList(friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR), paramObject -> (paramObject instanceof FriendlyFeedbackMessage) ? ((FriendlyFeedbackMessage)paramObject).forConsole((FriendlyFeedbackPalette)FFPMMOItems.get()) : ""), ""));
/*     */       }
/*     */ 
/*     */       
/*  76 */       setAmount(this.filter.getAmount(getAmount()));
/*  77 */       this.filter.setAmount(Integer.valueOf(getAmount()));
/*     */ 
/*     */       
/*  80 */       this.display = paramMMOLineConfig.getString("display", findName());
/*  81 */       this.material = Material.STONE;
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/*  88 */       this.material = Material.valueOf(str.toUpperCase().replace("-", "_").replace(" ", "_"));
/*     */ 
/*     */       
/*  91 */       this.filter = new ProvidedUIFilter((UIFilter)VanillaUIFilter.get(), this.material.toString(), "0");
/*  92 */       this.filter.setAmount(Integer.valueOf(getAmount()));
/*     */ 
/*     */       
/*  95 */       this.display = paramMMOLineConfig.getString("display", MMOUtils.caseOnWords(this.material.toString().toLowerCase().replace("_", " ")));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     if (this.filter.getItemStack(null) == null)
/*     */     {
/*     */       
/* 104 */       throw new IllegalArgumentException(SilentNumbers.collapseList(SilentNumbers.transcribeList(friendlyFeedbackProvider.getFeedbackOf(FriendlyFeedbackCategory.ERROR), paramObject -> (paramObject instanceof FriendlyFeedbackMessage) ? ((FriendlyFeedbackMessage)paramObject).forConsole((FriendlyFeedbackPalette)FFPMMOItems.get()) : ""), ""));
/*     */     }
/*     */ 
/*     */     
/* 108 */     this.displayName = paramMMOLineConfig.contains("name") ? paramMMOLineConfig.getString("name") : null;
/*     */   }
/*     */   
/*     */   public String getKey() {
/* 112 */     return "vanilla:" + (this.vanillaBackward ? this.material.toString().toLowerCase() : this.filter.toString().toLowerCase().replace(" ", "__")) + "_" + this.displayName;
/*     */   } public String formatDisplay(String paramString) {
/* 114 */     return paramString.replace("#item#", this.display).replace("#amount#", String.valueOf(getAmount()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(VanillaPlayerIngredient paramVanillaPlayerIngredient) {
/* 120 */     if (this.vanillaBackward) {
/*     */       
/* 122 */       if (paramVanillaPlayerIngredient.getType() != this.material)
/*     */       {
/* 124 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 131 */       return (paramVanillaPlayerIngredient.getDisplayName() != null) ? paramVanillaPlayerIngredient.getDisplayName().equals(this.displayName) : ((this.displayName == null));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     return this.filter.matches(paramVanillaPlayerIngredient.getSourceItem(), true, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack generateItemStack(@NotNull RPGPlayer paramRPGPlayer, boolean paramBoolean) {
/* 146 */     ItemStack itemStack = this.filter.getItemStack(null);
/* 147 */     itemStack.setAmount(getAmount());
/*     */ 
/*     */     
/* 150 */     if (this.displayName != null) {
/* 151 */       ItemMeta itemMeta = itemStack.getItemMeta();
/* 152 */       itemMeta.setDisplayName(MythicLib.plugin.parseColors(this.displayName));
/* 153 */       itemStack.setItemMeta(itemMeta);
/*     */     } 
/*     */ 
/*     */     
/* 157 */     return itemStack;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   String findName() {
/* 163 */     return SilentNumbers.getItemName(this.filter.getParent().getDisplayStack(this.filter.getArgument(), this.filter.getData(), null), false);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\crafting\ingredient\VanillaIngredient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */