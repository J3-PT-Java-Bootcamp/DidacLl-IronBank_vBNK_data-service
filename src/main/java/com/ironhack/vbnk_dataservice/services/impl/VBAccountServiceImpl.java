package com.ironhack.vbnk_dataservice.services.impl;

import com.ironhack.vbnk_dataservice.data.dao.accounts.*;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.dto.accounts.*;
import com.ironhack.vbnk_dataservice.repositories.accounts.*;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VBAccountServiceImpl implements VBAccountService {
    final VBAccountRepository repository;
    final SavingsAccountRepository savingsAccountRepository;
    final CheckingAccountRepository checkingRepository;
    final CreditAccountRepository creditRepository;
    final StudentCheckingAccountRepository studentRepository;
    final VBUserService userService;

    public VBAccountServiceImpl(VBAccountRepository repository,
                                SavingsAccountRepository savingsAccountRepository,
                                CheckingAccountRepository checkingRepository,
                                CreditAccountRepository creditRepository,
                                StudentCheckingAccountRepository studentRepository, VBUserService userService) {
        this.repository = repository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.checkingRepository = checkingRepository;
        this.creditRepository = creditRepository;
        this.studentRepository = studentRepository;
        this.userService = userService;
    }
    @Override
    public AccountDTO getAccount(UUID id) {
        return AccountDTO.fromAnyAccountEntity(repository.findById(id).orElseThrow());
    }
    @Override
    public List<AccountDTO> getAllUserAccounts(String userId) {
        var primary=  repository.findAllByPrimaryOwnerId(userId);
        primary.addAll(repository.findAllBySecondaryOwnerId(userId));
        return primary.stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new));

    }

    @Override
    public void create(AccountDTO dto, String userId) {
        var owner = userService.getAccountHolder(userId);
        var admin= userService.getRandomAdmin();
        dto.setPrimaryOwner(AccountHolder.fromDTO(owner)).setAdministratedBy(VBAdmin.fromDTO(admin));
        if(dto instanceof CheckingDTO &&
                dto.getPrimaryOwner().getDateOfBirth().plusYears(18).isEqual(LocalDate.now())){
           var dto2= new StudentCheckingDTO();
            dto2.setId(dto.getId())
                    .setBalance(dto.getBalance())
                    .setStatus(dto.getStatus())
                    .setSecretKey(dto.getSecretKey())
                    .setPrimaryOwner(dto.getPrimaryOwner())
                    .setSecondaryOwner(dto.getSecondaryOwner())
                    .setAdministratedBy(dto.getAdministratedBy());
            dto=dto2;
        }

        switch (dto.getClass().getSimpleName()){
            case "CheckingDTO"-> checkingRepository.save(CheckingAccount.fromDTO((CheckingDTO) dto));
            case "StudentCheckingDTO"-> studentRepository.save(StudentCheckingAccount.fromDTO((StudentCheckingDTO) dto));
            case "SavingsDTO"-> savingsAccountRepository.save(SavingsAccount.fromDTO((SavingsDTO) dto));
            case "CreditDTO"-> creditRepository.save(CreditAccount.fromDTO((CreditDTO) dto));
        }

    }

    @Override
    public void update(AccountDTO dto, UUID id) {
        // TODO: 07/09/2022  
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
