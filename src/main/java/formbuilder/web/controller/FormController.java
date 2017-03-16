package formbuilder.web.controller;

import java.util.List;


import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import formbuilder.model.Block;
import formbuilder.model.Form;
import formbuilder.model.Page;
import formbuilder.model.dao.FormDao;
import formbuilder.model.dao.UserDao;

@Controller
@SessionAttributes({ "forms", "form","page" })
public class FormController {

	@Autowired
	private FormDao formDao;
	@Autowired
	private UserDao userDao;

	@RequestMapping("/form/list.html")
	public String listForm(ModelMap models) {
		models.put("forms", formDao.getForms());
		return "form/list";
	}

	@RequestMapping("/form/view/{id}.html")
	public String viewForm(@PathVariable Integer id, ModelMap models) {
		Form form = formDao.getForm(id);
		models.put("form", form);
		return "form/view";
	}

	@RequestMapping(value = "/form/add.html", method = RequestMethod.GET)
	public String addForm(ModelMap models) {
		models.put("form", new Form());
		models.put("forms", formDao.getForms());

		return "form/add";
	}

	@RequestMapping(value = "/form/add.html", method = RequestMethod.POST)
	public String addForm(@RequestParam String name, @RequestParam String description, @RequestParam String available) {

		// TODO: instead of creating new, use form object in session scope
		Form form = new Form();
		form.setName(name);
		form.setDescription(description);

		if (available.equals("available")) {
			form.setAvailable(true);
		} else {
			form.setAvailable(false);
		}

		// TODO: for testing before we have user login, this should be replaced
		// later by userId in the session
		int id = 2;
		form.setUser(userDao.getUser(id));

		form.setCreateDate(new java.sql.Date(new java.util.Date().getTime()));

		formDao.saveForm(form);

		// TODO: This should actually be redirecting the the form details page
		// to create the form specifics
		return "redirect:/form/list.html";
	}

	@RequestMapping(value = "/form/add_page.html", method = RequestMethod.GET)
	public String addPage(@RequestParam Integer id, ModelMap models) {

		Form form = formDao.getForm(id);
		// find next page number
		List<Page> pages = form.getPages();
		int nextPageNum = pages.size() + 1;

		// create new page and save to db
		Page page = new Page();
		page.setForm(form);
		page.setPageNumber(nextPageNum);
		formDao.savePage(page);

		// show form view again
		models.put("form", form);
		return viewForm(id, models);
	}

	@RequestMapping(value = "/form/page_view.html", method = RequestMethod.GET)
	public String viewPage(@RequestParam Integer id, @RequestParam Integer p, ModelMap models) {
		Form form = formDao.getForm(id);
		List<Page> pages = form.getPages();
		Page page = null;
		if (p >= 1) {
			page = pages.get(p - 1);
		} else {
			page = pages.get(0);
		}

		models.put("page", page);
		models.put("form", form);

		return "form/pageview";
	}

	@RequestMapping(value = "/form/edit/{id}.html", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, ModelMap models) {
		models.put("form", formDao.getForm(id));
		// models.put("forms", formDao.getForms());

		return "form/edit";
	}

	@RequestMapping(value = "/form/edit/{id}.html", method = RequestMethod.POST)
	public String edit(@ModelAttribute Form form, BindingResult result, SessionStatus sessionStatus) {

		// TODO: add formValidator here
		// if (result.hasErrors() ) return "admin/formEdit";

		form = formDao.saveForm(form);
		sessionStatus.setComplete();

		return "redirect:/form/list.html";
	}

	@RequestMapping(value = "/form/remove/{id}.html", method = RequestMethod.POST)
	public String remove(@ModelAttribute Form form, BindingResult result, SessionStatus sessionStatus) {

		// TODO: need to implement delete function user FormDao

		return "redirect:form/list.html";
	}
}