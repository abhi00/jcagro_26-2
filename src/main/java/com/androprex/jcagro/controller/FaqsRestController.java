package com.androprex.jcagro.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.androprex.jcagro.model.FaqS;
import com.androprex.jcagro.repo.FaqsRepository;


@RestController
@RequestMapping("/api")
public class FaqsRestController {

	@Autowired
	private FaqsRepository faqsRepo;
	

	

    @GetMapping("/faqs")
    public Page<FaqS> getAllPosts(Pageable pageable) {
        return faqsRepo.findAll(pageable);
    }
	
	
    @PostMapping("/faqs")
    public FaqS createFaqS(@Valid @RequestBody FaqS faqs) {
        return faqsRepo.save(faqs);
    }
	
    @PutMapping("/faqs/{faqsId}")
    public FaqS updateFaqS(@PathVariable Long faqsId, @Valid @RequestBody FaqS faqsRequest) {
        return faqsRepo.findById(faqsId).map(faqs -> {
        	faqs.setQuestion(faqsRequest.getQuestion());
        	faqs.setAnswer(faqsRequest.getAnswer());
            return faqsRepo.save(faqs);
        }).orElseThrow(() -> new RuntimeException("FaqsId " + faqsId + " not found"));
    }
	
    @DeleteMapping("/faqs/{faqsId}")
    public ResponseEntity<?> deleteFaqS(@PathVariable Long faqsId) {
        return faqsRepo.findById(faqsId).map(faqs -> {
            faqsRepo.delete(faqs);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new RuntimeException("CategoryId " + faqsId + " not found"));
    }

	
}
