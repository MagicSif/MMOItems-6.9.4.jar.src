/*     */ package net.Indyuce.mmoitems.comp.rpg;
/*     */ import com.herocraftonline.heroes.Heroes;
/*     */ import com.herocraftonline.heroes.api.SkillUseInfo;
/*     */ import com.herocraftonline.heroes.api.events.ClassChangeEvent;
/*     */ import com.herocraftonline.heroes.api.events.HeroChangeLevelEvent;
/*     */ import com.herocraftonline.heroes.characters.Hero;
/*     */ import com.herocraftonline.heroes.characters.skill.SkillType;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.api.player.EquipmentSlot;
/*     */ import io.lumine.mythic.lib.api.player.MMOPlayerData;
/*     */ import io.lumine.mythic.lib.api.stat.provider.StatProvider;
/*     */ import io.lumine.mythic.lib.damage.AttackHandler;
/*     */ import io.lumine.mythic.lib.damage.AttackMetadata;
/*     */ import io.lumine.mythic.lib.damage.DamageMetadata;
/*     */ import io.lumine.mythic.lib.damage.DamageType;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import net.Indyuce.mmoitems.ItemStats;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityDamageEvent;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class HeroesHook implements RPGHandler, Listener, AttackHandler {
/*  33 */   private final Map<SkillType, DamageType> damages = new HashMap<>();
/*     */   
/*  35 */   public static final ItemStat MAX_STAMINA = (ItemStat)new DoubleStat("MAX_STAMINA", Material.EMERALD, "Max Stamina", new String[] { "Adds stamina to your max stamina bar" });
/*     */   
/*     */   public HeroesHook() {
/*  38 */     MythicLib.plugin.getDamage().registerHandler(this);
/*     */     
/*  40 */     this.damages.put(SkillType.ABILITY_PROPERTY_PHYSICAL, DamageType.PHYSICAL);
/*  41 */     this.damages.put(SkillType.ABILITY_PROPERTY_MAGICAL, DamageType.MAGIC);
/*  42 */     this.damages.put(SkillType.ABILITY_PROPERTY_PROJECTILE, DamageType.PROJECTILE);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public AttackMetadata getAttack(EntityDamageEvent paramEntityDamageEvent) {
/*  48 */     SkillUseInfo skillUseInfo = Heroes.getInstance().getDamageManager().getSpellTargetInfo(paramEntityDamageEvent.getEntity());
/*  49 */     if (skillUseInfo == null || !(skillUseInfo.getCharacter().getEntity() instanceof Player)) {
/*  50 */       return null;
/*     */     }
/*  52 */     Player player = (Player)skillUseInfo.getCharacter().getEntity();
/*  53 */     Objects.requireNonNull(this.damages); Objects.requireNonNull(this.damages); DamageMetadata damageMetadata = new DamageMetadata(paramEntityDamageEvent.getDamage(), (DamageType[])skillUseInfo.getSkill().getTypes().stream().filter(this.damages::containsKey).map(this.damages::get).toArray(paramInt -> new DamageType[paramInt]));
/*  54 */     return new AttackMetadata(damageMetadata, (LivingEntity)paramEntityDamageEvent.getEntity(), (StatProvider)MMOPlayerData.get((OfflinePlayer)player).getStatMap().cache(EquipmentSlot.MAIN_HAND));
/*     */   }
/*     */ 
/*     */   
/*     */   public void refreshStats(PlayerData paramPlayerData) {
/*  59 */     Hero hero = Heroes.getInstance().getCharacterManager().getHero(paramPlayerData.getPlayer());
/*  60 */     hero.removeMaxMana("MMOItems");
/*  61 */     hero.addMaxMana("MMOItems", (int)paramPlayerData.getStats().getStat(ItemStats.MAX_MANA));
/*  62 */     hero.removeMaxStamina("MMOItems");
/*  63 */     hero.addMaxStamina("MMOItems", (int)paramPlayerData.getStats().getStat(MAX_STAMINA));
/*  64 */     hero.removeMaxHealth("MMOItems");
/*  65 */     hero.addMaxHealth("MMOItems", paramPlayerData.getStats().getStat(ItemStats.MAX_HEALTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public RPGPlayer getInfo(PlayerData paramPlayerData) {
/*  70 */     return new HeroesPlayer(paramPlayerData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void a(HeroChangeLevelEvent paramHeroChangeLevelEvent) {
/*  79 */     PlayerData.get((OfflinePlayer)paramHeroChangeLevelEvent.getHero().getPlayer()).getInventory().scheduleUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @EventHandler
/*     */   public void b(ClassChangeEvent paramClassChangeEvent) {
/*  88 */     PlayerData.get((OfflinePlayer)paramClassChangeEvent.getHero().getPlayer()).getInventory().scheduleUpdate();
/*     */   }
/*     */   
/*     */   public static class HeroesPlayer extends RPGPlayer {
/*     */     private final Hero hero;
/*     */     
/*     */     public HeroesPlayer(PlayerData param1PlayerData) {
/*  95 */       super(param1PlayerData);
/*  96 */       this.hero = Heroes.getInstance().getCharacterManager().getHero(getPlayer());
/*     */     }
/*     */ 
/*     */     
/*     */     public int getLevel() {
/* 101 */       return this.hero.getHeroLevel();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getClassName() {
/* 106 */       return this.hero.getHeroClass().getName();
/*     */     }
/*     */ 
/*     */     
/*     */     public double getMana() {
/* 111 */       return this.hero.getMana();
/*     */     }
/*     */ 
/*     */     
/*     */     public double getStamina() {
/* 116 */       return this.hero.getStamina();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setMana(double param1Double) {
/* 121 */       this.hero.setMana((int)param1Double);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setStamina(double param1Double) {
/* 126 */       this.hero.setStamina((int)param1Double);
/*     */     }
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\comp\rpg\HeroesHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */