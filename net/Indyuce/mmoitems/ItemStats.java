/*     */ package net.Indyuce.mmoitems;
/*     */ import io.lumine.mythic.lib.version.VersionMaterial;
/*     */ import net.Indyuce.mmoitems.stat.Commands;
/*     */ import net.Indyuce.mmoitems.stat.CompatibleMaterials;
/*     */ import net.Indyuce.mmoitems.stat.CompatibleTypes;
/*     */ import net.Indyuce.mmoitems.stat.ManaCost;
/*     */ import net.Indyuce.mmoitems.stat.RequiredBiomes;
/*     */ import net.Indyuce.mmoitems.stat.StaffSpiritStat;
/*     */ import net.Indyuce.mmoitems.stat.StoredTags;
/*     */ import net.Indyuce.mmoitems.stat.block.MaxXP;
/*     */ import net.Indyuce.mmoitems.stat.type.BooleanStat;
/*     */ import net.Indyuce.mmoitems.stat.type.DisableStat;
/*     */ import net.Indyuce.mmoitems.stat.type.DoubleStat;
/*     */ import net.Indyuce.mmoitems.stat.type.ItemStat;
/*     */ import org.bukkit.Material;
/*     */ 
/*     */ public class ItemStats {
/*  18 */   public static final ItemStat REVISION_ID = (ItemStat)new RevisionID();
/*  19 */   public static final ItemStat MATERIAL = (ItemStat)new MaterialStat();
/*  20 */   public static final ItemStat ITEM_DAMAGE = (ItemStat)new ItemDamage();
/*  21 */   public static final ItemStat CUSTOM_MODEL_DATA = (ItemStat)new CustomModelData();
/*  22 */   public static final ItemStat MAX_DURABILITY = (ItemStat)new MaximumDurability();
/*  23 */   public static final ItemStat WILL_BREAK = (ItemStat)new LostWhenBroken();
/*  24 */   public static final ItemStat NAME = (ItemStat)new DisplayName();
/*  25 */   public static final ItemStat LORE = (ItemStat)new Lore();
/*  26 */   public static final ItemStat NBT_TAGS = (ItemStat)new NBTTags();
/*  27 */   public static final ItemStat LORE_FORMAT = (ItemStat)new LoreFormat();
/*     */ 
/*     */   
/*  30 */   public static final ItemStat BLOCK_ID = (ItemStat)new BlockID();
/*  31 */   public static final ItemStat REQUIRED_POWER = (ItemStat)new RequiredPower();
/*  32 */   public static final ItemStat REQUIRE_POWER_TO_BREAK = (ItemStat)new RequirePowerToBreak();
/*  33 */   public static final ItemStat MIN_XP = (ItemStat)new MinXP();
/*  34 */   public static final ItemStat MAX_XP = (ItemStat)new MaxXP();
/*  35 */   public static final ItemStat GEN_TEMPLATE = (ItemStat)new GenTemplate();
/*     */ 
/*     */   
/*  38 */   public static final ItemStat DISPLAYED_TYPE = (ItemStat)new DisplayedType();
/*  39 */   public static final ItemStat ENCHANTS = (ItemStat)new Enchants();
/*  40 */   public static final ItemStat HIDE_ENCHANTS = (ItemStat)new HideEnchants();
/*  41 */   public static final ItemStat PERMISSION = (ItemStat)new Permission();
/*  42 */   public static final ItemStat ITEM_PARTICLES = (ItemStat)new ItemParticles();
/*  43 */   public static final ItemStat ARROW_PARTICLES = (ItemStat)new ArrowParticles();
/*  44 */   public static final ItemStat PROJECTILE_PARTICLES = (ItemStat)new ProjectileParticles();
/*     */ 
/*     */   
/*  47 */   public static final ItemStat DISABLE_INTERACTION = (ItemStat)new DisableStat("INTERACTION", VersionMaterial.GRASS_BLOCK.toMaterial(), "Disable Interaction", new String[] { "!block", "all" }, new String[] { "Disable any unwanted interaction:", "block placement, item use..." });
/*  48 */   public static final ItemStat DISABLE_CRAFTING = (ItemStat)new DisableStat("CRAFTING", VersionMaterial.CRAFTING_TABLE.toMaterial(), "Disable Crafting", new String[] { "Players can't use this item while crafting." }), DISABLE_SMELTING = (ItemStat)new DisableStat("SMELTING", Material.FURNACE, "Disable Smelting", new String[] { "Players can't use this item in furnaces." });
/*  49 */   public static final ItemStat DISABLE_SMITHING = (ItemStat)new DisableStat("SMITHING", Material.DAMAGED_ANVIL, "Disable Smithing", new String[] { "Players can't smith this item in smithing tables." });
/*  50 */   public static final ItemStat DISABLE_ENCHANTING = (ItemStat)new DisableStat("ENCHANTING", VersionMaterial.ENCHANTING_TABLE.toMaterial(), "Disable Enchanting", new String[] { "!block", "all" }, new String[] { "Players can't enchant this item." });
/*  51 */   public static final ItemStat DISABLE_REPAIRING = (ItemStat)new DisableStat("REPAIRING", Material.ANVIL, "Disable Repairing", new String[] { "!block", "all" }, new String[] { "Players can't use this item in anvils." });
/*  52 */   public static final ItemStat DISABLE_ARROW_SHOOTING = (ItemStat)new DisableStat("ARROW_SHOOTING", Material.ARROW, "Disable Arrow Shooting", new Material[] { Material.ARROW }, new String[] { "Players can't shoot this", "item using a bow." });
/*  53 */   public static final ItemStat DISABLE_ATTACK_PASSIVE = (ItemStat)new DisableStat("ATTACK_PASSIVE", Material.BARRIER, "Disable Attack Passive", new String[] { "piercing", "slashing", "blunt" }, new String[] { "Disables the blunt/slashing/piercing", "passive effects on attacks." });
/*  54 */   public static final ItemStat DISABLE_DROP = (ItemStat)new DisableStat("DROPING", Material.LAVA_BUCKET, "Disable Item Dropping", new String[] { "all" }, new String[] { "Disables the dropping of this item!" });
/*  55 */   public static final ItemStat DISABLE_ARROW_CONSUMPTION = (ItemStat)new DisableStat("ARROW_CONSUMPTION", Material.ARROW, "Disable Arrow Consumption", new String[] { "crossbow" }, new String[] { "Disable arrow requirement and consumption." });
/*     */ 
/*     */   
/*  58 */   public static final ItemStat REQUIRED_LEVEL = (ItemStat)new RequiredLevel();
/*  59 */   public static final ItemStat REQUIRED_CLASS = (ItemStat)new RequiredClass();
/*  60 */   public static final ItemStat ATTACK_DAMAGE = (ItemStat)new AttackDamage();
/*  61 */   public static final ItemStat ATTACK_SPEED = (ItemStat)new AttackSpeed();
/*  62 */   public static final ItemStat CRITICAL_STRIKE_CHANCE = (ItemStat)new DoubleStat("CRITICAL_STRIKE_CHANCE", Material.NETHER_STAR, "Critical Strike Chance", new String[] { "Critical Strikes deal more damage.", "In % chance." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  63 */   public static final ItemStat CRITICAL_STRIKE_POWER = (ItemStat)new DoubleStat("CRITICAL_STRIKE_POWER", Material.NETHER_STAR, "Critical Strike Power", new String[] { "The extra damage weapon crits deals.", "(Stacks with default value)", "In %." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  64 */   public static final ItemStat SKILL_CRITICAL_STRIKE_CHANCE = (ItemStat)new DoubleStat("SKILL_CRITICAL_STRIKE_CHANCE", Material.NETHER_STAR, "Skill Critical Strike Chance", new String[] { "Increases the chance of dealing skill crits (in %)." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  65 */   public static final ItemStat SKILL_CRITICAL_STRIKE_POWER = (ItemStat)new DoubleStat("SKILL_CRITICAL_STRIKE_POWER", Material.NETHER_STAR, "Skill Critical Strike Power", new String[] { "Extra damage dealt (in %) by skill crits.", "(Stacks with default value)", "In %." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  66 */   public static final ItemStat BLOCK_POWER = (ItemStat)new DoubleStat("BLOCK_POWER", Material.IRON_HELMET, "Block Power", new String[] { "The % of the damage your", "armor/shield can block.", "Default: 25%" }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  67 */   public static final ItemStat BLOCK_RATING = (ItemStat)new DoubleStat("BLOCK_RATING", Material.IRON_HELMET, "Block Rating", new String[] { "The chance your piece of armor", "has to block any entity attack." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  68 */   public static final ItemStat BLOCK_COOLDOWN_REDUCTION = (ItemStat)new DoubleStat("BLOCK_COOLDOWN_REDUCTION", Material.IRON_HELMET, "Block Cooldown Reduction", new String[] { "Reduces the blocking cooldown (%)." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  69 */   public static final ItemStat DODGE_RATING = (ItemStat)new DoubleStat("DODGE_RATING", Material.FEATHER, "Dodge Rating", new String[] { "The chance to dodge an attack.", "Dodging completely negates", "the attack damage." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  70 */   public static final ItemStat DODGE_COOLDOWN_REDUCTION = (ItemStat)new DoubleStat("DODGE_COOLDOWN_REDUCTION", Material.FEATHER, "Dodge Cooldown Reduction", new String[] { "Reduces the dodging cooldown (%)." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  71 */   public static final ItemStat PARRY_RATING = (ItemStat)new DoubleStat("PARRY_RATING", Material.BUCKET, "Parry Rating", new String[] { "The chance to parry an attack.", "Parrying negates the damage", "and knocks the attacker back." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  72 */   public static final ItemStat PARRY_COOLDOWN_REDUCTION = (ItemStat)new DoubleStat("PARRY_COOLDOWN_REDUCTION", Material.BUCKET, "Parry Cooldown Reduction", new String[] { "Reduces the parrying cooldown (%)." }, new String[] { "!miscellaneous", "!block", "all" }, new Material[0]);
/*  73 */   public static final ItemStat COOLDOWN_REDUCTION = (ItemStat)new DoubleStat("COOLDOWN_REDUCTION", Material.BOOK, "Cooldown Reduction", new String[] { "Reduces cooldowns of item and player skills (%)." });
/*  74 */   public static final ItemStat RANGE = (ItemStat)new DoubleStat("RANGE", Material.STICK, "Range", new String[] { "The range of your item attacks." }, new String[] { "staff", "whip", "wand", "musket", "gem_stone" }, new Material[0]);
/*  75 */   public static final ItemStat MANA_COST = (ItemStat)new ManaCost();
/*  76 */   public static final ItemStat STAMINA_COST = (ItemStat)new DoubleStat("STAMINA_COST", VersionMaterial.LIGHT_GRAY_DYE.toMaterial(), "Stamina Cost", new String[] { "Stamina spent by your weapon to be used." }, new String[] { "piercing", "slashing", "blunt", "range" }, new Material[0]);
/*  77 */   public static final ItemStat ARROW_VELOCITY = (ItemStat)new DoubleStat("ARROW_VELOCITY", Material.ARROW, "Arrow Velocity", new String[] { "Determines how far your", "weapon can shoot.", "Default: 1.0" }, new String[] { "gem_stone", "bow", "crossbow" }, new Material[0]);
/*  78 */   public static final ItemStat ARROW_POTION_EFFECTS = (ItemStat)new ArrowPotionEffects();
/*  79 */   public static final ItemStat PVE_DAMAGE = (ItemStat)new DoubleStat("PVE_DAMAGE", VersionMaterial.PORKCHOP.toMaterial(), "PvE Damage", new String[] { "Additional damage against", "non human entities in %." }, new String[] { "piercing", "slashing", "blunt", "catalyst", "range", "tool", "armor", "gem_stone", "accessory" }, new Material[0]);
/*  80 */   public static final ItemStat PVP_DAMAGE = (ItemStat)new DoubleStat("PVP_DAMAGE", VersionMaterial.SKELETON_SKULL.toMaterial(), "PvP Damage", new String[] { "Additional damage", "against players in %." }, new String[] { "piercing", "slashing", "blunt", "catalyst", "range", "tool", "armor", "gem_stone", "accessory" }, new Material[0]);
/*  81 */   public static final ItemStat BLUNT_POWER = (ItemStat)new DoubleStat("BLUNT_POWER", Material.IRON_AXE, "Blunt Power", new String[] { "The radius of the AoE attack.", "If set to 2.0, enemies within 2 blocks", "around your target will take damage." }, new String[] { "blunt", "gem_stone" }, new Material[0]);
/*  82 */   public static final ItemStat BLUNT_RATING = (ItemStat)new DoubleStat("BLUNT_RATING", Material.BRICK, "Blunt Rating", new String[] { "The force of the blunt attack.", "If set to 50%, enemies hit by the attack", "will take 50% of the initial damage." }, new String[] { "blunt", "gem_stone" }, new Material[0]);
/*  83 */   public static final ItemStat WEAPON_DAMAGE = (ItemStat)new DoubleStat("WEAPON_DAMAGE", Material.IRON_SWORD, "Weapon Damage", new String[] { "Additional on-hit weapon damage in %." });
/*  84 */   public static final ItemStat SKILL_DAMAGE = (ItemStat)new DoubleStat("SKILL_DAMAGE", Material.BOOK, "Skill Damage", new String[] { "Additional ability damage in %." });
/*  85 */   public static final ItemStat PROJECTILE_DAMAGE = (ItemStat)new DoubleStat("PROJECTILE_DAMAGE", Material.ARROW, "Projectile Damage", new String[] { "Additional skill/weapon projectile damage." });
/*  86 */   public static final ItemStat MAGIC_DAMAGE = (ItemStat)new DoubleStat("MAGIC_DAMAGE", Material.MAGMA_CREAM, "Magic Damage", new String[] { "Additional magic skill damage in %." });
/*  87 */   public static final ItemStat PHYSICAL_DAMAGE = (ItemStat)new DoubleStat("PHYSICAL_DAMAGE", Material.IRON_AXE, "Physical Damage", new String[] { "Additional skill/weapon physical damage." });
/*  88 */   public static final ItemStat DEFENSE = (ItemStat)new DoubleStat("DEFENSE", Material.SHIELD, "Defense", new String[] { "Reduces damage from any source.", "Formula can be set in MMOLib Config." });
/*  89 */   public static final ItemStat DAMAGE_REDUCTION = (ItemStat)new DoubleStat("DAMAGE_REDUCTION", Material.IRON_CHESTPLATE, "Damage Reduction", new String[] { "Reduces damage from any source.", "In %." });
/*  90 */   public static final ItemStat FALL_DAMAGE_REDUCTION = (ItemStat)new DoubleStat("FALL_DAMAGE_REDUCTION", Material.FEATHER, "Fall Damage Reduction", new String[] { "Reduces fall damage.", "In %." });
/*  91 */   public static final ItemStat PROJECTILE_DAMAGE_REDUCTION = (ItemStat)new DoubleStat("PROJECTILE_DAMAGE_REDUCTION", VersionMaterial.SNOWBALL.toMaterial(), "Projectile Damage Reduction", new String[] { "Reduces projectile damage.", "In %." });
/*  92 */   public static final ItemStat PHYSICAL_DAMAGE_REDUCTION = (ItemStat)new DoubleStat("PHYSICAL_DAMAGE_REDUCTION", Material.LEATHER_CHESTPLATE, "Physical Damage Reduction", new String[] { "Reduces physical damage.", "In %." });
/*  93 */   public static final ItemStat FIRE_DAMAGE_REDUCTION = (ItemStat)new DoubleStat("FIRE_DAMAGE_REDUCTION", Material.BLAZE_POWDER, "Fire Damage Reduction", new String[] { "Reduces fire damage.", "In %." });
/*  94 */   public static final ItemStat MAGIC_DAMAGE_REDUCTION = (ItemStat)new DoubleStat("MAGIC_DAMAGE_REDUCTION", Material.POTION, "Magic Damage Reduction", new String[] { "Reduce magic damage dealt by potions.", "In %." });
/*  95 */   public static final ItemStat PVE_DAMAGE_REDUCTION = (ItemStat)new DoubleStat("PVE_DAMAGE_REDUCTION", VersionMaterial.PORKCHOP.toMaterial(), "PvE Damage Reduction", new String[] { "Reduces damage dealt by mobs.", "In %." });
/*  96 */   public static final ItemStat PVP_DAMAGE_REDUCTION = (ItemStat)new DoubleStat("PVP_DAMAGE_REDUCTION", VersionMaterial.SKELETON_SKULL.toMaterial(), "PvP Damage Reduction", new String[] { "Reduces damage dealt by players", "In %." });
/*  97 */   public static final ItemStat UNDEAD_DAMAGE = (ItemStat)new DoubleStat("UNDEAD_DAMAGE", VersionMaterial.SKELETON_SKULL.toMaterial(), "Undead Damage", new String[] { "Deals additional damage to undead.", "In %." });
/*  98 */   public static final ItemStat LIFESTEAL = (ItemStat)new DoubleStat("LIFESTEAL", Material.REDSTONE, "Lifesteal", new String[] { "Percentage of damage you gain back as", "health when inflicting weapon damage." });
/*  99 */   public static final ItemStat SPELL_VAMPIRISM = (ItemStat)new DoubleStat("SPELL_VAMPIRISM", Material.REDSTONE, "Spell Vampirism", new String[] { "Percentage of damage you gain back as", "health when inflicting skill damage." });
/*     */ 
/*     */   
/* 102 */   public static final ItemStat UNBREAKABLE = (ItemStat)new Unbreakable();
/* 103 */   public static final ItemStat TIER = (ItemStat)new ItemTierStat();
/* 104 */   public static final ItemStat SET = (ItemStat)new ItemSetStat();
/* 105 */   public static final ItemStat ARMOR = (ItemStat)new DoubleStat("ARMOR", VersionMaterial.GOLDEN_CHESTPLATE.toMaterial(), "Armor", new String[] { "The armor given to the holder." });
/* 106 */   public static final ItemStat ARMOR_TOUGHNESS = (ItemStat)new DoubleStat("ARMOR_TOUGHNESS", Material.DIAMOND_CHESTPLATE, "Armor Toughness", new String[] { "Armor toughness reduces damage taken." });
/* 107 */   public static final ItemStat MAX_HEALTH = (ItemStat)new DoubleStat("MAX_HEALTH", Material.GOLDEN_APPLE, "Max Health", new String[] { "The amount of health your", "item gives to the holder." });
/* 108 */   public static final ItemStat UNSTACKABLE = (ItemStat)new Unstackable();
/* 109 */   public static final ItemStat MAX_MANA = (ItemStat)new DoubleStat("MAX_MANA", VersionMaterial.LAPIS_LAZULI.toMaterial(), "Max Mana", new String[] { "Adds mana to your max mana bar." });
/* 110 */   public static final ItemStat KNOCKBACK_RESISTANCE = (ItemStat)new KnockbackResistance();
/* 111 */   public static final ItemStat MOVEMENT_SPEED = (ItemStat)new MovementSpeed();
/* 112 */   public static final ItemStat TWO_HANDED = (ItemStat)new BooleanStat("TWO_HANDED", Material.IRON_INGOT, "Two Handed", new String[] { "If set to true, a player will be", "significantly slower if holding two", "items, one being Two Handed." }, new String[] { "piercing", "slashing", "blunt", "catalyst", "range", "tool" }, new Material[0]);
/* 113 */   public static final ItemStat REQUIRED_BIOMES = (ItemStat)new RequiredBiomes();
/* 114 */   public static final ItemStat DROP_ON_DEATH = (ItemStat)new DisableDeathDrop();
/* 115 */   public static final ItemStat DURABILITY_BAR = (ItemStat)new DurabilityBar();
/*     */ 
/*     */   
/* 118 */   public static final ItemStat PERM_EFFECTS = (ItemStat)new PermanentEffects();
/* 119 */   public static final ItemStat GRANTED_PERMISSIONS = (ItemStat)new GrantedPermissions();
/*     */ 
/*     */   
/* 122 */   public static final ItemStat RESTORE_HEALTH = (ItemStat)new RestoreHealth();
/* 123 */   public static final ItemStat RESTORE_FOOD = (ItemStat)new RestoreFood();
/* 124 */   public static final ItemStat RESTORE_SATURATION = (ItemStat)new RestoreSaturation();
/* 125 */   public static final ItemStat RESTORE_MANA = (ItemStat)new RestoreMana();
/* 126 */   public static final ItemStat RESTORE_STAMINA = (ItemStat)new RestoreStamina();
/* 127 */   public static final ItemStat CAN_IDENTIFY = (ItemStat)new CanIdentify();
/* 128 */   public static final ItemStat CAN_DECONSTRUCT = (ItemStat)new CanDeconstruct();
/* 129 */   public static final ItemStat CAN_DESKIN = (ItemStat)new CanDeskin();
/* 130 */   public static final ItemStat EFFECTS = (ItemStat)new Effects();
/* 131 */   public static final ItemStat SOULBINDING_CHANCE = (ItemStat)new SoulbindingChance();
/* 132 */   public static final ItemStat SOULBOUND_BREAK_CHANCE = (ItemStat)new SoulbindingBreakChance();
/* 133 */   public static final ItemStat SOULBOUND_LEVEL = (ItemStat)new SoulboundLevel();
/*     */   
/* 135 */   public static final ItemStat ITEM_COOLDOWN = (ItemStat)new DoubleStat("ITEM_COOLDOWN", Material.COOKED_CHICKEN, "Item Cooldown", new String[] { "This cooldown applies for consumables", "as well as for item commands." }, new String[] { "!armor", "!gem_stone", "!block", "all" }, new Material[0]);
/* 136 */   public static final ItemStat COOLDOWN_REFERENCE = (ItemStat)new StringStat("COOLDOWN_REFERENCE", Material.CHICKEN, "Cooldown Reference", new String[] { "Two items with the same cooldown reference", "will share their cooldowns. This is useful", "for health or mana pots for example." }, new String[] { "!armor", "!gem_stone", "!block", "all" }, new Material[0]);
/* 137 */   public static final ItemStat VANILLA_EATING_ANIMATION = (ItemStat)new VanillaEatingAnimation();
/* 138 */   public static final ItemStat GEM_COLOR = (ItemStat)new GemColor();
/* 139 */   public static final ItemStat GEM_UPGRADE_SCALING = (ItemStat)new GemUpgradeScaling();
/* 140 */   public static final ItemStat ITEM_TYPE_RESTRICTION = (ItemStat)new ItemTypeRestriction();
/* 141 */   public static final ItemStat MAX_CONSUME = (ItemStat)new MaxConsume();
/* 142 */   public static final ItemStat SUCCESS_RATE = (ItemStat)new SuccessRate();
/*     */ 
/*     */   
/* 145 */   public static final ItemStat CRAFTING = (ItemStat)new Crafting();
/* 146 */   public static final ItemStat CRAFT_PERMISSION = (ItemStat)new CraftingPermission();
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final ItemStat AUTOSMELT = (ItemStat)new BooleanStat("AUTOSMELT", Material.COAL, "Autosmelt", new String[] { "If set to true, your tool will", "automaticaly smelt mined ores." }, new String[] { "tool" }, new Material[0]);
/* 151 */   public static final ItemStat BOUNCING_CRACK = (ItemStat)new BooleanStat("BOUNCING_CRACK", VersionMaterial.COBBLESTONE_WALL.toMaterial(), "Bouncing Crack", new String[] { "If set to true, your tool will", "also break nearby blocks." }, new String[] { "tool" }, new Material[0]);
/* 152 */   public static final ItemStat PICKAXE_POWER = (ItemStat)new PickaxePower();
/* 153 */   public static final ItemStat CUSTOM_SOUNDS = (ItemStat)new CustomSounds();
/* 154 */   public static final ItemStat ELEMENTS = (ItemStat)new Elements();
/* 155 */   public static final ItemStat COMMANDS = (ItemStat)new Commands();
/* 156 */   public static final ItemStat STAFF_SPIRIT = (ItemStat)new StaffSpiritStat();
/* 157 */   public static final ItemStat LUTE_ATTACK_SOUND = (ItemStat)new LuteAttackSoundStat();
/* 158 */   public static final ItemStat LUTE_ATTACK_EFFECT = (ItemStat)new LuteAttackEffectStat();
/* 159 */   public static final ItemStat NOTE_WEIGHT = (ItemStat)new DoubleStat("NOTE_WEIGHT", VersionMaterial.MUSIC_DISC_MALL.toMaterial(), "Note Weight", new String[] { "Defines how the projectile cast", "by your lute tilts downwards." }, new String[] { "lute" }, new Material[0]);
/* 160 */   public static final ItemStat REMOVE_ON_CRAFT = (ItemStat)new BooleanStat("REMOVE_ON_CRAFT", Material.GLASS_BOTTLE, "Remove on Craft", new String[] { "If the item should be completely", "removed when used in a recipe,", "or if it should become an", "empty bottle or bucket." }, new String[] { "all" }, new Material[] { Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION, Material.MILK_BUCKET, Material.LAVA_BUCKET, Material.WATER_BUCKET });
/* 161 */   public static final ItemStat COMPATIBLE_TYPES = (ItemStat)new CompatibleTypes();
/* 162 */   public static final ItemStat COMPATIBLE_IDS = (ItemStat)new CompatibleIds();
/* 163 */   public static final ItemStat COMPATIBLE_MATERIALS = (ItemStat)new CompatibleMaterials();
/* 164 */   public static final ItemStat GEM_SOCKETS = (ItemStat)new GemSockets();
/* 165 */   public static final ItemStat RANDOM_UNSOCKET = (ItemStat)new RandomUnsocket();
/*     */   
/* 167 */   public static final ItemStat REPAIR = (ItemStat)new RepairPower();
/* 168 */   public static final ItemStat REPAIR_PERCENT = (ItemStat)new RepairPowerPercent();
/* 169 */   public static final ItemStat REPAIR_TYPE = (ItemStat)new RepairReference();
/* 170 */   public static final ItemStat INEDIBLE = (ItemStat)new BooleanStat("INEDIBLE", Material.POISONOUS_POTATO, "Inedible", new String[] { "Players won't be able to right-click this consumable.", "", "No effects of it will take place." }, new String[] { "consumable" }, new Material[0]);
/* 171 */   public static final ItemStat DISABLE_RIGHT_CLICK_CONSUME = (ItemStat)new DisableStat("RIGHT_CLICK_CONSUME", Material.BAKED_POTATO, "Infinite Consume", new String[] { "consumable" }, new String[] { "Players will be able to right-click this consumable", "and benefit from its effects, but it won't be consumed." });
/* 172 */   public static final ItemStat KNOCKBACK = (ItemStat)new DoubleStat("KNOCKBACK", VersionMaterial.IRON_HORSE_ARMOR.toMaterial(), "Knockback", new String[] { "Using this musket will knock", "the user back if positive." }, new String[] { "musket", "gem_stone" }, new Material[0]);
/* 173 */   public static final ItemStat RECOIL = (ItemStat)new DoubleStat("RECOIL", VersionMaterial.IRON_HORSE_ARMOR.toMaterial(), "Recoil", new String[] { "Corresponds to the shooting innacuracy." }, new String[] { "musket", "gem_stone" }, new Material[0]);
/* 174 */   public static final ItemStat HANDWORN = (ItemStat)new BooleanStat("HANDWORN", Material.STRING, "Handworn", new String[] { "This item ignores two-handedness.", "", "Basically for a ring or a glove that you", " can wear and still have your hand free", " to carry a two-handed weapon." }, new String[] { "catalyst" }, new Material[0]);
/* 175 */   public static final ItemStat AMPHIBIAN = (ItemStat)new Amphibian();
/*     */ 
/*     */   
/* 178 */   public static final ItemStat ABILITIES = (ItemStat)new Abilities();
/* 179 */   public static final ItemStat UPGRADE = (ItemStat)new UpgradeStat();
/* 180 */   public static final ItemStat DOWNGRADE_ON_BREAK = (ItemStat)new BooleanStat("BREAK_DOWNGRADE", Material.DAMAGED_ANVIL, "Downgrade when Broken", new String[] { "If this item's durability reaches 0,", "it will be fully repaired but also", "downgraded by one level.", "", "&cIt will only break if it cannot be", "&cdowngraded further", "", "Requires to define an &6Upgrade Template", "Required to define &6Custom Durability" }, new String[] { "piercing", "slashing", "blunt", "catalyst", "range", "tool", "armor", "consumable", "accessory" }, new Material[0]);
/* 181 */   public static final ItemStat DOWNGRADE_ON_DEATH = (ItemStat)new BooleanStat("DEATH_DOWNGRADE", Material.DAMAGED_ANVIL, "Downgrade on Death", new String[] { "If the wearer of this item dies, it may", "downgrade (based on &6Death Downgrade", "&6Chance &7stat)", "", "Required to define an &6Upgrade Template", "Requires keep-inventory gamerule. " }, new String[] { "piercing", "slashing", "blunt", "catalyst", "range", "tool", "armor", "consumable", "accessory" }, new Material[0]);
/* 182 */   public static final ItemStat DOWNGRADE_ON_DEATH_CHANCE = (ItemStat)new DoubleStat("DEATH_DOWNGRADE_CHANCE", Material.SKELETON_SKULL, "Death Downgrade Chance", new String[] { "Probability that an item with &cDowngrade ", "&con Death&7 will be downgraded when the", "player dies. ", "", "Exceeding 100% will for sure downgrade", "one item, and roll again to downgrade", "another (with the excess probability).", "&6The same item wont be downgraded twice." }, new String[] { "!miscellaneous", "!block", "all" }, false, new Material[0]);
/*     */ 
/*     */   
/* 185 */   public static final ItemStat SKULL_TEXTURE = (ItemStat)new SkullTextureStat();
/* 186 */   public static final ItemStat DYE_COLOR = (ItemStat)new DyeColor();
/* 187 */   public static final ItemStat HIDE_DYE = (ItemStat)new HideDye();
/* 188 */   public static final ItemStat TRIM_MATERIAL = (ItemStat)new TrimMaterialStat();
/* 189 */   public static final ItemStat TRIM_PATTERN = (ItemStat)new TrimPatternStat();
/* 190 */   public static final ItemStat HIDE_ARMOR_TRIM = (ItemStat)new HideTrim();
/* 191 */   public static final ItemStat POTION_EFFECTS = (ItemStat)new PotionEffects();
/* 192 */   public static final ItemStat POTION_COLOR = (ItemStat)new PotionColor();
/* 193 */   public static final ItemStat SHIELD_PATTERN = (ItemStat)new ShieldPatternStat();
/* 194 */   public static final ItemStat HIDE_POTION_EFFECTS = (ItemStat)new HidePotionEffects();
/*     */ 
/*     */   
/* 197 */   public static final ItemStat SOULBOUND = (ItemStat)new Soulbound();
/* 198 */   public static final ItemStat CUSTOM_DURABILITY = (ItemStat)new CustomDurability();
/* 199 */   public static final ItemStat STORED_TAGS = (ItemStat)new StoredTags();
/* 200 */   public static final ItemStat ITEM_LEVEL = (ItemStat)new ItemLevel();
/* 201 */   public static final ItemStat BROWSER_DISPLAY_IDX = (ItemStat)new BrowserDisplayIDX();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/* 208 */   public static final ItemStat DURABILITY = ITEM_DAMAGE;
/*     */ }


/* Location:              O:\QQ\203636290\FileRecv\MMOItems-6.9.4.jar!\net\Indyuce\mmoitems\ItemStats.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */