package mn.foreman.guardrail;

import mn.foreman.guardrail.model.PickaxeConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;

/** Creates all of the beans. */
@org.springframework.context.annotation.Configuration
public class Configuration {

    /**
     * Creates a {@link File} to the data.
     *
     * @param dbPath       The path.
     * @param objectMapper The mapper for JSON.
     *
     * @return The file.
     *
     * @throws IOException on failure.
     */
    @Bean
    public File dbPath(
            @Value("${dbPath}") final String dbPath,
            final ObjectMapper objectMapper) throws IOException {
        final File dbFile = new File(dbPath);
        if (!dbFile.exists()) {
            if (!dbFile.createNewFile()) {
                throw new IOException("Failed to create DB file");
            }
            objectMapper.writeValue(
                    dbFile,
                    new PickaxeConfig());
        }
        return dbFile;
    }
}
