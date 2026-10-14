package com.rightsflow.ai;

import com.rightsflow.dto.ProblemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TextToSpeechService {

    private static final Logger log = LoggerFactory.getLogger(TextToSpeechService.class);

    public ProblemDto.TextToSpeechResponse synthesize(ProblemDto.TextToSpeechRequest request) {
        log.info("Synthesizing audio for text in language: {}", request.getLanguage());

        return ProblemDto.TextToSpeechResponse.builder()
                .audioFormat("audio/mp3")
                .audioBase64("")
                .build();
    }
}
