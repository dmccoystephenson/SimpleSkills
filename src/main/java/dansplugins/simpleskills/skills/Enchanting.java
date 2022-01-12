package dansplugins.simpleskills.skills;

import dansplugins.simpleskills.AbstractSkill;
import dansplugins.simpleskills.data.PlayerRecord;
import dansplugins.simpleskills.utils.ChanceCalculator;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Callum Johnson
 * @since 11/01/2022 - 16:06
 */
public class Enchanting extends AbstractSkill {

    /**
     * The Enchanting skill is levelled
     */
    public Enchanting() {
        super("Enchanting", EnchantItemEvent.class);
    }

    /**
     * Method to get the chance of a skill incrementing or levelling.
     *
     * @return double chance (1-100).
     * @see #randomExpGainChance()
     */
    @Override
    public double getChance() {
        return 0;
    }

    /**
     * Method to determine if a skill is chance-incremented/levelled.
     *
     * @return {@code true} or {@code false}.
     */
    @Override
    public boolean randomExpGainChance() {
        return false;
    }

    /**
     * Method to handle the {@link EnchantItemEvent} event.
     *
     * @param event to handle.
     */
    public void onEnchant(@NotNull EnchantItemEvent event) {
        incrementExperience(event.getEnchanter());
        executeReward(event.getEnchanter(), event);
    }

    /**
     * Method to reward the player at their level.
     *
     * @param player    to reward.
     * @param skillData assigned data to the skill reward, 'Block' for 'BlockSkills' etc.
     */
    @Override
    public void executeReward(@NotNull Player player, Object... skillData) {
        if (skillData.length != 1) throw new IllegalArgumentException("Skill Data is not of length '1'");
        final Object eventData = skillData[0];
        if (!(eventData instanceof EnchantItemEvent)) throw new IllegalArgumentException("Skill Data[0] is not EnchantEvent");
        final PlayerRecord record = getRecord(player);
        if (record == null) return;
        if (!ChanceCalculator.getInstance().roll(record, this, 0.10)) return;
        final EnchantItemEvent event = (EnchantItemEvent) skillData[0];
        final Random random = new Random();
        final List<Enchantment> enchants = new ArrayList<>(event.getEnchantsToAdd().keySet());
        Enchantment enchant = enchants.get(random.nextInt(enchants.size()));
        int level = event.getEnchantsToAdd().get(enchant);
        if (enchant.getMaxLevel() <= level + 1) {
            final List<Enchantment> collect = Arrays.stream(Enchantment.values())
                    .filter(e -> e.canEnchantItem(event.getItem()))
                    .collect(Collectors.toList());
            enchant = collect.get(random.nextInt(collect.size()));
            event.getEnchantsToAdd().put( enchant, random.nextInt(enchant.getMaxLevel()-1) + 1);
        } else event.getEnchantsToAdd().put(enchant, level + 1);
        level = event.getEnchantsToAdd().get(enchant);
        player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 5, 2);
        player.playSound(event.getEnchantBlock().getLocation(), Sound.ENTITY_ENDERMAN_AMBIENT, 5, 2);
        player.sendMessage("§bYou got lucky and got §6"
                + WordUtils.capitalizeFully(enchant.getKey().getKey().replaceAll("_", " ").toLowerCase())
                + " " + getRomanNumber(level));
    }

    /**
     * Method to get a Roman Equivalent from a number.
     *
     * @param number to convert.
     * @return Roman Numerals.
     */
    private String getRomanNumber(int number) {
        return getIs(number).replace("IIIII", "V").replace("IIII", "IV").replace("VV", "X")
                .replace("VIV", "IX").replace("XXXXX", "L");
    }

    /**
     * Method to convert 'number' into 'number' of "I"s.
     * <p>
     *      Converts 2 -> "II"<br>
     *      Converts 4 -> "IIII"<br>
     *      And so on...
     * </p>
     *
     * @param number to convert.
     * @return "I"s
     */
    @NotNull
    private String getIs(int number) {
        final StringBuilder builder = new StringBuilder();
        for (int index = 0; index < number; index++) builder.append("I");
        return builder.toString().trim();
    }

}
