package net.jeremybrooks.jinx.response;

import com.google.gson.Gson;
import org.junit.Test;

import java.io.InputStreamReader;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * @author Jeremy Brooks
 */
public class ActivityResponseTest {

	/**
	 * Test deserialization of known data.
	 * @throws Exception
	 */
	@Test
	public void testUserPhotosFromJson() throws Exception {
		InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/sample_user_photos.json"));
		ActivityResponse response = new Gson().fromJson(reader, ActivityResponse.class);
		reader.close();

		assertEquals("ok", response.getStat());
		assertEquals(1, response.getPage());
		assertEquals(1, response.getPages());
		assertEquals(10, response.getPerPage());
		assertEquals(5, response.getTotal());
		assertEquals(5, response.getItemList().size());

		for (ActivityResponse.Item item : response.getItemList()) {
			assertEquals("photo", item.getType());
			assertNotNull(item.getId());
			assertEquals("Jeremy Brooks", item.getOwnerName());
			assertEquals("85853333@N00", item.getOwner());
			assertNotNull(item.getIconServer());
			assertEquals(1, item.getIconFarm());
			assertNotNull(item.getSecret());
			assertNotNull(item.getServer());
			assertEquals(9, item.getFarm());
			assertNotNull(item.getTitle());
			assertEquals("photo", item.getMedia());
			assertTrue(item.getComments() > 0);
			assertTrue(item.getNotes() > 0);
			assertTrue(item.getViews() > 0);
			assertTrue(item.getFaves() > 0);

			assertNotNull(item.getActivityEventList());
			assertTrue(item.getActivityEventList().size() > 0);

			for (ActivityResponse.Event event : item.getActivityEventList()) {
				assertNotNull(event.getType());
				if (event.getType().equals("fave")) {
					assertNotNull(event.getUser());
					assertNotNull(event.getUsername());
					assertNotNull(event.getIconServer());
					assertTrue(event.getIconFarm() > 0);
					assertNotNull(event.getDateAdded());
				} else if (event.getType().equals("comment")) {
					assertNotNull(event.getUser());
					assertNotNull(event.getUsername());
					assertNotNull(event.getIconServer());
					assertTrue(event.getIconFarm() > 0);
					assertNotNull(event.getDateAdded());
					assertNotNull(event.getCommentId());
					assertNotNull(event.getContent());
				} else { // unexpected type
					fail("Unexpected type: " + event.getType());
				}
			}
		}
	}


	/**
	 * Test deserialization of known data.
	 * @throws Exception
	 */
	@Test
		public void testUserCommentsFromJson() throws Exception {
			InputStreamReader reader = new InputStreamReader(ActivityResponseTest.class.getResourceAsStream("/response/sample_user_comments.json"));
			ActivityResponse response = new Gson().fromJson(reader, ActivityResponse.class);
			reader.close();

		assertEquals("ok", response.getStat());
		assertEquals(1, response.getPage());
		assertEquals(1, response.getPages());
		assertEquals(10, response.getPerPage());
		assertEquals(9, response.getTotal());
		assertEquals(9, response.getItemList().size());

		for (ActivityResponse.Item item : response.getItemList()) {
			assertEquals("photo", item.getType());
			assertNotNull(item.getId());
			assertNotNull(item.getOwner());
			assertNotNull(item.getOwnerName());
			assertNotNull(item.getIconServer());
			assertTrue(item.getIconFarm() > 0);
			assertNotNull(item.getSecret());
			assertNotNull(item.getServer());
			assertTrue(item.getFarm() > 0);
			assertNotNull(item.getTitle());
			assertEquals("photo", item.getMedia());
			assertTrue(item.getComments() > 0);
			assertTrue(item.getNotes() > 0);
			assertTrue(item.getViews() > 0);
			assertTrue(item.getFaves() > 0);

			assertNotNull(item.getActivityEventList());
			assertTrue(item.getActivityEventList().size() > 0);

			for (ActivityResponse.Event event : item.getActivityEventList()) {
				assertNotNull(event.getType());
				assertNotNull(event.getUser());
				assertNotNull(event.getUsername());
				assertNotNull(event.getIconServer());
				assertTrue(event.getIconFarm() > 0);
				assertNotNull(event.getDateAdded());
				assertNotNull(event.getCommentId());
				assertNotNull(event.getContent());
			}
		}
	}
}