package mn.foreman.guardrail.model;

import lombok.Data;

/** A pickaxe configuration. */
@Data
public class PickaxeConfig {

    /** The API key. */
    public String apiKey;

    /** The LED blink rate. */
    public Integer blinkRate;

    /** The client ID. */
    public Integer clientId;

    /** Whether or not control is allowed. */
    public Boolean control = true;

    /** The factory reset throttle rate. */
    public Integer factoryResetRate;

    /** The IP ranges. */
    public String ipRanges = "0-255.0-255.0-255.0-255";

    /** The network throttle rate. */
    public Integer networkRate;

    /** The password throttle rate. */
    public Integer passwordRate;

    /** The pool change throttle rate. */
    public Integer poolChangeRate;

    /** The power mode throttle rate. */
    public Integer powerModeRate;

    /** The reboot throttle rate. */
    public Integer rebootRate;
}