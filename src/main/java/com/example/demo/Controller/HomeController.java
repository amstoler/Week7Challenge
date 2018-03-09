package com.example.demo.Controller;

import com.example.demo.Model.AppUser;
import com.example.demo.Model.Article;
import com.example.demo.Model.NewsObject;
import com.example.demo.Repositories.AppRoleRepository;
import com.example.demo.Repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping(value = {"/", "/home"})
    public  String showHome() {return "index";}

    @PostMapping("/login")
    public String login()
    {
        return "/login";
    }

    @GetMapping("/register")
    public String registerUser(Model model)
    {
        model.addAttribute("newuser",new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@Valid @ModelAttribute("newuser") AppUser appUser, BindingResult result, HttpServletRequest request)
    {
        if(result.hasErrors())
        {
            return "register";
        }

        appUser.addRole(appRoleRepository.findAppRoleByRoleName("USER"));

        appUserRepository.save(appUser);
        return "redirect:/";
    }


        @RequestMapping("/test")
        public String showIndex(Model model){
        RestTemplate restTemplate = new RestTemplate();

            NewsObject newsObject = restTemplate.getForObject(
                    "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=9e829714c9984782a328e0d01885d95b",NewsObject.class);
// Matched below example
            model.addAttribute("newsitem", newsObject.getArticles());
            return "index";
}
    }


      /*@GetMapping("/addNewsItem")
    public String showItemForm(Model model) {
        model.addAttribute("item", new Item());

        return "newsform";

    }

    @PostMapping("/processNewsItem")
    public String lostitems(@Valid @ModelAttribute("item") Item item, Model model, BindingResult result, Authentication auth) {
        if (result.hasErrors()) {
            return "itemForm2";
        }

        if(item.getImage().isEmpty()){
            item.setImage("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQv4jSEk4XcXfmO7GQcd-Di4E15P082E3P88qM1_rwRqsZUB9DZ");
        }


        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        itemRepo.save(item);
        appUser.addItemtoAppUser(item);
        appUserRepository.save(appUser);

        //End of show. This method existed before to display ALL items but modified to save items
        // to currently logged in user.

        model.addAttribute("item", itemRepo.findAll());
//testing needed if want to display to return lostitems html page
        return "home";

    }

    @GetMapping("/founditems")
    public String showfounditems(Model model, Authentication auth) {
        model.addAttribute("item", new Item());
        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        model.addAttribute("item", itemRepo.findAllByitemStatusContainingIgnoreCase("found"));

        return "founditems";

    }
    // Testing below method to look same as above but for lost
    @GetMapping("/lostitems")
    public String showlostitems(Model model, Authentication auth) {
        model.addAttribute("item", new Item());
        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        model.addAttribute("item", itemRepo.findAllByitemStatusContainingIgnoreCase("lost"));

        return "lostitems";

    }



    @GetMapping("/additemtofound/{id}")
    public String additemtofound(@PathVariable("id") long id, Model model, Authentication auth){

        Item item = itemRepo.findOne(id);
        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        appUser.addItemtoAppUser(item);
        item.setItemStatus("Found");
        model.addAttribute("itemsfoundlist", itemRepo.findOne(id));
        itemRepo.save(item);
        appUserRepository.save(appUser);
        model.addAttribute("listusers", appUserRepository.findAll());
        model.addAttribute("itemlist", appUserRepository.findAll());
        return "redirect:/lostitems";
    }

    //Insert @GetMapping("/additemtolost/{id}")

    @GetMapping("/additemtolost/{id}")
    public String additemtolostlist(@PathVariable("id") long id, Model model, Authentication auth){

        Item item = itemRepo.findOne(id);

        AppUser appUser = appUserRepository.findAppUserByUsername(auth.getName());
        appUser.addItemtoAppUser(item);
        item.setItemStatus("Lost");
        model.addAttribute("lostitemslist", itemRepo.findOne(id));
        itemRepo.save(item);
        appUserRepository.save(appUser);
        model.addAttribute("item",appUserRepository.findAll());
        return "redirect:/founditems";
    }*/




