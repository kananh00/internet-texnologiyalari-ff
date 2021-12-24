package com.peopleapi.service.impl;

import com.peopleapi.dto.PersonDto;
import com.peopleapi.entity.Address;
import com.peopleapi.entity.Person;
import com.peopleapi.repo.AdresRepository;
import com.peopleapi.repo.PersonRepository;
import com.peopleapi.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AdresRepository adresRepository;
    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {

        Person person = new Person();
        person.setAdi(personDto.getAdi());
        person.setSoyadi(personDto.getSoyadi());
        final Person personDb = personRepository.save(person);
        List<Address> adress_list = new ArrayList<>();

        personDto.getAdresler().forEach(item -> {
            Address address = new Address();
            address.setAdres(item);
            address.setAddressType(Address.AddressType.OTHER);
            address.setActive(true);
            address.setPerson(personDb);
            adress_list.add(address);
        });
        adresRepository.saveAll(adress_list);
        personDto.setId(personDb.getId());
        return personDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PersonDto> getAll() {
        List<Person> people = personRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();
        people.forEach(it -> {
            PersonDto personDto = new PersonDto();
            personDto.setId(it.getId());
            personDto.setAdi(it.getAdi());
            personDto.setSoyadi(it.getSoyadi());
            personDto.setAdresler(it.getAdresleri().stream().map(Address::getAdres)
                    .collect(Collectors.toList()));
            personDtos.add(personDto);
        });
        return personDtos;
    }

    @Override
    public Page<PersonDto> getAll(Pageable pageable) {
        return null;
    }
}
