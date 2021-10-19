package mn.foreman.guardrail.controller;

import mn.foreman.guardrail.config.Repository;
import mn.foreman.guardrail.model.PickaxeConfig;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        return new PickaxeConfigDto.PickaxeConfigDtoBuilder()
                .clientId(pickaxeConfig.clientId)
                .apiKey(pickaxeConfig.getApiKey())
                .ranges(ranges)
                .build();
    }

    /** A simple object to help serialize the config. */
    @Data
    @Builder
    public static class PickaxeConfigDto {

        /** The key. */
        public String apiKey;

        /** The client ID. */
        public long clientId;

        /** The ranges. */
        public List<String> ranges;
    }
}
