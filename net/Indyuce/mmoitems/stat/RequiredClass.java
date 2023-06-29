/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.regex.Pattern;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.StringListStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RequiredClass
/*     */   extends StringListStat
/*     */   implements ItemRestriction, GemStoneStat
/*     */ {
/*     */   public RequiredClass() {
/*  40 */     super("REQUIRED_CLASS", VersionMaterial.WRITABLE_BOOK.toMaterial(), "Required Class", new String[] { "The class you need to", "profess to use your item." }, new String[] { "!block", "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringListData whenInitialized(Object paramObject) {
/*  47 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/*  48 */     return new StringListData((List)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  53 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  54 */       (new StatEdition(paramEditionInventory, (ItemStat)this, new Object[0])).enable(new String[] { "Write in the chat the class you want your item to support." });
/*     */     }
/*  56 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  57 */       paramEditionInventory.getEditedSection().getKeys(false).contains("required-class")) {
/*  58 */       List<String> list = paramEditionInventory.getEditedSection().getStringList("required-class");
/*  59 */       if (list.size() < 1) {
/*     */         return;
/*     */       }
/*  62 */       String str = list.get(list.size() - 1);
/*  63 */       list.remove(str);
/*  64 */       paramEditionInventory.getEditedSection().set(getPath(), (list.size() == 0) ? null : list);
/*  65 */       paramEditionInventory.registerTemplateEdition();
/*  66 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + str + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  74 */     List<String> list = paramEditionInventory.getEditedSection().getKeys(false).contains("required-class") ? paramEditionInventory.getEditedSection().getStringList("required-class") : new ArrayList();
/*  75 */     list.add(paramString);
/*  76 */     paramEditionInventory.getEditedSection().set(getPath(), list);
/*  77 */     paramEditionInventory.registerTemplateEdition();
/*  78 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Required Class successfully added.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  85 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  86 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/*  87 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/*  90 */     StringListData stringListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/*  93 */     if (stringListData != null) paramReadMMOItem.setData((ItemStat)this, (StatData)stringListData);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StringListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 101 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 104 */     if (itemTag != null)
/*     */     {
/*     */       
/* 107 */       return new StringListData(((String)itemTag.getValue()).split(Pattern.quote(", ")));
/*     */     }
/*     */ 
/*     */     
/* 111 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/* 117 */     if (paramOptional.isPresent()) {
/* 118 */       paramList.add(ChatColor.GRAY + "Current Value:");
/* 119 */       StringListData stringListData = paramOptional.get();
/* 120 */       stringListData.getList().forEach(paramString -> paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + paramString));
/*     */     } else {
/*     */       
/* 123 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/* 125 */     paramList.add("");
/* 126 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a class.");
/* 127 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last class.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringListData paramStringListData) {
/* 135 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 138 */     arrayList.add(new ItemTag(getNBTPath(), String.join(", ", paramStringListData.getList())));
/*     */ 
/*     */     
/* 141 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 146 */     String str = paramNBTItem.getString(ItemStats.REQUIRED_CLASS.getNBTPath());
/* 147 */     if (!str.equals("") && !hasRightClass(paramRPGPlayer, str) && !paramRPGPlayer.getPlayer().hasPermission("mmoitems.bypass.class")) {
/* 148 */       if (paramBoolean) {
/* 149 */         Message.WRONG_CLASS.format(ChatColor.RED, new String[0]).send(paramRPGPlayer.getPlayer());
/* 150 */         paramRPGPlayer.getPlayer().playSound(paramRPGPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/*     */       } 
/* 152 */       return false;
/*     */     } 
/* 154 */     return true;
/*     */   }
/*     */   
/*     */   private boolean hasRightClass(RPGPlayer paramRPGPlayer, String paramString) {
/* 158 */     String str = ChatColor.stripColor(paramRPGPlayer.getClassName());
/*     */     
/* 160 */     for (String str1 : paramString.split(Pattern.quote(", "))) {
/* 161 */       if (str1.equalsIgnoreCase(str))
/* 162 */         return true; 
/*     */     } 
/* 164 */     return false;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\RequiredClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */