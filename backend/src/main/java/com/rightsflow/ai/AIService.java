package com.rightsflow.ai;

import com.rightsflow.dto.ProblemDto;

public interface AIService {
    ProblemDto.AIAnalysisResponse analyzeProblem(ProblemDto.ProblemAnalysisRequest request);
    ProblemDto.VoiceTranscriptionResponse transcribeAudio(ProblemDto.VoiceInputRequest request);
    ProblemDto.TextToSpeechResponse synthesizeSpeech(ProblemDto.TextToSpeechRequest request);
    boolean isLiveProviderAvailable();
}
