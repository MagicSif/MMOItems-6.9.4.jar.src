/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
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
/*     */ public class Permission
/*     */   extends StringListStat
/*     */   implements ItemRestriction {
/*     */   public Permission() {
/*  36 */     super("PERMISSION", VersionMaterial.OAK_SIGN.toMaterial(), "Permission", new String[] { "The permission needed to use this item." }, new String[] { "!block", "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public StringListData whenInitialized(Object paramObject) {
/*  43 */     Validate.isTrue(paramObject instanceof List, "Must specify a string list");
/*  44 */     return new StringListData((List)paramObject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  49 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_ALL) {
/*  50 */       (new StatEdition(paramEditionInventory, ItemStats.PERMISSION, new Object[0])).enable(new String[] { "Write in the chat the permission you want your item to require." });
/*     */     }
/*  52 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF && 
/*  53 */       paramEditionInventory.getEditedSection().contains("permission")) {
/*  54 */       List<String> list = paramEditionInventory.getEditedSection().getStringList("permission");
/*  55 */       if (list.size() < 1) {
/*     */         return;
/*     */       }
/*  58 */       String str = list.get(list.size() - 1);
/*  59 */       list.remove(str);
/*  60 */       paramEditionInventory.getEditedSection().set("permission", (list.size() == 0) ? null : list);
/*  61 */       paramEditionInventory.registerTemplateEdition();
/*  62 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + str + ".");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  69 */     Validate.isTrue(!paramString.contains("|"), "Your perm node must not contain any | symbol.");
/*  70 */     List<String> list = paramEditionInventory.getEditedSection().contains("permission") ? paramEditionInventory.getEditedSection().getStringList("permission") : new ArrayList();
/*  71 */     list.add(paramString);
/*  72 */     paramEditionInventory.getEditedSection().set("permission", list);
/*  73 */     paramEditionInventory.registerTemplateEdition();
/*  74 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Permission successfully added.");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<StringListData> paramOptional) {
/*  80 */     if (paramOptional.isPresent()) {
/*  81 */       paramList.add(ChatColor.GRAY + "Current Value:");
/*  82 */       StringListData stringListData = paramOptional.get();
/*  83 */       stringListData.getList().forEach(paramString -> paramList.add(ChatColor.GRAY + "* " + ChatColor.GREEN + paramString));
/*     */     } else {
/*     */       
/*  86 */       paramList.add(ChatColor.GRAY + "Current Value: " + ChatColor.RED + "None");
/*     */     } 
/*  88 */     paramList.add("");
/*  89 */     paramList.add(ChatColor.YELLOW + "►" + " Click to add a compatible permission.");
/*  90 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove the last permission.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull StringListData paramStringListData) {
/*  98 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*     */ 
/*     */     
/* 101 */     arrayList.add(new ItemTag(getNBTPath(), String.join("|", paramStringListData.getList())));
/*     */ 
/*     */     
/* 104 */     return arrayList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/* 111 */     ArrayList<ItemTag> arrayList = new ArrayList();
/* 112 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath())) {
/* 113 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING));
/*     */     }
/*     */     
/* 116 */     StringListData stringListData = getLoadedNBT(arrayList);
/*     */ 
/*     */     
/* 119 */     if (stringListData != null) paramReadMMOItem.setData((ItemStat)this, (StatData)stringListData);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public StringListData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 127 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*     */ 
/*     */     
/* 130 */     if (itemTag != null) {
/*     */ 
/*     */       
/* 133 */       ArrayList arrayList = new ArrayList(Arrays.asList((Object[])((String)itemTag.getValue()).split("\\|")));
/*     */ 
/*     */       
/* 136 */       return new StringListData(arrayList);
/*     */     } 
/*     */     
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 144 */     String str = paramNBTItem.getString("MMOITEMS_PERMISSION");
/* 145 */     if (!str.equals("") && !paramRPGPlayer.getPlayer().hasPermission("mmoitems.bypass.item") && MMOItems.plugin
/* 146 */       .getConfig().getBoolean("permissions.items")) {
/* 147 */       String[] arrayOfString = str.split("\\|");
/* 148 */       for (String str1 : arrayOfString) {
/* 149 */         if (!paramRPGPlayer.getPlayer().hasPermission(str1)) {
/* 150 */           if (paramBoolean) {
/* 151 */             Message.NOT_ENOUGH_PERMS.format(ChatColor.RED, new String[0]).send(paramRPGPlayer.getPlayer());
/* 152 */             paramRPGPlayer.getPlayer().playSound(paramRPGPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/*     */           } 
/* 154 */           return false;
/*     */         } 
/*     */       } 
/* 157 */     }  return true;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Permission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */