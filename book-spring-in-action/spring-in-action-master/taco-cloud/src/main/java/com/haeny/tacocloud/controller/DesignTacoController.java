package com.haeny.tacocloud.controller;

import com.haeny.tacocloud.domain.Ingredient;
import com.haeny.tacocloud.domain.Ingredient.Type;
import com.haeny.tacocloud.domain.Order;
import com.haeny.tacocloud.domain.Taco;
import com.haeny.tacocloud.repository.IngredientRepository;
import com.haeny.tacocloud.repository.TacoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingredients::add);

        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors,
                                @ModelAttribute Order order) {

        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepository.save(design);
        order.addDesign(saved);

        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
            .filter(ingredient -> ingredient.getType().equals(type))
            .collect(Collectors.toList());
    }

}
