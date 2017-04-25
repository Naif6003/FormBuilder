package formbuilder.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import formbuilder.model.uploadFileDao.FileUploadDAO;
import formbuilder.model.uploadfile.UploadFile;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private FileUploadDAO fileUploadDao;

	@RequestMapping({ "/index.html", "/home.html" })
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password.");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}

		model.setViewName("login");

		return model;

	}

	@RequestMapping("/userHome.html")
	public String userHome() {
		return "userHome";
	}
	
	@RequestMapping("/staffHome.html")
	public String staffHome() {
		return "staffHome";
	}

	// ############### file upload controllers ######################

	@RequestMapping(value = "/upload.html", method = RequestMethod.GET)
	public String showUploadForm(HttpServletRequest request) {
		return "/upload";
	}

	@RequestMapping(value = "/upload.html", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request, @RequestParam CommonsMultipartFile[] fileUpload)
			throws Exception {

		if (fileUpload != null && fileUpload.length > 0) {
			for (CommonsMultipartFile aFile : fileUpload) {


				System.out.println("Saving file: " + aFile.getOriginalFilename());
				UploadFile uploadFile = new UploadFile();
				uploadFile.setFileName(aFile.getOriginalFilename());
				uploadFile.setData(aFile.getBytes());
				fileUploadDao.save(uploadFile);
				
				if (!aFile.isEmpty()) {
				try {
					byte[] bytes = aFile.getBytes();

					// Creating the directory to store file

						String rootPath = "/FormBuilder/src/main";
						File dir = new File(rootPath + File.separator + "PDFresource");
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath()
								+ File.separator + aFile.getOriginalFilename());
						BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					logger.info("Server File Location="
							+ serverFile.getAbsolutePath());

						System.out.println("You successfully uploaded file=" + aFile.getOriginalFilename());
				} catch (Exception e) {
						System.out.println(
								"You failed to upload " + aFile.getOriginalFilename() + " => " + e.getMessage());
				}
			} else {
					System.out.println(
							"You failed to upload " + aFile.getOriginalFilename() + " because the file was empty.");
			}
				
				
				
			}
		}

		return "/success";
	}

}
