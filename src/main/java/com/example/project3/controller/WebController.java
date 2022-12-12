package com.example.project3.controller;

import com.example.project3.entity.Registration;
import com.example.project3.model.RegistrationModel;
import com.example.project3.model.RegistrationUpdate;
import com.example.project3.model.StudentSlotModel;
import com.example.project3.repository.RegistrationRepository;
import com.example.project3.response.RegisterStudentResponse;
import com.example.project3.service.interfaces.BaseService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {

	@Autowired
	private BaseService baseService;

	@Autowired
	private RegistrationRepository registrationRepository;

	@RequestMapping(value = "/index")
	public String index(Model model) {

		baseService.setIndexParams(model);
		return "index";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String openRegistration(Model model, @RequestParam("slot_id") Long slotId) {
		RegistrationModel registrationModel = new RegistrationModel();
		registrationModel.setSlotId(slotId);
		model.addAttribute("data", registrationModel);
		return "registration";
	}

	@RequestMapping(value = "registrationSubmit", method = RequestMethod.POST)
	public RedirectView registerStudent(Model model, @ModelAttribute RegistrationModel registrationModel,
			RedirectAttributes redirectAttributes) {
		RegisterStudentResponse response = baseService.registerStudent(registrationModel);
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		if (Boolean.TRUE.equals(response.getSuccess())) {
			redirectView.setUrl("success");
			redirectAttributes.addFlashAttribute("action", "registered");
		} else if (Boolean.FALSE.equals(response.getSuccess())
				&& Boolean.TRUE.equals(response.getAlreadyRegistered())) {
			redirectView.setUrl("update");
			redirectAttributes.addFlashAttribute("student", response.getStudent());
			redirectAttributes.addFlashAttribute("existingSlot", response.getExistingSlot());
			redirectAttributes.addFlashAttribute("newSlot", response.getNewSlot());
		} else {
			redirectView.setUrl("index");
		}
		return redirectView;
	}

	@RequestMapping(value = "/success")
	public String success(Model model) {
		return "success";
	}

	@RequestMapping(value = "/update")
	public String getUpdate(Model model) {
		return "update";
	}

	@GetMapping({ "/list", "/" })
	public ModelAndView getAllStudentRegistrationDetails() {
		List<Registration> slotModels = baseService.getStudentSlotDetails();
		ModelAndView mav = new ModelAndView("studentSlotDetails");
		mav.addObject("studentsDetails", slotModels);
		return mav;
	}

	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Long registrationId) {
		ModelAndView mav = new ModelAndView("update-registration-form");
		Registration registrationDetails = registrationRepository.findById(registrationId).get();
		mav.addObject("registrationDetails", registrationDetails);
		return mav;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public RedirectView updateSlot(Model model, @ModelAttribute RegistrationUpdate registrationUpdate,
			RedirectAttributes redirectAttributes) {
		List<Registration> slotModels = baseService.getStudentSlotDetails();
		baseService.updateSlot(registrationUpdate);
		RedirectView redirectView = new RedirectView();
		redirectView.setContextRelative(true);
		redirectView.setUrl("studentSlotDetails");
		redirectAttributes.addFlashAttribute("studentsDetails", slotModels);
		return redirectView;
	}

}
