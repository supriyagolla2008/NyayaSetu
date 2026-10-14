package com.rightsflow.ai;

import com.rightsflow.entity.LegalCategory;
import com.rightsflow.repository.LegalCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class ProblemClassificationService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProblemClassificationService.class);

    public ProblemClassificationService(LegalCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    private final LegalCategoryRepository categoryRepository;

    private static final Map<String, String[]> KEYWORD_RULES = new HashMap<>();

    static {
        KEYWORD_RULES.put("Women & Gender Rights", new String[]{
                "sexual harassment", "posh", "workplace harassment", "domestic violence", "dowry", "maternity", "woman", "women", "wife", "in-laws", "coparcenary", "equal pay", "mahila",
                "दहेज", "घरेलू हिंसा", "महिला", "पत्नी", "ससुराल", "उत्पीड़न", "संरक्षण आदेश",
                "వరకట్నం", "గృహ హింస", "మహిళ", "వేధింపులు"
        });
        KEYWORD_RULES.put("Employment & Labour", new String[]{
                "salary", "wage", "employer", "boss", "job", "notice period", "termination", "gratuity", "provident fund", "pf", "overtime", "unpaid", "layoff", "severance", "workplace safety",
                "वेतन", "तनख्वाह", "मजदूरी", "ग्रेच्युटी", "छंटनी", "नौकरी", "मालिक", "पीएफ", "बकाया",
                "జీతం", "వేతనం", "ఉద్యోగం", "యజమాని", "తొలగింపు"
        });
        KEYWORD_RULES.put("Education & Student Rights", new String[]{
                "school", "college", "fee hike", "rte", "admission", "capitation", "ragging", "student", "degree certificate", "university", "anti-ragging",
                "स्कूल", "कॉलेज", "फीस", "दाखिला", "रैगिंग", "छात्र", "విద్యా హక్కు", "ఫీజు"
        });
        KEYWORD_RULES.put("Healthcare & Patient Rights", new String[]{
                "hospital", "doctor", "medical negligence", "overcharging", "emergency care", "patient rights", "bills", "discharge", "charter", "clinical establishment", "consent",
                "अस्पताल", "डॉक्टर", "इलाज", "दवा", "आपातकालीन", "వైద్యం", "ఆసుపత్రి"
        });
        KEYWORD_RULES.put("Property, Tenancy & Assets", new String[]{
                "landlord", "tenant", "rent", "security deposit", "eviction", "lease", "flat", "rera", "builder", "property", "partition", "land acquisition", "registration", "encroachment",
                "किराया", "मकान मालिक", "किराएदार", "डिपॉजिट", "बेदखली", "फ्लैट", "जमीन", "संपत्ति",
                "అద్దె", "ఇంటి యజమాని", "డిపాజిట్", "ఖాళీ చేయించడం", "ఆస్తి"
        });
        KEYWORD_RULES.put("Family & Personal Matters", new String[]{
                "divorce", "maintenance", "custody", "child custody", "alimony", "mutual consent", "marriage", "succession", "will", "probate", "domestic",
                "तलाक", "गुजारा भत्ता", "शादी", "विवाह", "విడాకులు", "భరణం"
        });
        KEYWORD_RULES.put("Consumer Rights", new String[]{
                "product", "defective", "warranty", "refund", "seller", "merchant", "amazon", "flipkart", "damaged", "replacement", "tv", "refrigerator", "e-commerce", "unfair trade", "consumer helpline",
                "उपभोक्ता", "खराब सामान", "वारंटी", "रिफंड", "दुकानदार",
                "వినియోగదారుల", "లోపభూయిష్ట", "రీఫండ్", "వస్తువు"
        });
        KEYWORD_RULES.put("Cybercrime & Digital Rights", new String[]{
                "cyber", "fraud", "hacked", "otp", "upi", "scam", "phishing", "bank account debit", "unauthorized", "fake caller", "data leak", "privacy", "dpdp", "stalking", "deepfake", "identity theft",
                "साइबर", "धोखाधड़ी", "ओटीपी", "बैंक खाता", "ऑनलाइन ठगी", "हैकिंग",
                "సైబర్", "మోసం", "ఓటీపీ", "ఆన్‌లైన్ ఫ్రాడ్"
        });
        KEYWORD_RULES.put("Government Services & Citizen Rights", new String[]{
                "rti", "right to information", "aadhaar", "ration card", "passport", "voter id", "pension", "cpgrams", "public authority", "pio", "first appeal",
                "सूचना का अधिकार", "आरटीआई", "आधार", "राशन कार्ड", "पेंशन", "अपील",
                "సమాచార హక్కు", "రేషన్ కార్డు", "ఆధార్"
        });
        KEYWORD_RULES.put("Legal Aid & Access to Justice", new String[]{
                "free lawyer", "legal aid", "cannot afford", "nalsa", "dlsa", "court fee", "lok adalat", "poverty", "poor", "tele-law", "legal aid defense",
                "मुफ्त वकील", "कानूनी सहायता", "नालसा", "लोक अदालत", "ఉచిత న్యాయ సహాయం"
        });
        KEYWORD_RULES.put("Senior Citizens", new String[]{
                "senior citizen", "elderly", "parents", "old age", "maintenance of parents", "gift deed cancel", "abandoned by son", "pension", "geriatric", "elder line",
                "वरिष्ठ नागरिक", "बुजुर्ग", "माता-पिता", "वृद्धाश्रम", "వయోవృద్ధులు"
        });
        KEYWORD_RULES.put("Children & Child Protection", new String[]{
                "child", "child labour", "pocso", "orphan", "adoption", "cara", "minor", "child helpline", "abuse", "school corporal punishment",
                "बाल श्रम", "पोक्सो", "अनाथ", "बच्चे", "బాల కార్మిక", "చిన్నారులు"
        });
        KEYWORD_RULES.put("Persons with Disabilities", new String[]{
                "disability", "disabled", "wheelchair", "accessibility", "reservation", "rpwd", "divyang", "pwd", "hearing impaired", "visually impaired",
                "दिव्यांग", "विकलांग", "व्हीलचेयर", "దివ్యాంగులు"
        });
        KEYWORD_RULES.put("Crime, Personal Safety & Victim Support", new String[]{
                "fir", "zero fir", "police", "arrest", "bail", "custodial", "victim compensation", "cognizable", "witness protection", "chargesheet", "police harassment",
                "एफआईआर", "पुलिस", "गिरफ्तारी", "जमानत", "हिरासत", "थाना",
                "ఎఫ్‌ఐఆర్", "పోలీస్", "అరెస్ట్", "బెయిల్"
        });
    }

    public String classifyCategory(String query, String userHint) {
        if (userHint != null && !userHint.isBlank()) {
            return userHint;
        }

        String lowerQuery = query != null ? query.toLowerCase(Locale.ROOT) : "";
        String bestCategory = "Consumer Rights";
        int maxMatches = 0;

        for (Map.Entry<String, String[]> entry : KEYWORD_RULES.entrySet()) {
            int score = 0;
            for (String kw : entry.getValue()) {
                if (lowerQuery.contains(kw)) {
                    score += 2;
                }
            }
            if (score > maxMatches) {
                maxMatches = score;
                bestCategory = entry.getKey();
            }
        }

        log.debug("Classified query '{}' as category '{}' with match score {}", query, bestCategory, maxMatches);
        return bestCategory;
    }

    public String detectLanguage(String text) {
        if (text == null || text.isBlank()) {
            return "en";
        }
        // Check for Devanagari script (Hindi)
        for (char c : text.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.DEVANAGARI) {
                return "hi";
            }
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.TELUGU) {
                return "te";
            }
        }
        return "en";
    }
}
