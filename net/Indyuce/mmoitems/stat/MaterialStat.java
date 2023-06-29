/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.util.EnumUtils;
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
/*     */ import net.Indyuce.mmoitems.stat.data.MaterialData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.ComponentBuilder;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class MaterialStat
/*     */   extends ItemStat<MaterialData, MaterialData> {
/*     */   public MaterialStat() {
/*  32 */     super("MATERIAL", VersionMaterial.GRASS_BLOCK.toMaterial(), "Material", new String[] { "Your item material." }, new String[] { "all" }, new Material[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialData whenInitialized(Object paramObject) {
/*  37 */     Validate.isTrue(paramObject instanceof String, "Must specify material name as string");
/*  38 */     return new MaterialData(Material.valueOf(((String)paramObject).toUpperCase().replace("-", "_").replace(" ", "_")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  43 */     (new StatEdition(paramEditionInventory, ItemStats.MATERIAL, new Object[0])).enable(new String[] { "Write in the chat the material you want." });
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  48 */     Optional<Material> optional = EnumUtils.getIfPresent(Material.class, paramString.toUpperCase().replace("-", "_").replace(" ", "_"));
/*  49 */     if (optional.isPresent()) {
/*  50 */       paramEditionInventory.getEditedSection().set("material", ((Material)optional.get()).name());
/*  51 */       paramEditionInventory.registerTemplateEdition();
/*  52 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Material successfully changed to " + ((Material)optional.get()).name() + ".");
/*     */     } else {
/*  54 */       paramEditionInventory.getPlayer().spigot().sendMessage((new ComponentBuilder("Invalid material! (Click for a list of valid materials)")).color(ChatColor.RED)
/*  55 */           .event(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html")).create());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull MaterialData paramMaterialData) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  68 */     paramReadMMOItem.setData(this, (StatData)new MaterialData(paramReadMMOItem.getNBT().getItem().getType()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<MaterialData> paramOptional) {
/*  73 */     paramList.add(ChatColor.GRAY + "Current Value: " + (
/*  74 */         paramOptional.isPresent() ? (
/*  75 */         ChatColor.GREEN + MMOUtils.caseOnWords(((MaterialData)paramOptional.get()).getMaterial().name().toLowerCase().replace("_", " "))) : (
/*  76 */         ChatColor.RED + "None")));
/*     */     
/*  78 */     paramList.add("");
/*  79 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to change this value.");
/*  80 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove this value.");
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MaterialData getClearStatData() {
/*  86 */     return new MaterialData(Material.IRON_ORE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull MaterialData paramMaterialData) {
/*  95 */     return new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MaterialData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 103 */     return null;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\MaterialStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */