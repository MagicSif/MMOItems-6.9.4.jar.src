/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import net.Indyuce.mmoitems.api.item.build.MMOItemBuilder;
/*    */ import net.Indyuce.mmoitems.stat.data.random.RandomStatData;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ 
/*    */ public class SkullTextureData
/*    */   implements StatData, RandomStatData<SkullTextureData> {
/*    */   private final GameProfile profile;
/*    */   
/*    */   public SkullTextureData(GameProfile paramGameProfile) {
/* 13 */     this.profile = paramGameProfile;
/*    */   }
/*    */   
/*    */   public GameProfile getGameProfile() {
/* 17 */     return this.profile;
/*    */   }
/*    */ 
/*    */   
/*    */   public SkullTextureData randomize(MMOItemBuilder paramMMOItemBuilder) {
/* 22 */     return this;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\SkullTextureData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */