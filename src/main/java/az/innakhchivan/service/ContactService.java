package az.innakhchivan.service;

import az.innakhchivan.dto.request.ContactRequestDto;
import az.innakhchivan.dto.response.ContactResponseDto;
import az.innakhchivan.entity.Contact;
import az.innakhchivan.exception.ContactNotFoundException;
import az.innakhchivan.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactResponseDto createContact(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        contact.setPhone(contactRequestDto.getPhone());
        contact.setEmail(contactRequestDto.getEmail());
        contact.setAddress(contactRequestDto.getAddress());
        contactRepository.save(contact);

        return ContactResponseDto.builder()
                .id(contact.getId())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .address(contact.getAddress())
                .build();
    }

    public ContactResponseDto updateContact(Long Id, ContactRequestDto contactRequestDto) {
        Contact contact = contactRepository.findById(Id).orElseThrow(
                () -> new ContactNotFoundException("Contact not found Id : " + Id));
        contact.setPhone(contactRequestDto.getPhone());
        contact.setEmail(contactRequestDto.getEmail());
        contact.setAddress(contactRequestDto.getAddress());
        contactRepository.save(contact);

        return ContactResponseDto.builder()
                .id(contact.getId())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .address(contact.getAddress())
                .build();

    }

    public ContactResponseDto getByIdContact(Long Id) {
        Contact contact = contactRepository.findById(Id).orElseThrow(
                () -> new ContactNotFoundException("Contact not found Id : " + Id));

        return ContactResponseDto.builder()
                .id(contact.getId())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .address(contact.getAddress())
                .build();
    }

    public List<ContactResponseDto> getContactsAll() {
        return contactRepository.findAll().stream()
                .map(x -> new ContactResponseDto(
                        x.getId(),
                        x.getPhone(),
                        x.getEmail(),
                        x.getAddress()
                ))
                .collect(Collectors.toList());
    }

    public void deleteContact(Long Id) {
        Contact contact = contactRepository.findById(Id).orElseThrow(
                () -> new ContactNotFoundException("Contact not found Id : " + Id));
        contactRepository.delete(contact);
    }

}
