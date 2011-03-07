package skeleton.skull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import skeleton.exception.ResourceNotFound;

/**
 * Exposed endpoint for Skull
 */
@Controller
@RequestMapping(value = "/skull")
public class SkullController {

	/** The skull dao. */
	@Autowired
	SkullDAO skullDAO;

	/**
	 * Looks up a skull.
	 * 
	 * @param id
	 *            the skull id
	 * @param model
	 *            the mvc model
	 * @return view points to skull
	 * @throws ResourceNotFound
	 *             the resource was not found
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView findBooking(@PathVariable Integer id, Model model)
			throws ResourceNotFound {
		Skull skull = skullDAO.findById(id);
		model.addAttribute(skull);
		return new ModelAndView("skull");
	}

}
