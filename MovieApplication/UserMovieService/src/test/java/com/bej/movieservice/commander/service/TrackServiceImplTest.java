//package com.bej.movieservice.commander.service;
//
//import com.bej.movieservice.domain.Artist;
//import com.bej.movieservice.domain.Movie;
//import com.bej.movieservice.domain.User;
//
//import com.bej.movieservice.repository.UserMovieRepository;
//import com.bej.movieservice.service.MovieServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//import java.util.List;
//import java.util.Arrays;
//import java.util.Optional;
//
//
//@ExtendWith(MockitoExtension.class)
//class TrackServiceImplTest {
//
//    @Mock
//    private UserMovieRepository userMovieRepository;
//
//    @InjectMocks
//    private MovieServiceImpl trackService;
//    private Movie track1, track2;
//    List<Movie> trackList;
//    Artist artist1,artist2;
//    User user;
//
//    @BeforeEach
//    void setUp() {
////        artist1 = new Artist("1001","artist1");
////        track1 = new Movie("TR001","track1","Good Track1",4,artist1);
////        artist2 = new Artist("1002","artist2");
////        track2 = new Movie("TR002","track2","Good Track2",4,artist2);
////        trackList = Arrays.asList(track1,track2);
////        user = new User();
////        user.setUserId("U1234");
////        user.setAge(35);
////        user.setUserName("John");
////        user.setEmail("John@gmail.com");
////        user.setTrackList(trackList);
//    }
//    @AfterEach
//    void tearDown() {
//        track1 = null;
//        track2 = null;
//        user = null;
//    }
//
//
//
//    @Test
//    public void getAllUserTracksFromWishListSuccess() throws Exception {
//        when(userMovieRepository.findById(anyString())).thenReturn(Optional.ofNullable(user));
//        when(userMovieRepository.findById(anyString())).thenReturn(Optional.of(user));
//        assertEquals(trackList,trackService.getAllUserTracksFromWishList(user.getUserId()));
//        verify(userMovieRepository,times(2)).findById(anyString());
//
//    }
//
//
//
//
//
//
//}
//
