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

    public void createContact(ContactRequestDto contactRequestDto) {
        Contact contact = new Contact();
        contact.setName(contactRequestDto.getName());
        contact.setSurname(contactRequestDto.getSurname());
        contact.setPhone(contactRequestDto.getPhone());
        contact.setEmail(contactRequestDto.getEmail());
        contact.setApplicationContent(contactRequestDto.getApplicationContent());
        contactRepository.save(contact);
    }


    public void updateContact(Long id, ContactRequestDto contactRequestDto) {
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new ContactNotFoundException("Contact not found with id: " + id)
        );
        contact.setName(contactRequestDto.getName());
        contact.setSurname(contactRequestDto.getSurname());
        contact.setPhone(contactRequestDto.getPhone());
        contact.setEmail(contactRequestDto.getEmail());
        contact.setApplicationContent(contactRequestDto.getApplicationContent());
        contactRepository.save(contact);
    }


    public List<ContactResponseDto> getContactsAll() {
        return contactRepository.findAllByOrderByIdAsc().stream()
                .map(x -> new ContactResponseDto(
                        x.getId(),
                        x.getName(),
                        x.getSurname(),
                        x.getPhone(),
                        x.getEmail(),
                        x.getApplicationContent(),
                        x.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    public void deleteContact(Long Id) {
        Contact contact = contactRepository.findById(Id).orElseThrow(
                () -> new ContactNotFoundException("Contact not found Id : " + Id));
        contactRepository.delete(contact);
    }

}
