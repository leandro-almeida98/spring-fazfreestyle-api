package veexi.fazfreestyle.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerUIController {

	@GetMapping("/swagger-ui/index")
	public String swaggerUI() {
		return "redirect:/swagger-ui.html";
	}
}
