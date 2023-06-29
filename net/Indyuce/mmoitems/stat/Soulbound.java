/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.google.gson.JsonParser;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.regex.Pattern;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.SoulboundData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.InternalStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.NotImplementedException;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class Soulbound
/*     */   extends ItemStat<RandomStatData<SoulboundData>, SoulboundData>
/*     */   implements InternalStat, ItemRestriction
/*     */ {
/*     */   public Soulbound() {
/*  40 */     super("SOULBOUND", VersionMaterial.ENDER_EYE.toMaterial(), "Soulbound", new String[0], new String[] { "all" }, new org.bukkit.Material[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public RandomStatData<SoulboundData> whenInitialized(Object paramObject) {
/*  46 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  51 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  56 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<RandomStatData<SoulboundData>> paramOptional) {
/*  61 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull SoulboundData paramSoulboundData) {
/*  66 */     paramItemStackBuilder.addItemTag(getAppliedNBT(paramSoulboundData));
/*     */ 
/*     */     
/*  69 */     String str = Message.SOULBOUND_ITEM_LORE.getUpdated().replace("#player#", paramSoulboundData.getName()).replace("#level#", 
/*  70 */         MMOUtils.intToRoman(paramSoulboundData.getLevel()));
/*  71 */     paramItemStackBuilder.getLore().insert("soulbound", str.split(Pattern.quote("//")));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull SoulboundData paramSoulboundData) {
/*  77 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  78 */     arrayList.add(new ItemTag(getNBTPath(), paramSoulboundData.toJson().toString()));
/*  79 */     return arrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*  84 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  85 */     if (paramReadMMOItem.getNBT().hasTag(getNBTPath()))
/*  86 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramReadMMOItem.getNBT(), SupportedNBTTagValues.STRING)); 
/*  87 */     SoulboundData soulboundData = getLoadedNBT(arrayList);
/*  88 */     if (soulboundData != null) paramReadMMOItem.setData(this, (StatData)soulboundData);
/*     */   
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public SoulboundData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/*  94 */     ItemTag itemTag = ItemTag.getTagAtPath(getNBTPath(), paramArrayList);
/*  95 */     if (itemTag != null) {
/*     */       
/*     */       try {
/*     */         
/*  99 */         return new SoulboundData((new JsonParser()).parse((String)itemTag.getValue()).getAsJsonObject());
/*     */       }
/* 101 */       catch (JsonSyntaxException|IllegalStateException jsonSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 107 */     return null;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public SoulboundData getClearStatData() {
/* 112 */     return new SoulboundData(UUID.fromString("df930b7b-a84d-4f76-90ac-33be6a5b6c88"), "gunging", 0);
/*     */   }
/*     */   
/*     */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/* 116 */     if (paramNBTItem.hasTag(ItemStats.SOULBOUND.getNBTPath()) && !paramNBTItem.getString(ItemStats.SOULBOUND.getNBTPath()).contains(paramRPGPlayer.getPlayer().getUniqueId().toString()) && !paramRPGPlayer.getPlayer().hasPermission("mmoitems.bypass.soulbound")) {
/* 117 */       if (paramBoolean) {
/* 118 */         int i = (new JsonParser()).parse(paramNBTItem.getString(ItemStats.SOULBOUND.getNBTPath())).getAsJsonObject().get("Level").getAsInt();
/* 119 */         Message.SOULBOUND_RESTRICTION.format(ChatColor.RED, new String[0]).send(paramRPGPlayer.getPlayer());
/* 120 */         paramRPGPlayer.getPlayer().playSound(paramRPGPlayer.getPlayer().getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0F, 1.5F);
/* 121 */         paramRPGPlayer.getPlayer()
/* 122 */           .damage((MMOItems.plugin.getLanguage()).soulboundBaseDamage + i * (MMOItems.plugin.getLanguage()).soulboundPerLvlDamage);
/*     */       } 
/* 124 */       return false;
/*     */     } 
/* 126 */     return true;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Soulbound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */