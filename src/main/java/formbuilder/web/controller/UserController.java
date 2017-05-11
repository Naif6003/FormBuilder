package formbuilder.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import formbuilder.model.core.User;
import formbuilder.model.core.dao.UserDao;
import formbuilder.model.questionform.Form;
import formbuilder.web.validator.UserValidator;

@Controller
@SessionAttributes({ "user", "users" })
public class UserController {

	@Autowired
	private UserDao userDao;


	@Autowired
	private UserValidator userValidator;

	@RequestMapping("/user/list.html")
	public String list(ModelMap models) {

		models.put("users", userDao.getUsers());
		return "user/list";
	}

	@RequestMapping("/user/view.html")
	public String view(@RequestParam Integer id, ModelMap models) {

		models.put("user", userDao.getUser(id));
		
		List<Form> forms = userDao.getUser(id).getForms();
		
		models.put("forms", forms);
		
		return "user/view";
	}

	@RequestMapping(value = "/user/edit.html", method = RequestMethod.GET)
	public String edit(@RequestParam Integer id, ModelMap models) {

		models.put("user", userDao.getUser(id));
		models.put("users", userDao.getUsers());
		return "user/edit";
	}

	@RequestMapping(value = "/user/edit.html", method = RequestMethod.POST)
	public String edit(@ModelAttribute @Valid User user, BindingResult result, SessionStatus sessionStatus) {

		userValidator.validate(user, result);
		if (result.hasErrors())
			return "user/edit";

		user = userDao.saveUser(user);
		sessionStatus.setComplete();

		return "redirect:/user/edit.html?id=" + user.getId();
	}

	@RequestMapping(value = "/user/add.html", method = RequestMethod.GET)
	public String add(ModelMap models) {

		models.put("user", new User());
		return "user/add";
	}

	@RequestMapping(value = "/user/add.html", method = RequestMethod.POST)
	public String add(@ModelAttribute @Valid User user, BindingResult result) {
		userValidator.validate(user, result);

		if (result.hasErrors())
			return "user/add";

		user = userDao.saveUser(user);
		return "redirect:list.html";
	}


	@RequestMapping(value = "/signup.html", method = RequestMethod.GET)
	public String signup(ModelMap models) {

		models.put("user", new User());
		return "/signup";
	}

	@RequestMapping(value = "/signup.html", method = RequestMethod.POST)
	public String signup(@ModelAttribute @Valid User user, BindingResult result) {

		userValidator.validate(user, result);

		if (result.hasErrors())
			return "user/add";

		user = userDao.saveUserSignup(user);
		return "/home";
	}

	@RequestMapping(value = "/user/delete.html")
	public String deleteUser(@RequestParam int id, HttpServletResponse response) {
		userDao.delete(id);
		return "redirect:list.html";
	}

}
