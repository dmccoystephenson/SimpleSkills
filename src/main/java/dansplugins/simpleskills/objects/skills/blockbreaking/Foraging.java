package dansplugins.simpleskills.objects.skills.blockbreaking;

import dansplugins.simpleskills.enums.SupportedSkill;
import dansplugins.simpleskills.objects.abs.BlockBreakingSkill;
import dansplugins.simpleskills.objects.benefits.DoubleDrop;
import org.bukkit.Material;

import java.util.HashSet;

/**
 * @author Daniel Stephenson
 */
public class Foraging extends BlockBreakingSkill {
    public Foraging() {
        super(SupportedSkill.FORAGING.ordinal(), "Foraging");
        initialize();
    }

    private void initialize() {
        addBenefit(new DoubleDrop());

        HashSet<Material> materials = new HashSet<>();
        materials.add(Material.SWEET_BERRIES);
        materials.add(Material.BROWN_MUSHROOM);
        materials.add(Material.RED_MUSHROOM);
        materials.add(Material.KELP_PLANT);
        materials.add(Material.CRIMSON_FUNGUS);
        materials.add(Material.WARPED_FUNGUS);
        materials.add(Material.GLOW_BERRIES);
        materials.add(Material.CACTUS);
        materials.add(Material.SEA_PICKLE);
        super.initializeAssociatedMaterials(materials);
    }
}