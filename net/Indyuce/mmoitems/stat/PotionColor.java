/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.ColorData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.NotImplementedException;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.meta.PotionMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PotionColor extends ItemStat<ColorData, ColorData> {
/*     */   public PotionColor() {
/*  30 */     super("POTION_COLOR", Material.POTION, "Potion Color", new String[] { "The color of your potion.", "(Doesn't impact the effects)." }, new String[] { "all" }, new Material[] { Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION, Material.TIPPED_ARROW });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ColorData whenInitialized(Object paramObject) {
/*  37 */     Validate.isTrue(paramObject instanceof String, "Must specify a string");
/*  38 */     return new ColorData((String)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  43 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  44 */       (new StatEdition(paramEditionInventory, ItemStats.POTION_COLOR, new Object[0])).enable(new String[] { "Write in the chat the RGB color you want.", ChatColor.AQUA + "Format: {Red} {Green} {Blue}" });
/*     */     }
/*     */     
/*  47 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  48 */       paramEditionInventory.getEditedSection().set("potion-color", null);
/*  49 */       paramEditionInventory.registerTemplateEdition();
/*  50 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed Potion Color.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  56 */     String[] arrayOfString = paramString.split(" ");
/*  57 */     Validate.isTrue((arrayOfString.length == 3), "Use this format: {Red} {Green} {Blue}. Example: '75 0 130' stands for Purple.");
/*     */     
/*  59 */     for (String str : arrayOfString) {
/*  60 */       int i = Integer.parseInt(str);
/*  61 */       Validate.isTrue((i >= 0 && i < 256), "Color must be between 0 and 255");
/*     */     } 
/*     */     
/*  64 */     paramEditionInventory.getEditedSection().set("potion-color", paramString);
/*  65 */     paramEditionInventory.registerTemplateEdition();
/*  66 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Potion Color successfully changed to " + paramString + ".");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  71 */     if (!(paramReadMMOItem.getNBT().getItem().getItemMeta() instanceof PotionMeta)) {
/*     */       return;
/*     */     }
/*  74 */     Color color = ((PotionMeta)paramReadMMOItem.getNBT().getItem().getItemMeta()).getColor();
/*  75 */     if (color != null) {
/*  76 */       paramReadMMOItem.setData(this, (StatData)new ColorData(color));
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ColorData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/*  82 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<ColorData> paramOptional) {
/*  88 */     paramList.add(paramOptional.isPresent() ? (ChatColor.GREEN + ((ColorData)paramOptional.get()).toString()) : (ChatColor.RED + "Uncolored"));
/*     */     
/*  90 */     paramList.add("");
/*  91 */     paramList.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  92 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the potion color.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ColorData getClearStatData() {
/*  98 */     return new ColorData(0, 0, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull ColorData paramColorData) {
/* 103 */     if (paramItemStackBuilder.getItemStack().getType().name().contains("POTION") || paramItemStackBuilder.getItemStack().getType() == Material.TIPPED_ARROW) {
/* 104 */       ((PotionMeta)paramItemStackBuilder.getMeta()).setColor(paramColorData.getColor());
/*     */     }
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull ColorData paramColorData) {
/* 110 */     throw new NotImplementedException();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\PotionColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */