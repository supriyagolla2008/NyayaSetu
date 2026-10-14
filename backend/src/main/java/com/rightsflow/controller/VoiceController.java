package com.rightsflow.controller;

import com.rightsflow.ai.AIService;
import com.rightsflow.dto.ProblemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voice")
@Tag(name = "Voice & Speech Engine", description = "Speech-to-Text and Text-to-Speech endpoints for multilingual support")
public class VoiceController {
    public VoiceController(AIService aiService) {
        this.aiService = aiService;
    }



    private final AIService aiService;

    @PostMapping("/transcribe")
    @Operation(summary = "Transcribe spoken problem audio to text")
    public ResponseEntity<ProblemDto.VoiceTranscriptionResponse> transcribeAudio(
            @RequestBody ProblemDto.VoiceInputRequest request
    ) {
        return ResponseEntity.ok(aiService.transcribeAudio(request));
    }

    @PostMapping("/synthesize")
    @Operation(summary = "Synthesize legal advice response text to speech")
    public ResponseEntity<ProblemDto.TextToSpeechResponse> synthesizeSpeech(
            @RequestBody ProblemDto.TextToSpeechRequest request
    ) {
        return ResponseEntity.ok(aiService.synthesizeSpeech(request));
    }
}
