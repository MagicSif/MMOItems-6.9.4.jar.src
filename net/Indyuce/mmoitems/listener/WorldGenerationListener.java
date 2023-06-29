/*    */ package net.Indyuce.mmoitems.listener;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.Indyuce.mmoitems.api.world.MMOBlockPopulator;
/*    */ import net.Indyuce.mmoitems.manager.WorldGenManager;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.world.ChunkLoadEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenerationListener
/*    */   implements Listener
/*    */ {
/*    */   private final WorldGenManager manager;
/*    */   private final Map<String, MMOBlockPopulator> populatorMap;
/*    */   
/*    */   public WorldGenerationListener(WorldGenManager paramWorldGenManager) {
/* 34 */     this.populatorMap = new HashMap<>();
/*    */     this.manager = paramWorldGenManager;
/*    */   } @EventHandler
/*    */   public void onChunkLoad(ChunkLoadEvent paramChunkLoadEvent) {
/* 38 */     World world = paramChunkLoadEvent.getWorld();
/* 39 */     if (!paramChunkLoadEvent.isNewChunk() || this.populatorMap.containsKey(world.getName()))
/* 40 */       return;  MMOBlockPopulator mMOBlockPopulator = this.manager.populator(world);
/* 41 */     world.getPopulators().add(mMOBlockPopulator);
/* 42 */     this.populatorMap.put(world.getName(), mMOBlockPopulator);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\listener\WorldGenerationListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */