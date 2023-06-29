/*     */ package net.Indyuce.mmoitems.stat.data;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import io.lumine.mythic.lib.MythicLib;
/*     */ import io.lumine.mythic.lib.UtilityMethods;
/*     */ import io.lumine.mythic.lib.player.cooldown.CooldownInfo;
/*     */ import io.lumine.mythic.lib.player.cooldown.CooldownObject;
/*     */ import io.lumine.mythic.lib.skill.Skill;
/*     */ import io.lumine.mythic.lib.skill.SkillMetadata;
/*     */ import io.lumine.mythic.lib.skill.handler.SkillHandler;
/*     */ import io.lumine.mythic.lib.skill.trigger.TriggerType;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import net.Indyuce.mmoitems.MMOItems;
/*     */ import net.Indyuce.mmoitems.api.player.PlayerData;
/*     */ import net.Indyuce.mmoitems.api.player.RPGPlayer;
/*     */ import net.Indyuce.mmoitems.api.util.message.Message;
/*     */ import net.Indyuce.mmoitems.skill.RegisteredSkill;
/*     */ import net.Indyuce.mmoitems.util.MMOUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbilityData
/*     */   extends Skill
/*     */ {
/*     */   private final RegisteredSkill ability;
/*     */   @NotNull
/*  36 */   private final Map<String, Double> modifiers = new HashMap<>();
/*     */ 
/*     */   
/*     */   public AbilityData(@NotNull JsonObject paramJsonObject) {
/*  40 */     super(MMOUtils.backwardsCompatibleTriggerType(paramJsonObject.get("CastMode").getAsString()));
/*     */     
/*  42 */     this.ability = MMOItems.plugin.getSkills().getSkill(paramJsonObject.get("Id").getAsString());
/*     */     
/*  44 */     JsonObject jsonObject = paramJsonObject.getAsJsonObject("Modifiers");
/*  45 */     jsonObject.entrySet().forEach(paramEntry -> setModifier((String)paramEntry.getKey(), ((JsonElement)paramEntry.getValue()).getAsDouble()));
/*     */   }
/*     */   
/*     */   public AbilityData(@NotNull ConfigurationSection paramConfigurationSection) {
/*  49 */     super(MMOUtils.backwardsCompatibleTriggerType(UtilityMethods.enumName(Objects.<String>requireNonNull(paramConfigurationSection.getString("mode"), "Ability is missing mode"))));
/*     */     
/*  51 */     Validate.isTrue(paramConfigurationSection.contains("type"), "Ability is missing type");
/*     */     
/*  53 */     String str = UtilityMethods.enumName(paramConfigurationSection.getString("type"));
/*  54 */     Validate.isTrue(MMOItems.plugin.getSkills().hasSkill(str), "Could not find ability called '" + str + "'");
/*  55 */     this.ability = MMOItems.plugin.getSkills().getSkill(str);
/*     */     
/*  57 */     for (String str1 : paramConfigurationSection.getKeys(false)) {
/*  58 */       if (!str1.equalsIgnoreCase("mode") && !str1.equalsIgnoreCase("type") && this.ability.getHandler().getModifiers().contains(str1))
/*  59 */         this.modifiers.put(str1, Double.valueOf(paramConfigurationSection.getDouble(str1))); 
/*     */     } 
/*     */   }
/*     */   public AbilityData(RegisteredSkill paramRegisteredSkill, TriggerType paramTriggerType) {
/*  63 */     super(paramTriggerType);
/*     */     
/*  65 */     this.ability = paramRegisteredSkill;
/*     */   }
/*     */   
/*     */   public RegisteredSkill getAbility() {
/*  69 */     return this.ability;
/*     */   }
/*     */   
/*     */   public Set<String> getModifiers() {
/*  73 */     return this.modifiers.keySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setModifier(String paramString, double paramDouble) {
/*  78 */     this.modifiers.put(paramString, Double.valueOf(paramDouble));
/*     */   }
/*     */   
/*     */   public boolean hasModifier(String paramString) {
/*  82 */     return this.modifiers.containsKey(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getResult(SkillMetadata paramSkillMetadata) {
/*  88 */     PlayerData playerData = PlayerData.get(paramSkillMetadata.getCaster().getData().getUniqueId());
/*  89 */     RPGPlayer rPGPlayer = playerData.getRPG();
/*  90 */     Player player = paramSkillMetadata.getCaster().getPlayer();
/*     */ 
/*     */     
/*  93 */     if (paramSkillMetadata.getCaster().getData().getCooldownMap().isOnCooldown((CooldownObject)this)) {
/*  94 */       CooldownInfo cooldownInfo = playerData.getMMOPlayerData().getCooldownMap().getInfo((CooldownObject)this);
/*  95 */       if (!getTrigger().isSilent()) {
/*  96 */         StringBuilder stringBuilder = new StringBuilder(ChatColor.YELLOW + "");
/*  97 */         double d = (cooldownInfo.getInitialCooldown() - cooldownInfo.getRemaining()) / cooldownInfo.getInitialCooldown() * 10.0D;
/*  98 */         String str = MMOItems.plugin.getConfig().getString("cooldown-progress-bar-char");
/*  99 */         for (byte b = 0; b < 10; b++)
/* 100 */           stringBuilder.append((d >= b) ? ChatColor.GREEN : ChatColor.WHITE).append(str); 
/* 101 */         Message.SPELL_ON_COOLDOWN.format(ChatColor.RED, new String[] { "#left#", (MythicLib.plugin.getMMOConfig()).decimal.format(cooldownInfo.getRemaining() / 1000.0D), "#progress#", stringBuilder.toString(), "#s#", (cooldownInfo.getRemaining() > 1999L) ? "s" : "" }).send(player);
/*     */       } 
/* 103 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 107 */     if (MMOItems.plugin.getConfig().getBoolean("permissions.abilities") && !player.hasPermission("mmoitems.ability." + getHandler().getLowerCaseId()) && !player.hasPermission("mmoitems.bypass.ability")) {
/* 108 */       return false;
/*     */     }
/*     */     
/* 111 */     if (hasModifier("mana") && rPGPlayer.getMana() < getParameter("mana")) {
/* 112 */       Message.NOT_ENOUGH_MANA.format(ChatColor.RED, new String[0]).send(player);
/* 113 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 117 */     if (hasModifier("stamina") && rPGPlayer.getStamina() < getParameter("stamina")) {
/* 118 */       Message.NOT_ENOUGH_STAMINA.format(ChatColor.RED, new String[0]).send(player);
/* 119 */       return false;
/*     */     } 
/*     */     
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void whenCast(SkillMetadata paramSkillMetadata) {
/* 127 */     PlayerData playerData = PlayerData.get(paramSkillMetadata.getCaster().getData().getUniqueId());
/* 128 */     RPGPlayer rPGPlayer = playerData.getRPG();
/*     */ 
/*     */     
/* 131 */     if (hasModifier("mana")) rPGPlayer.giveMana(-paramSkillMetadata.getParameter("mana"));
/*     */ 
/*     */     
/* 134 */     if (hasModifier("stamina")) rPGPlayer.giveStamina(-paramSkillMetadata.getParameter("stamina"));
/*     */ 
/*     */     
/* 137 */     double d = paramSkillMetadata.getParameter("cooldown") * (1.0D - Math.min(0.8D, paramSkillMetadata.getCaster().getStat("COOLDOWN_REDUCTION") / 100.0D));
/* 138 */     if (d > 0.0D) paramSkillMetadata.getCaster().getData().getCooldownMap().applyCooldown((CooldownObject)this, d);
/*     */   
/*     */   }
/*     */   
/*     */   public SkillHandler getHandler() {
/* 143 */     return this.ability.getHandler();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getParameter(String paramString) {
/* 148 */     return ((Double)this.modifiers.getOrDefault(paramString, Double.valueOf(this.ability.getDefaultModifier(paramString)))).doubleValue();
/*     */   }
/*     */   
/*     */   public JsonObject toJson() {
/* 152 */     JsonObject jsonObject1 = new JsonObject();
/* 153 */     jsonObject1.addProperty("Id", this.ability.getHandler().getId());
/* 154 */     jsonObject1.addProperty("CastMode", getTrigger().name());
/*     */     
/* 156 */     JsonObject jsonObject2 = new JsonObject();
/* 157 */     this.modifiers.keySet().forEach(paramString -> paramJsonObject.addProperty(paramString, Double.valueOf(getParameter(paramString))));
/* 158 */     jsonObject1.add("Modifiers", (JsonElement)jsonObject2);
/*     */     
/* 160 */     return jsonObject1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 165 */     if (this == paramObject) return true; 
/* 166 */     if (paramObject == null || getClass() != paramObject.getClass()) return false; 
/* 167 */     AbilityData abilityData = (AbilityData)paramObject;
/* 168 */     return (this.ability.equals(abilityData.ability) && getTrigger().equals(abilityData.getTrigger()) && this.modifiers.equals(abilityData.modifiers));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 173 */     return Objects.hash(new Object[] { this.ability, this.modifiers });
/*     */   }
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\stat\data\AbilityData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */