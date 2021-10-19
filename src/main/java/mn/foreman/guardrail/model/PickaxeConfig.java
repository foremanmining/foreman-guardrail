package mn.foreman.guardrail.model;

import lombok.Data;

/** A pickaxe configuration. */
@Data
public class PickaxeConfig {

    /** The API key. */
    public String apiKey;

    /** The client ID. */
    public long clientId;

    /** The IP ranges. */
    public String ipRanges;
}
