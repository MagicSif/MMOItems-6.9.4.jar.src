/*    */ package net.Indyuce.mmoitems.api.item.util;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.authlib.properties.Property;
/*    */ import io.lumine.mythic.lib.MythicLib;
/*    */ import io.lumine.mythic.lib.api.item.ItemTag;
/*    */ import io.lumine.mythic.lib.util.AdventureUtils;
/*    */ import io.lumine.mythic.lib.version.VersionMaterial;
/*    */ import java.lang.reflect.Field;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import java.util.logging.Level;
/*    */ import java.util.stream.Collectors;
/*    */ import net.Indyuce.mmoitems.MMOItems;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.inventory.ItemFlag;
/*    */ import org.bukkit.inventory.meta.ItemMeta;
/*    */ 
/*    */ public class CustomSkull extends ConfigItem {
/*    */   private final String textureValue;
/*    */   
/*    */   public CustomSkull(String paramString1, String paramString2) {
/* 23 */     this(paramString1, paramString2, (String)null, new String[0]);
/*    */   }
/*    */   
/*    */   public CustomSkull(String paramString1, String paramString2, String paramString3, String... paramVarArgs) {
/* 27 */     super(paramString1, VersionMaterial.PLAYER_HEAD.toMaterial(), paramString3, paramVarArgs);
/*    */     
/* 29 */     this.textureValue = paramString2;
/*    */   }
/*    */   
/*    */   public void updateItem() {
/* 33 */     setItem(VersionMaterial.PLAYER_HEAD.toItem());
/* 34 */     ItemMeta itemMeta = getItem().getItemMeta();
/* 35 */     AdventureUtils.setDisplayName(itemMeta, getName());
/* 36 */     itemMeta.addItemFlags(ItemFlag.values());
/*    */     
/* 38 */     GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
/* 39 */     gameProfile.getProperties().put("textures", new Property("textures", this.textureValue));
/*    */     try {
/* 41 */       Field field = itemMeta.getClass().getDeclaredField("profile");
/* 42 */       field.setAccessible(true);
/* 43 */       field.set(itemMeta, gameProfile);
/* 44 */     } catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException noSuchFieldException) {
/* 45 */       MMOItems.plugin.getLogger().log(Level.WARNING, "Could not load skull texture");
/*    */     } 
/*    */     
/* 48 */     if (hasLore()) {
/* 49 */       AdventureUtils.setLore(itemMeta, (List)getLore()
/* 50 */           .stream()
/* 51 */           .map(paramString -> ChatColor.GRAY + paramString)
/* 52 */           .collect(Collectors.toList()));
/*    */     }
/* 54 */     getItem().setItemMeta(itemMeta);
/* 55 */     setItem(MythicLib.plugin.getVersion().getWrapper().getNBTItem(getItem()).addTag(new ItemTag[] { new ItemTag("ItemId", getId()) }).toItem());
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\api\ite\\util\CustomSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */