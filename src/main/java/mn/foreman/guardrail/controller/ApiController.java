package mn.foreman.guardrail.controller;

import mn.foreman.guardrail.config.Repository;
import mn.foreman.guardrail.model.PickaxeConfig;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/** The single API endpoint. */
@RestController
@RequestMapping("/api")
public class ApiController {

    /** The {@link Repository}. */
    private final Repository repository;

    /**
     * Constructor.
     *
     * @param repository The repositor.
     */
    @Autowired
    public ApiController(final Repository repository) {
        this.repository = repository;
    }

    /**
     * Returns the config, but in a user-friendly format.
     *
     * @return The config.
     */
    @GetMapping("/config")
    public PickaxeConfigDto getConfig() {
        final PickaxeConfig pickaxeConfig = this.repository.getConfig();
        final List<String> ranges = new LinkedList<>();
        if (pickaxeConfig.ipRanges != null && !pickaxeConfig.ipRanges.isEmpty()) {
            Arrays.stream(pickaxeConfig.ipRanges.split("\\r?\\n|\\r"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .filter(s -> !s.startsWith("#"))
                    .forEach(ranges::add);
        }

        final Map<String, Integer> rateLimits = new HashMap<>();
        if (pickaxeConfig.blinkRate != null) {
            rateLimits.put(
                    "blink",
                    pickaxeConfig.blinkRate);
        }
        if (pickaxeConfig.factoryResetRate != null) {
            rateLimits.put(
                    "factory-reset",
                    pickaxeConfig.factoryResetRate);
        }
        if (pickaxeConfig.networkRate != null) {
            rateLimits.put(
                    "network",
                    pickaxeConfig.networkRate);
        }
        if (pickaxeConfig.passwordRate != null) {
            rateLimits.put(
                    "password",
                    pickaxeConfig.passwordRate);
        }
        if (pickaxeConfig.poolChangeRate != null) {
            rateLimits.put(
                    "change-pools",
                    pickaxeConfig.poolChangeRate);
        }
        if (pickaxeConfig.powerModeRate != null) {
            rateLimits.put(
                    "power-mode",
                    pickaxeConfig.powerModeRate);
        }
        if (pickaxeConfig.rebootRate != null) {
            rateLimits.put(
                    "reboot",
                    pickaxeConfig.rebootRate);
        }

        return new PickaxeConfigDto.PickaxeConfigDtoBuilder()
                .clientId(pickaxeConfig.clientId)
                .apiKey(pickaxeConfig.getApiKey())
                .ranges(ranges)
                .control(pickaxeConfig.control)
                .rateLimits(rateLimits)
                .build();
    }

    /** A simple object to help serialize the config. */
    @Data
    @Builder
    public static class PickaxeConfigDto {

        /** The key. */
        public String apiKey;

        /** The client ID. */
        public Integer clientId;

        /** The control. */
        public Boolean control;

        /** The ranges. */
        public List<String> ranges;

        /** The rate limits. */
        public Map<String, Integer> rateLimits;
    }
}
