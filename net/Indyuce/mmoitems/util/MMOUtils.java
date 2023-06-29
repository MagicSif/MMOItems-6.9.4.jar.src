/*     */ package net.Indyuce.mmoitems.util;
/*     */ import com.mojang.authlib.properties.Property;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.Type;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.event.entity.EntityDamageByEntityEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.Vector;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public class MMOUtils {
/*     */   public MMOUtils() {
/*  34 */     throw new IllegalArgumentException("This class cannot be instantiated.");
/*     */   }
/*     */   private static final String UNIVERSAL_REFERENCE = "all";
/*     */   public static boolean isColorable(@NotNull Particle paramParticle) {
/*  38 */     return (paramParticle.getDataType() == Particle.DustOptions.class);
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
/*     */   public static boolean hasBeenRemoved(@NotNull NBTItem paramNBTItem) {
/*  50 */     if (!paramNBTItem.hasType()) return false;
/*     */     
/*  52 */     String str = paramNBTItem.getType();
/*  53 */     return (isNonEmpty(str) && (!Type.isValid(str) || !MMOItems.plugin.getTemplates().hasTemplate(Type.get(str), paramNBTItem.getString("MMOITEMS_ITEM_ID"))));
/*     */   }
/*     */   
/*     */   public static boolean isNonEmpty(@Nullable String paramString) {
/*  57 */     return (paramString != null && !paramString.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getSkullTextureURL(ItemStack paramItemStack) {
/*     */     try {
/*  65 */       ItemMeta itemMeta = paramItemStack.getItemMeta();
/*  66 */       Field field = itemMeta.getClass().getDeclaredField("profile");
/*  67 */       field.setAccessible(true);
/*  68 */       Collection collection = ((GameProfile)field.get(paramItemStack.getItemMeta())).getProperties().get("textures");
/*  69 */       Property property = ((Property[])collection.toArray((T[])new Property[0]))[0];
/*  70 */       return (new String(Base64.decodeBase64(property.getValue()))).replace("{textures:{SKIN:{url:\"", "").replace("\"}}}", "");
/*  71 */     } catch (Exception exception) {
/*  72 */       return "";
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkReference(@Nullable String paramString1, @Nullable String paramString2) {
/*  95 */     return (paramString1 == null || paramString1.isEmpty() || paramString2 == null || paramString2.isEmpty() || paramString1.equals("all") || paramString2.equals("all") || paramString1.equals(paramString2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 101 */   private static final Map<ChatColor, Color> COLOR_MAPPINGS = (Map<ChatColor, Color>)ImmutableMap.builder()
/* 102 */     .put(ChatColor.BLACK, Color.fromRGB(0, 0, 0))
/* 103 */     .put(ChatColor.DARK_BLUE, Color.fromRGB(0, 0, 170))
/* 104 */     .put(ChatColor.DARK_GREEN, Color.fromRGB(0, 170, 0))
/* 105 */     .put(ChatColor.DARK_AQUA, Color.fromRGB(0, 170, 170))
/* 106 */     .put(ChatColor.DARK_RED, Color.fromRGB(170, 0, 0))
/* 107 */     .put(ChatColor.DARK_PURPLE, Color.fromRGB(170, 0, 170))
/* 108 */     .put(ChatColor.GOLD, Color.fromRGB(255, 170, 0))
/* 109 */     .put(ChatColor.GRAY, Color.fromRGB(170, 170, 170))
/* 110 */     .put(ChatColor.DARK_GRAY, Color.fromRGB(85, 85, 85))
/* 111 */     .put(ChatColor.BLUE, Color.fromRGB(85, 85, 255))
/* 112 */     .put(ChatColor.GREEN, Color.fromRGB(85, 255, 85))
/* 113 */     .put(ChatColor.AQUA, Color.fromRGB(85, 255, 255))
/* 114 */     .put(ChatColor.RED, Color.fromRGB(255, 85, 85))
/* 115 */     .put(ChatColor.LIGHT_PURPLE, Color.fromRGB(255, 85, 255))
/* 116 */     .put(ChatColor.YELLOW, Color.fromRGB(255, 255, 85))
/* 117 */     .put(ChatColor.WHITE, Color.fromRGB(255, 255, 255))
/* 118 */     .build();
/*     */   
/*     */   @NotNull
/*     */   public static Color toRGB(ChatColor paramChatColor) {
/* 122 */     return Objects.<Color>requireNonNull(COLOR_MAPPINGS.get(paramChatColor), "Not a color");
/*     */   }
/*     */   
/*     */   public static int getPickaxePower(Player paramPlayer) {
/* 126 */     ItemStack itemStack = paramPlayer.getInventory().getItemInMainHand();
/* 127 */     if (itemStack == null || itemStack.getType() == Material.AIR) {
/* 128 */       return 0;
/*     */     }
/* 130 */     NBTItem nBTItem = NBTItem.get(itemStack);
/* 131 */     if (nBTItem.hasTag("MMOITEMS_PICKAXE_POWER")) {
/* 132 */       return nBTItem.getInteger("MMOITEMS_PICKAXE_POWER");
/*     */     }
/* 134 */     switch (itemStack.getType().name()) {
/*     */       case "WOODEN_PICKAXE":
/*     */       case "WOOD_PICKAXE":
/* 137 */         return 5;
/*     */       case "STONE_PICKAXE":
/* 139 */         return 10;
/*     */       case "GOLDEN_PICKAXE":
/*     */       case "GOLD_PICKAXE":
/* 142 */         return 15;
/*     */       case "IRON_PICKAXE":
/* 144 */         return 20;
/*     */       case "DIAMOND_PICKAXE":
/* 146 */         return 25;
/*     */       case "NETHERITE_PICKAXE":
/* 148 */         return 30;
/*     */     } 
/* 150 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static TriggerType backwardsCompatibleTriggerType(@NotNull String paramString) {
/* 161 */     if (paramString == null) {
/* 162 */       throw new IllegalArgumentException("Trigger cannot be null");
/*     */     }
/* 164 */     switch (paramString) {
/*     */       case "ON_HIT":
/* 166 */         return TriggerType.ATTACK;
/*     */       case "WHEN_HIT":
/* 168 */         return TriggerType.DAMAGED;
/*     */     } 
/* 170 */     return TriggerType.valueOf(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMMOItem(@Nullable ItemStack paramItemStack, @NotNull String paramString1, @NotNull String paramString2) {
/* 181 */     if (paramItemStack == null) {
/* 182 */       return false;
/*     */     }
/*     */     
/* 185 */     NBTItem nBTItem = NBTItem.get(paramItemStack);
/*     */ 
/*     */     
/* 188 */     String str = getID(nBTItem);
/*     */ 
/*     */     
/* 191 */     if (str == null) {
/* 192 */       return false;
/*     */     }
/*     */     
/* 195 */     if (!str.equals(paramString2)) {
/* 196 */       return false;
/*     */     }
/*     */     
/* 199 */     return nBTItem.getType().equals(paramString1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Type getType(@Nullable NBTItem paramNBTItem) {
/* 208 */     if (paramNBTItem == null || !paramNBTItem.hasType()) {
/* 209 */       return null;
/*     */     }
/*     */     
/* 212 */     return MMOItems.plugin.getTypes().get(paramNBTItem.getType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static String getID(@Nullable NBTItem paramNBTItem) {
/* 221 */     if (paramNBTItem == null || !paramNBTItem.hasType()) {
/* 222 */       return null;
/*     */     }
/* 224 */     ItemTag itemTag = ItemTag.getTagAtPath("MMOITEMS_ITEM_ID", paramNBTItem, SupportedNBTTagValues.STRING);
/* 225 */     if (itemTag == null) {
/* 226 */       return null;
/*     */     }
/* 228 */     return (String)itemTag.getValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vector normalize(Vector paramVector) {
/* 239 */     return (paramVector.getX() == 0.0D && paramVector.getY() == 0.0D) ? paramVector : paramVector.normalize();
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
/*     */   public static double parseDouble(String paramString) {
/*     */     try {
/* 252 */       return Double.parseDouble(paramString);
/* 253 */     } catch (IllegalArgumentException illegalArgumentException) {
/* 254 */       throw new IllegalArgumentException("Could not read number from '" + paramString + "'");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static UUID UUIDFromString(@Nullable String paramString) {
/* 263 */     if (paramString == null) {
/* 264 */       return null;
/*     */     }
/*     */     
/* 267 */     if (paramString.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}"))
/*     */     {
/*     */       
/* 270 */       return UUID.fromString(paramString);
/*     */     }
/*     */     
/* 273 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static LivingEntity getDamager(EntityDamageByEntityEvent paramEntityDamageByEntityEvent) {
/* 279 */     if (paramEntityDamageByEntityEvent.getDamager() instanceof LivingEntity) return (LivingEntity)paramEntityDamageByEntityEvent.getDamager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 285 */     if (paramEntityDamageByEntityEvent.getDamager() instanceof Projectile) {
/* 286 */       Projectile projectile = (Projectile)paramEntityDamageByEntityEvent.getDamager();
/* 287 */       if (projectile.getShooter() instanceof LivingEntity) {
/* 288 */         return (LivingEntity)projectile.getShooter();
/*     */       }
/*     */     } 
/* 291 */     return null;
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
/*     */   public static int getEffectDuration(PotionEffectType paramPotionEffectType) {
/* 305 */     return (paramPotionEffectType.equals(PotionEffectType.NIGHT_VISION) || paramPotionEffectType.equals(PotionEffectType.CONFUSION)) ? 260 : (paramPotionEffectType.equals(PotionEffectType.BLINDNESS) ? 140 : 80);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static String getDisplayName(@Nullable ItemStack paramItemStack) {
/* 310 */     if (paramItemStack == null) {
/* 311 */       return "null";
/*     */     }
/* 313 */     return (paramItemStack.hasItemMeta() && paramItemStack.getItemMeta().hasDisplayName()) ? 
/* 314 */       paramItemStack.getItemMeta().getDisplayName() : 
/* 315 */       caseOnWords(paramItemStack.getType().name().toLowerCase().replace("_", " "));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String caseOnWords(String paramString) {
/* 325 */     StringBuilder stringBuilder = new StringBuilder(paramString);
/* 326 */     boolean bool = true;
/* 327 */     for (byte b = 0; b < stringBuilder.length(); b++) {
/* 328 */       char c = stringBuilder.charAt(b);
/* 329 */       if (bool && c >= 'a' && c <= 'z')
/* 330 */       { stringBuilder.setCharAt(b, (char)(c + -32));
/* 331 */         bool = false; }
/* 332 */       else { bool = (c == ' ') ? true : false; }
/*     */     
/* 334 */     }  return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isMetaItem(ItemStack paramItemStack, boolean paramBoolean) {
/* 344 */     return (paramItemStack != null && paramItemStack.getType() != Material.AIR && paramItemStack.getItemMeta() != null && paramItemStack.getItemMeta().getDisplayName() != null && (!paramBoolean || paramItemStack.getItemMeta().getLore() != null));
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
/*     */   public static void saturate(@NotNull Player paramPlayer, double paramDouble) {
/* 356 */     saturate(paramPlayer, paramDouble, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void saturate(@NotNull Player paramPlayer, double paramDouble, boolean paramBoolean) {
/* 367 */     if (paramDouble > 0.0D || paramBoolean) {
/* 368 */       paramPlayer.setSaturation(Math.max(0.0F, Math.min(20.0F, paramPlayer.getSaturation() + (float)paramDouble)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void feed(@NotNull Player paramPlayer, int paramInt) {
/* 378 */     feed(paramPlayer, paramInt, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void feed(@NotNull Player paramPlayer, int paramInt, boolean paramBoolean) {
/* 389 */     if (paramInt > 0 || paramBoolean) paramPlayer.setFoodLevel(Math.max(Math.min(20, paramPlayer.getFoodLevel() + paramInt), 0));
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void heal(@NotNull LivingEntity paramLivingEntity, double paramDouble) {
/* 399 */     heal(paramLivingEntity, paramDouble, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void heal(@NotNull LivingEntity paramLivingEntity, double paramDouble, boolean paramBoolean) {
/* 410 */     if (paramDouble > 0.0D || paramBoolean) {
/* 411 */       paramLivingEntity.setHealth(Math.min(paramLivingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), paramLivingEntity.getHealth() + paramDouble));
/*     */     }
/*     */   }
/*     */   
/* 415 */   private static final String[] romanChars = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
/* 416 */   private static final int[] romanValues = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String intToRoman(int paramInt) {
/* 423 */     if (paramInt < 1 || paramInt > 3999) throw new IllegalArgumentException("Input must be between 1 and 3999");
/*     */     
/* 425 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/* 427 */     for (byte b = 0; b < romanChars.length; b++) {
/* 428 */       while (paramInt >= romanValues[b]) {
/* 429 */         stringBuilder.append(romanChars[b]);
/* 430 */         paramInt -= romanValues[b];
/*     */       } 
/*     */     } 
/* 433 */     return stringBuilder.toString();
/*     */   }
/*     */   
/*     */   public static double truncation(double paramDouble, int paramInt) {
/* 437 */     double d = Math.pow(10.0D, paramInt);
/* 438 */     return Math.floor(paramDouble * d) / d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Vector rotateFunc(Vector paramVector, Location paramLocation) {
/* 447 */     double d1 = (paramLocation.getYaw() / 180.0F) * Math.PI;
/* 448 */     double d2 = (paramLocation.getPitch() / 180.0F) * Math.PI;
/* 449 */     paramVector = rotAxisX(paramVector, d2);
/* 450 */     paramVector = rotAxisY(paramVector, -d1);
/* 451 */     return paramVector;
/*     */   }
/*     */   
/*     */   private static Vector rotAxisX(Vector paramVector, double paramDouble) {
/* 455 */     double d1 = paramVector.getY() * Math.cos(paramDouble) - paramVector.getZ() * Math.sin(paramDouble);
/* 456 */     double d2 = paramVector.getY() * Math.sin(paramDouble) + paramVector.getZ() * Math.cos(paramDouble);
/* 457 */     return paramVector.setY(d1).setZ(d2);
/*     */   }
/*     */   
/*     */   private static Vector rotAxisY(Vector paramVector, double paramDouble) {
/* 461 */     double d1 = paramVector.getX() * Math.cos(paramDouble) + paramVector.getZ() * Math.sin(paramDouble);
/* 462 */     double d2 = paramVector.getX() * -Math.sin(paramDouble) + paramVector.getZ() * Math.cos(paramDouble);
/* 463 */     return paramVector.setX(d1).setZ(d2);
/*     */   }
/*     */   
/*     */   private static Vector rotAxisZ(Vector paramVector, double paramDouble) {
/* 467 */     double d1 = paramVector.getX() * Math.cos(paramDouble) - paramVector.getY() * Math.sin(paramDouble);
/* 468 */     double d2 = paramVector.getX() * Math.sin(paramDouble) + paramVector.getY() * Math.cos(paramDouble);
/* 469 */     return paramVector.setX(d1).setY(d2);
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
/*     */   public static List<Entity> getNearbyChunkEntities(Location paramLocation) {
/* 481 */     ArrayList<Entity> arrayList = new ArrayList();
/*     */     
/* 483 */     int i = paramLocation.getChunk().getX();
/* 484 */     int j = paramLocation.getChunk().getZ();
/*     */     
/* 486 */     for (byte b = -1; b < 2; b++) {
/* 487 */       for (byte b1 = -1; b1 < 2; b1++)
/* 488 */         arrayList.addAll(Arrays.asList(paramLocation.getWorld().getChunkAt(i + b, j + b1).getEntities())); 
/*     */     } 
/* 490 */     return arrayList;
/*     */   }
/*     */   
/*     */   public static ItemStack readIcon(String paramString) {
/* 494 */     String[] arrayOfString = paramString.split(":");
/* 495 */     Material material = Material.valueOf(arrayOfString[0].toUpperCase().replace("-", "_").replace(" ", "_"));
/* 496 */     return (arrayOfString.length > 1) ? MythicLib.plugin.getVersion().getWrapper().textureItem(material, Integer.parseInt(arrayOfString[1])) : new ItemStack(material);
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitem\\util\MMOUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */