package com.ironhack.vbnk_dataservice.services.impl;

import com.ironhack.vbnk_dataservice.data.dao.accounts.CheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.CreditAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.SavingsAccount;
import com.ironhack.vbnk_dataservice.data.dao.accounts.StudentCheckingAccount;
import com.ironhack.vbnk_dataservice.data.dao.users.AccountHolder;
import com.ironhack.vbnk_dataservice.data.dao.users.VBAdmin;
import com.ironhack.vbnk_dataservice.data.dto.accounts.*;
import com.ironhack.vbnk_dataservice.data.dto.users.AccountHolderDTO;
import com.ironhack.vbnk_dataservice.data.dto.users.AdminDTO;
import com.ironhack.vbnk_dataservice.data.http.request.NewAccountRequest;
import com.ironhack.vbnk_dataservice.data.http.request.NewCheckingAccountRequest;
import com.ironhack.vbnk_dataservice.data.http.request.NewCreditAccountRequest;
import com.ironhack.vbnk_dataservice.data.http.request.NewSavingsAccountRequest;
import com.ironhack.vbnk_dataservice.repositories.accounts.CheckingAccountRepository;
import com.ironhack.vbnk_dataservice.repositories.accounts.CreditAccountRepository;
import com.ironhack.vbnk_dataservice.repositories.accounts.SavingsAccountRepository;
import com.ironhack.vbnk_dataservice.repositories.accounts.StudentCheckingAccountRepository;
import com.ironhack.vbnk_dataservice.services.VBAccountService;
import com.ironhack.vbnk_dataservice.services.VBUserService;
import org.apache.http.client.HttpResponseException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.ironhack.vbnk_dataservice.utils.VBNKConfig.*;
import static org.springframework.http.HttpStatus.I_AM_A_TEAPOT;

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
    public AccountDTO getAccount(String ref) throws HttpResponseException {
        if(ref.contains(VBNK_INT_ENTITY_CODE + VBNK_ENTITY_CODE)){
            if (checkingRepository.existsByAccountNumber(ref)) return CheckingDTO.fromEntity(checkingRepository.findByAccountNumber(ref)
                    .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
            if (savingsAccountRepository.existsByAccountNumber(ref)) return SavingsDTO.fromEntity(savingsAccountRepository.findByAccountNumber(ref)
                    .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
            if (creditRepository.existsByAccountNumber(ref)) return CreditDTO.fromEntity(creditRepository.findByAccountNumber(ref)
                    .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
            if (studentRepository.existsByAccountNumber(ref)) return StudentCheckingDTO.fromEntity(studentRepository.findByAccountNumber(ref)
                    .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
            else throw new HttpResponseException(404, "ID NOK");
        }
//        return AccountDTO.fromAnyAccountEntity(repository.findById(ref).orElseThrow());
        if (checkingRepository.existsById(ref)) return CheckingDTO.fromEntity(checkingRepository.findById(ref)
                .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
        if (savingsAccountRepository.existsById(ref)) return SavingsDTO.fromEntity(savingsAccountRepository.findById(ref)
                .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
        if (creditRepository.existsById(ref)) return CreditDTO.fromEntity(creditRepository.findById(ref)
                .orElseThrow(() -> new HttpResponseException(404, "FATAL ERR")));
        if (studentRepository.existsById(ref)) return StudentCheckingDTO.fromEntity(studentRepository.findById(ref)
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
    public AccountDTO create(NewAccountRequest request) throws HttpResponseException {
        var owner = userService.getAccountHolder(request.getPrimaryOwner());
        if (owner == null) throw new HttpResponseException(404, "Wrong User Id");
        if(request.getCurrency()==null)request.setCurrency(VBNK_CURRENCY_DEF);
        var admin = (request.getAdministratedBy() == null
                || request.getAdministratedBy().equals("")
                || request.getAdministratedBy().equals(" ")) ?
                userService.getRandomAdmin()
                : userService.getAdmin(request.getAdministratedBy());
        if (admin == null) throw new HttpResponseException(404, "Wrong Admin Id");
        AccountHolderDTO sOwner = null;
        try {
            sOwner = userService.getAccountHolder(request.getSecondaryOwner());
        }catch (Throwable ignored){}
        request.setPrimaryOwner(owner.getId()).setAdministratedBy(admin.getId());
        return switch (request.getClass().getSimpleName()) {
            case "NewCheckingAccountRequest" ->
                    (owner.getDateOfBirth().plusYears(24).isAfter(LocalDate.now())) ?
                            StudentCheckingDTO.fromEntity(
                                    studentRepository.save(
                                            StudentCheckingAccount.fromDTO(
                                                    StudentCheckingDTO.fromRequest((NewCheckingAccountRequest) request,
                                                    AccountHolder.fromDTO(owner),
                                                    sOwner==null?null:AccountHolder.fromDTO(sOwner),
                                                    VBAdmin.fromDTO(admin),
                                                    createAccountNumber(owner, admin)))))
                            :
                            CheckingDTO.fromEntity(checkingRepository.save(
                                    CheckingAccount.fromDTO(
                                            CheckingDTO.fromRequest((NewCheckingAccountRequest) request,
                                            AccountHolder.fromDTO(owner),
                                            sOwner==null?null:AccountHolder.fromDTO(sOwner),
                                            VBAdmin.fromDTO(admin),
                                            createAccountNumber(owner, admin)))));

            case "NewSavingsAccountRequest" -> SavingsDTO.fromEntity(
                    savingsAccountRepository.save(SavingsAccount.fromDTO(
                            SavingsDTO.fromRequest((NewSavingsAccountRequest) request,
                                    AccountHolder.fromDTO(owner),
                                    sOwner==null?null:AccountHolder.fromDTO(sOwner),
                                    VBAdmin.fromDTO(admin),
                                    createAccountNumber(owner, admin)))));
            case "NewCreditAccountRequest" -> CreditDTO.fromEntity(
                    creditRepository.save(CreditAccount.fromDTO(
                            CreditDTO.fromRequest((NewCreditAccountRequest) request,
                                    AccountHolder.fromDTO(owner),
                                    sOwner==null?null:AccountHolder.fromDTO(sOwner),
                                    VBAdmin.fromDTO(admin),
                                    createAccountNumber(owner, admin)))));
            default ->
                    throw new HttpResponseException(I_AM_A_TEAPOT.value(), I_AM_A_TEAPOT.getReasonPhrase());
        };
    }

    @Override
    public AccountDTO update(AccountDTO dto, String id) throws HttpResponseException {
        AccountDTO original = getAccount(id);
        if (dto.getAmount() != null) original.setAmount(dto.getAmount());
        if (dto.getPrimaryOwner() != null) original.setPrimaryOwner(dto.getPrimaryOwner());
        if (dto.getSecondaryOwner() != null) original.setSecondaryOwner(dto.getSecondaryOwner());
        if (dto.getAdministratedBy() != null) original.setAdministratedBy(dto.getAdministratedBy());
        if (dto.getState() != null) original.setState(dto.getState());
        if (dto.getSecretKey() != null) original.setSecretKey(dto.getSecretKey());
        if (dto instanceof CheckingDTO espDto) {
            var save = (CheckingDTO) original;
            if (espDto.getMinimumBalance() != null) (save).setMinimumBalance(espDto.getMinimumBalance());
            if (espDto.getPenaltyFee() != null) (save).setPenaltyFee(espDto.getPenaltyFee());
            if (espDto.getMonthlyMaintenanceFee() != null)
                (save).setMonthlyMaintenanceFee(espDto.getMonthlyMaintenanceFee());
            return AccountDTO.fromAnyAccountEntity(checkingRepository.save(CheckingAccount.fromDTO(save)));
        }
        if (dto instanceof SavingsDTO espDto) {
            var save = (SavingsDTO) original;
            if (espDto.getMinimumBalance() != null) (save).setMinimumBalance(espDto.getMinimumBalance());
            if (espDto.getPenaltyFee() != null) (save).setPenaltyFee(espDto.getPenaltyFee());
            if (espDto.getInterestRate() != null) (save).setInterestRate(espDto.getInterestRate());
            return AccountDTO.fromAnyAccountEntity(savingsAccountRepository.save(SavingsAccount.fromDTO(save)));
        }
        if (dto instanceof CreditDTO espDto) {
            var save = (CreditDTO) original;
            if (espDto.getCreditLimit() != null) (save).setCreditLimit(espDto.getCreditLimit());
            if (espDto.getInterestRate() != null) (save).setInterestRate(espDto.getInterestRate());
            return AccountDTO.fromAnyAccountEntity(creditRepository.save(CreditAccount.fromDTO(save)));
        }
        if (dto instanceof StudentCheckingDTO) {
            var save = (StudentCheckingDTO) original;
            return AccountDTO.fromAnyAccountEntity(studentRepository.save(StudentCheckingAccount.fromDTO(save)));
        }
        throw new HttpResponseException(I_AM_A_TEAPOT.value(),I_AM_A_TEAPOT.getReasonPhrase());
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
        return checkingRepository.existsById(destinationAccountRef)
                || checkingRepository.existsByAccountNumber(destinationAccountRef)
                || savingsAccountRepository.existsById(destinationAccountRef)
                || savingsAccountRepository.existsByAccountNumber(destinationAccountRef)
                || creditRepository.existsById(destinationAccountRef)
                || creditRepository.existsByAccountNumber(destinationAccountRef)
                || studentRepository.existsById(destinationAccountRef)
                || studentRepository.existsByAccountNumber(destinationAccountRef);

    }


    public String createAccountNumber(AccountHolderDTO pOwner, AdminDTO admin) {
        Random rand = new Random();
        StringBuilder userNumbers = new StringBuilder();
        for (Character num : getNumbersFromId(pOwner.getId())) userNumbers.append(num);
        int val = rand.nextInt(6, userNumbers.toString().length());
        var accountNumber = userNumbers.toString().substring(val - 6, val);
        userNumbers = new StringBuilder();
        for (Character num : getNumbersFromId(admin.getId())) userNumbers.append(num);
        val = rand.nextInt(4, userNumbers.toString().length());
        accountNumber += userNumbers.substring(val - 4, val);
        String securityCode = ((pOwner.getDateOfBirth().getDayOfYear() + 10) + " ").substring(0, 2);
        String IBANNumber = VBNK_INT_ENTITY_CODE + VBNK_ENTITY_CODE + securityCode + accountNumber;
        if (exist(IBANNumber)) return createAccountNumber(pOwner, admin);
        return IBANNumber;
    }


}
