/*     */ package net.Indyuce.mmoitems.api.item.build;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.ItemTier;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
/*     */ import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
/*     */ import net.Indyuce.mmoitems.api.item.template.NameModifier;
/*     */ import net.Indyuce.mmoitems.api.item.template.TemplateModifier;
/*     */ import net.Indyuce.mmoitems.stat.data.DoubleData;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.Mergeable;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import net.Indyuce.mmoitems.stat.type.NameData;
/*     */ import net.Indyuce.mmoitems.stat.type.StatHistory;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class MMOItemBuilder
/*     */ {
/*     */   private final MMOItem mmoitem;
/*     */   private final int level;
/*     */   private final ItemTier tier;
/*  33 */   private final HashMap<UUID, NameModifier> nameModifiers = new HashMap<>();
/*     */   
/*     */   public MMOItemBuilder(MMOItemTemplate paramMMOItemTemplate, int paramInt, @Nullable ItemTier paramItemTier) {
/*  36 */     this(paramMMOItemTemplate, paramInt, paramItemTier, false);
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
/*     */   public MMOItemBuilder(MMOItemTemplate paramMMOItemTemplate, int paramInt, @Nullable ItemTier paramItemTier, boolean paramBoolean) {
/*  51 */     this.level = paramInt;
/*  52 */     this.tier = paramItemTier;
/*     */ 
/*     */     
/*  55 */     paramItemTier = (paramItemTier != null) ? paramItemTier : (paramMMOItemTemplate.getBaseItemData().containsKey(ItemStats.TIER) ? MMOItems.plugin.getTiers().get(((RandomStatData)paramMMOItemTemplate.getBaseItemData().get(ItemStats.TIER)).toString()) : null);
/*     */ 
/*     */     
/*  58 */     double d = ((paramItemTier != null && paramItemTier.hasCapacity()) ? paramItemTier.getModifierCapacity() : (MMOItems.plugin.getLanguage()).defaultItemCapacity).calculate(paramInt);
/*  59 */     this.mmoitem = new MMOItem(paramMMOItemTemplate.getType(), paramMMOItemTemplate.getId());
/*     */ 
/*     */     
/*  62 */     paramMMOItemTemplate.getBaseItemData().forEach((paramItemStat, paramRandomStatData) -> applyData(paramItemStat, paramRandomStatData.randomize(this)));
/*     */     
/*  64 */     if (paramItemTier != null)
/*  65 */       this.mmoitem.setData(ItemStats.TIER, (StatData)new StringData(paramItemTier.getId())); 
/*  66 */     if (paramInt > 0) {
/*  67 */       this.mmoitem.setData(ItemStats.ITEM_LEVEL, (StatData)new DoubleData(paramInt));
/*     */     }
/*     */     
/*  70 */     if (!paramBoolean)
/*  71 */       for (TemplateModifier templateModifier : rollModifiers(paramMMOItemTemplate)) {
/*     */         
/*  73 */         if (!templateModifier.rollChance() || templateModifier.getWeight() > d) {
/*     */           continue;
/*     */         }
/*     */         
/*  77 */         UUID uUID = UUID.randomUUID();
/*     */         
/*  79 */         d -= templateModifier.getWeight();
/*  80 */         if (templateModifier.hasNameModifier()) {
/*  81 */           addModifier(templateModifier.getNameModifier(), uUID);
/*     */         }
/*     */         
/*  84 */         for (ItemStat itemStat : templateModifier.getItemData().keySet())
/*  85 */           addModifierData(itemStat, ((RandomStatData)templateModifier.getItemData().get(itemStat)).randomize(this), uUID); 
/*     */       }  
/*     */   }
/*     */   
/*     */   public int getLevel() {
/*  90 */     return this.level;
/*     */   }
/*     */   
/*     */   public ItemTier getTier() {
/*  94 */     return this.tier;
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
/*     */   public MMOItem build() {
/* 106 */     if (!this.nameModifiers.isEmpty()) {
/*     */ 
/*     */       
/* 109 */       StatHistory statHistory = StatHistory.from(this.mmoitem, ItemStats.NAME);
/* 110 */       if (!this.mmoitem.hasData(ItemStats.NAME)) {
/* 111 */         this.mmoitem.setData(ItemStats.NAME, (StatData)new NameData("Item"));
/*     */       }
/*     */       
/* 114 */       for (UUID uUID : this.nameModifiers.keySet()) {
/*     */ 
/*     */         
/* 117 */         NameModifier nameModifier = this.nameModifiers.get(uUID);
/* 118 */         NameData nameData = new NameData("");
/*     */ 
/*     */         
/* 121 */         if (nameModifier.getType() == NameModifier.ModifierType.PREFIX) {
/* 122 */           nameData.addPrefix(nameModifier.getFormat());
/*     */         }
/* 124 */         if (nameModifier.getType() == NameModifier.ModifierType.SUFFIX) {
/* 125 */           nameData.addSuffix(nameModifier.getFormat());
/*     */         }
/*     */ 
/*     */         
/* 129 */         statHistory.registerModifierBonus(uUID, (StatData)nameData);
/*     */       } 
/*     */ 
/*     */       
/* 133 */       this.mmoitem.setData(ItemStats.NAME, statHistory.recalculate(this.mmoitem.getUpgradeLevel()));
/*     */     } 
/*     */     
/* 136 */     return this.mmoitem;
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
/*     */   public void applyData(ItemStat paramItemStat, StatData paramStatData) {
/* 149 */     if (this.mmoitem.hasData(paramItemStat) && paramStatData instanceof Mergeable) {
/*     */       
/* 151 */       ((Mergeable)this.mmoitem.getData(paramItemStat)).merge(paramStatData);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 156 */       this.mmoitem.setData(paramItemStat, paramStatData);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addModifierData(@NotNull ItemStat paramItemStat, @NotNull StatData paramStatData, @NotNull UUID paramUUID) {
/* 167 */     if (paramItemStat.getClearStatData() instanceof Mergeable) {
/* 168 */       StatHistory.from(this.mmoitem, paramItemStat).registerModifierBonus(paramUUID, paramStatData);
/*     */     } else {
/* 170 */       this.mmoitem.setData(paramItemStat, paramStatData);
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
/*     */   
/*     */   public void addModifier(@NotNull NameModifier paramNameModifier, @NotNull UUID paramUUID) {
/* 184 */     Iterator<NameModifier> iterator = this.nameModifiers.values().iterator();
/* 185 */     while (iterator.hasNext()) {
/* 186 */       NameModifier nameModifier = iterator.next();
/* 187 */       if (nameModifier.getType() == paramNameModifier.getType()) {
/* 188 */         if (nameModifier.getPriority() > paramNameModifier.getPriority())
/* 189 */           return;  if (nameModifier.getPriority() < paramNameModifier.getPriority()) iterator.remove();
/*     */       
/*     */       } 
/*     */     } 
/* 193 */     this.nameModifiers.put(paramUUID, paramNameModifier);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Collection<TemplateModifier> rollModifiers(@NotNull MMOItemTemplate paramMMOItemTemplate) {
/* 203 */     if (!paramMMOItemTemplate.hasOption(MMOItemTemplate.TemplateOption.ROLL_MODIFIER_CHECK_ORDER)) {
/* 204 */       return paramMMOItemTemplate.getModifiers().values();
/*     */     }
/* 206 */     ArrayList<?> arrayList = new ArrayList(paramMMOItemTemplate.getModifiers().values());
/* 207 */     Collections.shuffle(arrayList);
/* 208 */     return (Collection)arrayList;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\item\build\MMOItemBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */