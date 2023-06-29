/*     */ package net.Indyuce.mmoitems.stat.type;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.util.StatChoice;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public abstract class ChooseStat
/*     */   extends StringStat
/*     */ {
/*  35 */   private final List<StatChoice> choices = new ArrayList<>();
/*     */   
/*     */   public ChooseStat(String paramString1, Material paramMaterial, String paramString2, String[] paramArrayOfString1, String[] paramArrayOfString2, Material... paramVarArgs) {
/*  38 */     super(paramString1, paramMaterial, paramString2, paramArrayOfString1, paramArrayOfString2, paramVarArgs);
/*     */   }
/*     */   
/*     */   public void addChoices(StatChoice... paramVarArgs) {
/*  42 */     this.choices.addAll(Arrays.asList(paramVarArgs));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public StatChoice getChoice(String paramString) {
/*  47 */     for (StatChoice statChoice : this.choices) {
/*  48 */       if (statChoice.getId().equals(paramString))
/*  49 */         return statChoice; 
/*  50 */     }  return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  55 */     Validate.isTrue((this.choices.size() > 0), "Invalid choice-based stat '" + getId() + ": no options to choose from.");
/*     */ 
/*     */     
/*  58 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*     */ 
/*     */       
/*  61 */       paramEditionInventory.getEditedSection().set(getPath(), null);
/*  62 */       paramEditionInventory.registerTemplateEdition();
/*     */ 
/*     */       
/*  65 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + getName() + ".");
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  70 */       StatChoice statChoice = getChoice(paramEditionInventory.getEditedSection().getString(getPath()));
/*     */ 
/*     */       
/*  73 */       byte b = (statChoice != null) ? Math.max(0, this.choices.indexOf(statChoice)) : 0;
/*     */ 
/*     */       
/*  76 */       if (++b >= this.choices.size()) b = 0;
/*     */ 
/*     */       
/*  79 */       statChoice = this.choices.get(b);
/*     */ 
/*     */       
/*  82 */       paramEditionInventory.getEditedSection().set(getPath(), statChoice.getId());
/*  83 */       paramEditionInventory.registerTemplateEdition();
/*     */ 
/*     */       
/*  86 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + getName() + " successfully changed to " + statChoice + ChatColor.GRAY + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringData> paramOptional) {
/*  92 */     Validate.isTrue((this.choices.size() > 0), "Invalid choice-based stat '" + getId() + ": no options to choose from.");
/*     */ 
/*     */     
/*  95 */     StatChoice statChoice = paramOptional.isPresent() ? getChoice(((StringData)paramOptional.get()).toString()) : this.choices.get(0);
/*  96 */     paramList.add(ChatColor.GRAY + "Current Value: " + (paramOptional.isPresent() ? (String)ChatColor.GREEN : (String)ChatColor.RED) + statChoice);
/*     */ 
/*     */     
/*  99 */     for (String str : SilentNumbers.chop(statChoice.getHint(), 50, "")) {
/* 100 */       paramList.add(ChatColor.GRAY + " " + str);
/*     */     }
/* 102 */     paramList.add("");
/* 103 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to return to default value.");
/* 104 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to cycle through the available options:");
/* 105 */     for (StatChoice statChoice1 : this.choices) {
/*     */ 
/*     */       
/* 108 */       String str = statChoice1.equals(statChoice) ? (ChatColor.RED.toString() + ChatColor.BOLD) : ChatColor.GOLD.toString();
/* 109 */       paramList.add(str + "  " + "▸" + " " + ChatColor.GRAY + statChoice1.getId());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\type\ChooseStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */