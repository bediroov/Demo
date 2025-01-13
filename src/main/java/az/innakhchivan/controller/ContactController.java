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
    public ResponseEntity<Void> created(@RequestBody ContactRequestDto contactRequestDto) {
     contactService.createContact(contactRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<Void> updated(@PathVariable Long id, @RequestBody ContactRequestDto contactRequestDto) {
        contactService.updateContact(id, contactRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<ContactResponseDto>> getAll() {
        List<ContactResponseDto> contactResponseDto = contactService.getContactsAll();
        return new ResponseEntity<>(contactResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
