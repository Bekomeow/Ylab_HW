//package org.beko.util;
//
//import org.beko.service.AdminService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//public class AdminServiceTest {
//    private AdminService adminService = new AdminService(connectionManager);
//
//    @Test
//    public void testLoginAdminSuccessfully() {
//        assertDoesNotThrow(() -> adminService.login("Admin", "Admin"));
//    }
//
//    @Test
//    public void testLoginAdminInvalidCredentials() {
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            adminService.login("Admin", "WrongPassword");
//        });
//        assertEquals("Invalid admin name or password.", exception.getMessage());
//    }
//}
