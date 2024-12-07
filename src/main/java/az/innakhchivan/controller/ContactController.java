package az.innakhchivan.controller;

import az.innakhchivan.dto.request.ContactRequestDto;
import az.innakhchivan.dto.response.ContactResponseDto;
import az.innakhchivan.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/contact")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResponseDto> created(@RequestBody ContactRequestDto contactRequestDto) {
        ContactResponseDto contactResponseDto = contactService.createContact(contactRequestDto);
        return new ResponseEntity<>(contactResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDto> updated(@PathVariable Long id, @RequestBody ContactRequestDto contactRequestDto) {
        ContactResponseDto contactResponseDto = contactService.updateContact(id,contactRequestDto);
        return new ResponseEntity<>(contactResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDto> getById(@PathVariable Long id) {
        ContactResponseDto contactResponseDto = contactService.getByIdContact(id);
        return new ResponseEntity<>(contactResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getAll() {
        List<ContactResponseDto> contactResponseDto = contactService.getContactsAll();
        return new ResponseEntity<>(contactResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
         contactService.deleteContact(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
