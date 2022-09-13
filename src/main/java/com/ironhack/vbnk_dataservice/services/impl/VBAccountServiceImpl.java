package com.ironhack.vbnk_dataservice.services.impl;

import com.ironhack.vbnk_dataservice.data.dao.accounts.CheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.StudentCheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.dto.accounts.*;
import com.ironhack.vbnk_dataservice.data.http.request.*;
import com.ironhack.vbnk_dataservice.repositories.accounts.CheckingAccountRepository;
import com.ironhack.vbnk_dataservice.repositories.accounts.CreditAccountRepository;
import com.ironhack.vbnk_dataservice.repositories.accounts.SavingsAccountRepository;
import com.ironhack.vbnk_dataservice.repositories.accounts.StudentCheckingAccountRepository;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.client.HttpResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VBAccountServiceImpl implements VBAccountService {
    //    final VBAccountRepository repository;
    final SavingsAccountRepository savingsAccountRepository;
    final CheckingAccountRepository checkingRepository;
    final CreditAccountRepository creditRepository;
    final StudentCheckingAccountRepository studentRepository;
    final VBUserService userService;

    public VBAccountServiceImpl(
            SavingsAccountRepository savingsAccountRepository,
            CheckingAccountRepository checkingRepository,
            CreditAccountRepository creditRepository,
            StudentCheckingAccountRepository studentRepository, VBUserService userService) {
//        this.repository = repository;
        this.savingsAccountRepository = savingsAccountRepository;
        this.checkingRepository = checkingRepository;
        this.creditRepository = creditRepository;
        this.studentRepository = studentRepository;
        this.userService = userService;
    }

    @Override
    public AccountDTO getAccount(String id) throws HttpResponseException {
//        return AccountDTO.fromAnyAccountEntity(repository.findById(id).orElseThrow());
        if (checkingRepository.existsById(id)) return CheckingDTO.fromEntity(checkingRepository.findById(id)
                .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
        if (savingsAccountRepository.existsById(id)) return SavingsDTO.fromEntity(savingsAccountRepository.findById(id)
                .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
        if (creditRepository.existsById(id)) return CreditDTO.fromEntity(creditRepository.findById(id)
                .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
        if (studentRepository.existsById(id)) return StudentCheckingDTO.fromEntity(studentRepository.findById(id)
                .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
        else throw new HttpResponseException(404, "ID NOK");
    }

    @Override
    public List<AccountDTO> getAllUserAccounts(String userId) {
        List<AccountDTO> primary = checkingRepository.findAllByPrimaryOwnerId(userId)
                .stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new));
        primary.addAll(checkingRepository.findAllBySecondaryOwnerId(userId)
                .stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new)));
        primary.addAll(savingsAccountRepository.findAllByPrimaryOwnerId(userId)
                .stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new)));
        primary.addAll(savingsAccountRepository.findAllBySecondaryOwnerId(userId)
                .stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new)));
        primary.addAll(creditRepository.findAllByPrimaryOwnerId(userId)
                .stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new)));
        primary.addAll(creditRepository.findAllBySecondaryOwnerId(userId)
                .stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new)));
        primary.addAll(studentRepository.findAllByPrimaryOwnerId(userId)
                .stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new)));
        primary.addAll(studentRepository.findAllBySecondaryOwnerId(userId)
                .stream().map(AccountDTO::fromAnyAccountEntity).collect(Collectors.toCollection(ArrayList::new)));
        return primary;
    }

    @Override
    public AccountDTO create(NewAccountRequest request, String userId) throws HttpResponseException {
        var owner = userService.getAccountHolder(userId);
        if (owner == null) throw new HttpResponseException(404, "Wrong User Id");
        var admin = userService.getRandomAdmin();
        var sOwner = userService.getAccountHolder(request.getSecondaryOwner());
        request.setId(null);
        request.setPrimaryOwner(userId).setAdministratedBy(admin.getId());
        if (request instanceof NewCreditAccountRequest &&
                owner.getDateOfBirth().plusYears(18).isEqual(LocalDate.now())) {
            NewStudentCheckingAccountRequest dto2 = new NewStudentCheckingAccountRequest();
            dto2.setId(request.getId())
                    .setInitialAmount(request.getInitialAmount())
                    .setCurrency(request.getCurrency())
                    .setStatus(request.getStatus())
                    .setSecretKey(request.getSecretKey())
                    .setPrimaryOwner(request.getPrimaryOwner())
                    .setSecondaryOwner(request.getSecondaryOwner())
                    .setAdministratedBy(request.getAdministratedBy());
            request = dto2;
        }
        return switch (request.getClass().getSimpleName()) {
            case "NewCheckingAccountRequest" -> CheckingDTO.fromEntity(checkingRepository.save(CheckingAccount.fromDTO(
                    CheckingDTO.fromRequest((NewCheckingAccountRequest) request,
                            AccountHolder.fromDTO(owner),
                            AccountHolder.fromDTO(sOwner),
                            VBAdmin.fromDTO(admin)))));
            case "NewStudentCheckingAccountRequest" ->
                    StudentCheckingDTO.fromEntity(studentRepository.save(StudentCheckingAccount.fromDTO(
                            StudentCheckingDTO.fromRequest((NewStudentCheckingAccountRequest) request,
                                    AccountHolder.fromDTO(owner),
                                    AccountHolder.fromDTO(sOwner),
                                    VBAdmin.fromDTO(admin)))));
            case "NewSavingsAccountRequest" ->
                    SavingsDTO.fromEntity(savingsAccountRepository.save(SavingsAccount.fromDTO(
                            SavingsDTO.fromRequest((NewSavingsAccountRequest) request,
                                    AccountHolder.fromDTO(owner),
                                    AccountHolder.fromDTO(sOwner),
                                    VBAdmin.fromDTO(admin)))));
            case "NewCreditAccountRequest" -> CreditDTO.fromEntity(creditRepository.save(CreditAccount.fromDTO(
                    CreditDTO.fromRequest((NewCreditAccountRequest) request,
                            AccountHolder.fromDTO(owner),
                            AccountHolder.fromDTO(sOwner),
                            VBAdmin.fromDTO(admin)))));
            default ->
                    throw new HttpResponseException(HttpStatus.I_AM_A_TEAPOT.value(), HttpStatus.I_AM_A_TEAPOT.getReasonPhrase());
        };
    }

    @Override
    public void update(AccountDTO dto, String id) throws HttpResponseException {
        AccountDTO original = getAccount(id);
        if (dto.getAmount() != null) original.setAmount(dto.getAmount());
        if (dto.getPrimaryOwner() != null) original.setPrimaryOwner(dto.getPrimaryOwner());
        if (dto.getSecondaryOwner() != null) original.setSecondaryOwner(dto.getSecondaryOwner());
        if (dto.getAdministratedBy() != null) original.setAdministratedBy(dto.getAdministratedBy());
        if (dto.getStatus() != null) original.setStatus(dto.getStatus());
        if (dto.getSecretKey() != null) original.setSecretKey(dto.getSecretKey());
        if (dto instanceof CheckingDTO espDto) {
            var save = (CheckingDTO) original;
            if (espDto.getMinimumBalance() != null) (save).setMinimumBalance(espDto.getMinimumBalance());
            if (espDto.getPenaltyFee() != null) (save).setPenaltyFee(espDto.getPenaltyFee());
            if (espDto.getMonthlyMaintenanceFee() != null)
                (save).setMonthlyMaintenanceFee(espDto.getMonthlyMaintenanceFee());
            checkingRepository.save(CheckingAccount.fromDTO(save));
        }
        if (dto instanceof SavingsDTO espDto) {
            var save = (SavingsDTO) original;
            if (espDto.getMinimumBalance() != null) (save).setMinimumBalance(espDto.getMinimumBalance());
            if (espDto.getPenaltyFee() != null) (save).setPenaltyFee(espDto.getPenaltyFee());
            if (espDto.getInterestRate() != null) (save).setInterestRate(espDto.getInterestRate());
            savingsAccountRepository.save(SavingsAccount.fromDTO(save));
        }
        if (dto instanceof CreditDTO espDto) {
            var save = (CreditDTO) original;
            if (espDto.getCreditLimit() != null) (save).setCreditLimit(espDto.getCreditLimit());
            if (espDto.getInterestRate() != null) (save).setInterestRate(espDto.getInterestRate());
            creditRepository.save(CreditAccount.fromDTO(save));
        }
        if (dto instanceof StudentCheckingDTO) {
            var save = (StudentCheckingDTO) original;
            studentRepository.save(StudentCheckingAccount.fromDTO(save));
        }
    }

    @Override
    public void delete(String id) {
        if (checkingRepository.existsById(id)) checkingRepository.deleteById(id);
        else if (savingsAccountRepository.existsById(id)) savingsAccountRepository.deleteById(id);
        else if (creditRepository.existsById(id)) creditRepository.deleteById(id);
        else if (studentRepository.existsById(id)) studentRepository.deleteById(id);
    }

    @Override
    public boolean exist(String destinationAccountRef) {
        return checkingRepository.existsById(destinationAccountRef) || checkingRepository.existsByAccountNumber(destinationAccountRef) ||
                savingsAccountRepository.existsById(destinationAccountRef) || savingsAccountRepository.existsByAccountNumber(destinationAccountRef) ||
                creditRepository.existsById(destinationAccountRef) || creditRepository.existsByAccountNumber(destinationAccountRef) ||
                studentRepository.existsById(destinationAccountRef) || studentRepository.existsByAccountNumber(destinationAccountRef);

    }
}
