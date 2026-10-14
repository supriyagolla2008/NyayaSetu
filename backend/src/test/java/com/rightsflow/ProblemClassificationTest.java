package com.rightsflow;

import com.rightsflow.ai.ProblemClassificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ProblemClassificationTest {

    @Autowired
    private ProblemClassificationService classificationService;

    @Test
    void testCrimeAndPersonalSafetyClassification() {
        String queryEn = "Police officer refused to file zero fir after illegal arrest without warrant bail custody";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Crime, Personal Safety & Victim Support", category);

        String queryHi = "पुलिस ने एफआईआर (FIR) दर्ज करने से मना कर दिया और अवैध हिरासत में रखा";
        String categoryHi = classificationService.classifyCategory(queryHi, null);
        assertEquals("Crime, Personal Safety & Victim Support", categoryHi);
    }

    @Test
    void testConsumerProtectionClassification() {
        String queryEn = "E-commerce platform delivered defective product and seller refused refund warranty on consumer helpline";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Consumer Rights", category);

        String queryTe = "ఆన్‌లైన్ షాపింగ్‌లో లోపభూయిష్ట వస్తువు వచ్చింది, విక్రేత రీఫండ్ నిరాకరించాడు";
        String categoryTe = classificationService.classifyCategory(queryTe, null);
        assertEquals("Consumer Rights", categoryTe);
    }

    @Test
    void testLabourAndEmploymentClassification() {
        String queryEn = "Company terminated employment without notice period and withheld unpaid salary gratuity pf";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Employment & Labour", category);

        String queryHi = "कंपनी ने बिना नोटिस वेतन और ग्रेच्युटी रोकी, अवैध छंटनी की";
        String categoryHi = classificationService.classifyCategory(queryHi, null);
        assertEquals("Employment & Labour", categoryHi);
    }

    @Test
    void testWomenAndGenderJusticeClassification() {
        String queryEn = "Facing domestic violence at in-laws house and dowry harassment POSH workplace";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Women & Gender Rights", category);

        String queryHi = "ससुराल वाले दहेज उत्पीड़न और घरेलू हिंसा कर रहे हैं, संरक्षण आदेश चाहिए";
        String categoryHi = classificationService.classifyCategory(queryHi, null);
        assertEquals("Women & Gender Rights", categoryHi);
    }

    @Test
    void testEducationAndStudentRightsClassification() {
        String queryEn = "School college denied admission under RTE quota and demanding illegal capitation fee ragging";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Education & Student Rights", category);
    }

    @Test
    void testPropertyAndTenancyClassification() {
        String queryEn = "Landlord refused to return security deposit after eviction from rented flat under lease";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Property, Tenancy & Assets", category);

        String queryTe = "ఇంటి యజమాని అద్దె డిపాజిట్ తిరిగి ఇవ్వడానికి నిరాకరిస్తున్నాడు";
        String categoryTe = classificationService.classifyCategory(queryTe, null);
        assertEquals("Property, Tenancy & Assets", categoryTe);
    }

    @Test
    void testCybercrimeAndDigitalRightsClassification() {
        String queryEn = "Fell victim to online banking otp fraud and unauthorized financial cyber crime scam phishing";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Cybercrime & Digital Rights", category);

        String queryHi = "बैंक खाते से ऑनलाइन धोखाधड़ी और साइबर अपराध से पैसे कट गए";
        String categoryHi = classificationService.classifyCategory(queryHi, null);
        assertEquals("Cybercrime & Digital Rights", categoryHi);
    }

    @Test
    void testHealthcareAndPatientRightsClassification() {
        String queryEn = "Private hospital doctor refused emergency care without cash advance and committed medical negligence";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Healthcare & Patient Rights", category);
    }

    @Test
    void testLegalAidAndAccessToJusticeClassification() {
        String queryEn = "I am very poor and cannot afford a court fee, need free lawyer from NALSA DLSA legal aid";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Legal Aid & Access to Justice", category);
    }

    @Test
    void testSeniorCitizensClassification() {
        String queryEn = "Elderly parents abandoned by son seeking maintenance of parents under senior citizen tribunal";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Senior Citizens", category);
    }

    @Test
    void testChildrenAndChildProtectionClassification() {
        String queryEn = "Minor forced into illegal child labour and facing abuse under POCSO safeguards";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Children & Child Protection", category);
    }

    @Test
    void testPersonsWithDisabilitiesClassification() {
        String queryEn = "Government office denied wheelchair ramp accessibility and reasonable accommodation under RPwD disability";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Persons with Disabilities", category);
    }

    @Test
    void testGovernmentServicesAndRtiClassification() {
        String queryEn = "Public Information Officer PIO rejected RTI application 30 days and refused disclosure first appeal";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Government Services & Citizen Rights", category);

        String queryHi = "सूचना का अधिकार (RTI) के तहत आवेदन का जवाब 30 दिनों में नहीं दिया गया";
        String categoryHi = classificationService.classifyCategory(queryHi, null);
        assertEquals("Government Services & Citizen Rights", categoryHi);
    }

    @Test
    void testFamilyAndPersonalMattersClassification() {
        String queryEn = "Filing for mutual consent divorce, alimony, and child custody maintenance in family court";
        String category = classificationService.classifyCategory(queryEn, null);
        assertEquals("Family & Personal Matters", category);
    }

    @Test
    void testLanguageDetection() {
        assertEquals("en", classificationService.detectLanguage("Hello world"));
        assertEquals("hi", classificationService.detectLanguage("नमस्ते मुझे मदद चाहिए"));
        assertEquals("te", classificationService.detectLanguage("నాకు న్యాయ సహాయం కావాలి"));
    }
}
