/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
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
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class DyeColor
/*     */   extends ItemStat<ColorData, ColorData>
/*     */ {
/*     */   public DyeColor() {
/*  34 */     super("DYE_COLOR", VersionMaterial.RED_DYE.toMaterial(), "Dye Color", new String[] { "The color of your item", "(for dyeable items).", "In RGB." }, new String[] { "all" }, new Material[] { Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS, VersionMaterial.LEATHER_HORSE_ARMOR
/*     */           
/*  36 */           .toMaterial() });
/*     */   }
/*     */ 
/*     */   
/*     */   public ColorData whenInitialized(Object paramObject) {
/*  41 */     Validate.isTrue(paramObject instanceof String, "Must specify a string");
/*  42 */     return new ColorData((String)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  47 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  48 */       (new StatEdition(paramEditionInventory, ItemStats.DYE_COLOR, new Object[0])).enable(new String[] { "Write in the chat the RGB color you want.", ChatColor.AQUA + "Format: {Red} {Green} {Blue}" });
/*     */     }
/*     */     
/*  51 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  52 */       paramEditionInventory.getEditedSection().set("dye-color", null);
/*  53 */       paramEditionInventory.registerTemplateEdition();
/*  54 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed Dye Color.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  60 */     String[] arrayOfString = paramString.split(" ");
/*  61 */     Validate.isTrue((arrayOfString.length == 3), "Use this format: {Red} {Green} {Blue}.");
/*  62 */     for (String str : arrayOfString) {
/*  63 */       int i = Integer.parseInt(str);
/*  64 */       Validate.isTrue((i >= 0 && i < 256), "Color must be between 0 and 255");
/*     */     } 
/*     */     
/*  67 */     paramEditionInventory.getEditedSection().set("dye-color", paramString);
/*  68 */     paramEditionInventory.registerTemplateEdition();
/*  69 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Dye Color successfully changed to " + paramString + ".");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<ColorData> paramOptional) {
/*  74 */     paramList.add(ChatColor.GRAY + "Current Value: " + (paramOptional.isPresent() ? (ChatColor.GREEN + ((ColorData)paramOptional.get()).toString()) : (ChatColor.RED + "None")));
/*  75 */     paramList.add("");
/*  76 */     paramList.add(ChatColor.YELLOW + "►" + " Click to change this value.");
/*  77 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the dye color.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ColorData getClearStatData() {
/*  83 */     return new ColorData(0, 0, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  90 */     ItemMeta itemMeta = paramReadMMOItem.getNBT().getItem().getItemMeta();
/*     */     
/*  92 */     if (itemMeta instanceof LeatherArmorMeta) {
/*     */ 
/*     */       
/*  95 */       ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */       
/*  98 */       arrayList.add(new ItemTag(getNBTPath(), ((LeatherArmorMeta)itemMeta).getColor()));
/*     */ 
/*     */       
/* 101 */       ColorData colorData = getLoadedNBT(arrayList);
/*     */ 
/*     */       
/* 104 */       if (colorData != null) paramReadMMOItem.setData(this, (StatData)colorData);
/*     */     
/*     */     } 
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
/*     */   @Nullable
/*     */   public ColorData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 119 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 122 */     if (itemTag != null) {
/*     */ 
/*     */       
/* 125 */       Color color = (Color)itemTag.getValue();
/*     */ 
/*     */       
/* 128 */       return new ColorData(color);
/*     */     } 
/*     */     
/* 131 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull ColorData paramColorData) {
/* 138 */     if (paramItemStackBuilder.getMeta() instanceof LeatherArmorMeta)
/*     */     {
/*     */       
/* 141 */       ((LeatherArmorMeta)paramItemStackBuilder.getMeta()).setColor(paramColorData.getColor());
/*     */     }
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
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull ColorData paramColorData) {
/* 155 */     return new ArrayList<>();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\DyeColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */