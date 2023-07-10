package com.example.projectbackend.service;
import com.example.projectbackend.dto.UserDto;
import com.example.projectbackend.dto.UserOutputDto;
import com.example.projectbackend.model.User;
import com.example.projectbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    @InjectMocks
    UserService userService;

    @Captor
    ArgumentCaptor<User> captor;

    User testUser1;
    User testUser2;


    @BeforeEach
    void setUp() {
        testUser1 = new User("gpfan@mail.nl", "gpfan", "$2a$12$cqKDF2eQ.wL3whWCynfBF.So6L0sNMRdy3LGVfws70zxYyF8uW2Qm");
        testUser2 = new User("racefan@mail.nl", "racefan", "$2a$12$KwFctY8S3Xyt.9/GNLcsFujj.fh60sjm5JRJmzVB7iJ/5SQ0a8R5a");
    }


    @Test
    void getUser() {
        when(userRepository.findById("gpfan")).thenReturn(Optional.of(testUser1));

        UserDto userDto = userService.getUser("gpfan");

        assertEquals("gpfan@mail.nl", userDto.getEmail());
        assertEquals("gpfan", userDto.getUsername());
        assertEquals("$2a$12$cqKDF2eQ.wL3whWCynfBF.So6L0sNMRdy3LGVfws70zxYyF8uW2Qm", userDto.getPassword());
    }

    @Test
    void getUsers() {
        when(userRepository.findAll()).thenReturn(List.of(testUser1, testUser2));
        List<UserOutputDto> usersDtoList = userService.getUsers();
        assertEquals(testUser1.getEmail(), usersDtoList.get(0).getEmail());
        assertEquals(testUser1.getUsername(), usersDtoList.get(0).getUsername());

        assertEquals(testUser2.getEmail(), usersDtoList.get(1).getEmail());
        assertEquals(testUser2.getUsername(), usersDtoList.get(1).getUsername());
    }


    @Test
    void deleteUser() {
        userService.deleteUser("racefan");

        verify(userRepository).deleteById("racefan");
    }

}