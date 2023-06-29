/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoundData
/*    */ {
/*    */   private final String sound;
/*    */   private final double volume;
/*    */   private final double pitch;
/*    */   
/*    */   public SoundData(String paramString, double paramDouble1, double paramDouble2) {
/* 19 */     this.sound = paramString;
/* 20 */     this.volume = paramDouble1;
/* 21 */     this.pitch = paramDouble2;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SoundData(Object paramObject) {
/* 29 */     if (paramObject instanceof String) {
/* 30 */       this.sound = paramObject.toString();
/* 31 */       this.volume = 1.0D;
/* 32 */       this.pitch = 1.0D;
/*    */     
/*    */     }
/* 35 */     else if (paramObject instanceof ConfigurationSection) {
/* 36 */       ConfigurationSection configurationSection = (ConfigurationSection)paramObject;
/* 37 */       this.sound = configurationSection.getString("sound");
/* 38 */       this.volume = configurationSection.getDouble("volume");
/* 39 */       this.pitch = configurationSection.getDouble("pitch");
/*    */     }
/*    */     else {
/*    */       
/* 43 */       throw new IllegalArgumentException("You must provide a string or config section");
/*    */     } 
/*    */   }
/*    */   public String getSound() {
/* 47 */     return this.sound;
/*    */   }
/*    */   
/*    */   public double getVolume() {
/* 51 */     return this.volume;
/*    */   }
/*    */   
/*    */   public double getPitch() {
/* 55 */     return this.pitch;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\SoundData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */