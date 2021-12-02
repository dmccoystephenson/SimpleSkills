package dansplugins.simpleskills.objects.skills;

import dansplugins.simpleskills.objects.skills.abs.BlockBreakingSkill;
import org.bukkit.Material;

import java.util.HashSet;

/**
 * @author Daniel Stephenson
 */
public class Farming extends BlockBreakingSkill {
    public Farming(int ID) {
        super(ID, "Farming", 100, 10, 2);
        initialize();
    }

    private void initialize() {
        HashSet<Material> materials = new HashSet<>();
        materials.add(Material.WHEAT);
        materials.add(Material.BEETROOT);
        materials.add(Material.CARROT);
        materials.add(Material.POTATO);
        materials.add(Material.MELON);
        materials.add(Material.PUMPKIN);
        materials.add(Material.BAMBOO);
        materials.add(Material.COCOA_BEANS);
        materials.add(Material.SUGAR_CANE);
        materials.add(Material.SWEET_BERRIES);
        materials.add(Material.CHORUS_FRUIT);
        super.initializeAssociatedMaterials(materials);
    }
}