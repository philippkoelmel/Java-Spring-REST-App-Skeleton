package skeleton.skull;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;

import skeleton.SkullDBFixture;

/**
 * Test the SkullController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-context.xml")
public class SkullControllerTest extends SkullDBFixture {

	/** The application context. */
	@Autowired
	private ApplicationContext applicationContext;

	/** A mock request. */
	private MockHttpServletRequest request;

	/** A mock response. */
	private MockHttpServletResponse response;

	/** The handler adapter which wraps the handleRequest */
	private HandlerAdapter handlerAdapter;

	/** The skull controller to be tested */
	@Autowired
	private SkullController skullController;

	/**
	 * Setup mock-ups
	 */
	@Before
	public void setup() {
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		handlerAdapter = applicationContext.getBean(HandlerAdapter.class);
	}

	/**
	 * Find the first skull.
	 * 
	 * @throws Exception
	 *             thrown by the handlerAdapter
	 */
	@Test
	public void findFirstSkull() throws Exception {
		request.setMethod("GET");
		request.setRequestURI("/skull/1");
		request.addHeader("Accept", "application/json");
		ModelAndView mav = handlerAdapter.handle(request, response,
				skullController);
		Skull skull = (Skull) mav.getModel().get("skull");
		assertEquals(new Integer(1), skull.getId());
	}

	/**
	 * Test if the aspect compiling worked
	 */
	@Test
	public void aspect() {
		assertTrue(skullController.aspectTest());
	}

}
