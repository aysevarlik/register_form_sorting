package com.example.registerformandsorting.Controller;

import com.example.registerformandsorting.Dto.RegisterDto;
import com.example.registerformandsorting.Dto.RegisterEntity;
import com.example.registerformandsorting.impl.IRegisterRepo;
import com.example.registerformandsorting.Dto.RegisterDto;
import com.example.registerformandsorting.Dto.RegisterEntity;
import com.example.registerformandsorting.impl.IRegisterRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@Log4j2
public class RegisterController {

    @Autowired
    IRegisterRepo iRegisterRepo;

    //http://localhost:8082/register/save
    @GetMapping("register/save")
    public String getRegister(Model model){
        model.addAttribute("user",new RegisterDto());
        return "register";
    }

    @PostMapping("register/save")
    public String postRegister(@Valid @ModelAttribute("user") @RequestBody RegisterDto registerDto, BindingResult result){
        if (result.hasErrors()){
            return "register";
        }
        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setName(registerDto.getName());
        registerEntity.setLastName(registerDto.getLastName());
        registerEntity.setMail(registerDto.getMail());
        registerEntity.setPassword(registerDto.getPassword());
        iRegisterRepo.save(registerEntity);
        return "redirect:/register/list";
    }


    //SAVE
    //http://localhost:8082/register/save/fake
    @GetMapping("register/save/fake")
    public String saveRegisterForm(Model model, String registerEntity){
        for (int i = 0; i < 11; i++) {
            UUID uuid = UUID.randomUUID();
            RegisterEntity entity = RegisterEntity.builder().
                    id(0L).
                    name("name"+uuid).
                    lastName("last name"+i).
                    mail("mail"+i).
                    password("password"+i).build();
            iRegisterRepo.save(entity);
            model.addAttribute("key",entity);
        }

        return "table";
    }

    //FIND

    @GetMapping("register/find/{id}")
    public String findRegisterId(@PathVariable(name="id") Long id, Model model){
        Optional<RegisterEntity> entityOptional = iRegisterRepo.findById(id);
        if (entityOptional.isPresent()){
            model.addAttribute("find",entityOptional.get());
            return "information";
        }else{
            return "redirect:/table";
        }
    }

    //DELETE
    //http://localhost:8082/register/delete/1
    @GetMapping("register/delete/{id}")
    public String deleteRegisterId(@PathVariable(name="id") Long id, Model model){
        Optional<RegisterEntity> entityOptional = iRegisterRepo.findById(id);
        if (entityOptional.isPresent()){
            iRegisterRepo.deleteById(id);
            model.addAttribute("delete","success");
        }else{
            model.addAttribute("delete","failed");
        }
        return "redirect:/register/list";
    }

    //UPDATE
    //http://localhost:8082/register/update/2
    @GetMapping("register/update/{id}")
    @ResponseBody
    public String updateRegisterId(@PathVariable(name="id") Long id){
        Optional<RegisterEntity> entityOptional = iRegisterRepo.findById(id);
        if (entityOptional.isPresent()){

            return id+ " güncellendi."+entityOptional.get();
        }else{
            return id+ "bulunamadı.";
        }
    }

    //LIST
    //http://localhost:8082/register/list
    @GetMapping("register/list")
    public String listRegister(Model model){
        Sort sort = Sort.by("id").ascending();
        Iterable<RegisterEntity> list = iRegisterRepo.findAll(sort);
        model.addAttribute("list",list);
        return "table";
    }

    //http://localhost:8082/register/sortById
    @GetMapping("register/sortById")
    public String sortingById(Model model){
        Sort sort = Sort.by("id").descending();
        List<RegisterEntity> list = iRegisterRepo.findAll(sort);
        model.addAttribute("list",list);
        return "table";
    }

    //bu şekilde de sorting kullanabiliriz.
    //http://localhost:8082/register/sortByName
    @GetMapping("register/sortByName")
    public String sortByName(Model model){
        List<RegisterEntity> list = iRegisterRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
        model.addAttribute("list",list);
        return "table";
    }

    //http://localhost:8082/register/sortByName2
    @GetMapping("register/sortByName2")
    public String sortByName2(Model model){
        Sort sort = Sort.by("name").descending();
        List<RegisterEntity> list = iRegisterRepo.findAll(sort);
        model.addAttribute("list",list);
        return "table";
    }

    //http://localhost:8082/register/sortBySurname
    @GetMapping("register/sortBySurname")
    public String sortBySurname(Model model){
        List<RegisterEntity> list = iRegisterRepo.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
        model.addAttribute("list",list);
        return "table";
    }

    //http://localhost:8082/register/sortBySurname2
    @GetMapping("register/sortBySurname2")
    public String sortBySurname2(Model model){
        Sort sort = Sort.by("lastName").descending();
        List<RegisterEntity> list = iRegisterRepo.findAll(sort);
        model.addAttribute("list",list);
        return "table";
    }

}
