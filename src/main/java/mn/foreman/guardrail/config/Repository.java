package mn.foreman.guardrail.config;

import mn.foreman.guardrail.model.PickaxeConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/** Provides a simple mechanism to store the Pickaxe configuration. */
@Component
public class Repository {

    /** The logger for this class. */
    private static final Logger LOG =
            LoggerFactory.getLogger(Repository.class);

    /** The db path. */
    private final File dbPath;

    /** The JSON mapper. */
    private final ObjectMapper objectMapper;

    /**
     * Constructor.
     *
     * @param dbPath       The DB path.
     * @param objectMapper The mapper for JSON.
     */
    @Autowired
    public Repository(
            final File dbPath,
            final ObjectMapper objectMapper) {
        this.dbPath = dbPath;
        this.objectMapper = objectMapper;
    }

    /**
     * Retrieves the current config.
     *
     * @return The current config.
     */
    public PickaxeConfig getConfig() {
        try {
            return this.objectMapper.readValue(
                    this.dbPath,
                    PickaxeConfig.class);
        } catch (final IOException e) {
            LOG.warn("Exception while reading config", e);
        }
        return new PickaxeConfig();
    }

    /**
     * Writes the provided configuration.
     *
     * @param pickaxeConfig The new configuration.
     *
     * @throws IOException on failure.
     */
    public void updateConfig(final PickaxeConfig pickaxeConfig)
            throws IOException {
        this.objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(
                        this.dbPath,
                        pickaxeConfig);
    }
}
