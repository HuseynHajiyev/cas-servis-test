package com.cas.casdemo.casservis.service.accountObject;

import com.cas.casdemo.casservis.dto.accountObject.AccountObjectDetailsDTO;
import com.cas.casdemo.casservis.dto.accountObject.AccountObjectGetResponseDTO;
import com.cas.casdemo.casservis.dto.accountObject.AccountObjectPostRequestDTO;
import com.cas.casdemo.casservis.dto.accountObject.AccountObjectPutRequestDTO;
import com.cas.casdemo.casservis.entity.accountObject.Account;
import com.cas.casdemo.casservis.entity.accountObject.AccountObject;
import com.cas.casdemo.casservis.entity.accountObject.AccountObjectDetails;
import com.cas.casdemo.casservis.entity.accountObject.Servicer;
import com.cas.casdemo.casservis.entity.customer.Customer;
import com.cas.casdemo.casservis.repository.AccountObjectRepository;
import com.cas.casdemo.casservis.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountObjectService {
    private final AccountObjectRepository accountObjectRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public AccountObjectService(AccountObjectRepository accountObjRepo, CustomerRepository cusRepo, ModelMapper mapper) {
        this.accountObjectRepository = accountObjRepo;
        this.customerRepository = cusRepo;
        this.modelMapper = mapper;
    }

    public Long save(AccountObjectPostRequestDTO dto, Long customerId) {
        AccountObject ao = new AccountObject();

        ao.setType(dto.getType());
        ao.setCurrency(String.valueOf(dto.getCurrency()));

        Account acct = new Account();
        acct.setIban(dto.getAccount().getIban());
        ao.setAccount(acct);

        Servicer serv = new Servicer();
        serv.setBic(dto.getServicer().getBic());
        ao.setServicer(serv);

        AccountObjectDetails det = new AccountObjectDetails();
        det.setAuthMethod(dto.getDetails().getAuthMethod());
        ao.setAccountObjectDetails(det);

        Customer owner = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        ao.setCustomer(owner);

        return accountObjectRepository.save(ao).getId();
    }


    public AccountObjectGetResponseDTO findById(Long id) {
        AccountObject entity = accountObjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountObject not found, id = " + id));
        return modelMapper.map(entity, AccountObjectGetResponseDTO.class);
    }

    public AccountObjectGetResponseDTO findByIdAndCustomerId(Long id, Long customerId) {
        AccountObject entity = accountObjectRepository.findByIdAndCustomerId(id, customerId)
                .orElseThrow(() -> new EntityNotFoundException("Invalid Account Object or Customer Id"));
                return modelMapper.map(entity, AccountObjectGetResponseDTO.class);
    }

    public List<AccountObjectGetResponseDTO> findAll() {
        return accountObjectRepository.findAll().stream()
                .map(entity -> modelMapper.map(entity, AccountObjectGetResponseDTO.class))
                .toList();
    }

    public List<AccountObjectGetResponseDTO> findAllByCustomerId(Long customerId) {
        return accountObjectRepository.findAllByCustomerId(customerId)
                .stream()
                .map(entity -> modelMapper.map(entity, AccountObjectGetResponseDTO.class))
                .toList();
    }

    public void deleteById(Long id) {
        AccountObject entity = accountObjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountObject not found, Provide a valid id"));
        accountObjectRepository.delete(entity);
    }

    public AccountObjectGetResponseDTO update(Long id, Long customerId, AccountObjectPutRequestDTO dto) {
        Customer parentCust = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        AccountObject existing = accountObjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AccountObject not found, Provide a valid id"));

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, existing);

        AccountObject updated = accountObjectRepository.save(existing);
        return modelMapper.map(updated, AccountObjectGetResponseDTO.class);
    }

    public Long addAccountToCustomer(Long customerId, AccountObject account) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        account.setCustomer(customer);
        return accountObjectRepository.save(account).getId();
    }
}
