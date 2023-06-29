/*     */ package net.Indyuce.mmoitems.api.item.build;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.util.AdventureUtils;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import net.Indyuce.mmoitems.api.event.GenerateLoreEvent;
/*     */ import net.Indyuce.mmoitems.api.event.ItemBuildEvent;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.stat.data.MaterialData;
/*     */ import net.Indyuce.mmoitems.stat.data.StringListData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.Previewable;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class ItemStackBuilder {
/*     */   @NotNull
/*     */   private final MMOItem mmoitem;
/*     */   private final ItemStack item;
/*     */   private final ItemMeta meta;
/*     */   private final LoreBuilder lore;
/*  44 */   private final List<ItemTag> tags = new ArrayList<>();
/*     */   
/*  46 */   private static final AttributeModifier fakeModifier = new AttributeModifier(UUID.fromString("87851e28-af12-43f6-898e-c62bde6bd0ec"), "mmoitemsDecoy", 0.0D, AttributeModifier.Operation.ADD_NUMBER);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String history_keyword = "HSTRY_";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStackBuilder(@NotNull MMOItem paramMMOItem) {
/*  57 */     this.mmoitem = paramMMOItem;
/*     */ 
/*     */     
/*  60 */     this
/*  61 */       .item = new ItemStack(paramMMOItem.hasData(ItemStats.MATERIAL) ? ((MaterialData)paramMMOItem.getData(ItemStats.MATERIAL)).getMaterial() : Material.DIAMOND_SWORD);
/*     */ 
/*     */     
/*  64 */     this
/*     */       
/*  66 */       .lore = new LoreBuilder(paramMMOItem.hasData(ItemStats.LORE_FORMAT) ? MMOItems.plugin.getFormats().getFormat(new String[] { paramMMOItem.getData(ItemStats.LORE_FORMAT).toString(), paramMMOItem.getType().getLoreFormat() }) : MMOItems.plugin.getFormats().getFormat(new String[] { paramMMOItem.getType().getLoreFormat() }));
/*     */ 
/*     */     
/*  69 */     this.meta = this.item.getItemMeta();
/*     */     
/*  71 */     this.meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
/*     */ 
/*     */     
/*  74 */     this.tags.add(new ItemTag("MMOITEMS_ITEM_TYPE", paramMMOItem.getType().getId()));
/*  75 */     this.tags.add(new ItemTag("MMOITEMS_ITEM_ID", paramMMOItem.getId()));
/*     */   }
/*     */   
/*     */   public LoreBuilder getLore() {
/*  79 */     return this.lore;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MMOItem getMMOItem() {
/*  84 */     return this.mmoitem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getItemStack() {
/*  96 */     return this.item;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public ItemMeta getMeta() {
/* 101 */     return this.meta;
/*     */   }
/*     */   
/*     */   public void addItemTag(List<ItemTag> paramList) {
/* 105 */     this.tags.addAll(paramList);
/*     */   }
/*     */   
/*     */   public void addItemTag(ItemTag... paramVarArgs) {
/* 109 */     this.tags.addAll(Arrays.asList(paramVarArgs));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTItem buildNBT() {
/* 118 */     return buildNBT(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTItem buildNBT(boolean paramBoolean) {
/* 129 */     MMOItem mMOItem = this.mmoitem.clone();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     if (!mMOItem.hasData(ItemStats.ENCHANTS)) {
/* 138 */       mMOItem.setData(ItemStats.ENCHANTS, ItemStats.ENCHANTS.getClearStatData());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 146 */     if (!mMOItem.hasData(ItemStats.NAME))
/* 147 */       mMOItem.setData(ItemStats.NAME, ItemStats.NAME.getClearStatData()); 
/* 148 */     if (mMOItem.getStatHistory(ItemStats.NAME) == null) {
/* 149 */       StatHistory.from(mMOItem, ItemStats.NAME);
/*     */     }
/*     */     
/* 152 */     for (ItemStat itemStat : mMOItem.getStats()) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 159 */         StatHistory statHistory = mMOItem.getStatHistory(itemStat);
/* 160 */         int i = this.mmoitem.getUpgradeLevel();
/*     */ 
/*     */         
/* 163 */         if (statHistory != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 169 */           mMOItem.setData(itemStat, statHistory.recalculate(i));
/*     */ 
/*     */           
/* 172 */           if (!statHistory.isClear())
/*     */           {
/*     */             
/* 175 */             addItemTag(new ItemTag[] { new ItemTag("HSTRY_" + itemStat.getId(), statHistory.toNBTString()) });
/*     */           }
/*     */         } 
/*     */         
/* 179 */         if (paramBoolean && itemStat instanceof Previewable) {
/*     */ 
/*     */           
/* 182 */           MMOItemTemplate mMOItemTemplate = MMOItems.plugin.getTemplates().getTemplate(mMOItem.getType(), mMOItem.getId());
/* 183 */           if (mMOItemTemplate == null) {
/* 184 */             throw new IllegalArgumentException("MMOItem $r" + mMOItem
/* 185 */                 .getType().getId() + " " + mMOItem.getId() + "$b doesn't exist.");
/*     */           }
/*     */ 
/*     */           
/* 189 */           ((Previewable)itemStat).whenPreviewed(this, mMOItem.getData(itemStat), (RandomStatData)mMOItemTemplate.getBaseItemData().get(itemStat));
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 194 */         itemStat.whenApplied(this, mMOItem.getData(itemStat));
/*     */ 
/*     */       
/*     */       }
/* 198 */       catch (IllegalArgumentException|NullPointerException illegalArgumentException) {
/*     */ 
/*     */         
/* 201 */         MMOItems.print(Level.WARNING, "An error occurred while trying to generate item '$f{0}$b' with stat '$f{1}$b': {2}", "ItemStackBuilder", new String[] { mMOItem
/* 202 */               .getId(), itemStat.getId(), illegalArgumentException.getMessage() });
/*     */       } 
/*     */     } 
/*     */     
/* 206 */     if (mMOItem.getType() == Type.GEM_STONE) {
/* 207 */       this.lore.insert("gem-stone-lore", new String[] { ItemStat.translate("gem-stone-lore") });
/*     */     }
/*     */     
/* 210 */     this.lore.insert("item-type", new String[] { ItemStat.translate("item-type").replace("{type}", 
/* 211 */             mMOItem.getStats().contains(ItemStats.DISPLAYED_TYPE) ? 
/* 212 */             mMOItem.getData(ItemStats.DISPLAYED_TYPE).toString() : mMOItem.getType().getName()) });
/*     */ 
/*     */     
/* 215 */     if (mMOItem.hasData(ItemStats.LORE)) {
/* 216 */       ArrayList<String> arrayList = new ArrayList();
/* 217 */       ((StringListData)mMOItem.getData(ItemStats.LORE)).getList().forEach(paramString -> paramList.add(this.lore.applySpecialPlaceholders(paramString)));
/* 218 */       this.lore.insert("lore", arrayList);
/*     */     } 
/*     */ 
/*     */     
/* 222 */     List<String> list1 = this.lore.getLore();
/* 223 */     List<String> list2 = this.lore.build();
/*     */     
/* 225 */     GenerateLoreEvent generateLoreEvent = new GenerateLoreEvent(mMOItem, this.lore, list2, list1);
/* 226 */     Bukkit.getPluginManager().callEvent((Event)generateLoreEvent);
/* 227 */     AdventureUtils.setLore(this.meta, generateLoreEvent.getParsedLore().stream()
/* 228 */         .map(paramString -> ChatColor.WHITE + paramString)
/* 229 */         .toList());
/* 230 */     if (this.meta.hasDisplayName()) {
/* 231 */       AdventureUtils.setDisplayName(this.meta, ChatColor.WHITE + this.meta.getDisplayName());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     JsonArray jsonArray = new JsonArray();
/* 238 */     Objects.requireNonNull(jsonArray); generateLoreEvent.getParsedLore().forEach(jsonArray::add);
/* 239 */     if (jsonArray.size() != 0) this.tags.add(new ItemTag("MMOITEMS_DYNAMIC_LORE", jsonArray.toString()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     this.meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, fakeModifier);
/*     */     
/* 248 */     this.item.setItemMeta(this.meta);
/*     */     
/* 250 */     return NBTItem.get(this.item).addTag(this.tags);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack build() {
/* 258 */     ItemBuildEvent itemBuildEvent = new ItemBuildEvent(buildNBT().toItem());
/* 259 */     Bukkit.getServer().getPluginManager().callEvent((Event)itemBuildEvent);
/* 260 */     return itemBuildEvent.getItemStack();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack buildSilently() {
/* 267 */     return buildNBT().toItem();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack build(boolean paramBoolean) {
/* 274 */     return buildNBT(paramBoolean).toItem();
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\build\ItemStackBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */