package dansplugins.simpleskills.objects.benefits;

import dansplugins.simpleskills.enums.SupportedBenefit;
import dansplugins.simpleskills.objects.abs.Benefit;

/**
 * @author Daniel Stephenson
 */
public class ResourceExtraction extends Benefit {
    public ResourceExtraction() {
        super(SupportedBenefit.RESOURCE_EXTRACTION.ordinal(), "Resource Extraction");
    }
}