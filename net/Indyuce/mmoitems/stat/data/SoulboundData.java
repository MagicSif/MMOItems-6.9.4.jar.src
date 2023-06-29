/*    */ package net.Indyuce.mmoitems.stat.data;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.UUID;
/*    */ import net.Indyuce.mmoitems.stat.data.type.StatData;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ 
/*    */ public class SoulboundData
/*    */   implements StatData
/*    */ {
/*    */   private final UUID uuid;
/*    */   private final String name;
/*    */   private final int level;
/*    */   
/*    */   public SoulboundData(Player paramPlayer, int paramInt) {
/* 17 */     this(paramPlayer.getUniqueId(), paramPlayer.getName(), paramInt);
/*    */   }
/*    */   
/*    */   public SoulboundData(UUID paramUUID, String paramString, int paramInt) {
/* 21 */     this.uuid = paramUUID;
/* 22 */     this.name = paramString;
/* 23 */     this.level = paramInt;
/*    */   }
/*    */   
/*    */   public SoulboundData(JsonObject paramJsonObject) {
/* 27 */     this.uuid = UUID.fromString(paramJsonObject.get("UUID").getAsString());
/* 28 */     this.name = paramJsonObject.get("Name").getAsString();
/* 29 */     this.level = paramJsonObject.get("Level").getAsInt();
/*    */   }
/*    */   
/*    */   public UUID getUniqueId() {
/* 33 */     return this.uuid;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 37 */     return this.name;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 41 */     return this.level;
/*    */   }
/*    */   
/*    */   public JsonObject toJson() {
/* 45 */     JsonObject jsonObject = new JsonObject();
/* 46 */     jsonObject.addProperty("Level", Integer.valueOf(this.level));
/* 47 */     jsonObject.addProperty("Name", this.name);
/* 48 */     jsonObject.addProperty("UUID", this.uuid.toString());
/* 49 */     return jsonObject;
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\SoulboundData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */