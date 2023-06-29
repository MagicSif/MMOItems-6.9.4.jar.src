/*    */ package net.Indyuce.mmoitems.api.util;
/*    */ 
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Sound;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public class SoundReader {
/*    */   @Nullable
/*    */   private final Sound sound;
/*    */   
/*    */   public SoundReader(@NotNull String paramString, @NotNull Sound paramSound) {
/*    */     Sound sound;
/*    */     String str;
/* 16 */     if (paramString.isEmpty()) {
/* 17 */       this.sound = paramSound;
/* 18 */       this.soundKey = "";
/*    */ 
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/*    */     try {
/* 25 */       sound = Sound.valueOf(paramString);
/* 26 */       str = null;
/* 27 */     } catch (Exception exception) {
/* 28 */       sound = null;
/* 29 */       str = paramString.toLowerCase();
/*    */     } 
/*    */     
/* 32 */     this.sound = sound;
/* 33 */     this.soundKey = str;
/*    */   } @Nullable
/*    */   private final String soundKey;
/*    */   @Nullable
/*    */   public Sound getSound() {
/* 38 */     return this.sound;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public String getSoundKey() {
/* 43 */     return this.soundKey;
/*    */   }
/*    */   
/*    */   public void play(@NotNull Player paramPlayer) {
/* 47 */     play(paramPlayer, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public void play(@NotNull Player paramPlayer, float paramFloat1, float paramFloat2) {
/* 51 */     if (this.sound != null) { paramPlayer.playSound(paramPlayer.getLocation(), this.sound, paramFloat1, paramFloat2); }
/* 52 */     else { paramPlayer.playSound(paramPlayer.getLocation(), this.soundKey, paramFloat1, paramFloat2); }
/*    */   
/*    */   }
/*    */   public void play(@NotNull Location paramLocation) {
/* 56 */     play(paramLocation, 1.0F, 1.0F);
/*    */   }
/*    */   
/*    */   public void play(@NotNull Location paramLocation, float paramFloat1, float paramFloat2) {
/* 60 */     if (this.sound != null) { paramLocation.getWorld().playSound(paramLocation, this.sound, paramFloat1, paramFloat2); }
/* 61 */     else { paramLocation.getWorld().playSound(paramLocation, this.soundKey, paramFloat1, paramFloat2); }
/*    */   
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ap\\util\SoundReader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */