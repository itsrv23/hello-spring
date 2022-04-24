package com.javastart.hellospring.controller;

import com.javastart.hellospring.controller.dto.AccountRequestDTO;
import com.javastart.hellospring.controller.dto.AccountResponseDTO;
import com.javastart.hellospring.entity.Account;
import com.javastart.hellospring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountConroller {
    private final AccountService accountService;

    @Autowired
    public AccountConroller(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/hello")
    public String helloSpring() {
        return "Tuk Tuk Neo. Spring has you... ";
    }


    @PostMapping("/accounts")
    public Long createAccount(@RequestBody AccountRequestDTO accountRequestDTO) {
        return accountService.createAccount(accountRequestDTO.getName(), accountRequestDTO.getEmail(), accountRequestDTO.getBill());
    }

    @GetMapping("/accounts/{id}")
    public AccountResponseDTO getAccount(@PathVariable Long id) {
        return new AccountResponseDTO(accountService.getAccountById(id));
    }

    @GetMapping("/accounts")
    public List<AccountResponseDTO> getAll(){
        return accountService.getAll().stream().map(AccountResponseDTO::new).collect(Collectors.toList());
    }

    @DeleteMapping("/accounts/{id}")
    public AccountResponseDTO deleteById(@PathVariable Long id){
        return new AccountResponseDTO(accountService.deleteById(id));
    }

//    @GetMapping("/accounts")
//    // Можем вернуть и такой лист, но ....
//    public List<Account> getAll(){
//        return new ArrayList<>(accountService.getAll());
//    }
}
