/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.edition.StatEdition;
/*     */ import net.Indyuce.mmoitems.api.item.build.ItemStackBuilder;
/*     */ import net.Indyuce.mmoitems.api.item.mmoitem.ReadMMOItem;
/*     */ import net.Indyuce.mmoitems.gui.edition.EditionInventory;
/*     */ import net.Indyuce.mmoitems.stat.data.SkullTextureData;
/*     */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.event.inventory.InventoryAction;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class SkullTextureStat
/*     */   extends ItemStat<SkullTextureData, SkullTextureData> {
/*     */   public SkullTextureStat() {
/*  34 */     super("SKULL_TEXTURE", VersionMaterial.PLAYER_HEAD.toMaterial(), "Skull Texture", new String[] { "The head texture &nvalue&7.", "Can be found on heads databases." }, new String[] { "all" }, new Material[] { VersionMaterial.PLAYER_HEAD
/*     */           
/*  36 */           .toMaterial() });
/*     */   }
/*     */ 
/*     */   
/*     */   public SkullTextureData whenInitialized(Object paramObject) {
/*  41 */     Validate.isTrue(paramObject instanceof ConfigurationSection, "Must specify a config section");
/*  42 */     ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/*     */     
/*  44 */     String str1 = configurationSection.getString("value");
/*  45 */     Validate.notNull(str1, "Could not load skull texture value");
/*     */     
/*  47 */     String str2 = configurationSection.getString("uuid");
/*  48 */     Validate.notNull(str2, "Could not find skull texture UUID: re-enter your skull texture value and one will be selected randomly.");
/*     */     
/*  50 */     SkullTextureData skullTextureData = new SkullTextureData(new GameProfile(UUID.fromString(str2), null));
/*  51 */     skullTextureData.getGameProfile().getProperties().put("textures", new Property("textures", str1));
/*  52 */     return skullTextureData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenDisplayed(List<String> paramList, Optional<SkullTextureData> paramOptional) {
/*  57 */     paramList.add(ChatColor.GRAY + "Current Value: " + (paramOptional.isPresent() ? (ChatColor.GREEN + "Texture value provided") : (ChatColor.RED + "None")));
/*  58 */     paramList.add("");
/*  59 */     paramList.add(ChatColor.YELLOW + "►" + " Left click to change this value.");
/*  60 */     paramList.add(ChatColor.YELLOW + "►" + " Right click to remove this value.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenInput(@NotNull EditionInventory paramEditionInventory, @NotNull String paramString, Object... paramVarArgs) {
/*  65 */     paramEditionInventory.getEditedSection().set("skull-texture.value", paramString);
/*  66 */     paramEditionInventory.getEditedSection().set("skull-texture.uuid", UUID.randomUUID().toString());
/*  67 */     paramEditionInventory.registerTemplateEdition();
/*  68 */     paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + getName() + " successfully changed to " + paramString + ".");
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenApplied(@NotNull ItemStackBuilder paramItemStackBuilder, @NotNull SkullTextureData paramSkullTextureData) {
/*  73 */     if (paramItemStackBuilder.getItemStack().getType() != VersionMaterial.PLAYER_HEAD.toMaterial()) {
/*     */       return;
/*     */     }
/*     */     try {
/*  77 */       Field field = paramItemStackBuilder.getMeta().getClass().getDeclaredField("profile");
/*  78 */       field.setAccessible(true);
/*  79 */       field.set(paramItemStackBuilder.getMeta(), paramSkullTextureData.getGameProfile());
/*  80 */     } catch (NoSuchFieldException|IllegalAccessException noSuchFieldException) {
/*  81 */       throw new IllegalArgumentException(noSuchFieldException.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ArrayList<ItemTag> getAppliedNBT(@NotNull SkullTextureData paramSkullTextureData) {
/*  90 */     return new ArrayList<>();
/*     */   }
/*     */   
/*     */   public void whenClicked(@NotNull EditionInventory paramEditionInventory, @NotNull InventoryClickEvent paramInventoryClickEvent) {
/*  94 */     if (paramInventoryClickEvent.getAction() == InventoryAction.PICKUP_HALF) {
/*  95 */       paramEditionInventory.getEditedSection().set(getPath(), null);
/*  96 */       paramEditionInventory.registerTemplateEdition();
/*  97 */       paramEditionInventory.getPlayer().sendMessage(MMOItems.plugin.getPrefix() + "Successfully removed " + getName() + ".");
/*     */     } else {
/*  99 */       (new StatEdition(paramEditionInventory, this, new Object[0])).enable(new String[] { "Write in the chat the text you want." });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void whenLoaded(@NotNull ReadMMOItem paramReadMMOItem) {
/*     */     try {
/* 105 */       Field field = paramReadMMOItem.getNBT().getItem().getItemMeta().getClass().getDeclaredField("profile");
/* 106 */       field.setAccessible(true);
/* 107 */       paramReadMMOItem.setData(ItemStats.SKULL_TEXTURE, (StatData)new SkullTextureData((GameProfile)field.get(paramReadMMOItem.getNBT().getItem().getItemMeta())));
/* 108 */     } catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException noSuchFieldException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SkullTextureData getLoadedNBT(@NotNull ArrayList<ItemTag> paramArrayList) {
/* 117 */     return null;
/*     */   }
/*     */   @NotNull
/*     */   public SkullTextureData getClearStatData() {
/* 121 */     return new SkullTextureData(new GameProfile(UUID.fromString("df930b7b-a84d-4f76-90ac-33be6a5b6c88"), "gunging"));
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\SkullTextureStat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */