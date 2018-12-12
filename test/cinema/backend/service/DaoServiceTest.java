/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema.backend.service;

import cinema.backend.dao.DaoManager;
import cinema.backend.entities.Film;
import cinema.backend.entities.Room;
import cinema.backend.entities.Seats;
import cinema.backend.entities.Show;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author CzP
 */
public class DaoServiceTest {
  
  @Mock
  private DaoManager dm;
  
  private DaoService underTest;
  
  public DaoServiceTest() {
  }
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    underTest = new DaoService(dm);
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of listAllFilms method, of class DaoService.
   */
  @Test
  public void testListAllFilms() {
    List<Film> movies = new ArrayList<>();
    BDDMockito.given(dm.listAllFilms()).willReturn(movies);
    
    List<Film> result = underTest.listAllFilms();
    
    Assert.assertEquals(movies, result);
    BDDMockito.verify(dm).listAllFilms();
  }

//  /**
//   * Test of getFilm method, of class DaoService.
//   */
//  @Test
//  public void testGetFilm() {
//    System.out.println("getFilm");
//    Long filmId = null;
//    DaoService instance = new DaoService();
//    Film expResult = null;
//    Film result = instance.getFilm(filmId);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of listAllShows method, of class DaoService.
//   */
//  @Test
//  public void testListAllShows() {
//    System.out.println("listAllShows");
//    DaoService instance = new DaoService();
//    List<Show> expResult = null;
//    List<Show> result = instance.listAllShows();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of listAllRooms method, of class DaoService.
//   */
//  @Test
//  public void testListAllRooms() {
//    System.out.println("listAllRooms");
//    DaoService instance = new DaoService();
//    List<Room> expResult = null;
//    List<Room> result = instance.listAllRooms();
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of listShowsByFilmId method, of class DaoService.
//   */
//  @Test
//  public void testListShowsByFilmId() {
//    System.out.println("listShowsByFilmId");
//    Long filmId = null;
//    DaoService instance = new DaoService();
//    List<Show> expResult = null;
//    List<Show> result = instance.listShowsByFilmId(filmId);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of listShowsByRoom method, of class DaoService.
//   */
//  @Test
//  public void testListShowsByRoom() {
//    System.out.println("listShowsByRoom");
//    String roomName = "";
//    DaoService instance = new DaoService();
//    List<Show> expResult = null;
//    List<Show> result = instance.listShowsByRoom(roomName);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getShow method, of class DaoService.
//   */
//  @Test
//  public void testGetShow() {
//    System.out.println("getShow");
//    Long showId = null;
//    DaoService instance = new DaoService();
//    Show expResult = null;
//    Show result = instance.getShow(showId);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of getRoom method, of class DaoService.
//   */
//  @Test
//  public void testGetRoom() {
//    System.out.println("getRoom");
//    String roomName = "";
//    DaoService instance = new DaoService();
//    Room expResult = null;
//    Room result = instance.getRoom(roomName);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of listSeatsByShowId method, of class DaoService.
//   */
//  @Test
//  public void testListSeatsByShowId() {
//    System.out.println("listSeatsByShowId");
//    Long showId = null;
//    DaoService instance = new DaoService();
//    Seats expResult = null;
//    Seats result = instance.listSeatsByShowId(showId);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of modifySeatsStatus method, of class DaoService.
//   */
//  @Test
//  public void testModifySeatsStatus() {
//    System.out.println("modifySeatsStatus");
//    Seats seats = null;
//    DaoService instance = new DaoService();
//    instance.modifySeatsStatus(seats);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of saveShow method, of class DaoService.
//   */
//  @Test
//  public void testSaveShow() {
//    System.out.println("saveShow");
//    Long filmId = null;
//    String roomName = "";
//    LocalDate startDate = null;
//    LocalTime startTime = null;
//    DaoService instance = new DaoService();
//    Long expResult = null;
//    Long result = instance.saveShow(filmId, roomName, startDate, startTime);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of updateShow method, of class DaoService.
//   */
//  @Test
//  public void testUpdateShow() {
//    System.out.println("updateShow");
//    Long showId = null;
//    Long filmId = null;
//    String roomName = "";
//    LocalDate startDate = null;
//    LocalTime startTime = null;
//    DaoService instance = new DaoService();
//    instance.updateShow(showId, filmId, roomName, startDate, startTime);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of deleteShow method, of class DaoService.
//   */
//  @Test
//  public void testDeleteShow() {
//    System.out.println("deleteShow");
//    Long showId = null;
//    DaoService instance = new DaoService();
//    instance.deleteShow(showId);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of listShowsByFilmIdAtDate method, of class DaoService.
//   */
//  @Test
//  public void testListShowsByFilmIdAtDate() {
//    System.out.println("listShowsByFilmIdAtDate");
//    Long filmId = null;
//    LocalDate date = null;
//    DaoService instance = new DaoService();
//    List<Show> expResult = null;
//    List<Show> result = instance.listShowsByFilmIdAtDate(filmId, date);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
//
//  /**
//   * Test of listShowsByRoomAtDate method, of class DaoService.
//   */
//  @Test
//  public void testListShowsByRoomAtDate() {
//    System.out.println("listShowsByRoomAtDate");
//    String roomName = "";
//    LocalDate date = null;
//    DaoService instance = new DaoService();
//    List<Show> expResult = null;
//    List<Show> result = instance.listShowsByRoomAtDate(roomName, date);
//    assertEquals(expResult, result);
//    // TODO review the generated test code and remove the default call to fail.
//    fail("The test case is a prototype.");
//  }
  
}
