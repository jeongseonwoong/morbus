package OpenSourceProject.morbus.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

import OpenSourceProject.morbus.VOclass.Disease;
import OpenSourceProject.morbus.VOclass.SearchText;
import OpenSourceProject.morbus.VOclass.Symptom;
import OpenSourceProject.morbus.algorithm.DiseaseSetting;
import OpenSourceProject.morbus.algorithm.SymptomRecord;
import OpenSourceProject.morbus.algorithm.SymptomSetting;
import OpenSourceProject.morbus.algorithm.SymptomRecordService;
import OpenSourceProject.morbus.controller.MorbusController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MorbusController.class)
public class MorbusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiseaseSetting diseaseSetting;

    @MockBean
    private SymptomSetting symptomSetting;

    @MockBean
    private SymptomRecordService symptomRecordService;

    @Test
    public void testMainPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("../static/morbus"));
    }

    @Test
    public void testToMainPage() throws Exception {
        mockMvc.perform(get("/morbus.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("../static/morbus"));
    }

    @Test
    public void testGetSymptomList() throws Exception {
        // 가짜 증상 목록 생성
        List<Symptom> SymptomList = new ArrayList<>();
        Symptom Symptom1 = new Symptom();
        Symptom Symptom2 = new Symptom();
        ArrayList<String> keylist = new ArrayList<>();
        ArrayList<Disease> diseaselist = new ArrayList<>();
        keylist.add("Symptom1");
        keylist.add("Symptom2");
        Disease disease = new Disease("감기","s","s","d","e");
        diseaselist.add(disease);
        Symptom1.set("기침",diseaselist,keylist);
        Symptom2.set("오한",diseaselist,keylist);
        SymptomList.add(Symptom1);
        SymptomList.add(Symptom2);

        when(symptomSetting.findAllSymptom()).thenReturn(SymptomList);

        mockMvc.perform(get("/getSymptomList"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0]").value("기침"))
                .andExpect(jsonPath("$[1]").value("오한"));
    }


    @Test
    public void testSearchSym_ExistingSymptom() throws Exception {
        // 가짜 SearchText 객체 생성
        SearchText searchText = new SearchText();
        searchText.setSearchText("headache");

        // 가짜 Symptom 객체 생성
        Symptom symptom = new Symptom();
        ArrayList<String> keylist = new ArrayList<>();
        ArrayList<Disease> diseaselist = new ArrayList<>();
        keylist.add("Symptom1");
        keylist.add("Symptom2");
        Disease disease = new Disease("감기","s","s","d","e");
        diseaselist.add(disease);
        symptom.set("기침",diseaselist,keylist);

        // SymptomSetting 목 객체 설정
        when(symptomSetting.findSymptomByName("headache")).thenReturn(Optional.of(symptom));

        // POST 요청 테스트
        mockMvc.perform(post("/selectSymptom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"headache\"}")) // JSON 형식의 데이터 전송
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchSym_NonExistingSymptom() throws Exception {
        // 가짜 SearchText 객체 생성
        SearchText searchText = new SearchText();
        searchText.setSearchText("cough");

        // SymptomSetting 목 객체 설정
        when(symptomSetting.findSymptomByName("cough")).thenReturn(Optional.empty());

        // POST 요청 테스트
        mockMvc.perform(post("/selectSymptom")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"cough\"}")) // JSON 형식의 데이터 전송
                .andExpect(status().isOk());
    }

    @Test
    public void testSymptom() throws Exception {
        List<Symptom> symptoms = new ArrayList<>();
        when(symptomSetting.findAllSymptom()).thenReturn(symptoms);

        mockMvc.perform(get("/Symptom"))
                .andExpect(status().isOk())
                .andExpect(view().name("selectSymptom"))
                .andExpect(model().attribute("SymList", symptoms));
    }

    @Test
    public void testMedicineInfo() throws Exception {
        mockMvc.perform(get("/MedicineInfo"))
                .andExpect(status().isOk())
                .andExpect(view().name("MedicineInfo"));
    }


    @Test
    public void testRelateDisease() throws Exception {
        // Add necessary mock behavior for SymptomSetting and DiseaseSetting
        mockMvc.perform(post("/RelateDisease")
                        .param("Symptom", "headache"))
                .andExpect(status().isOk())
                .andExpect(view().name("RelateDisease"));
    }


    @Test
    public void testDiseaseInfo() throws Exception {
        Disease disease = new Disease("독감","a","b","c","d");
        when(diseaseSetting.findByName("독감")).thenReturn(Optional.of(disease));

        mockMvc.perform(post("/diseaseInfo")
                        .param("diseaseName", "독감"))
                .andExpect(status().isOk())
                .andExpect(view().name("diseaseInfo"))
                .andExpect(model().attribute("diseaseName", "독감"))
                .andExpect(model().attributeExists("disease"));
    }

    @Test
    public void testRecordSymptom() throws Exception {
        mockMvc.perform(post("/Symptom_record")
                        .param("message", "Headache"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/Symptom_record"));
    }

    @Test
    public void testShowRecords() throws Exception {
        List<SymptomRecord> records = new ArrayList<>();
        when(symptomRecordService.getAllRecords(1L)).thenReturn(records);

        mockMvc.perform(get("/Symptom_record"))
                .andExpect(status().isOk())
                .andExpect(view().name("Symptom_record"))
                .andExpect(model().attribute("records", records));
    }
}