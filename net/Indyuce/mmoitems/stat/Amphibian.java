/*     */ package net.Indyuce.mmoitems.stat;
/*     */ 
/*     */ import io.lumine.mythic.lib.api.item.ItemTag;
/*     */ import io.lumine.mythic.lib.api.item.NBTItem;
/*     */ import io.lumine.mythic.lib.api.item.SupportedNBTTagValues;
/*     */ import io.lumine.mythic.lib.api.util.ui.SilentNumbers;
/*     */ import java.util.ArrayList;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.stat.data.StringData;
/*     */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*     */ import net.Indyuce.mmoitems.stat.type.ChooseStat;
/*     */ import net.Indyuce.mmoitems.stat.type.GemStoneStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemRestriction;
/*     */ import net.Indyuce.mmoitems.util.StatChoice;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.util.BoundingBox;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class Amphibian
/*     */   extends ChooseStat
/*     */   implements ItemRestriction, GemStoneStat
/*     */ {
/*  26 */   public static final StatChoice NORMAL = new StatChoice("NORMAL", "No liquids dependency");
/*  27 */   public static final StatChoice DRY = new StatChoice("DRY", "The item does not work if the player is touching a liquid block.");
/*  28 */   public static final StatChoice WET = new StatChoice("WET", "The only works if the player is touching water (rain does not count).");
/*  29 */   public static final StatChoice DAMP = new StatChoice("DAMP", "The only works if the player is completely submerged in water.");
/*  30 */   public static final StatChoice LAVA = new StatChoice("LAVA", "The only works if the player is touching lava.");
/*  31 */   public static final StatChoice MOLTEN = new StatChoice("MOLTEN", "The only works if the player is completely submerged in lava.");
/*  32 */   public static final StatChoice LIQUID = new StatChoice("LIQUID", "The only works if the player is touching any liquid.");
/*  33 */   public static final StatChoice SUBMERGED = new StatChoice("SUBMERGED", "The only works if the player is completely submerged in any liquid.");
/*     */   
/*     */   public Amphibian() {
/*  36 */     super("AMPHIBIAN", Material.WATER_BUCKET, "Amphibian", new String[] { "May this item only be used in specific", "environments regarding liquids?" }, new String[] { "!block", "all" }, new Material[0]);
/*     */     
/*  38 */     addChoices(new StatChoice[] { NORMAL, DRY, WET, DAMP, LAVA, MOLTEN, LIQUID, SUBMERGED });
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public StringData getClearStatData() {
/*  44 */     return new StringData(NORMAL.getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(RPGPlayer paramRPGPlayer, NBTItem paramNBTItem, boolean paramBoolean) {
/*  51 */     if (!paramNBTItem.hasTag(getNBTPath())) {
/*  52 */       return true;
/*     */     }
/*     */ 
/*     */     
/*  56 */     ArrayList<ItemTag> arrayList = new ArrayList();
/*  57 */     if (paramNBTItem.hasTag(getNBTPath())) {
/*  58 */       arrayList.add(ItemTag.getTagAtPath(getNBTPath(), paramNBTItem, SupportedNBTTagValues.STRING));
/*     */     }
/*     */ 
/*     */     
/*  62 */     String str = getLoadedNBT(arrayList).toString();
/*     */ 
/*     */     
/*  65 */     if (str != null) {
/*     */ 
/*     */ 
/*     */       
/*  69 */       switch (str)
/*     */       
/*     */       { 
/*     */         default:
/*  73 */           return true;
/*     */         case "DRY":
/*  75 */           for (Block block : blocksTouchedByPlayer(paramRPGPlayer.getPlayer())) {
/*     */             
/*  77 */             if (block.isLiquid())
/*     */             {
/*  79 */               return false;
/*     */             }
/*     */           } 
/*     */           
/*  83 */           return true;
/*     */         case "WET":
/*  85 */           for (Block block : blocksTouchedByPlayer(paramRPGPlayer.getPlayer())) {
/*     */             
/*  87 */             if (block.getType().equals(Material.WATER))
/*     */             {
/*  89 */               return true;
/*     */             }
/*     */           } 
/*     */           
/*  93 */           return false;
/*     */         case "DAMP":
/*  95 */           for (Block block : blocksTouchedByPlayer(paramRPGPlayer.getPlayer())) {
/*     */             
/*  97 */             if (!block.getType().equals(Material.WATER))
/*     */             {
/*  99 */               return false;
/*     */             }
/*     */           } 
/*     */           
/* 103 */           return true;
/*     */         case "LAVA":
/* 105 */           for (Block block : blocksTouchedByPlayer(paramRPGPlayer.getPlayer())) {
/*     */             
/* 107 */             if (block.getType().equals(Material.LAVA))
/*     */             {
/* 109 */               return true;
/*     */             }
/*     */           } 
/*     */           
/* 113 */           return false;
/*     */         case "MOLTEN":
/* 115 */           for (Block block : blocksTouchedByPlayer(paramRPGPlayer.getPlayer())) {
/*     */             
/* 117 */             if (!block.getType().equals(Material.LAVA))
/*     */             {
/* 119 */               return false;
/*     */             }
/*     */           } 
/*     */           
/* 123 */           return true;
/*     */         case "LIQUID":
/* 125 */           for (Block block : blocksTouchedByPlayer(paramRPGPlayer.getPlayer())) {
/*     */             
/* 127 */             if (block.isLiquid())
/*     */             {
/* 129 */               return true;
/*     */             }
/*     */           } 
/*     */           
/* 133 */           return false;
/*     */         case "SUBMERGED":
/* 135 */           break; }  for (Block block : blocksTouchedByPlayer(paramRPGPlayer.getPlayer())) {
/*     */         
/* 137 */         if (!block.isLiquid())
/*     */         {
/* 139 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 143 */       return true;
/*     */     } 
/*     */     
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDynamic() {
/* 151 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ArrayList<Block> blocksTouchedByPlayer(@NotNull Player paramPlayer) {
/* 158 */     BoundingBox boundingBox = paramPlayer.getBoundingBox();
/*     */ 
/*     */     
/* 161 */     ArrayList<Block> arrayList = new ArrayList();
/*     */     
/*     */     double d;
/* 164 */     for (d = boundingBox.getMinX(); d <= boundingBox.getMaxX(); d += Math.min(1.0D, Math.max(boundingBox.getWidthX() - d - boundingBox.getMinX(), 0.001D))) {
/* 165 */       double d1; for (d1 = boundingBox.getMinY(); d1 <= boundingBox.getMaxY(); d1 += Math.min(1.0D, Math.max(boundingBox.getHeight() - d1 - boundingBox.getMinY(), 0.001D))) {
/* 166 */         double d2; for (d2 = boundingBox.getMinZ(); d2 <= boundingBox.getMaxZ(); d2 += Math.min(1.0D, Math.max(boundingBox.getWidthZ() - d2 - boundingBox.getMinZ(), 0.001D))) {
/*     */ 
/*     */           
/* 169 */           int i = SilentNumbers.floor(d);
/* 170 */           int j = SilentNumbers.floor(d1);
/* 171 */           int k = SilentNumbers.floor(d2);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 176 */           Block block = paramPlayer.getLocation().getWorld().getBlockAt(i, j, k);
/*     */ 
/*     */           
/* 179 */           if (!arrayList.contains(block))
/*     */           {
/*     */ 
/*     */             
/* 183 */             arrayList.add(block);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 190 */     return arrayList;
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\Amphibian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */