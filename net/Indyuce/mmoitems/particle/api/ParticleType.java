/*    */ package net.Indyuce.mmoitems.particle.api;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.BiFunction;
/*    */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*    */ import net.Indyuce.mmoitems.api.util.StringValue;
/*    */ import net.Indyuce.mmoitems.stat.data.ParticleData;
/*    */ import net.Indyuce.mmoitems.util.MMOUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ParticleType
/*    */ {
/* 21 */   OFFSET(net.Indyuce.mmoitems.particle.OffsetParticles::new, false, 5L, "Some particles randomly spawning around your body.", new StringValue[] { new StringValue("amount", 5.0D), new StringValue("vertical-offset", 0.5D), new StringValue("horizontal-offset", 0.3D), new StringValue("speed", 0.0D), new StringValue("height", 1.0D) }),
/* 22 */   FIREFLIES(net.Indyuce.mmoitems.particle.FirefliesParticles::new, true, 1L, "Particles dashing around you at the same height.", new StringValue[] { new StringValue("amount", 3.0D), new StringValue("speed", 0.0D), new StringValue("rotation-speed", 1.0D), new StringValue("radius", 1.3D), new StringValue("height", 1.0D) }),
/* 23 */   VORTEX(net.Indyuce.mmoitems.particle.VortexParticles::new, true, 1L, "Particles flying around you in a cone shape.", new StringValue[] { new StringValue("radius", 1.5D), new StringValue("height", 2.4D), new StringValue("speed", 0.0D), new StringValue("y-speed", 1.0D), new StringValue("rotation-speed", 1.0D), new StringValue("amount", 3.0D) }),
/* 24 */   GALAXY(net.Indyuce.mmoitems.particle.GalaxyParticles::new, true, 1L, "Particles flying around you in spiral arms.", new StringValue[] { new StringValue("height", 1.0D), new StringValue("speed", 1.0D), new StringValue("y-coord", 0.0D), new StringValue("rotation-speed", 1.0D), new StringValue("amount", 6.0D) }),
/* 25 */   DOUBLE_RINGS(net.Indyuce.mmoitems.particle.DoubleRingsParticles::new, true, 1L, "Particles drawing two rings around you.", new StringValue[] { new StringValue("radius", 0.8D), new StringValue("y-offset", 0.4D), new StringValue("height", 1.0D), new StringValue("speed", 0.0D), new StringValue("rotation-speed", 1.0D) }),
/* 26 */   HELIX(net.Indyuce.mmoitems.particle.HelixParticles::new, true, 1L, "Particles drawing a sphere around you.", new StringValue[] { new StringValue("radius", 0.8D), new StringValue("height", 0.6D), new StringValue("rotation-speed", 1.0D), new StringValue("y-speed", 1.0D), new StringValue("amount", 4.0D), new StringValue("speed", 0.0D) }),
/* 27 */   AURA(net.Indyuce.mmoitems.particle.AuraParticles::new, true, 1L, "Particles dashing around you (height can differ).", new StringValue[] { new StringValue("amount", 3.0D), new StringValue("speed", 0.0D), new StringValue("rotation-speed", 1.0D), new StringValue("y-speed", 1.0D), new StringValue("y-offset", 0.7D), new StringValue("radius", 1.3D), new StringValue("height", 1.0D) });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 33 */   private final Map<String, Double> modifiers = new HashMap<>();
/*    */ 
/*    */ 
/*    */   
/*    */   private final BiFunction<ParticleData, PlayerData, ParticleRunnable> func;
/*    */ 
/*    */ 
/*    */   
/*    */   private final boolean override;
/*    */ 
/*    */   
/*    */   private final long period;
/*    */ 
/*    */   
/*    */   private final String lore;
/*    */ 
/*    */ 
/*    */   
/*    */   ParticleType(BiFunction<ParticleData, PlayerData, ParticleRunnable> paramBiFunction, boolean paramBoolean, long paramLong, String paramString1, StringValue... paramVarArgs) {
/* 52 */     this.func = paramBiFunction;
/* 53 */     this.override = paramBoolean;
/* 54 */     this.period = paramLong;
/* 55 */     this.lore = paramString1;
/*    */     
/* 57 */     for (StringValue stringValue : paramVarArgs)
/* 58 */       this.modifiers.put(stringValue.getName(), Double.valueOf(stringValue.getValue())); 
/*    */   }
/*    */   
/*    */   public String getDefaultName() {
/* 62 */     return MMOUtils.caseOnWords(name().toLowerCase().replace("_", " "));
/*    */   }
/*    */   
/*    */   public double getModifier(String paramString) {
/* 66 */     return ((Double)this.modifiers.get(paramString)).doubleValue();
/*    */   }
/*    */   
/*    */   public Set<String> getModifiers() {
/* 70 */     return this.modifiers.keySet();
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 74 */     return this.lore;
/*    */   }
/*    */   
/*    */   public boolean hasPriority() {
/* 78 */     return this.override;
/*    */   }
/*    */   
/*    */   public long getTime() {
/* 82 */     return this.period;
/*    */   }
/*    */   
/*    */   public ParticleRunnable newRunnable(ParticleData paramParticleData, PlayerData paramPlayerData) {
/* 86 */     return this.func.apply(paramParticleData, paramPlayerData);
/*    */   }
/*    */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\particle\api\ParticleType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */