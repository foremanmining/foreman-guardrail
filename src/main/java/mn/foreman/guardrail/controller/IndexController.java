package mn.foreman.guardrail.controller;

import mn.foreman.api.ForemanApi;
import mn.foreman.api.ForemanApiImpl;
import mn.foreman.api.JdkWebUtil;
import mn.foreman.guardrail.config.Repository;
import mn.foreman.guardrail.model.PickaxeConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/** Contains all of the code necessary to server the basic, single-page UI. */
@Controller
@RequestMapping("/")
public class IndexController {

    /** The repository. */
    private final Repository repository;

    /**
     * Constructor.
     *
     * @param repository The repository.
     */
    @Autowired
    public IndexController(final Repository repository) {
        this.repository = repository;
    }

    /**
     * Returns the index.
     *
     * @param model The model.
     *
     * @return The view.
     */
    @GetMapping
    public String index(final Model model) {
        model.addAttribute(
                "pickaxeConfig",
                this.repository.getConfig());
        return "index";
    }

    /**
     * Performs an update.
     *
     * @param pickaxeConfig      The config.
     * @param redirectAttributes The redirect attributes.
     *
     * @return The view.
     *
     * @throws IOException on failure.
     */
    @PostMapping
    public String update(
            @ModelAttribute final PickaxeConfig pickaxeConfig,
            final RedirectAttributes redirectAttributes)
            throws IOException {
        final ForemanApi foremanApi =
                new ForemanApiImpl(
                        Integer.toString(pickaxeConfig.clientId),
                        "",
                        new ObjectMapper(),
                        new JdkWebUtil(
                                "https://api.foreman.mn/",
                                pickaxeConfig.apiKey,
                                5,
                                TimeUnit.SECONDS));
        if (foremanApi.ping().pingClient()) {
            try {
                this.repository.updateConfig(pickaxeConfig);
                redirectAttributes.addFlashAttribute(
                        "success",
                        "Saved!");
            } catch (final Exception e) {
                redirectAttributes.addFlashAttribute(
                        "error",
                        "Invalid form data");
            }
        } else {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Failed to validate those credentials against the Foreman API");
        }
        return "redirect:/";
    }
}