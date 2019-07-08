package br.com.softplan.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.softplan.entity.Pessoa;
import br.com.softplan.exception.CPFInvalidoException;
import br.com.softplan.exception.DataInvalidaException;
import br.com.softplan.exception.EmailInvalidoException;
import br.com.softplan.exception.PessoaNotFoundException;
import br.com.softplan.service.PessoaService;
import br.com.softplan.service.util.ValidaCPF;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	private static final Logger logger = Logger.getLogger(PessoaController.class);

	private final PessoaService pessoaService;
	
	public PessoaController(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

	@GetMapping("list")
	public ModelAndView list(ModelMap modelMap) {
		modelMap.addAttribute("pessoas", pessoaService.findAll());
		modelMap.addAttribute("pessoa", new Pessoa());
		return new ModelAndView("/fragments/pessoas/list", modelMap);
	}

	@GetMapping("/new")
	public ModelAndView newPessoa(ModelMap modelMap) {
		modelMap.addAttribute("pessoa", new Pessoa());
		return new ModelAndView("/fragments/pessoas/new", modelMap);
	}

	@PostMapping("/save")
	public ModelAndView savePessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		try {
			if(pessoa.getEmail() != null && pessoa.getEmail().equals("") && !isValidEmailAddressRegex(pessoa.getEmail()))
				throw new EmailInvalidoException("id-" + pessoa.getId());
			
			if(pessoa.getDataNascimento() == null || ( pessoa.getDataNascimento() != null && !isValidDate(pessoa.getDataNascimento())) )
				throw new DataInvalidaException("id-" + pessoa.getDataNascimento());
			
			if(pessoa.getCpf() == null || ( pessoa.getCpf() != null && !ValidaCPF.isCPF(pessoa.getCpf())) )
				throw new CPFInvalidoException("id-" + pessoa.getCpf());
			
			pessoaService.save(pessoa);
		} catch (Exception e) {
			logger.info("Erro ao incluir a pessoa");
		}

		return new ModelAndView("redirect:/pessoas/list");
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {

		Pessoa pessoa = pessoaService.findById(id);

		if (pessoa == null)
			throw new PessoaNotFoundException("id-" + id);
		
		/*if(pessoa.getEmail() != null && pessoa.getEmail().equals("") && !isValidEmailAddressRegex(pessoa.getEmail()))
			throw new EmailInvalidoException("id-" + pessoa.getId());
		
		if(pessoa.getDataNascimento() == null || ( pessoa.getDataNascimento() != null && !isValidDate(pessoa.getDataNascimento())) )
			throw new DataInvalidaException("id-" + pessoa.getDataNascimento());
		
		if(pessoa.getCpf() == null || ( pessoa.getCpf() != null && !ValidaCPF.isCPF(pessoa.getCpf())) )
			throw new CPFInvalidoException("id-" + pessoa.getCpf());*/
		
		//pessoa = pessoaService.save(pessoa);
		
		model.addAttribute("pessoa", pessoa);
		return new ModelAndView("fragments/pessoas/edit", model);
	}

	@PostMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") Long id, Pessoa pessoa) {
		pessoaService.updateForm(pessoa);
		return new ModelAndView("redirect:/pessoas/list");
	}

	@GetMapping("/remove/{id}")
	public ModelAndView remove(@PathVariable("id") Long id) {
		Pessoa pessoa = pessoaService.findById(id);

		pessoaService.delete(pessoa);
		logger.info("Pessoa excluído");

		return new ModelAndView("redirect:/pessoas/list");
	}
	
	@PostMapping("/buscar")
	public ModelAndView buscar(@ModelAttribute("pessoa") Pessoa pessoa, ModelMap modelMap) {
		modelMap.addAttribute("pessoa",  pessoa);
		
		modelMap.addAttribute("pessoas", pessoaService.findAll());
		
		if (pessoa.getCpf() != null && !pessoa.getCpf().equals("")) {
			modelMap.addAttribute("pessoas", pessoaService.findByCpf(pessoa.getCpf()));
		}
		
		if (pessoa.getNome() != null && !pessoa.getNome().equals("")) {
			modelMap.addAttribute("pessoas", pessoaService.findByName(pessoa.getNome().toUpperCase()));
		}
		
		
		
		return new ModelAndView("/fragments/pessoas/list", modelMap);
	}
	
	public static boolean isValidEmailAddressRegex(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        
        if (email.equals("")) {
        	isEmailIdValid = true;
        }
        return isEmailIdValid;
    }
	
	 public boolean isValidDate(String date) {
      	  try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate d = LocalDate.parse(date,formatter);	
            return true;
      	  } catch (DateTimeParseException e) {
      	  	return false;
      	  }   
      }

}
