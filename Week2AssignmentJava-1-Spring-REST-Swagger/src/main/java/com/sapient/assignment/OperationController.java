package com.sapient.assignment;

import java.text.ParseException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OperationController implements ErrorController {
	private final static String PATH="/error";
	@Override
    @RequestMapping(PATH)
    @PostMapping
    public String getErrorPath() {
        return "error";
    }
	@GetMapping("/index")
	public String showMenu() {
		return "index";
	}
	@GetMapping("/view1")
	public String one() {
		return "view1";
	}
	@GetMapping("/view2")
	public String two() {
		return "view2";
	}
	@GetMapping("/view3")
	public String three() {
		return "view3";
	}
	@GetMapping("/view4")
	public String four() {
		return "view4";
	}
	@GetMapping("/view5")
	public String five() {
		return "view5";
	}
	@GetMapping("/view6")
	public String six() {
		return "view6";
	}
	@GetMapping("/view7")
	public String seven() {
		return "view7";
	}
	@PostMapping("/view1")
	public String solveOne(Model send,@RequestParam String date1,@RequestParam String date2,@RequestParam String operation) {
		String ans;
		try {
			if(operation.equals("+"))
				ans=DateOperations.dateToString(DateOperations.addDates(DateOperations.stringToDate(date1),DateOperations.stringToDate(date2)));
			else
				ans=DateOperations.substractDates(DateOperations.stringToDate(date1),DateOperations.stringToDate(date2));
		} catch(ParseException e) {
			ans="Invalid Input!";
		} catch(NullPointerException e) {
			ans="Invalid Input!";
		} catch(NumberFormatException e) {
			ans="Invalid Input!";
		}
		send.addAttribute("ans",ans);
		return "result";
	}
	@PostMapping("/view2")
	public String solveTwo(Model send,@RequestParam String date,@RequestParam String days,@RequestParam String operation) {
		String ans;
		try {
			int loc=Integer.parseInt(days);
			if(operation.equals("-"))
				loc=-loc;
			ans=DateOperations.dateToString(DateOperations.addDaysToDate(DateOperations.stringToDate(date),loc));
		} catch(ParseException e) {
			ans="Invalid Input!";
		} catch(NullPointerException e) {
			ans="Invalid Input!";
		} catch(NumberFormatException e) {
			ans="Invalid Input!";
		}
		send.addAttribute("ans",ans);
		return "result";
	}
	@PostMapping("/view3")
	public String solveThree(Model send,@RequestParam String date,@RequestParam String weeks,@RequestParam String operation) {
		String ans;
		try {
			int loc=Integer.parseInt(weeks);
			if(operation.equals("-"))
				loc=-loc;
			ans=DateOperations.dateToString(DateOperations.addWeeksToDate(DateOperations.stringToDate(date),loc));
		} catch(ParseException e) {
			ans="Invalid Input!";
		} catch(NullPointerException e) {
			ans="Invalid Input!";
		} catch(NumberFormatException e) {
			ans="Invalid Input!";
		}
		send.addAttribute("ans",ans);
		return "result";
	}
	@PostMapping("/view4")
	public String solveFour(Model send,@RequestParam String date,@RequestParam String months,@RequestParam String operation) {
		String ans;
		try {
			int loc=Integer.parseInt(months);
			if(operation.equals("-"))
				loc=-loc;
			ans=DateOperations.dateToString(DateOperations.addMonthsToDate(DateOperations.stringToDate(date),loc));
		} catch(ParseException e) {
			ans="Invalid Input!";
		} catch(NullPointerException e) {
			ans="Invalid Input!";
		} catch(NumberFormatException e) {
			ans="Invalid Input!";
		}
		send.addAttribute("ans",ans);
		return "result";
	}
	@PostMapping("/view5")
	public String solveFive(Model send,@RequestParam String date) {
		String ans;
		try {
			ans=DateOperations.findWeekday(DateOperations.stringToDate(date));
		} catch(ParseException e) {
			ans="Invalid Input!";
		}
		send.addAttribute("ans",ans);
		return "result";
	}
	@PostMapping("/view6")
	public String solveSix(Model send,@RequestParam String date) {
		String ans;
		try {
			ans=DateOperations.findWeekNumber(DateOperations.stringToDate(date));
		} catch(ParseException e) {
			ans="Invalid Input!";
		}
		send.addAttribute("ans",ans);
		return "result";
	}
	@PostMapping("/view7")
	public String solveSeven(Model send,@RequestParam String phrase) {
		String ans;
		try {
			ans=DateOperations.dateToString(NaturalPhraseProcessor.convertNaturalPhrase(phrase));
		} catch(ParseException e) {
			ans="Invalid Input!";
		} catch(NullPointerException e) {
			ans="Invalid Input!";
		} catch(NumberFormatException e) {
			ans="Invalid Input!";
		}
		send.addAttribute("ans",ans);
		return "result";
	}
}