package com.rightsflow.ai;

import com.rightsflow.dto.ProblemDto;
import com.rightsflow.entity.Law;
import com.rightsflow.entity.LawProvision;
import com.rightsflow.entity.Right;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RAGService implements AIService {

    private static final Logger log = LoggerFactory.getLogger(RAGService.class);

    private final ProblemClassificationService classificationService;
    private final LegalRetrievalService retrievalService;
    private final SpeechToTextService speechToTextService;
    private final TextToSpeechService textToSpeechService;

    @Value("${app.ai.provider:demo}")
    private String aiProvider;

    @Value("${app.ai.api-key:}")
    private String apiKey;

    public RAGService(
            ProblemClassificationService classificationService,
            LegalRetrievalService retrievalService,
            SpeechToTextService speechToTextService,
            TextToSpeechService textToSpeechService
    ) {
        this.classificationService = classificationService;
        this.retrievalService = retrievalService;
        this.speechToTextService = speechToTextService;
        this.textToSpeechService = textToSpeechService;
    }

    @Override
    public boolean isLiveProviderAvailable() {
        return ("gemini".equalsIgnoreCase(aiProvider) || "openai".equalsIgnoreCase(aiProvider)) &&
               apiKey != null && !apiKey.isBlank();
    }

    @Override
    public ProblemDto.AIAnalysisResponse analyzeProblem(ProblemDto.ProblemAnalysisRequest request) {
        String problemText = request.getProblemText();
        log.info("Executing RAG Pipeline for query: '{}', language: '{}'", problemText, request.getLanguage());

        String detectedLang = request.getLanguage() != null && !request.getLanguage().isBlank()
                ? request.getLanguage()
                : classificationService.detectLanguage(problemText);

        String category = classificationService.classifyCategory(problemText, request.getCategoryHint());

        Optional<Right> matchedRightOpt = retrievalService.retrieveBestMatchingRight(problemText, category);

        if (matchedRightOpt.isEmpty()) {
            return ProblemDto.AIAnalysisResponse.builder()
                    .summary("No sufficient verified information found in knowledge base.")
                    .detectedLanguage(detectedLang)
                    .classifiedCategory(category)
                    .simpleExplanation("I couldn't find sufficient verified information for this specific question in the official knowledge base. Please consult a qualified legal professional or the relevant District Legal Services Authority (DLSA).")
                    .isDemoMode(!isLiveProviderAvailable())
                    .confidenceScore(0.50)
                    .build();
        }

        Right right = matchedRightOpt.get();

        String lawTitle = "Statutory Law of India";
        String section = "Applicable Sections";
        if (right.getLaws() != null && !right.getLaws().isEmpty()) {
            Law law = right.getLaws().iterator().next();
            lawTitle = law.getTitle();
            if (law.getProvisions() != null && !law.getProvisions().isEmpty()) {
                LawProvision prov = law.getProvisions().get(0);
                section = prov.getSectionNumber() + " - " + prov.getTitle();
            }
        }

        List<String> actions = parseDelimitedList(right.getRecommendedActions());
        List<String> docs = parseDelimitedList(right.getRequiredDocuments());

        if (actions.isEmpty()) {
            actions = Arrays.asList(
                    "Collect all written agreements and transactional evidence",
                    "Issue a formal written demand notice to the opposite party",
                    "Approach the designated official authority or portal",
                    "Track the docket number until resolution"
            );
        }

        if (docs.isEmpty()) {
            docs = Arrays.asList(
                    "Identity proof (Aadhaar / Voter ID)",
                    "Relevant contract, receipt, or transaction statement",
                    "Written correspondence or email proof"
            );
        }

        String explanation = right.getSimpleExplanation();
        String example = right.getRealWorldExample();

        if ("hi".equalsIgnoreCase(detectedLang)) {
            explanation = "आपको कानून के तहत इस अधिकार का पूर्ण संरक्षण प्राप्त है। " + right.getSimpleExplanation();
        } else if ("te".equalsIgnoreCase(detectedLang)) {
            explanation = "చట్టం ప్రకారం మీకు ఈ హక్కు కింద పూర్తి రక్షణ ఉంది. " + right.getSimpleExplanation();
        }

        return ProblemDto.AIAnalysisResponse.builder()
                .summary("Analysis completed based on verified legal knowledge base.")
                .detectedLanguage(detectedLang)
                .classifiedCategory(right.getCategory() != null ? right.getCategory().getName() : category)
                .relevantRightId(right.getId())
                .relevantRightTitle(right.getTitle())
                .relevantRightSlug(right.getSlug())
                .applicableLaw(lawTitle)
                .applicableSection(section)
                .simpleExplanation(explanation)
                .realWorldExample(example)
                .whatYouShouldDoNow(actions)
                .actionChecklist(actions)
                .documentChecklist(docs)
                .relevantAuthority(right.getRelevantAuthority() != null ? right.getRelevantAuthority() : "Competent District Authority")
                .relevantService(right.getRelevantService() != null ? right.getRelevantService() : "Official Citizen Redressal Portal")
                .officialSourceUrl(right.getOfficialSourceUrl())
                .sourceAuthority(right.getSourceAuthority())
                .lastVerified(right.getLastVerified() != null ? right.getLastVerified().toString() : "Current")
                .confidenceScore(0.94)
                .isDemoMode(!isLiveProviderAvailable())
                .build();
    }

    @Override
    public ProblemDto.VoiceTranscriptionResponse transcribeAudio(ProblemDto.VoiceInputRequest request) {
        return speechToTextService.transcribe(request);
    }

    @Override
    public ProblemDto.TextToSpeechResponse synthesizeSpeech(ProblemDto.TextToSpeechRequest request) {
        return textToSpeechService.synthesize(request);
    }

    private List<String> parseDelimitedList(String raw) {
        if (raw == null || raw.isBlank()) {
            return Collections.emptyList();
        }
        String[] lines = raw.split("\n|,\\s*(?=[0-9]\\.)|;");
        List<String> clean = new ArrayList<>();
        for (String line : lines) {
            String trimmed = line.replaceAll("^[0-9]+\\.\\s*", "").trim();
            if (!trimmed.isEmpty()) {
                clean.add(trimmed);
            }
        }
        return clean;
    }
}
