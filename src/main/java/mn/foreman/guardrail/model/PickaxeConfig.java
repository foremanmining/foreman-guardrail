package mn.foreman.guardrail.model;

import lombok.Data;

/** A pickaxe configuration. */
@Data
public class PickaxeConfig {

    /** The API key. */
    public String apiKey;

    /** The client ID. */
    public Integer clientId;

    /** Whether or not control is allowed. */
    public Boolean control = true;

    /** The IP ranges. */
    public String ipRanges = "0-255.0-255.0-255.0-255";
}
