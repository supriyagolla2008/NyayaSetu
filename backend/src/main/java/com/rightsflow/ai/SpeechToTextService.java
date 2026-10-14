package com.rightsflow.ai;

import com.rightsflow.dto.ProblemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpeechToTextService {

    private static final Logger log = LoggerFactory.getLogger(SpeechToTextService.class);

    public ProblemDto.VoiceTranscriptionResponse transcribe(ProblemDto.VoiceInputRequest request) {
        log.info("Processing Speech-To-Text request with format: {}, language hint: {}",
                request.getAudioFormat(), request.getLanguageHint());

        String detectedLang = request.getLanguageHint() != null ? request.getLanguageHint() : "en";
        String sampleText = "My employer has not paid my salary for two months.";

        if ("hi".equalsIgnoreCase(detectedLang)) {
            sampleText = "मेरे नियोक्ता ने पिछले दो महीनों से मेरा वेतन नहीं दिया है।";
        } else if ("te".equalsIgnoreCase(detectedLang)) {
            sampleText = "నా యజమాని గత రెండు నెలలుగా నా జీతం చెల్లించలేదు.";
        }

        return ProblemDto.VoiceTranscriptionResponse.builder()
                .transcribedText(sampleText)
                .detectedLanguage(detectedLang)
                .confidence(0.96)
                .build();
    }
}
