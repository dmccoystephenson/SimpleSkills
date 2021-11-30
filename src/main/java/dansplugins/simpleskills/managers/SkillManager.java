package dansplugins.simpleskills.managers;

import dansplugins.simpleskills.data.PersistentData;
import dansplugins.simpleskills.objects.Skill;

public class SkillManager {
    private static SkillManager instance;

    private SkillManager() {

    }

    public static SkillManager getInstance() {
        if (instance == null) {
            instance = new SkillManager();
        }
        return instance;
    }

    public void initializeSkills() {
        // woodcutting
        PersistentData.getInstance().addSkill(new Skill(2, "Woodcutting", 100));
    }
}