package mn.foreman.guardrail.controller;

import mn.foreman.guardrail.config.Repository;
import mn.foreman.guardrail.model.PickaxeConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

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
     * @param pickaxeConfig The config.
     *
     * @return The view.
     *
     * @throws IOException on failure.
     */
    @PostMapping
    public String update(@ModelAttribute final PickaxeConfig pickaxeConfig)
            throws IOException {
        this.repository.updateConfig(pickaxeConfig);
        return "redirect:/";
    }
}