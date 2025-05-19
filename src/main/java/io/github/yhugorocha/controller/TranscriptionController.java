package io.github.yhugorocha.controller;

import io.github.yhugorocha.service.TranscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/ai/transcription")
public class TranscriptionController {

    private final TranscriptionService transcriptionService;

    public TranscriptionController(TranscriptionService transcriptionService) {
        this.transcriptionService = transcriptionService;
    }
    @PostMapping
    public ResponseEntity<String> transcribeAudio(@RequestParam("file") MultipartFile file) {
        try {
            var transcription = transcriptionService.transcribeAudio(file);
            return ResponseEntity.ok(transcription);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Não foi possivel transcrever o audio: " + e.getMessage());
        }
    }

}
